package com.example.videoapppro.VideoCategoryPackage;

import android.app.ActionBar;
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

public class VideoViewAdapter extends RecyclerView.Adapter<VideoViewAdapter.Viewholder> {
    ArrayList<Video> arrayList;
    Context context;

    public VideoViewAdapter(Context context) {
        this.context = context;
    }

    public VideoViewAdapter(ArrayList<Video> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.video_category_list_item,parent,false);
        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final Video videos = arrayList.get(position);
        String avatar = videos.getAvatar();
        String title = videos.getTitle();
        holder.title_item_list_category.setText(title);
        Picasso.with(holder.item_list_category.getContext()).load(avatar).fit().centerInside().into(holder.item_list_category);
        holder.item_list_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ViewVideoCategory.class);
                intent.putExtra("file_mp4",videos.getFile_mp4());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView item_list_category;
        TextView title_item_list_category;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            item_list_category = itemView.findViewById(R.id.item_list_category);
            title_item_list_category = itemView.findViewById(R.id.title_item_list_category);
        }
    }
}
