package com.chy.videotutorial.module.main.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import com.chy.videotutorial.MyWiew.NoSlideViewPager;
import com.chy.videotutorial.MyWiew.PageNumberView;
import com.chy.videotutorial.R;
import com.chy.videotutorial.Utils.Constants;
import com.chy.videotutorial.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

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

    private List<View> mOnlineVideoGridViewList;                     //GridView作为一个View对象添加到ViewPager集合中
    private int mPageSize = Constants.PAGE_SIZE_ONLINE;              //每页显示的最大的数量
    private int mTotalPage;                                          //总的页数
    private List listDatas;                                          //总的数据源

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
    protected void initDataBeforeView() {
        super.initDataBeforeView();
        listDatas = new ArrayList();
        for (int i = 0; i < 100; i++) {
            listDatas.add(i);
        }

        //总的页数向上取整
        mTotalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
    }

    @Override
    protected void initView() {
        initOnlineVideo();
    }

    @Override
    protected void initDataAfterView() {

    }


    /**
     * 课程名的VeiwPager GridView PageNumberView等的初始化
     */
    private void initOnlineVideo(){
        mOnlineVideoGridViewList = new ArrayList<View>();
        for (int i = 0; i < mTotalPage; i++) {
            //每个页面都是inflate出一个新实例
            final GridView gridView = (GridView) View.inflate(getActivity(), R.layout.item_viewpager_gridview_online_video, null);
            gridView.setPadding(100,50,100,0);
            gridView.setAdapter(new OnlineVideoGVAdapter(getActivity(), listDatas, i, mPageSize));
            //添加item点击监听
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            mOnlineVideoGridViewList.add(gridView);
        }
        //设置ViewPager适配器
        mViewPager.setAdapter(new OnlineVideoPagingViewPagerAdapter(mOnlineVideoGridViewList));

        mPageNumberView.setmTotalPages(mTotalPage);
        mPageNumberView.setViewPager(mViewPager);
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
