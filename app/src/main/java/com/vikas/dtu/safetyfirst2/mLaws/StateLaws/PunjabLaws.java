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

public class PunjabLaws extends AppCompatActivity {
    RecyclerView lawsrecycler,formsrecycler;
    ArrayList<StateLawsRowInfo> lawsArrayList,formsArrayList;
    Uri path=null;
    String factoriesrules="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FCopy%20of%20the_punjab_factories_rules_1952.pdf?alt=media&token=fc2f7c90-df66-4754-9fa5-6989f27fb7d4";
    String overtimemusterrollforexemptedworker="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2010%20-%20Overtime%20Muster%20Roll%20for%20Exempted%20worker%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=b9518d5d-819d-4bf4-b82a-8fcca2b805ae";
    String noticeperiodforadultworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2011%20-%20Notice%20period%20of%20work%20for%20Adult%20Workers%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=61ff4cc1-ebca-43e7-b36d-8c319e31d536";
    String registerofadultworker="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2012%20-%20Register%20of%20Adult%20Workers%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=7b0ce24b-72d5-4fd7-8c86-e1ef734d680d";
    String noticeperioforchildworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2013%20-%20Notice%20of%20periods%20of%20work%20for%20child%20worker%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=749baf8b-4565-4dce-aa62-8642b24bad47";
    String registerofchildworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2014%20-%20Register%20of%20Child%20worker%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=41f0bcaf-b602-4e26-88f8-a8bcce654d31";
    String registerofleavewithwages="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2015%20-%20Register%20of%20Leave%20with%20Wages%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=412e61e3-fa62-4556-8a7f-bc5ff2415ff9";
    String healthregister="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2017%20-%20Health%20Register%20-%20Kerala%20Factories%20Act.pdf?alt=media&token=5c471af6-c308-4750-be5d-dd5bf3102f04";
    String annualreturn="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2021%20-%20Annual%20Return%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=f0b6cf6f-1422-4e47-8c83-6ae5b66923a4";
    String halfyearlyreturns="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2022%20-%20Half%20Yearly%20Returns%20-%20Kerala%20Factories%20Act.pdf?alt=media&token=f2d2b982-565d-4fef-81b7-50c183c171d0";
    String musterroll="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2025%20-%20Muster%20Roll%20-%20Telangana%20Factories%20Act.pdf?alt=media&token=2039a2ce-d60d-4249-86f5-89381de4263f";
    String recordoflimewashingetc="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%207%20-%20Record%20of%20Lime%20washing%2Cpainting%2Cetc%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=4470834e-439d-4e55-b0ee-391868c68b09";
    String registerofcompensatoryholidays="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%209%20-%20Register%20of%20Compensatory%20Holidays%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=7d731775-06c2-4d2e-926e-4a9f949ba442";
    float per = 0;
    TextView tv_loading;

