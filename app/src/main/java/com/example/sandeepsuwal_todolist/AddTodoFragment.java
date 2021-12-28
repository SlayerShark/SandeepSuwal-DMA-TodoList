package com.example.sandeepsuwal_todolist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class AddTodoFragment extends Fragment {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button addButton;
    private MainViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static AddTodoFragment newInstance(){
        AddTodoFragment fragment = new AddTodoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_todo, container, false);
        titleEditText = view.findViewById(R.id.title_et);
        descriptionEditText = view.findViewById(R.id.description_et);
        addButton = view.findViewById(R.id.add_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        repository = Repository.getInstance(getActivity().getApplicationContext());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                Todo newTodo = new Todo(title, description, 1, new Date());

                viewModel.insert(newTodo);

//                TodoListFragment fragment = new TodoListFragment();
                FragmentManager fm = getParentFragmentManager();
                fm.popBackStack();
//                fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();

            }
        });
    }
}