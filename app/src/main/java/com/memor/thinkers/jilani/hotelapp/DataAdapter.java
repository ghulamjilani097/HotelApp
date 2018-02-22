package com.memor.thinkers.jilani.hotelapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder>
        {
    List<Data> dataList;
    Context context;

    public DataAdapter(List<Data> dataList,Context context){
        this.dataList=dataList;
        this.context=context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView,textView1;

        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
            textView1=(TextView)view.findViewById(R.id.textView2);
        }
    }

    public DataAdapter(List<Data> moviesList) {
        this.dataList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

            @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.textView.setText(data.getFood());
        holder.textView1.setText(data.getFooddetails());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
