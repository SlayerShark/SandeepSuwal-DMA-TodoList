package com.example.sandeepsuwal_todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Repository repository;
    List<Todo> todos;
    public static final String Tag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = Repository.getInstance(this);
        todos = repository.getAllTodos();
        Log.d(Tag, "todos size: "+ todos.size());


    }
}