package com.heziz.qixia3.adaper.project;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.qixia3.R;
import com.heziz.qixia3.bean.HomeListBean;
import com.heziz.qixia3.bean.ProjectBean;
import com.heziz.qixia3.utils.StringUtil;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class ProjectListAdapter extends BaseQuickAdapter<ProjectBean,BaseViewHolder> {
    private Context context;
    public ProjectListAdapter(Context context, List<ProjectBean> list) {
        super(R.layout.project_lsit_item,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectBean item) {
        helper.setText(R.id.tvName, item.getName());
        helper.setText(R.id.tvNum, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvStreet,item.getJdName());
        String s="";
        String type=item.getPType();
        if(StringUtil.isNull(type)){
            s="-";
        }else if(type.equals("1")) {
            s = "房建";
        }else if (type.equals("2")) {
            s = "市政";
        }else if (type.equals("3")) {
            s = "交通";
        }else if (type.equals("4")) {
            s = "轨道";
        }else if (type.equals("5")) {
            s = "水务";
        }else if (type.equals("6")) {
            s = "园林";
        }else{
            s="其他";
        }
        helper.setText(R.id.tvtype,s);
        String diff="";
        if(item.getDiff().equals("1")){
            diff="差别化工地";
        }else if (item.getDiff().equals("2")){
            diff="智慧工地";
        }else{
            diff="智慧差别";
        }
        helper.setText(R.id.tvProperty,diff);
////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
//        Glide.with(mContext).load(R.mipmap.zhihui_fangjian_icon).crossFade().into((ImageView) helper.getView(R.id.ivType));
    }
}
