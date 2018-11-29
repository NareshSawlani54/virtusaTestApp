package com.example.nareshs.virtusatestapp.web;

import com.example.nareshs.virtusatestapp.placeholder.AlbumItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by naresh.s on 28-Nov-18.
 */

public interface AlbumWebApiInterface {
    @GET("/albums")
    Call<List<AlbumItem>> getAlbumList();
}
