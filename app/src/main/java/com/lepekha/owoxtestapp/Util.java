package com.lepekha.owoxtestapp;

import android.content.Context;

/**
 * Created by Ruslan on 27.10.2017.
 */

public class Util {

    Context context;

    public Util(Context context) {
        App.getComponent().inject(this);
        this.context = context;
    }


}
