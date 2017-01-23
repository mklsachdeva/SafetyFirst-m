package com.vikas.dtu.safetyfirst2.mRecycler;

/**
 * Created by Vikas on 10-07-2016.
 */

public class MyAdapter{

/*    Context c;
    ArrayList<News> news;

    public MyAdapter(Context c, ArrayList<News> news) {

        this.c = c;
        this.news = news;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, null);

        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

       *//* holder.headlineTxt.setText( news.get(position).getHeadline());
        holder.articleTxt.setText(news.get(position).getArticle());
        if(news.get(position).getImgSrc()!=null){
            holder.img .setVisibility(View.VISIBLE);
            PicassoClient.downloadImage(c,news.get(position).getImgSrc(),holder.img);*//*
        }


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
              *//*  Intent intent = new Intent(c, DetailActivity.class);
                intent.putExtra("Headline", news.get(position).getHeadline());
                intent.putExtra("Article", news.get(position).getArticle());*//*

                String transitionName = "hello";

                View viewstart = holder.cardView;
                ActivityOptionsCompat options =

                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) c,
                                viewstart,   // Starting view
                                transitionName    // The String
                        );
                ActivityCompat.startActivity((Activity) c, intent, options.toBundle());
            //    c.startActivity(intent);
               }
        });

holder.likeBtn.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View v)
    {

    }
});
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public void dismissNews(int position){
news.remove(position);
        this.notifyItemRemoved(position);
    }*/

}