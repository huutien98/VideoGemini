package com.example.videoapppro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.videoapppro.VideoHotPackage.Video;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";
    static final String DB_NAME_TABLE = "ClickHistory";
    static final String DB_NAME= "ClickHistory.db";
    static final int VERSION= 2;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;
    public SQLite(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ClickHistory = "CREATE TABLE ClickHistory ( "+
                "title TEXT," +
                "avatar TEXT," +
                "file_mp4 TEXT )";

        sqLiteDatabase.execSQL(ClickHistory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i!= i1){
            sqLiteDatabase.execSQL("drop table if exists "+ DB_NAME_TABLE);
            onCreate(sqLiteDatabase);
        }
    }
    public void insertItem(String title, String avatar, String file_mp4){
        sqLiteDatabase = getWritableDatabase();
        contentValues= new ContentValues();
        contentValues.put("title", title);
        contentValues.put("avatar", avatar);
        contentValues.put("file_mp4", file_mp4);

        sqLiteDatabase.insert(DB_NAME_TABLE, null, contentValues);
        closeDB();
    }
    public List<Video> getAll() {
        List<Video> videoList= new ArrayList<>();
        Video video;
        sqLiteDatabase= getReadableDatabase();
        cursor= sqLiteDatabase.query(false, DB_NAME_TABLE, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String title= cursor.getString(cursor.getColumnIndex("title"));
            String avatar= cursor.getString(cursor.getColumnIndex("avatar"));
            String file_mp4= cursor.getString(cursor.getColumnIndex("file_mp4"));
            video= new Video(null, title, avatar, file_mp4);
            videoList.add(video);
        }
        closeDB();
        return videoList;
    }
    public void closeDB() {

        if (sqLiteDatabase != null) sqLiteDatabase.close();
        if (contentValues != null) contentValues.clear();
        if (cursor != null) cursor.close();
    }
    }




