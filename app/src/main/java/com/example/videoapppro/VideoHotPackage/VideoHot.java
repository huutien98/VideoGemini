package com.example.videoapppro.VideoHotPackage;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.videoapppro.R;
import com.example.videoapppro.SQLite;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class VideoHot extends Fragment {
    String url = "https://demo5639557.mockable.io/getVideoHot";
    private static final String TAG = "VideoHot";
    ArrayList<Video> arrayList;
    RecyclerView rv_videohot;
    ShimmerFrameLayout shimmer_videohot;
    SQLite sqLite;
    public VideoHot() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_hot,container,false);
        rv_videohot = view.findViewById(R.id.rv_videohot);
        shimmer_videohot = view.findViewById(R.id.shimmer_videohot);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList<>();
        new DoGetProduct(url).execute();
    }

    class DoGetProduct extends AsyncTask<Void, Void, Void> {
        String urlnew;
        String result = "";

        public DoGetProduct(String urlnew) {
            this.urlnew = urlnew;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            shimmer_videohot.startShimmer();
            super.onPostExecute(aVoid);
            try {
                JSONArray array = new JSONArray(result);
                for (int i = 0 ; i<array.length();i++){
                    JSONObject objectVideo = array.getJSONObject(i);
                    arrayList.add(new Video(objectVideo.getString("id"),objectVideo.getString("title"),objectVideo.getString("avatar"),objectVideo.getString("file_mp4")));
                }
                RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL);
                VideoHotAdapter videoHotAdapter = new VideoHotAdapter(arrayList);
				                rv_videohot.setAdapter(videoHotAdapter);
                rv_videohot.setLayoutManager(layoutManager);
                sqLite= new SQLite(getContext());
                videoHotAdapter.setOnClickHistory(new IOnClickHistory() {
                    @Override
                    public void onClick(String title, String avatar, String mp4) {
                        sqLite.insertItem(title, avatar, mp4);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
            shimmer_videohot.stopShimmer();
            shimmer_videohot.setVisibility(View.GONE);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(urlnew);
                URLConnection connection = null;
                connection = url.openConnection();
                InputStream is = connection.getInputStream();
                int byteCharacter;

                while ((byteCharacter = is.read()) != -1) {
                    result += (char) byteCharacter;
                }

                Log.d(TAG, "doInBackground: " + result);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }


}
