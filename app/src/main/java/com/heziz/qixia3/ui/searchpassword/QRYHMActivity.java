package com.heziz.qixia3.ui.searchpassword;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.qixia3.R;
import com.heziz.qixia3.app.MyApplication;
import com.heziz.qixia3.base.BaseActivity;
import com.heziz.qixia3.network.API;
import com.heziz.qixia3.network.JsonCallBack1;
import com.heziz.qixia3.network.OkGoClient;
import com.heziz.qixia3.network.SRequstBean;
import com.heziz.qixia3.ui.mine.mineinfo.MineXGMMActivity;
import com.heziz.qixia3.utils.ScreenUtils;
import com.heziz.qixia3.utils.StringUtil;
import com.heziz.qixia3.utils.TS;
import com.heziz.qixia3.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QRYHMActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.btn1)
    Button btn;
    MyBroadcastReceiver mBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qryhm);
        ButterKnife.bind(this);
        initViews();

        initeListners();
    }

    private void initViews() {
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("zh_password_success");
        registerReceiver(mBroadcastReceiver, intentFilter);
        tvTitle.setText("找回密码");
    }

    private void initDatas(String str1) {
        showProgressDialog();
        String urlnum = API.USERNAME_CZ;
        Map<String, String> paramsnum = new HashMap<>();
        paramsnum.put("account", str1);
        JsonCallBack1<SRequstBean<String>> jsonCallBacknum = new JsonCallBack1<SRequstBean<String>>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                dissmissProgressDialog();
                String res=response.body().getData();
                if("帐号存在！".equals(res)) {
                    Intent intent=new Intent(mContext,SearchPasswordActivity.class);
                    intent.putExtra("account",str1);
                    startActivity(intent);
                }else{
                    tvInfo.setText(res);
                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<SRequstBean<String>> response) {
                super.onError(response);
                ToastUtil.showToast("服务器异常");
                dissmissProgressDialog();
            }

        };
        OkGoClient.getInstance()
                .getJsonData1(urlnum, paramsnum, jsonCallBacknum);


    }

    private void initeListners() {
        rlBack.setOnClickListener(this);
        btn.setOnClickListener(this);

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvInfo.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
            case R.id.btn1:
                String str1=et1.getText().toString();
                if(StringUtil.isEmpty(str1)){
                    tvInfo.setText("用户名不能为空！");
                    return;
                }


                ScreenUtils.hiddenKeyBord(QRYHMActivity.this);
                initDatas(str1);


                break;
        }
    }
    class MyBroadcastReceiver extends BroadcastReceiver {
        public static final String TAG = "MyBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("zh_password_success")) {
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
