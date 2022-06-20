package com.example.randomcolordb.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.randomcolordb.ColorApplication;
import com.example.randomcolordb.dao.ColorDao;
import com.example.randomcolordb.entities.Color;

import java.util.List;

public class ColorServiceImpl extends Service implements ColorService  {

    private final IBinder binder = new ColorServiceBinder();

    public class ColorServiceBinder extends Binder {
        public ColorService getService() {
            return ColorServiceImpl.this;
        }
    }

    @Override
    public void insertColors(Color... colors) {
        ColorDao colorDao = ((ColorApplication) getApplication()).db.colorDao();
        colorDao.insertColors(colors);
    }

    @Override
    public void getAll(ColorServiceGetAllCallback callback) {
        ((ColorApplication)getApplication()).executorService.execute(() -> {
            ColorDao colorDao = ((ColorApplication) getApplication()).db.colorDao();
            callback.onComplete(colorDao.getAll());
        });

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}

