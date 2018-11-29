package com.example.nareshs.virtusatestapp;

import android.app.Application;

import com.example.nareshs.virtusatestapp.db.AlbumRoomDatabase;

/**
 * Created by naresh.s on 28-Nov-18.
 */

public class AlbumApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Init Db
        AlbumRoomDatabase.initDb(this);
    }
}
