package com.chy.videotutorial.module.main.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by chenyu on 2017/4/19.
 * 视频种类控制viewpager的适配器
 */

public class ViewTypeViewPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    Fragment currentFragment;

    public ViewTypeViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0) {
            return CourseFragment.newInstance();
        }else if (position==3) {
            return OnlineFragment.newInstance();
        } else {
            return CourseFragment.newInstance();
        }
//        return CourseFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 4;
    }


    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (position != 3){
            currentFragment = (CourseFragment) object;
        }else {
            currentFragment = (OnlineFragment)object;
        }

        super.setPrimaryItem(container,position,object);
    }
}
