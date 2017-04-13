package com.chy.videotutorial.base.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;


/**
 * Fragment基类：1、加入了友盟的页面统计功能；
 *
 * Created by 丁飞 on 2016/1/7.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 获取界面布局资源ID
     *
     * @return
     */
    protected abstract int getLayoutResID();

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 视图初始化后，填充初始化数据
     */
    protected abstract void initDataAfterView();

    /**
     * 视图未初始化之前，初始化的数据
     */
    protected void initDataBeforeView() {}

    protected void showToast(String message ) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(getLayoutResID(), container, false);
        ButterKnife.bind(this, mainView);

        initDataBeforeView();
        initView();
        initDataAfterView();
        return mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.unbind(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}