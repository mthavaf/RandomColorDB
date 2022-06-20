package com.example.randomcolordb.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.randomcolordb.entities.Color;

import java.util.List;

@Dao
public interface ColorDao {

    @Insert
    void insertColors(Color... colors);

    @Query("SELECT * FROM time_color")
    List<Color> getAll();
}
