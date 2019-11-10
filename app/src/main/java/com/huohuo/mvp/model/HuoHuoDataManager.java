package com.huohuo.mvp.model;

import com.dian.commonlib.net.DataManager;
import com.dian.commonlib.net.HttpResult;
import com.huohuo.mvp.model.bean.AboutBean;
import com.huohuo.mvp.model.bean.ContractsBean;
import com.huohuo.mvp.model.bean.CountryCodeBean;
import com.huohuo.mvp.model.bean.JiYanData;
import com.huohuo.mvp.model.bean.ScanBean;
import com.huohuo.mvp.model.bean.SplashBean;
import com.huohuo.mvp.model.bean.TokenBean;
import com.huohuo.mvp.model.bean.User;
import com.huohuo.net.HuoHuoApi;
import com.huohuo.dao.table.Friend;
import com.huohuo.dao.table.FriendApply;
import com.huohuo.dao.table.Group;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * Created by kennysun on 2019/8/26.
 */

public class HuoHuoDataManager extends DataManager<HuoHuoApi> {
    private static HuoHuoDataManager huoHuoDataManager;

    public static HuoHuoDataManager getHuoHuoDataManager(String baseUrl, Class<HuoHuoApi> cls) {
        if (huoHuoDataManager == null) {
            huoHuoDataManager = new HuoHuoDataManager(baseUrl, cls);
        }
        return huoHuoDataManager;
    }

    public HuoHuoDataManager(String baseUrl, Class<HuoHuoApi> cls) {
        super(baseUrl, cls);
    }

    //api接口相关

    /**
     * 发送添加好友的申请
     *
     * @param friendUid
     * @param remark
     * @return
     */
    public Observable<HttpResult<Object>> addFriend(String friendUid, String remark) {
        return mApi.addFriend(friendUid, remark);
    }

    /**
     * 获取手机联系人列表
     *
     * @param mobile
     * @return
     */
    public Observable<HttpResult<List<Friend>>> mobileList(String mobile) {
        return mApi.mobileList(mobile);
    }

    /**
     * 手机联系人  邀请短信内容
     *
     * @return
     */
    public Observable<HttpResult<String>> getSmsContent() {
        return mApi.getSmsContent();
    }

    /**
     * 通过手机号或用户id查询搜索好友
     *
     * @return
     */
    public Observable<HttpResult<Friend>> getSearchFriendInfo(String friendUid, String mobile) {
        return mApi.getSearchFriendInfo(friendUid, mobile);
    }

    /**
     * 扫描二维码、或者输入一段字符串，判断字符串类型（内部币种地址、外部币种地址、其他字符串）
     *
     * @param code
     * @return
     */
    public Observable<HttpResult<ScanBean>> getCoinAddressType(String code) {
        return mApi.getCoinAddressType(code);
    }


    /**
     * 转币页面扫码专用
     * 扫描二维码、或者输入一段字符串，判断字符串类型（内部币种地址、外部币种地址、其他字符串）
     *
     * @param code
     * @return
     */
    public Observable<HttpResult<ScanBean>> getTransSend(String code) {
        return mApi.getTransSend(code);
    }

    /**
     * 解密userid
     *
     * @param id
     * @return
     */
    public Observable<HttpResult<String>> decodingUserid(String id) {
        return mApi.decodingUserid(id);
    }

    /**
     * 获取加密的userid,用在生成自己的二维码
     *
     * @return
     */
    public Observable<HttpResult<String>> getJiamiUserId() {
        return mApi.getJiamiUserId();
    }

    /**
     * 回复好友申请
     *
     * @param friendUid
     * @param replyType 好友申请回复类型 1:同意 2:删除消息
     * @return
     */
    public Observable<HttpResult<Object>> replyApply(String friendUid, int replyType) {
        return mApi.replyApply(friendUid, replyType);
    }

    /**
     * 获取好友申请列表
     *
     * @return
     */
    public Observable<HttpResult<List<FriendApply>>> listFriendApply() {
        return mApi.listFriendApply();
    }

