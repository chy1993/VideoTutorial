package com.chy.videotutorial.module.main.view;


import com.chy.videotutorial.base.mvp.IBaseView;
import com.chy.videotutorial.entities.VideoInfo;
import com.chy.videotutorial.entities.VideoTypeDetailPlusInfo;

/**
 */
public interface IMainView extends IBaseView {
    void setVideoInfoData(VideoInfo videoInfo);
    void setVideoDetailData(VideoTypeDetailPlusInfo videoTypeDetailPlusInfo);
}