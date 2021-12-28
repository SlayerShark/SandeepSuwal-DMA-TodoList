package com.example.sandeepsuwal_todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Query("select * from todo order by priority")
    public LiveData<List<Todo>> getAllTodos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Todo todo);

    @Query("DELETE FROM todo WHERE id = :id")
    void deleteById(int id);

    @Update()
    void updateById(Todo todo);
}
