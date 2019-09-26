package com.example.videoapppro.VideoHotPackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoapppro.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoHotAdapter extends RecyclerView.Adapter<VideoHotAdapter.Viewhoder> {
    ArrayList<Video> videoArrayList;
    Context context;
    IOnClickHistory onClickHistory;

    public void setOnClickHistory(IOnClickHistory onClickHistory) {
        this.onClickHistory = onClickHistory;
    }

    public VideoHotAdapter(Context context) {
        this.context = context;
    }

    public VideoHotAdapter(ArrayList<Video> videoArrayList) {
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.video_hot_item,parent,false);
        return new Viewhoder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        final Video videos = videoArrayList.get(position);

            String title = videos.getTitle();
            String avatar = videos.getAvatar();
            holder.title_video.setText(title);
            Picasso.with(holder.avatar_video.getContext()).load(avatar).fit().centerInside().into(holder.avatar_video);
            holder.avatar_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickHistory.onClick(videos.getTitle(), videos.getAvatar(), videos.youtube_url);
                    Intent intent = new Intent(view.getContext(), ViewVideoHot.class);
                    intent.putExtra("file_mp4", videos.getYoutube_url());
                    view.getContext().startActivity(intent);
                }
            });
        }


    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        ImageView avatar_video;
        TextView title_video;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);

            avatar_video = itemView.findViewById(R.id.avatar_video);
            title_video = itemView.findViewById(R.id.title_video);
        }
    }
}
