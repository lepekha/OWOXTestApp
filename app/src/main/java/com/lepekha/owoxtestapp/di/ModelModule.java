package com.lepekha.owoxtestapp.di;

import android.support.annotation.NonNull;

import com.lepekha.owoxtestapp.model.api.APIhelper;
import com.lepekha.owoxtestapp.model.api.UnsplashApi;
import com.lepekha.owoxtestapp.view.MainActivityImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ruslan on 12.09.2017.
 */
@Module
public class ModelModule {

    @Provides
    @Singleton
    @NonNull
    public APIhelper provideAPIhelper(){
        return new APIhelper();
    }
}