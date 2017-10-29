package com.lepekha.owoxtestapp.presenter;

import android.util.Log;

import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.event.FinishLoadPhoto;
import com.lepekha.owoxtestapp.model.cache.PreferenceImpl;
import com.lepekha.owoxtestapp.model.pojo.Photo;
import com.lepekha.owoxtestapp.model.pojo.SearchPhoto;
import com.lepekha.owoxtestapp.model.rest.RequestImpl;
import com.lepekha.owoxtestapp.view.MainActivityImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;

/**
 * Класс для загрузки Новых фото и поиска фото по запросу
 */

public class DownloadPhotosImpl implements DownloadPhotos {

    public static final int NEW_PHOTOS = 0;
    public static final int SEARCH = 1;
    public static final int PER_PAGE = 30;

    @Inject
    RequestImpl requestImpl;

    @Inject
    PreferenceImpl cache;

    private MainActivityImpl view = null;

    public DownloadPhotosImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public void setView(MainActivityImpl view) {
        this.view = view;
    }

    /**Загружаем список фото с сервера /photos*/
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
                            cache.savePhotosJson(photos);//сохраняем список фото в кеш
                            cache.setUseCache(true);// = true - Если собылие не будет получено то используем сохраненный кеш
                            EventBus.getDefault().post(new FinishLoadPhoto(photos)); //Посылаем событие окончания загрузки + данные которые мы получили в фрагмент ListPhotosFragment
                            view.hideProgressBar();
                        }
                    });
    }

    /**Загружаем список фото с сервера /search/photos*/
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
                        errorLoad();
                    }

                    @Override
                    public void onNext(SearchPhoto searchPhotos) {
                        /**Посылаем событие окончания загрузки + данные которые мы получили в фрагмент ListPhotosFragment*/
                        cache.savePhotosJson(searchPhotos.getResults());//сохраняем список фото в кеш
                        cache.setUseCache(true);// = true - Если собылие не будет получено то используем сохраненный кеш
                        EventBus.getDefault().post(new FinishLoadPhoto(searchPhotos.getResults()));//Посылаем событие окончания загрузки + данные которые мы получили в фрагмент ListPhotosFragment
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
