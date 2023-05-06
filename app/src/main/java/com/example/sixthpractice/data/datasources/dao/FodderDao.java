package com.example.sixthpractice.data.datasources.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sixthpractice.data.datasources.Entity.FodderEntity;

import java.util.List;

@Dao
public interface FodderDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(FodderEntity fodder);
    @Query(value = "DELETE FROM fodder_table")
    void deleteAll();
    @Query("SELECT * FROM fodder_table ORDER BY id")
    LiveData<List<FodderEntity>> getAllFodders();
}

