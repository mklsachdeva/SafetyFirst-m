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

public class HaryanaLaws extends AppCompatActivity {
    RecyclerView lawsrecycler,formsrecycler;
    ArrayList<StateLawsRowInfo> lawsArrayList,formsArrayList;
    Uri path=null;
    float per = 0;
    TextView tv_loading;
    String factoriesrules="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FCopy%20of%20the_punjab_factories_rules_1952.pdf?alt=media&token=fc2f7c90-df66-4754-9fa5-6989f27fb7d4";
    String recordoflimewashing="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_form%20-%207%20-%20Record%20of%20Lime%20washing%2Cpainting%2Cetc.pdf?alt=media&token=24627748-4422-4833-b374-136996cc4aa8";
    String overtimemusterrollforexemptedworker="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2010%20-%20Overtime%20Muster%20Roll%20for%20Exempted%20worker%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=70f58cfb-5981-420c-884b-dec55bddb064";
    String noticeofperiodforadultworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2011%20-%20Notice%20period%20of%20work%20for%20Adult%20Workers%20-%20Haryana%20Factories%20Act.pdf?alt=media&token=af64399b-5220-4755-b1e9-c9a11a5d3a73";
    String registerofadultworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2012%20-%20Register%20of%20Adult%20Workers%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=2881cf55-31c4-473e-bdfc-1aa6bdf380d5";
    String noticeofperiodforchildworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2013%20-%20Notice%20of%20periods%20of%20work%20for%20child%20worker%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=5b1afd20-d75a-4f45-b413-e8036debf854";
    String registerofchildworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2014%20-%20Register%20of%20Child%20worker%20-%20Telangana%20Factories%20Act.pdf?alt=media&token=25145329-fdba-4d5d-a5a5-a10f498e731d";
    String registerofleavewithwages="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2015%20-%20Register%20of%20Leave%20with%20Wages%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=843d1a34-ac60-4ab7-b208-6bd5fb5cdf28";
    String healthregister="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2017%20-%20Health%20Register%20-%20Haryana%20Factories%20Act.pdf?alt=media&token=f7fb2a82-a35b-4fbf-8151-7b854501d13c";
    String annualreturn="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2021%20-%20Annual%20Return%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=042c908b-32ba-4b59-9984-9039701e9795";
    String halfyearlyreturn="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2022%20-%20Half%20Yearly%20Returns%20-%20Kerala%20Factories%20Act.pdf?alt=media&token=c16d3b58-7dfd-42f6-a23d-fe0bbd6de476";
    String musterroll="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2025%20-%20Muster%20Roll%20-%20Telangana%20Factories%20Act.pdf?alt=media&token=dce96e5a-ef4c-4bd1-b3ee-3aeb784b6b57";
    String registerofcompensatoryholidays="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%209%20-%20Register%20of%20Compensatory%20Holidays%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=0bc96ba7-98ea-47e0-844e-073f44be5008";

