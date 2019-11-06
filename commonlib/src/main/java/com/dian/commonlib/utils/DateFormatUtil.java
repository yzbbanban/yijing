package com.dian.commonlib.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.dian.commonlib.R;
import com.dian.commonlib.app.Constants;
import com.dian.commonlib.lang.MultiLanguageUtil;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kennysun on 2019/8/6.
 */

public class DateFormatUtil extends SimpleDateFormat {
    public final static String DAY = "day";
    public final static String WEEK = "week";
    public final static String MONTH = "month";
    public final static String QUARTER = "quarter";
    public final static String HALFAYEAR = "halfayear";
    public final static String YEAR = "year";
    private static Locale LOCALE = MultiLanguageUtil.getInstance().getLanguageLocale();
    String[] WEEKS;
    String[] MOMENTS;
    String yesterday;
    String the_day_before_yesterday;
    private static String mFormat = "%s";
    private static String yearFormat = "yyyy-";
    private static String dateFormat = "M-d-";
    private static String timeFormat = "HH:mm";

    public DateFormatUtil(String format, Context context) {
        this(yearFormat, dateFormat, timeFormat);
        this.mFormat = format;
        yesterday = context.getResources().getString(R.string.yesterday);
        the_day_before_yesterday = context.getResources().getString(R.string.the_day_before_yesterday);
        WEEKS = new String[]{
                context.getResources().getString(R.string.Sunday),
                context.getResources().getString(R.string.Monday),
                context.getResources().getString(R.string.Tuesday),
                context.getResources().getString(R.string.Wednesday),
                context.getResources().getString(R.string.Thursday),
                context.getResources().getString(R.string.Friday),
                context.getResources().getString(R.string.Saturday)};
        MOMENTS = new String[]{
                context.getResources().getString(R.string.noon),
                context.getResources().getString(R.string.before_dawn),
                context.getResources().getString(R.string.morning),
                context.getResources().getString(R.string.afternoon),
                context.getResources().getString(R.string.night)};
    }

    public DateFormatUtil(String yearFormat, String dateFormat, String timeFormat) {
        super(String.format(LOCALE, "%s %s %s", yearFormat, dateFormat, timeFormat), LOCALE);
    }

    @Override
    public StringBuffer format(@NonNull Date date, @NonNull StringBuffer toAppendTo, @NonNull FieldPosition pos) {
        StringBuffer mToAppendTo = super.format(date, toAppendTo, pos);
        Calendar otherCalendar = calendar;
        Calendar todayCalendar = Calendar.getInstance();
        int hour = otherCalendar.get(Calendar.HOUR_OF_DAY);
        String[] times = mToAppendTo.toString().split(" ");
        String moment;
        if (hour == 12) {
            moment = MOMENTS[0];
        } else {
            moment = MOMENTS[hour / 6 + 1];
        }
        String timeFormat = moment + times[2];
        String dateFormat = times[1] + timeFormat;
        String yearFormat = times[0] + dateFormat;
        mToAppendTo.delete(0, mToAppendTo.length());

        Boolean yearTemp = todayCalendar.get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR);
        if (yearTemp) {
            int todayMonth = todayCalendar.get(Calendar.MONTH);
            int otherMonth = otherCalendar.get(Calendar.MONTH);
            if (todayMonth == otherMonth) {//表示同一个月
                int temp = todayCalendar.get(Calendar.DATE) - otherCalendar.get(Calendar.DATE);
                switch (temp) {
                    case 0:
                        mToAppendTo.append(timeFormat);
                        break;
                    case 1:
                        mToAppendTo.append(yesterday);
                        mToAppendTo.append(timeFormat);
                        break;
                    case 2:
                        mToAppendTo.append(the_day_before_yesterday);
                        mToAppendTo.append(timeFormat);
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        int dayOfMonth = otherCalendar.get(Calendar.WEEK_OF_MONTH);
                        int todayOfMonth = otherCalendar.get(Calendar.WEEK_OF_MONTH);
                        if (dayOfMonth == todayOfMonth) {//同一周
                            int dayOfWeek = otherCalendar.get(Calendar.DAY_OF_WEEK);
                            if (dayOfWeek != 1) {//判断当前是不是星期日     如想显示为：周日 12:09 可去掉此判断
                                mToAppendTo.append(WEEKS[otherCalendar.get(Calendar.DAY_OF_WEEK) - 1]);
                                mToAppendTo.append(' ');
                                mToAppendTo.append(timeFormat);
                            } else {
                                mToAppendTo.append(dateFormat);
                            }
                        } else {
                            mToAppendTo.append(dateFormat);
                        }
                        break;
                    default:
                        mToAppendTo.append(dateFormat);
                        break;
                }
            } else {
                mToAppendTo.append(dateFormat);
            }
        } else {
            mToAppendTo.append(yearFormat);
        }
        int length = mToAppendTo.length();
        mToAppendTo.append(String.format(LOCALE, mFormat, mToAppendTo.toString()));
        mToAppendTo.delete(0, length);
        return mToAppendTo;
    }

    /**
     * 获取服务器当前时间
     */
    public static Long getMyServiceTime() {
        Long serviceTime = MmkvUtil.decodeLong(Constants.SERVICE_TIME, 0l);
        Long localTime = MmkvUtil.decodeLong(Constants.LOCAL_TIME, 0l);
        Long currentTime = System.currentTimeMillis();
        Long timestamp = (currentTime - localTime) + serviceTime;
        return timestamp;
    }

    /**
     * 保存服务器当前时间
     *
     * @param time
     */
    public static void saveServiceTime(long time) {
        MmkvUtil.encodeLong(Constants.LOCAL_TIME, System.currentTimeMillis());
        MmkvUtil.encodeLong(Constants.SERVICE_TIME, time);
    }

    /**
     * 格式化时间
     */
    public static String timeStamp2Date(String seconds, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (TextUtils.isEmpty(seconds)) {
            return "";
        } else {
            if (seconds.length() <= 10) {
                seconds = seconds + "000";
            }
            return sdf.format(new Date(Long.valueOf(seconds)));
        }
    }

    /**
     * 格式化时间
     *
     * @param seconds
     * @return
     */
    public static String timeStamp2Date(String seconds) {
        return timeStamp2Date(seconds, "yyyy-MM-dd HH:mm:ss");
    }
}
