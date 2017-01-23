package com.vikas.dtu.safetyfirst2.mNewsActivity.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class RecentNewsFragment extends NewsListFragment {

    public RecentNewsFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_posts_query]
        // Last 1000 posts, these are automatically the 1000 most recent
        // due to sorting by push() keys
        Query recentPostsQuery = databaseReference.child("news")
                .limitToFirst(1000);
        // [END recent_posts_query]

        return recentPostsQuery;
    }
}
