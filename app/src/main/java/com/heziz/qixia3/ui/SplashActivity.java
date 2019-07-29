package com.heziz.qixia3.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.heziz.qixia3.R;
import com.heziz.qixia3.base.BaseActivity;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SplashActivity extends BaseActivity {

    private TextView version;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_splash);
        requestPermission();
        initViews();
    }

    private void requestPermission() {

        if (ContextCompat.checkSelfPermission(this, READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{READ_PHONE_STATE},
                    10);
        }
        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE},
                    12);
        }
        if (ContextCompat.checkSelfPermission(this,ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},
                    12);
        }
        if (ContextCompat.checkSelfPermission(this,CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{CAMERA},
                    12);
        }
    }

    private void initViews() {
        version = (TextView) findViewById(R.id.mversion);
        version.setText("当前版本为:V"+getVersion());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMyActivity(LoginActivity.class);
                finish();
//                startMyActivity(WeatherActivity.class);
//                finish();

            }
        },2000);
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
