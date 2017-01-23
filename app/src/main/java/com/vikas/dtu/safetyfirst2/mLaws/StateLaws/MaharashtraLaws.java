package com.vikas.dtu.safetyfirst2.mLaws.StateLaws;

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
import com.vikas.dtu.safetyfirst2.mLaws.ActivityLaws;
import com.vikas.dtu.safetyfirst2.mLaws.StateLawsRowInfo;
import com.vikas.dtu.safetyfirst2.mRecycler.MyAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MaharashtraLaws extends AppCompatActivity {
    RecyclerView lawsrecycler,formsrecycler;
    ArrayList<StateLawsRowInfo> lawsArrayList,formsArrayList;
    Uri path=null,pathd=null;
    float per = 0;
    TextView tv_loading;
    String factoriesact="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Ffactories-act-1948.pdf?alt=media&token=4b247841-6f5c-49c8-a8e0-5041228a0f36";
    String labourwelfarefundact="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FMaharashtra%2Fmaharashtra_labor_welfare_fund_act.pdf?alt=media&token=d24180b3-f4cb-4682-b411-e8e3ca67ba62";
    String cumreturn="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FMaharashtra%2FNone_Form%20A-1%20CUM%20Return.doc?alt=media&token=6c4143be-9738-4dcd-9f25-75b099b48c82";
    int downloadedSize = 0, totalsize;
    File SDcardroot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maharashtra_laws);
        lawsrecycler=(RecyclerView) findViewById(R.id.LawsRecycler);
        formsrecycler=(RecyclerView) findViewById(R.id.FormsRecycler);
        lawsArrayList=fillLawsData();
        formsArrayList=fillFormsData();
        StateLawAdapter Adapter=new StateLawAdapter(getApplicationContext(), lawsArrayList, new StateLawAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                    if(item.text=="Factories Act"){
                        if(Checkforpermission.CheckforPermissions(MaharashtraLaws.this)){
                        downloadandShow(factoriesact);}
                        else{
                            Checkforpermission.requestpermission(MaharashtraLaws.this,1);
                        }
                    }else  if(item.text=="Labour Welfare Fund Act"){
                        if(Checkforpermission.CheckforPermissions(MaharashtraLaws.this)){
                            downloadandShow(labourwelfarefundact);}
                        else{
                            Checkforpermission.requestpermission(MaharashtraLaws.this,2);
                        }
                    }
            }
        });
        lawsrecycler.setAdapter(Adapter);
        lawsrecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        StateFormAdapter Adapter1=new StateFormAdapter(getApplicationContext(), formsArrayList, new StateFormAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                        if(item.text=="Labour Welfare Board Cum Return"){
                            if(Checkforpermission.CheckforPermissions(MaharashtraLaws.this)){
                                downloadandShowdoc(cumreturn);}
                            else{
                                Checkforpermission.requestpermission(MaharashtraLaws.this,3);
                            }
                        }
            }
        });
        formsrecycler.setAdapter(Adapter1);
        formsrecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if(requestCode==1){
                downloadandShow(factoriesact);
            }else  if(requestCode==2){
                downloadandShow(labourwelfarefundact);
            }else  if(requestCode==3){
                downloadandShowdoc(cumreturn);
            }
        }
    }

    private ArrayList<StateLawsRowInfo> fillLawsData() {
        ArrayList<StateLawsRowInfo> temp=new ArrayList<>();

        temp.add(new StateLawsRowInfo("Factories Act",R.drawable.back_law1));
        temp.add(new StateLawsRowInfo("Labour Welfare Fund Act",R.drawable.back_law2));

        return temp;
    }

    private ArrayList<StateLawsRowInfo> fillFormsData(){
        ArrayList<StateLawsRowInfo> temp1=new ArrayList<>();
            temp1.add(new StateLawsRowInfo("Labour Welfare Board Cum Return",0));
        return temp1;
    }
    public void downloadandShow(String url) {

        //  Log.d(TAG, url);
        if (url != null) {
            tv_loading = new TextView(MaharashtraLaws.this);

            tv_loading.setGravity(Gravity.CENTER);
            tv_loading.setTypeface(null, Typeface.BOLD);
            downloadAndOpenPDF(url);

        } else {
            Toast.makeText(MaharashtraLaws.this, "No File Attached", Toast.LENGTH_SHORT).show();
        }

    }
    public void downloadandShowdoc(String url) {

        //  Log.d(TAG, url);
        if (url != null) {
            tv_loading = new TextView(MaharashtraLaws.this);

            tv_loading.setGravity(Gravity.CENTER);
            tv_loading.setTypeface(null, Typeface.BOLD);
            downloadandopendoc(url);

        } else {
            Toast.makeText(MaharashtraLaws.this, "No File Attached", Toast.LENGTH_SHORT).show();
        }

    }
    void downloadAndOpenPDF(final String url) {
        new Thread(new Runnable() {
            public void run() {
                File file=null;
                if(url==factoriesact){
                    file=new File(Environment.getExternalStorageDirectory(),"Maharashtra factories act.pdf");
                }
                else if(url==labourwelfarefundact){
                    file=new File(Environment.getExternalStorageDirectory(),"Maharashtra labour welfare fund act.pdf");
                }

                if(file.exists()==false){
                    MaharashtraLaws.this.runOnUiThread(new Runnable() {
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
    void downloadandopendoc(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file1=null;
                if(url==cumreturn){
                    file1=new File(Environment.getExternalStorageDirectory(),"Maharashtra cum return.doc");
                }
                if(file1.exists()==false){
                    MaharashtraLaws.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setContentView(tv_loading);
                        }
                    });

                    file1 = downloadFile(url);
                    if(file1!=null){
                    pathd = Uri.fromFile(file1);}
                }else{
                    pathd = Uri.fromFile(file1);}

                if (file1.exists()) {

                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction(Intent.ACTION_VIEW);
                    String type = "application/msword";
                    intent.setDataAndType(pathd, type);
                    startActivity(intent);
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
            if(dwnload_file_path==factoriesact){
                file=new File(Environment.getExternalStorageDirectory(),"Maharashtra factories act.pdf");
            }
            else if(dwnload_file_path==labourwelfarefundact){
                file=new File(Environment.getExternalStorageDirectory(),"Maharashtra labour welfare fund act.pdf");
            }else if(dwnload_file_path==cumreturn){
                file=new File(Environment.getExternalStorageDirectory(),"Maharashtra cum return.doc");
            }

            fileOutput = new FileOutputStream(file);
            // Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();

            // this is the total size of the file which we are
            // downloading
            totalsize = urlConnection.getContentLength();
            setText("Starting download...");

            // create a buffer...
            byte[] buffer = new byte[1024 * 1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
                per = ((float) downloadedSize / totalsize) * 100;
                setText("Total File size  : "
                        + (totalsize / 1024)
                        + " KB\n\nDownloading PDF " + (int) per
                        + "% complete");
            }
            // close the output stream when complete //
            fileOutput.close();
            setText("Download Complete. Open PDF/Word Application installed in the device.");

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
