package com.tushar.secrectnote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText setpss;
    Button done;
    SharedPreferences sp;
    SharedPreferences.Editor login_editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setpss = (EditText) findViewById(R.id.setpss);
        done = (Button) findViewById(R.id.done);
        sp = getSharedPreferences("my_login", Context.MODE_PRIVATE);
        if (sp.getString("login", "null").equals("1")) {
            setpss.setHint("Enter password");
            setpss.setFocusable(true);
        }


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!setpss.getText().toString().isEmpty()) {
                    if (!sp.getString("login", "null").equals("1")) {
                        login_editor = sp.edit();
                        login_editor.putString("passwd", setpss.getText().toString());
                        login_editor.putString("login", "1");
                        login_editor.apply();
                        setpss.getText().clear();
                        setpss.setHint("Enter password");
                        Toast.makeText(getApplicationContext(), "Password saved :)", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!sp.getString("passwd", "null").equals(setpss.getText().toString())) {
                            setpss.setError("Password not valid :(");
                            setpss.getText().clear();
                        } else {
                            Intent intent = new Intent(MainActivity.this, NotesList.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                            setpss.getText().clear();
                        }
                    }
                } else {
                    setpss.setError("Empty pal!");
                }
            }
        });
    }


}
