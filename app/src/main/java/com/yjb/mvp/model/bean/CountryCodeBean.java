package com.yjb.mvp.model.bean;

import android.support.annotation.NonNull;

/**
 * Created by kennysun on 2019/8/29.
 */

public class CountryCodeBean implements Comparable<CountryCodeBean> {
    /**
     * "id": 1,
     * "cnCountryName": "中国",
     * "enCountryName": "China",
     * "countryCode": "86",
     * "sort": 0,
     * "updateTime": 1535011648,
     * "cnFirstLetter": "Z"
     */

    private int id;
    private String cnCountryName;
    private String enCountryName;
    private String countryCode;
    private String cnFirstLetter;
    private String sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnCountryName() {
        return cnCountryName;
    }

    public void setCnCountryName(String cnCountryName) {
        this.cnCountryName = cnCountryName;
    }

    public String getEnCountryName() {
        return enCountryName;
    }

    public void setEnCountryName(String enCountryName) {
        this.enCountryName = enCountryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCnFirstLetter() {
        return cnFirstLetter;
    }

    public void setCnFirstLetter(String cnFirstLetter) {
        this.cnFirstLetter = cnFirstLetter;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public int compareTo(@NonNull CountryCodeBean countryCode) {
        return this.cnFirstLetter.compareTo(countryCode.cnFirstLetter);
    }
}
