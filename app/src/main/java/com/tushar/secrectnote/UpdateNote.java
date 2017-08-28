package com.tushar.secrectnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rio on 27/8/17.
 */

public class UpdateNote extends AppCompatActivity {
    private TextView header;
    private EditText title;
    private EditText desc;
    private Button updateNote;
    private DataHelper db;
    private Note note;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();


        updateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!title.getText().toString().isEmpty() && !desc.getText().toString().isEmpty()) {
                    if (db.updateNote(title.getText().toString(), desc.getText().toString(), getIntent().getStringExtra("id"))) {
                        Toast.makeText(getApplicationContext(), "Note updated :)", Toast.LENGTH_SHORT).show();
                        desc.getText().clear();
                        title.getText().clear();
                    } else {
                        Toast.makeText(getApplicationContext(), "note doesn't exist to be updated :(", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    title.setError("Fields required");
                    desc.setError("Fields required");
                }
            }
        });
    }

    private void initView() {
        header = (TextView) findViewById(R.id.note);
        title = (EditText) findViewById(R.id.tittle);
        desc = (EditText) findViewById(R.id.desc);
        updateNote = (Button) findViewById(R.id.add);
        db = new DataHelper(this);
        title.setText(getIntent().getStringExtra("title"));
        desc.setText(getIntent().getStringExtra("desc"));
        header.setText("Update Note");
        Bundle args = getIntent().getExtras();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UpdateNote.this, NotesList.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
