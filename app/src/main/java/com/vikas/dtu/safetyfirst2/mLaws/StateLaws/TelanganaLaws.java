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

public class TelanganaLaws extends AppCompatActivity {
    RecyclerView lawsrecycler,formsrecycler;
    ArrayList<StateLawsRowInfo> lawsArrayList,formsArrayList;
    Uri path=null;
    float per = 0;
    TextView tv_loading;
    String factoriesrules="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Fthe_telangana_factorie_rules.pdf?alt=media&token=2f72ad5c-0ab0-4688-b9f4-44831e11acb2";
    String registerofchildworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTelanganaForm%2FNone_Form%2014%20-%20Register%20of%20Child%20worker%20-%20Telangana%20Factories%20Act.pdf?alt=media&token=1c506423-9bec-4c8b-9bf6-85791c97796c";
    String factoriesact="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTelanganaForm%2FNone_Form%2017%20-%20B%20-%20Telangana%20Factories%20Act.pdf?alt=media&token=2b0945e1-74a1-47aa-88b8-9d0cc133e576";
    String noticeofchangeofmanager="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTelanganaForm%2FNone_Form%202-A%20-%20Notice%20of%20Change%20of%20Manager%20or%20Occupier.pdf?alt=media&token=f05b4d95-14c3-49a0-8658-03fd8a5dec8b";
    String certificateoffitness="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTelanganaForm%2FNone_Form%205%20-%20Certificate%20of%20Fitness.pdf?alt=media&token=e7bf0856-8091-45e5-8450-76cef93c1789";
    String annualreturn="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTelanganaForm%2FNone_Form%20AR%20-%20Annual%20Return%20for%20the%20Year%20Ending%2031st%20December.pdf?alt=media&token=22ddc9ef-216a-487a-882b-69058d838da2";
    String factoriesrules1="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTelanganaForm%2Fthe_telangana_factorie_rules.pdf?alt=media&token=6b04868d-930a-4bf9-8c8d-a8fc0e3ddc98";


