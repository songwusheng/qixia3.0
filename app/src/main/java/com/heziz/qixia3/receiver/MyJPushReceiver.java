package com.heziz.qixia3.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dahuatech.utilslib.ToastUtils;
import com.google.gson.Gson;
import com.heziz.qixia3.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * Created by sws on 2019-11-15.
 * from:
 * describe:
 */

public class MyJPushReceiver extends JPushMessageReceiver {

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        //super.onAliasOperatorResult(context, jPushMessage);
        ToastUtil.showToast(jPushMessage.getAlias());
}

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        //super.onMessage(context, customMessage);
        ToastUtil.showToast(customMessage.title);
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        //super.onNotifyMessageArrived(context, notificationMessage);
        ToastUtil.showToast(notificationMessage.notificationTitle+"=="+notificationMessage.notificationExtras);
        Log.w("main",notificationMessage.notificationExtras);

        String extraStr=notificationMessage.notificationExtras;
        try {
            JSONObject object=new JSONObject(extraStr);
            int num=object.getInt("badge");
            Bundle extra =new Bundle();
            extra.putString("package", "com.heziz.qixia3");
            extra.putString("class", "com.heziz.qixia3.ui.SplashActivity");
            extra.putInt("badgenumber", num);
            context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, extra);
        } catch (JSONException e) {
            e.printStackTrace();
    }

    }
}
