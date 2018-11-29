package com.example.nareshs.virtusatestapp.data;

import android.arch.lifecycle.MutableLiveData;
import android.os.Looper;

import com.example.nareshs.virtusatestapp.db.AlbumDAO;
import com.example.nareshs.virtusatestapp.db.AlbumRoomDatabase;
import com.example.nareshs.virtusatestapp.placeholder.AlbumItem;
import com.example.nareshs.virtusatestapp.web.model.RetrofitWebModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by naresh.s on 29-Nov-18.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({RetrofitWebModel.class,Looper.class,AlbumRoomDatabase.class,AlbumDAO.class})
public class LoadDataTest {
    private AlbumRoomDatabase mMockDb;

    @Mock
    private RetrofitWebModel mRetrofitModel;
    @Mock
    private Call<List<AlbumItem>> mAlbumCall;

    private Response<List<AlbumItem>> mResponse;

    private AlbumDAO mAlbumDAO;
    private List<AlbumItem> mAlbums;
    private LoadDataTask mLoadDataTask;
    @Mock
    private MutableLiveData<List<AlbumItem>> mAlbumLiveData;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockStatic(Looper.class);
        Looper mockMainThreadLooper = Mockito.mock(Looper.class);
        when(Looper.getMainLooper()).thenReturn(mockMainThreadLooper);

        mMockDb = DataTestUtils.getMockDb();
        mAlbumDAO = mMockDb.getAlbumDao();
//        mAlbumDAO = Mockito.spy(mAlbumDAO);

        mockStatic(AlbumRoomDatabase.class);
        when(AlbumRoomDatabase.getAlbumDatabase()).thenReturn(mMockDb);

        mockStatic(RetrofitWebModel.class);
        when(RetrofitWebModel.getInstance()).thenReturn(mRetrofitModel);

        mLoadDataTask = new LoadDataTask(mAlbumLiveData);
        mLoadDataTask = Mockito.spy(mLoadDataTask);
        Mockito.doReturn(false).when(mLoadDataTask).isCancelled();
    }

    @Test
    public void loadDataNetworkFail() {
//        Mockito.doReturn(null).when(mAlbumDAO).getAllWords();
        Mockito.doReturn(null).when(mRetrofitModel).getAlbums();
        //Execute Task
        mLoadDataTask.doInBackground(null);
        Mockito.verify(mMockDb,times(3)).getAlbumDao();
        Mockito.verify(mRetrofitModel).getAlbums();
        Mockito.verify(mAlbumLiveData).postValue(null);
    }

    @Test
    public void loadDataNetworkSuccess() throws IOException {
        mAlbums = new ArrayList<>();
        mAlbums.add(new AlbumItem(1,1,"loadDatatest"));
        mResponse = Response.success(mAlbums);

        Mockito.doReturn(mAlbumCall).when(mRetrofitModel).getAlbums();
        Mockito.doReturn(mResponse).when(mAlbumCall).execute();
        //Execute task
        mLoadDataTask.doInBackground(null);

        Mockito.verify(mMockDb,times(3)).getAlbumDao();
        Mockito.verify(mRetrofitModel).getAlbums();
        Mockito.verify(mAlbumLiveData).postValue(mAlbums);
    }

    @Test
    public void loadDataDb() {
        mAlbums = new ArrayList<>();
        mAlbums.add(new AlbumItem(1,1,"loadDataDBtest"));
        Mockito.doReturn(mAlbums).when(mAlbumDAO).getAllWords();
        mLoadDataTask.doInBackground(null);
        Mockito.verify(mMockDb,times(3)).getAlbumDao();
        Mockito.verify(mAlbumLiveData).postValue(mAlbums);
    }
}
