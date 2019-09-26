package com.example.videoapppro.VideoCategoryPackage;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.videoapppro.R;
import com.example.videoapppro.VideoHotPackage.VideoHotAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class VideoCategory extends Fragment {
    String url = "https://demo5639557.mockable.io/getItemCategory";
    private static final String TAG = "VideoCategory";
    ArrayList<Video> arrayListcate;
    RecyclerView rv_videocategory;
    ShimmerFrameLayout shimmerFrameLayout;

    public VideoCategory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_category,container,false);
        rv_videocategory = view.findViewById(R.id.rv_videocategory);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayListcate = new ArrayList<>();
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
            shimmerFrameLayout.startShimmer();
            super.onPostExecute(aVoid);
            try {
                JSONArray arrayCate = new JSONArray(result);
                for (int i =0;i<arrayCate.length();i++){
                    JSONObject objectCate = arrayCate.getJSONObject(i);
                    arrayListcate.add(new Video(objectCate.getString("id"),objectCate.getString("title"),objectCate.getString("avatar"),objectCate.getString("file_mp4")));
                }
                RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL);
                VideoCategoryAdapter videoCategoryAdapter = new VideoCategoryAdapter(arrayListcate);
				
                rv_videocategory.setLayoutManager(layoutManager);
                rv_videocategory.setAdapter(videoCategoryAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setVisibility(View.GONE);

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
