package com.lepekha.owoxtestapp.model.cache;

import com.lepekha.owoxtestapp.model.pojo.Photo;

import java.util.List;

/**
 * Created by Ruslan on 20.09.2017.
 */

public interface Preference {
    void savePhotosJson(List<Photo> photos);
    List<Photo> getPhotosFromJson();
    void setUseCache(boolean useCache);
    boolean getUseCache();
}
