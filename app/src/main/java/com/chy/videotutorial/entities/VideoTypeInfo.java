package com.chy.videotutorial.entities;

/**
 * Created by chenyu on 2017/4/28.
 */

public class VideoTypeInfo extends BaseEntity {
    int ID;                                             //视频某一类型的id
    String TypeName;                                    //视频某一类型的名字
    int sort;
    int isfree;
    int price;
    int auditing;
    String cwkuserid;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getIsfree() {
        return isfree;
    }

    public void setIsfree(int isfree) {
        this.isfree = isfree;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAuditing() {
        return auditing;
    }

    public void setAuditing(int auditing) {
        this.auditing = auditing;
    }

    public String getCwkuserid() {
        return cwkuserid;
    }

    public void setCwkuserid(String cwkuserid) {
        this.cwkuserid = cwkuserid;
    }
}
