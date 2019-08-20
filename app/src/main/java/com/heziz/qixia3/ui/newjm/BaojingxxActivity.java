package com.heziz.qixia3.ui.newjm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.heziz.qixia3.R;
import com.heziz.qixia3.app.MyApplication;
import com.heziz.qixia3.base.BaseActivity;
import com.heziz.qixia3.bean.UserInfor;
import com.heziz.qixia3.bean.mine.MineBean;
import com.heziz.qixia3.network.API;
import com.heziz.qixia3.network.JsonCallBack1;
import com.heziz.qixia3.network.OkGoClient;
import com.heziz.qixia3.network.SRequstBean;
import com.heziz.qixia3.ui.mine.clcx.MineCLCXListActivity;
import com.heziz.qixia3.ui.mine.yc.MineYcListActivity;
import com.heziz.qixia3.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaojingxxActivity extends BaseActivity {
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvYc)
    TextView tvYc;
    @BindView(R.id.tvCl)
    TextView tvCl;
    @BindView(R.id.llYc)
    LinearLayout llYc;
    @BindView(R.id.llCl)
    LinearLayout llCl;
    private UserInfor userInfor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baojingxx);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
        userInfor=MyApplication.getInstance().getUserInfor();
    }

    private void initDatas() {
        showProgressDialog();
        String urlnum = API.YC_CL_NUM+"?access_token="+ MyApplication.getInstance().getUserInfor().getUuid();
        Map<String, String> paramsnum = new HashMap<>();
        if(userInfor.getPosition().equals("1")){
            paramsnum.put("station",userInfor.getStation()+"");
        }else if(userInfor.getPosition().equals("2")){
            paramsnum.put("managerRoleIds",userInfor.getManagerId()+"");
        }else if(userInfor.getPosition().equals("3")){
            paramsnum.put("createName",userInfor.getAccount()+"");
        }
        paramsnum.put("licensePlateColor","黄");
        paramsnum.put("licenseType","-1");
        JsonCallBack1<SRequstBean<String>> jsonCallBacknum = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                dissmissProgressDialog();
                String res=response.body().getData().replace("\\","");
                LogUtils.show(res);
                Gson gson=new Gson();
                MineBean bean=gson.fromJson(res, MineBean.class);
                tvCl.setText(bean.getFtpAlarmCount());
                tvYc.setText(bean.getWeatherAlarmCount());
//                tvOnLine.setText(bean.getOnlinecount());
//                tvOffline.setText(bean.getNotonlinecount());
//                int total=Integer.valueOf(bean.getNotonlinecount())+Integer.valueOf(bean.getOnlinecount())+Integer.valueOf(bean.getUnknowcount());
//                tvTotal.setText(total+"");
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData(urlnum, paramsnum, jsonCallBacknum);
    }

    private void initListeners() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        llYc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(BaojingxxActivity.this, MineYcListActivity.class);
                startActivity(intent);
            }
        });
        llCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(BaojingxxActivity.this, MineCLCXListActivity.class);
                startActivity(intent);
            }
        });
    }
}
