package com.vikas.dtu.safetyfirst2.mDiscussion.fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class SearchPostFragment extends PostListFragment {

    private String query;

    public static SearchPostFragment newInstance(String query) {
        SearchPostFragment postFragment = new SearchPostFragment();
        postFragment.query = query;
        return postFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // TODO : Check if child exists , this method is not working
        if (databaseReference.child("keywords").child(query.toLowerCase()).getRef() == null) {
            Toast.makeText(getContext(), "No TAGS Found. Try another word.", Toast.LENGTH_SHORT).show();
        }
        return databaseReference.child("keywords").child(query.toLowerCase());
    }


}
