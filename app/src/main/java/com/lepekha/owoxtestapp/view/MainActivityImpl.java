package com.lepekha.owoxtestapp.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.R;
import com.lepekha.owoxtestapp.Util;
import com.lepekha.owoxtestapp.presenter.DownloadPhotosImpl;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivityImpl extends AppCompatActivity implements MainActivity{

    FragmentManager fragmentManager = getSupportFragmentManager();

    MainActivityFragment mainActivityFragment = null;
    FullScreenPhotoFragment fullScreenPhotoFragment = null;

    @Inject
    DownloadPhotosImpl downloadPhotos;



    @BindView(R.id.conteiner)
    CoordinatorLayout conteiner;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindString(R.string.snackbar_load_error)
    String SNACKBAR_LOAD_ERROR;

    @BindString(R.string.snackbar_load_error_full_screen_photo)
    String SNACKBAR_LOAD_FULL_PHOTO_ERROR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        App.getComponent().inject(this);
        downloadPhotos.setView(this);//присваеваем вьею для вызова методов с презентера
        mainActivityFragment = (MainActivityFragment) fragmentManager.findFragmentByTag(MainActivityFragment.FRAGMENT_NAME);
        if(mainActivityFragment == null) {
            mainActivityFragment = MainActivityFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentConteiner, mainActivityFragment, MainActivityFragment.FRAGMENT_NAME)
                    .commit();
        }
    }


    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String text) {
        Snackbar.make(conteiner,text, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorLoadMessage() {
        showMessage(SNACKBAR_LOAD_ERROR);
    }

    @Override
    public void showErrorLoadFullPhotoMessage() {
        showMessage(SNACKBAR_LOAD_FULL_PHOTO_ERROR);
    }

    @Override
    public void openPhotoFullScreen(String photoUrl, String name) {
        fullScreenPhotoFragment = (FullScreenPhotoFragment) fragmentManager.findFragmentByTag(FullScreenPhotoFragment.FRAGMENT_NAME);
        if(fullScreenPhotoFragment == null) {
            fullScreenPhotoFragment = FullScreenPhotoFragment.newInstance(photoUrl, name);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentConteiner, fullScreenPhotoFragment, FullScreenPhotoFragment.FRAGMENT_NAME)
                    .addToBackStack(null)
                    .commit();
        }
    }


}
