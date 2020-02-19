package com.heziz.qixia3.ui.rcjc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heziz.qixia3.R;
import com.heziz.qixia3.base.BaseActivity;
import com.heziz.qixia3.utils.LogUtils;
import com.heziz.qixia3.view.ProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebviewActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.webView)
    ProgressWebView web;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    private String mWebUrl;
    private String title;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initListeners();
    }

    private void initViews() {
//        showProgressDialog();
        mWebUrl=getIntent().getStringExtra("mWebUrl");
        LogUtils.show(mWebUrl);
        id=getIntent().getIntExtra("id",0);
        switch (id){
            case 1:
                title="日常检查";
                break;
            case 2:
                title="专项检查";
                break;
            case 3:
                title="日常巡检整改";
                break;
            case 4:
                title="专项检查整改";
                break;
            case 5:
                title="政策文件";
                break;
            case 6:
                title="疫情期间行程查询";
                break;
        }

        tvTitle.setText(title);
        web.getSettings().setDefaultTextEncodingName("UTF-8");
        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        web.getSettings().setBuiltInZoomControls(true);

        web.getSettings().setDomStorageEnabled(true);
//        web.getSettings().setSupportMultipleWindows(true);
//        web.addJavascriptInterface(new Android(),"android");
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDisplayZoomControls(false);
//        web.getSettings().setDomStorageEnabled(true);

//        web.getSettings().setAppCacheEnabled(true);
//        web.getSettings().setDomStorageEnabled(true);
////        web.getSettings().supportMultipleWindows(true);
//        web.getSettings().setAllowContentAccess(true);
//        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        web.getSettings().setUseWideViewPort(true);
//        web.getSettings().setLoadWithOverviewMode(true);
//        web.getSettings().setSavePassword(true);
//        web.getSettings().setSaveFormData(true);
//        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        web.getSettings().setLoadsImagesAutomatically(true);


        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith("sms")){
                    Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
                    startActivity(sendIntent);
                }else{
                    view.loadUrl(url);

                }

                Log.w("main","======跳转地址======="+url);
                return true;
            }


        });
//        web.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
//                WebView newWebView = new WebView(view.getContext());
//                newWebView.setWebViewClient(new WebViewClient() {
//                    @Override
//                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                        // 在此处进行跳转URL的处理, 一般情况下_black需要重新打开一个页面,
//                        Log.w("main","第二个链接"+url);
//                        if(url.startsWith("sms")){
//                            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
//                            startActivity(sendIntent);
//                        }else{
//                            web.loadUrl(url);
//                        }
//
//                        return true;
//                    }
//
//                });
//                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
//                transport.setWebView(newWebView);
//                resultMsg.sendToTarget();
//                return true;
//            }
//        });

//        web.loadUrl("javascript:change('" + MyApplication.getInstance().getUserInfor().uuid + "')");
        web.loadUrl(mWebUrl);
    }

    private void initDatas() {

//        String url = "https://www.heziz.com/apis/urmp/wechatMiniProgram/getOpenId?code=023Wy2MY1iYJpT01GsOY1SCKLY1Wy2Mj";
//        Map<String,String> params=new HashMap<>();
////        params.put("siteId","1551258680345413");
//        JsonCallBack1<SRequstBean<List<StreetBean>>> jsonCallBack = new JsonCallBack1<SRequstBean<List<StreetBean>>>() {
//            @Override
//            public void onSuccess(com.lzy.okgo.model.Response<SRequstBean<List<StreetBean>>> response) {
//
//            }
//
//            @Override
//            public void onError(com.lzy.okgo.model.Response<SRequstBean<List<StreetBean>>> response) {
//                super.onError(response);
//                dissmissProgressDialog();
//            }
//
//        };
//        OkGoClient.getInstance()
//                .getJsonData1(url, params, jsonCallBack);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("main", "requestCode=" + requestCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == ProgressWebView.TYPE_CAMERA) { // 相册选择
//                if (data!=null){
//                    Uri uri = data.getData();
//                    Log.d("main", "uri=" + uri);
//                    if (uri != null) {
                web.onActivityCallBack(true, null,1);
//                    }
//                }

            } else if (requestCode == ProgressWebView.TYPE_GALLERY) {// 相册选择
                if (data != null) {
                    Uri uri = data.getData();
                    Log.d("main", "uri=" + uri);
                    if (uri != null) {
                        web.onActivityCallBack(false, uri,1);
                    } else {
                        Toast.makeText(WebviewActivity.this, "获取数据为空", Toast.LENGTH_LONG).show();
                    }
                }else{
                    web.onActivityCallBack(false, null,2);
                }
            }

        }else{
            web.onActivityCallBack(false, null,2);
        }
    }

    // 权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == ProgressWebView.TYPE_REQUEST_PERMISSION) {
////            web.toCamera();// 到相机
//            web.showOptions();
//        }
    }


    private void initListeners() {
        rlBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlBack:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if(web.canGoBack()){
                web.goBack();
            }else{
                finish();
            }
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
