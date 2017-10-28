package com.lepekha.owoxtestapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Ruslan on 27.10.2017.
 */

public class Util {

    Context context;

    public Util(Context context) {
        App.getComponent().inject(this);
        this.context = context;
    }

    /**Проверяем есть ли соединение с интернетом*/
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;

    }
}
