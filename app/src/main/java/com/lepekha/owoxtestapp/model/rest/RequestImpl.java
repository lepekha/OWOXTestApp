package com.lepekha.owoxtestapp.model.rest;

import com.lepekha.owoxtestapp.model.api.APIhelper;
import com.lepekha.owoxtestapp.model.api.RxUtil;
import com.lepekha.owoxtestapp.model.api.UnsplashApi;
import com.lepekha.owoxtestapp.model.pojo.Photo;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Ruslan on 25.10.2017.
 */

public class RequestImpl implements Request {
    @Inject
    APIhelper apiHelper;

    private UnsplashApi unsplashApi;

    public RequestImpl() {
        unsplashApi = apiHelper.getService();
    }

    @Override
    public Observable<List<Photo>> getPhotos(String page, String per_page) {
        return unsplashApi
                .getPhotos(page, per_page)
                .compose(RxUtil.applyIOToMainThreadSchedulers());
    }
}