    /**
     * 查看个人群列表
     *
     * @return
     */
    public Observable<HttpResult<List<Group>>> getGroups() {
        return mApi.getGroups();
    }

    /**
     * 获取好友列表
     *
     * @return
     */
    public Observable<HttpResult<ContractsBean>> getFriends() {
        return mApi.getFriends();
    }

    /**
     * 检查密码是否正确
     *
     * @param payPass
     * @return
     */
    public Observable<HttpResult<Object>> checkPayPwd(String payPass) {
        return mApi.checkPayPwd(payPass);
    }

    /**
     * 获取关于app相关信息
     *
     * @return
     */
    public Observable<HttpResult<AboutBean>> about() {
        return mApi.about();
    }

    /**
     * 手机号注册
     *
     * @param countryCode
     * @param mobile
     * @param valiRegisterCode
     * @return
     */
    public Observable<HttpResult<TokenBean>> regist(String countryCode, String mobile, String valiRegisterCode) {
        return mApi.regist(countryCode, mobile, valiRegisterCode);
    }

    /**
     * 获取注册短信验证码
     *
     * @param countryCode
     * @param mobile
     * @return
     */
    public Observable<HttpResult<Object>> getRegistCode(String countryCode, String mobile) {
        return mApi.getRegistCode(countryCode, mobile);
    }

    /**
     * 获取国家代码
     *
     * @return
     */
    public Observable<HttpResult<List<CountryCodeBean>>> getCountryCode() {
        return mApi.getCountryCode();
    }

    /**
     * 极验
     *
     * @return
     */
    public Observable<HttpResult<JiYanData>> jiYanApi1() {
        return mApi.jiYanApi1();
    }

    /**
     * 极验二次验证
     *
     * @return
     */
    public Observable<HttpResult<Object>> jiYanApi2(String challenge, String validate, String userName, String countryCode, RequestBody seccode) {
        return mApi.jiYanApi2(challenge, validate, userName, countryCode, seccode);
    }


    /**
     * 退出登录
     *
     * @return
     */
    public Observable<HttpResult<Object>> userExit() {
        return mApi.userExit();
    }

    /**
     * 获取登录验证码
     *
     * @param countryCode
     * @param mobile
     * @return
     */
    public Observable<HttpResult<Object>> sendLoginSms(String countryCode, String mobile) {
        return mApi.sendLoginSms(countryCode, mobile);
    }


    /**
     * 验证码登录
     *
     * @param countryCode
     * @param mobile
     * @param valiRegisterCode
     * @return
     */
    public Observable<HttpResult<TokenBean>> codeLogin(String countryCode, String mobile, String valiRegisterCode) {
        return mApi.codeLogin(countryCode, mobile, valiRegisterCode);
    }

    /**
     * 启动页
     *
     * @return
     */
    public Observable<HttpResult<SplashBean>> splash() {
        return mApi.splash();
    }

    /**
     * 获取服务器时间（毫秒）
     *
     * @return
     */
    public Observable<HttpResult<Long>> getServiceTime() {
        return mApi.getServiceTime();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public Observable<HttpResult<User.UserInfo>> getUserInfo() {
        return mApi.getUserInfo();
    }

    /**
     * 获取用户账户信息
     *
     * @return
     */
    public Observable<HttpResult<User.UserAccount>> getUserAccount() {
        return mApi.getUserAccount();
    }

    /**
     * 获取用户安全设置
     *
     * @return
     */
    public Observable<HttpResult<User.UserSafeSet>> getUserSafeSet() {
        return mApi.getUserSafeSet();
    }

    /**
     * 获取用户隐私设置
     *
     * @return
     */
    public Observable<HttpResult<User.UserPrivacySet>> getUserPrivacySet() {
        return mApi.getUserPrivacySet();
    }

    /**
     * 获取用户通用设置
     *
     * @return
     */
    public Observable<HttpResult<User.UserCommonSet>> getUserCommonSet() {
        return mApi.getUserCommonSet();
    }


}
