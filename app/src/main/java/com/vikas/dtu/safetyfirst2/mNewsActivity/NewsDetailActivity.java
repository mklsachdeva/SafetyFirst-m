package com.vikas.dtu.safetyfirst2.mNewsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vikas.dtu.safetyfirst2.BaseActivity;
import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mData.News;
import com.vikas.dtu.safetyfirst2.mPicasso.PicassoClient;
import com.vikas.dtu.safetyfirst2.mWebview.WebViewActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewsDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "NewsDetailActivity";

    public static final String EXTRA_NEWS_KEY = "post_key";

    private DatabaseReference mPostReference;
    private ValueEventListener mNewsListener;
    private String mPostKey;
    private TextView mTitleView;
    private TextView mBodyView;
    private TextView mReadMore;
    private ImageView mNewsImage;
    private String url;
    private String HEADLINE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
       // Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
       // setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up round_blue_dark
        ab.setDisplayHomeAsUpEnabled(true);


        // Get post key from intent
        mPostKey = getIntent().getStringExtra(EXTRA_NEWS_KEY);
        if (mPostKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_NEWS_KEY");
        }

        // Initialize Database
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("news").child(mPostKey);

        // Initialize Views

        mTitleView = (TextView) findViewById(R.id.post_title);
        mBodyView = (TextView) findViewById(R.id.post_body);
        mReadMore = (TextView) findViewById(R.id.readMore);
        mNewsImage = (ImageView) findViewById(R.id.news_photo);
        findViewById(R.id.share_news).setOnClickListener(this);

    mReadMore.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the news
        // [START news_value_event_listener]
        ValueEventListener newsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get News object and use the values to update the UI
                News news = dataSnapshot.getValue(News.class);
                // [START_EXCLUDE]
//                mAuthorView.setText(news.author);
                HEADLINE = news.title;
                mTitleView.setText(news.title);
                mBodyView.setText(news.body);
                url = news.author;
                if(news.imgUrl!=null){
                    PicassoClient.downloadImage(getApplicationContext(), news.imgUrl, mNewsImage);
                    mNewsImage.setVisibility(View.VISIBLE);
                }
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting News failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(NewsDetailActivity.this, "Failed to load news.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mPostReference.addValueEventListener(newsListener);
        // [END news_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mNewsListener = newsListener;

    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mNewsListener != null) {
            mPostReference.removeEventListener(mNewsListener);
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.readMore) {
          //  Toast.makeText(NewsDetailActivity.this, url, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("Url", url);
            startActivity(intent);
        }
        if (i==R.id.share_news) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, url + "\nShared via:Safety First\nhttps://play.google.com/store/apps/details?id=com.vikas.dtu.safetyfirst2");
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
