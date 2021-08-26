package com.example.myapplication.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Using @Entity as a signature to tell andriod we are looking for database information
 * name the local database as fulltable
 */
@Entity(tableName = "Fulltable")

/**
 * create a class called Contact that store the user's contact information into the database
 */
public class Contact {
    /**
     * https://developer.android.com/reference/android/arch/persistence/room/PrimaryKey
     * auto generate the contact_id
     */
    @PrimaryKey(autoGenerate = true)
    public int contact_id;

    /**
     * linking the database columnInfo name to the sqlite name
     */
    @ColumnInfo(name = "contact_nameofuser")
    public String contact_name_sql;


    @ColumnInfo(name = "contact_phone")
    public String contact_phone_sql;

    @ColumnInfo(name = "contact_email")
    public String contact_email_sql;

    @ColumnInfo(name = "user_id_email")
    public String user_id_email_sql;



}
