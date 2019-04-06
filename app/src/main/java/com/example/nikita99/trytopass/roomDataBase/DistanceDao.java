package com.example.nikita99.trytopass.roomDataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DistanceDao {

    /**
     * methods for update, insert and update data in table Distance
     * */
    @Insert
    void insert(Distance distance);

    @Delete
    void delete(Distance distance);

    @Update
    void update(Distance distance);

    @Query("SELECT * FROM distance")
    List<Distance> getAll();
}
