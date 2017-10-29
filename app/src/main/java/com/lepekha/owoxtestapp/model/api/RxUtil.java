package com.lepekha.owoxtestapp.model.api;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RxUtil {

    private static Observable.Transformer ioToMainThreadSchedulerTransformer;

    static {
        ioToMainThreadSchedulerTransformer = createIOToMainThreadScheduler();
    }

    @SuppressWarnings("unchecked")
    private static <T> Observable.Transformer<T, T> createIOToMainThreadScheduler() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @SuppressWarnings("unchecked")
    public static <T> Observable.Transformer<T, T> applyIOToMainThreadSchedulers() {
        return ioToMainThreadSchedulerTransformer;
    }
}