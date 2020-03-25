package com.yjb.mvp.model.bean;

public class YjActivityDetail {

    /**
     * id : 1
     * activitytype_id : 2
     * title : 1
     * content : <p>1</p>
     * coverimage : /uploads/20191110/832a52e11613eb6f20f04668032e78cf.jpg
     * requirementdata :
     * activitystarttime : 1573952556
     * activitystoptime : 1574075013
     * activitystopapplytime : 1573520556
     * activityno : 1
     * address : 1
     * user_id : 11
     * createtime : 1573520613
     * updatetime : 1574070860
     * deletetime : null
     * requirementdata_text :
     * activitystarttime_text : 2019-11-17 09:02:36
     * activitystopapplytime_text : 2019-11-12 09:02:36
     * activitystoptime_text : 2019-11-18 19:03:33
     * userid_text : {"nickname":"","mobile":""}
     */

    private Integer id;
    private Integer activitytype_id;
    private String title;
    private String content;
    private String coverimage;
    private String requirementdata;
    private Integer activitystarttime;
    private Integer activitystoptime;
    private Integer activitystopapplytime;
    private Integer activityno;
    private String address;
    private Integer user_id;
    private Integer createtime;
    private Integer updatetime;
    private Object deletetime;
    private String requirementdata_text;
    private String activitystarttime_text;
    private String activitystopapplytime_text;
    private String activitystoptime_text;
    private UseridTextBean userid_text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivitytype_id() {
        return activitytype_id;
    }

    public void setActivitytype_id(Integer activitytype_id) {
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

    public Integer getActivitystarttime() {
        return activitystarttime;
    }

    public void setActivitystarttime(Integer activitystarttime) {
        this.activitystarttime = activitystarttime;
    }

    public Integer getActivitystoptime() {
        return activitystoptime;
    }

    public void setActivitystoptime(Integer activitystoptime) {
        this.activitystoptime = activitystoptime;
    }

    public Integer getActivitystopapplytime() {
        return activitystopapplytime;
    }

    public void setActivitystopapplytime(Integer activitystopapplytime) {
        this.activitystopapplytime = activitystopapplytime;
    }

    public Integer getActivityno() {
        return activityno;
    }

    public void setActivityno(Integer activityno) {
        this.activityno = activityno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public Integer getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Integer updatetime) {
        this.updatetime = updatetime;
    }

    public Object getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(Object deletetime) {
        this.deletetime = deletetime;
    }

    public String getRequirementdata_text() {
        return requirementdata_text;
    }

    public void setRequirementdata_text(String requirementdata_text) {
        this.requirementdata_text = requirementdata_text;
    }

    public String getActivitystarttime_text() {
        return activitystarttime_text;
    }

    public void setActivitystarttime_text(String activitystarttime_text) {
        this.activitystarttime_text = activitystarttime_text;
    }

    public String getActivitystopapplytime_text() {
        return activitystopapplytime_text;
    }

    public void setActivitystopapplytime_text(String activitystopapplytime_text) {
        this.activitystopapplytime_text = activitystopapplytime_text;
    }

    public String getActivitystoptime_text() {
        return activitystoptime_text;
    }

    public void setActivitystoptime_text(String activitystoptime_text) {
        this.activitystoptime_text = activitystoptime_text;
    }

    public UseridTextBean getUserid_text() {
        return userid_text;
    }

    public void setUserid_text(UseridTextBean userid_text) {
        this.userid_text = userid_text;
    }

    public static class UseridTextBean {
        /**
         * nickname :
         * mobile :
         */

        private String nickname;
        private String mobile;

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

        @Override
        public String toString() {
            return "UseridTextBean{" +
                    "nickname='" + nickname + '\'' +
                    ", mobile='" + mobile + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "YjActivityDetail{" +
                "id=" + id +
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
                ", user_id=" + user_id +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", deletetime=" + deletetime +
                ", requirementdata_text='" + requirementdata_text + '\'' +
                ", activitystarttime_text='" + activitystarttime_text + '\'' +
                ", activitystopapplytime_text='" + activitystopapplytime_text + '\'' +
                ", activitystoptime_text='" + activitystoptime_text + '\'' +
                ", userid_text=" + userid_text +
                '}';
    }
}
