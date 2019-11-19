package com.huohuo.mvp.model.bean;

import java.util.List;

public class MallList {

    /**
     * user_nickname : 张三
     * user_score : 100
     * total : 1
     * list : [{"id":1,"name":"苹果","productimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","integral":50,"status":"1","createtime":1573362762,"updatetime":1573362937,"deletetime":null,"status_text":"Status 1"}]
     */

    private String user_nickname;
    private int user_score;
    private int total;
    private List<ListBean> list;

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public int getUser_score() {
        return user_score;
    }

    public void setUser_score(int user_score) {
        this.user_score = user_score;
    }

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

    public static class ListBean {
        /**
         * id : 1
         * name : 苹果
         * productimage : /uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg
         * integral : 50
         * status : 1
         * createtime : 1573362762
         * updatetime : 1573362937
         * deletetime : null
         * status_text : Status 1
         */

        private int id;
        private String name;
        private String productimage;
        private int integral;
        private String status;
        private int createtime;
        private int updatetime;
        private Object deletetime;
        private String status_text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProductimage() {
            return productimage;
        }

        public void setProductimage(String productimage) {
            this.productimage = productimage;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getStatus_text() {
            return status_text;
        }

        public void setStatus_text(String status_text) {
            this.status_text = status_text;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", productimage='" + productimage + '\'' +
                    ", integral=" + integral +
                    ", status='" + status + '\'' +
                    ", createtime=" + createtime +
                    ", updatetime=" + updatetime +
                    ", deletetime=" + deletetime +
                    ", status_text='" + status_text + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MallList{" +
                "user_nickname='" + user_nickname + '\'' +
                ", user_score=" + user_score +
                ", total=" + total +
                ", list=" + list +
                '}';
    }
}
