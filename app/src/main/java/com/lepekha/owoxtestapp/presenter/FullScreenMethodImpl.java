package com.lepekha.owoxtestapp.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.Util;
import com.lepekha.owoxtestapp.view.MainActivityImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by Ruslan on 28.10.2017.
 */

public class FullScreenMethodImpl implements FullScreenMethod {

    @Inject
    Util util;

    private MainActivityImpl view = null;


    public FullScreenMethodImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public void setView(MainActivityImpl view) {
        this.view = view;
    }

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


    @Override
    public void saveCurrentPhoto(Context context, Bitmap bmp, String photoId) {
            String imgSaved = MediaStore.Images.Media.insertImage(
                    context.getContentResolver(), bmp,
                    photoId + ".jpg", "drawing");
            view.showMessageOpenPhoto(imgSaved);
    }

    private static void scanFile(Context context, Uri imageUri){
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);


    }

}
