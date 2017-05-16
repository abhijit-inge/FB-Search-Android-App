package com.example.a12345.fbsearch;

import java.io.Serializable;

/**
 * Created by a12345 on 4/25/17.
 */

public class ToPass implements Serializable{
    String name;
    String id;
    String profile_pic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
