package com.huohuo.mvp.model.bean;

import java.io.Serializable;
import java.util.List;

public class NewsList implements Serializable {

    /**
     * total : 1
     * list : [{"id":1,"title":"1","coverimage":"/uploads/20191119/8408395400e5ce31ebda27b82fba224d.jpg","content":"<p>32<\/p>","view":0,"createtime":1574150334,"updatetime":1574150334,"deletetime":null,"createtime_text":"2019-11-19 15:58:54"}]
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
         * title : 1
         * coverimage : /uploads/20191119/8408395400e5ce31ebda27b82fba224d.jpg
         * content : <p>32</p>
         * view : 0
         * createtime : 1574150334
         * updatetime : 1574150334
         * deletetime : null
         * createtime_text : 2019-11-19 15:58:54
         */

        private int id;
        private String title;
        private String coverimage;
        private String content;
        private int view;
        private int createtime;
        private int updatetime;
        private Object deletetime;
        private String createtime_text;

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

        public String getCoverimage() {
            return coverimage;
        }

        public void setCoverimage(String coverimage) {
            this.coverimage = coverimage;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getView() {
            return view;
        }

        public void setView(int view) {
            this.view = view;
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

        public String getCreatetime_text() {
            return createtime_text;
        }

        public void setCreatetime_text(String createtime_text) {
            this.createtime_text = createtime_text;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", coverimage='" + coverimage + '\'' +
                    ", content='" + content + '\'' +
                    ", view=" + view +
                    ", createtime=" + createtime +
                    ", updatetime=" + updatetime +
                    ", deletetime=" + deletetime +
                    ", createtime_text='" + createtime_text + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsList{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
