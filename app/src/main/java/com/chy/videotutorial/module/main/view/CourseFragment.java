package com.chy.videotutorial.module.main.view;


import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.chy.videotutorial.MyWiew.NoSlideViewPager;
import com.chy.videotutorial.MyWiew.PageNumberView;
import com.chy.videotutorial.R;
import com.chy.videotutorial.Utils.Constants;
import com.chy.videotutorial.base.activity.BaseAppCompatActivity;
import com.chy.videotutorial.base.fragment.BaseCallBackFrg2Aty;
import com.chy.videotutorial.module.videoplayer.VideoPlayerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseFragment extends BaseCallBackFrg2Aty<CourseFragment.OnGridViewChangeListener> {

    @BindView(R.id.ibBack)
    ImageButton mBack;

    @BindView(R.id.llCourseTitle)
    LinearLayout mCourseTitle;

    @BindView(R.id.vpCourseTitlePaging)
    NoSlideViewPager mCourseTitlePagingViewPager;

    @BindView(R.id.courseTitlePageNumberView)
    PageNumberView mCourseTitlePageNumberView;

    @BindView(R.id.llCourseContent)
    LinearLayout mCourseContent;

    @BindView(R.id.vpCourseContentPaging)
    NoSlideViewPager mCourseContentPagingViewPager;

    @BindView(R.id.courseContentPageNumberView)
    PageNumberView mCourseContentPageNumberView;

    private boolean isHome = true;                                  //判断按钮是home键还是back键   默认是home键

    private int mPageSize = Constants.PAGE_SIZE_COURSE;                                      //每页显示的最大的数量
    private int totalPage;                                          //总的页数
    private List listDatas;                                         //总的数据源
    private List<View> mCourseTitleGridViewList;                    //GridView作为一个View对象添加到ViewPager集合中
    private List<View> mCourseContentGridViewList;                  //GridView作为一个View对象添加到ViewPager集合中

    MainActivity activity;

    /**
     * 当GridView切换时为Activity提供的回调接口
     */
    interface OnGridViewChangeListener {
        void updateBackButton();
    }


    public static CourseFragment newInstance() {
        CourseFragment fragment = new CourseFragment();

        return fragment;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.main_fragment_course_title;
    }

    @Override
    protected void initDataBeforeView() {
        super.initDataBeforeView();

        activity = (MainActivity) getActivity();

        listDatas = new ArrayList();
        for (int i = 0; i < 100; i++) {
            listDatas.add(i);
        }

        //总的页数向上取整
        totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
    }

    @Override
    protected void initView() {
        initCourseTitle();
    }

    @Override
    protected void initDataAfterView() {
        activity.setLeftCommonVideo();
    }

    /**
     * 课程名的VeiwPager GridView PageNumberView等的初始化
     */
    private void initCourseTitle(){
        mCourseTitleGridViewList = new ArrayList<View>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(getActivity(), R.layout.item_viewpager_gridview_course_title, null);
            gridView.setPadding(100,50,100,0);
            gridView.setAdapter(new CourseTitleGVAdapter(getActivity(), listDatas, i, mPageSize));
            //添加item点击监听
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    if (position == 0){
                        initCourseContent();
                        showCourseContentLayout();
                        updateBackButton();
                    }
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            mCourseTitleGridViewList.add(gridView);
        }
        //设置ViewPager适配器
        mCourseTitlePagingViewPager.setAdapter(new CourseTitlePagingViewPagerAdapter(mCourseTitleGridViewList));

        mCourseTitlePageNumberView.setmTotalPages(totalPage);
        mCourseTitlePageNumberView.setViewPager(mCourseTitlePagingViewPager);
    }


    /**
     * 课程内容的VeiwPager GridView PageNumberView等的初始化
     */
    private void initCourseContent(){
        mCourseContentGridViewList = new ArrayList<View>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(getActivity(), R.layout.item_viewpager_gridview_course_title, null);
            gridView.setPadding(100,50,100,0);
            gridView.setAdapter(new CourseContentGVAdapter(getActivity(), listDatas, i, mPageSize));
            //添加item点击监听
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
//                                             Object obj = gridView.getItemAtPosition(position);
//                                                if(obj != null ){
//                                                      System.out.println(obj);
//                                                    }
                    if (position == 0){
                        VideoPlayerActivity.navigationToActivity((BaseAppCompatActivity) getActivity(),Constants.LOCAL_VIDEO,"");
                    }
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            mCourseContentGridViewList.add(gridView);
        }
        //设置ViewPager适配器
        mCourseContentPagingViewPager.setAdapter(new CourseTitlePagingViewPagerAdapter(mCourseContentGridViewList));

        mCourseContentPageNumberView.setmTotalPages(totalPage);
        mCourseContentPageNumberView.setViewPager(mCourseContentPagingViewPager);
    }

    @OnClick(R.id.ibBack)
    public void onBackOrHome(){
        if (!isHome){
                showCourseTitleLayout();
                updateBackButton();
        }else {
            getActivity().finish();
        }
    }

    /**
     * 更新回退按钮的状态
     */
    public void updateBackButton() {
        if (isHome) {
            mBack.setBackground(getResources().getDrawable(R.mipmap.common_back_mid));
        } else {
            mBack.setBackground(getResources().getDrawable(R.mipmap.common_home));
        }
        isHome = !isHome;
    }


    /**
     * 显示标题的布局
     */
    private void showCourseTitleLayout(){
        mCourseTitle.setVisibility(View.VISIBLE);
        mCourseContent.setVisibility(View.GONE);
    }

    /**
     * 显示内容的布局
     */
    private void showCourseContentLayout(){
        mCourseTitle.setVisibility(View.GONE);
        mCourseContent.setVisibility(View.VISIBLE);
    }

}
