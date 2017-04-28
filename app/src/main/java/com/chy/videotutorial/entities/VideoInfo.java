package com.chy.videotutorial.entities;

import java.util.List;

/**
 * Created by Administrator on 2017/4/28.
 * 在线视频的总实体类
 */

public class VideoInfo extends BaseEntity {
    int currentPageIndex;                                 //当前页
    int totalPageCount;                                   //总页数
    int currentListID;                                    //当前视频类型id
    List<VideoTypeInfo> ListContent;                      //所有视频类型的集合
    List<VideoTypeDetailInfo>  pageContent;               //当前页所有视频信息的集合
}
