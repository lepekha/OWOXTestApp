package com.lepekha.owoxtestapp.presenter;

import android.util.Log;

import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.Constants;
import com.lepekha.owoxtestapp.event.FinishLoadPhoto;
import com.lepekha.owoxtestapp.model.pojo.Photo;
import com.lepekha.owoxtestapp.model.pojo.SearchPhoto;
import com.lepekha.owoxtestapp.model.rest.RequestImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observer;

/**
 * Created by Ruslan on 25.10.2017.
 */

public class DownloadPhotosImpl implements DownloadPhotos {

    @Inject
    RequestImpl requestImpl;

    public static List<Photo> photoList = new ArrayList<>();
    public static List<SearchPhoto> searchPhotosList;

    public DownloadPhotosImpl() {
        App.getComponent().inject(this);
    }

    public static List<Photo> getPhotoList() {
        return photoList;
    }

    public static void setPhotoList(List<Photo> photoList) {
        DownloadPhotosImpl.photoList = photoList;
    }

    public static List<SearchPhoto> getSearchPhotosList() {
        return searchPhotosList;
    }

    public static void setSearchPhotosList(List<SearchPhoto> searchPhotosList) {
        DownloadPhotosImpl.searchPhotosList = searchPhotosList;
    }

    @Override
    public void getPhotosFromAPI(int page, int per_page) {
        requestImpl
                .getPhotos(String.valueOf(page), String.valueOf(per_page))
                .subscribe(new Observer<List<Photo>>(){

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Photo> photos) {
                        /**Посылаем событие окончания загрузки + данные которые мы получили в фрагмент MainActivityFragment*/
                        EventBus.getDefault().post(new FinishLoadPhoto(photos));
                    }
                });
    }

    @Override
    public void getSearchPhotosFromAPI(String query, int page, int per_page) {
        requestImpl
                .searchPhotos(query, String.valueOf(page), String.valueOf(per_page))
                .subscribe(new Observer<List<SearchPhoto>>(){
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<SearchPhoto> searchPhotos) {
                        searchPhotosList = searchPhotos;
                    }
                });
    }

    @Override
    public List<Photo> prepearPhotoToList(int page, List<Photo> photos) {
        return null;
    }

}
