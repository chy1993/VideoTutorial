package com.chy.videotutorial.module.main.view;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chy.videotutorial.R;
import com.chy.videotutorial.base.activity.BaseAppCompatActivity;
import com.chy.videotutorial.module.videoplayer.VideoPlayerActivity;

import butterknife.BindView;

public class MainActivity extends BaseAppCompatActivity {
    @BindView(R.id.button)
    Button button;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoPlayerActivity.navigationToActivity(MainActivity.this);
            }
        });
    }

    @Override
    protected void initDataAfterView() {

    }
}
