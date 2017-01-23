package com.vikas.dtu.safetyfirst2.mKnowIt;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.Checkforpermission;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class KnowItFourth extends AppCompatActivity {

    public static final String TYPE = "type";
    public static final String POSITION = "position";

    TextView infoTxt, howtoTxt, status, heading;
    ImageView imageView;
    TextView watchAgain;

    TextView tv_loading;
    String dest_file_path = "checklist.pdf";
    int downloadedSize = 0, totalsize;
    float per = 0;

    // ProgressBar waitSign;

    private String title;
    private String info;
    private String howto;
    //private String checklist = "http://www.ehsdb.com/resources/Aerial_lift/Articulating%20Boom%20Lift.pdf";
   // private String videoLink = "https://www.youtube.com/embed/OGZOX2f2Yrc";
    private String checklist = "no";
    private String videoLink = "no";
    private String noLink = "no";
    // private String videoLink2 = "rtsp://v5.cache1.c.youtube.com/CjYLENy73wIaLQnhycnrJQ8qmRMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYPj_hYjnq6uUTQw=/0/0/0/video.3gp";
    // private String videoLink3 = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    private Drawable image;
    private ImageButton infoBtn, howToBtn, checkBtn, videoBtn;
    int position, type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.know_it_fourth);

        position = getIntent().getIntExtra(POSITION, 0);
        type = getIntent().getIntExtra(TYPE,0);

        checklist = getChecklist(type,position);
        videoLink = getVideoLink(type,position);

        Resources res = getResources();
        TypedArray titleArray = res.obtainTypedArray(R.array.third_title);
        int titleId = titleArray.getResourceId(type,0);
        title = res.getStringArray(titleId)[position];
        setTitle(title);

        TypedArray descArray = res.obtainTypedArray(R.array.third_description);
        int descId =descArray.getResourceId(type,0);
        info = res.getStringArray(descId)[position];

        TypedArray imageArray = res.obtainTypedArray(R.array.third_image);
        int imageId = imageArray.getResourceId(type,0);
        TypedArray a = res.obtainTypedArray(imageId);
        image = a.getDrawable(position);

        //setFeatureDrawable(imageId,image);

        TypedArray howToArray = res.obtainTypedArray(R.array.fourth_how_to);
        int howToId = howToArray.getResourceId(type,0);
        howto = res.getStringArray(howToId)[position];

