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

public class DelhiLaws extends AppCompatActivity {
    RecyclerView formsrecycler;
    ArrayList<StateLawsRowInfo> formslist;
    String recordlimewashing="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_form%20-%207%20-%20Record%20of%20Lime%20washing%2Cpainting%2Cetc.pdf?alt=media&token=9fa3d07b-b09d-4c4e-8c59-e27cf31d1194";
    String overtimemusterroll="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2010%20-%20Overtime%20Muster%20Roll%20for%20Exempted%20worker%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=a5fbf92a-9aee-4022-be83-195a4a0dd844";
    String noticeperiodforadultworkers= "https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2011%20-%20Notice%20of%20period%20of%20work%20for%20Adult%20Workers%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=70314442-90b6-4cb7-8356-bb5b73dc27aa";
    String registerofadultworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2012%20-%20Register%20of%20Adult%20Workers%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=18cf8de4-b0b3-40a2-9154-12e1ae0d37e3";
    String noticeperiodforchildworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2013%20-%20Notice%20of%20periods%20of%20work%20for%20child%20worker%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=93b9b467-307c-4430-9e80-d82c596a1e13";
    String registerofchildworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2014%20-%20Register%20of%20Child%20Workers%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=d91a0593-5d3d-48ec-996c-abdba53e7382";
    String registerofleavewithwages="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2015%20-%20Register%20of%20Leave%20with%20Wages%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=b6ffd8c7-aa7a-4c1d-b3af-bea9d51080b4";
    String healthregister="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2017%20-%20Health%20Register%20-%20Kerala%20Factories%20Act.pdf?alt=media&token=49455f73-1b9e-418f-9797-6755f969918d";
    String annualreturn="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2021%20-%20Annual%20Return%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=5c370dba-1514-497c-ba74-120a5deb3eb9";
    String halfyearlyretuns="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2022%20-%20Half%20Yearly%20Return.pdf?alt=media&token=acc56a7a-6177-4bf8-bb46-228f14335d3d";
    String musterroll="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2026%20-%20Muster%20Roll%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=407ed9da-c303-4df8-be9f-e12b54aae0d3";
    String registerofaccidentanddangerousoccurence="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2027%20-%20Register%20of%20Accident%20and%20Dangerous%20Occurences%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=6cf81b5c-ac7d-49ac-b542-f2f914de1aff";
    String registeroftrainedadultworkers="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2029%20-%20Register%20of%20Trained%20Adult%20Workers%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=a2c9a7d8-4dbf-4ccb-bb99-666a918fd68e";
    String noticeofchangeofmananger="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%203%20-%20Notice%20of%20change%20of%20Manager%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=e25d5baa-72b4-48f5-a49e-672174b3f066";
    String registerofcompensatoryholidays="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%209%20-%20Register%20of%20Compensatory%20Holidays%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=ced366a5-8dcc-44f2-896b-498aae375a8b";
    float per = 0;
    TextView tv_loading;

