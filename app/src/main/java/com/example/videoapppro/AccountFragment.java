package com.example.videoapppro;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    RelativeLayout account;
    ImageView imageaccount,facebook,instagram,github,google;
    LinearLayout itemaccount;
    Animation move_down, move_up;
    TextView textname,textschool;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_account,container,false);
       imageaccount = v.findViewById(R.id.imageaccount);
       itemaccount = v.findViewById(R.id.itemaccount);
       account = v.findViewById(R.id.account);
       facebook = v.findViewById(R.id.facebook);
       instagram = v.findViewById(R.id.instagram);
        github = v.findViewById(R.id.github);
        google = v.findViewById(R.id.google);
        textname = v.findViewById(R.id.textname);
        textschool = v.findViewById(R.id.textschool);

        move_down = AnimationUtils.loadAnimation(getContext(), R.anim.move_down);
        move_up = AnimationUtils.loadAnimation(getContext(), R.anim.move_up);

        itemaccount.startAnimation(move_up);
        imageaccount.startAnimation(move_up);

        textname.setAnimation(move_down);
        textschool.setAnimation(move_down);


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/tihon.satthu.94"));
                startActivity(intent);
            }
        });

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/"));
                startActivity(intent);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/"));
                startActivity(intent);
            }
        });




        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
