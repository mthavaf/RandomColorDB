package com.example.randomcolordb;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.randomcolordb.entities.Color;
import com.example.randomcolordb.services.ColorService;
import com.example.randomcolordb.services.ColorServiceImpl;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    CustomAdapter adapter;

    ColorService mService;
    boolean mBound = false;
    List<String> colors = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button insertButton = findViewById(R.id.insert_button);


        RecyclerView recyclerView = findViewById(R.id.color_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CustomAdapter(colors);
        recyclerView.setAdapter(adapter);


        insertButton.setOnClickListener(view -> {

            long currentMillis = Clock.systemDefaultZone().millis();
            String val = Integer.toHexString((int) (currentMillis % 16777215));
            Color color = new Color();
            color.id = currentMillis;
            color.color = val;
            AsyncTask.execute(() -> {
                mService.insertColors(color);
            });
        });

        Button loadButton = findViewById(R.id.load_button);
        loadButton.setOnClickListener(view -> {
            colors.clear();
            mService.getAll(colorsFromDB -> {
                colors.addAll(colorsFromDB.stream().map(color -> color.color).collect(Collectors.toList()));
                updateList();
            });


        });
    }

    private void updateList() {
        runOnUiThread(() -> {
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, ColorServiceImpl.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);


    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            ColorServiceImpl.ColorServiceBinder binder = (ColorServiceImpl.ColorServiceBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

}