package com.heziz.qixia3.adaper.xmjc.rcjc;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.qixia3.R;
import com.heziz.qixia3.bean.HomeListBean;
import com.heziz.qixia3.bean.ProjectBean;
import com.heziz.qixia3.bean.rcjc.CheckDetailsBean;
import com.heziz.qixia3.utils.ScreenUtils;
import com.heziz.qixia3.view.LeftSlideView;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class CheckListAdapter extends BaseQuickAdapter<CheckDetailsBean,BaseViewHolder>  {
    public CheckListAdapter(Activity context, List<CheckDetailsBean> list) {
        super(R.layout.check_list_item,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckDetailsBean item) {

        helper.setText(R.id.tvName, item.getProjectName());
        int endStatus=item.getEndStatus();
        switch (endStatus){
            case 1:
                helper.setText(R.id.tvHg,"未检查");
                break;
            case 2:
                helper.setText(R.id.tvHg,"合格");
                break;
            case 3:
                helper.setText(R.id.tvHg,"不合格");
                break;
            case 4:
                helper.setText(R.id.tvHg,"通知整改");
                break;
            case 5:
                helper.setText(R.id.tvHg,"整改待确认");
                break;
            case 6:
                helper.setText(R.id.tvHg,"整改通过");
                break;
            case 7:
                helper.setText(R.id.tvHg,"整改不通过");
                break;
        }

        helper.setText(R.id.tvCheckName,item.getWgName());
        helper.setText(R.id.tvTime,item.getCreateTime());
        helper.addOnClickListener(R.id.tv_delete);

    }

}