//        TypedArray checklistArray = res.obtainTypedArray(R.array.fourth_checklist);
//        int checklistId = checklistArray.getResourceId(type,0);
        // checklist = res.getStringArray(checklistId)[position];


        infoTxt = (TextView) findViewById(R.id.fourth_info) ;
        howtoTxt = (TextView) findViewById(R.id.fourth_how_to);
        status = (TextView) findViewById(R.id.fourth_status);
        watchAgain = (TextView) findViewById(R.id.fourth_video);
        imageView = (ImageView) findViewById(R.id.fourth_image);
        heading = (TextView) findViewById(R.id.fourth_heading);

        //   videoView = (VideoView) findViewById(R.id.videoview);
        //      waitSign = (ProgressBar) findViewById(R.id.progress_bar);
        //     waitSign.setVisibility(View.GONE);

        infoBtn = (ImageButton)findViewById(R.id.info_btn);
        howToBtn = (ImageButton)findViewById(R.id.howto_btn);
        checkBtn = (ImageButton)findViewById(R.id.check_btn);
        videoBtn = (ImageButton)findViewById(R.id.video_btn);

        String blue = "@drawable/round_blue_dark";
        int blueResource = getResources().getIdentifier(blue, null, getPackageName());
        final Drawable blueBG = getResources().getDrawable(blueResource);

        String red = "@drawable/round_red_dark";
        int redResource = getResources().getIdentifier(red, null, getPackageName());
        final Drawable redBG = getResources().getDrawable(redResource);

        infoBtn.setBackground(blueBG);

        infoTxt.setVisibility(View.VISIBLE);
        howtoTxt.setVisibility(View.GONE);
        status.setVisibility(View.GONE);
        watchAgain.setVisibility(View.GONE);

        imageView.setImageDrawable(image);
        infoTxt.setText(info);
        //if(howto!="no") {
            howtoTxt.setText(howto);
        //}
        //else{
          //  howtoTxt.setText("NOT YET AVAILABLE");
        //}
        heading.setText("INFORMATION");

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View view) {

                if(view == findViewById(R.id.info_btn)){
                    infoTxt.setVisibility(View.VISIBLE);
                    howtoTxt.setVisibility(View.GONE);
                    status.setVisibility(View.GONE);
                    watchAgain.setVisibility(View.GONE);
                    infoBtn.setBackground(blueBG);
                    howToBtn.setBackground(redBG);
                    checkBtn.setBackground(redBG);
                    videoBtn.setBackground(redBG);
                    heading.setText("INFORMATION");

                } else if(view == findViewById(R.id.howto_btn)){
                    infoTxt.setVisibility(View.GONE);
                    howtoTxt.setVisibility(View.VISIBLE);
                    status.setVisibility(View.GONE);
                    watchAgain.setVisibility(View.GONE);
                    infoBtn.setBackground(redBG);
                    howToBtn.setBackground(blueBG);
                    checkBtn.setBackground(redBG);
                    videoBtn.setBackground(redBG);
                    heading.setText("HOW TO USE");

                    if(howtoTxt.getText().toString().equals("NOT YET AVAILABLE")) {
                        howtoTxt.setVisibility(View.GONE);
                        status.setVisibility(View.VISIBLE);
                        status.setText("Not Available");
                    }

                }
                else if(view == findViewById(R.id.check_btn)){
                    infoTxt.setVisibility(View.GONE);
                    howtoTxt.setVisibility(View.GONE);
                    status.setVisibility(View.VISIBLE);
                    watchAgain.setVisibility(View.GONE);
                    infoBtn.setBackground(redBG);
                    howToBtn.setBackground(redBG);
                    checkBtn.setBackground(blueBG);
                    videoBtn.setBackground(redBG);
                    heading.setText("SAFETY CHECKLIST");
                    if(Checkforpermission.CheckforPermissions(KnowItFourth.this)){
                    showFile(checklist);}
                    else{
                        Checkforpermission.requestpermission(KnowItFourth.this,1);
                    }
                }
                else if(view == findViewById(R.id.video_btn)){
                    infoTxt.setVisibility(View.GONE);
                    howtoTxt.setVisibility(View.GONE);
                    status.setVisibility(View.VISIBLE);
                    watchAgain.setVisibility(View.VISIBLE);
                    infoBtn.setBackground(redBG);
                    howToBtn.setBackground(redBG);
                    checkBtn.setBackground(redBG);
                    videoBtn.setBackground(blueBG);
                    heading.setText("VIDEO");
                    showVideo(videoLink);

                }

            }
        };

        ImageButton btn1 = (ImageButton)findViewById(R.id.info_btn);
        btn1.setOnClickListener(listener);
        ImageButton btn2 = (ImageButton)findViewById(R.id.howto_btn);
        btn2.setOnClickListener(listener);
        ImageButton btn3 = (ImageButton)findViewById(R.id.check_btn);
        btn3.setOnClickListener(listener);
        ImageButton btn4 = (ImageButton)findViewById(R.id.video_btn);
        btn4.setOnClickListener(listener);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if(requestCode==1){
                showFile(checklist);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case android.R.id.home:
                //User clicked home, do whatever you want
                Intent intent = new Intent(KnowItFourth.this,KnowItThird.class);
                intent.putExtra(KnowItThird.POSITION, type);
                startActivity(intent);
                return true;
            default:
                // Log.d("BackButton",item.getItemId()+"");
                return super.onOptionsItemSelected(item);
        }
    }

    public void showFile(String url) {

        //String url = (String) dataSnapshot.getValue();
        //  Log.d(TAG, url);
        if (url != "no"&&url!=null) {
            tv_loading = new TextView(KnowItFourth.this);
            setContentView(tv_loading);
            tv_loading.setGravity(Gravity.CENTER);
            tv_loading.setTypeface(null, Typeface.BOLD);
            downloadAndOpenPDF(url);
            status.setText("Checklist Downloaded");

        } else {
            Toast.makeText(KnowItFourth.this, "Not available", Toast.LENGTH_SHORT).show();
            status.setText("Checklist Not Available");
        }
    }

    void downloadAndOpenPDF(final String url) {
        new Thread(new Runnable() {
            public void run() {
                File file = downloadFile(url);
                if(file!=null){
                    Uri path = Uri.fromFile(file);
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(path, "application/pdf");
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } catch (ActivityNotFoundException e) {
                        tv_loading.setError("PDF Reader application is not installed in your device");
                    }
                }
                else{

                }
            }
        }).start();

    }

    File downloadFile(String download_file_path) {
        File file = null;
        try {
            //   Log.d("here","1");
            URL url = new URL(download_file_path);
//                Log.d("here","2");
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();

            //  urlConnection.setRequestMethod("GET");
            //  urlConnection.setDoOutput(true);

            // connect
            urlConnection.connect();
//                Log.d("here","3");
            // set the path where we want to save the file
            File SDCardRoot = Environment.getExternalStorageDirectory();
            // create a new file, to save the downloaded file
            file = new File(SDCardRoot, dest_file_path);
//                Log.d("here","4");
            FileOutputStream fileOutput = new FileOutputStream(file);
//                Log.d("here","5");
            // Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();
//                Log.d("here","6");
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
            return file;
        } catch (final MalformedURLException e) {
            status.setText("Checklist Not Downloaded. Please try again.");
            setTextError("Some error occured.Check your Internet connection and try again.",
                    Color.RED);
        } catch (final IOException e) {
            status.setText("Checklist Not Downloaded. Please try again.");
            setTextError("Some error occured.Check your Internet connection and try again.",
                    Color.RED);
        } catch (final Exception e) {
            status.setText("Checklist Not Downloaded. Please check your internet connection and try again.");
            Log.d("Download",e+ " ");
            setTextError(
                    "Failed to download Checklist. Please check your internet connection.",
                    Color.RED);
        }
        return null;
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

    public void showVideo(String url) {
        if (url != null&&url!="no") {

            status.setText("Video Available");
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(title);

            String frameVideo = "<html><body><iframe width=\"312\" height=\"200\" src=\""+url+"\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
            Log.d("Video1",frameVideo);
            final WebView wv = new WebView(this);
            //   wv.loadUrl(url);
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });

            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadData(frameVideo, "text/html", "utf-8");
            alert.setView(wv);
            alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    wv.setVisibility(View.GONE);
                    wv.loadUrl("https://www.google.co.in/");
                }
            });
            alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    wv.setVisibility(View.GONE);
                    wv.loadUrl("https://www.google.co.in/");
                    dialog.dismiss();
                }
            });
            alert.show();

        } else {
            status.setText("Video Not Available");
            watchAgain.setVisibility(View.GONE);
            Toast.makeText(this, "Video Not Available", Toast.LENGTH_SHORT).show();
        }

        if(isNetworkStatusAvailable (getApplicationContext())) {
           // Toast.makeText(getApplicationContext(), "Playing Video", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            status.setText("Video Available. Check your Internet connection");

        }
    }

    public static boolean isNetworkStatusAvailable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }

    public void clickWatchAgain(View v){
        showVideo(videoLink);
    }

    public String getChecklist(int type, int position){
        int maxTypes = 9;
        int maxPosition = 20;
        String all[][] = new String[maxTypes][maxPosition];
        //Types:  0 = Aerial(6) , 1 = Crane Lift, 2 = Fire Sprinklers, 3 = Forklift, 4 = Heat Stress, 5 = Ladders, 6 = Respiratory,
        //        7 = Safety,  8 = Scaffolds
        String check = "no";

        //Aerial Lift(6) :
        all[0][0] = "http://www.ehsdb.com/resources/Aerial_lift/Articulating%20Boom%20Lift.pdf";
        all[0][1] = "https://www.google.co.in/url?sa=t&rct=j&q=&esrc=s&source=web&cd=2&cad=rja&uact=8&ved=0ahUKEwjeic-GzvbPAhXJwI8KHQ8NDs8QFgghMAE&url=https%3A%2F%2Fwww.worksafe.vic.gov.au%2F__data%2Fassets%2Fword_doc%2F0014%2F12326%2FElevated_Work_Platform_Checklist.doc&usg=AFQjCNHs6QVHEkixyuLqm-zt6Khq8lVAaA&sig2=NIo28G8C02Uc7HvDDEXHMw&bvm=bv.136593572,d.c2I";
        all[0][2] = "http://access.ewu.edu/Documents/HRRR/ehs/Procedures/P25AerialLiftPREInsp.pdf";
        all[0][3] = "http://publicsafety.lafayette.edu/files/2011/11/Scissor-Lift-Inspection-Checklist.pdf";
        all[0][4] = "https://www.jlg.com/ground_support/pdf/annual-machine-inspection-check-list.pdf";
        all[0][5] = "no";

        //Crane Lift(11) :
        all[1][0] = "https://www.wshc.sg/files/wshc/upload/cms/file/3_Mobile_Crawler_Crane_checklist.pdf";
        all[1][1] = "http://www.ehsdb.com/resources/Crane/Inspection_forms/General%20crane%20inspection%20form.PDF";
        all[1][2] = "https://www.wshc.sg/files/wshc/upload/cms/file/2_Tower_Crane_Checklist.pdf";
        all[1][3] = "http://www.mysafetytraining.com/images/truckcranechecklist.pdf";
        all[1][4] = "http://www.ehsdb.com/resources/Crane/Inspection_forms/General%20crane%20inspection%20form.PDF";
        all[1][5] = "http://www.stos.co.nz/PTW%5CHot%20Work%202%5CChecklist%2029%20-%20Lifting%20Operations%20Involving%20Crane%20or%20Hiab%20Onshore.pdf";
        all[1][6] = "http://stlcrane.com/wp-content/uploads/daily_crane_operator_inspection_checklist.pdf";
        all[1][7] = "http://www.ehsdb.com/resources/Crane/Inspection_forms/General%20crane%20inspection%20form.PDF";
        all[1][8] = "http://www.ehsdb.com/resources/Crane/Inspection_forms/General%20crane%20inspection%20form.PDF";
        all[1][9] = "http://www.isri.org/docs/default-source/safety/overhead-gantry-crane.pdf?sfvrsn=4";
        all[1][10] = "http://www.ehsdb.com/resources/Crane/Inspection_forms/General%20crane%20inspection%20form.PDF";

        //Fire Sprinklers(4) :
        all[2][0] = "http://www.co.frederick.va.us/home/showdocument?id=230";
        all[2][1] = "http://www.hanover.com/linec/docs/171-1749.pdf";
        all[2][2] = "http://c.ymcdn.com/sites/www.nfsa.org/resource/resmgr/forms/25-13x-2011.pdf";
        all[2][3] = "https://drive.google.com/open?id=0B28ZutF8NxpZT3RrWWxPcnVPR0k";

        //Fork Lift(6)  :
        all[3][0] = "http://sielift.com/wp-content/uploads/2016/06/Reach-Truck-Daily-Operator-Checklist-2.pdf";
        all[3][1] = "https://drive.google.com/file/d/0B28ZutF8NxpZR1llM0hETm9Ea00/view?usp=sharing";
        all[3][2] = "https://www.nccer.org/uploads/fileLibrary/HEO_L2_2013_CEP.pdf";
        all[3][3] = "http://www.aalhysterforklifts.com.au/uploads/blog/Adaptalift_Hyster_Forklift_Safety_Prestart_Checklist.pdf";
        all[3][4] = "http://www.tsha.com.au/uploads/TSH%20Pre-Acceptance%20Checklist%20Issue%202%20Oct%202013.pdf";
        all[3][5] = "https://drive.google.com/open?id=0B28ZutF8NxpZck1faXZwUU1Ob3M";

        //Heat Stress(5) :
        all[4][0] = "no";
        all[4][1] = "no";
        all[4][2] = "no";
        all[4][3] = "no";
        all[4][4] = "no";

        //Ladders(6) :
        all[5][0] = "http://www.britishladders.co.uk/images/steplader.pdf";
        all[5][1] = "http://www-ehs.ucsd.edu/shop/pdf/Ladder_Inspection_Form.pdf";
        all[5][2] = "https://www.google.co.in/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&sqi=2&ved=0ahUKEwi2kKadvfbPAhUGvI8KHRugAP8QFggbMAA&url=http%3A%2F%2Fsafety.ucanr.org%2Ffiles%2F138047.docx&usg=AFQjCNH3SofAdlE9tBqderCzXVUnEz3i4g&bvm=bv.136593572,d.c2I&cad=rja";
        all[5][3] = "https://www.google.co.in/url?sa=t&rct=j&q=&esrc=s&source=web&cd=9&ved=0ahUKEwi42_7MvfbPAhUMPY8KHWXBAvsQFgg5MAg&url=http%3A%2F%2Fwww.kelsi.org.uk%2F__data%2Fassets%2Fword_doc%2F0011%2F27884%2FLadder-safety-Ladders-checklist-3-monthly-inspection.doc&usg=AFQjCNFQ6VaVt0WmMQeJNlp1rP2DGjCbZg&bvm=bv.136593572,d.c2I&cad=rja";
        all[5][4] = "https://www.google.co.in/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0ahUKEwjSsKaIvvbPAhVKqI8KHdx-BgQQFggbMAA&url=http%3A%2F%2Fwww.isri.org%2Fdocs%2Fdefault-source%2Fosha-safety%2Fmobile-ladder-stands-checklist.docx%3Fsfvrsn%3D2&usg=AFQjCNHWWUYfdm_EjsBo1cHH3VHxwEZ8rg&bvm=bv.136593572,d.c2I&cad=rja";
        all[5][5] = "https://www.osha.gov/dte/grant_materials/fy06/46e0-ht10/stf_ladder_safety_checklist.pdf";

        //Respiratory(6)
        all[6][0] = "https://www.google.co.in/url?sa=t&rct=j&q=&esrc=s&source=web&cd=2&cad=rja&uact=8&ved=0ahUKEwiG3ozy9PfPAhUBJZQKHXdzBq8QFgghMAE&url=http%3A%2F%2Fwww.doa.state.wi.us%2Fdocuments%2FDEO%2FRisk%2520Management%2Fsafety%2520categories%2FRespiratory%2520Protection%2FResp_Inspection_Checklist.doc&usg=AFQjCNHa0cMs-s3dHXtRKRnBzmH_wAkvSQ&sig2=8E1TKG4qqTARoZ4B9M6oHw";
        all[6][1] = all[6][0];
        all[6][2] = all[6][0];
        all[6][3] = all[6][0];
        all[6][4] = all[6][0];
        all[6][5] = all[6][0];

        //Scaffolds(19) :
        all[7][0] = "http://www.hymer-alu.de/uploads/media/HYM_16_EN_Kontrollblatt_Gerueste_01_Ansicht.pdf";
        all[7][1] = all[7][0];
        all[7][2] = "http://eagosh.org/eagosh-files/events/nov_09/Mobilescaffoldtowers67150_e.pdf";
        all[7][3] = "http://ohsinsider.com/wp-content/uploads/2013/11/scaffolding-inspection-checklist1.pdf";
        all[7][4] = "http://www.atlas-sales.com/Training/Forms/Scaffolding%20Inspection%20Checklist.pdf"; //5
        all[7][5] = "http://www.atlas-sales.com/Training/Forms/Scaffolding%20Inspection%20Checklist.pdf";
        all[7][6] = all[7][5];
        all[7][7] = "https://www.worksafe.qld.gov.au/__data/assets/pdf_file/0013/83200/suspendedscaffchecklist.pdf";
        all[7][8] = "https://www.worksafe.qld.gov.au/__data/assets/pdf_file/0013/83200/suspendedscaffchecklist.pdf";
        all[7][9] = all[7][8]; //10
        all[7][10] = all[7][8];
        all[7][11] = all[7][8];
        all[7][12] = all[7][8];
        all[7][13] = all[7][8];
        all[7][14] = all[7][8]; //15
        all[7][15] = "no";
        all[7][16] = "no";
        all[7][17] = "no";
        all[7][18] = "no"; //19
        check = all[type][position];
        return check;
    }

    public String getVideoLink(int type,int position){
        int maxTypes = 9;
        int maxPosition = 20;
        String all[][] = new String[maxTypes][maxPosition];
        //Types:  0 = Aerial(6) , 1 = Crane Lift, 2 = Fire Sprinklers, 3 = Forklift, 4 = Heat Stress, 5 = Ladders, 6 = Respiratory,
        //        7 = Safety,  8 = Scaffolds
        String video = "no";

        //Aerial Lift
        all[0][0] = "https://www.youtube.com/embed/OGZOX2f2Yrc";
        all[0][1] = "https://www.youtube.com/embed/7j_q90XMqH0";
        all[0][2] = "no";
        all[0][3] = "https://www.youtube.com/embed/M-mFITmJ_q4";
        all[0][4] = "no";
        all[0][5] = "no";

        //Crane Lift :
        all[1][0] = "https://www.youtube.com/embed/OYO_w-eLfag";
        all[1][1] = "no";
        all[1][2] = "https://www.youtube.com/embed/op8xvBmqQlU";
        all[1][3] = "https://www.youtube.com/embed/bQhkZVvd-IU";
        all[1][4] = "https://www.youtube.com/embed/NDPUfTrp4IE";
        all[1][5] = "https://www.youtube.com/embed/uO-RwTWqhrA";
        all[1][6] = "https://www.youtube.com/embed/jXIp0VEV67I";
        all[1][7] = "no";
        all[1][8] = "no";
        all[1][9] = "https://www.youtube.com/embed/9u3EuI6JRQA";
        all[1][10] = "https://www.youtube.com/embed/VWp9GOxrM08";

        //Fire Sprinklers :
        all[2][0] = "https://www.youtube.com/embed/0Ej2j6NuJdo";
        all[2][1] = "https://www.youtube.com/embed/ALUAHfU1nYg";
        all[2][2] = "https://www.youtube.com/embed/ZDwRlcFl2Qo";
        all[2][3] = "https://www.youtube.com/embed/uHIQwTmfA44";

        //Fork Lift  :
        all[3][0] = "https://www.youtube.com/embed/5zzPw_oe-So";
        all[3][1] = "https://www.youtube.com/embed/pHcBUF5NzJ8";
        all[3][2] = "https://www.youtube.com/embed/7s-h87pogc4";
        all[3][3] = "https://www.youtube.com/embed/tKw2numtNd8";
        all[3][4] = "https://www.youtube.com/embed/qjZlFoKnyIw";
        all[3][5] = "https://www.youtube.com/embed/PuHD3ZuxrWM";

        //Heat Stress(5) :
        all[4][0] = "no";
        all[4][1] = "no";
        all[4][2] = "no";
        all[4][3] = "no";
        all[4][4] = "no";

        //Ladders :
        all[5][0] = "https://www.youtube.com/embed/tcsdVup8NzA";
        all[5][1] = "https://www.youtube.com/embed/sWuOBu3GjHw&feature=youtu.be";
        all[5][2] = "https://www.youtube.com/embed/sWuOBu3GjHw&feature=youtu.be";
        all[5][3] = "no";
        all[5][4] = "https://www.youtube.com/embed/isv6O-FIj8c";
        all[5][5] = "https://www.youtube.com/embed/3g-Kwc7qMaY";

        //Respiratory Problems :
        all[6][0] = "https://www.youtube.com/embed/0d_RaKdqeck";
        all[6][1] = "https://www.youtube.com/embed/yBg2B2BP9nA";
        all[6][2] = "https://www.youtube.com/embed/OJ3ANkCeSzE";
        all[6][3] = "no";
        all[6][4] = "no";
        all[6][5] = "https://www.youtube.com/embed/EgtNgi7KMIk";

        //Scaffolds:
        all[7][0] = "no";
        all[7][1] = "no";
        all[7][2] = "https://www.youtube.com/embed/veF4uSUtrEY&t=41s&list=PLXrxTqXRekj2tkWIWUwVTsp18ydbhG5Lb&index=1";
        all[7][3] = "https://www.youtube.com/embed/56NZrk183BI";
        all[7][4] = "https://www.youtube.com/embed/-8hukCYUo3U"; //5
        all[7][5] = "https://www.youtube.com/embed/-8hukCYUo3U";
        all[7][6] = "https://www.youtube.com/embed/-8hukCYUo3U";
        all[7][7] = "https://www.youtube.com/embed/Hx9i3L2eDzs";
        all[7][8] = "https://www.youtube.com/embed/Hx9i3L2eDzs";
        all[7][9] = all[7][8]; //10
        all[7][10] = all[7][8];
        all[7][11] = all[7][8];
        all[7][12] = all[7][8];
        all[7][13] = all[7][8];
        all[7][14] = all[7][8]; //15
        all[7][15] = "no";
        all[7][16] = "no";
        all[7][17] = "no";
        all[7][18] = "no"; //19

        video = all[type][position];

        return video;
    }

}

