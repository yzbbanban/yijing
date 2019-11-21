package com.yjb.net;

import com.dian.commonlib.app.Constants;
import com.dian.commonlib.net.HttpResult;
import com.yjb.mvp.model.bean.AcMyList;
import com.yjb.mvp.model.bean.ActivityList;
import com.yjb.dao.table.Friend;
import com.yjb.dao.table.FriendApply;
import com.yjb.dao.table.Group;
import com.yjb.mvp.model.bean.ArticleList;
import com.yjb.mvp.model.bean.ExchangeList;
import com.yjb.mvp.model.bean.FengcaiList;
import com.yjb.mvp.model.bean.HomeYjRank;
import com.yjb.mvp.model.bean.JFInfo;
import com.yjb.mvp.model.bean.MallList;
import com.yjb.mvp.model.bean.NewsList;
import com.yjb.mvp.model.bean.RankList;
import com.yjb.mvp.model.bean.UserInfo;
import com.yjb.mvp.model.bean.AboutBean;
import com.yjb.mvp.model.bean.ContractsBean;
import com.yjb.mvp.model.bean.CountryCodeBean;
import com.yjb.mvp.model.bean.JiYanData;
import com.yjb.mvp.model.bean.ScanBean;
import com.yjb.mvp.model.bean.SplashBean;
import com.yjb.mvp.model.bean.TokenBean;
import com.yjb.mvp.model.bean.User;
import com.yjb.mvp.model.bean.YjTeam;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by kennysun on 2019/8/26.
 */

public interface HuoHuoApi {


    /**
     * 发送短信
     *
     * @return
     */
    @GET(Constants.API_VERSION + "/sms/send")
    Observable<HttpResult<Object>> smsSend(
            @Query("mobile") String mobile,
            @Query("event") String event
    );


    /**
     * 验证码登录
     *
     * @param mobile
     * @param captcha
     * @return
     */
    @GET(Constants.API_VERSION + "/user/mobilelogin")
    Observable<HttpResult<UserInfo>> userLogin(
            @Query("mobile") String mobile,
            @Query("captcha") String captcha
    );


    /**
     * 义警活动列表
     *
     * @param page
     * @param size
     * @param type
     * @return
     */
    @POST(Constants.API_VERSION + "/activity/list")
    Observable<HttpResult<ActivityList>> activityList(
            @Query("token") String token,
            @Query("page") String page,
            @Query("size") String size,
            @Query("type") String type
    );

    /**
     * 首页义警排名
     *
     * @return
     */
    @POST(Constants.API_VERSION + "/apply/rank")
    Observable<HttpResult<List<HomeYjRank>>> homeYjRank(
            @Query("token") String token
    );

    /**
     * 我的，积分显示
     *
     * @return
     */
    @POST(Constants.API_VERSION + "/apply/info")
    Observable<HttpResult<JFInfo>> jFinfo(
            @Query("token") String token,
            @Query("user_id") String user_id
    );

    /**
     * 月排行
     *
     * @return
     */
    @POST(Constants.API_VERSION + "/apply/rankmonth")
    Observable<HttpResult<RankList>> rankMonth(
            @Query("token") String token,
            @Query("page") String page,
            @Query("size") String size
    );

    /**
     * 义警队伍
     *
     * @return
     */
    @POST(Constants.API_VERSION + "/teammgt/list")
    Observable<HttpResult<YjTeam>> yjTeam(
            @Query("token") String token,
            @Query("page") String page,
            @Query("size") String size
    );

    /**
     * 年排行
     *
     * @return
     */
    @POST(Constants.API_VERSION + "/apply/rankyear")
    Observable<HttpResult<RankList>> rankYear(
            @Query("token") String token,
            @Query("page") String page,
            @Query("size") String size
    );


    /**
     * 义警风采
     *
     * @param page
     * @param size
     * @param size
     * @return
     */
    @POST(Constants.API_VERSION + "/fengcai/list")
    Observable<HttpResult<FengcaiList>> fengcaiList(
            @Query("token") String token,
            @Query("page") String page,
            @Query("size") String size
    );

    /**
     * 文件上传
     *
     * @param token
     * @param file
     * @return
     */
    @Multipart
    @POST(Constants.API_VERSION + "/common/upload")
    Observable<HttpResult<String>> commonUpload(
            @Part("token") String token,
            @Part RequestBody file
    );

    /**
     * 活动报名签到
     *
     * @return
     */
    @POST(Constants.API_VERSION + "/activity/signup")
    Observable<HttpResult<String>> activitySignUp(
            @Query("token") String token,
            @Query("activity_id") String activity_id,
            @Query("user_id") String user_id,
            @Query("teammgt_id") String teammgt_id
    );

    /**
     * 活动签退
     *
     * @return
     */
    @POST(Constants.API_VERSION + "/activity/sign")
    Observable<HttpResult<String>> activitySignOut(
            @Query("token") String token,
            @Query("fakeid") String fakeid,
            @Query("activity_id") String activity_id
    );

