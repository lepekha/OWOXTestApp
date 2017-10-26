package com.lepekha.owoxtestapp.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.R;
import com.lepekha.owoxtestapp.presenter.DownloadPhotosImpl;

import javax.inject.Inject;

public class MainActivityImpl extends AppCompatActivity implements MainActivity{

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Inject
    DownloadPhotosImpl downloadPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        App.getComponent().inject(this);
        fragmentManager.beginTransaction()
                .add(R.id.fragmentConteiner, MainActivityFragment.newInstance())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
