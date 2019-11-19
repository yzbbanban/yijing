package com.huohuo.mvp.model.bean;

import java.util.List;

public class ExchangeList {

    /**
     * user_nickname : 张三
     * user_score : 100
     * total : 10
     * list : [{"id":1,"mall_id":1,"user_id":11,"status":"2","remark":"1","createtime":1573363930,"updatetime":1573371080,"deletetime":null,"gettime":null,"status_text":"已领取","mallid_text":{"id":1,"name":"苹果","productimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":"Status 1","integral":""},"createtime_text":"2019-11-10 13:32:10"},{"id":2,"mall_id":1,"user_id":11,"status":"1","remark":"","createtime":1573363931,"updatetime":1573371080,"deletetime":null,"gettime":null,"status_text":"未领取","mallid_text":{"id":1,"name":"苹果","productimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":"Status 1","integral":""},"createtime_text":"2019-11-10 15:16:56"},{"id":3,"mall_id":1,"user_id":11,"status":"1","remark":"","createtime":1573363932,"updatetime":1573371080,"deletetime":null,"gettime":null,"status_text":"未领取","mallid_text":{"id":1,"name":"苹果","productimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":"Status 1","integral":""},"createtime_text":"2019-11-10 15:19:42"},{"id":4,"mall_id":1,"user_id":11,"status":"1","remark":"","createtime":1573363933,"updatetime":1573371080,"deletetime":null,"gettime":null,"status_text":"未领取","mallid_text":{"id":1,"name":"苹果","productimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":"Status 1","integral":""},"createtime_text":"2019-11-10 15:19:54"},{"id":5,"mall_id":1,"user_id":11,"status":"1","remark":"","createtime":1573363934,"updatetime":1573371080,"deletetime":null,"gettime":null,"status_text":"未领取","mallid_text":{"id":1,"name":"苹果","productimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":"Status 1","integral":""},"createtime_text":"2019-11-10 15:20:59"},{"id":6,"mall_id":1,"user_id":11,"status":"1","remark":"","createtime":1573363935,"updatetime":1573371080,"deletetime":null,"gettime":null,"status_text":"未领取","mallid_text":{"id":1,"name":"苹果","productimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":"Status 1","integral":""},"createtime_text":"2019-11-10 15:21:49"},{"id":7,"mall_id":1,"user_id":11,"status":"1","remark":"","createtime":1573363936,"updatetime":1573371080,"deletetime":null,"gettime":null,"status_text":"未领取","mallid_text":{"id":1,"name":"苹果","productimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":"Status 1","integral":""},"createtime_text":"2019-11-10 15:22:01"},{"id":8,"mall_id":1,"user_id":11,"status":"1","remark":"","createtime":1573363937,"updatetime":1573371080,"deletetime":null,"gettime":null,"status_text":"未领取","mallid_text":{"id":1,"name":"苹果","productimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":"Status 1","integral":""},"createtime_text":"2019-11-10 15:23:29"},{"id":9,"mall_id":1,"user_id":11,"status":"1","remark":"","createtime":1573363938,"updatetime":1573371080,"deletetime":null,"gettime":null,"status_text":"未领取","mallid_text":{"id":1,"name":"苹果","productimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":"Status 1","integral":""},"createtime_text":"2019-11-10 15:29:37"},{"id":10,"mall_id":1,"user_id":11,"status":"1","remark":"","createtime":1573363939,"updatetime":1573371080,"deletetime":null,"gettime":null,"status_text":"未领取","mallid_text":{"id":1,"name":"苹果","productimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":"Status 1","integral":""},"createtime_text":"2019-11-10 15:29:46"}]
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
         * mall_id : 1
         * user_id : 11
         * status : 2
         * remark : 1
         * createtime : 1573363930
         * updatetime : 1573371080
         * deletetime : null
         * gettime : null
         * status_text : 已领取
         * mallid_text : {"id":1,"name":"苹果","productimage":"/uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg","status_text":"Status 1","integral":""}
         * createtime_text : 2019-11-10 13:32:10
         */

        private int id;
        private int mall_id;
        private int user_id;
        private String status;
        private String remark;
        private int createtime;
        private int updatetime;
        private Object deletetime;
        private Object gettime;
        private String status_text;
        private MallidTextBean mallid_text;
        private String createtime_text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMall_id() {
            return mall_id;
        }

        public void setMall_id(int mall_id) {
            this.mall_id = mall_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public Object getGettime() {
            return gettime;
        }

        public void setGettime(Object gettime) {
            this.gettime = gettime;
        }

        public String getStatus_text() {
            return status_text;
        }

        public void setStatus_text(String status_text) {
            this.status_text = status_text;
        }

        public MallidTextBean getMallid_text() {
            return mallid_text;
        }

        public void setMallid_text(MallidTextBean mallid_text) {
            this.mallid_text = mallid_text;
        }

        public String getCreatetime_text() {
            return createtime_text;
        }

        public void setCreatetime_text(String createtime_text) {
            this.createtime_text = createtime_text;
        }

        public static class MallidTextBean {
            /**
             * id : 1
             * name : 苹果
             * productimage : /uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg
             * status_text : Status 1
             * integral :
             */

            private int id;
            private String name;
            private String productimage;
            private String status_text;
            private String integral;

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

            public String getStatus_text() {
                return status_text;
            }

            public void setStatus_text(String status_text) {
                this.status_text = status_text;
            }

            public String getIntegral() {
                return integral;
            }

            public void setIntegral(String integral) {
                this.integral = integral;
            }
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "id=" + id +
                    ", mall_id=" + mall_id +
                    ", user_id=" + user_id +
                    ", status='" + status + '\'' +
                    ", remark='" + remark + '\'' +
                    ", createtime=" + createtime +
                    ", updatetime=" + updatetime +
                    ", deletetime=" + deletetime +
                    ", gettime=" + gettime +
                    ", status_text='" + status_text + '\'' +
                    ", mallid_text=" + mallid_text +
                    ", createtime_text='" + createtime_text + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ExchangeList{" +
                "user_nickname='" + user_nickname + '\'' +
                ", user_score=" + user_score +
                ", total=" + total +
                ", list=" + list +
                '}';
    }
}
