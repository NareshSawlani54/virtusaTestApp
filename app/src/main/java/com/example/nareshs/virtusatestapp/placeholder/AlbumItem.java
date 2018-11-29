package com.example.nareshs.virtusatestapp.placeholder;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naresh.s on 28-Nov-18.
 */
@Entity(tableName = "album_table")
public class AlbumItem implements Comparable<AlbumItem>{
    @SerializedName("userId")
    @ColumnInfo(name = "userId")
    public int mUserId;

    @SerializedName("id")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "_id")
    public int mId;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    public String mTitle;

    public AlbumItem(int mUserId, int mId, String mTitle) {
        this.mUserId = mUserId;
        this.mId = mId;
        this.mTitle = mTitle;
    }

    public int getmUserId() {
        return mUserId;
    }

    public void setmUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    @NonNull
    public int getmId() {
        return mId;
    }

    public void setmId(@NonNull int mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public int compareTo(@NonNull AlbumItem o) {
        if(o==null || mTitle == null)
            return 0;
        return this.mTitle.compareTo(o.getmTitle());
    }
}
