package com.dian.commonlib.utils;

import com.dian.commonlib.net.IoMainScheduler;

/**
 * Created by kennysun on 2019/8/6.
 */

public class SchedulerUtil {
    public  static <T> IoMainScheduler<T> ioToMain() {
        return new IoMainScheduler();
    }
}
