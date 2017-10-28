package com.lepekha.owoxtestapp.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.Constants;
import com.lepekha.owoxtestapp.R;
import com.lepekha.owoxtestapp.Util;
import com.lepekha.owoxtestapp.event.FinishLoadPhoto;
import com.lepekha.owoxtestapp.model.pojo.Photo;
import com.lepekha.owoxtestapp.model.pojo.SearchPhoto;
import com.lepekha.owoxtestapp.model.rest.RequestImpl;
import com.lepekha.owoxtestapp.view.MainActivityImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import butterknife.BindString;
import rx.Observer;

/**
 * Created by Ruslan on 25.10.2017.
 */

public class DownloadPhotosImpl implements DownloadPhotos {

    @Inject
    RequestImpl requestImpl;

    @Inject
    Util util;

    MainActivityImpl view = null;


    public DownloadPhotosImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public void setView(MainActivityImpl view) {
        this.view = view;
    }

    @Override
    public void getPhotosFromAPI(int page, int per_page) {
        view.showProgressBar();
            requestImpl
                    .getPhotos(String.valueOf(page), String.valueOf(per_page))
                    .subscribe(new Observer<List<Photo>>() {

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            errorLoad();
                        }

                        @Override
                        public void onNext(List<Photo> photos) {
                            /**Посылаем событие окончания загрузки + данные которые мы получили в фрагмент MainActivityFragment*/
                            EventBus.getDefault().post(new FinishLoadPhoto(photos));
                            view.hideProgressBar();
                        }
                    });
    }

    @Override
    public void getSearchPhotosFromAPI(String query, int page, int per_page) {
        view.showProgressBar();
        requestImpl
                .searchPhotos(query, String.valueOf(page), String.valueOf(per_page))
                .subscribe(new Observer<SearchPhoto>(){
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ewq","error "+e);
                        errorLoad();
                    }

                    @Override
                    public void onNext(SearchPhoto searchPhotos) {
                        /**Посылаем событие окончания загрузки + данные которые мы получили в фрагмент MainActivityFragment*/
                        Log.e("ewq","data " + searchPhotos.getResults().toString());
                        EventBus.getDefault().post(new FinishLoadPhoto(searchPhotos.getResults()));
                        view.hideProgressBar();
                    }
                });
    }

    @Override
    public List<Photo> prepearPhotoToList(int page, List<Photo> photos) {
        return null;
    }

    @Override
    public void cacheListOfData(List<Photo> photos) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String temp = gson.toJson(photos);
    }

    @Override
    public void errorLoad() {
        /**если ошибка загрузики данных выводим сообщение об ошибке*/
        view.showErrorLoadMessage();
        view.hideProgressBar();
    }


}
