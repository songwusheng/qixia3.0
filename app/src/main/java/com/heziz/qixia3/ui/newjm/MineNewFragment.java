package com.heziz.qixia3.ui.newjm;


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
import com.heziz.qixia3.bean.HomeBean;
import com.heziz.qixia3.bean.UserInfor;
import com.heziz.qixia3.bean.mine.MineBean;
import com.heziz.qixia3.network.API;
import com.heziz.qixia3.network.JsonCallBack1;
import com.heziz.qixia3.network.OkGoClient;
import com.heziz.qixia3.network.SRequstBean;
import com.heziz.qixia3.ui.LoginActivity;
import com.heziz.qixia3.ui.mine.clcx.MineCLCXListActivity;
import com.heziz.qixia3.ui.mine.fdl.MineFdlListActivity;
import com.heziz.qixia3.ui.mine.mineinfo.MineInfoActivity;
import com.heziz.qixia3.ui.mine.mineinfo.MineXGMMActivity;
import com.heziz.qixia3.ui.mine.wdbygs.MineWDBYGSListActivity;
import com.heziz.qixia3.ui.mine.xwzx.XWZXListActivity;
import com.heziz.qixia3.ui.mine.yc.MineYcListActivity;
import com.heziz.qixia3.ui.mine.zxjc.MineZXJCListActivity;
import com.heziz.qixia3.utils.LogUtils;
import com.heziz.qixia3.utils.NumberUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * new_clcx_d_cph simple {@link Fragment} subclass.
 */
public class MineNewFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvWDBYGS)
    TextView tvWDBYGS;
    @BindView(R.id.tvZXJC)
    TextView tvZXJC;
    @BindView(R.id.tvFDLJXGL)
    TextView tvFDLJXGL;
    @BindView(R.id.ivMine)
    ImageView ivMine;
    @BindView(R.id.tvXGMM)
    TextView tvXGMM;
    private UserInfor userInfor;
    public MineNewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_new_mine, container, false);
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

    }

    private void initeListeners() {
        btn.setOnClickListener(this);
        tvWDBYGS.setOnClickListener(this);
        tvZXJC.setOnClickListener(this);
        tvFDLJXGL.setOnClickListener(this);
        tvXGMM.setOnClickListener(this);
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
            case R.id.tvWDBYGS:
                intent.setClass(getActivity(), MineWDBYGSListActivity.class);
                break;
            case R.id.tvZXJC:
                intent.setClass(getActivity(), MineZXJCListActivity.class);
                break;
            case R.id.tvFDLJXGL:
                intent.setClass(getActivity(), MineFdlListActivity.class);
                break;
            case R.id.ivMine:
                intent.setClass(getActivity(), MineInfoActivity.class);
                break;
            case R.id.tvXGMM:
                intent.setClass(getActivity(), MineXGMMActivity.class);
                break;

        }
        startActivity(intent);
    }
}
