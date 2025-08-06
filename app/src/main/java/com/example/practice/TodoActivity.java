package com.example.practice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.adapter.TodoAdapter;
import com.example.practice.item.TodoItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class TodoActivity extends AppCompatActivity {

    private Button backBtn;
    private Button addTodoBtn;
    private EditText todoEdit;
    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;
    private List<TodoItem> todoList;
    private SharedPreferences sharedPreferences;
    private static final String PREF_KEY = "items";
    private static final String PREF_NAME = "my_app_prefs";

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.todo_layout);


        backBtn = findViewById(R.id.backBtn);
        addTodoBtn = findViewById(R.id.addTodoBtn);
        todoEdit = findViewById(R.id.todoEdit);
        recyclerView = findViewById(R.id.recyclerView);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        todoList = loadData();

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        addTodoBtn.setOnClickListener(v -> {
            addTodoItem(todoEdit);
        });
    }

    public void addTodoItem(EditText text){
        String textToAdd = text.getText().toString();

        if(!textToAdd.isEmpty()){
            TodoItem item = new TodoItem(textToAdd);
            todoList.add(item);
            todoAdapter.notifyItemInserted(todoList.size() - 1);
            saveData();
            text.setText("");
        }
    }

    // to save list in local
    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(todoList);
        editor.putString(PREF_KEY, json);
        editor.apply();
    }

    // to load local data
    private List<TodoItem> loadData() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(PREF_KEY, null);
        Type type = new TypeToken<ArrayList<TodoItem>>() {}.getType();
        return json == null ? new ArrayList<>() : gson.fromJson(json, type);
    }
}
