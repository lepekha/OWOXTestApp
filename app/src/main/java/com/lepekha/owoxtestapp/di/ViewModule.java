package com.lepekha.owoxtestapp.di;

import android.support.annotation.NonNull;

import com.lepekha.owoxtestapp.view.ImplMainActivity;

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
    public ImplMainActivity provideImplMainActivity(){
        return new ImplMainActivity();
    }
}