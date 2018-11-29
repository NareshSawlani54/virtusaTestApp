package com.example.nareshs.virtusatestapp.db;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.nareshs.virtusatestapp.placeholder.AlbumItem;

/**
 * Created by naresh.s on 28-Sep-18.
 */
@Database(entities = AlbumItem.class, version = 1)
public abstract class AlbumRoomDatabase extends RoomDatabase {
    public abstract AlbumDAO getAlbumDao();

    private static volatile AlbumRoomDatabase INSTANCE;
    public static void initDb(Application appctx) {
        if(INSTANCE == null) {
            synchronized (AlbumRoomDatabase.class) {
                if (INSTANCE == null) {
                    try {
                        INSTANCE = Room.databaseBuilder(appctx,AlbumRoomDatabase.class,"albumDB").allowMainThreadQueries().build();
                    } catch (UnsupportedOperationException uoe) {
                        uoe.printStackTrace();
                    }
                }
            }
        }
    }
    public static AlbumRoomDatabase getAlbumDatabase() {
        return INSTANCE;
    }

    public static void clearDbInstance() {
        INSTANCE = null;
    }
}
