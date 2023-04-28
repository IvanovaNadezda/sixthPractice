package com.example.sixthpractice.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sixthpractice.data.database.Entity.OwlEntity;

import java.util.List;

@Dao
public interface OwlDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(OwlEntity owl);
    @Query("DELETE FROM owl_table")
    void deleteAll();
    @Query("SELECT * FROM owl_table ORDER BY id")
    LiveData<List<OwlEntity>> getAllOwls();
}
