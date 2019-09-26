package com.example.videoapppro;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoapppro.VideoHotPackage.IOnClickHistory;
import com.example.videoapppro.VideoHotPackage.Video;
import com.example.videoapppro.VideoHotPackage.VideoHotAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    RecyclerView rcvHistory;
    SQLite sqLite;
    List<Video> videoList;
    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history,container,false);
        rcvHistory= view.findViewById(R.id.rcvHistory);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvHistory.setLayoutManager(layoutManager);

        //get list tu sql
        sqLite= new SQLite(getContext());
        videoList= sqLite.getAll();
        VideoHotAdapter adapter= new VideoHotAdapter((ArrayList<Video>) videoList);
        rcvHistory.setAdapter(adapter);
         return view;
    }

}
