package com.vikas.dtu.safetyfirst2.mData;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class News {

    public String uid;
    public String author;
    public String title;
    public String body;
    public String imgUrl;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public News() {
        // Default constructor required for calls to DataSnapshot.getValue(News.class)
    }

    public News(String uid, String author, String title, String body, String imgUrl) {
        this.uid = uid;
        this.author = author;
        this.title = title;
        this.body = body;
        this.imgUrl = imgUrl;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("title", title);
        result.put("body", body);
        result.put("imgUrl", imgUrl);
        result.put("starCount", starCount);
        result.put("stars", stars);

        return result;
    }
    // [END post_to_map]

}
// [END post_class]
