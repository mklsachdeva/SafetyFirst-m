package com.vikas.dtu.safetyfirst2.mNewsActivity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mData.News;
import com.vikas.dtu.safetyfirst2.mNewsActivity.NewsDetailActivity;
import com.vikas.dtu.safetyfirst2.mNewsActivity.viewholder.NewsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;

import java.util.HashMap;
import java.util.Map;


public abstract class NewsListFragment extends Fragment {

    private static final String TAG = "NewsListFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private ProgressBar mProgressBar;

    private FirebaseRecyclerAdapter<News, NewsViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public NewsListFragment() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_all_news, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.messages_list);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        mRecycler.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query newsQuery = getQuery(mDatabase);
        mAdapter = new FirebaseRecyclerAdapter<News, NewsViewHolder>(News.class, R.layout.item_news,
                NewsViewHolder.class, newsQuery) {
            @Override
            protected void populateViewHolder(final NewsViewHolder viewHolder, final News model, final int position) {

                mProgressBar.setVisibility(ProgressBar.INVISIBLE);

                final DatabaseReference newsRef = getRef(position);

                // Set click listener for the whole news view
                final String newsKey = newsRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch NewsDetailActivity
                        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                        intent.putExtra(NewsDetailActivity.EXTRA_NEWS_KEY, newsKey);
                        startActivity(intent);
                    }
                });

                viewHolder.bindToNews(model,

                        getContext());
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);

}
