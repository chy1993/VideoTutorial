package com.chy.videotutorial.module.main.view;


import android.content.Intent;
import android.view.View;
import android.widget.GridView;

import com.chy.videotutorial.R;
import com.chy.videotutorial.base.fragment.BaseFragment;

import butterknife.BindView;

public class CourseTitleFragment extends BaseFragment {
    @BindView(R.id.gvCourseTitle)
    GridView mCourseTitleGridView;

    private CourseTitleGridViewAdapter mAdapter;

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
        mAdapter = new CourseTitleGridViewAdapter(getActivity());
        mCourseTitleGridView.setAdapter(mAdapter);

    }

    @Override
    protected void initDataAfterView() {

    }



}
