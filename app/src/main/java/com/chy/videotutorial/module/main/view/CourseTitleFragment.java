package com.chy.videotutorial.module.main.view;


import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.chy.videotutorial.R;
import com.chy.videotutorial.base.activity.BaseAppCompatActivity;
import com.chy.videotutorial.base.fragment.BaseFragment;
import com.chy.videotutorial.module.videoplayer.VideoPlayerActivity;

import butterknife.BindView;

public class CourseTitleFragment extends BaseFragment {
    @BindView(R.id.gvCourseTitle)
    GridView mCourseTitleGridView;

    @BindView(R.id.gvCourseContent)
    GridView mCourseContentGridView;

    private CourseTitleGridViewAdapter mCourseTitleGridViewAdapter;

    private CourseContentGridViewAdapter mCourseContentGridViewAdapter;

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

    private void initCourseTitleGridView(){
        mCourseTitleGridViewAdapter = new CourseTitleGridViewAdapter(getActivity());
        mCourseTitleGridView.setAdapter(mCourseTitleGridViewAdapter);
        mCourseTitleGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 ){
                    showCourseContentGridView();
                    initCourseContentGridView();

                }
            }
        });
    }


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


    /**
     * 显示课程具体内容的gridview
     */
    private void showCourseContentGridView(){
        mCourseTitleGridView.setVisibility(View.GONE);
        mCourseContentGridView.setVisibility(View.VISIBLE);
    }

    /**
     * 显示课程标题的gridview
     */
    private void showCourseTitleGridView(){
        mCourseTitleGridView.setVisibility(View.VISIBLE);
        mCourseContentGridView.setVisibility(View.GONE);
    }


}
