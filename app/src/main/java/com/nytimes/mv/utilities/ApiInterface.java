package com.nytimes.mv.utilities;


import com.nytimes.mv.model.PopularResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("mostpopular/v2/mostviewed/all-sections/7.json")
    Observable<PopularResponse> getMostPopularArticles(@QueryMap Map<String, String> options);
}