package com.lepekha.owoxtestapp.view;

/**
 * Created by Ruslan on 24.10.2017.
 */

public interface MainActivity {
    void showProgressBar();
    void hideProgressBar();
    void showMessage(String text);
    void showErrorLoadMessage();
    void showErrorLoadFullPhotoMessage();
    void openPhotoFullScreen(String photoUrl, String thumbneilImage);
}
