package com.tushar.secrectnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tushar on 19-08-2017.
 */

public class DataHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "note.db";

    public DataHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE notes(id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,descrip TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertNote(String tittle, String desc) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("descrip", desc);
        contentValues.put("title", tittle);
        SQLiteDatabase db = this.getWritableDatabase();
        long rowId = db.insert("notes", null, contentValues);

        db.close();
        if (rowId > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateNote(String tittle, String desc) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("descrip", desc);
        contentValues.put("title", tittle);
        SQLiteDatabase db = this.getWritableDatabase();
        long rowId = db.update("notes", contentValues, "title=?", new String[]{tittle});
        db.close();
        if (rowId > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateNote(String tittle, String desc, String id) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("descrip", desc);
        contentValues.put("title", tittle);
        SQLiteDatabase db = this.getWritableDatabase();
        long rowId = db.update("notes", contentValues, "id=?", new String[]{id});
        db.close();
        if (rowId > 0) {
            return true;
        } else {
            return false;
        }
    }


    public List<Note> getAllNotes() {
        List<Note> noteList = new ArrayList<Note>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from notes", null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getString(0));
                note.setTitle(cursor.getString(1));
                note.setDesc(cursor.getString(2));

                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return noteList;
    }
}
