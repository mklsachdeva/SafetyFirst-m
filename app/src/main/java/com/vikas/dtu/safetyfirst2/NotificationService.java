package com.vikas.dtu.safetyfirst2;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vikas.dtu.safetyfirst2.mDiscussion.PostDetailActivity;
import com.vikas.dtu.safetyfirst2.model.PostNotify;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by viveksb007 on 1/11/16.
 */

public class NotificationService extends IntentService {

    private static final int NOTIFICATION_ID = 1;
    Realm realm = null;

    public NotificationService() {
        super(NotificationService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        checkForNotification();
    }

    private void checkForNotification() {
        DatabaseReference postNotifyRef = FirebaseDatabase.getInstance().getReference().child("post-notify");
        realm = Realm.getDefaultInstance();
        final RealmResults<PostNotify> notifyRealmResults = realm.where(PostNotify.class).findAll();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        for (int i = 0; i < notifyRealmResults.size(); i++) {
            if (notifyRealmResults.get(i).getUid().equals(uid)) {
                final int numComments = notifyRealmResults.get(i).getNumComments();
                final String postKey = notifyRealmResults.get(i).getPostKey();
                if (postKey != null) {
                    postNotifyRef.child(postKey).child("num_of_comments").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                if (numComments != Integer.parseInt(dataSnapshot.getValue().toString())) {
                                    processStartNotification(postKey);
                                    changeLocalDB_Value(postKey, Integer.parseInt(dataSnapshot.getValue().toString()));
                                    //temp.setNumComments(Integer.parseInt(dataSnapshot.getValue().toString()));
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        }
    }

    private void changeLocalDB_Value(String postKey, int val) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(PostNotify.class).equalTo("postKey", postKey).findFirst().setNumComments(val);
        realm.commitTransaction();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void processStartNotification(String postKey) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Some Update on Post")
                .setAutoCancel(true)
                .setColor(Color.parseColor("#A8A8A8"))
                .setContentText("Someone commented on your post or some update on post you are involved with.")
                .setSmallIcon(R.drawable.ic_email_white_24dp)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID,
                new Intent(this, PostDetailActivity.class).putExtra(PostDetailActivity.EXTRA_POST_KEY, postKey),
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());

    }
}
