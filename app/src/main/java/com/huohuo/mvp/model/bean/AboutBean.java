package com.huohuo.mvp.model.bean;

/**
 * Created by kennysun on 2019/8/29.
 */

public class AboutBean {
    /**
     * contactPhone : string
     * feedbackEmail : string
     * lang : string
     * protocolContent : string
     * protocolName : string
     * protocolUrl : string
     */

    private String contactPhone;
    private String feedbackEmail;
    private String lang;
    private String protocolContent;
    private String protocolName;
    private String protocolUrl;

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getFeedbackEmail() {
        return feedbackEmail;
    }

    public void setFeedbackEmail(String feedbackEmail) {
        this.feedbackEmail = feedbackEmail;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getProtocolContent() {
        return protocolContent;
    }

    public void setProtocolContent(String protocolContent) {
        this.protocolContent = protocolContent;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getProtocolUrl() {
        return protocolUrl;
    }

    public void setProtocolUrl(String protocolUrl) {
        this.protocolUrl = protocolUrl;
    }
}
