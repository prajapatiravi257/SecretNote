package com.tushar.secrectnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNotes extends AppCompatActivity {
    EditText title, desc;
    private DataHelper not;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        not = new DataHelper(this);
        add = (Button) findViewById(R.id.add);
        title = (EditText) findViewById(R.id.tittle);
        desc = (EditText) findViewById(R.id.desc);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!title.getText().toString().isEmpty() && !desc.getText().toString().isEmpty()) {

                    if (not.insertNote(title.getText().toString(), desc.getText().toString())) {
                        Toast.makeText(getBaseContext(), "Note is added", Toast.LENGTH_SHORT).show();
                        title.getText().clear();
                        desc.getText().clear();
                    } else {
                        Toast.makeText(getBaseContext(), "note is not added", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    title.setError("Fields required");
                    desc.setError("Fields required");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddNotes.this, NotesList.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
