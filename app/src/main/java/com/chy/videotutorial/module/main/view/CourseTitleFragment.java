package com.chy.videotutorial.module.main.view;


import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.chy.videotutorial.R;
import com.chy.videotutorial.base.fragment.BaseFragment;

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
                    showToast("我被点击了");
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
                    showToast("1");
                }
            }
        });
    }


    private void showCourseContentGridView(){
        mCourseTitleGridView.setVisibility(View.GONE);
        mCourseContentGridView.setVisibility(View.VISIBLE);
    }


    private void showCourseTitleGridView(){
        mCourseTitleGridView.setVisibility(View.VISIBLE);
        mCourseContentGridView.setVisibility(View.GONE);
    }


}