    int downloadedSize = 0, totalsize;
    File SDcardroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telangana);
        lawsrecycler=(RecyclerView) findViewById(R.id.LawsRecycler);
        formsrecycler=(RecyclerView) findViewById(R.id.FormsRecycler);
        lawsArrayList=fillLawsData();
        formsArrayList=fillFormsData();
        StateLawAdapter Adapter=new StateLawAdapter(getApplicationContext(), lawsArrayList, new StateLawAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                    if(item.text=="Factories Rules"){
                        if(Checkforpermission.CheckforPermissions(TelanganaLaws.this)){
                            downloadandShow(factoriesrules);}
                        else{
                            Checkforpermission.requestpermission(TelanganaLaws.this,1);
                        }
                    }
            }
        });
        lawsrecycler.setAdapter(Adapter);
        lawsrecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        StateFormAdapter Adapter1=new StateFormAdapter(getApplicationContext(), formsArrayList, new StateFormAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                    if(item.text=="Register of Child Workers"){
                        if(Checkforpermission.CheckforPermissions(TelanganaLaws.this)){
                            downloadandShow(registerofchildworkers);}
                        else{
                            Checkforpermission.requestpermission(TelanganaLaws.this,2);
                        }
                    }else  if(item.text=="Notice of Change of Manager"){
                        if(Checkforpermission.CheckforPermissions(TelanganaLaws.this)){
                            downloadandShow(noticeofchangeofmanager);}
                        else{
                            Checkforpermission.requestpermission(TelanganaLaws.this,3);
                        }
                    }else  if(item.text=="Annual Return"){
                        if(Checkforpermission.CheckforPermissions(TelanganaLaws.this)){
                            downloadandShow(annualreturn);}
                        else{
                            Checkforpermission.requestpermission(TelanganaLaws.this,4);
                        }
                    }else  if(item.text=="Factories Act Form"){
                        if(Checkforpermission.CheckforPermissions(TelanganaLaws.this)){
                            downloadandShow(factoriesact);}
                        else{
                            Checkforpermission.requestpermission(TelanganaLaws.this,5);
                        }
                    }else  if(item.text=="Factories Rules and Forms"){
                        if(Checkforpermission.CheckforPermissions(TelanganaLaws.this)){
                            downloadandShow(factoriesrules1);}
                        else{
                            Checkforpermission.requestpermission(TelanganaLaws.this,6);
                        }
                    }else  if(item.text=="Certificate of Fitness"){
                        if(Checkforpermission.CheckforPermissions(TelanganaLaws.this)){
                            downloadandShow(certificateoffitness);}
                        else{
                            Checkforpermission.requestpermission(TelanganaLaws.this,7);
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
                downloadandShow(factoriesrules);
            }else if(requestCode==2){
                downloadandShow(registerofchildworkers);
            }else if(requestCode==3){
                downloadandShow(noticeofchangeofmanager);
            }else if(requestCode==4){
                downloadandShow(annualreturn);
            }else if(requestCode==5){
                downloadandShow(factoriesact);
            }else if(requestCode==6){
                downloadandShow(factoriesrules1);
            }else if(requestCode==7){
                downloadandShow(certificateoffitness);
            }
        }
    }

    private ArrayList<StateLawsRowInfo> fillLawsData() {
        ArrayList<StateLawsRowInfo> temp=new ArrayList<>();

        temp.add(new StateLawsRowInfo("Factories Rules",R.drawable.back_law6));

        return temp;
    }

    private ArrayList<StateLawsRowInfo> fillFormsData(){
        ArrayList<StateLawsRowInfo> temp1=new ArrayList<>();

        temp1.add(new StateLawsRowInfo("Register of Child Workers",0));
        temp1.add(new StateLawsRowInfo("Notice of Change of Manager",0));
        temp1.add(new StateLawsRowInfo("Annual Return",0));
        temp1.add(new StateLawsRowInfo("Factories Act Form",0));
        temp1.add(new StateLawsRowInfo("Factories Rules and Forms",0));
        temp1.add(new StateLawsRowInfo("Certificate of Fitness",0));
        return temp1;
    }

    public void downloadandShow(String url) {

        //  Log.d(TAG, url);
        if (url != null) {
            tv_loading = new TextView(TelanganaLaws.this);

            tv_loading.setGravity(Gravity.CENTER);
            tv_loading.setTypeface(null, Typeface.BOLD);
            downloadAndOpenPDF(url);

        } else {
            Toast.makeText(TelanganaLaws.this, "No File Attached", Toast.LENGTH_SHORT).show();
        }

    }
    void downloadAndOpenPDF(final String url) {
        new Thread(new Runnable() {
            public void run() {
                File file=null;
                if(url==factoriesrules){
                    file=new File(Environment.getExternalStorageDirectory(),"Telangana factories rules.pdf");
                }
                else if(url==registerofchildworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Telangana register of child workers.pdf");
                }
                else if(url==noticeofchangeofmanager){
                    file=new File(Environment.getExternalStorageDirectory(),"Telangana notice of change of manager.pdf");
                }  else if(url==annualreturn){
                    file=new File(Environment.getExternalStorageDirectory(),"Telangana annual return.pdf");
                }  else if(url==factoriesact){
                    file=new File(Environment.getExternalStorageDirectory(),"Telangana factories act.pdf");
                }  else if(url==factoriesrules1){
                    file=new File(Environment.getExternalStorageDirectory(),"Telangana factories rules and forms.pdf");
                }else if(url==certificateoffitness){
                    file=new File(Environment.getExternalStorageDirectory(),"Telangana certificate of fitness.pdf");
                }
                if(file.exists()==false){
                    TelanganaLaws.this.runOnUiThread(new Runnable() {
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

            if(dwnload_file_path==factoriesrules){
                file=new File(Environment.getExternalStorageDirectory(),"Telangana factories rules.pdf");
            }
            else if(dwnload_file_path==registerofchildworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Telangana register of child workers.pdf");
            }
            else if(dwnload_file_path==noticeofchangeofmanager){
                file=new File(Environment.getExternalStorageDirectory(),"Telangana notice of change of manager.pdf");
            }  else if(dwnload_file_path==annualreturn){
                file=new File(Environment.getExternalStorageDirectory(),"Telangana annual return.pdf");
            }  else if(dwnload_file_path==factoriesact){
                file=new File(Environment.getExternalStorageDirectory(),"Telangana factories act.pdf");
            }  else if(dwnload_file_path==factoriesrules1){
                file=new File(Environment.getExternalStorageDirectory(),"Telangana factories rules and forms.pdf");
            }else if(dwnload_file_path==certificateoffitness){
                file=new File(Environment.getExternalStorageDirectory(),"Telangana certificate of fitness.pdf");
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

