package com.example.nareshs.virtusatestapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.nareshs.virtusatestapp.data.LoadDataTask;
import com.example.nareshs.virtusatestapp.placeholder.AlbumItem;
import com.example.nareshs.virtusatestapp.web.model.RetrofitWebModel;

import java.util.List;

/**
 * Created by naresh.s on 28-Nov-18.
 */

public class AlbumListViewModel extends AndroidViewModel {
    private MutableLiveData<List<AlbumItem>> mAlbumsLiveData = new MutableLiveData<>();
    private LoadDataTask mLoadDataTask;
    public AlbumListViewModel(Application app) {
        super(app);
    }

    public LiveData<List<AlbumItem>> getAlbumList() {
        if(mLoadDataTask == null || mLoadDataTask.getStatus() == AsyncTask.Status.FINISHED) {
            mLoadDataTask = new LoadDataTask(mAlbumsLiveData);
            mLoadDataTask.execute();
        }
        return mAlbumsLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mAlbumsLiveData = null;
        RetrofitWebModel.getInstance().reset();
        mLoadDataTask.cancel(true);
        mLoadDataTask = null;
    }
}
