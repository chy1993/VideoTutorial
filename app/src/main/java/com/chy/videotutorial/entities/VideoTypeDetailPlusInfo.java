package com.chy.videotutorial.entities;

import java.util.List;

/**
 * Created by Administrator on 2017/4/28.
 * 具体某一类型的某一课的详细信息      比VideoTypeDetailInfo详细
 */

public class VideoTypeDetailPlusInfo extends BaseEntity {
    int ClassID;                               //课程id
    List<VideoContentInfo> Content;                  //课程详细内容

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public List<VideoContentInfo> getContent() {
        return Content;
    }

    public void setContent(List<VideoContentInfo> content) {
        Content = content;
    }
}
