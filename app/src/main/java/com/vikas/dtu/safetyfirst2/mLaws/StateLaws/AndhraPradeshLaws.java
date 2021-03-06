package com.vikas.dtu.safetyfirst2.mLaws.StateLaws;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mLaws.StateLawsRowInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AndhraPradeshLaws extends AppCompatActivity {
    RecyclerView lawsrecycler;
    ArrayList<StateLawsRowInfo> lawslist;
    TextView tv_loading;
    Uri path=null;
    int downloadedSize = 0, totalsize;
    float per = 0;
    File SDcardroot;
    String factoryurl="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTHE%20FACTORIES%20ACT-1948.pdf?alt=media&token=293a89e2-f4b9-4cc8-b2bc-58f400e14829";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andhra_pradesh_laws);
        lawsrecycler=(RecyclerView) findViewById(R.id.LawsRecycler);
        lawslist=filldata();
        StateLawAdapter Adapter=new StateLawAdapter(getApplicationContext(), lawslist, new StateLawAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                if(Checkforpermission.CheckforPermissions(AndhraPradeshLaws.this)){
            downloadandShow(factoryurl);}
                else{
                    Checkforpermission.requestpermission(AndhraPradeshLaws.this,1);
                }
            }
        });
        lawsrecycler.setAdapter(Adapter);
        lawsrecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloadandShow(factoryurl);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                }
                return;
    }
        }
    }

    private ArrayList<StateLawsRowInfo> filldata() {
        ArrayList<StateLawsRowInfo> temp=new ArrayList<>();
        temp.add(new StateLawsRowInfo("Factories Act",R.drawable.back_law7));
        return temp;
    }
    public void downloadandShow(String url) {

        //  Log.d(TAG, url);
        if (url != null) {
            tv_loading = new TextView(AndhraPradeshLaws.this);

            tv_loading.setGravity(Gravity.CENTER);
            tv_loading.setTypeface(null, Typeface.BOLD);
            downloadAndOpenPDF(url);

        } else {
            Toast.makeText(AndhraPradeshLaws.this, "No File Attached", Toast.LENGTH_SHORT).show();
        }

    }
    void downloadAndOpenPDF(final String url) {
        new Thread(new Runnable() {
            public void run() {
                File file=null;


               file=new File(Environment.getExternalStorageDirectory(),"AndhraPradesh Factories Act.pdf");

                if(file.exists()==false){
                    AndhraPradeshLaws.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setContentView(tv_loading);
                        }
                    });

                    file = downloadFile(url);
                    if(file!=null){
                    path = Uri.fromFile(file);}
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

                file=new File(SDcardroot,"AndhraPradesh Factories Act.pdf");
                fileOutput = new FileOutputStream(file);


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
            setTextError("Some Error Occurred.Please Check your Internet Connection",
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


