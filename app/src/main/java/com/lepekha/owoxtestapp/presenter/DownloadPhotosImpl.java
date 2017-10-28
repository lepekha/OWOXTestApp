package com.lepekha.owoxtestapp.presenter;

import android.util.Log;

import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.event.FinishLoadPhoto;
import com.lepekha.owoxtestapp.model.cache.ImplPreference;
import com.lepekha.owoxtestapp.model.pojo.Photo;
import com.lepekha.owoxtestapp.model.pojo.SearchPhoto;
import com.lepekha.owoxtestapp.model.rest.RequestImpl;
import com.lepekha.owoxtestapp.view.MainActivityImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by Ruslan on 25.10.2017.
 */

public class DownloadPhotosImpl implements DownloadPhotos {

    public static final int NEW_PHOTOS = 0;
    public static final int SEARCH = 1;
    public static final int PER_PAGE = 30;

    @Inject
    RequestImpl requestImpl;

    @Inject
    ImplPreference cache;

    private MainActivityImpl view = null;

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
                            EventBus.getDefault().post(new FinishLoadPhoto(cache.getPhotosFromJson()));
                            errorLoad();
                        }

                        @Override
                        public void onNext(List<Photo> photos) {
                            /**Посылаем событие окончания загрузки + данные которые мы получили в фрагмент ListPhotosFragment*/
                            cache.savePhotosJson(photos);
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
                        EventBus.getDefault().post(new FinishLoadPhoto(cache.getPhotosFromJson()));
                        errorLoad();
                    }

                    @Override
                    public void onNext(SearchPhoto searchPhotos) {
                        cache.savePhotosJson(searchPhotos.getResults());
                        /**Посылаем событие окончания загрузки + данные которые мы получили в фрагмент ListPhotosFragment*/
                        EventBus.getDefault().post(new FinishLoadPhoto(searchPhotos.getResults()));
                        view.hideProgressBar();
                    }
                });
    }

    @Override
    public void errorLoad() {
        /**если ошибка загрузики данных выводим сообщение об ошибке*/
        view.showErrorLoadMessage();
        view.hideProgressBar();
    }


}
