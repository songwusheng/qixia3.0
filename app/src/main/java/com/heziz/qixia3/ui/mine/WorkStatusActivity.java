package com.heziz.qixia3.ui.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.qixia3.R;
import com.heziz.qixia3.app.MyApplication;
import com.heziz.qixia3.base.BaseActivity;
import com.heziz.qixia3.bean.StreetBean;
import com.heziz.qixia3.network.API;
import com.heziz.qixia3.network.JsonCallBack1;
import com.heziz.qixia3.network.OkGoClient;
import com.heziz.qixia3.network.SRequstBean;
import com.heziz.qixia3.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkStatusActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvWork)
    TextView tvWork;
    @BindView(R.id.tvMetting)
    TextView tvMetting;
    @BindView(R.id.tvWaic)
    TextView tvWaic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_status);
        ButterKnife.bind(this);

        initViews();

        initListeners();
    }

    private void initViews() {
        tvTitle.setText("工作状态");
    }

    private void initDatas(int status) {
        showProgressDialog();
        String url = API.WORKSTATUS;
        Map<String,String> params=new HashMap<>();
        params.put("access_token", MyApplication.getInstance().getUserInfor().getUuid());
        params.put("workStatus", status+"");
        JsonCallBack1<SRequstBean<String>> jsonCallBack = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                if(response.body().getData().equals("success")){
                    ToastUtil.showToast("工作状态修改成功");
                    Intent intent = new Intent();
                    intent.setAction("xg_workstatus_success");
                    intent.putExtra("status",status+"");
                    sendBroadcast(intent);
                    finish();
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(url, params, jsonCallBack);
    }

    private void initListeners() {
        rlBack.setOnClickListener(this);
        tvWork.setOnClickListener(this);
        tvMetting.setOnClickListener(this);
        tvWaic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
            case R.id.tvWaic:
                initDatas(3);
                break;
            case R.id.tvWork:
                initDatas(1);
                break;
            case R.id.tvMetting:
                initDatas(2);
                break;
        }
    }
}
