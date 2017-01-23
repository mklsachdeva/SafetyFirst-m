package com.vikas.dtu.safetyfirst2.mKnowIt;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikas.dtu.safetyfirst2.R;

import java.util.ArrayList;

public class KnowItSecond extends AppCompatActivity {

    public static final String POSITION = "position";
    int position;
    ImageView image, typesImage;
    TextView title, description, safetyRules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.know_it_second);
        Resources res = this.getResources();

          ActionBar ab = getSupportActionBar();

        // Enable the Up round_blue_dark
        ab.setDisplayHomeAsUpEnabled(true);

        position = getIntent().getIntExtra(POSITION,0);
        setTitle(res.getStringArray(R.array.item_title)[position]);

        image = (ImageView) findViewById(R.id.second_image);
        typesImage = (ImageView) findViewById(R.id.second_types_image);
        title = (TextView) findViewById(R.id.second_title);
        description = (TextView) findViewById(R.id.second_desc);
        safetyRules = (TextView) findViewById(R.id.second_safety_rules);

        //   Resources res = this.getResources();

        TypedArray a = res.obtainTypedArray(R.array.second_types_image);
        image.setImageDrawable(a.getDrawable(position));

        title.setText(res.getStringArray(R.array.item_title)[position]);
        description.setText(res.getStringArray(R.array.second_desc)[position]);
        safetyRules.setText(res.getStringArray(R.array.second_safety_rules)[position]);

        TypedArray b = res.obtainTypedArray(R.array.second_image);
        typesImage.setImageDrawable(b.getDrawable(position));


//        ArrayList<Pages> allPages = new ArrayList<Pages>();
//
//        Resources res = this.getResources();
//        TypedArray a = res.obtainTypedArray(R.array.item_picture_gradient);
//        Drawable[] images = new Drawable[a.length()];
//        for(int i=0;i<images.length;i++){
//            images[i] = a.getDrawable(i);
//        }
//        String[] titles = res.getStringArray(R.array.item_title);
//        String[] descriptions = res.getStringArray(R.array.item_desc);
//        String[] safetyRules = res.getStringArray(R.array.item_safety_rules);
//
//        TypedArray b = res.obtainTypedArray(R.array.item_typesImage);
//        Drawable[] typesImages = new Drawable[b.length()];
//        for(int i=0;i<typesImages.length;i++){
//            typesImages[i] = b.getDrawable(i);
//        }
//
//        for(int i=0;i<titles.length;i++){
//            allPages.add(new Pages(images[position], titles[position],descriptions[position],safetyRules[position],typesImages[position]));
//        }
    }

    public void onClickTypesCard(View v){

        Intent intent = new Intent(KnowItSecond.this,KnowItThird.class);
        intent.putExtra(KnowItThird.POSITION, position);
        startActivity(intent);
    }

//    protected void showPage(int position){
//
//        Resources res = this.getResources();
//        String title = res.getStringArray(R.array.item_title)[position];
//        String desc = res.getStringArray(R.array.item_desc)[position];
//        String safetyRules = res.getStringArray(R.array.item_safety_rules)[position];
//        TypedArray a = res.obtainTypedArray(R.array.second_image);
//        Drawable image = a.getDrawable(position);
//        TypedArray b = res.obtainTypedArray(R.array.second_types_image)[position];
//        Drawable typesImage = b.getDrawable(position);
//
//
//
//
//    }
}

//class Pages{
//    Drawable image;
//    String title;
//    String description;
//    String safetyRules;
//    Drawable typesImage;
//
//    public Pages(Drawable image, String title, String description, String safetyRules, Drawable typesImage) {
//        this.image = image;
//        this.title = title;
//        this.description = description;
//        this.safetyRules = safetyRules;
//        this.typesImage = typesImage;
//    }
//};
