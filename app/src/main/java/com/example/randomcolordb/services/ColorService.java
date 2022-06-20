package com.example.randomcolordb.services;


import com.example.randomcolordb.entities.Color;

import java.util.List;

public interface ColorService {
    void insertColors(Color... colors);

    void getAll(ColorServiceGetAllCallback callback);
}
