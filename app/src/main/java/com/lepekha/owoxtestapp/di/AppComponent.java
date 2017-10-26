package com.lepekha.owoxtestapp.di;

import com.lepekha.owoxtestapp.model.api.APIhelper;
import com.lepekha.owoxtestapp.model.rest.RequestImpl;
import com.lepekha.owoxtestapp.presenter.DownloadPhotosImpl;
import com.lepekha.owoxtestapp.view.MainActivityFragment;
import com.lepekha.owoxtestapp.view.MainActivityImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ruslan on 12.09.2017.
 */
@Singleton
@Component(modules = {ViewModule.class, ModelModule.class, PresenterModule.class, AppModule.class})
public interface AppComponent {
        void inject(APIhelper apIhelper);
        void inject(RequestImpl requestImpl);
        void inject(DownloadPhotosImpl downloadPhotosImpl);
        void inject(MainActivityImpl mainActivityImpl);
        void inject(MainActivityFragment mainActivityFragment);
}
