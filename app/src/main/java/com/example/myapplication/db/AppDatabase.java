package com.example.myapplication.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;


/**
 * create an abstract class called AppDatabase that use RoomDatabase
 * android.arch.persistence.room.RoomDatabase. Base class for all Room databases.
 * This is a local database that will be stored on your own android device.
 * https://developer.android.com/training/data-storage/room
 */
@Database(entities =  {Contact.class},version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ContactDAO contactDao();
}
