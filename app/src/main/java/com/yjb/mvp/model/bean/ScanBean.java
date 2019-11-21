package com.yjb.mvp.model.bean;

import java.util.List;

/**
 * Created by kennysun on 2019/9/4.
 */

public class ScanBean {
    /**
     * "type":0,
     * "assetWalletTransferVOList"
     */
    private int type;//2跳转内部链接,-2加好友, 1(外部)、0(内部)充币地址
    private String userId;
    private List<CoinAddressBean> assetWalletTransferVOList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<CoinAddressBean> getAssetWalletTransferVOList() {
        return assetWalletTransferVOList;
    }

    public void setAssetWalletTransferVOList(List<CoinAddressBean> assetWalletTransferVOList) {
        this.assetWalletTransferVOList = assetWalletTransferVOList;
    }
}
