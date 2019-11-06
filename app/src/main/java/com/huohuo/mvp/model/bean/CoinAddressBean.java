package com.huohuo.mvp.model.bean;

import java.io.Serializable;

/**
 * Created by kennysun on 2019/9/4.
 */

public class CoinAddressBean implements Serializable {

    /**
     * 扫一扫返回
     * "coinId":3,
     * "address":"Aa7SZRkFMsHvxzLdnYQ1eSHHjHcYKqXNYU",
     * "enName":"GAS",
     * "logoUrl":"image/coin/logo/GAS.png"
     * <p>
     * <p>
     * 收币页面返回的
     * {"address":"btwcomwallet","memo":"w8"}
     */

    private String memo;

    private int coinId;
    private String address;
    private Integer type;//0是内部，1是外部
    private String enName;
    private String logoUrl;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getCoinId() {
        return coinId;
    }

    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }
}
