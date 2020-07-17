package com.example.tofolist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    lateinit var layoutManager: LinearLayoutManager;
    lateinit var adapter: ToDoAdapter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        fab.setOnClickListener { view ->
            val intent = Intent(this,CreateToDo:: class.java);
            startActivity(intent);
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete_all) {
            var prefs = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
            prefs.edit().putString(getString(R.string.pref_key),null).apply();
            updateRecycler();
            return true;
        }
        return when (item.itemId) {
            R.id.action_delete_all -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume();
        updateRecycler();
    }

    fun updateRecycler() {
        var prefs = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE)
        var todos = prefs.getStringSet(getString(R.string.pref_key), setOf())?.toMutableSet();

        layoutManager = LinearLayoutManager(this);
        adapter = ToDoAdapter(todos!!.toList());

        recyclerView.layoutManager = layoutManager;
        recyclerView.adapter = adapter;
    }
}