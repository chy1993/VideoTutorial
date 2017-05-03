package com.chy.videotutorial.module.main.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chy.videotutorial.MyWiew.CourseInfoDialog;
import com.chy.videotutorial.MyWiew.NoSlideViewPager;
import com.chy.videotutorial.MyWiew.OnlinePageNumberView;
import com.chy.videotutorial.MyWiew.PageNumberView;
import com.chy.videotutorial.R;
import com.chy.videotutorial.Utils.Constants;
import com.chy.videotutorial.base.fragment.BaseCallBackFrg2Aty;
import com.chy.videotutorial.base.fragment.BaseFragment;
import com.chy.videotutorial.base.fragment.BaseMvpFragment;
import com.chy.videotutorial.base.mvp.BasePresenter;
import com.chy.videotutorial.entities.VideoInfo;
import com.chy.videotutorial.entities.VideoTypeDetailInfo;
import com.chy.videotutorial.entities.VideoTypeInfo;
import com.chy.videotutorial.module.main.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OnlineFragment extends BaseMvpFragment<MainPresenter> implements IMainView{
    @BindView(R.id.gvOnlineVideo)
    GridView mGridView;

    @BindView(R.id.onLineVideoPageNumberView)
    OnlinePageNumberView mPageNumberView;

    @BindView(R.id.ibBack)
    ImageButton mBackOrHome;

    @BindView(R.id.ibRefrsh)
    ImageButton mRefrsh;

    private int mPageSize = Constants.PAGE_SIZE_ONLINE;              //每页显示的最大的数量
    private int mTotalPage;                                          //总的页数
    private int mCurrentPageIndex = Constants.PAGE_CURRENT_ONLINE;     //默认展示第一页
    private int mCurrentListID = Constants.VIDEO_TYPE_ONLINE;

    List<VideoTypeDetailInfo> mVideoTypeDetailInfos;                 //页面信息的集合

    OnlineVideoGVAdapter mAdapter;

    MainActivity activity;

//    OnActivityLeftListClicked mListener;
//
//    public interface OnActivityLeftListClicked{
//        void  onClicked();
//    }
//
//    public void setActivityLeftListClicked(OnActivityLeftListClicked listener){
//        this.mListener = listener;
//    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }


    @Override
    public void setVideoInfoData(VideoInfo videoInfo) {
        mVideoTypeDetailInfos = videoInfo.getPageContent();
        List<VideoTypeInfo> videoTypeInfoList = videoInfo.getListContent();

        mTotalPage = videoInfo.getTotalPageCount();
        mPageNumberView.setmTotalPages(mTotalPage);
        mAdapter.setData(mVideoTypeDetailInfos);

        activity.setLeftVideoType(videoInfo.getListContent());
//        activity.setVideoTypeInfoList(videoInfo.getListContent());

        mCurrentListID = videoInfo.getCurrentListID();

        setClickListener(activity.mLeftList1,videoTypeInfoList.get(0).getID());
        setClickListener(activity.mLeftList2,videoTypeInfoList.get(1).getID());
        setClickListener(activity.mLeftList3,videoTypeInfoList.get(2).getID());
        setClickListener(activity.mLeftList4,videoTypeInfoList.get(3).getID());
        setClickListener(activity.mLeftList5,videoTypeInfoList.get(4).getID());
        setClickListener(activity.mLeftList6,videoTypeInfoList.get(5).getID());
        setClickListener(activity.mLeftList7,videoTypeInfoList.get(6).getID());
        setClickListener(activity.mLeftList8,videoTypeInfoList.get(7).getID());
    }


    public static OnlineFragment newInstance() {
        OnlineFragment fragment = new OnlineFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.main_fragment_online_title;
    }

    @Override
    protected void initDataBeforeView() {
        super.initDataBeforeView();
        activity = (MainActivity) getActivity();

        mPresenter.loadVideoInfo(mCurrentPageIndex,mCurrentListID);
    }

    @Override
    protected void initView() {
        initOnlineVideo();
    }

    @Override
    protected void initDataAfterView() {

    }


    /**
     * 课程名的VeiwPager GridView PageNumberView等的初始化
     */
    private void initOnlineVideo(){
        mAdapter  = new OnlineVideoGVAdapter(getActivity(), mPageSize);
        mGridView.setPadding(100,50,100,0);
        mGridView.setAdapter(mAdapter);
        mPageNumberView.setPageChangeListener(new OnlinePageNumberView.OnPageChangeListener() {
            @Override
            public void onChanged(int pageNum) {
                mPresenter.loadVideoInfo(pageNum,mCurrentListID);
            }
        });

    }


    @OnClick({R.id.ibBack, R.id.ibRefrsh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibBack:
                break;
            case R.id.ibRefrsh:
                break;
        }
    }

    /**
     * 展示课程详细信息的dialog
     */
    private void showCourseInfoDialog(){
        CourseInfoDialog dialog = new CourseInfoDialog(getActivity());
        dialog.show();
    }

    /**
     * 一组TextView点击事件 写在一起
     * @param textView
     * @param currentListID
     */
    private void setClickListener(TextView textView, final int currentListID){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loadVideoInfo(1,currentListID);
            }
        });
    }




    /**
     * 将数据展示在界面上
     */
    private void setPageContent(List<VideoTypeDetailInfo> data){


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
}
