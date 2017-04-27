package com.chy.videotutorial.module.main.view;

import android.view.View;
import android.widget.ImageButton;

import com.chy.videotutorial.MyWiew.NoSlideViewPager;
import com.chy.videotutorial.MyWiew.PageNumberView;
import com.chy.videotutorial.R;
import com.chy.videotutorial.base.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class OnlineTitleFragment extends BaseFragment {

    @BindView(R.id.vpOnlineVideoPaging)
    NoSlideViewPager mViewPager;

    @BindView(R.id.onLineVideoPageNumberView)
    PageNumberView mPageNumberView;

    @BindView(R.id.ibBack)
    ImageButton mBackOrHome;

    @BindView(R.id.ibRefrsh)
    ImageButton mRefrsh;

    public static OnlineTitleFragment newInstance() {
        OnlineTitleFragment fragment = new OnlineTitleFragment();
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
    protected void initView() {

    }

    @Override
    protected void initDataAfterView() {

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
}
