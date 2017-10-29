package com.lepekha.owoxtestapp.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.view.MainActivityImpl;

/**
 * Created by Ruslan on 28.10.2017.
 */

public class FullScreenMethodImpl implements FullScreenMethod {

    private MainActivityImpl view = null;

    public FullScreenMethodImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public void setView(MainActivityImpl view) {
        this.view = view;
    }

    /**Метод для отправки html ссылки на фото*/
    @Override
    public void shareCurrentPhoto(Context context, String header, String message) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        Intent chooser_intent = Intent.createChooser(shareIntent, header);
        chooser_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(chooser_intent);
    }


    /**Метод для сохранения загруженной фотографии на устройство*/
    @Override
    public void saveCurrentPhoto(Context context, Bitmap bmp, String photoId) {
            String imgSaved = MediaStore.Images.Media.insertImage(
                    context.getContentResolver(), bmp,
                    photoId + ".jpg", "drawing");
            view.showMessageOpenPhoto(imgSaved);
    }
}
