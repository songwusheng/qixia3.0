package com.heziz.qixia3.ui.zhihui.yc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.heziz.qixia3.R;
import com.heziz.qixia3.adaper.yc.YcListAdapter;
import com.heziz.qixia3.app.MyApplication;
import com.heziz.qixia3.base.BaseActivity;
import com.heziz.qixia3.bean.UserInfor;
import com.heziz.qixia3.bean.yc.YcDevicezlxNumBean;
import com.heziz.qixia3.bean.yc.YczlxNumBean;
import com.heziz.qixia3.network.API;
import com.heziz.qixia3.network.JsonCallBack1;
import com.heziz.qixia3.network.OkGoClient;
import com.heziz.qixia3.network.SRequstBean;
import com.heziz.qixia3.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StreetYcDeviceListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ivIcon)
    ImageView ivIcon;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.tvOnLine)
    TextView tvOnLine;
    @BindView(R.id.tvOffline)
    TextView tvOffline;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    String type;

    YcListAdapter adapter;
    private List<YczlxNumBean> projectBeanList=new ArrayList<>();

    private UserInfor userInfor;
    Map<String,String> params1=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_device_list);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
    }

    private void initViews() {
        userInfor= MyApplication.getInstance().getUserInfor();
        type=getIntent().getStringExtra("type");
        tvTitle.setText("扬尘监控");

        adapter=new YcListAdapter(this,projectBeanList);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(StreetYcDeviceListActivity.this, YcStreetProjectListActivity.class);
                intent.putExtra("id",projectBeanList.get(position).getManagerRoleId());
                intent.putExtra("name",projectBeanList.get(position).getStreet());
//                intent.putExtra("object",projectBeanList.get(position).getManagerRoleId());
                startActivity(intent);
            }
        });
    }

    private void initDatas() {
        String urlnum = API.YC_STREET_NUM;
        Map<String, String> paramsnum = new HashMap<>();
        if(userInfor.getPosition().equals("1")){
            paramsnum.put("station",userInfor.getStation()+"");
        }else if(userInfor.getPosition().equals("2")){
            paramsnum.put("managerRoleIds",userInfor.getManagerId()+"");
        }else if(userInfor.getPosition().equals("3")){
            paramsnum.put("id",userInfor.getAccount()+"");
        }
        JsonCallBack1<SRequstBean<String>> jsonCallBacknum = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {

                String res=response.body().getData().replace("\\","");
                LogUtils.show(res);
                Gson gson=new Gson();
                YcDevicezlxNumBean bean=gson.fromJson(res, YcDevicezlxNumBean.class);
                tvOnLine.setText(bean.getOnlinecount());
                tvOffline.setText(bean.getNotonlinecount());
                int total=Integer.valueOf(bean.getNotonlinecount())+Integer.valueOf(bean.getOnlinecount())+Integer.valueOf(bean.getUnknowcount());
                tvTotal.setText(total+"");
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(urlnum, paramsnum, jsonCallBacknum);

        if(userInfor.getPosition().equals("1")){
            params1.put("station",userInfor.getStation()+"");
        }else if(userInfor.getPosition().equals("2")){
            params1.put("managerRoleIds",userInfor.getManagerId()+"");
        }
        initProjectData();
    }

    private void initProjectData() {
        showProgressDialog();
        String url1 = API.YC_STREET_LIST+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();

        JsonCallBack1<SRequstBean<List<YczlxNumBean>>> jsonCallBack1 = new JsonCallBack1<SRequstBean<List<YczlxNumBean>>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<YczlxNumBean>>> response) {
                projectBeanList.clear();
                projectBeanList.addAll(response.body().getData());
                adapter.notifyDataSetChanged();
//                showMark(projectBeanList);
                dissmissProgressDialog();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<YczlxNumBean>>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(url1, params1, jsonCallBack1);
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
