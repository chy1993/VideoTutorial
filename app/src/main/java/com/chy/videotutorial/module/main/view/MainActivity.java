package com.chy.videotutorial.module.main.view;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chy.videotutorial.MyWiew.NoSlideViewPager;
import com.chy.videotutorial.MyWiew.VideoSearchDialog;
import com.chy.videotutorial.R;
import com.chy.videotutorial.Utils.LogUtils;
import com.chy.videotutorial.base.activity.BaseAppCompatActivity;
import com.chy.videotutorial.base.activity.BaseMvpAppCompatAty;
import com.chy.videotutorial.entities.VideoInfo;
import com.chy.videotutorial.module.main.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseMvpAppCompatAty<MainPresenter> implements CourseFragment.OnGridViewChangeListener,IMainView {

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

    @BindView(R.id.tvLeftListTitle)
    TextView mLeftListTitle;

    @BindView(R.id.tvLeftList1)
    TextView mLeftList1;

    @BindView(R.id.tvLeftList2)
    TextView mLeftList2;

    @BindView(R.id.tvLeftList3)
    TextView mLeftList3;

    @BindView(R.id.tvLeftList4)
    TextView mLeftList4;

    @BindView(R.id.tvLeftList5)
    TextView mLeftList5;

    @BindView(R.id.tvLeftList6)
    TextView mLeftList6;

    @BindView(R.id.tvLeftList7)
    TextView mLeftList7;

    @BindView(R.id.tvLeftList8)
    TextView mLeftList8;

    @BindView(R.id.tvLeftList9)
    TextView mLeftList9;

    private ViewTypeViewPagerAdapter mAdapter;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initViewPager();
        initRadioGroup();
        initLeftList();
    }

    @Override
    protected void initDataAfterView() {
        mPresenter.loadVideoInfo(1,7);
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

    /**
     * 左侧列表的初始化
     */
    private void initLeftList(){
        mLeftList1.setSelected(true);
        mLeftList2.setSelected(true);
        mLeftList3.setSelected(true);
        mLeftList4.setSelected(true);
        mLeftList5.setSelected(true);
        mLeftList6.setSelected(true);
        mLeftList7.setSelected(true);
        mLeftList8.setSelected(true);
        mLeftList9.setSelected(true);
    }

    @OnClick(R.id.ibSearch)
    public void onShowSearchView(){
        VideoSearchDialog dialog = new VideoSearchDialog(this);
        dialog.show();
    }

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

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void onShowErrorView(String msg) {

    }

    @Override
    public void onShowLoadingView(String msg) {

    }

    @Override
    public void onHideLoadingView() {

    }

    @Override
    public void setVideoInfoData(VideoInfo videoInfo) {
        int pageIndexe = videoInfo.getCurrentPageIndex();
        LogUtils.getInstance().d(pageIndexe);
        Log.d("klsw",pageIndexe+"");
    }
}
