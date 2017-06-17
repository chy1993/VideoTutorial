package com.chy.videotutorial.module.main.view;

import android.view.View;
import android.widget.Button;

import com.chy.videotutorial.R;
import com.chy.videotutorial.base.activity.BaseAppCompatActivity;

import butterknife.BindView;

public class Main2Activity extends BaseAppCompatActivity {
    @BindView(R.id.button4)
    Button button;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("111111");
            }
        });
    }

    @Override
    protected void initDataAfterView() {

    }
}
