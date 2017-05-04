package com.chy.videotutorial.module.main.view;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chy.videotutorial.MyWiew.NoSlideViewPager;
import com.chy.videotutorial.MyWiew.VideoSearchDialog;
import com.chy.videotutorial.R;
import com.chy.videotutorial.base.activity.BaseAppCompatActivity;
import com.chy.videotutorial.entities.VideoTypeInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseAppCompatActivity implements CourseFragment.OnGridViewChangeListener{

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

    @BindView(R.id.tvLeftTVListTitle)
    TextView mLeftTVListTitle;

    @BindView(R.id.tvLeftTVList1)
    TextView mLeftTVList1;

    @BindView(R.id.tvLeftTVList2)
    TextView mLeftTVList2;

    @BindView(R.id.tvLeftTVList3)
    TextView mLeftTVList3;

    @BindView(R.id.tvLeftTVList4)
    TextView mLeftTVList4;

    @BindView(R.id.tvLeftTVList5)
    TextView mLeftTVList5;

    @BindView(R.id.tvLeftTVList6)
    TextView mLeftTVList6;

    @BindView(R.id.tvLeftTVList7)
    TextView mLeftTVList7;

    @BindView(R.id.tvLeftTVList8)
    TextView mLeftTVList8;

    @BindView(R.id.tvLeftTVList9)
    TextView mLeftTVList9;

    @BindView(R.id.tvLeftCVListTitle)
    TextView mLeftCVListTitle;

    @BindView(R.id.tvLeftCVList1)
    TextView mLeftCVList1;

    @BindView(R.id.tvLeftCVList2)
    TextView mLeftCVList2;

    @BindView(R.id.tvLeftCVList3)
    TextView mLeftCVList3;

    @BindView(R.id.tvLeftCVList4)
    TextView mLeftCVList4;

    @BindView(R.id.tvLeftCVList5)
    TextView mLeftCVList5;

    @BindView(R.id.tvLeftCVList6)
    TextView mLeftCVList6;

    @BindView(R.id.tvLeftCVList7)
    TextView mLeftCVList7;

    @BindView(R.id.tvLeftCVList8)
    TextView mLeftCVList8;

    @BindView(R.id.tvLeftCVList9)
    TextView mLeftCVList9;

    @BindView(R.id.ivRefresh)
    ImageView mRefreshImg;

    @BindView(R.id.rlMainBG)
    RelativeLayout mBG;

    @BindView(R.id.llCommonVideo)
    LinearLayout mCV;

    @BindView(R.id.llTeachingVideo)
    LinearLayout mTV;

    private ViewTypeViewPagerAdapter mAdapter;


//    List<VideoTypeInfo> mVideoTypeInfoList;                             //左侧视频类型列表的集合

//    public void setVideoTypeInfoList(List<VideoTypeInfo> videoTypeInfoList) {
//        this.mVideoTypeInfoList = videoTypeInfoList;
//    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initViewPager();
        initRadioGroup();
        initLeftList();
        Glide.with(this).load(R.drawable.common_wait_end).into(mRefreshImg);
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
                        showCVList();
                        break;
                    case R.id.rbGrading:
                        mVideoTypeViewPager.setCurrentItem(1,false);
                        showCVList();
                        break;
                    case R.id.rbWorld:
                        mVideoTypeViewPager.setCurrentItem(2,false);
                        showCVList();
                        break;
                    case R.id.rbOnline:
                        mVideoTypeViewPager.setCurrentItem(3,false);
                        showTVList();
                        break;
                    default:
                        mVideoTypeViewPager.setCurrentItem(0,false);
                        showCVList();
                        break;

                }
            }
        });
    }

    /**
     * 左侧列表的初始化
     * 为实现跑马灯效果 需设置setSelected(true);
     */
    private void initLeftList(){
        mLeftTVList1.setSelected(true);
        mLeftTVList2.setSelected(true);
        mLeftTVList3.setSelected(true);
        mLeftTVList4.setSelected(true);
        mLeftTVList5.setSelected(true);
        mLeftTVList6.setSelected(true);
        mLeftTVList7.setSelected(true);
        mLeftTVList8.setSelected(true);
        mLeftTVList9.setSelected(true);
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


    /**
     * 设置左边视频类型列表
     * @param videoTypeInfoList
     */
    public void setLeftVideoType(List<VideoTypeInfo> videoTypeInfoList){
        mLeftTVListTitle.setText(R.string.TeachingVideo);
        mLeftTVList1.setText(videoTypeInfoList.get(0).getTypeName());
        mLeftTVList2.setText(videoTypeInfoList.get(1).getTypeName());
        mLeftTVList3.setText(videoTypeInfoList.get(2).getTypeName());
        mLeftTVList4.setText(videoTypeInfoList.get(3).getTypeName());
        mLeftTVList5.setText(videoTypeInfoList.get(4).getTypeName());
        mLeftTVList6.setText(videoTypeInfoList.get(5).getTypeName());
        mLeftTVList7.setText(videoTypeInfoList.get(6).getTypeName());
        mLeftTVList8.setText(videoTypeInfoList.get(7).getTypeName());
//        mLeftTVList9.setText(videoTypeInfoList.get(8).getTypeName());
    }

    /**
     * 设置左边常用视频列表
     */
    public void setLeftCommonVideo(){
        mLeftTVListTitle.setText(R.string.CommonVideo);
        mLeftTVList1.setText("");
        mLeftTVList2.setText("");
        mLeftTVList3.setText("");
        mLeftTVList4.setText("");
        mLeftTVList5.setText("");
        mLeftTVList6.setText("");
        mLeftTVList7.setText("");
        mLeftTVList8.setText("");
//        mLeftTVList9.setText(videoTypeInfoList.get(8).getTypeName());
    }

    /**
     * 展示加载动画
     */
    public void showLoadingView(){
        mRefreshImg.setVisibility(View.VISIBLE);
        mBG.setAlpha(0.5f);
        mBG.setEnabled(false);
    }


    /**
     *隐藏加载动画
     */
    public void hideLoadingView(){
        mRefreshImg.setVisibility(View.GONE);
        mBG.setAlpha(1);
        mBG.setEnabled(true);
    }


    /**
     * 展示教学视频列表
     */
    public void showTVList(){
        mTV.setVisibility(View.VISIBLE);
        mCV.setVisibility(View.GONE);
    }

    /**
     * 展示常用视频列表
     */
    public void showCVList(){
        mCV.setVisibility(View.VISIBLE);
        mTV.setVisibility(View.GONE);
    }


}
