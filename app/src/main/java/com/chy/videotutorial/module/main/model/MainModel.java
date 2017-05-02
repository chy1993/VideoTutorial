package com.chy.videotutorial.module.main.model;

import com.chy.videotutorial.Utils.ApiResponse;
import com.chy.videotutorial.Utils.JsonUtils;
import com.chy.videotutorial.Utils.LogUtils;
import com.chy.videotutorial.Utils.OkHttpUtils;
import com.chy.videotutorial.Utils.Urls;
import com.chy.videotutorial.entities.VideoInfo;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频播放主界面的数据处理
 */
public class MainModel {
    public interface OnLoadVideoListInfoListener{
        void onLoadVideoListInfoSuccess(VideoInfo videoInfo);
        void onLoadVideoListInfoFailure(String msg,Exception e);
    }

    public void requestVideoInfo(int currentPageIndex, int currentListID, final OnLoadVideoListInfoListener videoListInfoListener) {
        // 接口数据
        OkHttpUtils.ResultCallback<String> videoInfoCallBack = new OkHttpUtils.ResultCallback<String>() {

            @Override
            public void onSuccess(String response) {
                LogUtils.getInstance().i("视频信息结果：" + response);
                try
                {
                    VideoInfo beans = JsonUtils.deserialize(response,new TypeToken<VideoInfo>(){}.getType());
                    videoListInfoListener.onLoadVideoListInfoSuccess(beans);

                } catch (Exception e) {
                     videoListInfoListener.onLoadVideoListInfoFailure("服务器连接失败，请检查网络或稍后重试",e);
                    LogUtils.getInstance().e(e);
                }
            }

            @Override
            public void onFailure(Exception e) {
                videoListInfoListener.onLoadVideoListInfoFailure("服务器连接失败，请检查网络或稍后重试",e);
            }
        };


        List<OkHttpUtils.Param> params = new ArrayList<>();
        params.add(new OkHttpUtils.Param("PageId",String.valueOf(currentPageIndex)));
//        params.add(new OkHttpUtils.Param("VersionNo",""));
//        params.add(new OkHttpUtils.Param("VersionPlatform", ""));
//        params.add(new OkHttpUtils.Param("Uid", ""));
        params.add(new OkHttpUtils.Param("ListId", String.valueOf(currentListID)));
        OkHttpUtils.post(Urls.Online_VIDEO_INFO, videoInfoCallBack, params);

    }
}