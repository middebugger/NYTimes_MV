package com.nytimes.mv.utilities;

import com.nytimes.mv.BuildConfig;
import com.nytimes.mv.model.PopularResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class DataRequest {

    private static final String TAG = DataRequest.class.getSimpleName();
    private ApiInterface apiEndpoints;

    public DataRequest() {
        this.apiEndpoints = RetroClient.createService(ApiInterface.class);
    }

    public Observable<PopularResponse> getMostPopularArticles() {

        Map<String, String> data = new HashMap<>();
        data.put("api-key", BuildConfig.API_KEY);

        return apiEndpoints.getMostPopularArticles(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}