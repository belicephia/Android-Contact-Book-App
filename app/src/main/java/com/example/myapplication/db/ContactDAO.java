package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * create an interface called ContactDao useage room's build-in delete, insert and update methods
 * using Data access object(DAO)
 * https://developer.android.com/training/data-storage/room
 */
@Dao
public interface ContactDAO {
    @Query("SELECT * FROM Fulltable WHERE user_id_email = :userid")
    List<Contact> loadAllByIds(String userid);

    @Query("SELECT * FROM Fulltable WHERE contact_id = :contact_id")
    Contact findIdivContact(String contact_id);

    @Delete
    void delete(Contact contact);

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);
}
