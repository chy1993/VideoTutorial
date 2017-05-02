package com.chy.videotutorial.module.main.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import com.chy.videotutorial.MyWiew.CourseInfoDialog;
import com.chy.videotutorial.MyWiew.NoSlideViewPager;
import com.chy.videotutorial.MyWiew.PageNumberView;
import com.chy.videotutorial.R;
import com.chy.videotutorial.Utils.Constants;
import com.chy.videotutorial.base.fragment.BaseCallBackFrg2Aty;
import com.chy.videotutorial.base.fragment.BaseFragment;
import com.chy.videotutorial.base.fragment.BaseMvpFragment;
import com.chy.videotutorial.base.mvp.BasePresenter;
import com.chy.videotutorial.entities.VideoInfo;
import com.chy.videotutorial.entities.VideoTypeDetailInfo;
import com.chy.videotutorial.module.main.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OnlineFragment extends BaseMvpFragment<MainPresenter> implements IMainView{

    @BindView(R.id.vpOnlineVideoPaging)
    NoSlideViewPager mViewPager;

    @BindView(R.id.onLineVideoPageNumberView)
    PageNumberView mPageNumberView;

    @BindView(R.id.ibBack)
    ImageButton mBackOrHome;

    @BindView(R.id.ibRefrsh)
    ImageButton mRefrsh;

    private List<View> mOnlineVideoGridViewList;                     //GridView作为一个View对象添加到ViewPager集合中
    private int mPageSize = Constants.PAGE_SIZE_ONLINE;              //每页显示的最大的数量
    private int mTotalPage;                                          //总的页数
//    private List listDatas;                                          //总的数据源

    List<VideoTypeDetailInfo> mVideoTypeDetailInfos;

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
        mVideoTypeDetailInfos = videoInfo.getPageContent();
        mTotalPage = videoInfo.getTotalPageCount();

        initOnlineVideo();
    }

    /**
     * Activity通过此接口传递数据给此Fragment
     */
    interface OnSetDataToFragment {
    }

    public static OnlineFragment newInstance() {
        OnlineFragment fragment = new OnlineFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.main_fragment_online_title;
    }

    @Override
    protected void initDataBeforeView() {
        super.initDataBeforeView();
        mPresenter.loadVideoInfo(1,9);

    }

    @Override
    protected void initView() {
//        initOnlineVideo();
    }

    @Override
    protected void initDataAfterView() {

    }


    /**
     * 课程名的VeiwPager GridView PageNumberView等的初始化
     */
    private void initOnlineVideo(){
        mOnlineVideoGridViewList = new ArrayList<View>();
        for (int i = 0; i < mTotalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(getActivity(), R.layout.item_viewpager_gridview_online_video, null);
            gridView.setPadding(100,50,100,0);
            OnlineVideoGVAdapter adapter = new OnlineVideoGVAdapter(getActivity(), i, mPageSize);
            adapter.setData(mVideoTypeDetailInfos);
            gridView.setAdapter(adapter);

            //添加item点击监听
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    showCourseInfoDialog();

                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            mOnlineVideoGridViewList.add(gridView);
        }
        //设置ViewPager适配器
        mViewPager.setAdapter(new OnlineVideoPagingViewPagerAdapter(mOnlineVideoGridViewList));

        mPageNumberView.setmTotalPages(mTotalPage);
        mPageNumberView.setViewPager(mViewPager);
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
     * 将数据展示在界面上
     */
    private void setPageContent(List<VideoTypeDetailInfo> data){


    }
}
