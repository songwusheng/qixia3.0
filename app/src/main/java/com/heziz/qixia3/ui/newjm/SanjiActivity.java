package com.heziz.qixia3.ui.newjm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heziz.qixia3.R;
import com.heziz.qixia3.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SanjiActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.ivPicBg)
    ImageView iv;
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanji);

        ButterKnife.bind(this);

        type=getIntent().getIntExtra("type",0);

        switch (type){
            case 100:
                tvTitle.setText("塔吊监控");
                //iv.setImageResource(R.drawable.td_bg);
                Glide.with(this).load(R.drawable.td_bg).into(iv);
                break;
            case 101:
                tvTitle.setText("实名制考勤");
                Glide.with(this).load(R.drawable.smz_bg).into(iv);
                break;
            case 102:
                tvTitle.setText("安全管理");
                Glide.with(this).load(R.drawable.aq_bg).into(iv);
                break;
            case 103:
                tvTitle.setText("质量管理");
                Glide.with(this).load(R.drawable.zl_bg).into(iv);
                break;
        }



        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
