package com.heziz.qixia3.adaper.sp;

import android.content.Context;
import android.widget.ImageView;

import com.android.business.entity.ChannelInfo;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.qixia3.R;
import com.heziz.qixia3.bean.sp.ProjectVideoBean;
import com.heziz.qixia3.bean.sp.VideoProjectBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class DeviceInfoAdapter extends BaseQuickAdapter<ProjectVideoBean<VideoProjectBean>,BaseViewHolder> {
    private Context context;
    public DeviceInfoAdapter(Context context, List<ProjectVideoBean<VideoProjectBean>> list) {
        super(R.layout.shipin_channel_list,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectVideoBean<VideoProjectBean> item) {
        helper.setText(R.id.tvChannelName, "设备");
        if(item.getIsOnline().equals("Offline")){
            Glide.with(mContext).load(R.drawable.sp_offline).into((ImageView) helper.getView(R.id.ivChannel));
        }else{
            Glide.with(mContext).load(R.drawable.sp_online).into((ImageView) helper.getView(R.id.ivChannel));
        }
////        helper.setText(R.id.tvNum, String.valueOf(n));
////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
//        Glide.with(mContext).load(R.mipmap.zhihui_fangjian_icon).crossFade().into((ImageView) helper.getView(R.id.ivType));
    }
}