    /**
     * 商城列表
     *
     * @param token
     * @param page
     * @param size
     * @param user_id
     * @return
     */
    @POST(Constants.API_VERSION + "/mall/list")
    Observable<HttpResult<MallList>> mallList(
            @Query("token") String token,
            @Query("page") String page,
            @Query("size") String size,
            @Query("user_id") String user_id

    );

    /**
     * 义警申请
     *
     * @return
     */
    @POST(Constants.API_VERSION + "apply/apply")
    Observable<HttpResult<String>> yjApply(
            @Query("token") String token,
            @Query("user_id") String user_id,
            @Query("vision") String vision,
            @Query("photoimage") String photoimage,
            @Query("homeaddress") String homeaddress,
            @Query("politically") String politically,
            @Query("identifier") String identifier,
            @Query("job") String job,
            @Query("nickname") String nickname,
            @Query("gender") String gender,
            @Query("birthday") String birthday

    );


    /**
     * 兑换列表
     *
     * @param page
     * @param size
     * @param size
     * @param user_id
     * @return
     */
    @POST(Constants.API_VERSION + "/exchange/list")
    Observable<HttpResult<ExchangeList>> exchangeList(
            @Query("token") String token,
            @Query("page") String page,
            @Query("size") String size,
            @Query("user_id") String user_id

    );

    /**
     * 新闻列表
     *
     * @param page
     * @param size
     * @param size
     * @return
     */
    @POST(Constants.API_VERSION + "/news/list")
    Observable<HttpResult<NewsList>> newsList(
            @Query("token") String token,
            @Query("page") String page,
            @Query("size") String size
    );

    /**
     * 新闻列表
     *
     * @param page
     * @param size
     * @param size
     * @return
     */
    @POST(Constants.API_VERSION + "/activity/mylist")
    Observable<HttpResult<AcMyList>> acMyList(
            @Query("token") String token,
            @Query("page") String page,
            @Query("size") String size,
            @Query("user_id") String user_id
    );

    /**
     * 轮播新闻
     *
     * @param page
     * @param size
     * @param size
     * @return
     */
    @POST(Constants.API_VERSION + "/Article/list")
    Observable<HttpResult<ArticleList>> articleLlist(
            @Query("token") String token,
            @Query("page") String page,
            @Query("size") String size
    );

    /**
     * 新闻详情自增
     *
     * @param token
     * @param id
     * @return
     */
    @POST(Constants.API_VERSION + "/news/viewinc")
    Observable<HttpResult<Object>> newsDetailIncr(
            @Query("token") String token,
            @Query("id") String id
    );


    /**
     * 发送添加好友申请
     *
     * @param friendUid
     * @return
     */
    @POST("v1/personalFriend/sendFriendApplication")
    Observable<HttpResult<Object>> addFriend(
            @Query("friendUid") String friendUid,
            @Query(value = "remark", encoded = true) String remark
    );

    /**
     * 获取手机联系人列表
     *
     * @param mobile
     * @return
     */
    @POST("v1/personalFriend/listUserInfoByMobile")
    Observable<HttpResult<List<Friend>>> mobileList(
            @Query("mobile") String mobile
    );

    /**
     * 手机联系人  邀请短信内容
     *
     * @return
     */
    @GET("v1/personalFriendSearch/getMessage")
    Observable<HttpResult<String>> getSmsContent();


    /**
     * 通过手机号或用户id查询搜索好友
     *
     * @return
     */
    @GET("v1/personalFriend/getUserInfoByMobile")
    Observable<HttpResult<Friend>> getSearchFriendInfo(
            @Query("friendUid") String friendUid,
            @Query("mobile") String mobile
    );

    /**
     * 扫描二维码、或者输入一段字符串，判断字符串类型（内部币种地址、外部币种地址、其他字符串）
     *
     * @param code
     * @return
     */
    @POST("v2/scan/send")
    Observable<HttpResult<ScanBean>> getCoinAddressType(
            @Query("code") String code
    );


    /**
     * 转币页面扫码专用
     * 扫描二维码、或者输入一段字符串，判断字符串类型（内部币种地址、外部币种地址、其他字符串）
     *
     * @param code
     * @return
     */
    @POST("v1/scan/transSend ")
    Observable<HttpResult<ScanBean>> getTransSend(
            @Query("code") String code
    );

    /**
     * 解密userid
     *
     * @param id
     * @return
     */
    @GET("v1/personalFriend/decoding")
    Observable<HttpResult<String>> decodingUserid(
            @Query("id") String id
    );

    /**
     * 获取加密的userid,用在生成自己的二维码
     *
     * @return
     */
    @GET("v1/personalFriendInfo/getInfo")
    Observable<HttpResult<String>> getJiamiUserId();

    /**
     * 回复好友申请
     *
     * @param friendUid
     * @param replyType 好友申请回复类型 1:同意 2:删除消息
     * @return
     */
    @POST("v2/personalFriend/replyApply")
    Observable<HttpResult<Object>> replyApply(
            @Query("friendUid") String friendUid,
            @Query("replyType") int replyType
    );

