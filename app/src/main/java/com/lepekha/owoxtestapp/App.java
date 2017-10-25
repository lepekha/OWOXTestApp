package com.lepekha.owoxtestapp;

import android.app.Application;

import com.lepekha.owoxtestapp.di.AppComponent;
import com.lepekha.owoxtestapp.di.AppModule;
import com.lepekha.owoxtestapp.di.DaggerAppComponent;
import com.lepekha.owoxtestapp.di.ModelModule;
import com.lepekha.owoxtestapp.di.PresenterModule;
import com.lepekha.owoxtestapp.di.ViewModule;

/**
 * Created by Ruslan on 12.09.2017.
 */

public class App extends Application {
    private static AppComponent component;
    public static AppComponent getComponent(){
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponent();
    }

    protected void buildComponent(){
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .modelModule(new ModelModule())
                .presenterModule(new PresenterModule())
                .viewModule(new ViewModule())
                .build();
    }

}
