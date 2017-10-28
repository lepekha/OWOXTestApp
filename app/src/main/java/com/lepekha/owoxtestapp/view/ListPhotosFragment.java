package com.lepekha.owoxtestapp.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.R;
import com.lepekha.owoxtestapp.event.FinishLoadPhoto;
import com.lepekha.owoxtestapp.model.cache.ImplPreference;
import com.lepekha.owoxtestapp.model.pojo.Photo;
import com.lepekha.owoxtestapp.presenter.DownloadPhotosImpl;
import com.lepekha.owoxtestapp.view.adapter.PhotosListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Фрагмент для отображения списка фотографий загруженных с АПИ
 */
public class ListPhotosFragment extends Fragment implements PhotosListAdapter.OnItemClickListener  {

    private int PAGE = 1;
    private int STATE = 0;
    String query;

    @Inject
    DownloadPhotosImpl downloadPhotos;

    @BindView(R.id.photos_list)
    RecyclerView photosList;

    @BindView(R.id.btnLoadMore)
    Button btnLoadMore;

    @BindView(R.id.txtNoResults)
    TextView txtNoResults;

    @BindString(R.string.menu_new_photos)
    String TITLE_NEW_PHOTOS;

    @BindString(R.string.menu_search)
    String TITLE_SEARCH;

    @Inject
    ImplPreference cache;

    private boolean flagFirstStartDevice = false;

    public static final String FRAGMENT_NAME = "fragment_photos_list";
    public static final String FULL_SCREEN_FRAGMENT_NAME = "fragment_full_screen";
    private static final int ANIM_SHOW_TRANSLATION_Y = -50;
    private static final int ANIM_HIDE_TRANSLATION_Y = 150;

    private PhotosListAdapter photosListAdapter;
    private RecyclerView.LayoutManager photoListManager;

    /**Список в котором хранятся обьекты на фото вместе со ссылками*/
    private List<Photo> photos = new ArrayList<>();

    @BindInt(R.integer.gridCount) int gridCount;

    public static ListPhotosFragment newInstance() {
        ListPhotosFragment listPhotosFragment = new ListPhotosFragment();
        Bundle args = new Bundle();
        listPhotosFragment.setArguments(args);
        return listPhotosFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
        setHasOptionsMenu(true);
        App.getComponent().inject(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener
                (new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        ((MainActivityImpl) getActivity()).getSupportActionBar().setTitle(TITLE_SEARCH + ": " + query);
                        searchView.onActionViewCollapsed();
                        startSearch(query);
                        return true;
                    }
                    @Override
                    public boolean onQueryTextChange(String s) {
                        return false;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_new_photo:
                photos.clear();
                ((MainActivityImpl) getActivity()).getSupportActionBar().setTitle(TITLE_NEW_PHOTOS);
                startNewPhotos();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        ((MainActivityImpl) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivityImpl) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        ((MainActivityImpl) getActivity()).getSupportActionBar().setTitle(TITLE_NEW_PHOTOS);
        photoListManager = new GridLayoutManager(getContext(),gridCount);
        photosList.setLayoutManager(photoListManager);
        photosListAdapter = new PhotosListAdapter(photos, getContext());
        photosListAdapter.setOnItemClick(this);
        photosList.setAdapter(photosListAdapter);

        btnLoadMore.setVisibility(View.GONE);//убираем кнопку Загрузки остальных фото при старте

        photosList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    btnLoadMore.setVisibility(View.VISIBLE);
                    btnLoadMore.animate().translationY(ANIM_SHOW_TRANSLATION_Y);

                }else {
                    btnLoadMore.animate().translationY(ANIM_HIDE_TRANSLATION_Y);

                }
            }
        });


        /**При первом старте приложения загружаем фото с апи*/
        if(!flagFirstStartDevice){
            startNewPhotos();

        }

        return view;
    }

    @OnClick(R.id.btnLoadMore)
    public void onClickLoadMore(){
        switch (STATE){
            case 0: downloadPhotos.getPhotosFromAPI(++PAGE, downloadPhotos.PER_PAGE);
                break;
            case 1: downloadPhotos.getSearchPhotosFromAPI(query,++PAGE,downloadPhotos.PER_PAGE);
                break;
            default:
                break;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**Если загрузка фото закончена обрабатываем событие*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFinishLoadPhoto(FinishLoadPhoto event) {
        if(!flagFirstStartDevice){
            photos.clear();
            this.photos.addAll(event.photos);
            flagFirstStartDevice = true;
        }else{
            this.photos.addAll(event.photos);
        }

        if(photos.size() == 0){
            showNoResultsPlaceholder();
        }else{
            hideNoResultsPlaceholder();
        }
        photosListAdapter.notifyDataSetChanged();
    }
    /**Ловим нажатие в списке*/
    @Override
    public void onItemClick(View view, Photo photo, int position) {
        /**Вызываем метод с основного активити для замены фрагмента с фото на полный экран*/
        ((MainActivityImpl)getActivity()).openPhotoFullScreen(
                photo.getUrls().getRegular(),
                photo.getUser().getName(),
                photo.getLinks().getHtml(),
                photo.getId()
        );
    }

    public void startSearch(String query){
        this.query = query;
        STATE = downloadPhotos.SEARCH;
        photos.clear();
        PAGE = 1;
        downloadPhotos.getSearchPhotosFromAPI(query,PAGE,downloadPhotos.PER_PAGE);
    }

    public void startNewPhotos(){
        firtsStart();
        STATE = downloadPhotos.NEW_PHOTOS;
        PAGE = 1;
        downloadPhotos.getPhotosFromAPI(PAGE, downloadPhotos.PER_PAGE);
    }

    public void firtsStart(){
       // photos.clear();
        this.photos.addAll(cache.getPhotosFromJson());
        photosListAdapter.notifyDataSetChanged();
    }

    public void showNoResultsPlaceholder(){
        txtNoResults.setVisibility(View.VISIBLE);
        photosList.setVisibility(View.INVISIBLE);
    }

    public void hideNoResultsPlaceholder(){
        txtNoResults.setVisibility(View.INVISIBLE);
        photosList.setVisibility(View.VISIBLE);
    }
}
