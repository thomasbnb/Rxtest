package com.thomas.rxtextapplication.retrofit;

import com.thomas.rxtextapplication.bean.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by thomas on 16/6/17.
 */
public interface MovieService {

    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
