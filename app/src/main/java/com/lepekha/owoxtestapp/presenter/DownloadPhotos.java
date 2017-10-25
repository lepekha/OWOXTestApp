package com.lepekha.owoxtestapp.presenter;

import android.provider.ContactsContract;

import com.lepekha.owoxtestapp.model.pojo.Photo;

import java.util.List;


/**
 * Created by Ruslan on 25.10.2017.
 */

public interface DownloadPhotos {
    List<Photo> getPhotosFromAPI(String page, String per_page);
}
