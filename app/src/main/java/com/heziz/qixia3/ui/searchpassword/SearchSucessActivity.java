package com.heziz.qixia3.ui.searchpassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.heziz.qixia3.R;
import com.heziz.qixia3.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchSucessActivity extends BaseActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_sucess);
        ButterKnife.bind(this);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("zh_password_success");
                sendBroadcast(intent);
                finish();
            }
        });
    }
}
