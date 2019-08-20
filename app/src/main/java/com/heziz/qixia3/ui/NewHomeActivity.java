package com.heziz.qixia3.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.heziz.qixia3.R;
import com.heziz.qixia3.app.MyApplication;
import com.heziz.qixia3.base.BaseActivity;
import com.heziz.qixia3.bean.DataBean;
import com.heziz.qixia3.bean.HomeBean;
import com.heziz.qixia3.bean.UserInfor;
import com.heziz.qixia3.network.API;
import com.heziz.qixia3.network.JsonCallBack1;
import com.heziz.qixia3.network.OkGoClient;
import com.heziz.qixia3.network.SRequstBean;
import com.heziz.qixia3.ui.newjm.BaojingxxActivity;
import com.heziz.qixia3.ui.newjm.CommActivity;
import com.heziz.qixia3.ui.zhihui.clwcx.ClcxStreetProjectActivity;
import com.heziz.qixia3.ui.zhihui.clwcx.ClwcxStreetDeviceActivity;
import com.heziz.qixia3.ui.zhihui.fdl.FDLProjectListActivity;
import com.heziz.qixia3.ui.zhihui.fdl.FdlStreetDeviceListActivity;
import com.heziz.qixia3.ui.zhihui.sp.SpStreetDeviceListActivity;
import com.heziz.qixia3.ui.zhihui.sp.SpStreetProjectActivity;
import com.heziz.qixia3.ui.zhihui.yc.StreetYcDeviceListActivity;
import com.heziz.qixia3.ui.zhihui.yc.YcStreetProjectListActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewHomeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivXtsz)
    ImageView ivXtsz;
    @BindView(R.id.tvSp)
    TextView tvSp;
    @BindView(R.id.tvYc)
    TextView tvYc;
    @BindView(R.id.tvBj)
    TextView tvBj;
    @BindView(R.id.tvCl)
    TextView tvCl;
    @BindView(R.id.tvXm)
    TextView tvXm;
    @BindView(R.id.tvDt)
    TextView tvDt;
    @BindView(R.id.tvRc)
    TextView tvRc;
    @BindView(R.id.tvFd)
    TextView tvFd;
    @BindView(R.id.tvVertion)
    TextView tvVertion;

    @BindView(R.id.tvWeather)
    TextView tvWeather;
    @BindView(R.id.tvFl)
    TextView tvFl;
    @BindView(R.id.tvKq)
    TextView tvKq;

    private UserInfor userInfor;
    private String position;
    private NewHomeActivity mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initDatas() {
        Map<String,String> params1=new HashMap<>();
        String url1 = API.HOME_WHEATHER + "?access_token=" + userInfor.getUuid();
        params1.put("citynameId","1347");
        JsonCallBack1<SRequstBean<DataBean>> jsonCallBack = new JsonCallBack1<SRequstBean<DataBean>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<DataBean>> response) {
                DataBean dataBean=response.body().getData();
                String resulte=dataBean.getResult().replace("\\","");
                try {
                    JSONObject object=new JSONObject(resulte);
                    JSONObject today=object.getJSONObject("today");
                    String weather=today.getString("weather");
                    String tmp=today.getString("temperature");
                    tvWeather.setText(weather+" "+tmp);

                    JSONObject sk=object.getJSONObject("sk");
                    String fl=today.getString("wind");
                    String fj=sk.getString("wind_strength");
                    tvFl.setText(fl+" "+fj);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<DataBean>> response) {
                super.onError(response);
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .postJsonData0(url1, params1, jsonCallBack);
    }

    private void initViews() {
        mContext=this;
        userInfor= MyApplication.getInstance().getUserInfor();
        position=userInfor.getPosition();
        tvVertion.setText(getVersion());
    }

    private void initListeners() {
        ivXtsz.setOnClickListener(this);
        tvSp.setOnClickListener(this);
        tvYc.setOnClickListener(this);
        tvBj.setOnClickListener(this);
        tvCl.setOnClickListener(this);
        tvXm.setOnClickListener(this);
        tvDt.setOnClickListener(this);
        tvRc.setOnClickListener(this);
        tvFd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.ivXtsz:
                intent.setClass(mContext,CommActivity.class);
                intent.putExtra("type",3);
                intent.putExtra("title","我的管理");
                break;
            case R.id.tvSp:
                if(userInfor.getPosition().equals("3")){
                    intent.setClass(mContext,SpStreetProjectActivity.class);
                    intent.putExtra("id","");
                    intent.putExtra("name","");
                }else {
                    intent.setClass(mContext,SpStreetDeviceListActivity.class);
                }
                break;
            case R.id.tvYc:
                if(userInfor.getPosition().equals("3")){
                    intent=new Intent(mContext, YcStreetProjectListActivity.class);
                    intent.putExtra("id","");
                    intent.putExtra("name","");
                }else {
                    intent.setClass(mContext,StreetYcDeviceListActivity.class);
                    intent.putExtra("type","yc");
                }
                break;
            case R.id.tvBj:
                intent.setClass(mContext,BaojingxxActivity.class);
                break;
            case R.id.tvCl:
                if(userInfor.getPosition().equals("3")){
                    intent=new Intent(mContext, ClcxStreetProjectActivity.class);
                    intent.putExtra("id","");
                    intent.putExtra("name","");
                }else {
                    intent.setClass(mContext,ClwcxStreetDeviceActivity.class);
                }
                break;
            case R.id.tvXm:
                intent.setClass(mContext,CommActivity.class);
                intent.putExtra("type",0);
                intent.putExtra("title","项目总览");
                break;
            case R.id.tvDt:
                intent.setClass(mContext,CommActivity.class);
                intent.putExtra("type",1);
                intent.putExtra("title","地图");
                break;
            case R.id.tvRc:
                intent.setClass(mContext,CommActivity.class);
                intent.putExtra("type",2);
                intent.putExtra("title","日常检查");
                break;
            case R.id.tvFd:
                if(userInfor.getPosition().equals("3")){
                    intent.setClass(mContext,FDLProjectListActivity.class);
                    intent.putExtra("managerId","");
                }else{
                    intent.setClass(mContext,FdlStreetDeviceListActivity.class);
                }
                break;
        }
        startActivity(intent);
    }

    private String getVersion(){
        PackageInfo pkg;
        String versionName="";
        try {
            pkg = getPackageManager().getPackageInfo(getApplication().getPackageName(), 0);
            String appName = pkg.applicationInfo.loadLabel(getPackageManager()).toString();
            versionName = pkg.versionName;
            System.out.println("appName:" + appName);
            System.out.println("versionName:" + versionName);

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  versionName;
    }
}
