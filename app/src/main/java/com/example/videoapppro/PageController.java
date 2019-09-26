package com.example.videoapppro;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.videoapppro.VideoCategoryPackage.VideoCategory;
import com.example.videoapppro.VideoHotPackage.VideoHot;

public class PageController extends FragmentPagerAdapter {

    int tabCount;

    public PageController(FragmentManager fm,int tabCount) {

        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new VideoHot();
            case 1:
                return new VideoCategory();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
