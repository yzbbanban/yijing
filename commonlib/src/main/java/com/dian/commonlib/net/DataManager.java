package com.dian.commonlib.net;


import com.dian.commonlib.app.App;
import com.dian.commonlib.app.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kennysun on 2019/8/26.
 */

public class DataManager<T> {
    protected final T mApi;

    public DataManager(String baseUrl, Class<T> cls) {
        HostInterceptor interceptor = new HostInterceptor(App.mApp);
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        OkHttpClient okHttpClient = clientBuilder.addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // retrofit 용 rxjava2 adapter
                .client(okHttpClient)
                .build();
        mApi = retrofit.create(cls);
    }

}
