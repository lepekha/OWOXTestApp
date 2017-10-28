package com.lepekha.owoxtestapp.model.rest;

import com.lepekha.owoxtestapp.model.pojo.Photo;
import com.lepekha.owoxtestapp.model.pojo.SearchPhoto;

import java.util.List;

import rx.Observable;

/**
 * Created by Ruslan on 25.10.2017.
 */

public interface Request {
    Observable<List<Photo>> getPhotos(String page, String per_page);
    Observable<SearchPhoto> searchPhotos(String query, String page, String per_page);
}