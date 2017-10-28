package com.lepekha.owoxtestapp.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.R;
import com.lepekha.owoxtestapp.presenter.DownloadPhotosImpl;
import com.lepekha.owoxtestapp.presenter.FullScreenMethodImpl;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivityImpl extends AppCompatActivity implements MainActivity{

    FragmentManager fragmentManager = getSupportFragmentManager();

    ListPhotosFragment listPhotosFragment = null;
    FullScreenPhotoFragment fullScreenPhotoFragment = null;

    @Inject
    DownloadPhotosImpl downloadPhotos;

    @Inject
    FullScreenMethodImpl fullScreenMethod;

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
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        ButterKnife.bind(this);
        App.getComponent().inject(this);
        downloadPhotos.setView(this);//присваеваем вьею для вызова методов с презентера DownloadPhotos
        fullScreenMethod.setView(this);//присваеваем вьею для вызова методов с презентера FullScreenMethod
        listPhotosFragment = (ListPhotosFragment) fragmentManager.findFragmentByTag(ListPhotosFragment.FRAGMENT_NAME);
        if(listPhotosFragment == null) {
            listPhotosFragment = ListPhotosFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentConteiner, listPhotosFragment, ListPhotosFragment.FRAGMENT_NAME)
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
    public void showMessageOpenPhoto(String uri) {
        Snackbar.make(conteiner,"Photo has been saved", Snackbar.LENGTH_SHORT).setAction("Open", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        }).show();
    }

    @Override
    public void openPhotoFullScreen(String photoUrl, String name, String photoShareLink, String photoId) {
        fullScreenPhotoFragment = (FullScreenPhotoFragment) fragmentManager.findFragmentByTag(FullScreenPhotoFragment.FRAGMENT_NAME);
        if(fullScreenPhotoFragment == null) {
            fullScreenPhotoFragment = FullScreenPhotoFragment.newInstance(photoUrl, name, photoShareLink, photoId);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentConteiner, fullScreenPhotoFragment, FullScreenPhotoFragment.FRAGMENT_NAME)
                    .addToBackStack(null)
                    .commit();
        }
    }


}
