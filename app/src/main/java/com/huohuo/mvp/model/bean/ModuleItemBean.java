package com.huohuo.mvp.model.bean;

/**
 * Created by kennysun on 2019/9/2.
 */

public class ModuleItemBean {

    private String name;

    private String photoUrl;

    private Integer index;

    private Integer res;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "ModuleItemBean{" +
                "name='" + name + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", index=" + index +
                ", res=" + res +
                '}';
    }
}
