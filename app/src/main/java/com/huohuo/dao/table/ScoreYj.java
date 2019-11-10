package com.huohuo.dao.table;

public class ScoreYj {

    private Integer index;

    private String photoUrl;

    private String name;

    private String remark;

    private String time;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ScoreYj{" +
                "index=" + index +
                ", photoUrl='" + photoUrl + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
