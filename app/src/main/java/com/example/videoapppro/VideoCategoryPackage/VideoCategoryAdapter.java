package com.example.videoapppro.VideoCategoryPackage;

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

public class VideoCategoryAdapter extends RecyclerView.Adapter<VideoCategoryAdapter.Viewholder> {
    ArrayList<Video> arrayList;
    Context context;

    public VideoCategoryAdapter(Context context) {
        this.context = context;
    }

    public VideoCategoryAdapter(ArrayList<Video> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.video_category_item,parent,false);
        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final Video videos = arrayList.get(position);
              String title = videos.getTitle();
              String avatar = videos.getAvatar();
              holder.title_category.setText(title);
              Picasso.with(holder.avatar_category.getContext()).load(avatar).fit().centerInside().into(holder.avatar_category);
              holder.avatar_category.setOnClickListener(new View.OnClickListener() {
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
        ImageView avatar_category;
        TextView  title_category;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            avatar_category = itemView.findViewById(R.id.avatar_category);
            title_category = itemView.findViewById(R.id.title_category);
        }
    }
}
