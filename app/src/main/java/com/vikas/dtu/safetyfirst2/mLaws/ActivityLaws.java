package com.vikas.dtu.safetyfirst2.mLaws;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.AndhraPradeshLaws;
import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.DelhiLaws;
import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.GujaratLaws;
import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.HaryanaLaws;
import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.KarnatakaLaws;
import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.KerelaLaws;
import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.MaharashtraLaws;
import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.OrrisaLaws;
import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.PunjabLaws;
import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.TamilNaduLaws;
import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.TelanganaLaws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ActivityLaws extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CODE =123 ;
    RecyclerView recyclerView;
    CardView c1,c2,c3;
    Uri path=null;
    ArrayList<StateLawsRowInfo> data;
    static String dockworkerurl = "https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Fdockworker.pdf?alt=media&token=d4cc97f6-5bf9-40a4-90e1-5b65856a9a97";
    static String mineurl = "https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Fmines.pdf?alt=media&token=e300e4b7-1e26-4806-91df-a3d21ddf1893";
    static String factoryurl = "https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Ffactories.pdf?alt=media&token=c5d026fc-f82a-41db-b5cc-aa06a8e6fbca";

    float per = 0;
    TextView tv_loading;

    int downloadedSize = 0, totalsize;
    File SDcardroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alllaws);
        c1=(CardView) findViewById(R.id.cardview1) ;
        c2=(CardView) findViewById(R.id.cardview2) ;
        c3=(CardView) findViewById(R.id.cardview3) ;

        String state=Environment.getExternalStorageState();


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckforPermissions(ActivityLaws.this)){
            downloadandShow(mineurl);}
                else{
                    requestpermission(ActivityLaws.this,1);
                }
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckforPermissions(ActivityLaws.this)){
                    downloadandShow(factoryurl);}
                else{
                    requestpermission(ActivityLaws.this,2);
                }
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckforPermissions(ActivityLaws.this)){
                    downloadandShow(dockworkerurl);}
                else{
                    requestpermission(ActivityLaws.this,3);
                }
            }
        });

        recyclerView=(RecyclerView) findViewById(R.id.StateLawsRecycler);
        data=filldata();
        LawsRecyclerViewAdapter adapter=new LawsRecyclerViewAdapter(getApplicationContext(), data, new LawsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                String state=item.text.toString();
                if(state=="Karnataka"){
                    Intent intent=new Intent(ActivityLaws.this, KarnatakaLaws.class);
                    startActivity(intent);
                }else if(state=="Andhra Pradesh"){
                    Intent intent=new Intent(ActivityLaws.this, AndhraPradeshLaws.class);
                    startActivity(intent);
                }else if(state=="Delhi"){
                    Intent intent=new Intent(ActivityLaws.this, DelhiLaws.class);
                    startActivity(intent);
                }else if(state=="Gujarat"){
                    Intent intent=new Intent(ActivityLaws.this, GujaratLaws.class);
                    startActivity(intent);
                }else if(state=="Haryana"){
                    Intent intent=new Intent(ActivityLaws.this, HaryanaLaws.class);
                    startActivity(intent);
                }else if(state=="Kerela"){
                    Intent intent=new Intent(ActivityLaws.this, KerelaLaws.class);
                    startActivity(intent);
                }else if(state=="Maharashtra"){
                    Intent intent=new Intent(ActivityLaws.this, MaharashtraLaws.class);
                    startActivity(intent);
                }else if(state=="Orrisa"){
                    Intent intent=new Intent(ActivityLaws.this, OrrisaLaws.class);
                    startActivity(intent);
                }else if(state=="Punjab"){
                    Intent intent=new Intent(ActivityLaws.this, PunjabLaws.class);
                    startActivity(intent);
                }else if(state=="Tamil Nadu"){
                    Intent intent=new Intent(ActivityLaws.this, TamilNaduLaws.class);
                    startActivity(intent);
                }else if(state=="Telangana"){
                    Intent intent=new Intent(ActivityLaws.this, TelanganaLaws.class);
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
    }

    private ArrayList<StateLawsRowInfo> filldata() {
        ArrayList<StateLawsRowInfo> rowInfo= new ArrayList<>();
        rowInfo.add(new StateLawsRowInfo("Andhra Pradesh",R.drawable.statelawpattern));
        rowInfo.add(new StateLawsRowInfo("Delhi",R.drawable.statelawpattern));
        rowInfo.add(new StateLawsRowInfo("Gujarat",R.drawable.statelawpattern));
        rowInfo.add(new StateLawsRowInfo("Haryana",R.drawable.statelawpattern));
        rowInfo.add(new StateLawsRowInfo("Karnataka", R.drawable.statelawpattern));
        rowInfo.add(new StateLawsRowInfo("Kerela", R.drawable.statelawpattern));
        rowInfo.add(new StateLawsRowInfo("Maharashtra", R.drawable.statelawpattern));
        rowInfo.add(new StateLawsRowInfo("Orrisa", R.drawable.statelawpattern));
        rowInfo.add(new StateLawsRowInfo("Punjab", R.drawable.statelawpattern));
        rowInfo.add(new StateLawsRowInfo("Tamil Nadu", R.drawable.statelawpattern));
        rowInfo.add(new StateLawsRowInfo("Telangana", R.drawable.statelawpattern));

        return rowInfo;
    }


    public void downloadandShow(String url) {

        //  Log.d(TAG, url);
        if (url != null) {
            tv_loading = new TextView(ActivityLaws.this);

            tv_loading.setGravity(Gravity.CENTER);
            tv_loading.setTypeface(null, Typeface.BOLD);
            downloadAndOpenPDF(url);

        } else {
            Toast.makeText(ActivityLaws.this, "No File Attached", Toast.LENGTH_SHORT).show();
        }

    }
    public static boolean CheckforPermissions(Activity thisActivity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(thisActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        }
        else{
            return true;
    }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloadandShow(mineurl);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                }
                return;
            }
            case 2:{
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloadandShow(factoryurl);

                }
                return;
            }
            case 3:{
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloadandShow(dockworkerurl);
                }
                return;
            }
        }
    }

    void downloadAndOpenPDF(final String url) {

        new Thread(new Runnable() {
            public void run() {
                File file=null;
                if(url==dockworkerurl){
                    file=new File(Environment.getExternalStorageDirectory(),"Dock Workers Act.pdf");
                   }
                else if(url==mineurl){
                    file=new File(Environment.getExternalStorageDirectory(),"Mines Act.pdf");
                   }
                else if(url==factoryurl){
                    file=new File(Environment.getExternalStorageDirectory(),"Factories Act.pdf");
                   }
                if(file.exists()==false){
                    ActivityLaws.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setContentView(tv_loading);
                        }
                    });

                    file = downloadFile(url);
                    path = Uri.fromFile(file);
                }else{
                    path = Uri.fromFile(file);}

                if (file.exists()) {

                                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                                pdfIntent.setDataAndType(path, "application/pdf");
                                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(pdfIntent);
                }
            }

        }).start();
    }

    public static void requestpermission(Activity thisActivity, int MY_PERMISSIONS_REQUEST_READ_CONTACTS) {
        // Here, thisActivity is the current activity


            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(thisActivity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
              else  {

                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(thisActivity,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }



    File downloadFile(String dwnload_file_path) {
        File file = null;
        try {

            URL url = new URL(dwnload_file_path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //  urlConnection.setRequestMethod("GET");
            //  urlConnection.setDoOutput(true);

            // connect
            urlConnection.connect();

            // set the path where we want to save the file

           SDcardroot = Environment.getExternalStorageDirectory();
            FileOutputStream fileOutput=null;
            // create a new file, to save the downloaded file
            if(dwnload_file_path==dockworkerurl){
            file=new File(SDcardroot,"Dock Workers Act.pdf");
            fileOutput = new FileOutputStream(file);}
            else if(dwnload_file_path==mineurl){
                file=new File(SDcardroot,"Mines Act.pdf");
                fileOutput = new FileOutputStream(file);}
            else if(dwnload_file_path==factoryurl){
                file=new File(SDcardroot,"Factories Act.pdf");
                fileOutput = new FileOutputStream(file);}


            // Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();

            // this is the total size of the file which we are
            // downloading
            totalsize = urlConnection.getContentLength();
            setText("Starting PDF download...");

            // create a buffer...
            byte[] buffer = new byte[1024 * 1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
                per = ((float) downloadedSize / totalsize) * 100;
                setText("Total PDF File size  : "
                        + (totalsize / 1024)
                        + " KB\n\nDownloading PDF " + (int) per
                        + "% complete");
            }
            // close the output stream when complete //
            fileOutput.close();
            setText("Download Complete. Open PDF Application installed in the device.");

        } catch (final MalformedURLException e) {
            setTextError(e.getMessage(),
                    Color.RED);
        } catch (FileNotFoundException e){

            setTextError("Some Error Occured.Please Check for Storage Permissions of Application",
                    Color.RED);
        } catch (final IOException e) {
            setTextError(e.getMessage(),
                    Color.RED);
        } catch (final Exception e) {
            setTextError(
                    e.getMessage(),
                    Color.RED);
        }
        return file;
    }

    void setTextError(final String message, final int color) {
        runOnUiThread(new Runnable() {
            public void run() {
                tv_loading.setTextColor(color);
                tv_loading.setText(message);
            }
        });

    }

    void setText(final String txt) {
        runOnUiThread(new Runnable() {
            public void run() {
                tv_loading.setText(txt);
            }
        });
    }

}

