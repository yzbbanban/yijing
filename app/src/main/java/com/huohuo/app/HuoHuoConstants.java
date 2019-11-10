package com.huohuo.app;

/**
 * Created by kennysun on 2019/8/27.
 */

public class HuoHuoConstants {

    public static final float BANNER_RATE = 4.5f;//banner大小比例
    public static final float ASSET_BG_RATE = 2.5f;//首页资产背景大小比例

    public static final long CUTDOWN_TIME = 60000;//发送短信倒计时
    //socket
    public static String SOCKET_TYPE = "socket_type";
    public static String SOCKET_INIT = "socket_init";
    public static String SOCKET_RETY = "socket_rety";


    public static final String SAVE_IMG_PATH = "huohuo";        //保存图片地址
    public static final String TYPE = "type";
    public static final String COINID = "coinid";
    public static final String COINADDRESS = "coinaddress";
    public static final String PIC_PATH = "com.yj.fileprovider";       //拍照的存储路径
    public static final String LOCAL_LOCK = "localLock";        //本地指纹验证

    //login
    public static final String GESTURE_LOCK = "gestureLock";    //手势锁
    public static final String CLASSNAME = "classname";
    public static final String PHONE = "phone";
    public static final String COUNTRY_CODE = "country_code";

    //指纹相关
    public static final String JSONVALUE = "jsonValue";        //腾讯指纹jsonValue
    public static final String SIGNATURE = "signature";        //腾讯指纹签名
    public static final String IS_SUPPORT = "isSupport";        //是否支持腾讯指纹

    //好友列表
    public static final String ZHUAN_ZHANG = "zhuan_zhang";//转账类型
    public static final String FRIENDID = "friendid";//
    public static final String NAME = "name";//
    public static final String USER_ID = "user_id";//
    public static final String FRIEND_REMARK = "friend_remark";//
    public static final String USE_NO_DISTURB = "use_no_disturb";//
    public static final String AVATAR = "avatar";//

    //精度相关
    public static int UPDOWN_JINGDU = 2;//涨跌幅精度
    public static int RMB_JINGDU = 6;//人民币精度
    public static int COIN_JINGDU = 8;//虚拟币精度

    //scan
    public static final String SCAN_RESULT = "scan_result";
    public static String BTW_TYPE = "btw_type";//生成个人二维码type
    public static String ADD_FRIEND = "addfriend";////生成个人二维码type的value
    public static String APPLY_FRIEND = "apply_friend";////生成个人二维码type的value
    public static String MEMO = "memo";//扫码备注

    //支付弹窗相关start
    public static String PAY_TYPE = "pay_type";//支付类型
    public static String PAY_TITLE = "pay_title";
    public static String PAY_COIN_COUNT = "pay_coin_count";
    public static String PAY_RATE = "pay_rate";
    public static String PAY_TARGET = "pay_target";
    public static String PAY_COIN_LOGO = "pay_coin_logo";
    public static String PAY_COIN_NAME = "pay_coin_name";
    //支付弹窗相关end

    /**
     * 好友申请回复类型 1:同意 2:删除消息
     */
    public static final int REPLY_FRIEND_APPLY_AGREE = 1;
    public static final int REPLY_FRIEND_APPLY_REFUSE = 2;




