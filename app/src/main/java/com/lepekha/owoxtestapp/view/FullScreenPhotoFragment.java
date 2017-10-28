package com.lepekha.owoxtestapp.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lepekha.owoxtestapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ruslan on 24.08.2016.
 */

public class FullScreenPhotoFragment extends DialogFragment {

    private String photoUrl;
    private String name;

    @BindView(R.id.imgPhotoFullScreen)
    ImageView imgPhotoFullScreen;


    public static final String FRAGMENT_NAME = "fragment_full_screen";

    public static FullScreenPhotoFragment newInstance(String photoUrl, String name) {
        FullScreenPhotoFragment fullScreenPhotoFragment = new FullScreenPhotoFragment();

        Bundle args = new Bundle();
        args.putString("photoUrl", photoUrl);
        args.putString("name", name);
        fullScreenPhotoFragment.setArguments(args);
        return fullScreenPhotoFragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        /**очищаем меню от поискаи добавляем кнопки для сохранения и отправки фото*/
        menu.clear();
        inflater.inflate(R.menu.menu_full_screen_photo, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
        setHasOptionsMenu(true);
        photoUrl = getArguments().getString("photoUrl");
        name = getArguments().getString("name");
        ((MainActivityImpl) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivityImpl) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((MainActivityImpl) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((MainActivityImpl) getActivity()).getSupportActionBar().setTitle(getString(R.string.toolbar_author_full_screen_photo_fragment) + " " + name);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup conteiner, Bundle save) {
        View view = inflater.inflate(R.layout.full_screen_photo, conteiner, false);
        ButterKnife.bind(this, view);
        ((MainActivityImpl) getActivity()).showProgressBar();
        Picasso.with(getContext()).load(photoUrl).placeholder(R.drawable.ic_photo_camera_white_24px).into(imgPhotoFullScreen, new Callback() {
            @Override
            public void onSuccess() {
                ((MainActivityImpl) getActivity()).hideProgressBar();
            }

            @Override
            public void onError() {
                ((MainActivityImpl) getActivity()).showErrorLoadFullPhotoMessage();
                ((MainActivityImpl) getActivity()).hideProgressBar();
            }
        });

        return view;
    }



}