package com.yjb.mvp.model.bean;

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

        public int getTeammgt_id() {
            return teammgt_id;
        }

        public void setTeammgt_id(int teammgt_id) {
            this.teammgt_id = teammgt_id;
        }

        @Override
        public String toString() {
            return "Yjinfo{" +
                    "teammgt_id=" + teammgt_id +
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
