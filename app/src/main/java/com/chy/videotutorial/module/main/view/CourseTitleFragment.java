package com.chy.videotutorial.module.main.view;


import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import com.chy.videotutorial.MyWiew.PageNumberView;
import com.chy.videotutorial.R;
import com.chy.videotutorial.base.fragment.BaseCallBackFrg2Aty;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CourseTitleFragment extends BaseCallBackFrg2Aty<CourseTitleFragment.OnGridViewChangeListener> {

    @BindView(R.id.ibBack)
    ImageButton mBack;

    @BindView(R.id.vpCourseTitlePaging)
    ViewPager mCourseTitlePagingViewPager;

    @BindView(R.id.pageNumberView)
    PageNumberView mPageNumberView;

    private boolean isHome = true;                  //判断按钮是home键还是back键   默认是home键

    private int mPageSize = 6;                        //每页显示的最大的数量
    private int totalPage;                           //总的页数
    private List listDatas;                           //总的数据源
    private List<View> viewPagerList;                 //GridView作为一个View对象添加到ViewPager集合中

    /**
     * 当GridView切换时为Activity提供的回调接口
     */
    interface OnGridViewChangeListener {
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
        listDatas = new ArrayList();
        for (int i = 0; i < 15; i++) {
            listDatas.add(i);
        }
    }

    @Override
    protected void initDataAfterView() {
        //总的页数向上取整
        totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<View>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(getActivity(), R.layout.item_viewpager_gridview_course_title, null);
            gridView.setAdapter(new CourseTitleGVAdapter(getActivity(), listDatas, i, mPageSize));
            //添加item点击监听
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
//                                             Object obj = gridView.getItemAtPosition(position);
//                                                if(obj != null ){
//                                                      System.out.println(obj);
//                                                    }
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        mCourseTitlePagingViewPager.setAdapter(new CourseTitlePagingViewPagerAdapter(viewPagerList));


        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        mCourseTitlePagingViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }
        });

        mPageNumberView.setmTotalPages(totalPage);
        mPageNumberView.setViewPager(mCourseTitlePagingViewPager);
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

}
