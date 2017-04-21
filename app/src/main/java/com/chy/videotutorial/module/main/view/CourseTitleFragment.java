package com.chy.videotutorial.module.main.view;


import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import com.chy.videotutorial.R;
import com.chy.videotutorial.base.activity.BaseAppCompatActivity;
import com.chy.videotutorial.base.fragment.BaseCallBackFrg2Aty;
import com.chy.videotutorial.module.videoplayer.VideoPlayerActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseTitleFragment extends BaseCallBackFrg2Aty<CourseTitleFragment.OnGridViewChangeListener> {
    @BindView(R.id.gvCourseTitle)
    GridView mCourseTitleGridView;

    @BindView(R.id.gvCourseContent)
    GridView mCourseContentGridView;

    @BindView(R.id.ibBack)
    ImageButton mBack;

    private CourseTitleGridViewAdapter mCourseTitleGridViewAdapter;

    private CourseContentGridViewAdapter mCourseContentGridViewAdapter;

    private boolean  isHome  = true;                  //判断按钮是home键还是back键   默认是home键

    /**
     * 当GridView切换时为Activity提供的回调接口
     */
    interface OnGridViewChangeListener{
        void updateBackButton();
    }


    public static CourseTitleFragment newInstance() {
        CourseTitleFragment fragment = new CourseTitleFragment();

        return fragment;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.main_fragment_course_title;
    }

    @Override
    protected void initView() {
        initCourseTitleGridView();
    }

    @Override
    protected void initDataAfterView() {

    }


    /**
     * 初始化课程标题的GirdView
     */
    private void initCourseTitleGridView(){
        mCourseTitleGridViewAdapter = new CourseTitleGridViewAdapter(getActivity());
        mCourseTitleGridView.setAdapter(mCourseTitleGridViewAdapter);
        mCourseTitleGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 ){
                    showCourseContentGridView();
                    initCourseContentGridView();

                    updateBackButton();
                }
            }
        });
    }

    /**
     * 初始化课程内容的GirdView
     */
    private void initCourseContentGridView(){
        mCourseContentGridViewAdapter = new CourseContentGridViewAdapter(getActivity());
        mCourseContentGridView.setAdapter(mCourseContentGridViewAdapter);
        mCourseContentGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 ){
                    VideoPlayerActivity.navigationToActivity((BaseAppCompatActivity) getActivity());
                }
            }
        });
    }

    @OnClick(R.id.ibBack)
    public void onBackOrHome(){
        if (!isHome){
            showCourseTitleGridView();
            updateBackButton();
        }else {
            getActivity().finish();
        }
    }


    /**
     * 显示课程具体内容的gridview
     */
    public void showCourseContentGridView(){
        mCourseTitleGridView.setVisibility(View.GONE);
        mCourseContentGridView.setVisibility(View.VISIBLE);
    }

    /**
     * 显示课程标题的gridview
     */
    public void showCourseTitleGridView(){
        mCourseTitleGridView.setVisibility(View.VISIBLE);
        mCourseContentGridView.setVisibility(View.GONE);
    }

    /**
     * 更新回退按钮的状态
     */
    public void updateBackButton() {
        if (isHome) {
            mBack.setBackground(getResources().getDrawable(R.mipmap.common_back_mid));
        }else {
            mBack.setBackground(getResources().getDrawable(R.mipmap.common_home));
        }
        isHome = !isHome;
    }

}
