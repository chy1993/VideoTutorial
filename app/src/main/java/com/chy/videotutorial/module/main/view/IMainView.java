package com.chy.videotutorial.module.main.view;


import com.chy.videotutorial.base.mvp.IBaseView;
import com.chy.videotutorial.entities.VideoInfo;

/**
 */
public interface IMainView extends IBaseView {
    void setVideoInfoData(VideoInfo videoInfo);
}