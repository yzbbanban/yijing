package com.dian.commonlib.net;

import java.util.List;

/**
 * Created by dian on 2019/2/16.
 */

public class HttpResultList<T> {
    private List<T> dataList;
    private int count;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
