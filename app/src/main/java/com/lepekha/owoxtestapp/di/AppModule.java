package com.lepekha.owoxtestapp.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lepekha.owoxtestapp.Util;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ruslan on 12.09.2017.
 */
@Module
public class AppModule {

    private Context appContext;

    public AppModule(@NonNull Context context){
        appContext = context;
    }


    @Provides
    @Singleton
    Context provideContex(){
        return appContext;
    }

    @Provides
    @NonNull
    @Singleton
    public Util provideUtil(Context context){
        return new Util(context);
    }
}
