package com.heziz.qixia3.utils;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import com.heziz.qixia3.app.MyApplication;

import java.io.File;
import java.util.Locale;

public class OpenFileUtil {

    public static Intent openFile(String filePath) {

        File file = new File(filePath);
        if (!file.exists())
            return null;
        /* 取得扩展名 */
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase(Locale.getDefault());
        /* 依扩展名的类型决定MimeType */
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") || end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            return getAudioFileIntent(file);
        } else if (end.equals("3gp") || end.equals("mp4")) {
            return getVideoFileIntent(file);
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg") || end.equals("bmp")) {
            return getImageFileIntent(file);
        } else if (end.equals("apk")) {
            return getApkFileIntent(file);
        } else if (end.equals("ppt")) {
            return getPptFileIntent(file);
        } else if (end.equals("xls")) {
            return getExcelFileIntent(file);
        } else if (end.equals("doc")) {
            return getWordFileIntent(file);
        } else if (end.equals("pdf")) {
            return getPdfFileIntent(file);
        } else if (end.equals("chm")) {
            return getChmFileIntent(file);
        } else if (end.equals("txt")) {
            return getTextFileIntent(file, false);
        } else {
            return getAllIntent(file);
        }
    }

    // Android获取一个用于打开APK文件的intent
    public static Intent getAllIntent(File param) {
        Uri contentUri = FileProvider.getUriForFile(MyApplication.getAppContext(),"com.heziz.qixia3.fileProvider",param);

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(contentUri, "*/*");
        return intent;
    }

    // Android获取一个用于打开APK文件的intent
    public static Intent getApkFileIntent(File param) {
        Uri contentUri = FileProvider.getUriForFile(MyApplication.getAppContext(),"com.heziz.qixia3.fileProvider",param);

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        return intent;
    }

    // Android获取一个用于打开VIDEO文件的intent
    public static Intent getVideoFileIntent(File param) {
        Uri contentUri = FileProvider.getUriForFile(MyApplication.getAppContext(),"com.heziz.qixia3.fileProvider",param);

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        intent.setDataAndType(contentUri, "video/*");
        return intent;
    }

    // Android获取一个用于打开AUDIO文件的intent
    public static Intent getAudioFileIntent(File param) {
        Uri contentUri = FileProvider.getUriForFile(MyApplication.getAppContext(),"com.heziz.qixia3.fileProvider",param);

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        intent.setDataAndType(contentUri, "audio/*");
        return intent;
    }

    // Android获取一个用于打开Html文件的intent
    public static Intent getHtmlFileIntent(File param) {
        Uri contentUri = FileProvider.getUriForFile(MyApplication.getAppContext(),"com.heziz.qixia3.fileProvider",param);

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(contentUri, "text/html");
        return intent;
    }

    // Android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent(File param) {
        Uri contentUri = FileProvider.getUriForFile(MyApplication.getAppContext(),"com.heziz.qixia3.fileProvider",param);

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(contentUri, "image/*");
        return intent;
    }

    // Android获取一个用于打开PPT文件的intent
    public static Intent getPptFileIntent(File param) {
        Uri contentUri = FileProvider.getUriForFile(MyApplication.getAppContext(),"com.heziz.qixia3.fileProvider",param);

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(contentUri, "application/vnd.ms-powerpoint");
        return intent;
    }

    // Android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent(File param) {
        Uri contentUri = FileProvider.getUriForFile(MyApplication.getAppContext(),"com.heziz.qixia3.fileProvider",param);

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(contentUri, "application/vnd.ms-excel");
        return intent;
    }

    // Android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(File param) {
        Uri contentUri = FileProvider.getUriForFile(MyApplication.getAppContext(),"com.heziz.qixia3.fileProvider",param);

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(contentUri, "application/msword");
        return intent;
    }

    // Android获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent(File param) {
        Uri contentUri = FileProvider.getUriForFile(MyApplication.getAppContext(),"com.heziz.qixia3.fileProvider",param);

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(contentUri, "application/x-chm");
        return intent;
    }

    // Android获取一个用于打开文本文件的intent
    public static Intent getTextFileIntent(File param, boolean paramBoolean) {

        Uri contentUri = FileProvider.getUriForFile(MyApplication.getAppContext(),"com.heziz.qixia3.fileProvider",param);

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (paramBoolean) {
            intent.setDataAndType(contentUri, "text/plain");
        } else {
            intent.setDataAndType(contentUri, "text/plain");
        }
        return intent;
    }

    // Android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent(File param) {

        Uri contentUri = FileProvider.getUriForFile(MyApplication.getAppContext(),"com.heziz.qixia3.fileProvider",param);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(contentUri, "application/pdf");
        return intent;
    }

}
