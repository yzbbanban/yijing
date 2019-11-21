package com.yjb.mvp.model.bean;

/**
 * Created by kennysun on 2019/8/7.
 */

public class SplashBean {
    private String imageLink;//图片地址
    private String imageOpen;
    private String imageUrl;//跳转链接地址
    private String imageTime;//单位秒

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageOpen() {
        return imageOpen;
    }

    public void setImageOpen(String imageOpen) {
        this.imageOpen = imageOpen;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTime() {
        return imageTime;
    }

    public void setImageTime(String imageTime) {
        this.imageTime = imageTime;
    }
}
