package com.vikas.dtu.safetyfirst2.model;

import io.realm.RealmObject;

/**
 * Created by viveksb007 on 1/11/16.
 */

public class PostNotify extends RealmObject {

    private String uid;
    private String postKey;
    private int numComments;
    private int numStars;

    public PostNotify() {
    }

    public PostNotify(String uid, String postKey, int numComments, int numStars) {
        this.uid = uid;
        this.postKey = postKey;
        this.numComments = numComments;
        this.numStars = numStars;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public int getNumStars() {
        return numStars;
    }

    public void setNumStars(int numStars) {
        this.numStars = numStars;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
