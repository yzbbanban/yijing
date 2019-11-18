package com.huohuo.dao.table;

import com.huohuo.mvp.model.bean.YjActivityDetail;

import java.util.*;

public class ActivityList {

    private Integer total;

    private List<YjActivityDetail> yjActivityDetails;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<YjActivityDetail> getYjActivityDetails() {
        return yjActivityDetails;
    }

    public void setYjActivityDetails(List<YjActivityDetail> yjActivityDetails) {
        this.yjActivityDetails = yjActivityDetails;
    }

    @Override
    public String toString() {
        return "ActivityList{" +
                "total=" + total +
                ", yjActivityDetails=" + yjActivityDetails +
                '}';
    }
}
