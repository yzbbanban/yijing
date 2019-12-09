package com.yjb.mvp.model.bean;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    /**
     * userinfo : {"id":18,"username":"18012020804","nickname":"18012020804","mobile":"18012020804","avatar":"data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZlcnNpb249IjEuMSIgaGVpZ2h0PSIxMDAiIHdpZHRoPSIxMDAiPjxyZWN0IGZpbGw9InJnYigyMTIsMTYwLDIyOSkiIHg9IjAiIHk9IjAiIHdpZHRoPSIxMDAiIGhlaWdodD0iMTAwIj48L3JlY3Q+PHRleHQgeD0iNTAiIHk9IjUwIiBmb250LXNpemU9IjUwIiB0ZXh0LWNvcHk9ImZhc3QiIGZpbGw9IiNmZmZmZmYiIHRleHQtYW5jaG9yPSJtaWRkbGUiIHRleHQtcmlnaHRzPSJhZG1pbiIgYWxpZ25tZW50LWJhc2VsaW5lPSJjZW50cmFsIj4xPC90ZXh0Pjwvc3ZnPg==","score":10,"token":"4d8eb237-cf74-45bb-b271-bd00aa8731f2","user_id":18,"createtime":1574350353,"expiretime":1576942353,"expires_in":2592000}
     * yjinfo : null
     */

    private UserinfoBean userinfo;
    private Yjinfo yjinfo;

    public Yjinfo getYjinfo() {
        return yjinfo;
    }

    public void setYjinfo(Yjinfo yjinfo) {
        this.yjinfo = yjinfo;
    }

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class Yjinfo {



        private int teammgt_id;
        /**
         * id : 12
         * user_id : 23
         * vision : 好纠结就看看
         * photoimage : http://yhyj.chinayhdqzj.com/uploads/20191204/69ed3eccd9106c82516d3c7c687e4918.jpg
         * homeaddress : 安徽省安庆市枞阳县
         * politically : 不能解决
         * identifier : 2929399393
         * job : 黄金季节
         * createtime : 1575471908
         * updatetime : 1575471908
         * deletetime : null
         * status : 0
         * teammgt_id : null
         * status_text : Status 0
         */

        private int id;
        private int user_id;
        private String vision;
        private String photoimage;
        private String homeaddress;
        private String politically;
        private String identifier;
        private String job;
        private int createtime;
        private int updatetime;
        private Object deletetime;
        private String status;
        @SerializedName("teammgt_id")
        private Object teammgt_idX;
        private String status_text;

        public int getTeammgt_id() {
            return teammgt_id;
        }

        public void setTeammgt_id(int teammgt_id) {
            this.teammgt_id = teammgt_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getVision() {
            return vision;
        }

        public void setVision(String vision) {
            this.vision = vision;
        }

        public String getPhotoimage() {
            return photoimage;
        }

        public void setPhotoimage(String photoimage) {
            this.photoimage = photoimage;
        }

        public String getHomeaddress() {
            return homeaddress;
        }

        public void setHomeaddress(String homeaddress) {
            this.homeaddress = homeaddress;
        }

        public String getPolitically() {
            return politically;
        }

        public void setPolitically(String politically) {
            this.politically = politically;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(int updatetime) {
            this.updatetime = updatetime;
        }

        public Object getDeletetime() {
            return deletetime;
        }

        public void setDeletetime(Object deletetime) {
            this.deletetime = deletetime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getTeammgt_idX() {
            return teammgt_idX;
        }

        public void setTeammgt_idX(Object teammgt_idX) {
            this.teammgt_idX = teammgt_idX;
        }

        public String getStatus_text() {
            return status_text;
        }

        public void setStatus_text(String status_text) {
            this.status_text = status_text;
        }

        @Override
        public String toString() {
            return "Yjinfo{" +
                    "teammgt_id=" + teammgt_id +
                    ", id=" + id +
                    ", user_id=" + user_id +
                    ", vision='" + vision + '\'' +
                    ", photoimage='" + photoimage + '\'' +
                    ", homeaddress='" + homeaddress + '\'' +
                    ", politically='" + politically + '\'' +
                    ", identifier='" + identifier + '\'' +
                    ", job='" + job + '\'' +
                    ", createtime=" + createtime +
                    ", updatetime=" + updatetime +
                    ", deletetime=" + deletetime +
                    ", status='" + status + '\'' +
                    ", teammgt_idX=" + teammgt_idX +
                    ", status_text='" + status_text + '\'' +
                    '}';
        }
    }

    public static class UserinfoBean {
        /**
         * id : 18
         * username : 18012020804
         * nickname : 18012020804
         * mobile : 18012020804
         * avatar : data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZlcnNpb249IjEuMSIgaGVpZ2h0PSIxMDAiIHdpZHRoPSIxMDAiPjxyZWN0IGZpbGw9InJnYigyMTIsMTYwLDIyOSkiIHg9IjAiIHk9IjAiIHdpZHRoPSIxMDAiIGhlaWdodD0iMTAwIj48L3JlY3Q+PHRleHQgeD0iNTAiIHk9IjUwIiBmb250LXNpemU9IjUwIiB0ZXh0LWNvcHk9ImZhc3QiIGZpbGw9IiNmZmZmZmYiIHRleHQtYW5jaG9yPSJtaWRkbGUiIHRleHQtcmlnaHRzPSJhZG1pbiIgYWxpZ25tZW50LWJhc2VsaW5lPSJjZW50cmFsIj4xPC90ZXh0Pjwvc3ZnPg==
         * score : 10
         * token : 4d8eb237-cf74-45bb-b271-bd00aa8731f2
         * user_id : 18
         * createtime : 1574350353
         * expiretime : 1576942353
         * expires_in : 2592000
         */

        private int id;
        private String username;
        private String nickname;
        private String mobile;
        private String avatar;
        private int score;
        private String token;
        private int user_id;
        private int createtime;
        private int expiretime;
        private int expires_in;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getExpiretime() {
            return expiretime;
        }

        public void setExpiretime(int expiretime) {
            this.expiretime = expiretime;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        @Override
        public String toString() {
            return "UserinfoBean{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", score=" + score +
                    ", token='" + token + '\'' +
                    ", user_id=" + user_id +
                    ", createtime=" + createtime +
                    ", expiretime=" + expiretime +
                    ", expires_in=" + expires_in +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userinfo=" + userinfo +
                ", yjinfo=" + yjinfo +
                '}';
    }
}
