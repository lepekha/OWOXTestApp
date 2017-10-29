package com.lepekha.owoxtestapp.di;

import com.lepekha.owoxtestapp.model.api.APIhelper;
import com.lepekha.owoxtestapp.model.cache.PreferenceImpl;
import com.lepekha.owoxtestapp.model.rest.RequestImpl;
import com.lepekha.owoxtestapp.presenter.DownloadPhotosImpl;
import com.lepekha.owoxtestapp.presenter.FullScreenMethodImpl;
import com.lepekha.owoxtestapp.view.FullScreenPhotoFragment;
import com.lepekha.owoxtestapp.view.ListPhotosFragment;
import com.lepekha.owoxtestapp.view.MainActivityImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ruslan on 12.09.2017.
 */
@Singleton
@Component(modules = {ViewModule.class, ModelModule.class, PresenterModule.class, AppModule.class})
public interface AppComponent {
        void inject(FullScreenMethodImpl fullScreenMethod);
        void inject(FullScreenPhotoFragment fullScreenPhotoFragment);
        void inject(APIhelper apIhelper);
        void inject(RequestImpl requestImpl);
        void inject(PreferenceImpl preferenceImpl);
        void inject(DownloadPhotosImpl downloadPhotosImpl);
        void inject(MainActivityImpl mainActivityImpl);
        void inject(ListPhotosFragment listPhotosFragment);
}
