package com.lepekha.owoxtestapp.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.R;
import com.lepekha.owoxtestapp.presenter.FullScreenMethodImpl;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Фрагмент отображения фото на весь экран
 */

public class FullScreenPhotoFragment extends DialogFragment {

    private String photoUrl;
    private String name;
    private String photoShareLink;
    private String photoId;

    @BindView(R.id.imgPhotoFullScreen)
    ImageView imgPhotoFullScreen;

    @BindString(R.string.menu_share_message)
    String MENU_SHARE_MESSAGE;

    @Inject
    FullScreenMethodImpl fullScreenMethod;

    Picasso picasso;

    public static final int MULTIPLE_PERMISSIONS = 1;
    private static final String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static final String FRAGMENT_NAME = "fragment_full_screen";
    public static final String PICASSO_FULL_SCREEN_TAG = "picasso_full_screen_tag";


    public static FullScreenPhotoFragment newInstance(String photoUrl, String name, String photoShareLink, String photoId) {
        FullScreenPhotoFragment fullScreenPhotoFragment = new FullScreenPhotoFragment();

        Bundle args = new Bundle();
        args.putString("photoUrl", photoUrl);
        args.putString("name", name);
        args.putString("photoShareLink", photoShareLink);
        args.putString("photoId", photoId);
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
            case R.id.item_save:
                if(checkPermissions()){
                    fullScreenMethod.saveCurrentPhoto(getContext(),imgPhotoFullScreen.getDrawingCache(),photoId);
                }
                break;
            case R.id.item_share:
                fullScreenMethod.shareCurrentPhoto(getContext(),MENU_SHARE_MESSAGE,photoShareLink);
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
        App.getComponent().inject(this);
        photoUrl = getArguments().getString("photoUrl");
        name = getArguments().getString("name");
        photoShareLink = getArguments().getString("photoShareLink");
        photoId = getArguments().getString("photoId");


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup conteiner, Bundle save) {
        View view = inflater.inflate(R.layout.full_screen_photo, conteiner, false);
        ButterKnife.bind(this, view);
        ((MainActivityImpl) getActivity()).showProgressBar();
        ((MainActivityImpl) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivityImpl) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((MainActivityImpl) getActivity()).getSupportActionBar().setTitle(getString(R.string.toolbar_author_full_screen_photo_fragment) + " " + name);
        /**Загружаем фото в полный размер*/
        picasso = Picasso.with(getContext());
        picasso.load(photoUrl).tag(PICASSO_FULL_SCREEN_TAG).placeholder(R.drawable.ic_image_black_24dp).error(R.drawable.ic_error_black_24dp).into(imgPhotoFullScreen, new Callback() {
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

        imgPhotoFullScreen.setDrawingCacheEnabled(true);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        picasso.cancelTag(PICASSO_FULL_SCREEN_TAG);
        ((MainActivityImpl) getActivity()).hideProgressBar();
    }

    /**Запрос разрешения для сохранения фото на устройство*/
    public boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:PERMISSIONS) {
            result = ContextCompat.checkSelfPermission(getContext(),p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLE_PERMISSIONS );
            return false;
        }
        return true;
    }

}