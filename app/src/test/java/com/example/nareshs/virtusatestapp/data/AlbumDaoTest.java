package com.example.nareshs.virtusatestapp.data;

import android.arch.persistence.room.RoomSQLiteQuery;
import android.os.Looper;

import com.example.nareshs.virtusatestapp.db.AlbumDAO;
import com.example.nareshs.virtusatestapp.db.AlbumRoomDatabase;
import com.example.nareshs.virtusatestapp.placeholder.AlbumItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by naresh.s on 29-Nov-18.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Looper.class})
public class AlbumDaoTest {
    private AlbumDAO mAlbumDAO;
    private AlbumRoomDatabase mMockDb;
    private List<AlbumItem> mAlbumList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockStatic(Looper.class);
        Looper mockMainThreadLooper = Mockito.mock(Looper.class);
        when(Looper.getMainLooper()).thenReturn(mockMainThreadLooper);

        mMockDb = DataTestUtils.getMockDb();

        mAlbumDAO = mMockDb.getAlbumDao();
        mAlbumDAO = Mockito.spy(mAlbumDAO);

        mAlbumList = new ArrayList<>();
        mAlbumList = Mockito.spy(mAlbumList);

        mAlbumList.add(new AlbumItem(1,1,"test"));
        Mockito.verify(mAlbumList).add(any(AlbumItem.class));
    }

    @Test
    public void testInsert() {
        mAlbumDAO.insertAlbumList(mAlbumList);
    }

    @Test
    public void testReadAlbums() {
        mAlbumDAO.getAllWords();
        Mockito.verify(mMockDb).query(any(RoomSQLiteQuery.class));
    }

    @Test
    public void testDelete() {
        mAlbumDAO.deleteAllAlbums();
    }
//
//    @After
//    public void checkBeginEndDb() {
//        Mockito.verify(mMockDb).beginTransaction();
//        Mockito.verify(mMockDb).endTransaction();
//    }
}
