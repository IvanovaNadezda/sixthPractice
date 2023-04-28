package com.example.sixthpractice.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.sixthpractice.data.database.Entity.OwlEntity;
import com.example.sixthpractice.data.database.RoomDatabase.OwlRoomDatabase;
import com.example.sixthpractice.data.database.dao.OwlDao;
import com.example.sixthpractice.data.models.Owl;

import java.util.List;
import java.util.stream.Collectors;

public class OwlRepository {
    private final OwlDao mOwlDao;
    private final LiveData<List<Owl>> mAllOwls;

    private final Context context;

    OwlRoomDatabase roomDatabase;
    public OwlRepository(Context applicationContext) {
        context = applicationContext;
        roomDatabase = OwlRoomDatabase.getDatabase(context);
        mOwlDao = OwlRoomDatabase.getDatabase(context).owlDao();
        mAllOwls = Transformations.map(mOwlDao.getAllOwls(), entities -> entities.stream()
                .map(OwlEntity::toOwl).collect(Collectors.toList()));
    }
    public LiveData<List<Owl>> getAllOwls() {
        return mAllOwls;
    }

    public void insert(OwlEntity owl) {
        OwlRoomDatabase.databaseWriteExecutor.execute(() -> {
            mOwlDao.insert(owl);
        });
    }
}

