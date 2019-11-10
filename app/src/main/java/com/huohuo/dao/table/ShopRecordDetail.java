package com.huohuo.dao.table;

public class ShopRecordDetail extends ShopDetail {


    private String time;

    private Integer status;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShopRecordDetail{" +
                "time='" + time + '\'' +
                ", status=" + status +
                '}';
    }
}
