package com.example.nikita99.trytopass;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.nikita99.trytopass.roomDataBase.AppDataBase;

public class App extends Application {

    public static App instance;

    private AppDataBase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public AppDataBase getDatabase() {
        return database;
    }

    public static App getInstance() {
        return instance;
    }

}
