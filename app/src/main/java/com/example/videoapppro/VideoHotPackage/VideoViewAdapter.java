package com.example.videoapppro.VideoHotPackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoapppro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoViewAdapter extends RecyclerView.Adapter<VideoViewAdapter.ViewHolder> {
    ArrayList<Video> listVideoList;
    Context context;


    public VideoViewAdapter(ArrayList<Video> listVideoList) {
        this.listVideoList = listVideoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.video_hot_list_item,parent,false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Video videos = listVideoList.get(position);
        String avatar = videos.getAvatar();
        String title = videos.getTitle();
        holder.title_item_list_hot.setText(title);
        Picasso.with(holder.item_list_hot.getContext()).load(avatar).fit().centerInside().into(holder.item_list_hot);
        holder.item_list_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ViewVideoHot.class);
                intent.putExtra("file_mp4",videos.getYoutube_url());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listVideoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_list_hot;
        TextView title_item_list_hot;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_list_hot = itemView.findViewById(R.id.item_list_hot);
            title_item_list_hot = itemView.findViewById(R.id.title_item_list_hot);
        }
    }
}
