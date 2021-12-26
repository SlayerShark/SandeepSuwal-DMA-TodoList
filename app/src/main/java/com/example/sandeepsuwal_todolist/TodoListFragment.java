package com.example.sandeepsuwal_todolist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class TodoListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private MainViewModel viewModel;
    private List<Todo> todos;
    private FloatingActionButton fabButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        fabButton = view.findViewById(R.id.fab_button);
        recyclerView = view.findViewById(R.id.todos_list);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
//            viewModel= new ViewModelProvider(getActivity()).get(MainViewModel.class);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTodoFragment fragment = AddTodoFragment.newInstance();
                FragmentManager fm = getParentFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit(); //backstack for using back button
            }
        });

        Observer<List<Todo>> newObserver = new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                TodoAdapter adapter = new TodoAdapter();
                if (todos != null)
                    adapter.setData(todos);
                    recyclerView.setAdapter(adapter);

            }
        };

/*
        viewModel.getTodos().observe(getViewLifecycleOwner(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                adapter = new TodoAdapter();
                if (todos != null)
                    adapter.setData(todos);
            }
        });
*/

//        recyclerView.setAdapter(adapter);
    }
}