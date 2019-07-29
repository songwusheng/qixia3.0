package com.heziz.qixia3.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.heziz.qixia3.R;
import com.heziz.qixia3.app.MyApplication;
import com.heziz.qixia3.base.BaseFragment;
import com.heziz.qixia3.bean.UserInfor;
import com.heziz.qixia3.bean.mine.MineBean;
import com.heziz.qixia3.network.API;
import com.heziz.qixia3.network.JsonCallBack1;
import com.heziz.qixia3.network.OkGoClient;
import com.heziz.qixia3.network.SRequstBean;
import com.heziz.qixia3.ui.LoginActivity;
import com.heziz.qixia3.ui.mine.xwzx.XWZXListActivity;
import com.heziz.qixia3.ui.mine.clcx.MineCLCXListActivity;
import com.heziz.qixia3.ui.mine.fdl.MineFdlListActivity;
import com.heziz.qixia3.ui.mine.mineinfo.MineInfoActivity;
import com.heziz.qixia3.ui.mine.wdbygs.MineWDBYGSListActivity;
import com.heziz.qixia3.ui.mine.yc.MineYcListActivity;
import com.heziz.qixia3.ui.mine.zxjc.MineZXJCListActivity;
import com.heziz.qixia3.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tvYc)
    TextView tvYc;
    @BindView(R.id.tvCl)
    TextView tvCl;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.llYc)
    LinearLayout llYc;
    @BindView(R.id.llCl)
    LinearLayout llCl;
    @BindView(R.id.tvWDBYGS)
    TextView tvWDBYGS;
    @BindView(R.id.tvZXJC)
    TextView tvZXJC;
    @BindView(R.id.tvFDLJXGL)
    TextView tvFDLJXGL;
    @BindView(R.id.tvXWZX)
    TextView tvXWZX;
    @BindView(R.id.ivMine)
    ImageView ivMine;

    private UserInfor userInfor;
    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this,view);
        initViews();
        initDatas();
        initeListeners();
        return view;
    }

    private void initViews() {

        userInfor=MyApplication.getInstance().getUserInfor();
            tvPhone.setText(userInfor.getName());

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

    private void initeListeners() {
        btn.setOnClickListener(this);
        llYc.setOnClickListener(this);
        llCl.setOnClickListener(this);
        tvWDBYGS.setOnClickListener(this);
        tvZXJC.setOnClickListener(this);
        tvFDLJXGL.setOnClickListener(this);
        tvXWZX.setOnClickListener(this);
        ivMine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.btn:
                intent.setClass(getActivity(), LoginActivity.class);
                getActivity().finish();
                break;
            case R.id.llYc:
                intent.setClass(getActivity(), MineYcListActivity.class);
                break;
            case R.id.llCl:
                intent.setClass(getActivity(), MineCLCXListActivity.class);
                break;
            case R.id.tvWDBYGS:
                intent.setClass(getActivity(), MineWDBYGSListActivity.class);
                break;
            case R.id.tvZXJC:
                intent.setClass(getActivity(), MineZXJCListActivity.class);
                break;
            case R.id.tvFDLJXGL:
                intent.setClass(getActivity(), MineFdlListActivity.class);
                break;
            case R.id.tvXWZX:
                intent.setClass(getActivity(), XWZXListActivity.class);
                break;
            case R.id.ivMine:
                intent.setClass(getActivity(), MineInfoActivity.class);
                break;

        }
        startActivity(intent);
    }
}
