package com.heziz.qixia3.ui.zhihui.fdl;

import android.app.Activity;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.qixia3.R;
import com.heziz.qixia3.adaper.fdl.FdlProjectDetailsListAdapter;
import com.heziz.qixia3.adaper.fdl.FdlStreetListAdapter;
import com.heziz.qixia3.app.MyApplication;
import com.heziz.qixia3.base.BaseActivity;
import com.heziz.qixia3.bean.StreetBean;
import com.heziz.qixia3.bean.fdl.FdlProjectDetailsBean;
import com.heziz.qixia3.bigimage.JBrowseImgActivity;
import com.heziz.qixia3.bigimage.util.JMatrixUtil;
import com.heziz.qixia3.network.API;
import com.heziz.qixia3.network.JsonCallBack1;
import com.heziz.qixia3.network.OkGoClient;
import com.heziz.qixia3.network.SRequstBean;
import com.heziz.qixia3.ui.mine.fdl.MineFDLdetailsActivity;
import com.heziz.qixia3.utils.TimeUtils;
import com.heziz.qixia3.view.MyDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FDLProjectDetailListActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    private FdlProjectDetailsListAdapter adapter;
    private List<FdlProjectDetailsBean> fdlStreetListBeanList=new ArrayList<>();
    private Activity mContext;
    private String projectId;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdlproject_detail_list);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        projectId=getIntent().getStringExtra("projectId");
        name=getIntent().getStringExtra("name");
        mContext=this;
        tvName.setText(name);
        tvTitle.setText("非道路机械管理");
        adapter=new FdlProjectDetailsListAdapter(mContext,fdlStreetListBeanList);
        LinearLayoutManager manager=new LinearLayoutManager(mContext.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.empty_view);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent=new Intent(FDLProjectListActivity.this, ProjectDetailsActivity.class);
//                intent.putExtra("id",projectBeanList.get(position).getId());
//                startActivity(intent);
//                MyDialog.showDialog(mContext,fdlStreetListBeanList.get(position).getInvoicePath());

            }
        });
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
    }

    private void initDatas() {
        showProgressDialog();
        String url = API.FDL_PROJECT_DETAILS_LIST+"?access_token="+MyApplication.getInstance().getUserInfor().getUuid();
        Map<String,String> params=new HashMap<>();
        params.put("projectId",projectId);
        params.put("startTime", TimeUtils.getYesterdayTime());
        JsonCallBack1<SRequstBean<List<FdlProjectDetailsBean>>> jsonCallBack = new JsonCallBack1<SRequstBean<List<FdlProjectDetailsBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<FdlProjectDetailsBean>>> response) {

                fdlStreetListBeanList.addAll(response.body().getData());
                adapter.notifyDataSetChanged();
                dissmissProgressDialog();

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<FdlProjectDetailsBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url, params, jsonCallBack);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
        }
    }
}
