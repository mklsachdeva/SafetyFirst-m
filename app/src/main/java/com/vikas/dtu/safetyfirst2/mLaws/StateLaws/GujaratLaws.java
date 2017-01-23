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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GujaratLaws extends AppCompatActivity {
    RecyclerView lawsrecycler,formsrecycler;
    ArrayList<StateLawsRowInfo> lawsArrayList,formsArrayList;
    Uri path=null;
    float per = 0;
    TextView tv_loading;
    String factorylawurl="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGuj-Fact-Rules.pdf?alt=media&token=1212b060-7ed1-43f0-a93e-5d121c486d34";
    String registerofcompensatoryholidays=" https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%2013%20-%20Overtime%20Register%20for%20Exempted%20workers%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=a850ebb9-5d25-4bc3-837e-bcefc295d1fb";
    String registerofadultworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%2015%20-%20Register%20of%20Adult%20worker%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=1aff8ac3-8699-44d2-8708-b5b652940563";
    String noticeperiodforchild="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%2016%20-%20Notice%20of%20period%20of%20work%20for%20Child%20Worker%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=e3d042e0-4a4d-4612-a753-12a262a69c49";
    String registerofchildworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%2017%20-%20Register%20of%20Child%20workers%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=8614c51c-ef5b-4a31-9735-726462d0bdf8";
    String registerofleavewithwages="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%2018%20-%20Register%20of%20Leave%20with%20Wages%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=c13eba75-880e-40b4-a77d-cc0473c1a5af";
    String noticeofchangeofmanager="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%203-A%20-%20Notice%20of%20change%20of%20Manager%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=e6239ead-13ea-497c-b6cf-3d3bf04b7d44";
    String recordoflimewashingetc="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%207%20-%20Record%20of%20Lime%20Washing%2C%20Painting%2Cetc%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=b4a7f12e-0864-44ac-8b44-3441137f56b7";

    int downloadedSize = 0, totalsize;
    File SDcardroot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gujarat_laws);
        lawsrecycler=(RecyclerView) findViewById(R.id.LawsRecycler);
        formsrecycler=(RecyclerView) findViewById(R.id.FormsRecycler);
        lawsArrayList=fillLawsData();
        formsArrayList=fillFormsData();
        StateLawAdapter Adapter=new StateLawAdapter(getApplicationContext(), lawsArrayList, new StateLawAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                if(Checkforpermission.CheckforPermissions(GujaratLaws.this)){
                downloadandShow(factorylawurl);}
                else {
                    Checkforpermission.requestpermission(GujaratLaws.this,1);
                }
            }
        });
        lawsrecycler.setAdapter(Adapter);
        lawsrecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        StateFormAdapter Adapter1=new StateFormAdapter(getApplicationContext(), formsArrayList, new StateFormAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
            if(item.text=="Register of Compensatory Holidays"){
                if(Checkforpermission.CheckforPermissions(GujaratLaws.this)){
                    downloadandShow(registerofcompensatoryholidays);}
                else {
                    Checkforpermission.requestpermission(GujaratLaws.this,2);
                }

            }else if(item.text=="Register of Adult Workers"){
                if(Checkforpermission.CheckforPermissions(GujaratLaws.this)){
                    downloadandShow(registerofadultworkers);}
                else {
                    Checkforpermission.requestpermission(GujaratLaws.this,3);
                }
            }else if(item.text=="Notice Period of Work for Child"){
                if(Checkforpermission.CheckforPermissions(GujaratLaws.this)){
                    downloadandShow(noticeperiodforchild);}
                else {
                    Checkforpermission.requestpermission(GujaratLaws.this,4);
                }
            }else if(item.text=="Register of Child Workers"){
                if(Checkforpermission.CheckforPermissions(GujaratLaws.this)){
                    downloadandShow(registerofchildworkers);}
                else {
                    Checkforpermission.requestpermission(GujaratLaws.this,5);
                }
            }else if(item.text=="Register of Leave with Wages"){
                if(Checkforpermission.CheckforPermissions(GujaratLaws.this)){
                    downloadandShow(registerofleavewithwages);}
                else {
                    Checkforpermission.requestpermission(GujaratLaws.this,6);
                }
            }else if(item.text=="Notice of Change of Manager"){
                if(Checkforpermission.CheckforPermissions(GujaratLaws.this)){
                    downloadandShow(noticeofchangeofmanager);}
                else {
                    Checkforpermission.requestpermission(GujaratLaws.this,7);
                }
            }else if(item.text=="Record of Lime washing etc."){
                if(Checkforpermission.CheckforPermissions(GujaratLaws.this)){
                    downloadandShow(recordoflimewashingetc);}
                else {
                    Checkforpermission.requestpermission(GujaratLaws.this,8);
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
                downloadandShow(factorylawurl);
            }else if(requestCode==2){
                downloadandShow(registerofcompensatoryholidays);
            }else if(requestCode==3){
                downloadandShow(registerofadultworkers);
            }else if(requestCode==4){
                downloadandShow(noticeperiodforchild);
            }else if(requestCode==5){
                downloadandShow(registerofchildworkers);
            }else if(requestCode==6){
                downloadandShow(registerofleavewithwages);
            }else if(requestCode==7){
                downloadandShow(noticeofchangeofmanager);
            }else if(requestCode==8){
                downloadandShow(recordoflimewashingetc);
            }
        }
    }

    private ArrayList<StateLawsRowInfo> fillLawsData() {
        ArrayList<StateLawsRowInfo> temp=new ArrayList<>();

        temp.add(new StateLawsRowInfo("Factories Act",R.drawable.back_law3));

        return temp;
    }

    private ArrayList<StateLawsRowInfo> fillFormsData(){
        ArrayList<StateLawsRowInfo> temp1=new ArrayList<>();
        temp1.add(new StateLawsRowInfo("Register of Compensatory Holidays",0));
        temp1.add(new StateLawsRowInfo("Register of Adult Workers",0));
        temp1.add(new StateLawsRowInfo("Notice Period of Work for Child",0));
        temp1.add(new StateLawsRowInfo("Register of Child Workers",0));
        temp1.add(new StateLawsRowInfo("Register of Leave with Wages",0));
        temp1.add(new StateLawsRowInfo("Notice of Change of Manager",0));
        temp1.add(new StateLawsRowInfo("Record of Lime washing etc.",0));

        return temp1;}
    public void downloadandShow(String url) {

        //  Log.d(TAG, url);
        if (url != null) {
            tv_loading = new TextView(GujaratLaws.this);

            tv_loading.setGravity(Gravity.CENTER);
            tv_loading.setTypeface(null, Typeface.BOLD);
            downloadAndOpenPDF(url);

        } else {
            Toast.makeText(GujaratLaws.this, "No File Attached", Toast.LENGTH_SHORT).show();
        }

    }
    void downloadAndOpenPDF(final String url) {
        new Thread(new Runnable() {
            public void run() {
                File file=null;
                if(url==factorylawurl){
                    file=new File(Environment.getExternalStorageDirectory(),"Gujarat factories act.pdf");
                }
                else if(url==registerofcompensatoryholidays){
                    file=new File(Environment.getExternalStorageDirectory(),"Gujarat register of compensatory holidays.pdf");
                }
                else if(url==recordoflimewashingetc){
                    file=new File(Environment.getExternalStorageDirectory(),"Gujarat record of lime washing etc.pdf");
                }else if(url==registerofadultworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Gujarat register of adult workers.pdf");
                }else if(url==registerofchildworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Gujarat registerofchildworkers.pdf");
                }else if(url==registerofleavewithwages){
                    file=new File(Environment.getExternalStorageDirectory(),"Gujarat registerofleavewithwages.pdf");
                }else if(url==noticeofchangeofmanager){
                    file=new File(Environment.getExternalStorageDirectory(),"Gujarat noticeofchangeofmanager.pdf");
                }else if(url==noticeperiodforchild){
                    file=new File(Environment.getExternalStorageDirectory(),"Gujarat noticeperiodforchild.pdf");
                }
                if(file.exists()==false){
                    GujaratLaws.this.runOnUiThread(new Runnable() {
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
            if(dwnload_file_path==factorylawurl){
                file=new File(Environment.getExternalStorageDirectory(),"Gujarat factories act.pdf");
            }
            else if(dwnload_file_path==registerofcompensatoryholidays){
                file=new File(Environment.getExternalStorageDirectory(),"Gujarat register of compensatory holidays.pdf");
            }
            else if(dwnload_file_path==recordoflimewashingetc){
                file=new File(Environment.getExternalStorageDirectory(),"Gujarat record of lime washing etc.pdf");
            }else if(dwnload_file_path==registerofadultworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Gujarat register of adult workers.pdf");
            }else if(dwnload_file_path==registerofchildworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Gujarat registerofchildworkers.pdf");
            }else if(dwnload_file_path==registerofleavewithwages){
                file=new File(Environment.getExternalStorageDirectory(),"Gujarat registerofleavewithwages.pdf");
            }else if(dwnload_file_path==noticeofchangeofmanager){
                file=new File(Environment.getExternalStorageDirectory(),"Gujarat noticeofchangeofmanager.pdf");
            }else if(dwnload_file_path==noticeperiodforchild){
                file=new File(Environment.getExternalStorageDirectory(),"Gujarat noticeperiodforchild.pdf");
            }
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


