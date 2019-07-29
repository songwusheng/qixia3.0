package com.heziz.qixia3.ui.mine.yc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heziz.qixia3.R;
import com.heziz.qixia3.adaper.yc.MineYcGJListAdapter;
import com.heziz.qixia3.adaper.yc.MineYcProjectDetailsListAdapter;
import com.heziz.qixia3.app.MyApplication;
import com.heziz.qixia3.base.BaseActivity;
import com.heziz.qixia3.bean.mine.yc.MineYcListBean;
import com.heziz.qixia3.bean.yc.YcRealTimeBean;
import com.heziz.qixia3.network.API;
import com.heziz.qixia3.network.JsonCallBack1;
import com.heziz.qixia3.network.OkGoClient;
import com.heziz.qixia3.network.RequestBean;
import com.heziz.qixia3.network.SRequstBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineYcGJProjectXXListActivity extends BaseActivity {
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTitle1)
    TextView tvTitle1;
    //    @BindView(R.id.llList)
//    LinearLayout llList;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    private int pageNow=1;
    private MineYcProjectDetailsListAdapter adapter;
    private List<YcRealTimeBean> list=new ArrayList<>();
    private MineYcListBean mineYcListBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_yc_gjproject_xxlist);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        mineYcListBean=(MineYcListBean) getIntent().getSerializableExtra("mineYcListBean");
        tvTitle.setText("扬尘告警信息");
        tvTitle1.setText(mineYcListBean.getProjectName());

        adapter=new MineYcProjectDetailsListAdapter(this,list,mineYcListBean);
        LinearLayoutManager manager=new LinearLayoutManager(mContext.getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
    }

    private void initDatas() {
        showProgressDialog();
        String url1 = API.MINE_YC_DETAILS_LIST+"/"+pageNow+"/10";
        Map<String,String> params=new HashMap<>();
        params.put("access_token",MyApplication.getInstance().getUserInfor().getUuid());
        params.put("projectId",mineYcListBean.getProjectId());
        JsonCallBack1<SRequstBean<RequestBean<List<YcRealTimeBean>>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<RequestBean<List<YcRealTimeBean>>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<YcRealTimeBean>>>> response) {
                dissmissProgressDialog();
                if (response.body().getData()!=null){
//                            carDDetailsBeanList.addAll(response.body().getData().getList());
                    if(response.body().getData().getList().size()!=0){
                        list.addAll(response.body().getData().getList());
                        adapter.loadMoreComplete();
                    }else{
                        adapter.loadMoreEnd();
                    }
                }else{
                    adapter.loadMoreFail();
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<RequestBean<List<YcRealTimeBean>>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url1, params, jsonCallBack1);

    }

    private void initListeners() {
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNow++;
                initDatas();
            }
        });
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
