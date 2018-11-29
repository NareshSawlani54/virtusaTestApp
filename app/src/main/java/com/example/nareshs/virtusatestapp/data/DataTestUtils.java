package com.example.nareshs.virtusatestapp.data;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.content.Context;
import android.database.Cursor;

import com.example.nareshs.virtusatestapp.db.AlbumDAO;
import com.example.nareshs.virtusatestapp.db.AlbumRoomDatabase;

import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by naresh.s on 29-Nov-18.
 */

public class DataTestUtils {
    public static AlbumRoomDatabase getMockDb() {
        Context mockContext = Mockito.mock(Context.class);
        RoomDatabase.Builder<AlbumRoomDatabase> dbbuilder =Room.inMemoryDatabaseBuilder(mockContext,AlbumRoomDatabase.class);
        AlbumRoomDatabase Db =dbbuilder.allowMainThreadQueries().build();
        Db = Mockito.spy(Db);

        doNothing().when(Db).beginTransaction();
        doNothing().when(Db).endTransaction();
        doNothing().when(Db).setTransactionSuccessful();

        SupportSQLiteStatement stmt = Mockito.mock(SupportSQLiteStatement.class);
        Mockito.doReturn(stmt).when(Db).compileStatement(any(String.class));

        Cursor c = Mockito.mock(Cursor.class);
        Mockito.doReturn(c).when(Db).query(any(RoomSQLiteQuery.class));

        AlbumDAO albumDAO = Db.getAlbumDao();
        albumDAO = Mockito.spy(albumDAO);
        Mockito.doReturn(albumDAO).when(Db).getAlbumDao();
        return Db;
    }
}
