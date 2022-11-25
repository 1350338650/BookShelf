package com.example.mybookshelf.data;

import java.util.List;

public class Book {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResourceid() {
        return resourceid;
    }

    public void setResourceid(int resourceid) {
        this.resourceid = resourceid;
    }

    public Book(String title, int resourceid) {
        this.title = title;
        this.resourceid = resourceid;
    }

    private String  title;
    private int resourceid;

}
