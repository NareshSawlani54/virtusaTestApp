package com.example.nareshs.virtusatestapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nareshs.virtusatestapp.R;
import com.example.nareshs.virtusatestapp.placeholder.AlbumItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.nareshs.virtusatestapp.adapter.AlbumListAdapter.AlbumHolder;
/**
 * Created by naresh.s on 28-Nov-18.
 */

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumHolder> {
    private Context mContext;
    private List<AlbumItem> mAlbumList = new ArrayList();

    public AlbumListAdapter(Context ctx) {
        mContext = ctx;
    }
    @Override
    public AlbumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(mContext).inflate(R.layout.album_list_item,parent,false);
        return new AlbumHolder(itemview);
    }

    @Override
    public void onBindViewHolder(AlbumHolder holder, int position) {
        holder.mId.setText(""+mAlbumList.get(position).getmId());
        holder.mUserId.setText(""+mAlbumList.get(position).getmUserId());
        holder.mTitle.setText(""+mAlbumList.get(position).getmTitle());
    }

    @Override
    public int getItemCount() {
        return mAlbumList.size();
    }

    public void setAlbumList(List<AlbumItem> albumList, boolean needNotify) {
        this.mAlbumList = albumList;
        if(needNotify)
            notifyDataSetChanged();
    }

    public static class AlbumHolder extends RecyclerView.ViewHolder {

        private TextView mUserId;
        private TextView mId;
        private TextView mTitle;

        public AlbumHolder(View itemView) {
            super(itemView);
            mUserId = itemView.findViewById(R.id.album_userid);
            mTitle = itemView.findViewById(R.id.album_title);
            mId = itemView.findViewById(R.id.album_id);
        }
    }
}
