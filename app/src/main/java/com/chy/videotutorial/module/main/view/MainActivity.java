package com.chy.videotutorial.module.main.view;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.chy.videotutorial.MyWiew.NoSlideViewPager;
import com.chy.videotutorial.R;
import com.chy.videotutorial.base.activity.BaseAppCompatActivity;
import com.chy.videotutorial.module.videoplayer.VideoPlayerActivity;

import butterknife.BindView;

public class MainActivity extends BaseAppCompatActivity implements CourseTitleFragment.OnGridViewChangeListener {
    @BindView(R.id.ibBack)
    ImageButton mBack;

    @BindView(R.id.ibLeft)
    ImageButton mLeft;

    @BindView(R.id.ibRight)
    ImageButton mRight;

    @BindView(R.id.ibSearch)
    ImageButton mSearch;

    @BindView(R.id.rgVideoType)
    RadioGroup mVideoTypeRadioGroup;

    @BindView(R.id.vpVideoType)
    NoSlideViewPager mVideoTypeViewPager;

    private ViewTypeViewPagerAdapter mAdapter;

    private boolean  isHome  = true;                  //判断按钮时home键还是back键   默认是home键


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        initViewPager();
        initRadioGroup();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHome){

                }else {
                    finish();
                }

            }
        });
    }

    @Override
    protected void initDataAfterView() {

    }


    private void initViewPager(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new ViewTypeViewPagerAdapter(fragmentManager,this);
        mVideoTypeViewPager.setAdapter(mAdapter);
        mVideoTypeViewPager.setOffscreenPageLimit(3);
        mVideoTypeViewPager.setCurrentItem(0);
    }

    private void initRadioGroup(){
        mVideoTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rbAbc:
                        mVideoTypeViewPager.setCurrentItem(0,false);
                        break;
                    case R.id.rbGrading:
                        mVideoTypeViewPager.setCurrentItem(1,false);
                        break;
                    case R.id.rbWorld:
                        mVideoTypeViewPager.setCurrentItem(2,false);
                        break;
                    case R.id.rbOnline:
                        mVideoTypeViewPager.setCurrentItem(3,false);
                        break;
                    default:
                        mVideoTypeViewPager.setCurrentItem(0,false);
                        break;

                }
            }
        });
    }

    @Override
    public void updateBackButton() {
        if (isHome) {
            mBack.setBackground(getResources().getDrawable(R.mipmap.common_back_mid));
        }else {
            mBack.setBackground(getResources().getDrawable(R.mipmap.common_home));
        }
        isHome = !isHome;
    }
}
