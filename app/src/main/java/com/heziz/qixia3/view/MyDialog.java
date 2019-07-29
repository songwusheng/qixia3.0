package com.heziz.qixia3.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.heziz.qixia3.R;

/**
 * Created by sws on 2019-06-05.
 * from:
 * describe:
 */

public class MyDialog {
    public static void showDialog(Context context,String url){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
//        alertDialog.setOnCancelListener(new ProgressWebView.ReOnCancelListener());
//        alertDialog.setTitle("选择");
        View view= LayoutInflater.from(context).inflate(R.layout.big_image_include,null);
        ZoomImageView iv=(ZoomImageView) view.findViewById(R.id.iv);
        Glide.with(context).load(url).crossFade().into(iv);
        alertDialog.setView(view);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
            }
        });
        alertDialog.show();
    }
}
