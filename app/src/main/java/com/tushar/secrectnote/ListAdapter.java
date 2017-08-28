package com.tushar.secrectnote;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rio on 26/8/17.
 */

public class ListAdapter extends ArrayAdapter<Note> {

    Context context;
    int resource;
    List<Note> noteList;

    public ListAdapter(@NonNull Context context, int resource, @NonNull List<Note> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        noteList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        NoteHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            holder = new NoteHolder();
            holder.title = (TextView) row.findViewById(R.id.item_title);
            holder.desc = (TextView) row.findViewById(R.id.item_desc);

            row.setTag(holder);
        } else {
            holder = (NoteHolder) row.getTag();
        }

        Note note = getItem(position);
        holder.title.setText(note.getTitle());
        holder.desc.setText(note.getDesc());

        return row;
    }

    static class NoteHolder {
        TextView title, desc;
    }
}
