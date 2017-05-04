package com.chy.videotutorial.entities;

/**
 * Created by Administrator on 2017/4/28.
 * 具体某一类型的某一课的信息
 */

public class VideoContentInfo extends BaseEntity {
    int ID;                               //课程id
    int auditing;
    String Characteristic;
    int ClassType;
    String cwkuserid;
    String Keynote;
    String Serices;
    int sort;
    String Title;
    String AddTime;
    String ImgPath;
    String VideoPath;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAuditing() {
        return auditing;
    }

    public void setAuditing(int auditing) {
        this.auditing = auditing;
    }

    public String getCharacteristic() {
        return Characteristic;
    }

    public void setCharacteristic(String characteristic) {
        Characteristic = characteristic;
    }

    public int getClassType() {
        return ClassType;
    }

    public void setClassType(int classType) {
        ClassType = classType;
    }

    public String getCwkuserid() {
        return cwkuserid;
    }

    public void setCwkuserid(String cwkuserid) {
        this.cwkuserid = cwkuserid;
    }

    public String getKeynote() {
        return Keynote;
    }

    public void setKeynote(String keynote) {
        Keynote = keynote;
    }

    public String getSerices() {
        return Serices;
    }

    public void setSerices(String serices) {
        Serices = serices;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAddTime() {
        return AddTime;
    }

    public void setAddTime(String addTime) {
        AddTime = addTime;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String imgPath) {
        ImgPath = imgPath;
    }

    public String getVideoPath() {
        return VideoPath;
    }

    public void setVideoPath(String videoPath) {
        VideoPath = videoPath;
    }
}
