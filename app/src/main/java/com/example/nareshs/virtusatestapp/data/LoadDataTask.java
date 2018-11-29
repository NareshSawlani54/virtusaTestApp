package com.example.nareshs.virtusatestapp.data;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.nareshs.virtusatestapp.db.AlbumDAO;
import com.example.nareshs.virtusatestapp.db.AlbumRoomDatabase;
import com.example.nareshs.virtusatestapp.placeholder.AlbumItem;
import com.example.nareshs.virtusatestapp.web.model.RetrofitWebModel;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by naresh.s on 29-Nov-18.
 */

public class LoadDataTask extends AsyncTask<Void,Void,Void> {
    MutableLiveData<List<AlbumItem>> mAlbumsLiveData;
    public LoadDataTask(MutableLiveData<List<AlbumItem>> albumsLiveData) {
        mAlbumsLiveData = albumsLiveData;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            if(isCancelled())
                return null;
            AlbumDAO albumDao = AlbumRoomDatabase.getAlbumDatabase().getAlbumDao();
            List<AlbumItem> albumlist = albumDao.getAllWords();
            if (albumlist == null || albumlist.size() <= 0) {
                Call<List<AlbumItem>> albumListCall = RetrofitWebModel.getInstance().getAlbums();
                try {
                    Response<List<AlbumItem>> res = albumListCall.execute();
                    if (res != null && res.body() != null) {
                        albumlist = res.body();
                        //sort by Title
                        Collections.sort(albumlist);
                        if (albumlist != null)
                            mAlbumsLiveData.postValue(albumlist);

                        albumDao.insertAlbumList(albumlist);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    mAlbumsLiveData.postValue(null);
                }
            } else {
                mAlbumsLiveData.postValue(albumlist);
            }
        } catch (Exception ex) {
            mAlbumsLiveData.postValue(null);
        }
        return null;
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
        mAlbumsLiveData.setValue(null);
    }
}
