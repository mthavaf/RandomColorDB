package com.example.randomcolordb.services;

import com.example.randomcolordb.entities.Color;

import java.util.List;

public interface ColorServiceGetAllCallback {
    void onComplete(List<Color> colors);
}