    /**
     * 聊天模块
     */
    public static final String CONTRACT_NEW_APPLY = "contract_new_apply";//通讯录是否有新的好友申请红点
    //操作类型 0：新消息，1：发消息，2：更新chatactivity消息
    public static final int HANDTYPE_NEW = 0;
    public static final int HANDTYPE_SEND = 1;
    public static final int HANDTYPE_UPDATE = 2;
    public static long TIME_INTERVAL = 300000;//5分钟
    public static final int sending = 0x746;// 消息的发送状态
    public static final int sended = 0x747;// 消息的发送状态
    public static final int sendFail = 0x748;// 消息的发送状态
    //发红包类型(1一对一；2拼手气；3均分)
    public static final int HB_ONE_TO_ONE = 1;
    public static final int HB_LUCK = 2;
    public static final int HB_AVERAGE = 3;
    public static String REDPACKET_ID = "redPacketId";
    public static String REDPACKET_TYPE = "redPacketType";
    public static String BLESSINGS = "blessings";
    public static int HB_SHOU = 0x1234;
    public static int HB_FA = 0x2345;
    public static final int HB_TIMEOUT = 501;//红包领取超时
    public static final int HB_QIANG_WAN = 502;//红包领取超时
    /**
     * （-10账号在异地登陆  -2已经不是好友了 -1消息发送失败 0心跳  1聊天消息 2好友添加成功 3红包消息 4图片消息10消息确认  13已领取红包 20好友申请 22新好友申请，在会话列表展示）
     * 卡券消息100，回执99
     * <p>
     * 群：（9--发起群聊）
     */
    public static final String BTW_MESSAGE_WITHDRAW = "btw.message-withdraw";//撤回消息标志
    public static final int NOTIFY_MESSAGE_ACK = 97;//@消息确认
    public static final int NOTIFY_USER = 15;//@用户
    public static final int NOTIFY_ALL = 16;//@所有人
    public static final int MESSAGE_WITHDRAWAL = 98;//消息撤回
    public static final int WITHDRAWAL_OVER_TIME = -13;//撤回消息超过时间限制
    public static final int WITHDRAWAL_FAIL = -12;//撤回失败
    public static final int QUIT_GROUP_CHAT = -11;//退出群聊
    public static final int GROUP_BANNED = -9;//群组禁言
    public static final int PERSONAL_BANNED = -8;//个人禁言
    public static final int DISBAND_GROUP = -7;//解散群组
    public static final int SHIFT_OUT_GROUP = -6;//移除群聊通知
    public static final int NOT_IN_GROUP = -5;//不在群组中
    public static final int BE_BANNED = -4;//已禁言
    public static final int GROUP_NOT_EXIST = -3;//群组不存在
    public static final int GROUP_CHARACTERS = 5;//群聊普通文字消息
    public static final int JOIN_GROUP = 6;//加入群聊
    public static final int GROUP_RED_PACKET = 7;//群红包消息
    public static final int GROUP_SEND_PICTURE = 8;//群聊发送图片
    public static final int CREATE_GROUP = 9;//创建群聊
    public static final int GROUP_MESSAGE_SUCCESS = 11;//群消息发送成功回执
    public static final int GROUP_NEWS_RECEIPT = 12;//系统消息回执（不保存）
    public static final int UPDATE_GROUP_NAME = 14;//修改群名称
    public static final int GROUP_RECEIVE_RED_PACKET = 17;//红包领取推送
    public static final int REMOVE_GROUP_BANNED = 18;//移除群组禁言
    public static final int REMOVE_PERSONAL_BANNED = 19;//移除个人禁言
    public static final int NEW_INVITE = 21;//邀请信息
    public static final int HB_MESSAGE_SYS = 32;
    public static final int KL_HB = 1000;
    public static final int KL_HB_SURE = 1001;
    public static final int KL_HB_ERRPR = 1002;
    public static final int COUPON_MESSAGE = 100;
    public static final int COUPON_SURE = 99;
    public static final int ACCOUNT_OTHER_LOGIN = -10;
    public static final int NOT_FRIEND = -2;
    public static final int SEND_FAILE = -1;
    public static final int MESSAGE_HEART = 0;
    public static final int TEXT_MESSAGE = 1;
    public static final int ADD_SUCCESS = 2;
    public static final int HONGBAO_MESSAGE = 3;
    public static final int IMG_MESSAGE = 4;
    public static final int MESSAGE_SURE = 10;
    public static final int MESSAGE_HB_SYS_SURE = 24;
    public static final int HONGBAO_LQ = 13;
    public static final int APPLY = 20;
    public static final int NEW_APPLY = 22;
    public static final int CREATE_GROUP_CHAT = 0x9375;//邀请加入群聊的消息类型



    /**
     * 跳转登录页的几种类型
     * NORMAL                   正常登录
     * CHANGE_ACCOUNT           切换账号登录  （登录验证指纹或手势锁）
     * FORGET_GETTURE_LOCK      忘记手势锁密码（登录时验证手势锁）
     * FINGERPRINT_ERROR_MORE  指纹错误过多  （登录时验证指纹）
     */
    public enum LoginType {
        NORMAL, CHANGE_ACCOUNT, FORGET_GETTURE_LOCK, FINGERPRINT_ERROR_MORE, THIRD_PAY;
    }

    /**
     * 弹窗输入支付密码类型(好友转账、地址转账 、发红包 、转入存钱罐、转出存钱罐、实名认证验证支付密码，开启指纹)
     * 使用枚举id作为参数时需要+1
     */
    public enum PayType {
        FRIEND_TRANSFER_COIN, ADDRESS_TRANSFER_COIN, FA_HONG_BAO, IN_CQG, OUT_CQG, AUTH, AUTHENTICATION, GAMEGO, THIRD_PAY

    }


    /**
     * 设置密码页面相关类型
     */
    public enum PayPwdType {
        set, update, find_sms, find_card, find_live
    }




}
