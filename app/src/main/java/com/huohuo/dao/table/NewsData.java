package com.huohuo.dao.table;

import java.io.Serializable;

public class NewsData implements Serializable {

    private Integer id;

    private String title;

    private Integer readCount;

    private String time;

    private String url;

    public NewsData() {
    }

    public NewsData(Integer id, String title, Integer readCount, String time, String url) {
        this.id = id;
        this.title = title;
        this.readCount = readCount;
        this.time = time;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", readCount=" + readCount +
                ", time='" + time + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
