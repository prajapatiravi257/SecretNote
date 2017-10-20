package com.tushar.secrectnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NotesList extends AppCompatActivity {
    ListView listview;
    ListAdapter adapter;
    DataHelper db;
    FloatingActionButton fab;
    Note note;
    List<Note> list;

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        listview = (ListView) findViewById(R.id.listview);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        db = new DataHelper(this);

        list = new ArrayList<>();
        list.addAll(db.getAllNotes());
        adapter = new ListAdapter(this, R.layout.list_item, list);
        listview.setAdapter(adapter);

        Log.d("List count", String.valueOf(list.size()));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesList.this, AddNotes.class);
                startActivity(intent);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                note = adapter.getItem(i);

                Intent intent = new Intent(NotesList.this, UpdateNote.class);
                intent.putExtra("note", note);
                startActivity(intent);
            }
        });
    }


}
