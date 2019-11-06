package com.huohuo.mvp.model.bean;

import android.text.TextUtils;

import com.dian.commonlib.app.Constants;
import com.dian.commonlib.utils.MmkvUtil;
import com.huohuo.app.HuoHuoApp;
import com.huohuo.app.HuoHuoConstants;

import java.util.ArrayList;

/**
 * Created by kennysun on 2019/8/7.
 */

public class User {
    private static User user;

    public static User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    private UserInfo userInfo;
    private UserAccount userAccount;
    private UserCommonSet userCommonSet;
    private UserPrivacySet userPrivacySet;
    private UserSafeSet userSafeSet;

    public UserInfo getUserInfo() {
        if (userInfo == null) {
            String s = MmkvUtil.decodeString(Constants.USER_INFO, "");
            if (!TextUtils.isEmpty(s)) {
                userInfo = HuoHuoApp.gson.fromJson(s, UserInfo.class);
            }
        }
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserAccount getUserAccount() {
        if (userAccount == null) {
            String s = MmkvUtil.decodeString(Constants.USER_ACCOUNT, "");
            if (!TextUtils.isEmpty(s)) {
                userAccount = HuoHuoApp.gson.fromJson(s, UserAccount.class);
            }
        }
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public UserCommonSet getUserCommonSet() {
        if (userCommonSet == null) {
            String s = MmkvUtil.decodeString(Constants.USER_COMMON_SET, "");
            if (!TextUtils.isEmpty(s)) {
                userCommonSet = HuoHuoApp.gson.fromJson(s, UserCommonSet.class);
            }
        }
        return userCommonSet;
    }

    public void setUserCommonSet(UserCommonSet userCommonSet) {
        this.userCommonSet = userCommonSet;
    }

    public UserPrivacySet getUserPrivacySet() {
        if (userPrivacySet == null) {
            String s = MmkvUtil.decodeString(Constants.USER_PRIVACY_SET, "");
            if (!TextUtils.isEmpty(s)) {
                userPrivacySet = HuoHuoApp.gson.fromJson(s, UserPrivacySet.class);
            }
        }
        return userPrivacySet;
    }

    public void setUserPrivacySet(UserPrivacySet userPrivacySet) {
        this.userPrivacySet = userPrivacySet;
    }

    public UserSafeSet getUserSafeSet() {
        if (userSafeSet == null) {
            String s = MmkvUtil.decodeString(Constants.USER_SAFE_SET, "");
            if (!TextUtils.isEmpty(s)) {
                userSafeSet = HuoHuoApp.gson.fromJson(s, UserSafeSet.class);
            }
        }
        return userSafeSet;
    }

    public void setUserSafeSet(UserSafeSet userSafeSet) {
        this.userSafeSet = userSafeSet;
    }

    /**
     * applyTime (integer, optional): 认证申请时间 ,
     * certificateNo (string, optional): 证件号码 ,
     * certificatePhoto (string, optional): 证件照片路径 ,
     * certificatePhotos (Array[string], optional): 证件照片路径(数据处理) ,
     * certificateType (string, optional): 证件类型(1身份证；2护照，3港澳台身份证) ,
     * certificationLevel (string, optional): 认证等级（0未认证；1实名认证；2人脸识别） ,
     * checkStatus (string, optional): 认证状态（0待审核；1通过；2拒绝） ,
     * checkTime (integer, optional): 认证审核时间 ,
     * hasSetPayPass (boolean, optional): 是否已经设置支付密码 是true 没有false ,
     * headImage (string, optional): 头像 ,
     * indate (string, optional): 有效期限 ,
     * issuingAuthority (string, optional): 签发机关 ,
     * nickName (string, optional): 昵称 ,
     * realName (string, optional): 真实姓名 ,
     * rejectCause (string, optional): 认证拒绝原因 ,
     * updateTime (integer, optional): 修改时间
     */
    public class UserInfo {
        private Long id;
        private int certificateType;
        private int certificationLevel;
        private Long applyTime;
        private Long checkTime;
        private Long updateTime;
        private boolean hasSetPayPass;
        private String nickName;
        private String headImage;
        private String realName;
        private String certificateNo;
        private String certificatePhoto;
        private String issuingAuthority;
        private String indate;
        private String rejectCause;
        private ArrayList<String> certificatePhotos;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getCertificateType() {
            return certificateType;
        }

        public void setCertificateType(int certificateType) {
            this.certificateType = certificateType;
        }

        public int getCertificationLevel() {
            return certificationLevel;
        }

        public void setCertificationLevel(int certificationLevel) {
            this.certificationLevel = certificationLevel;
        }

        public Long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(Long applyTime) {
            this.applyTime = applyTime;
        }

        public Long getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(Long checkTime) {
            this.checkTime = checkTime;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

        public boolean isHasSetPayPass() {
            return hasSetPayPass;
        }

        public void setHasSetPayPass(boolean hasSetPayPass) {
            this.hasSetPayPass = hasSetPayPass;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getCertificateNo() {
            return certificateNo;
        }

        public void setCertificateNo(String certificateNo) {
            this.certificateNo = certificateNo;
        }

        public String getCertificatePhoto() {
            return certificatePhoto;
        }

        public void setCertificatePhoto(String certificatePhoto) {
            this.certificatePhoto = certificatePhoto;
        }

        public String getIssuingAuthority() {
            return issuingAuthority;
        }

        public void setIssuingAuthority(String issuingAuthority) {
            this.issuingAuthority = issuingAuthority;
        }

        public String getIndate() {
            return indate;
        }

        public void setIndate(String indate) {
            this.indate = indate;
        }

        public String getRejectCause() {
            return rejectCause;
        }

        public void setRejectCause(String rejectCause) {
            this.rejectCause = rejectCause;
        }

        public ArrayList<String> getCertificatePhotos() {
            return certificatePhotos;
        }

        public void setCertificatePhotos(ArrayList<String> certificatePhotos) {
            this.certificatePhotos = certificatePhotos;
        }
    }

    /**
     * /**
     * account (string, optional): 登录账号 ,
     * countryCode (string, optional): 国家代码 ,
     * createTime (integer, optional): 创建时间 ,
     * email (string, optional): 邮箱地址 ,
     * freezeCause (string, optional): 冻结原因 ,
     * freezeTime (integer, optional): 冻结时间 ,
     * freezed (boolean, optional): 是否冻结 ,
     * hasSetLoginPass (boolean, optional): 是否设置了登陆密码，是 true 没有 false ,
     * id (integer, optional): 个人用户账户ID ,
     * lastLoginTime (integer, optional): 最后一次登录时间 ,
     * mobile (string, optional): 手机号码 ,
     * registerIP (string, optional): 注册IP ,
     * registerSource (string, optional): 注册源 ,
     * registerType (string, optional): 注册方式 ,
     * updateTime (integer, optional): 修改时间
     */

    public class UserAccount {
        private Long id;
        private Long freezeTime;
        private Long createTime;
        private Long updateTime;
        private Long lastLoginTime;
        private boolean freezed;
        private boolean hasSetLoginPass;
        private String countryCode;
        private String mobile;
        private String email;
        private String account;
        private String freezeCause;
        private String registerType;
        private String registerSource;
        private String registerIP;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getFreezeTime() {
            return freezeTime;
        }

        public void setFreezeTime(Long freezeTime) {
            this.freezeTime = freezeTime;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

        public Long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(Long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public boolean isFreezed() {
            return freezed;
        }

        public void setFreezed(boolean freezed) {
            this.freezed = freezed;
        }

        public boolean isHasSetLoginPass() {
            return hasSetLoginPass;
        }

        public void setHasSetLoginPass(boolean hasSetLoginPass) {
            this.hasSetLoginPass = hasSetLoginPass;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getFreezeCause() {
            return freezeCause;
        }

        public void setFreezeCause(String freezeCause) {
            this.freezeCause = freezeCause;
        }

        public String getRegisterType() {
            return registerType;
        }

        public void setRegisterType(String registerType) {
            this.registerType = registerType;
        }

        public String getRegisterSource() {
            return registerSource;
        }

        public void setRegisterSource(String registerSource) {
            this.registerSource = registerSource;
        }

        public String getRegisterIP() {
            return registerIP;
        }

        public void setRegisterIP(String registerIP) {
            this.registerIP = registerIP;
        }
    }


    /**
     * id : 17
     * useLoginGoogle : false
     * useModifyPassGoogle : false
     * useModifyPayPassGoogle : false
     * useCoinOutGoogle : false
     * useLoginSafetyTips : true
     * updateTime : 1535184289
     * <p>
     * "id": ID,
     * "useLoginGoogle": 登录启用谷歌验证,
     * "useModifyPassGoogle": 修改登录密码启用谷歌验证,
     * "useModifyPayPassGoogle": 修改支付密码启用谷歌验证,
     * "useCoinOutGoogle": 提币启用谷歌验证,
     * "useLoginSafetyTips": 开启登录安全提醒,
     * "updateTime": 更新时间
     */
    public class UserSafeSet {
        private Long id;
        private Long updateTime;
        private boolean useLoginGoogle;
        private boolean useModifyPassGoogle;
        private boolean useModifyPayPassGoogle;
        private boolean useCoinOutGoogle;
        private boolean useLoginSafetyTips;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

        public boolean isUseLoginGoogle() {
            return useLoginGoogle;
        }

        public void setUseLoginGoogle(boolean useLoginGoogle) {
            this.useLoginGoogle = useLoginGoogle;
        }

        public boolean isUseModifyPassGoogle() {
            return useModifyPassGoogle;
        }

        public void setUseModifyPassGoogle(boolean useModifyPassGoogle) {
            this.useModifyPassGoogle = useModifyPassGoogle;
        }

        public boolean isUseModifyPayPassGoogle() {
            return useModifyPayPassGoogle;
        }

        public void setUseModifyPayPassGoogle(boolean useModifyPayPassGoogle) {
            this.useModifyPayPassGoogle = useModifyPayPassGoogle;
        }

        public boolean isUseCoinOutGoogle() {
            return useCoinOutGoogle;
        }

        public void setUseCoinOutGoogle(boolean useCoinOutGoogle) {
            this.useCoinOutGoogle = useCoinOutGoogle;
        }

        public boolean isUseLoginSafetyTips() {
            return useLoginSafetyTips;
        }

        public void setUseLoginSafetyTips(boolean useLoginSafetyTips) {
            this.useLoginSafetyTips = useLoginSafetyTips;
        }
    }


    /**
     * "id": ID,
     * "useFriendValid": 是否开启好友验证,
     * "useShowRealName": 是否公开真实姓名,
     * "useStrangerLookDynamic": 是否允许默生人查看我的动态,
     * "usePhoneNumFindMe": 通过手机号找到我,
     * "useEmailFindMe": 通过邮箱找到我,
     * "useAccountFindMe": 通过账号找到我,
     * "updateTime": 更新时间
     */
    public class UserPrivacySet {
        private Long id;
        private Long updateTime;
        private boolean useFriendValid;
        private boolean useShowRealName;
        private boolean useStrangerLookDynamic;
        private boolean usePhoneNumFindMe;
        private boolean useEmailFindMe;
        private boolean useAccountFindMe;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

        public boolean isUseFriendValid() {
            return useFriendValid;
        }

        public void setUseFriendValid(boolean useFriendValid) {
            this.useFriendValid = useFriendValid;
        }

        public boolean isUseShowRealName() {
            return useShowRealName;
        }

        public void setUseShowRealName(boolean useShowRealName) {
            this.useShowRealName = useShowRealName;
        }

        public boolean isUseStrangerLookDynamic() {
            return useStrangerLookDynamic;
        }

        public void setUseStrangerLookDynamic(boolean useStrangerLookDynamic) {
            this.useStrangerLookDynamic = useStrangerLookDynamic;
        }

        public boolean isUsePhoneNumFindMe() {
            return usePhoneNumFindMe;
        }

        public void setUsePhoneNumFindMe(boolean usePhoneNumFindMe) {
            this.usePhoneNumFindMe = usePhoneNumFindMe;
        }

        public boolean isUseEmailFindMe() {
            return useEmailFindMe;
        }

        public void setUseEmailFindMe(boolean useEmailFindMe) {
            this.useEmailFindMe = useEmailFindMe;
        }

        public boolean isUseAccountFindMe() {
            return useAccountFindMe;
        }

        public void setUseAccountFindMe(boolean useAccountFindMe) {
            this.useAccountFindMe = useAccountFindMe;
        }
    }

    /**
     * id : 17
     * useSystemNotice : true
     * useActivityNotice : true
     * useAcceptFriendMessage : true
     * useShowMessageDetail : true
     * updateTime : 1535184289
     * <p>
     * { "id": ID,
     * "useSystemNotice": 是否开启系统通知,
     * "useActivityNotice": 是否开启活动通知,
     * "useAcceptFriendMessage": 是否开启接收好友信息提醒,
     * "useShowMessageDetail": 是否显示消息通知详情,
     * "updateTime": 更新时间
     */
    public class UserCommonSet {
        private Long id;
        private Long updateTime;
        private boolean useSystemNotice;
        private boolean useActivityNotice;
        private boolean useAcceptFriendMessage;
        private boolean useShowMessageDetail;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

        public boolean isUseSystemNotice() {
            return useSystemNotice;
        }

        public void setUseSystemNotice(boolean useSystemNotice) {
            this.useSystemNotice = useSystemNotice;
        }

        public boolean isUseActivityNotice() {
            return useActivityNotice;
        }

        public void setUseActivityNotice(boolean useActivityNotice) {
            this.useActivityNotice = useActivityNotice;
        }

        public boolean isUseAcceptFriendMessage() {
            return useAcceptFriendMessage;
        }

        public void setUseAcceptFriendMessage(boolean useAcceptFriendMessage) {
            this.useAcceptFriendMessage = useAcceptFriendMessage;
        }

        public boolean isUseShowMessageDetail() {
            return useShowMessageDetail;
        }

        public void setUseShowMessageDetail(boolean useShowMessageDetail) {
            this.useShowMessageDetail = useShowMessageDetail;
        }
    }
}
