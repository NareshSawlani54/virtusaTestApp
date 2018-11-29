package com.example.nareshs.virtusatestapp.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nareshs.virtusatestapp.R;
import com.example.nareshs.virtusatestapp.adapter.AlbumListAdapter;
import com.example.nareshs.virtusatestapp.placeholder.AlbumItem;
import com.example.nareshs.virtusatestapp.viewmodel.AlbumListViewModel;

import java.util.List;

public class AlbumListActivity extends AppCompatActivity {

    private RecyclerView mAlbumRecyclerView;
    private AlbumListViewModel mAlbumViewModel;
    private AlbumListAdapter mAlbumAdapter;
    private LiveData<List<AlbumItem>> mAlbumListLiveData;
    private Observer<List<AlbumItem>> mObserver;
    private ProgressBar mProgress;
    private AlertDialog mDataConnectionErrorDialog;
//    private boolean mNeedReloadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAlbumRecyclerView = findViewById(R.id.albumlist);
        mProgress = findViewById(R.id.album_progress);
        mAlbumViewModel = ViewModelProviders.of(this).get(AlbumListViewModel.class);

//        if(isConnectedToInternet()) {
            mProgress.setVisibility(View.VISIBLE);
            init();
//        } else {
//            mProgress.setVisibility(View.GONE);
//            initErrorDialog();
//            mDataConnectionErrorDialog.show();
//        }
    }

    public boolean isConnectedToInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
       return activeNetwork != null && activeNetwork.isConnected();
    }

    public void initErrorDialog() {
        if(mDataConnectionErrorDialog != null)
            return;
        mDataConnectionErrorDialog = new AlertDialog.Builder(this)
                .setTitle("Data Connection Error").setMessage("No Internet connection")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).create();
    }

    public void init() {

        mAlbumRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAlbumAdapter =  new AlbumListAdapter(this);
        mAlbumRecyclerView.setAdapter(mAlbumAdapter);

        mAlbumListLiveData = mAlbumViewModel.getAlbumList();
        mObserver = new Observer<List<AlbumItem>>() {
            @Override
            public void onChanged(@Nullable List<AlbumItem> albumItems) {
                if(albumItems !=null)
                    mAlbumAdapter.setAlbumList(albumItems,true);
                else {
                    initErrorDialog();
                    mDataConnectionErrorDialog.show();
                }
                mProgress.setVisibility(View.GONE);
            }
        };
        mAlbumListLiveData.observe(this,mObserver);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(mNeedReloadData) {
//            mNeedReloadData = false;
//            mAlbumViewModel.getAlbumList();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mAlbumListLiveData != null)
            mAlbumListLiveData.removeObservers(this);
    }
}
