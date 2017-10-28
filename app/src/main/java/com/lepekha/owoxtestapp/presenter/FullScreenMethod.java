package com.lepekha.owoxtestapp.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.lepekha.owoxtestapp.view.MainActivityImpl;

/**
 * Created by Ruslan on 28.10.2017.
 */

public interface FullScreenMethod {
    void setView(MainActivityImpl view);
    void shareCurrentPhoto(Context context, String header, String message);
    void saveCurrentPhoto(Context context, Bitmap bmp, String photoId);
}
