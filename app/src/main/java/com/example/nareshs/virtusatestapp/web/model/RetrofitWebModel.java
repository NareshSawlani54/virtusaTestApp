package com.example.nareshs.virtusatestapp.web.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.nareshs.virtusatestapp.db.AlbumDbUpdateTask;
import com.example.nareshs.virtusatestapp.db.AlbumRoomDatabase;
import com.example.nareshs.virtusatestapp.placeholder.AlbumItem;
import com.example.nareshs.virtusatestapp.web.AlbumWebApiInterface;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by naresh.s on 28-Nov-18.
 */

public class RetrofitWebModel {
    public final String TYPICODE_URL = "https://jsonplaceholder.typicode.com";

    private static RetrofitWebModel INSTANCE;
    private Retrofit mRetrofitInstance;
    private MutableLiveData<List<AlbumItem>> mAlbumsLiveData = new MutableLiveData<>();
//    private AlbumRoomDatabase mAlbumDb;

    public static RetrofitWebModel getInstance() {
        if(INSTANCE == null)
            INSTANCE = new RetrofitWebModel();
        return INSTANCE;
    }

    private RetrofitWebModel() {
//        mAlbumDb = AlbumRoomDatabase.getAlbumDatabase();
    }

    public Retrofit getRetrofitInstance() {
        if(mRetrofitInstance == null)
            mRetrofitInstance = new Retrofit.Builder().baseUrl(INSTANCE.TYPICODE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build();
        return mRetrofitInstance;
    }

    public Call<List<AlbumItem>> getAlbums() {
        AlbumWebApiInterface albumWebApiInterface = getRetrofitInstance().create(AlbumWebApiInterface.class);
        Call<List<AlbumItem>> albumlistcall = albumWebApiInterface.getAlbumList();
//        albumlistcall.enqueue(new Callback<List<AlbumItem>>() {
//            @Override
//            public void onResponse(Call<List<AlbumItem>> call, Response<List<AlbumItem>> response) {
//                List<AlbumItem> albumList = response.body();
//                if(albumList == null)
//                    return;
//                mAlbumsLiveData.setValue(albumList);
//                AlbumDbUpdateTask dbUpdateTask = new AlbumDbUpdateTask(mAlbumDb);
//                dbUpdateTask.execute(albumList);
//            }
//
//            @Override
//            public void onFailure(Call<List<AlbumItem>> call, Throwable t) {
//
//            }
//        });
        return albumlistcall;
    }

    public void reset() {
        mRetrofitInstance = null;
        mAlbumsLiveData = null;
//        mAlbumDb = null;
    }
}
