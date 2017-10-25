package com.lepekha.owoxtestapp.di;

import com.lepekha.owoxtestapp.model.api.APIhelper;
import com.lepekha.owoxtestapp.view.MainActivityImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ruslan on 12.09.2017.
 */
@Singleton
@Component(modules = {ViewModule.class, ModelModule.class})
public interface AppComponent {
        void inject(APIhelper apIhelper);
        void inject(MainActivityImpl mainActivityImpl);
}
