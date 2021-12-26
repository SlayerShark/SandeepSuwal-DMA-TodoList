package com.example.sandeepsuwal_todolist;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Repository {

    private static Repository sInstance;    //single instance

    private List<Todo> todos;

    private AppDatabase database;

    private Repository(Context context) {
        database = AppDatabase.getInstance(context);
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todos = database.todoDao().getAllTodos();
            }
        });
    }


    /*-------------------singleton class---------------------------------*/
    public static Repository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Repository(context);
        }
        return sInstance;
    }

    public List<Todo> getAllTodos() {
        return todos;
    }

    /*-------------------------------CRUD add data--------------------------------------*/
    public void addTodo(Todo todo) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                database.todoDao().insert(todo);
            }
        });
    }

    /*---------------------------------CRUD read data----------------------------------*/
    public Todo getTodoById(UUID id) {
//        for (int i = 0; i < todos.size(); i++) {
//            Todo todo = todos.get(i);
//            if (todo.getId().equals(id)) {
//                return todo;
//            }
//        }
        return null;
    }

    /*---------------------------------CRUD delete data------------------------------*/
    public void delete(UUID id) {
//        Todo todo = getTodoById(id);
//        if (todo != null)
//        todos.remove(todo);
    }

        /*-------------------------------CRUD update data-------------------------------------*/
        public Todo update(Todo todo){
//        Todo newTodo = getTodoById(todo.getId());
//        newTodo.setPriority(todo.getPriority());
//        newTodo.setDescription(todo.getDescription());
//        newTodo.setTitle(todo.getTitle());
//        newTodo.setUpdatedAt(new Date());
            return null;
        }

}
