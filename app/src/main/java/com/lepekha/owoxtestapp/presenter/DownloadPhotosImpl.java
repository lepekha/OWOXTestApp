package com.lepekha.owoxtestapp.presenter;

import android.util.Log;

import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.model.pojo.Photo;
import com.lepekha.owoxtestapp.model.rest.RequestImpl;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by Ruslan on 25.10.2017.
 */

public class DownloadPhotosImpl implements DownloadPhotos {

    @Inject
    RequestImpl requestImpl;

    public DownloadPhotosImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public List<Photo> getPhotosFromAPI(String page, String per_page) {
        requestImpl
                .getPhotos(page, per_page)
                .subscribe(new Observer<List<Photo>>(){

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Photo> photos) {
                        Log.i("QWE", photos.get(1).getUrls().getRegular()+"");
                    }
                });

        return null;
    }
}
