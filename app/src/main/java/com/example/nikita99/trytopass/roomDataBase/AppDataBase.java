package com.example.nikita99.trytopass.roomDataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Distance.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract DistanceDao distanceDao();
}