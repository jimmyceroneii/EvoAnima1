package com.example.evoanima;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Environment {
    private String name;
    private String color;
    private String description;

    // for now we are hardcoding the animas into a single environment
    public Environment(String name, String description, String color) {
        this.name = name;
        this.color = color;
        this.description = description;
    }

    public Environment() {

    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return name;
    }

}
