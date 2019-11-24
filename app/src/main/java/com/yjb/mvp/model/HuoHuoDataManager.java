package com.yjb.mvp.model;

import com.dian.commonlib.net.DataManager;
import com.dian.commonlib.net.HttpResult;
import com.yjb.dao.table.Friend;
import com.yjb.dao.table.FriendApply;
import com.yjb.dao.table.Group;
import com.yjb.mvp.model.bean.AboutBean;
import com.yjb.mvp.model.bean.AcMyList;
import com.yjb.mvp.model.bean.ActivityList;
import com.yjb.mvp.model.bean.ArticleList;
import com.yjb.mvp.model.bean.ContractsBean;
import com.yjb.mvp.model.bean.CountryCodeBean;
import com.yjb.mvp.model.bean.ExchangeList;
import com.yjb.mvp.model.bean.FengcaiList;
import com.yjb.mvp.model.bean.HomeYjRank;
import com.yjb.mvp.model.bean.JFInfo;
import com.yjb.mvp.model.bean.JiYanData;
import com.yjb.mvp.model.bean.MallList;
import com.yjb.mvp.model.bean.NewsList;
import com.yjb.mvp.model.bean.RankList;
import com.yjb.mvp.model.bean.ScanBean;
import com.yjb.mvp.model.bean.SplashBean;
import com.yjb.mvp.model.bean.TokenBean;
import com.yjb.mvp.model.bean.UploadBean;
import com.yjb.mvp.model.bean.User;
import com.yjb.mvp.model.bean.UserInfo;
import com.yjb.mvp.model.bean.YjTeam;
import com.yjb.net.HuoHuoApi;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Query;

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
     * @param mobile
     * @param event
     * @return
     */
    public Observable<HttpResult<Object>> sendLoginSms(String mobile, String event) {
        return mApi.smsSend(mobile, event);
    }

    /**
     * 获取义警活动列表
     *
     * @return
     */
    public Observable<HttpResult<ActivityList>> getYjAc(String token, String page, String size, String type) {
        return mApi.activityList(token, page, size, type);
    }

    /**
     * 获取义警风采列表
     *
     * @return
     */
    public Observable<HttpResult<FengcaiList>> getYjFc(String token, String page, String size) {
        return mApi.fengcaiList(token, page, size);
    }


    /**
     * 首页义警排名
     *
     * @return
     */
    public Observable<HttpResult<List<HomeYjRank>>> homeYjRank(String token) {
        return mApi.homeYjRank(token);
    }


    /**
     * 月排名
     *
     * @return
     */
    public Observable<HttpResult<RankList>> rankMonthList(String token, String page, String size) {
        return mApi.rankMonth(token, page, size);
    }

    /**
     * 年排名
     *
     * @return
     */
    public Observable<HttpResult<RankList>> rankYearList(String token, String page, String size) {
        return mApi.rankYear(token, page, size);
    }

    /**
     * 义警队伍
     *
     * @return
     */
    public Observable<HttpResult<YjTeam>> yjTeam(String token, String page, String size) {
        return mApi.yjTeam(token, page, size);
    }

    /**
     * 首页义警排名
     *
     * @return
     */
    public Observable<HttpResult<JFInfo>> jFinfo(String token, String user_id) {
        return mApi.jFinfo(token, user_id);
    }

    /**
     * 获取签到状态
     * 未签到,已签到,未签退,已签退
     *
     * @return
     */
    public Observable<HttpResult<String>> getSignStatuInfo(String token, String activity_id, String user_id) {
        return mApi.getSignStatus(token, activity_id, user_id);
    }

    /**
     * 兑换
     *
     * @return
     */
    public Observable<HttpResult<String>> exchangeAPay(
            String token,
            String user_id,
            String id,
            String score) {
        return mApi.exchangeAPay(token, user_id, id, score);

    }

    /**
     * 获取商品列表
     *
     * @return
     */
    public Observable<HttpResult<MallList>> getMall(String token, String page, String size, String user_id) {
        return mApi.mallList(token, page, size, user_id);
    }

    /**
     * 获取义兑换列表
     *
     * @return
     */
    public Observable<HttpResult<ExchangeList>> getEx(String token, String page, String size, String user_id) {
        return mApi.exchangeList(token, page, size, user_id);
    }

    /**
     * 获取义兑换列表
     *
     * @return
     */
    public Observable<HttpResult<NewsList>> getNews(String token, String page, String size) {
        return mApi.newsList(token, page, size);
    }

    /**
     * 获取义兑换列表
     *
     * @return
     */
    public Observable<HttpResult<AcMyList>> getMyac(String token, String page, String size, String user_id) {
        return mApi.acMyList(token, page, size, user_id);
    }

    /**
     * 获取轮播新闻接口列表
     *
     * @return
     */
    public Observable<HttpResult<ArticleList>> articleLlist(String token, String page, String size) {
        return mApi.articleLlist(token, page, size);
    }

    /**
     * 获取轮播新闻接口列表
     *
     * @return
     */
    public Observable<HttpResult<String>> yjApply(String token,
                                                  String user_id,
                                                  String vision,
                                                  String photoimage,
                                                  String homeaddress,
                                                  String politically,
                                                  String identifier,
                                                  String job,
                                                  String nickname,
                                                  String gender,
                                                  String birthday) {
        return mApi.yjApply(token, user_id, vision, photoimage, homeaddress,
                politically, identifier, job, nickname, gender, birthday);
    }

    /**
     * 新闻详情自增
     *
     * @return
     */
    public Observable<HttpResult<Object>> newsDetailIncr(String token, String id) {
        return mApi.newsDetailIncr(token, id);
    }

    /**
     * 新闻详情自增
     *
     * @return
     */
    public Observable<HttpResult<UploadBean>> upload(RequestBody token, MultipartBody.Part file) {
        return mApi.commonUpload(token, file);
    }

    /**
     * 签退
     *
     * @return
     */
    public Observable<HttpResult<String>> activitySignOut(String token, String fakeid, String activity_id) {
        return mApi.activitySignOut(token, fakeid, activity_id);
    }

    /**
     * 签到
     *
     * @return
     */
    public Observable<HttpResult<String>> activitySignUp(String token,
                                                         String activity_id,
                                                         String user_id,
                                                         String teammgt_id) {
        return mApi.activitySignUp(token, activity_id, user_id, teammgt_id);
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
     * 验证码登录
     *
     * @param account
     * @param code
     * @return
     */
    public Observable<HttpResult<UserInfo>> userLogin(String account, String code) {
        return mApi.userLogin(account, code);
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
