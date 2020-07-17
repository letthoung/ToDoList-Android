package com.example.tofolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_create_to_do.*

class CreateToDo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_to_do)

        button.setOnClickListener {
            var todoDesc = "";
            if (checkBox.isChecked) {
                todoDesc = "!" + editText.text.toString();
            } else {
                todoDesc = editText.text.toString();
            }
            var prefs = getSharedPreferences("com.example.todolist.sharedprefs", Context.MODE_PRIVATE);
            var todos = prefs.getStringSet("todos", setOf())?.toMutableSet();
            todos?.add(todoDesc);
            Log.d("todoList", todos.toString());
            prefs.edit().putStringSet("todos",todos).apply();
            finish();
        }
    }
}