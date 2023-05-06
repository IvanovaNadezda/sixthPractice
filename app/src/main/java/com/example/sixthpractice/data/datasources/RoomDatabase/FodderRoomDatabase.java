package com.example.sixthpractice.data.datasources.RoomDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.sixthpractice.R;
import com.example.sixthpractice.data.datasources.Entity.FodderEntity;
import com.example.sixthpractice.data.datasources.dao.FodderDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {FodderEntity.class}, version = 1, exportSchema = false)
public abstract class FodderRoomDatabase extends RoomDatabase {
    public abstract FodderDao fodderDao();
    private static volatile FodderRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static FodderRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FodderRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    FodderRoomDatabase.class, "fodder_database")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    public static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                FodderDao dao = INSTANCE.fodderDao();
                dao.deleteAll();
                FodderEntity fodder = new FodderEntity("Мышка",R.drawable.mouse);
                dao.insert(fodder);
                fodder = new FodderEntity("Жучок",R.drawable.beatle);
                dao.insert(fodder);
            });
        }
    };
}

