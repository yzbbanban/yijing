package com.yjb.mvp.model.bean;

public class UserInfo {


    /**
     * userinfo : {"id":10,"username":"15558103801","nickname":"张三","mobile":"15558103801","avatar":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","score":100,"token":"0f33cbcd-e03a-453e-9082-5c32d2f9f4a3","user_id":10,"createtime":1574048582,"expiretime":1576640582,"expires_in":2592000}
     */

    private UserinfoBean userinfo;

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoBean {
        /**
         * id : 10
         * username : 15558103801
         * nickname : 张三
         * mobile : 15558103801
         * avatar : /uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg
         * score : 100
         * token : 0f33cbcd-e03a-453e-9082-5c32d2f9f4a3
         * user_id : 10
         * createtime : 1574048582
         * expiretime : 1576640582
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
                '}';
    }
}
