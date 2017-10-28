package com.lepekha.owoxtestapp.di;

import android.support.annotation.NonNull;

import com.lepekha.owoxtestapp.presenter.FullScreenMethodImpl;
import com.lepekha.owoxtestapp.view.FullScreenPhotoFragment;
import com.lepekha.owoxtestapp.view.ListPhotosFragment;
import com.lepekha.owoxtestapp.view.MainActivityImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ruslan on 12.09.2017.
 */
@Module
public class ViewModule {

    @Provides
    @Singleton
    @NonNull
    public MainActivityImpl provideMainActivityImpl(){
        return new MainActivityImpl();
    }

    @Provides
    @Singleton
    @NonNull
    public FullScreenPhotoFragment provideFullScreenPhotoFragment(){
        return new FullScreenPhotoFragment();
    }

    @Provides
    @Singleton
    @NonNull
    public ListPhotosFragment provideMainActivityFragment(){
        return new ListPhotosFragment();
    }


}