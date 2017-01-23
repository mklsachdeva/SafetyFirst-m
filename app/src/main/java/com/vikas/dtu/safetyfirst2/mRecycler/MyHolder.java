package com.vikas.dtu.safetyfirst2.mRecycler;

/**
 * Created by Vikas on 10-07-2016.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mInterface.ItemClickListener;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
  CardView cardView;

    TextView headlineTxt;
    ImageView img;
    Button readMoreBtn;
    ImageButton likeBtn;
    TextView articleTxt;
    ItemClickListener itemClickListener;

    public MyHolder(View itemView) {
        super(itemView);
        cardView = (CardView)itemView.findViewById(R.id.card_view);
        headlineTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        img= (ImageView) itemView.findViewById(R.id.movieImage);
        readMoreBtn= (Button) itemView.findViewById(R.id.readMoreBtn);
        articleTxt= (TextView) itemView.findViewById(R.id.articleTxt);
        likeBtn = (ImageButton) itemView.findViewById(R.id.favorite_button);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(view, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener = ic;

    }

}
