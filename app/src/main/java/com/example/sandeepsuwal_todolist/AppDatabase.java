package com.example.sandeepsuwal_todolist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Todo.class}, version = 1, exportSchema = false)
@TypeConverters(DataConverter.class)
public  abstract class AppDatabase extends RoomDatabase {


    public static final String DATABASE_NAME = "todo_db";

    private static AppDatabase sInstance;
    private static final Object LOCK = new Object();

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public static AppDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        //.allowMainThreadQueries()
                        .build();
            }
        }

        return sInstance;
    }
    public abstract TodoDao todoDao();
}
