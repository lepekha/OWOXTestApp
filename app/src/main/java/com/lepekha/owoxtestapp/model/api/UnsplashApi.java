package com.lepekha.owoxtestapp.model.api;

import com.lepekha.owoxtestapp.model.pojo.Photo;
import com.lepekha.owoxtestapp.model.pojo.SearchPhoto;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ruslan on 24.10.2017.
 */

public interface UnsplashApi {
    @Headers({
            "Authorization: Client-ID 4275b644cd96756e752c6d8ef8ca40d8352537f092fc4f9433bde060008e139e",
            "Accept-Version: v1"
    })
    @GET("photos")
    Observable<List<Photo>> getPhotos(@Query("page") String page, @Query("per_page") String per_page);

    @Headers({
            "Authorization: Client-ID 4275b644cd96756e752c6d8ef8ca40d8352537f092fc4f9433bde060008e139e",
            "Accept-Version: v1"
    })
    @GET("search/photos")
    Observable<SearchPhoto> searchPhotos(@Query("query") String query, @Query("page") String page, @Query("per_page") String per_page);
}
