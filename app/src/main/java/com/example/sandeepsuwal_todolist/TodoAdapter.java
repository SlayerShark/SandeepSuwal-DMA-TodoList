package com.example.sandeepsuwal_todolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder>{

    List<Todo> data;

    private static AppDatabase sInstance;

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextview;
        private TextView descriptionTextview;
        private ImageButton delButton;
        private Button upButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextview = itemView.findViewById(R.id.title_tv);
            descriptionTextview = itemView.findViewById(R.id.description_tv);

            delButton = itemView.findViewById(R.id.btnDelete);
            upButton = itemView.findViewById(R.id.btnUpdate);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    public void setData(List<Todo> todos){
        data = todos;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Todo todo = data.get(position);
        holder.titleTextview.setText(todo.getTitle());
        holder.descriptionTextview.setText(todo.getDescription());

        holder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = Room.databaseBuilder(holder.titleTextview.getContext(),
                        AppDatabase.class, "todo_db")
                        .allowMainThreadQueries()
                        .build();
                TodoDao todoDao = db.todoDao();

                //this is to delete the data from RoomDatabase
                todoDao.deleteById(todo.getId());

                //this is to delete the data from List
                data.remove(todo);

                Toast.makeText(v.getContext(), "Deleted Data Successfully", Toast.LENGTH_SHORT).show();

                //update the fresh list of List data to recieve
                notifyDataSetChanged();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putInt("id", todo.getId());
                bundle.putString("title" , todo.getTitle());
                bundle.putString("description" , todo.getDescription());

                UpdateTodoFragment fragment = new UpdateTodoFragment();

                fragment.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        if(data == null)
            return 0;

        return data.size();
    }


}
