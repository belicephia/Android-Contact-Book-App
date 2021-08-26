package com.example.myapplication;

import android.content.Context;
import androidx.room.Room;
import com.example.myapplication.db.AppDatabase;

/**
 * public class that create an database if not already being build or open the database if it already exist
 * https://developer.android.com/training/data-storage/roomhttps://developer.android.com/training/data-storage/room
 * https://stackoverflow.com/questions/15498654/java-is-there-any-reason-to-check-if-a-singleton-is-null-twice
 */
public class Credb {
    private static AppDatabase single_instance;

    public static AppDatabase getInstance(Context context){
        if(single_instance == null){
            single_instance =  Room.databaseBuilder(context, AppDatabase.class, "Fulltable").allowMainThreadQueries().build();
        }
        return single_instance;
    }
}
