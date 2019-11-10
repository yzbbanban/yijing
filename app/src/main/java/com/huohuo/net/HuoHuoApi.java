package com.huohuo.net;

import com.dian.commonlib.app.Constants;
import com.dian.commonlib.net.HttpResult;
import com.huohuo.dao.table.Friend;
import com.huohuo.dao.table.FriendApply;
import com.huohuo.dao.table.Group;
import com.huohuo.mvp.model.bean.AboutBean;
import com.huohuo.mvp.model.bean.ContractsBean;
import com.huohuo.mvp.model.bean.CountryCodeBean;
import com.huohuo.mvp.model.bean.JiYanData;
import com.huohuo.mvp.model.bean.ScanBean;
import com.huohuo.mvp.model.bean.SplashBean;
import com.huohuo.mvp.model.bean.TokenBean;
import com.huohuo.mvp.model.bean.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kennysun on 2019/8/26.
 */

public interface HuoHuoApi {

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
