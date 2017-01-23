package com.vikas.dtu.safetyfirst2.mLaws.StateLaws;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mLaws.LawsRecyclerViewAdapter;
import com.vikas.dtu.safetyfirst2.mLaws.StateLawsRowInfo;

import java.util.ArrayList;

/**
 * Created by DHEERAJ on 23-12-2016.
 */

public class StateFormAdapter extends RecyclerView.Adapter<StateFormAdapter.MyViewHolder> {
public interface OnItemClickListener{
    void onItemClick(StateLawsRowInfo item);
}
    LayoutInflater inflater;
    ArrayList<StateLawsRowInfo> rowInfos;
    private final OnItemClickListener listener;
    StateFormAdapter(Context context, ArrayList<StateLawsRowInfo> rowInfos, StateFormAdapter.OnItemClickListener listener){
        inflater=LayoutInflater.from(context);
        this.rowInfos=rowInfos;
        this.listener=listener;
    }

    @Override
    public StateFormAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.forms_state_recyclerview_row,parent,false);
        StateFormAdapter.MyViewHolder viewHolder= new StateFormAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StateFormAdapter.MyViewHolder holder, int position) {
        holder.bind(rowInfos.get(position),listener);
        StateLawsRowInfo rowInfo=rowInfos.get(position);
        holder.textView.setText(rowInfo.text);
        if(rowInfo.backgroundid!=0){
            holder.imageView.setBackgroundResource(rowInfo.backgroundid);}
    }

    @Override
    public int getItemCount() {
        return rowInfos.size();
    }
    public void insert(int position,StateLawsRowInfo rowInfo){
        rowInfos.add(position,rowInfo);
        notifyItemInserted(position);
    }
class MyViewHolder extends RecyclerView.ViewHolder{
    TextView textView;
    ImageView imageView;
    public MyViewHolder(View itemView) {
        super(itemView);
        textView=(TextView)itemView.findViewById(R.id.Lawtext);
        imageView=(ImageView) itemView.findViewById(R.id.Lawimage);
    }
    public  void bind(final StateLawsRowInfo item, final StateFormAdapter.OnItemClickListener listener){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }
}
}
