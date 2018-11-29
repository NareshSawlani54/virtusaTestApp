package com.example.nareshs.virtusatestapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.nareshs.virtusatestapp.placeholder.AlbumItem;

import java.util.List;

/**
 * Created by naresh.s on 28-Sep-18.
 */
@Dao
public interface AlbumDAO {

    @Insert
    void insert(AlbumItem albumItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbumList(List<AlbumItem> albumlist);

    @Query("DELETE FROM album_table")
    void deleteAllAlbums();

    @Query("SELECT * from album_table ORDER BY title ASC")
    List<AlbumItem> getAllWords();
}
