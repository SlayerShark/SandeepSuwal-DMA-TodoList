package com.example.sandeepsuwal_todolist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class UpdateTodoFragment extends Fragment {

    private EditText titleUpdateText , descriptionUpdateText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        titleUpdateText.setText(title);
        descriptionUpdateText.setText(description);


        return view;
    }
}