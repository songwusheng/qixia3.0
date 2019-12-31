package com.heziz.qixia3.ui.newjm;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heziz.qixia3.R;
import com.heziz.qixia3.base.BaseActivity;
import com.heziz.qixia3.fragment.main.HomeFragment;
import com.heziz.qixia3.fragment.main.ProjectFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommActivity extends BaseActivity {
    @BindView(R.id.rlBack)
    RelativeLayout rlBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    String title;
    int type;

    private HomeFragment homeFragment;
    private ProjectFragment projectFragment;
    //private RenwuFragment renwuFragment;
    private MineNewFragment mineFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        title=getIntent().getStringExtra("title");
        tvTitle.setText(title);
        type=getIntent().getIntExtra("type",0);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        switch (type){
            case 0:
                homeFragment = new HomeFragment();
                fragmentTransaction.add(R.id.flMain, homeFragment);
                break;
            case 1:
                projectFragment = new ProjectFragment();
                fragmentTransaction.add(R.id.flMain, projectFragment);
                break;
            case 2:
                //renwuFragment = new RenwuFragment();
                //fragmentTransaction.add(R.id.flMain, renwuFragment);
                break;
            case 3:
                mineFragment = new MineNewFragment();
                fragmentTransaction.add(R.id.flMain, mineFragment);
                break;
        }
        fragmentTransaction.commit();

        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
