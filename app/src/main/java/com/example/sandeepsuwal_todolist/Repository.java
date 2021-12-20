package com.example.sandeepsuwal_todolist;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Repository {

    private static Repository sInstance;    //single instance

    private ArrayList<Todo> todos;

    private Repository(Context context) {
        todos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Todo todo = new Todo();
            todo.setTitle("title" + i);
            todo.setDescription("description" + i);
            todo.setPriority(i);
            todos.add(todo);
        }
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
    public void addTodo(Todo todo){
        todos.add(todo);
    }

    /*---------------------------------CRUD read data----------------------------------*/
    public Todo getTodoById(UUID id) {
        for (int i = 0; i < todos.size(); i++) {
            Todo todo = todos.get(i);
            if (todo.getId().equals(id)) {
                return todo;
            }
        }
        return null;
    }

    /*---------------------------------CRUD delete data------------------------------*/
    public void delete(UUID id) {
/*        for (Todo todo : todos) {
            if (todo.getId().equals(id)) {
                todos.remove(todo);
            }
        }
*/
        Todo todo = getTodoById(id);
        if (todo != null)
        todos.remove(todo);
    }

    /*-------------------------------CRUD update data-------------------------------------*/
    public Todo update(Todo todo) {
        Todo newTodo = getTodoById(todo.getId());
        newTodo.setPriority(todo.getPriority());
        newTodo.setDescription(todo.getDescription());
        newTodo.setTitle(todo.getTitle());
        newTodo.setUpdatedAt(new Date());
        return newTodo;
    }
}
