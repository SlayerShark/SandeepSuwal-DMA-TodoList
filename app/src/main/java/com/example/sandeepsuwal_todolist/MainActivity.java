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
        Todo todo = todos.get(0);
        Log.d(Tag, ""+todo);

        //Checking through log if deleted or updated
        //repository.delete(todo.getId());
        todo.setTitle("xxx");
        repository.update(todo);
        //Log.d(Tag, "todos size: "+todos.size());
        Log.d(Tag, "todos size: "+todos.get(0));


    }
}