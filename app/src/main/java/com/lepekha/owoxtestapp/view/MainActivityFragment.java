package com.lepekha.owoxtestapp.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lepekha.owoxtestapp.R;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.photos_list)
    RecyclerView photosList;

    @BindInt(R.integer.gridCount) int gridCount;

    private RecyclerView.Adapter photosListAdapter;
    private RecyclerView.LayoutManager photoListManager;



    public MainActivityFragment() {
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
        return view;
    }
}
