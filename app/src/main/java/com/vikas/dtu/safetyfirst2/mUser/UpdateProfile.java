package com.vikas.dtu.safetyfirst2.mUser;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vikas.dtu.safetyfirst2.BaseActivity;
import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mData.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateProfile extends BaseActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    FirebaseUser user;
    Uri Imagepath=null;
    Uri TempUri;
    ImageView mPhoto;
    TextView mName;
    TextView mPhone;
    EditText mCompany;
    EditText mQualification;
    EditText mDesignation;
    EditText mCity;
    Spinner mSpinner;
    static final int RESULT_GALLERY_IMAGE=100;
    static final int RESULT_CAMERA_IMAGE=101;
    private ValueEventListener mUserListener;
    private File Imagefile;
    private Button mSubmit;
    DatabaseReference useRef;
    private StorageReference mstorageRef;
    StorageReference profilephotoRef;
    String joinAs;
    boolean check=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        ActionBar ab = getSupportActionBar();

        // Enable the Up round_blue_dark
        ab.setDisplayHomeAsUpEnabled(true);
        mstorageRef= FirebaseStorage.getInstance().getReference();
        mPhoto = (ImageView) findViewById(R.id.user_photo);
        mPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeProfilePic();
            }
        });
        mName = (TextView) findViewById(R.id.nameTxt);
        mPhone = (TextView) findViewById(R.id.phoneTxt);
        mCompany = (EditText) findViewById(R.id.company_name);
        mQualification = (EditText) findViewById(R.id.qualification);
        mDesignation = (EditText) findViewById(R.id.designation);
        mCity = (EditText) findViewById(R.id.city);
        mSubmit = (Button) findViewById(R.id.submit);
        user = getCurrentUser();
        mSpinner = (Spinner) findViewById(R.id.join);


        mSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Contractor");
        categories.add("Safety Officer");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mSpinner.setAdapter(dataAdapter);

        useRef = FirebaseDatabase.getInstance().getReference()
                .child("users").child(getUid());

        mSubmit.setOnClickListener(this);

    }

    private void ChangeProfilePic() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setItems(R.array.options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    Intent galleryintent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryintent,RESULT_GALLERY_IMAGE);
                }
                else if(which==1){
                    Intent cameraintent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    if(cameraintent.resolveActivity(getPackageManager())!=null){
                        startActivityForResult(cameraintent,RESULT_CAMERA_IMAGE);
                    }
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        check=true;
        if(requestCode==RESULT_GALLERY_IMAGE&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){

            Imagepath=data.getData();
            InputStream inputstream;
            try {
                inputstream=getContentResolver().openInputStream(Imagepath);
                Bitmap bitmap= BitmapFactory.decodeStream(inputstream);
                bitmap=Bitmap.createScaledBitmap(bitmap,710,710,true);
                mPhoto.setImageBitmap(bitmap);
                UploadPhoto(Imagepath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        else if(requestCode==RESULT_CAMERA_IMAGE&&resultCode==RESULT_OK){

            Bitmap bitmap;
            bitmap=(Bitmap)data.getExtras().get("data");
            bitmap=Bitmap.createScaledBitmap(bitmap,710,710,true);
            mPhoto.setImageBitmap(bitmap);
            Imagepath=getImageUri(this,bitmap);
            UploadPhoto(Imagepath);
        }


    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    private void UploadPhoto(Uri Imagepathq) {
        profilephotoRef = mstorageRef.child(user.getUid()+"/ProfilePhoto.jpg");

        profilephotoRef.putFile(Imagepathq)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(getApplicationContext(),"Some Error Occured..Please Try Again!",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        joinAs = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + joinAs, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.submit){
            if (validateForm()) {
                //TODO upload data
                String company = mCompany.getText().toString();
                String qualification = mQualification.getText().toString();
                String designation = mDesignation.getText().toString();
                String city = mCity.getText().toString();

                updateUser(company, qualification, designation, city);
            }
        }
    }

    void updateUser(String company,String qualification,String designation,String city){
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("company", company);
        childUpdates.put("qualification", qualification);
        childUpdates.put("designation", designation);
        childUpdates.put("city", city);
        childUpdates.put("joinAs", joinAs);

        useRef.updateChildren(childUpdates);

        Toast.makeText(this, getUid(), Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onStart() {
        super.onStart();


        profilephotoRef = mstorageRef.child(user.getUid()+"/ProfilePhoto.jpg");
        // Add value event listener to the news
        // [START user_value_event_listener]
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get User object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);
                // [START_EXCLUDE]
//
                if(check==true){
                    check=false;}else{
                    if(profilephotoRef!=null){
                    profilephotoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            useRef.child("userImage").setValue(uri.toString());
                        }
                    })   .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UpdateProfile.this, "Failed to get Url!", Toast.LENGTH_SHORT).show();
                        }
                    });

                        Glide.with(getApplicationContext()).load(user.getPhotoUrl()).override(165,165).into(mPhoto);
                    }else if (profilephotoRef==null&&user.getPhotoUrl() == null) {
                        mPhoto.setImageDrawable(ContextCompat.getDrawable(getBaseContext(),
                                R.drawable.ic_action_account_circle_40));
                    } else if(profilephotoRef==null&&user.getPhotoUrl()!=null) {
                        Glide.with(getApplicationContext())
                                .load(user.getPhotoUrl()).override(165,165)
                                .into(mPhoto);
                    }}





                mName.setText(user.getFull_name());

                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting News failed, log a message
                Log.w("UpdateProfile", "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(UpdateProfile.this, "Failed to load profile.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        useRef.addValueEventListener(userListener);
        // [END news_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mUserListener = userListener;

    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mUserListener != null) {
            useRef.removeEventListener(mUserListener);
        }

    }


    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }

    private boolean validateForm() {
        boolean result = true;

        if (TextUtils.isEmpty(mCompany.getText().toString())) {
            mCompany.setError("Required");
            result = false;
        } else {
            mCompany.setError(null);
        }

        if (TextUtils.isEmpty(mQualification.getText().toString())) {
            mQualification.setError("Required");
            result = false;
        } else {
            mQualification.setError(null);
        }

        if (TextUtils.isEmpty(mDesignation.getText().toString())) {
            mDesignation.setError("Required");
            result = false;
        } else {
            mDesignation.setError(null);
        }
        if (TextUtils.isEmpty(mCity.getText().toString())) {
            mCity.setError("Required");
            result = false;
        } else {
            mCity.setError(null);
        }

        return result;
    }


}
