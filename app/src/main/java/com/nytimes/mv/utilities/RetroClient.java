package com.nytimes.mv.utilities;


import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.nytimes.mv.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class RetroClient {

    private static final String TAG = RetroClient.class.getSimpleName();

    static <S> S createService(Class<S> serviceClass) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addNetworkInterceptor(new StethoInterceptor());
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(1, TimeUnit.MINUTES);
        httpClient.readTimeout(1, TimeUnit.MINUTES);
        httpClient.writeTimeout(1, TimeUnit.MINUTES);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.APP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(serviceClass);
    }
}