    int downloadedSize = 0, totalsize;
    File SDcardroot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punjab_laws);
        lawsrecycler=(RecyclerView) findViewById(R.id.LawsRecycler);
        formsrecycler=(RecyclerView) findViewById(R.id.FormsRecycler);
        lawsArrayList=fillLawsData();
        formsArrayList=fillFormsData();
        StateLawAdapter Adapter=new StateLawAdapter(getApplicationContext(), lawsArrayList, new StateLawAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                if(item.text=="Factories Rules"){
                    if(Checkforpermission.CheckforPermissions(PunjabLaws.this)){
                        downloadandShow(factoriesrules);}
                    else{
                        Checkforpermission.requestpermission(PunjabLaws.this,1);
                    }                }
            }
        });
        lawsrecycler.setAdapter(Adapter);
        lawsrecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        StateFormAdapter Adapter1=new StateFormAdapter(getApplicationContext(), formsArrayList, new StateFormAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                if (item.text == "Muster Roll") {
                    if(Checkforpermission.CheckforPermissions(PunjabLaws.this)){
                        downloadandShow(musterroll);}
                    else{
                        Checkforpermission.requestpermission(PunjabLaws.this,2);
                    }
                }else if (item.text == "Overtime Muster Roll for Exempted Worker") {
                    if(Checkforpermission.CheckforPermissions(PunjabLaws.this)){
                        downloadandShow(overtimemusterrollforexemptedworker);}
                    else{
                        Checkforpermission.requestpermission(PunjabLaws.this,3);
                    }
                }else if (item.text == "Annual Returns") {
                    if(Checkforpermission.CheckforPermissions(PunjabLaws.this)){
                        downloadandShow(annualreturn);}
                    else{
                        Checkforpermission.requestpermission(PunjabLaws.this,4);
                    }
                }else if (item.text == "Half Yearly Returns") {
                    if(Checkforpermission.CheckforPermissions(PunjabLaws.this)){
                        downloadandShow(halfyearlyreturns);}
                    else{
                        Checkforpermission.requestpermission(PunjabLaws.this,5);
                    }
                }else if (item.text == "Register of Adult Workers") {
                    if(Checkforpermission.CheckforPermissions(PunjabLaws.this)){
                        downloadandShow(registerofadultworker);}
                    else{
                        Checkforpermission.requestpermission(PunjabLaws.this,6);
                    }
                }else if (item.text == "Register of Child Workers") {
                    if(Checkforpermission.CheckforPermissions(PunjabLaws.this)){
                        downloadandShow(registerofchildworkers);}
                    else{
                        Checkforpermission.requestpermission(PunjabLaws.this,7);
                    }
                }else if (item.text == "Register of Compensatory Holidays") {
                    if(Checkforpermission.CheckforPermissions(PunjabLaws.this)){
                        downloadandShow(registerofcompensatoryholidays);}
                    else{
                        Checkforpermission.requestpermission(PunjabLaws.this,8);
                    }
                }else if (item.text == "Notice Period for Adult Workers") {
                    if(Checkforpermission.CheckforPermissions(PunjabLaws.this)){
                        downloadandShow(noticeperiodforadultworkers);}
                    else{
                        Checkforpermission.requestpermission(PunjabLaws.this,9);
                    }
                }else if (item.text == "Notice Period for Child Workers") {
                    if(Checkforpermission.CheckforPermissions(PunjabLaws.this)){
                        downloadandShow(noticeperioforchildworkers);}
                    else{
                        Checkforpermission.requestpermission(PunjabLaws.this,10);
                    }
                }else if (item.text == "Register of Leave with Wages") {
                    if(Checkforpermission.CheckforPermissions(PunjabLaws.this)){
                        downloadandShow(registerofleavewithwages);}
                    else{
                        Checkforpermission.requestpermission(PunjabLaws.this,11);
                    }
                }else if (item.text == "Health Register") {
                    if(Checkforpermission.CheckforPermissions(PunjabLaws.this)){
                        downloadandShow(healthregister);}
                    else{
                        Checkforpermission.requestpermission(PunjabLaws.this,12);
                    }
                }else if (item.text == "Record of Limewashing etc.") {
                    if(Checkforpermission.CheckforPermissions(PunjabLaws.this)){
                        downloadandShow(recordoflimewashingetc);}
                    else{
                        Checkforpermission.requestpermission(PunjabLaws.this,13);
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
                downloadandShow(musterroll);
            }else  if(requestCode==3){
                downloadandShow(overtimemusterrollforexemptedworker);
            }else  if(requestCode==4){
                downloadandShow(annualreturn);
            }else  if(requestCode==5){
                downloadandShow(halfyearlyreturns);
            }else  if(requestCode==6){
                downloadandShow(registerofadultworker);
            }else  if(requestCode==7){
                downloadandShow(registerofchildworkers);
            }else  if(requestCode==8){
                downloadandShow(registerofcompensatoryholidays);
            }else  if(requestCode==9){
                downloadandShow(noticeperiodforadultworkers);
            }else  if(requestCode==10){
                downloadandShow(noticeperioforchildworkers);
            }else  if(requestCode==11){
                downloadandShow(registerofleavewithwages);
            }else  if(requestCode==12){
                downloadandShow(healthregister);
            }else  if(requestCode==13){
                downloadandShow(recordoflimewashingetc);
            }
        }
    }

    private ArrayList<StateLawsRowInfo> fillLawsData() {
        ArrayList<StateLawsRowInfo> temp=new ArrayList<>();

        temp.add(new StateLawsRowInfo("Factories Rules",R.drawable.back_law5));

        return temp;
    }

    private ArrayList<StateLawsRowInfo> fillFormsData(){
        ArrayList<StateLawsRowInfo> temp1=new ArrayList<>();
        temp1.add(new StateLawsRowInfo("Muster Roll",0));
        temp1.add(new StateLawsRowInfo("Overtime Muster Roll for Exempted Worker",0));
        temp1.add(new StateLawsRowInfo("Annual Returns",0));
        temp1.add(new StateLawsRowInfo("Half Yearly Returns",0));
        temp1.add(new StateLawsRowInfo("Register of Adult Workers",0));
        temp1.add(new StateLawsRowInfo("Register of Child Workers",0));
        temp1.add(new StateLawsRowInfo("Register of Compensatory Holidays",0));
        temp1.add(new StateLawsRowInfo("Notice Period for Adult Workers",0));
        temp1.add(new StateLawsRowInfo("Notice Period for Child Workers",0));
        temp1.add(new StateLawsRowInfo("Register of Leave with Wages",0));
        temp1.add(new StateLawsRowInfo("Health Register",0));
        temp1.add(new StateLawsRowInfo("Record of Limewashing etc.",0));

        return temp1;
    }

    public void downloadandShow(String url) {

        //  Log.d(TAG, url);
        if (url != null) {
            tv_loading = new TextView(PunjabLaws.this);

            tv_loading.setGravity(Gravity.CENTER);
            tv_loading.setTypeface(null, Typeface.BOLD);
            downloadAndOpenPDF(url);

        } else {
            Toast.makeText(PunjabLaws.this, "No File Attached", Toast.LENGTH_SHORT).show();
        }

    }
    void downloadAndOpenPDF(final String url) {
        new Thread(new Runnable() {
            public void run() {
                File file=null;
                if(url==factoriesrules){
                    file=new File(Environment.getExternalStorageDirectory(),"Punjab factories rules.pdf");
                }else if(url==musterroll){
                    file=new File(Environment.getExternalStorageDirectory(),"Punjab muster roll.pdf");
                }else if(url==overtimemusterrollforexemptedworker){
                    file=new File(Environment.getExternalStorageDirectory(),"Punjab overtime muster roll for exempted worker.pdf");
                }else if(url==annualreturn){
                    file=new File(Environment.getExternalStorageDirectory(),"Punjab annual return.pdf");
                }else if(url==halfyearlyreturns){
                    file=new File(Environment.getExternalStorageDirectory(),"Punjab half yearly returns.pdf");
                }else if(url==registerofadultworker){
                    file=new File(Environment.getExternalStorageDirectory(),"Punjab register of adult workers.pdf");
                }else if(url==registerofchildworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Punjab register of child workers.pdf");
                }else if(url==registerofcompensatoryholidays){
                    file=new File(Environment.getExternalStorageDirectory(),"Punjab register of compensatory holidays.pdf");
                }else if(url==noticeperiodforadultworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Punjab notice period for adult workers.pdf");
                }else if(url==noticeperioforchildworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Punjab notice period for child workers.pdf");
                }else if(url==registerofleavewithwages){
                    file=new File(Environment.getExternalStorageDirectory(),"Punjab register of leave with wages.pdf");
                }else if(url==healthregister){
                    file=new File(Environment.getExternalStorageDirectory(),"Punjab health register.pdf");
                }else if(url==recordoflimewashingetc){
                    file=new File(Environment.getExternalStorageDirectory(),"Punjab record of limewashing etc.pdf");
                }


                if(file.exists()==false){
                    PunjabLaws.this.runOnUiThread(new Runnable() {
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
            if(dwnload_file_path==factoriesrules) {
                file = new File(SDcardroot, "Punjab factories rules.pdf");
            }else if(dwnload_file_path==musterroll){
                file=new File(Environment.getExternalStorageDirectory(),"Punjab muster roll.pdf");
            }else if(dwnload_file_path==overtimemusterrollforexemptedworker){
                file=new File(Environment.getExternalStorageDirectory(),"Punjab overtime muster roll for exempted worker.pdf");
            }else if(dwnload_file_path==annualreturn){
                file=new File(Environment.getExternalStorageDirectory(),"Punjab annual return.pdf");
            }else if(dwnload_file_path==halfyearlyreturns){
                file=new File(Environment.getExternalStorageDirectory(),"Punjab half yearly returns.pdf");
            }else if(dwnload_file_path==registerofadultworker){
                file=new File(Environment.getExternalStorageDirectory(),"Punjab register of adult workers.pdf");
            }else if(dwnload_file_path==registerofchildworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Punjab register of child workers.pdf");
            }else if(dwnload_file_path==registerofcompensatoryholidays){
                file=new File(Environment.getExternalStorageDirectory(),"Punjab register of compensatory holidays.pdf");
            }else if(dwnload_file_path==noticeperiodforadultworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Punjab notice period for adult workers.pdf");
            }else if(dwnload_file_path==noticeperioforchildworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Punjab notice period for child workers.pdf");
            }else if(dwnload_file_path==registerofleavewithwages){
                file=new File(Environment.getExternalStorageDirectory(),"Punjab register of leave with wages.pdf");
            }else if(dwnload_file_path==healthregister){
                file=new File(Environment.getExternalStorageDirectory(),"Punjab health register.pdf");
            }else if(dwnload_file_path==recordoflimewashingetc){
                file=new File(Environment.getExternalStorageDirectory(),"Punjab record of limewashing etc.pdf");
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



