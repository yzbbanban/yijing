package com.yjb.mvp.model.bean;

import java.io.Serializable;
import java.util.List;

public class RankList implements Serializable {

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
         * nickname : 小张
         * photoimage : /uploads/20191106/bd6d9ac651b18508b7d4138d0cf8b6a2.jpg
         * score : 31
         * worktime : 15
         */

        private String nickname;
        private String photoimage;
        private int score;
        private int worktime;
        private int index;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

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

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getWorktime() {
            return worktime;
        }

        public void setWorktime(int worktime) {
            this.worktime = worktime;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "nickname='" + nickname + '\'' +
                    ", photoimage='" + photoimage + '\'' +
                    ", score=" + score +
                    ", worktime=" + worktime +
                    ", index=" + index +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RankList{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
