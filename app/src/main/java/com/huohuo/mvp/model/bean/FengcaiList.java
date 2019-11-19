package com.huohuo.mvp.model.bean;

import java.util.List;

public class FengcaiList {

    /**
     * total : 2
     * list : [{"id":1,"title":"标题","createtime":1573536040,"updatetime":1573536040,"deletetime":null,"fengcai_set":null},{"id":2,"title":"测试","createtime":1573536041,"updatetime":1573536041,"deletetime":null,"fengcai_set":[{"phototitle":"1","photoimages":"/uploads/20191113/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg"},{"phototitle":"3","photoimages":"/uploads/20191112/be2f99a8d22ed2dcc8ebcffdf4922a89.jpg"},{"phototitle":"","photoimages":"/uploads/20191112/dd33fe0f8d711fe1f41e2ee01b194bd8.jpg"},{"phototitle":"","photoimages":"/assets/img/qrcode.png"}]}]
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

    public static class ListBean {
        /**
         * id : 1
         * title : 标题
         * createtime : 1573536040
         * updatetime : 1573536040
         * deletetime : null
         * fengcai_set : null
         */

        private int id;
        private String title;
        private int createtime;
        private int updatetime;
        private Object deletetime;
        private Object fengcai_set;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public Object getFengcai_set() {
            return fengcai_set;
        }

        public void setFengcai_set(Object fengcai_set) {
            this.fengcai_set = fengcai_set;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", createtime=" + createtime +
                    ", updatetime=" + updatetime +
                    ", deletetime=" + deletetime +
                    ", fengcai_set=" + fengcai_set +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "FengcaiList{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
