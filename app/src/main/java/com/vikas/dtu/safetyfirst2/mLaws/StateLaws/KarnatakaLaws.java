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
import com.vikas.dtu.safetyfirst2.mLaws.LawsRecyclerViewAdapter;
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

public class KarnatakaLaws extends AppCompatActivity {
    RecyclerView lawsrecycler,formsrecycler;
    ArrayList<StateLawsRowInfo> lawsArrayList,formsArrayList;
    float per = 0;
    TextView tv_loading;
    File file;
    int downloadedSize = 0, totalsize;
    File SDcardroot;
    Uri path=null,pathd=null;
    String citizenscharter="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2Fcitizen_charter_dept_of_factoriesboilers_industrial_safety__health_karnataka(1).pdf?alt=media&token=72507bbf-53b5-4b01-b3fa-395c42d8e6b8";
    String factoriesact="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2Ffactories_act_1948.pdf?alt=media&token=9a78f989-c310-47af-bebe-a06faabe0aa3";
    String nofeesforlicence="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2Fno_fees_for_trade_license_or_general_license_from_small_scale_industries_in_karnataka.pdf?alt=media&token=08118094-29a5-406c-8727-36d3b6c5492f";
    String factoriesrules="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%20Factories%20Rules%2C%201969.pdf?alt=media&token=e2f3a4e6-81ba-43f7-a64d-68ded3e3982f";
    String registerofaccidentsanddangerousoccurence="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2FNone_FORM%20%2023%20-%20REGISTER%20OF%20ACCIDENTS%20AND%20DANGEROUS%20OCCURRENCES.doc?alt=media&token=dbb404a7-a7f7-402c-abf6-6847f4c9c3c6";
    String registerofadultworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2FNone_FORM%2011%20-%20REGISTER%20OF%20ADULT%20WORKER.doc?alt=media&token=c3e97fa3-8def-493f-846d-cef705043c60";
    String registerofleavewithwages="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2FNone_FORM%2014%20-%20REGISTER%20OF%20LEAVE%20WITH%20WAGES%20FOR%20THE%20YEAR.doc?alt=media&token=437381e7-46a2-4585-84a3-ab57c9f82fb3";
    String leavebook="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2FNone_FORM%2015%20-%20Leave%20Book-%20REGISTER%20OF%20LEAVE%20WITH%20WAGES.doc?alt=media&token=1565481c-58aa-469e-9b20-1a3f7867073b";
    String healthregister="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2FNone_FORM%2016%20-%20HEALTH%20%20REGISTER.doc?alt=media&token=ed9b42ff-f44c-4d90-9239-bb6ce7154e6f";
    String factorylicencerenewal="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2FNone_FORM%202%20-%20FACTORY%20LICENCE%20RENEWAL-2.doc?alt=media&token=18c15ef6-6a67-4b09-bf55-8f4c47a90bf5";
    String annualreturn="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2FNone_FORM%2020%20-%20COMBINED%20ANNUAL%20RETURN.doc?alt=media&token=c41f8733-02f2-4d08-a6ae-f8a37790d9d1";
    String musterroll="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2FNone_FORM%2022%20-%20MUSTER%20ROLL%20CUM%20REGISTER%20OF%20WAGES.doc?alt=media&token=f238d412-3344-41b7-8ef4-ac993ac4aaf9";
    String registerofovertimeandpayment="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2FNone_FORM%209%20-%20REGISTER%20OF%20OVERTIME%20AND%20PAYMENT.doc?alt=media&token=1f523473-87fa-4fab-9f40-333449dcb59c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karnataka_laws);
        lawsrecycler=(RecyclerView) findViewById(R.id.LawsRecycler);
        formsrecycler=(RecyclerView) findViewById(R.id.FormsRecycler);
        lawsArrayList=fillLawsData();
        formsArrayList=fillFormsData();
        StateLawAdapter Adapter=new StateLawAdapter(getApplicationContext(), lawsArrayList, new StateLawAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                if(item.text=="Citizen`s Charter"){
                    if(Checkforpermission.CheckforPermissions(KarnatakaLaws.this)){
                        downloadandShow(citizenscharter);}
                    else{
                        Checkforpermission.requestpermission(KarnatakaLaws.this,1);
                    }
                }else if(item.text=="Factories Act"){
                    if(Checkforpermission.CheckforPermissions(KarnatakaLaws.this)){
                        downloadandShow(factoriesact);}
                    else{
                        Checkforpermission.requestpermission(KarnatakaLaws.this,2);
                    }
                }else if(item.text=="No Fees for Licence"){
                    if(Checkforpermission.CheckforPermissions(KarnatakaLaws.this)){
                        downloadandShow(nofeesforlicence);}
                    else{
                        Checkforpermission.requestpermission(KarnatakaLaws.this,3);
                    }
                }else if(item.text=="Factories Rules"){
                    if(Checkforpermission.CheckforPermissions(KarnatakaLaws.this)){
                        downloadandShow(factoriesrules);}
                    else{
                        Checkforpermission.requestpermission(KarnatakaLaws.this,4);
                    }
                }
            }
        });
        lawsrecycler.setAdapter(Adapter);
        lawsrecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        StateFormAdapter Adapter1=new StateFormAdapter(getApplicationContext(), formsArrayList, new StateFormAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                if(item.text=="Register of Accidents and Dangerous Occurence"){
                    if(Checkforpermission.CheckforPermissions(KarnatakaLaws.this)){
                    downloadandShowdoc(registerofaccidentsanddangerousoccurence);}
                    else{
                        Checkforpermission.requestpermission(KarnatakaLaws.this,5);
                    }
                }else if(item.text=="Register of Adult workers"){
                    if(Checkforpermission.CheckforPermissions(KarnatakaLaws.this)){
                        downloadandShowdoc(registerofadultworkers);}
                    else{
                        Checkforpermission.requestpermission(KarnatakaLaws.this,6);
                    }
                }else if(item.text=="Register of Leave with Wages"){
                    if(Checkforpermission.CheckforPermissions(KarnatakaLaws.this)){
                        downloadandShowdoc(registerofleavewithwages);}
                    else{
                        Checkforpermission.requestpermission(KarnatakaLaws.this,7);
                    }
                }else if(item.text=="Muster Roll"){
                    if(Checkforpermission.CheckforPermissions(KarnatakaLaws.this)){
                        downloadandShowdoc(musterroll);}
                    else{
                        Checkforpermission.requestpermission(KarnatakaLaws.this,8);
                    }
                }else if(item.text=="Register of Overtime and Payment"){
                    if(Checkforpermission.CheckforPermissions(KarnatakaLaws.this)){
                        downloadandShowdoc(registerofovertimeandpayment);}
                    else{
                        Checkforpermission.requestpermission(KarnatakaLaws.this,9);
                    }
                }else if(item.text=="Leave Book"){
                    if(Checkforpermission.CheckforPermissions(KarnatakaLaws.this)){
                        downloadandShowdoc(leavebook);}
                    else{
                        Checkforpermission.requestpermission(KarnatakaLaws.this,10);
                    }
                }else if(item.text=="Annual Return"){
                    if(Checkforpermission.CheckforPermissions(KarnatakaLaws.this)){
                        downloadandShowdoc(annualreturn);}
                    else{
                        Checkforpermission.requestpermission(KarnatakaLaws.this,11);
                    }
                }else if(item.text=="Health Register"){
                    if(Checkforpermission.CheckforPermissions(KarnatakaLaws.this)){
                        downloadandShowdoc(healthregister);}
                    else{
                        Checkforpermission.requestpermission(KarnatakaLaws.this,12);
                    }
                }else if(item.text=="Factory Licence Renewal"){
                    if(Checkforpermission.CheckforPermissions(KarnatakaLaws.this)){
                        downloadandShowdoc(factorylicencerenewal);}
                    else{
                        Checkforpermission.requestpermission(KarnatakaLaws.this,13);
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
                downloadandShow(citizenscharter);
            }else if(requestCode==2){
                downloadandShow(factoriesact);
            }else if(requestCode==3){
                downloadandShow(nofeesforlicence);
            }else if(requestCode==4){
                downloadandShow(factoriesrules);
            }else if(requestCode==5){
                downloadandShowdoc(registerofaccidentsanddangerousoccurence);
            }else if(requestCode==6){
                downloadandShowdoc(registerofadultworkers);
            }else if(requestCode==7){
                downloadandShowdoc(registerofleavewithwages);
            }else if(requestCode==8){
                downloadandShowdoc(musterroll);
            }else if(requestCode==9){
                downloadandShowdoc(registerofovertimeandpayment);
            }else if(requestCode==10){
                downloadandShowdoc(leavebook);
            }else if(requestCode==11){
                downloadandShowdoc(annualreturn);
            }else if(requestCode==12){
                downloadandShowdoc(healthregister);
            }else if(requestCode==13){
                downloadandShowdoc(factorylicencerenewal);
            }
        }
    }

    private ArrayList<StateLawsRowInfo> fillLawsData() {
        ArrayList<StateLawsRowInfo> temp=new ArrayList<>();
        temp.add(new StateLawsRowInfo("Citizen`s Charter",R.drawable.back_law3));
        temp.add(new StateLawsRowInfo("Factories Act",R.drawable.back_law4));
        temp.add(new StateLawsRowInfo("No Fees for Licence",R.drawable.back_law5));
        temp.add(new StateLawsRowInfo("Factories Rules",R.drawable.back_law6));
        return temp;
    }
    private ArrayList<StateLawsRowInfo> fillFormsData(){
        ArrayList<StateLawsRowInfo> temp1=new ArrayList<>();

        temp1.add(new StateLawsRowInfo("Register of Accidents and Dangerous Occurence",0));
        temp1.add(new StateLawsRowInfo("Register of Adult workers",0));
        temp1.add(new StateLawsRowInfo("Register of Leave with Wages",0));
        temp1.add(new StateLawsRowInfo("Muster Roll",0));
        temp1.add(new StateLawsRowInfo("Register of Overtime and Payment",0));
        temp1.add(new StateLawsRowInfo("Leave Book",0));
        temp1.add(new StateLawsRowInfo("Annual Return",0));
        temp1.add(new StateLawsRowInfo("Health Register",0));
        temp1.add(new StateLawsRowInfo("Factory Licence Renewal",0));

        return temp1;
    }
    public void downloadandShow(String url) {

        //  Log.d(TAG, url);
        if (url != null) {
            tv_loading = new TextView(KarnatakaLaws.this);

            tv_loading.setGravity(Gravity.CENTER);
            tv_loading.setTypeface(null, Typeface.BOLD);
            downloadAndOpenPDF(url);

        } else {
            Toast.makeText(KarnatakaLaws.this, "No File Attached", Toast.LENGTH_SHORT).show();
        }

    }
    public void downloadandShowdoc(String url) {

        //  Log.d(TAG, url);
        if (url != null) {
            tv_loading = new TextView(KarnatakaLaws.this);

            tv_loading.setGravity(Gravity.CENTER);
            tv_loading.setTypeface(null, Typeface.BOLD);
            downloadandopendoc(url);

        } else {
            Toast.makeText(KarnatakaLaws.this, "No File Attached", Toast.LENGTH_SHORT).show();
        }

    }
    void downloadAndOpenPDF(final String url) {
        new Thread(new Runnable() {
            public void run() {
                File file=null;
                if(url==citizenscharter){
                    file=new File(Environment.getExternalStorageDirectory(),"Karnataka citizen`s charter.pdf");
                }
                else if(url==factoriesact){
                    file=new File(Environment.getExternalStorageDirectory(),"Karnataka factories act.pdf");
                }
                else if(url==nofeesforlicence){
                    file=new File(Environment.getExternalStorageDirectory(),"Karnataka no fees for licence.pdf");
                } else if(url==factoriesrules){
                    file=new File(Environment.getExternalStorageDirectory(),"Karnataka factories rules.pdf");
                }
                if(file.exists()==false){
                    KarnatakaLaws.this.runOnUiThread(new Runnable() {
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
                if(url==registerofaccidentsanddangerousoccurence){
                    file1=new File(Environment.getExternalStorageDirectory(),"Karnataka register of accidents and dangerous occurence.doc");
                }else if(url==registerofadultworkers){
                    file1=new File(Environment.getExternalStorageDirectory(),"Karnataka register of adult workers.doc");
                }else if(url==registerofleavewithwages){
                    file1=new File(Environment.getExternalStorageDirectory(),"Karnataka register of leave with wages.doc");
                }else if(url==musterroll){
                    file1=new File(Environment.getExternalStorageDirectory(),"Karnataka muster roll.doc");
                }else if(url==registerofovertimeandpayment){
                    file1=new File(Environment.getExternalStorageDirectory(),"Karnataka register of overtime and payment.doc");
                }else if(url==leavebook){
                    file1=new File(Environment.getExternalStorageDirectory(),"Karnataka leave book.doc");
                }else if(url==annualreturn){
                    file1=new File(Environment.getExternalStorageDirectory(),"Karnataka annual return.doc");
                }else if(url==healthregister){
                    file1=new File(Environment.getExternalStorageDirectory(),"Karnataka health register.doc");
                }else if(url==factorylicencerenewal){
                    file1=new File(Environment.getExternalStorageDirectory(),"Karnataka factory licence renewal.doc");
                }
                if(file1.exists()==false){
                    KarnatakaLaws.this.runOnUiThread(new Runnable() {
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
            if(dwnload_file_path==citizenscharter){
                file=new File(Environment.getExternalStorageDirectory(),"Karnataka citizen`s charter.pdf");
            }
            else if(dwnload_file_path==factoriesact){
                file=new File(Environment.getExternalStorageDirectory(),"Karnataka factories act.pdf");
            }
            else if(dwnload_file_path==nofeesforlicence){
                file=new File(Environment.getExternalStorageDirectory(),"Karnataka no fees for licence.pdf");
            } else if(dwnload_file_path==factoriesrules){
                file=new File(Environment.getExternalStorageDirectory(),"Karnataka factories rules.pdf");
            }else if(dwnload_file_path==registerofaccidentsanddangerousoccurence){
                file=new File(Environment.getExternalStorageDirectory(),"Karnataka register of accidents and dangerous occurence.doc");
            }else if(dwnload_file_path==registerofadultworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Karnataka register of adult workers.doc");
            }else if(dwnload_file_path==registerofleavewithwages){
                file=new File(Environment.getExternalStorageDirectory(),"Karnataka register of leave with wages.doc");
            }else if(dwnload_file_path==musterroll){
                file=new File(Environment.getExternalStorageDirectory(),"Karnataka muster roll.doc");
            }else if(dwnload_file_path==registerofovertimeandpayment){
                file=new File(Environment.getExternalStorageDirectory(),"Karnataka register of overtime and payment.doc");
            }else if(dwnload_file_path==leavebook){
                file=new File(Environment.getExternalStorageDirectory(),"Karnataka leave book.doc");
            }else if(dwnload_file_path==annualreturn){
                file=new File(Environment.getExternalStorageDirectory(),"Karnataka annual return.doc");
            }else if(dwnload_file_path==healthregister){
                file=new File(Environment.getExternalStorageDirectory(),"Karnataka health register.doc");
            }else if(dwnload_file_path==factorylicencerenewal){
                file=new File(Environment.getExternalStorageDirectory(),"Karnataka factory licence renewal.doc");
            }

            fileOutput = new FileOutputStream(file);
            // Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();

            // this is the total size of the file which we are
            // downloading
            totalsize = urlConnection.getContentLength();
            setText("Starting File download...");

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
            setText("Download Complete. Open Pdf/Word Application installed in the device.");

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


