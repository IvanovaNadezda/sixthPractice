package com.example.sixthpractice.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.sixthpractice.data.datasources.Entity.FodderEntity;
import com.example.sixthpractice.data.datasources.RoomDatabase.FodderRoomDatabase;
import com.example.sixthpractice.data.datasources.dao.FodderDao;
import com.example.sixthpractice.data.models.Fodder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FodderRepository {
    private final FodderDao mFodderDao;


    private final LiveData<List<Fodder>> mAllFodders;

    private final Context context;

    FodderRoomDatabase roomDatabase;
    public FodderRepository(Context applicationContext) {
        context = applicationContext;
        roomDatabase = FodderRoomDatabase.getDatabase(context);
        mFodderDao = FodderRoomDatabase.getDatabase(context).fodderDao();
        mAllFodders = Transformations.map(mFodderDao.getAllFodders(), entities -> entities.stream()
                .map(FodderEntity::toFodder).collect(Collectors.toList()));
    }
    public LiveData<List<Fodder>> getAllFodders() {
        return mAllFodders;
    }

    public void insert(FodderEntity fodder) {
        FodderRoomDatabase.databaseWriteExecutor.execute(() -> {
            mFodderDao.insert(fodder);
        });
    }
}
