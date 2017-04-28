package com.chy.videotutorial.module.main.presenter;

import android.content.Context;

import com.chy.videotutorial.Utils.AppUtils;
import com.chy.videotutorial.base.mvp.BasePresenter;
import com.chy.videotutorial.entities.VideoInfo;
import com.chy.videotutorial.module.main.model.MainModel;
import com.chy.videotutorial.module.main.view.IMainView;

/**
 * Created by Administrator on 2017/4/28.
 */

public class MainPresenter extends BasePresenter<IMainView> implements MainModel.OnLoadVideoListInfoListener {
    MainModel mModel;
    public MainPresenter(IMainView view) {
        super(view);
        mModel = new MainModel();
    }


    public void loadVideoInfo(int currentPageIndex,int currentListID){
//            mView.onShowLoadingView("");
            mModel.requestVideoInfo(currentPageIndex, currentListID,this);
    }

    @Override
    public void onLoadVideoListInfoSuccess(VideoInfo videoInfo) {
        mView.setVideoInfoData(videoInfo);
    }

    @Override
    public void onLoadVideoListInfoFailure(String msg, Exception e) {

    }
}
