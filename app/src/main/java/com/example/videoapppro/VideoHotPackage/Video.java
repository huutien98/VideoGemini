package com.example.videoapppro.VideoHotPackage;

public class Video {
    String id;
    String title;
    String avatar;
    String youtube_url;


    public Video(String id, String title, String avatar, String youtube_url) {
        this.id = id;
        this.title = title;
        this.avatar = avatar;
        this.youtube_url = youtube_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getYoutube_url() {
        return youtube_url;
    }

    public void setYoutube_url(String youtube_url) {
        this.youtube_url = youtube_url;
    }


}
