package com.chy.videotutorial.module.main.view;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.chy.videotutorial.MyWiew.NoSlideViewPager;
import com.chy.videotutorial.R;
import com.chy.videotutorial.base.activity.BaseAppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseAppCompatActivity implements CourseFragment.OnGridViewChangeListener {
//    @BindView(R.id.ibBack)
//    ImageButton mBack;

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




    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initViewPager();
        initRadioGroup();
    }

    @Override
    protected void initDataAfterView() {

    }


    /**
     * 初始化视频类型切换的ViewPager
     */
    private void initViewPager(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new ViewTypeViewPagerAdapter(fragmentManager,this);
        mVideoTypeViewPager.setAdapter(mAdapter);
        mVideoTypeViewPager.setOffscreenPageLimit(3);
        mVideoTypeViewPager.setCurrentItem(0);
    }

    /**
     * 初始化切换视频类型的RadioGroup
     */
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

//    @OnClick(R.id.ibBack)
//    public void onBackOrHome(){
//        if (!isHome){
//            if (mAdapter.currentFragment instanceof CourseFragment){
//                ((CourseFragment) mAdapter.currentFragment).showCourseTitleGridView();
//            }
//        }else {
//            finish();
//        }
//    }

    @OnClick(R.id.ibLeft)
    public void onTabToLeft(){
        int currentItem = mVideoTypeViewPager.getCurrentItem();
        if (currentItem == 0){
            mVideoTypeViewPager.setCurrentItem(3);
            mVideoTypeRadioGroup.check(R.id.rbOnline);
        }else if (currentItem == 1){
            mVideoTypeViewPager.setCurrentItem(0);
            mVideoTypeRadioGroup.check(R.id.rbAbc);
        }else if (currentItem == 2){
            mVideoTypeViewPager.setCurrentItem(1);
            mVideoTypeRadioGroup.check(R.id.rbGrading);
        }else if (currentItem == 3){
            mVideoTypeViewPager.setCurrentItem(2);
            mVideoTypeRadioGroup.check(R.id.rbWorld);
        }else {

        }
    }

    @OnClick(R.id.ibRight)
    public void onTabToRight(){
        int currentItem = mVideoTypeViewPager.getCurrentItem();
        if (currentItem == 0){
            mVideoTypeViewPager.setCurrentItem(1);
            mVideoTypeRadioGroup.check(R.id.rbGrading);
        }else if (currentItem == 1){
            mVideoTypeViewPager.setCurrentItem(2);
            mVideoTypeRadioGroup.check(R.id.rbWorld);
        }else if (currentItem == 2){
            mVideoTypeViewPager.setCurrentItem(3);
            mVideoTypeRadioGroup.check(R.id.rbOnline);
        }else if (currentItem == 3){
            mVideoTypeViewPager.setCurrentItem(0);
            mVideoTypeRadioGroup.check(R.id.rbAbc);
        }else {

        }
    }


    /**
     * 跟新返回按钮状态的回调方法
     */
    @Override
    public void updateBackButton() {
//        if (isHome) {
//            mBack.setBackground(getResources().getDrawable(R.mipmap.common_back_mid));
//        }else {
//            mBack.setBackground(getResources().getDrawable(R.mipmap.common_home));
//        }
//        isHome = !isHome;
    }
}
