package com.yjb.mvp.model.bean;

public class UploadBean {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UploadBean{" +
                "url='" + url + '\'' +
                '}';
    }
}
