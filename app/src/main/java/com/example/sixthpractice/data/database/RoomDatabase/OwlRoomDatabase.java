package com.example.sixthpractice.data.database.RoomDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.sixthpractice.R;
import com.example.sixthpractice.data.database.Entity.OwlEntity;
import com.example.sixthpractice.data.database.dao.OwlDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {OwlEntity.class}, version = 1, exportSchema = false)
public abstract class OwlRoomDatabase extends RoomDatabase {
    public abstract OwlDao owlDao();
    private static volatile OwlRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static OwlRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OwlRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            OwlRoomDatabase.class, "owl_database")
                            .addCallback(sRoomDatabaseCallback).build();}}
        }
        return INSTANCE;
    }


    public static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                OwlDao dao = INSTANCE.owlDao();
                dao.deleteAll();
                OwlEntity owl = new OwlEntity("Тучка","Бородатая неясыть", R.drawable.owl2);
                dao.insert(owl);
                owl = new OwlEntity("Лютик","Сипуха", R.drawable.owl1);
                dao.insert(owl);
                owl = new OwlEntity("Ева","Ушастая неясыть", R.drawable.owl6);
                dao.insert(owl);
            });
        }
    };
}