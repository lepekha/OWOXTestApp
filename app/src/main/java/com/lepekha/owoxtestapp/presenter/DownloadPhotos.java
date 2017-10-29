package com.lepekha.owoxtestapp.presenter;

import com.lepekha.owoxtestapp.view.MainActivityImpl;


/**
 * Created by Ruslan on 25.10.2017.
 */

public interface DownloadPhotos {

    void setView(MainActivityImpl view);
    void getPhotosFromAPI(int page, int per_page);
    void getSearchPhotosFromAPI(String query, int page, int per_page);
    void errorLoad();
}
