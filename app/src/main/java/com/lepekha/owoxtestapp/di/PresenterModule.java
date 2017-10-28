package com.lepekha.owoxtestapp.di;

import android.support.annotation.NonNull;

import com.lepekha.owoxtestapp.presenter.DownloadPhotosImpl;
import com.lepekha.owoxtestapp.view.MainActivityImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ruslan on 12.09.2017.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    @NonNull
    public DownloadPhotosImpl provideDownloadPhotosImpl(){
        return new DownloadPhotosImpl();
    }
}