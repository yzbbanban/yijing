package com.yjb.mvp.presenter.msg;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.dian.commonlib.net.RxHttpCallback;
import com.dian.commonlib.net.exception.ErrorStatus;
import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.SchedulerUtil;
import com.dian.commonlib.utils.ValidateUtil;
import com.yjb.app.HuoHuoApp;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.dao.table.Friend;
import com.yjb.mvp.contract.msg.PhoneFriendContract;
import com.yjb.mvp.model.bean.PhoneInfoBean;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by kennysun on 2019/9/4.
 */

public class PhoneFriendPresenter extends HuoHuoBasePresenter<PhoneFriendContract.View> implements PhoneFriendContract.Presenter {
    private volatile List<PhoneInfoBean> phoneInfoBeans;

    @Override
    public List<PhoneInfoBean> loadPhoneContract() {
        List<PhoneInfoBean> list = new ArrayList<>();
        Cursor cursor;
        try {
            cursor = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{"display_name", "sort_key", "contact_id",
                            "data1"}, null, null, null);
//        moveToNext方法返回的是一个boolean类型的数据
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    //读取通讯录的姓名
                    String name = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    //读取通讯录的号码
                    String number = cursor.getString(cursor
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    int Id = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                    String Sortkey = getSortkey(cursor.getString(1));
                    PhoneInfoBean phoneInfo = new PhoneInfoBean(name, number, Sortkey, Id);
                    list.add(phoneInfo);
                }
                cursor.close();
            }
        } catch (Exception e) {
            handException(e, ErrorStatus.UNKNOWN_ERROR);
        }
        return list;
    }

    @Override
    public void loadPhoneFriend() {
        Observable.create((ObservableOnSubscribe<StringBuffer>) emitter -> {
            phoneInfoBeans = loadPhoneContract();//获取手机通讯录列表
            StringBuffer stringBuffer = new StringBuffer();
            for (PhoneInfoBean phoneInfo : phoneInfoBeans) {
                String number = phoneInfo.getNumber();
                number = number.replaceAll("\\s+", "").replaceAll("-", "");
                stringBuffer.append(number).append(",");
            }
            emitter.onNext(stringBuffer);
        }).flatMap(stringBuffer -> dataManager.mobileList(stringBuffer.toString())).flatMap(listHttpResult -> {
            int code = listHttpResult.getCode();
            List<Friend> friendList = new ArrayList<>();//自己组装的本地通讯录和接口合并的好友
            if (code == 200) {
                List<Friend> data = listHttpResult.getData();  //接口返回的通讯录中注册BTW的好友
                //遍历本地通讯录列表
                for (PhoneInfoBean phoneInfoBean : phoneInfoBeans) {
                    //是否是BTW用户
                    boolean hasBtw = false;
                    for (Friend friend : data) {
                        //是BTW用户
                        //判断是不是自己
                       if (!Objects.equals(friend.getId(), friend.getFriendUid())){
                           if (phoneInfoBean.getNumber().equals(friend.getMobile())) {
                               //BTW用户用#代替首字母
                               friend.setRealName(phoneInfoBean.getName());
                               friend.setFirstChar("#");
                               friend.setRegister(true);
                               friendList.add(friend);
                               hasBtw = true;
                               break;
                           }
                       }
                    }
                    //不是BTW用户
                    if (!hasBtw) {
                        Friend friend = new Friend();
                        //处理用户昵称或备注，转成对应的大写拼音
                        String name = phoneInfoBean.getName();
                        String letter;
                        if (TextUtils.isEmpty(name)) {
                            name = "☆";
                        }
                        if (ValidateUtil.isHanzi(name.charAt(0))) {//汉字
                            String[] strings = PinyinHelper.toHanyuPinyinStringArray(name.charAt(0));
                            String string = strings[0]; //可能有多音字，默认取第一个汉字的拼音
                            letter = String.valueOf(string.charAt(0)).toUpperCase();
                        } else if (ValidateUtil.isLetter(name.charAt(0))) {//字母
                            letter = String.valueOf(name.charAt(0)).toUpperCase();
                        } else {//特殊符号
                            letter = String.valueOf("☆");
                        }
                        friend.setFirstChar(letter);
                        friend.setRealName(phoneInfoBean.getName());
                        friend.setMobile(phoneInfoBean.getNumber());
                        friend.setRegister(false);
                        friendList.add(friend);
                    }
                }
                Collections.sort(friendList);
            } else {
                handApiException(listHttpResult);
            }
            return Observable.just(friendList);
        }).compose(SchedulerUtil.ioToMain())
                .subscribe(list -> {
                    LogUtil.d("list======" + HuoHuoApp.gson.toJson(list));
                    List<String> customLetters = new ArrayList<>();
                    HashMap<String, Integer> letters = new HashMap<>();
                    int position = 0;
                    for (Friend friend : list) {
                        String letter = friend.getFirstChar();
                        //如果没有这个key则加入并把位置也加入
                        if (!letters.containsKey(letter)) {
                            letters.put(letter, position);
                            customLetters.add(letter);
                        }
                        position++;
                    }
                    getMvpView().phoneFriend(list, customLetters, letters);
                }, throwable -> handException(throwable, ErrorStatus.UNKNOWN_ERROR));
    }


    @Override
    public void getSmsContent() {
        doRequestToMain(dataManager.getSmsContent(), false)
                .subscribeWith(new RxHttpCallback<String>(this) {
                    @Override
                    public void onData(String data) {
                        getMvpView().smsContent(data);
                    }
                });
    }

    @Override
    public void addFriend(String fId) {
        doRequestToMain(dataManager.addFriend(fId, ""))
                .compose(SchedulerUtil.ioToMain())
                .subscribeWith(new RxHttpCallback<Object>(this) {
                    @Override
                    public void onData(Object data) {
                        getMvpView().addFriendSendSuccess();
                    }
                });
    }

    private static String getSortkey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        } else
            return "#";
    }
}
