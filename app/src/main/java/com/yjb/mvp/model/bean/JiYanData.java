package com.yjb.mvp.model.bean;

/**
 * Created by kennysun on 2019/8/28.
 */

public class JiYanData {

    /**
     * success : 1
     * challenge : 31e1dcd15d8f894b82557ca873cec2ec
     * gt : 9b9a60cafea371a46f717b0dc252f05d
     * <p>
     * version : 4.0
     * status : success
     */

    private int success;
    private String challenge;
    private String gt;
    private boolean new_captcha;

    private String version;
    private String status;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public boolean isNew_captcha() {
        return new_captcha;
    }

    public void setNew_captcha(boolean new_captcha) {
        this.new_captcha = new_captcha;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
