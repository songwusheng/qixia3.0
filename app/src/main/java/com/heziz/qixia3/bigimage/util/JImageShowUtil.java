package com.heziz.qixia3.bigimage.util;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestListener;
import com.heziz.qixia3.app.MyApplication;


/**
 *  Created by jincancan on 16/9/11.
 *  图片加载封装类 默认CENTER_CROP
 */
public class JImageShowUtil {

    private static final String TAG = "TLImageShowUtil";




    /***
     *
     * @param imgUrl 图片链接
     * @param imageView view
     * @param userImageScale 是否使用imageview的scaleType
     */
    public static void displayImage(final String imgUrl, final ImageView imageView, boolean userImageScale){
        if(!TextUtils.isEmpty(imgUrl)){
//            RequestOptions options = new RequestOptions()
//                    .priority(Priority.NORMAL);
//
//            if(!userImageScale){
//                options.centerCrop();
//            }


            Glide.with(MyApplication.getInstance())
                    .load(imgUrl)
//                    .apply(options)
                    .into(imageView);
        }
    }








    public static void displayImageScale(final String imgUrl, final String smallImgUrl, final ImageView imageView){
        if(!TextUtils.isEmpty(imgUrl)){
//            RequestOptions options = new RequestOptions()
//                    .priority(Priority.NORMAL);
//
//            RequestBuilder<Drawable> thumbnailRequest = Glide
//                    .with(JApp.getIns())
//                    .load(smallImgUrl);

            Glide.with(MyApplication.getInstance())
                    .load(imgUrl)
//                    .apply(options)
//                    .thumbnail(thumbnailRequest)
//                    .listener(callback)
                    .into(imageView);
        }
    }

    public static void displayImageScale(final int imgResource, final String smallImgUrl, final ImageView imageView){
        if(imgResource != 0){
//            RequestOptions options = new RequestOptions()
//                    .priority(Priority.NORMAL);
//
//            RequestBuilder<Drawable> thumbnailRequest = Glide
//                    .with(JApp.getIns())
//                    .load(smallImgUrl);

            Glide.with(MyApplication.getInstance())
                    .load(smallImgUrl)
//                    .apply(options)
//                    .thumbnail(thumbnailRequest)
//                    .listener(callback)
                    .into(imageView);
        }
    }
}
