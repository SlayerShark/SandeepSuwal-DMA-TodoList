package com.example.sandeepsuwal_todolist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import java.util.Date;

public class UpdateTodoFragment extends Fragment {

    private EditText titleUpdateText , descriptionUpdateText;
    private Button updateButton;
    private MainViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_todo, container, false);


        Bundle bundleReceived = this.getArguments();
        String id = bundleReceived.get("id").toString();
        String title = bundleReceived.get("title").toString();
        String description = bundleReceived.get("description").toString();

//        Log.d("TAG", "onCreate: "+bundleReceived.get("id").toString());

        titleUpdateText = view.findViewById(R.id.title_up);
        descriptionUpdateText = view.findViewById(R.id.description_up);
        updateButton = view.findViewById(R.id.btnUpdate);

        titleUpdateText.setText(title);
        descriptionUpdateText.setText(description);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleUpdateText.getText().toString();
                String description = descriptionUpdateText.getText().toString();

                Todo todo = new Todo(title, description, 1, new Date());
                todo.setId(Integer.parseInt(id));
                viewModel.update(todo);

                Toast.makeText(getActivity(), "Updated Data Successfully", Toast.LENGTH_SHORT).show();

                FragmentManager fm = getParentFragmentManager();
                fm.popBackStack();
            }
        });

        return view;
    }

}