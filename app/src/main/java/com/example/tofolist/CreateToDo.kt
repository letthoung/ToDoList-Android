package com.example.tofolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_create_to_do.*

class CreateToDo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_to_do)

        if (intent.hasExtra("todoDesc")){
            val todo = intent.extras?.getString("todoDesc");
            editText.setText(todo);
            editText.isEnabled = false;
            checkBox.visibility = View.INVISIBLE;
            button.setText("COMPLETE");
            button.setOnClickListener{
                var prefs = getSharedPreferences(getString(R.string.shared_pref),Context.MODE_PRIVATE);
                var todos = prefs.getStringSet(getString(R.string.pref_key), setOf())?.toMutableSet();
                todos?.remove(todo);
                prefs.edit().putStringSet(getString(R.string.pref_key), todos).apply();
                finish();
            }
        } else {
            button.setOnClickListener {
                var todoDesc = "";
                if (checkBox.isChecked) {
                    todoDesc = "!" + editText.text.toString();
                } else {
                    todoDesc = editText.text.toString();
                }
                var prefs =
                    getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
                var todos = prefs.getStringSet(getString(R.string.pref_key), setOf())?.toMutableSet();
                todos?.add(todoDesc);
                Log.d("todoList", todos.toString());
                prefs.edit().putStringSet(getString(R.string.pref_key), todos).apply();
                finish();
            }
        }
    }
}