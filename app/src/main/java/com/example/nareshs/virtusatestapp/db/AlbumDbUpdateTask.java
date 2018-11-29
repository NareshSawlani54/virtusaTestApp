package com.example.nareshs.virtusatestapp.db;

import android.os.AsyncTask;

import com.example.nareshs.virtusatestapp.placeholder.AlbumItem;

import java.util.List;

/**
 * Created by naresh.s on 28-Nov-18.
 */

public class AlbumDbUpdateTask extends AsyncTask<List<AlbumItem>,Void,Void>{

    private AlbumRoomDatabase mDb;

    public AlbumDbUpdateTask(AlbumRoomDatabase db) {
        mDb = db;
    }

    @Override
    protected Void doInBackground(List<AlbumItem>... params) {
        mDb.getAlbumDao().insertAlbumList(params[0]);
        return null;
    }
}
