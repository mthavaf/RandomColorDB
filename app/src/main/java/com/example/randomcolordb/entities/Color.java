package com.example.randomcolordb.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "time_color")
public class Color {

    @NonNull
    @PrimaryKey
    public long id;

    @ColumnInfo
    public String color;
}
