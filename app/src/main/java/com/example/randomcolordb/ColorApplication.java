package com.example.randomcolordb;

import android.app.Application;

import androidx.room.Room;

import com.example.randomcolordb.database.ColorDatabase;
import com.example.randomcolordb.services.ColorService;
import com.example.randomcolordb.services.ColorServiceImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ColorApplication extends Application {

    public ExecutorService executorService;
    public ColorService colorService;
    public ColorDatabase db;


    @Override
    public void onCreate() {
        super.onCreate();
        executorService = Executors.newFixedThreadPool(4);
        colorService = new ColorServiceImpl();
        db = Room.databaseBuilder(getApplicationContext(),
                ColorDatabase.class, "color-database").build();
    }


}
