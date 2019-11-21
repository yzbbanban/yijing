package com.yjb.mvp.model.bean;

import java.io.Serializable;
import java.util.List;

public class AcMyList implements Serializable {

    /**
     * total : 2
     * list : [{"id":1,"user_id":11,"teammgt_id":2,"createtime":1573520613,"updatetime":1574070860,"deletetime":null,"status":"1","activity_id":1,"activitytype_id":2,"title":"1","content":"<p>1<\/p>","coverimage":"/uploads/20191110/832a52e11613eb6f20f04668032e78cf.jpg","requirementdata":"1,2","activitystarttime":1573952556,"activitystoptime":1574075013,"activitystopapplytime":1573520556,"activityno":1,"address":"1","status_text":"Status 1","createtime_text":"2019-11-12 09:03:33"},{"id":1,"user_id":11,"teammgt_id":2,"createtime":1573520613,"updatetime":1574070860,"deletetime":null,"status":"2","activity_id":1,"activitytype_id":2,"title":"1","content":"<p>1<\/p>","coverimage":"/uploads/20191110/832a52e11613eb6f20f04668032e78cf.jpg","requirementdata":"1,2","activitystarttime":1573952556,"activitystoptime":1574075013,"activitystopapplytime":1573520556,"activityno":1,"address":"1","status_text":"Status 2","createtime_text":"2019-11-12 09:03:33"}]
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
         * user_id : 11
         * teammgt_id : 2
         * createtime : 1573520613
         * updatetime : 1574070860
         * deletetime : null
         * status : 1
         * activity_id : 1
         * activitytype_id : 2
         * title : 1
         * content : <p>1</p>
         * coverimage : /uploads/20191110/832a52e11613eb6f20f04668032e78cf.jpg
         * requirementdata : 1,2
         * activitystarttime : 1573952556
         * activitystoptime : 1574075013
         * activitystopapplytime : 1573520556
         * activityno : 1
         * address : 1
         * status_text : Status 1
         * createtime_text : 2019-11-12 09:03:33
         */

        private int id;
        private int user_id;
        private int teammgt_id;
        private long createtime;
        private long updatetime;
        private Object deletetime;
        private String status;
        private int activity_id;
        private int activitytype_id;
        private String title;
        private String content;
        private String coverimage;
        private String requirementdata;
        private long activitystarttime;
        private long activitystoptime;
        private long activitystopapplytime;
        private long activityno;
        private String address;
        private String status_text;
        private String createtime_text;

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

        public int getTeammgt_id() {
            return teammgt_id;
        }

        public void setTeammgt_id(int teammgt_id) {
            this.teammgt_id = teammgt_id;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public long getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(long updatetime) {
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

        public int getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(int activity_id) {
            this.activity_id = activity_id;
        }

        public int getActivitytype_id() {
            return activitytype_id;
        }

        public void setActivitytype_id(int activitytype_id) {
            this.activitytype_id = activitytype_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCoverimage() {
            return coverimage;
        }

        public void setCoverimage(String coverimage) {
            this.coverimage = coverimage;
        }

        public String getRequirementdata() {
            return requirementdata;
        }

        public void setRequirementdata(String requirementdata) {
            this.requirementdata = requirementdata;
        }

        public long getActivitystarttime() {
            return activitystarttime;
        }

        public void setActivitystarttime(long activitystarttime) {
            this.activitystarttime = activitystarttime;
        }

        public long getActivitystoptime() {
            return activitystoptime;
        }

        public void setActivitystoptime(long activitystoptime) {
            this.activitystoptime = activitystoptime;
        }

        public long getActivitystopapplytime() {
            return activitystopapplytime;
        }

        public void setActivitystopapplytime(long activitystopapplytime) {
            this.activitystopapplytime = activitystopapplytime;
        }

        public long getActivityno() {
            return activityno;
        }

        public void setActivityno(long activityno) {
            this.activityno = activityno;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStatus_text() {
            return status_text;
        }

        public void setStatus_text(String status_text) {
            this.status_text = status_text;
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
                    ", user_id=" + user_id +
                    ", teammgt_id=" + teammgt_id +
                    ", createtime=" + createtime +
                    ", updatetime=" + updatetime +
                    ", deletetime=" + deletetime +
                    ", status='" + status + '\'' +
                    ", activity_id=" + activity_id +
                    ", activitytype_id=" + activitytype_id +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", coverimage='" + coverimage + '\'' +
                    ", requirementdata='" + requirementdata + '\'' +
                    ", activitystarttime=" + activitystarttime +
                    ", activitystoptime=" + activitystoptime +
                    ", activitystopapplytime=" + activitystopapplytime +
                    ", activityno=" + activityno +
                    ", address='" + address + '\'' +
                    ", status_text='" + status_text + '\'' +
                    ", createtime_text='" + createtime_text + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AcMyList{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
