package com.heziz.qixia3.adaper.xmjc.rcjc;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heziz.qixia3.R;
import com.heziz.qixia3.bean.ProjectBean;
import com.heziz.qixia3.bean.rcjc.CheckProbean;
import com.heziz.qixia3.utils.StringUtil;

import java.util.List;

/**
 * Created by sws on 2018/5/12.
 * from:
 * describe:
 */

public class NewCheckSelectProjectListAdapter extends BaseQuickAdapter<CheckProbean,BaseViewHolder> {
    private Context context;
    public NewCheckSelectProjectListAdapter(Context context, List<CheckProbean> list) {
        super(R.layout.new_check_pro_list_item,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckProbean item) {
        helper.setText(R.id.tvName, item.getProjectName());
        helper.setText(R.id.tvNum, helper.getLayoutPosition()+1+"");

    }
}
