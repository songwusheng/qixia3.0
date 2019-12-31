package com.heziz.qixia3.ui.newjm;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heziz.qixia3.R;
import com.heziz.qixia3.app.MyApplication;
import com.heziz.qixia3.base.BaseFragment;
import com.heziz.qixia3.bean.UserInfor;
import com.heziz.qixia3.ui.LoginActivity;
import com.heziz.qixia3.ui.mine.WorkStatusActivity;
import com.heziz.qixia3.ui.mine.mineinfo.MineXGMMActivity;

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
    @BindView(R.id.tvXGMM)
    TextView tvXGMM;
    @BindView(R.id.tvGZZT)
    TextView tvGZZT;
    private UserInfor userInfor;
    MyBroadcastReceiver mBroadcastReceiver;
    private String workStatus;
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
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("xg_workstatus_success");
        getActivity().registerReceiver(mBroadcastReceiver, intentFilter);
        userInfor=MyApplication.getInstance().getUserInfor();
        tvPhone.setText(userInfor.getName());
        workStatus=userInfor.getWorkStatus();
        initDatas();


    }

    private void initDatas() {

        if(workStatus.equals("0")){
            tvGZZT.setText("工作状态：无");
        }else if(workStatus.equals("1")){
            tvGZZT.setText("工作状态：工作中");
        }else if(workStatus.equals("2")){
            tvGZZT.setText("工作状态：会议中");
        }else if(workStatus.equals("3")){
            tvGZZT.setText("工作状态：外出");
        }
    }

    private void initeListeners() {
        btn.setOnClickListener(this);
        tvXGMM.setOnClickListener(this);
        tvGZZT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.btn:
                intent.setClass(getActivity(), LoginActivity.class);
                getActivity().finish();
                break;
            //case R.id.tvWDBYGS:
            //    intent.setClass(getActivity(), MineWDBYGSListActivity.class);
            //    break;
            //case R.id.tvZXJC:
            //    intent.setClass(getActivity(), MineZXJCListActivity.class);
            //    break;
            //case R.id.tvFDLJXGL:
            //    intent.setClass(getActivity(), MineFdlListActivity.class);
            //    break;
            case R.id.tvXGMM:
                intent.setClass(getActivity(), MineXGMMActivity.class);
                break;
            case R.id.tvGZZT:
                intent.setClass(getActivity(), WorkStatusActivity.class);
                break;

        }
        startActivity(intent);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        public static final String TAG = "MyBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("xg_workstatus_success")) {
                //finish();
                workStatus=intent.getStringExtra("status");
                initDatas();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }
}
