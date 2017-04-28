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

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getCurrentListID() {
        return currentListID;
    }

    public void setCurrentListID(int currentListID) {
        this.currentListID = currentListID;
    }

    public List<VideoTypeInfo> getListContent() {
        return ListContent;
    }

    public void setListContent(List<VideoTypeInfo> listContent) {
        ListContent = listContent;
    }

    public List<VideoTypeDetailInfo> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<VideoTypeDetailInfo> pageContent) {
        this.pageContent = pageContent;
    }
}