    int downloadedSize = 0, totalsize;
    File SDcardroot;
    Uri path=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delhi_laws);
        formsrecycler=(RecyclerView) findViewById(R.id.FormsRecycler);
        formslist=filldata();
        StateFormAdapter adapter=new StateFormAdapter(getApplicationContext(), formslist, new StateFormAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StateLawsRowInfo item) {
                if(item.text=="Record Lime Washing etc."){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(recordlimewashing);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,1);
                    }
                }else if(item.text=="Over Time Muster Roll"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(overtimemusterroll);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,2);
                    }
                }else if(item.text=="Notice of Change of Mananger"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(noticeofchangeofmananger);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,3);
                    }
                }else if(item.text=="Register of Accident and Dangerous Occurence"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(registerofaccidentanddangerousoccurence);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,4);
                    }
                }else if(item.text=="Register of Child Workers"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(registerofchildworkers);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,5);
                    }
                }else if(item.text=="Notice Period for Child Workers"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(noticeperiodforchildworkers);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,6);
                    }
                }else if(item.text=="Notice Period for Adult Workers"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(noticeperiodforadultworkers);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,7);
                    }
                }else if(item.text=="Register of Trained Adult Workers"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(registeroftrainedadultworkers);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,8);
                    }
                }else if(item.text=="Muster Roll"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(musterroll);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,9);
                    }
                }else if(item.text=="Half yearly Retuns"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(halfyearlyretuns);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,10);
                    }
                }else if(item.text=="Register of Leave with Wages"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(registerofleavewithwages);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,11);
                    }
                }else if(item.text=="Register of Compensatory Holidays"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(registerofcompensatoryholidays);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,12);
                    }
                }else if(item.text=="Register of Adult Workers"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(registerofadultworkers);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,13);
                    }
                }else if(item.text=="Annual Return"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(annualreturn);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,14);
                    }
                }else if(item.text=="Health Register"){
                    if(Checkforpermission.CheckforPermissions(DelhiLaws.this)){
                        downloadandShow(healthregister);}
                    else{
                        Checkforpermission.requestpermission(DelhiLaws.this,15);
                    }
                }
            }
        });
        formsrecycler.setAdapter(adapter);
        formsrecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if(requestCode==1){
                downloadandShow(recordlimewashing);
            }else if(requestCode==2){
                downloadandShow(overtimemusterroll);
            }else if(requestCode==3){
                downloadandShow(noticeofchangeofmananger);
            }else if(requestCode==4){
                downloadandShow(registerofaccidentanddangerousoccurence);
            }else if(requestCode==5){
                downloadandShow(registerofchildworkers);
            }else if(requestCode==6){
                downloadandShow(noticeperiodforchildworkers);
            }else if(requestCode==7){
                downloadandShow(noticeperiodforadultworkers);
            }else if(requestCode==8){
                downloadandShow(registeroftrainedadultworkers);
            }else if(requestCode==9){
                downloadandShow(musterroll);
            }else if(requestCode==10){
                downloadandShow(halfyearlyretuns);
            }else if(requestCode==11){
                downloadandShow(registerofleavewithwages);
            }else if(requestCode==12){
                downloadandShow(registerofcompensatoryholidays);
            }else if(requestCode==13){
                downloadandShow(registerofadultworkers);
            }else if(requestCode==14){
                downloadandShow(annualreturn);
            }else if(requestCode==15){
                downloadandShow(healthregister);
            }
        }
    }
    private ArrayList<StateLawsRowInfo> filldata() {
        ArrayList<StateLawsRowInfo> temp=new ArrayList<>();
        temp.add(new StateLawsRowInfo("Record Lime Washing etc.",0));
        temp.add(new StateLawsRowInfo("Over Time Muster Roll",0));
        temp.add(new StateLawsRowInfo("Notice of Change of Mananger",0));
        temp.add(new StateLawsRowInfo("Register of Accident and Dangerous Occurence",0));
        temp.add(new StateLawsRowInfo("Register of Child Workers",0));
        temp.add(new StateLawsRowInfo("Notice Period for Adult Workers",0));
        temp.add(new StateLawsRowInfo("Notice Period for Child Workers",0));
        temp.add(new StateLawsRowInfo("Register of Leave with Wages",0));
        temp.add(new StateLawsRowInfo("Register of Compensatory Holidays",0));
        temp.add(new StateLawsRowInfo("Half yearly Retuns",0));
        temp.add(new StateLawsRowInfo("Register of Trained Adult Workers",0));
        temp.add(new StateLawsRowInfo("Muster Roll",0));
        temp.add(new StateLawsRowInfo("Register of Adult Workers",0));
        temp.add(new StateLawsRowInfo("Annual Return",0));
        temp.add(new StateLawsRowInfo("Health Register",0));
        return temp;
    }



    public void downloadandShow(String url) {

        //  Log.d(TAG, url);
        if (url != null) {
            tv_loading = new TextView(DelhiLaws.this);

            tv_loading.setGravity(Gravity.CENTER);
            tv_loading.setTypeface(null, Typeface.BOLD);
            downloadAndOpenPDF(url);

        } else {
            Toast.makeText(DelhiLaws.this, "No File Attached", Toast.LENGTH_SHORT).show();
        }

    }
    void downloadAndOpenPDF(final String url) {
        new Thread(new Runnable() {
            public void run() {
                File file=null;
                if(url==recordlimewashing){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi record lime washing.pdf");
                }
                else if(url==overtimemusterroll){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi over time muster roll.pdf");
                }
                else if(url==registerofadultworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi register of adult workers.pdf");
                }else if(url==registerofaccidentanddangerousoccurence){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi register of accident and dangerous occurence.pdf");
                }else if(url==registerofchildworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi register of child workers.pdf");
                }else if(url==noticeofchangeofmananger){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi notice of change of mananger.pdf");
                }else if(url==noticeperiodforadultworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi notice period for adult workers.pdf");
                }else if(url==noticeperiodforchildworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi notice period for child workers.pdf");
                }else if(url==annualreturn){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi annual return.pdf");
                }else if(url==registerofleavewithwages){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi register of leave with wages.pdf");
                }else if(url==registerofcompensatoryholidays){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi register of compensatory holidays.pdf");
                }else if(url==halfyearlyretuns){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi half yearly retuns.pdf");
                }else if(url==musterroll){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi muster roll.pdf");
                }else if(url==registeroftrainedadultworkers){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi register of trained adult workers.pdf");
                }else if(url==healthregister){
                    file=new File(Environment.getExternalStorageDirectory(),"Delhi health register.pdf");
                }
                if(file.exists()==false){
                    DelhiLaws.this.runOnUiThread(new Runnable() {
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
            if(dwnload_file_path==recordlimewashing){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi record lime washing.pdf");
            }
            else if(dwnload_file_path==overtimemusterroll){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi over time muster roll.pdf");
            }
            else if(dwnload_file_path==registerofadultworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi register of adult workers.pdf");
            }else if(dwnload_file_path==registerofaccidentanddangerousoccurence){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi register of accident and dangerous occurence.pdf");
            }else if(dwnload_file_path==registerofchildworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi register of child workers.pdf");
            }else if(dwnload_file_path==noticeofchangeofmananger){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi notice of change of mananger.pdf");
            }else if(dwnload_file_path==noticeperiodforadultworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi notice period for adult workers.pdf");
            }else if(dwnload_file_path==noticeperiodforchildworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi notice period for child workers.pdf");
            }else if(dwnload_file_path==annualreturn){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi annual return.pdf");
            }else if(dwnload_file_path==registerofleavewithwages){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi register of leave with wages.pdf");
            }else if(dwnload_file_path==registerofcompensatoryholidays){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi register of compensatory holidays.pdf");
            }else if(dwnload_file_path==halfyearlyretuns){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi half yearly retuns.pdf");
            }else if(dwnload_file_path==musterroll){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi muster roll.pdf");
            }else if(dwnload_file_path==registeroftrainedadultworkers){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi register of trained adultworkers.pdf");
            }else if(dwnload_file_path==healthregister){
                file=new File(Environment.getExternalStorageDirectory(),"Delhi health register.pdf");
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


