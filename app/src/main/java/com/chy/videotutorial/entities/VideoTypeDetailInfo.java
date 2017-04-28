package com.chy.videotutorial.entities;

/**
 * Created by Administrator on 2017/4/28.
 * 具体某一类型的某一课的信息
 */

public class VideoTypeDetailInfo extends BaseEntity {
    int ClassID;                               //课程id
    String Title;                              //课程标题
    int ClassTypeID;                           //所属视频类型的类型id
    String ClassType;                          //所属视频类型的类型名
    String ImgPath;                            //显示图片的路径
    String Serices;                            //视频系列的系列名
    String Keynote;                            //主讲人名
    String Characteristic;                     //该课详细信息
    String VideoPath;                          //该课视频的播放路径

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getClassTypeID() {
        return ClassTypeID;
    }

    public void setClassTypeID(int classTypeID) {
        ClassTypeID = classTypeID;
    }

    public String getClassType() {
        return ClassType;
    }

    public void setClassType(String classType) {
        ClassType = classType;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String imgPath) {
        ImgPath = imgPath;
    }

    public String getSerices() {
        return Serices;
    }

    public void setSerices(String serices) {
        Serices = serices;
    }

    public String getKeynote() {
        return Keynote;
    }

    public void setKeynote(String keynote) {
        Keynote = keynote;
    }

    public String getCharacteristic() {
        return Characteristic;
    }

    public void setCharacteristic(String characteristic) {
        Characteristic = characteristic;
    }

    public String getVideoPath() {
        return VideoPath;
    }

    public void setVideoPath(String videoPath) {
        VideoPath = videoPath;
    }
}
