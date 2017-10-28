package com.lepekha.owoxtestapp.presenter;

import android.provider.ContactsContract;

import com.lepekha.owoxtestapp.model.pojo.Photo;
import com.lepekha.owoxtestapp.model.pojo.SearchPhoto;
import com.lepekha.owoxtestapp.view.MainActivityImpl;

import java.util.List;


/**
 * Created by Ruslan on 25.10.2017.
 */

public interface DownloadPhotos {
    void setView(MainActivityImpl view);
    void getPhotosFromAPI(int page, int per_page);
    void getSearchPhotosFromAPI(String query, int page, int per_page);
    List<Photo> prepearPhotoToList(int page, List<Photo> photos);
    void cacheListOfData(List<Photo> photos);
    void errorLoad();
}
