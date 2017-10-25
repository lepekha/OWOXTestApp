package com.lepekha.owoxtestapp.model.rest;

import com.lepekha.owoxtestapp.model.pojo.Photo;

import java.util.List;

import rx.Observable;

/**
 * Created by Ruslan on 25.10.2017.
 */

public interface Request {
    Observable<List<Photo>> getPhotos(String page, String per_page);
}