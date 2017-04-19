package com.chy.videotutorial.module.main.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chy.videotutorial.R;

public class CourseTitleFragment extends Fragment {

    public static CourseTitleFragment newInstance() {
        CourseTitleFragment fragment = new CourseTitleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_course_title, container, false);
    }


}
