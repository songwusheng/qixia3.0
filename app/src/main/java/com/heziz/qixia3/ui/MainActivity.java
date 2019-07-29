package com.heziz.qixia3.ui;

import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.heziz.qixia3.R;
import com.heziz.qixia3.base.BaseActivity;
import com.heziz.qixia3.fragment.main.HomeFragment;
import com.heziz.qixia3.fragment.main.MineFragment;
import com.heziz.qixia3.fragment.main.ProjectFragment;
import com.heziz.qixia3.fragment.main.RenwuFragment;
import com.heziz.qixia3.fragment.main.ZhihuiFragment;
import com.heziz.qixia3.ui.mine.mineinfo.MineInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rgBottom)
    RadioGroup rgBottom;

    private HomeFragment homeFragment;
    private ProjectFragment projectFragment;
    private ZhihuiFragment zhihuiFragment;
    private RenwuFragment renwuFragment;
    private MineFragment mineFragment;

    MyBroadcastReceiver mBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        initListeners();
    }

    private void initListeners() {
        rgBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                hidFragment(fragmentTransaction);
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.shouye:
                        tvTitle.setText("栖霞网格化管理平台");
                        if (homeFragment == null) {
                            homeFragment = new HomeFragment();
                            fragmentTransaction.add(R.id.flMain, homeFragment);
                        }else{
                            fragmentTransaction.show(homeFragment);
                        }

                        break;
                    case R.id.project:
                        tvTitle.setText("工程列表");
                        if (projectFragment == null) {
                            projectFragment = new ProjectFragment();
                            fragmentTransaction.add(R.id.flMain, projectFragment);
                        }else{
                            fragmentTransaction.show(projectFragment);
                        }

                        break;
                    case R.id.zhihui:
                        tvTitle.setText("智慧工地");
                        if (zhihuiFragment == null) {
                            zhihuiFragment = new ZhihuiFragment();
                            fragmentTransaction.add(R.id.flMain, zhihuiFragment);
                        }else{
                            fragmentTransaction.show(zhihuiFragment);
                        }

                        break;
                    case R.id.renwu:
                        tvTitle.setText("日常检查");
//                        if (renwuFragment == null) {
                            renwuFragment = new RenwuFragment();
                            fragmentTransaction.add(R.id.flMain, renwuFragment);
//                        }else{
//                            fragmentTransaction.show(renwuFragment);
//                        }

                        break;
                    case R.id.mine:
                        tvTitle.setText("我的管理");
//                        if (mineFragment == null) {
                            mineFragment = new MineFragment();
                            fragmentTransaction.add(R.id.flMain, mineFragment);
//                        }else{
//                            fragmentTransaction.show(mineFragment);
//                        }

                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }

    private void initViews() {
        tvTitle.setText("栖霞网格化管理平台");
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.flMain, homeFragment);
        }
        fragmentTransaction.commit();

        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("xg_password_success");
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private void hidFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (projectFragment != null) {
            fragmentTransaction.hide(projectFragment);
        }
        if (zhihuiFragment != null) {
            fragmentTransaction.hide(zhihuiFragment);
        }
        if (renwuFragment != null) {
            fragmentTransaction.remove(renwuFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.remove(mineFragment);
        }
    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        public static final String TAG = "MyBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("xg_password_success")) {
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }
}
