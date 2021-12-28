package com.example.sandeepsuwal_todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public LiveData<List<Todo>> getTodos() {
        return repository.getAllTodos();
    }

    public void insert(Todo todo) {
        repository.addTodo(todo);
    }

    public void update(Todo todo) {
        repository.editById(todo);
    }
}
