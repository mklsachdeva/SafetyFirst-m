package com.vikas.dtu.safetyfirst2.mData;

import com.google.firebase.database.IgnoreExtraProperties;

import java.net.URI;
import java.net.URL;
import java.util.Map;

// [START blog_user_class]
@IgnoreExtraProperties
public class User {

    public String userImage;
    public String username;
    public String email;
    public int questions_asked;
    public int answers_given;

    private String company;
    private String designation;
    private String qualification;
    private String city;
    private String joinAs;
    private Map<String, Boolean> posts;
    private Map<String, Object> following;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email, String userImage) {
        this.username = username;
        this.email = email;
        this.userImage = userImage;
    }

    public String getPhotoUrl() {
        return userImage;
    }

    public String getFull_name() {
        return username;
    }

    public String getCompany(){ return company;}

    public String getCity() {
        return city;
    }

    public String getDesignation() {
        return designation;
    }

    public String getJoinAs() {
        return joinAs;
    }

    public String getQualification() {
        return qualification;
    }


    public Map<String, Boolean> getPosts() {
        return posts;
    }

    public Map<String, Object> getFollowing() {
        return following;
    }



}

// [END blog_user_class]
