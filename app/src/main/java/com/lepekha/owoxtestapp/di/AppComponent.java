package com.lepekha.owoxtestapp.di;

import com.lepekha.owoxtestapp.view.ImplMainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ruslan on 12.09.2017.
 */
@Singleton
@Component(modules = {ViewModule.class})
public interface AppComponent {
        void inject(ImplMainActivity implMainActivity);
}
