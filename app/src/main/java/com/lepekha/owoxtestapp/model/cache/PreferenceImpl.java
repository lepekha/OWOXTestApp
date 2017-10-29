package com.lepekha.owoxtestapp.model.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.model.pojo.Photo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ruslan on 20.09.2017.
 */



public class PreferenceImpl implements Preference {

    private static final String APP_PREFERENCES = "owox_test_app_cache";
    private static final String APP_PREFERENCES_JSON = "saved_photos_json";
    private static final String APP_PREFERENCES_USE_CACHE = "use_cache";


    private SharedPreferences mSettings;

    public PreferenceImpl(Context context) {
        App.getComponent().inject(this);
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    /**Сохраняем список фото в виде строки json*/
    @Override
    public void savePhotosJson(List<Photo> photos) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        mSettings.edit().putString(APP_PREFERENCES_JSON, gson.toJson(photos)).apply();
        getPhotosFromJson();
    }

    /**Получаем список фото со строки json*/
    @Override
    public List<Photo> getPhotosFromJson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Photo> photos = new ArrayList<>();
        photos = gson.fromJson(mSettings.getString(APP_PREFERENCES_JSON, ""),new TypeToken<List<Photo>>(){}.getType());
        return photos;
    }

    @Override
    public void setUseCache(boolean useCache) {
        mSettings.edit().putBoolean(APP_PREFERENCES_USE_CACHE, useCache).apply();
    }

    @Override
    public boolean getUseCache() {
        return mSettings.getBoolean(APP_PREFERENCES_USE_CACHE, false);
    }
}

