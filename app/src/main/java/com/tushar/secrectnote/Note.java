package com.tushar.secrectnote;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rio on 26/8/17.
 */

public class Note implements Parcelable {
    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
    String title;
    String desc;
    String id;

    public Note() {
    }

    protected Note(Parcel in) {
        this.title = in.readString();
        this.desc = in.readString();
        this.id = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeString(this.id);
    }
}
