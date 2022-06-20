package com.example.randomcolordb.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.randomcolordb.dao.ColorDao;
import com.example.randomcolordb.entities.Color;

@Database(entities = {Color.class}, version = 2)
public abstract class ColorDatabase extends RoomDatabase {
    public abstract ColorDao colorDao();
}
