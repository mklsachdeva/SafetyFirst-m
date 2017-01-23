package com.vikas.dtu.safetyfirst2.mKnowIt;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vikas.dtu.safetyfirst2.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KnowItThird extends AppCompatActivity {

    public static final String POSITION = "position";
    int position;
    ListView thirdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.know_it_third);
        Resources r = getResources();
        position = getIntent().getIntExtra(POSITION,0);
        setTitle(r.getStringArray(R.array.item_title)[position]+" Types");
        thirdList = (ListView) findViewById(R.id.third_list);
        thirdList.setAdapter(new ThirdCustAdapter(this,position));
        thirdList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(KnowItThird.this, KnowItFourth.class);
                intent.putExtra(KnowItFourth.TYPE,position);
                intent.putExtra(KnowItFourth.POSITION, i);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case android.R.id.home:
                //User clicked home, do whatever you want
                Intent intent = new Intent(KnowItThird.this,KnowItSecond.class);
                intent.putExtra(KnowItSecond.POSITION,position);
                startActivity(intent);
                return true;
            default:
                // Log.d("BackButton",item.getItemId()+"");
                return super.onOptionsItemSelected(item);
        }
    }
}

class ThirdCards{
    public String title;
    public String description;
    public Drawable image;

    ThirdCards(String title, String description, Drawable image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }
}

class ThirdCustAdapter extends BaseAdapter {

    Context context;
    List<ThirdCards> list;

    ThirdCustAdapter(Context c, int position){
        context = c;
        list = new LinkedList<>();

        Resources res = c.getResources();
        TypedArray titleArray = res.obtainTypedArray(R.array.third_title);
        int titleId = titleArray.getResourceId(position,0);
        String[] titles = res.getStringArray(titleId);

        TypedArray descArray = res.obtainTypedArray(R.array.third_description);
        int descId =descArray.getResourceId(position,0);
        String[] descriptions = res.getStringArray(descId);

        TypedArray imageArray = res.obtainTypedArray(R.array.third_image);
        int imageId = imageArray.getResourceId(position,0);
        TypedArray a = res.obtainTypedArray(imageId);
        Drawable[] images = new Drawable[a.length()];
        for(int i=0;i<images.length;i++){
            images[i] = a.getDrawable(i);
        }

        for(int i=0;i<titles.length;i++){
            list.add(new ThirdCards(titles[i], descriptions[i],images[i]));
        }
    }


    class MyViewHolder{
        TextView title;
        TextView desc;
        ImageView image;

        MyViewHolder(View v){
            title = (TextView) v.findViewById(R.id.third_title);
            desc = (TextView) v.findViewById(R.id.third_description);
            image = (ImageView) v.findViewById(R.id.third_image);

        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        MyViewHolder holder = null;
        if(row==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.know_it_third_row,viewGroup,false);
            holder = new MyViewHolder(row);
            row.setTag(holder);
        }

        else{
            holder = (MyViewHolder) row.getTag();
        }

        ThirdCards card = list.get(i);
        holder.title.setText(card.title);
        holder.desc.setText(card.description);
        holder.image.setImageDrawable(card.image);

        return row;
    }
}
