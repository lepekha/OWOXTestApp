package com.lepekha.owoxtestapp.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lepekha.owoxtestapp.App;
import com.lepekha.owoxtestapp.Constants;
import com.lepekha.owoxtestapp.R;
import com.lepekha.owoxtestapp.event.FinishLoadPhoto;
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
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private int PAGE = 1;

    @Inject
    DownloadPhotosImpl downloadPhotos;

    @BindView(R.id.photos_list)
    RecyclerView photosList;

    @BindView(R.id.btnLoadMore)
    Button btnLoadMore;

    private RecyclerView.Adapter photosListAdapter;
    private RecyclerView.LayoutManager photoListManager;

    /**Список в котором хранятся обьекты на фото вместе со ссылками*/
    private List<Photo> photos = new ArrayList<>();

    @BindInt(R.integer.gridCount) int gridCount;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        App.getComponent().inject(this);
    }

    public static MainActivityFragment newInstance(){
        MainActivityFragment mainActivityFragment = new MainActivityFragment();
        Bundle args = new Bundle();
        mainActivityFragment.setArguments(args);
        return mainActivityFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        photoListManager = new GridLayoutManager(getContext(),gridCount);
        photosList.setLayoutManager(photoListManager);
        photosListAdapter = new PhotosListAdapter(photos, getContext());
        photosList.setAdapter(photosListAdapter);

        photosList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    btnLoadMore.setVisibility(View.VISIBLE);
                }else {
                    btnLoadMore.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        downloadPhotos.getPhotosFromAPI(PAGE, Constants.PER_PAGE);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFinishLoadPhoto(FinishLoadPhoto event) {
        this.photos.addAll(event.photos);
        photosListAdapter.notifyDataSetChanged();
    }
}
