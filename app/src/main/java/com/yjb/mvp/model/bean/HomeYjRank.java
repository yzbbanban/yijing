package com.yjb.mvp.model.bean;

public class HomeYjRank {
    /**
     * nickname : 小张
     * score : 100
     * photoimage : /uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg
     * status_text :
     */

    private String nickname;
    private int score;
    private String photoimage;
    private String status_text;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPhotoimage() {
        return photoimage;
    }

    public void setPhotoimage(String photoimage) {
        this.photoimage = photoimage;
    }

    public String getStatus_text() {
        return status_text;
    }

    public void setStatus_text(String status_text) {
        this.status_text = status_text;
    }

    @Override
    public String toString() {
        return "HomeYjRank{" +
                "nickname='" + nickname + '\'' +
                ", score=" + score +
                ", photoimage='" + photoimage + '\'' +
                ", status_text='" + status_text + '\'' +
                '}';
    }
}