    /**
     * 获取好友申请列表
     *
     * @return
     */
    @GET("v1/personalFriend/listFriendApply")
    Observable<HttpResult<List<FriendApply>>> listFriendApply();

    /**
     * 查看个人群列表
     *
     * @return
     */
    @GET("v1/personalGroupQuery/groups")
    Observable<HttpResult<List<Group>>> getGroups();

    /**
     * 获取好友列表
     *
     * @return
     */
    @GET("v1/personalFriend/listMobileLinkMan")
    Observable<HttpResult<ContractsBean>> getFriends();

    /**
     * 验证旧密码是否正确/验证交易密码是否正确
     *
     * @param payPass
     * @return
     */
    @POST("v1/personalUserInfo/checkPayPass")
    Observable<HttpResult<Object>> checkPayPwd(
            @Query("payPass") String payPass
    );

    /**
     * 获取关于app相关信息
     *
     * @return
     */
    @GET("v1/init/appAbout")
    Observable<HttpResult<AboutBean>> about();

    /**
     * 手机号注册
     *
     * @param countryCode
     * @param mobile
     * @param valiRegisterCode
     * @return
     */
    @POST("v2/personalUserAccount/registerUser")
    Observable<HttpResult<TokenBean>> regist(
            @Query("countryCode") String countryCode,
            @Query("mobile") String mobile,
            @Query("valiRegisterCode") String valiRegisterCode
    );

    /**
     * 获取注册短信验证码
     *
     * @param countryCode
     * @param mobile
     * @return
     */
    @GET("v1/sendSmsCode/sendRegisterSms")
    Observable<HttpResult<Object>> getRegistCode(
            @Query("countryCode") String countryCode,
            @Query("mobile") String mobile
    );

    /**
     * 获取国家代码
     *
     * @return
     */
    @GET("v1/init/areaCode")
    Observable<HttpResult<List<CountryCodeBean>>> getCountryCode();

    /**
     * 极验
     *
     * @return
     */
    @GET("v1/verifyCode/init")
    Observable<HttpResult<JiYanData>> jiYanApi1();

    /**
     * 极验二次验证
     *
     * @return
     */
    @POST("v1/verifyCode/verify")
    Observable<HttpResult<Object>> jiYanApi2(
            @Query("challenge") String challenge,
            @Query("validate") String validate,
            @Query("username") String userName,
            @Query("countryCode") String countryCode,
            @Body RequestBody seccode
    );


    /**
     * 退出登录
     *
     * @return
     */
    @GET(Constants.API_VERSION + "/personalUserAccount/logout")
    Observable<HttpResult<Object>> userExit();

    /**
     * 获取登录验证码
     *
     * @param countryCode
     * @param mobile
     * @return
     */
    @GET(Constants.API_VERSION + "/sendSmsCode/sendLoginSms")
    Observable<HttpResult<Object>> sendLoginSms(
            @Query("countryCode") String countryCode,
            @Query("mobile") String mobile
    );


    /**
     * 验证码登录
     *
     * @param countryCode
     * @param mobile
     * @param valiRegisterCode
     * @return
     */
    @GET("v2/personalUserAccount/dynamicLogin")
    Observable<HttpResult<TokenBean>> codeLogin(
            @Query("countryCode") String countryCode,
            @Query("mobile") String mobile,
            @Query("valiRegisterCode") String valiRegisterCode
    );

    /**
     * 启动页
     *
     * @return
     */
    @GET(Constants.API_VERSION + "/page/startInfo")
    Observable<HttpResult<SplashBean>> splash();

    /**
     * 获取服务器时间（毫秒）
     *
     * @return
     */
    @GET(Constants.API_VERSION + "/init/timestamp")
    Observable<HttpResult<Long>> getServiceTime();

    /**
     * 获取用户信息
     *
     * @return
     */
    @POST(Constants.API_VERSION + "/personalUserInfo/selectUserInfo")
    Observable<HttpResult<User.UserInfo>> getUserInfo();

    /**
     * 获取用户账户信息
     *
     * @return
     */
    @GET(Constants.API_VERSION + "/personalUserAccount/selectAccount")
    Observable<HttpResult<User.UserAccount>> getUserAccount();

    /**
     * 获取用户安全设置
     *
     * @return
     */
    @GET(Constants.API_VERSION + "/personalUserSet/selectSafeSet")
    Observable<HttpResult<User.UserSafeSet>> getUserSafeSet();

    /**
     * 获取用户隐私设置
     *
     * @return
     */
    @GET(Constants.API_VERSION + "/personalUserSet/selectPrivacySet")
    Observable<HttpResult<User.UserPrivacySet>> getUserPrivacySet();

    /**
     * 获取用户通用设置
     *
     * @return
     */
    @GET(Constants.API_VERSION + "/personalUserSet/selectCommonSet")
    Observable<HttpResult<User.UserCommonSet>> getUserCommonSet();


}