    int downloadedSize = 0, totalsize;
    File SDcardroot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haryana_laws);
        lawsArrayList=fillLawsData();
        lawsrecycler=(RecyclerView) findViewById(R.id.LawsRecycler);
        formsrecycler=(RecyclerView) findViewById(R.id.FormsRecycler);
        StateLawAdapter Adapter=new StateLawAdapter(getApplicationContext(), lawsArrayList, new StateLawAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                if(item.text=="Factories Rules"){
                    if(Checkforpermission.CheckforPermissions(HaryanaLaws.this)){
                    downloadandShow(factoriesrules);}
                    else {
                        Checkforpermission.requestpermission(HaryanaLaws.this,1);
                    }
                }
            }
        });
        lawsrecycler.setAdapter(Adapter);
        lawsrecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        formsArrayList=fillFormsData();
        StateFormAdapter Adapter1=new StateFormAdapter(getApplicationContext(), formsArrayList, new StateFormAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                if(item.text=="Record of Lime Washing etc."){
                    if(Checkforpermission.CheckforPermissions(HaryanaLaws.this)){
                        downloadandShow(recordoflimewashing);}
                    else {
                        Checkforpermission.requestpermission(HaryanaLaws.this,2);
                    }
                }else  if(item.text=="Overtime Muster Roll for Exempted Worker"){
                    if(Checkforpermission.CheckforPermissions(HaryanaLaws.this)){
                        downloadandShow(overtimemusterrollforexemptedworker);}
                    else {
                        Checkforpermission.requestpermission(HaryanaLaws.this,3);
                    }
                }else  if(item.text=="Notice of Period for Adult Workers"){
                    if(Checkforpermission.CheckforPermissions(HaryanaLaws.this)){
                        downloadandShow(noticeofperiodforadultworkers);}
                    else {
                        Checkforpermission.requestpermission(HaryanaLaws.this,4);
                    }
                }else  if(item.text=="Register of Adult Worker"){
                    if(Checkforpermission.CheckforPermissions(HaryanaLaws.this)){
                        downloadandShow(registerofadultworkers);}
                    else {
                        Checkforpermission.requestpermission(HaryanaLaws.this,5);
                    }
                }else  if(item.text=="Notice of Period for Child Workers"){
                    if(Checkforpermission.CheckforPermissions(HaryanaLaws.this)){
                        downloadandShow(noticeofperiodforchildworkers);}
                    else {
                        Checkforpermission.requestpermission(HaryanaLaws.this,6);
                    }
                }else  if(item.text=="Register of Child Workers"){
                    if(Checkforpermission.CheckforPermissions(HaryanaLaws.this)){
                        downloadandShow(registerofchildworkers);}
                    else {
                        Checkforpermission.requestpermission(HaryanaLaws.this,7);
                    }
                }else  if(item.text=="Register of Leave with Wages"){
                    if(Checkforpermission.CheckforPermissions(HaryanaLaws.this)){
                        downloadandShow(registerofleavewithwages);}
                    else {
                        Checkforpermission.requestpermission(HaryanaLaws.this,8);
                    }
                }else  if(item.text=="Health Register"){
                    if(Checkforpermission.CheckforPermissions(HaryanaLaws.this)){
                        downloadandShow(healthregister);}
                    else {
                        Checkforpermission.requestpermission(HaryanaLaws.this,9);
                    }
                }else  if(item.text=="Annual Return"){
                    if(Checkforpermission.CheckforPermissions(HaryanaLaws.this)){
                        downloadandShow(annualreturn);}
                    else {
                        Checkforpermission.requestpermission(HaryanaLaws.this,10);
                    }
                }else  if(item.text=="Half Yearly Return"){
                    if(Checkforpermission.CheckforPermissions(HaryanaLaws.this)){
                        downloadandShow(halfyearlyreturn);}
                    else {
                        Checkforpermission.requestpermission(HaryanaLaws.this,11);
                    }
                }else  if(item.text=="Muster Roll"){
                    if(Checkforpermission.CheckforPermissions(HaryanaLaws.this)){
                        downloadandShow(musterroll);}
                    else {
                        Checkforpermission.requestpermission(HaryanaLaws.this,12);
                    }
                }else  if(item.text=="Register of Compensatory Holidays"){
                    if(Checkforpermission.CheckforPermissions(HaryanaLaws.this)){
                        downloadandShow(registerofcompensatoryholidays);}
                    else {
                        Checkforpermission.requestpermission(HaryanaLaws.this,13);
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
            }else  if(requestCode==2){
                downloadandShow(recordoflimewashing);
            }else  if(requestCode==3){
                downloadandShow(overtimemusterrollforexemptedworker);
            }else  if(requestCode==4){
                downloadandShow(noticeofperiodforadultworkers);
            }else  if(requestCode==5){
                downloadandShow(registerofadultworkers);
            }else  if(requestCode==6){
                downloadandShow(noticeofperiodforchildworkers);
            }else  if(requestCode==7){
                downloadandShow(registerofchildworkers);
            }else  if(requestCode==8){
                downloadandShow(registerofleavewithwages);
            }else  if(requestCode==9){
                downloadandShow(healthregister);
            }else  if(requestCode==10){
                downloadandShow(annualreturn);
            }else  if(requestCode==11){
                downloadandShow(halfyearlyreturn);
            }else  if(requestCode==12){
                downloadandShow(musterroll);
            }else  if(requestCode==13){
                downloadandShow(registerofcompensatoryholidays);
            }
        }
    }

    private ArrayList<StateLawsRowInfo> fillLawsData() {
        ArrayList<StateLawsRowInfo> temp=new ArrayList<>();

        temp.add(new StateLawsRowInfo("Factories Rules",R.drawable.back_law2));

        return temp;
    }

    private ArrayList<StateLawsRowInfo> fillFormsData(){
        ArrayList<StateLawsRowInfo> temp1=new ArrayList<>();
        temp1.add(new StateLawsRowInfo("Record of Lime Washing etc.",0));
        temp1.add(new StateLawsRowInfo("Overtime Muster Roll for Exempted Worker",0));
        temp1.add(new StateLawsRowInfo("Notice of Period for Adult Workers",0));
        temp1.add(new StateLawsRowInfo("Register of Adult Workers",0));
        temp1.add(new StateLawsRowInfo("Notice of Period for Child Workers",0));
        temp1.add(new StateLawsRowInfo("Register of Child Workers",0));
        temp1.add(new StateLawsRowInfo("Register of Leave with Wages",0));
        temp1.add(new StateLawsRowInfo("Health Register",0));
        temp1.add(new StateLawsRowInfo("Annual Return",0));
        temp1.add(new StateLawsRowInfo("Half Yearly Return",0));
        temp1.add(new StateLawsRowInfo("Muster Roll",0));
        temp1.add(new StateLawsRowInfo("Register of Compensatory Holidays",0));
        return temp1;
    }

    public void downloadandShow(String url) {

        //  Log.d(TAG, url);
        if (url != null) {
            tv_loading = new TextView(HaryanaLaws.this);

            tv_loading.setGravity(Gravity.CENTER);
            tv_loading.setTypeface(null, Typeface.BOLD);
            downloadAndOpenPDF(url);

        } else {
            Toast.makeText(HaryanaLaws.this, "No File Attached", Toast.LENGTH_SHORT).show();
        }

    }
    void downloadAndOpenPDF(final String url) {
        new Thread(new Runnable() {
            public void run() {
                File file=null;
                if(url==recordoflimewashing){
                    file=new File(Environment.getExternalStorageDirectory(),"Haryana record of lime washing etc.pdf");
                }
                else if(url==registerofadultworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Haryana register of adult workers.pdf");
                }
                else if(url==registerofchildworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Haryana register of child workers.pdf");
                }else if(url==overtimemusterrollforexemptedworker){
                    file=new File(Environment.getExternalStorageDirectory(),"Haryana overtime musterroll for exempted worker.pdf");
                }else if(url==noticeofperiodforadultworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Haryana notice of period for adult workers.pdf");
                }else if(url==noticeofperiodforchildworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Haryana noticeofperiodforchildworkers.pdf");
                }else if(url==registerofcompensatoryholidays){
                    file=new File(Environment.getExternalStorageDirectory(),"Haryana register of compensatory holidays.pdf");
                }else if(url==registerofleavewithwages){
                    file=new File(Environment.getExternalStorageDirectory(),"Haryana register of leave with wages.pdf");
                }else if(url==healthregister){
                    file=new File(Environment.getExternalStorageDirectory(),"Haryana health register.pdf");
                }else if(url==annualreturn){
                    file=new File(Environment.getExternalStorageDirectory(),"Haryana annual return.pdf");
                }else if(url==halfyearlyreturn){
                    file=new File(Environment.getExternalStorageDirectory(),"Haryana half yearly return.pdf");
                }else if(url==musterroll){
                    file=new File(Environment.getExternalStorageDirectory(),"Haryana muster roll.pdf");
                }else if(url==factoriesrules){
                    file=new File(Environment.getExternalStorageDirectory(),"Haryana factories rules.pdf");
                }
                if(file.exists()==false){
                    HaryanaLaws.this.runOnUiThread(new Runnable() {
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
            if(dwnload_file_path==recordoflimewashing){
                file=new File(Environment.getExternalStorageDirectory(),"Haryana record of lime washing etc.pdf");
            }
            else if(dwnload_file_path==registerofadultworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Haryana register of adult workers.pdf");
            }
            else if(dwnload_file_path==registerofchildworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Haryana register of child workers.pdf");
            }else if(dwnload_file_path==overtimemusterrollforexemptedworker){
                file=new File(Environment.getExternalStorageDirectory(),"Haryana overtime musterroll for exempted worker.pdf");
            }else if(dwnload_file_path==noticeofperiodforadultworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Haryana notice of period for adult workers.pdf");
            }else if(dwnload_file_path==noticeofperiodforchildworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Haryana noticeofperiodforchildworkers.pdf");
            }else if(dwnload_file_path==registerofcompensatoryholidays){
                file=new File(Environment.getExternalStorageDirectory(),"Haryana register of compensatory holidays.pdf");
            }else if(dwnload_file_path==registerofleavewithwages){
                file=new File(Environment.getExternalStorageDirectory(),"Haryana register of leave with wages.pdf");
            }else if(dwnload_file_path==healthregister){
                file=new File(Environment.getExternalStorageDirectory(),"Haryana health register.pdf");
            }else if(dwnload_file_path==annualreturn){
                file=new File(Environment.getExternalStorageDirectory(),"Haryana annual return.pdf");
            }else if(dwnload_file_path==halfyearlyreturn){
                file=new File(Environment.getExternalStorageDirectory(),"Haryana half yearly return.pdf");
            }else if(dwnload_file_path==musterroll){
                file=new File(Environment.getExternalStorageDirectory(),"Haryana muster roll.pdf");
            }else if(dwnload_file_path==factoriesrules){
                file=new File(Environment.getExternalStorageDirectory(),"Haryana factories rules.pdf");
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


