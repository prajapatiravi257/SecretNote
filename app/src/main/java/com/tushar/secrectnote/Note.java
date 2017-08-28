package com.tushar.secrectnote;

import java.io.Serializable;

/**
 * Created by rio on 26/8/17.
 */

public class Note implements Serializable {
    String title;
    String desc;
    String id;


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

}
