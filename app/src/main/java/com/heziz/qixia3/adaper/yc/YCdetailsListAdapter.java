package com.heziz.qixia3.adaper.yc;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.qixia3.R;
import com.heziz.qixia3.bean.car.CarDetailsBean;
import com.heziz.qixia3.bean.yc.YcRealTimeBean;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class YCdetailsListAdapter extends BaseQuickAdapter<YcRealTimeBean,BaseViewHolder> {
    private Context context;
    public YCdetailsListAdapter(Context context, List<YcRealTimeBean> list) {
        super(R.layout.yc_details_list,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, YcRealTimeBean item) {
        helper.setText(R.id.tvZ, item.getA34002Rtd()+"");
        helper.setText(R.id.tvNum, helper.getLayoutPosition()+1+"");
        helper.setText(R.id.tvTime,item.getSysdate());
//        helper.setText(R.id.tvzh,item.getBuild()+"");
//        helper.setText(R.id.tvcb,item.getDiff()+"");
////                .setText(R.id.tweetText, item.getText())
////                .setText(R.id.tweetDate, item.getCreatedAt())
////                .setVisible(R.id.tweetRT, item.isRetweet())
////                .linkify(R.id.tweetText);
//        if (item.get)
//        Glide.with(mContext).load(R.mipmap.zhihui_fangjian_icon).crossFade().into((ImageView) helper.getView(R.id.ivType));
    }
}
