package com.gzeinnumer.class_koding.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.model.DataListContentByModulIdItem;

import java.util.ArrayList;
import java.util.PriorityQueue;

class AdapterContentList extends RecyclerView.Adapter<AdapterContentList.MyHolder> {

    private Context context;
    private ArrayList<DataListContentByModulIdItem> list = new ArrayList<>();

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_content_modul, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
