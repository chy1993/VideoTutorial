package com.chy.videotutorial.module.main.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by chenyu on 2017/4/19.
 * 视频种类控制viewpager的适配器
 */

public class ViewTypeViewPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public ViewTypeViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return CourseTitleFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
