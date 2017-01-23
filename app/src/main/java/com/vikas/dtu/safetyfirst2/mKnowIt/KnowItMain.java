package com.vikas.dtu.safetyfirst2.mKnowIt;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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

public class KnowItMain extends AppCompatActivity {

    ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.know_it_main);

        myList = (ListView) findViewById(R.id.my_list);
        myList.setAdapter(new FirstCustAdapter(this));
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(KnowItMain.this, KnowItSecond.class);
                intent.putExtra(KnowItSecond.POSITION, i);
                startActivity(intent);
            }
        });

    }
}

class Cards{
    public String title;
    public Drawable image;

    Cards(String title, Drawable image) {
        this.title = title;
        this.image = image;
    }
}


class FirstCustAdapter extends BaseAdapter {

    Context context;
    List<Cards> list;

    FirstCustAdapter(Context c){
        context = c;
        list = new LinkedList<>();

        Resources res = c.getResources();
        String[] titles = res.getStringArray(R.array.item_title);
        TypedArray a = res.obtainTypedArray(R.array.item_picture);
        Drawable[] images = new Drawable[a.length()];
        for(int i=0;i<images.length;i++){
            images[i] = a.getDrawable(i);
        }

        for(int i=0;i<titles.length;i++){
            list.add(new Cards(titles[i], images[i]));
        }
    }

    class MyViewHolder{
        TextView title;
        ImageView image;

        MyViewHolder(View v){
            title = (TextView) v.findViewById(R.id.card_title);
            image = (ImageView) v.findViewById(R.id.card_image);

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
            row = inflater.inflate(R.layout.know_it_main_row,viewGroup,false);
            holder = new MyViewHolder(row);
            row.setTag(holder);
        }

        else{
            holder = (MyViewHolder) row.getTag();
        }

        Cards card = list.get(i);
        holder.title.setText(card.title);
        holder.image.setImageDrawable(card.image);

        return row;
    }
}
