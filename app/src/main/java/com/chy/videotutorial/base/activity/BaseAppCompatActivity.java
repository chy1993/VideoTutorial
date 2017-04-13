package com.chy.videotutorial.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.Toast;


import com.chy.videotutorial.R;
import com.chy.videotutorial.Utils.AppManager;
import com.chy.videotutorial.Utils.AppUtils;

import butterknife.ButterKnife;


/**
 * 所有AppCompatActivity的基类，默认实现了创建时被添加到管理队列，销毁是移出队列的处理
 *
 * Created by 丁飞 on 2016/1/7.
 *
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    /**获取布局资源ID*/
    protected abstract int getLayoutResID();

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 视图初始化后，填充初始化数据
     */
    protected abstract void initDataAfterView();

    /**
     * 视图未初始化之前，初始化的数据
     */
    protected void initDataBeforeView() {}

    protected boolean   mExitWithAnim = true;
    /**快速点击坐标记录*/
    private float xDown=0,yDown=0,moveToX=0,moveToY=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到管理队列
        AppManager.getInstance().addActivity(this);
        setContentView(getLayoutResID());
        ButterKnife.bind(this);

        initDataBeforeView();
        initView();
        initDataAfterView();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppManager.getInstance().onResume(this);
    }

    @Override
    protected void onDestroy() {
        // 移除Activity
        AppManager.getInstance().removeActivity(this);

        super.onDestroy();
    }

    protected void showToast(String message ) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void setTitle(String strTitle, boolean showHome){
        setTitle(strTitle);
        getSupportActionBar().setDisplayShowHomeEnabled(showHome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHome);
    }

    public void startActivitySlide(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if ( mExitWithAnim )
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public void finish() {
        super.finish();
        if ( mExitWithAnim )
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                xDown = ev.getRawX();
                yDown = ev.getRawY();
                if ( AppUtils.isFastClick() ) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                moveToX = ev.getRawX()-xDown;//X轴偏移距离
                moveToY = ev.getRawY()-yDown;//y轴偏移距离
                if( moveToX > 20.0 || moveToX < -20.0|| moveToY > 20.0 || moveToY < -20.0 ){
                    AppUtils.clearLastClickTime();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}