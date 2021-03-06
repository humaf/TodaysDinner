package com.example.todaysdinner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by redrose on 10/7/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private String[] dataSource;
    private List<RecyclerItem> ingItemList;
    private Context rcontext;

    public RecyclerAdapter(Context context, List<RecyclerItem> iItemList) {
        this.ingItemList =iItemList;
        this.rcontext = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        Log.d("onCreateView", "onCreateView");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
      RecyclerItem recyclerItem = ingItemList.get(position);
        holder.textView.setText(recyclerItem.getIngredient());
        Log.d("check", recyclerItem.getIngredient().toString());
    }

    @Override
    public int getItemCount() {
        return ingItemList.size();
    }

    public  class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public  TextView textView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.list_item);
        }
    }
}