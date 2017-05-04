package com.chy.videotutorial.module.main.presenter;

import com.chy.videotutorial.base.mvp.BasePresenter;
import com.chy.videotutorial.entities.VideoInfo;
import com.chy.videotutorial.entities.VideoTypeDetailPlusInfo;
import com.chy.videotutorial.module.main.model.MainModel;
import com.chy.videotutorial.module.main.view.IMainView;

/**
 * Created by Administrator on 2017/4/28.
 */

public class MainPresenter extends BasePresenter<IMainView> implements MainModel.OnLoadVideoListInfoListener,MainModel.OnLoadVideoDetailListener {
    MainModel mModel;
    public MainPresenter(IMainView view) {
        super(view);
        mModel = new MainModel();
    }

    /**
     * 加载视频总信息
     * @param currentPageIndex
     * @param currentListID
     */
    public void loadVideoInfo(int currentPageIndex,int currentListID){
            mView.onShowLoadingView("");
            mModel.requestVideoInfo(currentPageIndex, currentListID,this);
    }

    @Override
    public void onLoadVideoListInfoSuccess(VideoInfo videoInfo) {
        mView.setVideoInfoData(videoInfo);
        mView.onHideLoadingView();
    }

    @Override
    public void onLoadVideoListInfoFailure(String msg, Exception e) {
        mView.onHideLoadingView();
    }




    /**
     * 加载某一视频的详细信息
     */
    public void loadVideoDetailInfo(int classID){
        mView.onShowLoadingView("");
        mModel.requestVideoContentInfo(classID,this);
    }


    @Override
    public void onLoadVideoDetailSuccess(VideoTypeDetailPlusInfo videoTypeDetailPlusInfo) {
        mView.setVideoDetailData(videoTypeDetailPlusInfo);
        mView.onHideLoadingView();
    }

    @Override
    public void onLoadVideoDetailFailure(String msg, Exception e) {
        mView.onHideLoadingView();
    }
}
