package com.yjb.mvp.model.bean;

import java.io.Serializable;
import java.util.List;

public class YjTeam implements Serializable {

    /**
     * total : 4
     * list : [{"id":1,"teamname":"小二","slogan":"表里如一","yjSet":[{"nickname":"小张","photoimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":""},{"nickname":"小李","photoimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":""}]},{"id":2,"teamname":"小三","slogan":"小三","yjSet":[{"nickname":"小张","photoimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":""},{"nickname":"小李","photoimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":""}]},{"id":3,"teamname":"小四","slogan":"小四","yjSet":[{"nickname":"小张","photoimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":""},{"nickname":"小李","photoimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":""}]},{"id":4,"teamname":"小五","slogan":"小五","yjSet":[{"nickname":"小张","photoimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":""},{"nickname":"小李","photoimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":""}]}]
     */

    private int total;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable {
        /**
         * id : 1
         * teamname : 小二
         * slogan : 表里如一
         * yjSet : [{"nickname":"小张","photoimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":""},{"nickname":"小李","photoimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":""}]
         */

        private int id;
        private String teamname;
        private String slogan;
        private String content;
        private List<YjSetBean> yjSet;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTeamname() {
            return teamname;
        }

        public void setTeamname(String teamname) {
            this.teamname = teamname;
        }

        public String getSlogan() {
            return slogan;
        }

        public void setSlogan(String slogan) {
            this.slogan = slogan;
        }

        public List<YjSetBean> getYjSet() {
            return yjSet;
        }

        public void setYjSet(List<YjSetBean> yjSet) {
            this.yjSet = yjSet;
        }

        public static class YjSetBean implements Serializable {
            /**
             * nickname : 小张
             * photoimage : /uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg
             * status_text :
             */

            private String nickname;
            private String photoimage;
            private String status_text;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
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
                return "YjSetBean{" +
                        "nickname='" + nickname + '\'' +
                        ", photoimage='" + photoimage + '\'' +
                        ", status_text='" + status_text + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "id=" + id +
                    ", teamname='" + teamname + '\'' +
                    ", slogan='" + slogan + '\'' +
                    ", content='" + content + '\'' +
                    ", yjSet=" + yjSet +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "YjTeam{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
