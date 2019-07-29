package com.heziz.qixia3.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heziz.qixia3.bean.HomeListBean;
import com.heziz.qixia3.bean.ProjectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sws on 2019-05-08.
 * from:
 * describe:
 */

public class ListDataSave {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public ListDataSave(Context mContext, String preferenceName) {
        preferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * 保存List
     * @param tag
     * @param datalist
     */
    public void setDataList(String tag, List<HomeListBean<List<ProjectBean>>> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.clear();
        editor.putString(tag, strJson);
        editor.commit();

    }

    /**
     * 获取List
     * @param tag
     * @return
     */
    public List<HomeListBean<List<ProjectBean>>> getDataList(String tag) {
        List<HomeListBean<List<ProjectBean>>> datalist=new ArrayList<HomeListBean<List<ProjectBean>>>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<HomeListBean<List<ProjectBean>>>>() {
        }.getType());
        return datalist;

    }
}
