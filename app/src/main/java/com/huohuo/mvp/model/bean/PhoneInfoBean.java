package com.huohuo.mvp.model.bean;

/**
 * 手机联系人
 * Created by kennysun on 2019/9/4.
 */

public class PhoneInfoBean {  private String name;
    private String number;
    private String sortKey;
    private int id;

    private boolean isAlreadyRegist = false;

    public PhoneInfoBean(String name, String number, String sortkey, int id) {
        this.name = name;
        this.number = number;
        this.sortKey = sortKey;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAlreadyRegist() {
        return isAlreadyRegist;
    }

    public void setAlreadyRegist(boolean alreadyRegist) {
        isAlreadyRegist = alreadyRegist;
    }
}