package com.dian.commonlib.net;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kennysun on 2019/8/6.
 */

public class IoMainScheduler<T> extends BaseScheduler<T> {
    public IoMainScheduler() {
        super(Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
