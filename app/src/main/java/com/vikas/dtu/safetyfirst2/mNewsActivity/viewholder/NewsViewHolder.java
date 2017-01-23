package com.vikas.dtu.safetyfirst2.mNewsActivity.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mData.News;
import com.vikas.dtu.safetyfirst2.mPicasso.PicassoClient;


public class NewsViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public ImageView bookmark;
    public ImageView share;
    public TextView bodyView;
    public ImageView mNewsImage;

    public NewsViewHolder(View itemView) {
        super(itemView);

        titleView = (TextView) itemView.findViewById(R.id.post_title);
        bodyView = (TextView) itemView.findViewById(R.id.post_body);
      //  bookmark = (ImageView) itemView.findViewById(R.id.bookmark);
     //   share = (ImageView) itemView.findViewById(R.id.share);
        mNewsImage = (ImageView) itemView.findViewById(R.id.card_image);

    }

    public void bindToNews(News news,
                           //View.OnClickListener starClickListener, View.OnClickListener shareClickListener,
                            Context c) {
        titleView.setText(news.title);
        bodyView.setText(news.body);
      //  bookmark.setOnClickListener(starClickListener);
//        share.setOnClickListener(shareClickListener);
        if(news.imgUrl!=null){
            PicassoClient.downloadImage(c, news.imgUrl, mNewsImage);
            mNewsImage.setVisibility(View.VISIBLE);
        }
        else{
            mNewsImage.setVisibility(View.GONE);

        }
//
    }
}
