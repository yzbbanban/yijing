package com.huohuo.mvp.model.bean;

import java.util.List;

public class ArticleList {

    /**
     * total : 1
     * list : [{"id":2,"news_id":2,"status":"1","loopswitch":1,"topswitch":1,"drafswitch":0,"createtime":1574228803,"updatetime":1574228803,"deletetime":null,"title":"测试新闻","coverimage":"/uploads/20191119/95b24adb862da41b50a6a6f356bdf80b.jpeg","content":"<p>123<\/p>","view":0,"status_text":"Status 1"}]
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
         * id : 2
         * news_id : 2
         * status : 1
         * loopswitch : 1
         * topswitch : 1
         * drafswitch : 0
         * createtime : 1574228803
         * updatetime : 1574228803
         * deletetime : null
         * title : 测试新闻
         * coverimage : /uploads/20191119/95b24adb862da41b50a6a6f356bdf80b.jpeg
         * content : <p>123</p>
         * view : 0
         * status_text : Status 1
         */

        private int id;
        private int news_id;
        private String status;
        private int loopswitch;
        private int topswitch;
        private int drafswitch;
        private int createtime;
        private int updatetime;
        private Object deletetime;
        private String title;
        private String coverimage;
        private String content;
        private int view;
        private String status_text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getLoopswitch() {
            return loopswitch;
        }

        public void setLoopswitch(int loopswitch) {
            this.loopswitch = loopswitch;
        }

        public int getTopswitch() {
            return topswitch;
        }

        public void setTopswitch(int topswitch) {
            this.topswitch = topswitch;
        }

        public int getDrafswitch() {
            return drafswitch;
        }

        public void setDrafswitch(int drafswitch) {
            this.drafswitch = drafswitch;
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
                    ", news_id=" + news_id +
                    ", status='" + status + '\'' +
                    ", loopswitch=" + loopswitch +
                    ", topswitch=" + topswitch +
                    ", drafswitch=" + drafswitch +
                    ", createtime=" + createtime +
                    ", updatetime=" + updatetime +
                    ", deletetime=" + deletetime +
                    ", title='" + title + '\'' +
                    ", coverimage='" + coverimage + '\'' +
                    ", content='" + content + '\'' +
                    ", view=" + view +
                    ", status_text='" + status_text + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ArticleList{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
