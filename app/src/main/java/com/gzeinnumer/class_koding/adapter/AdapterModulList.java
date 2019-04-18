package com.gzeinnumer.class_koding.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.activity.StartLearning;
import com.gzeinnumer.class_koding.model.DataListModulByModulIdItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterModulList extends RecyclerView.Adapter<AdapterModulList.MyHolder> {
    private Context context;
    private ArrayList<DataListModulByModulIdItem> list = new ArrayList<>();
    private int number = 1;

    public AdapterModulList(Context context, ArrayList<DataListModulByModulIdItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.modul_list_item, viewGroup, false);
        return new MyHolder(view);
    }

    public Boolean statusLoad;
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, @SuppressLint("RecyclerView") final int i) {
        if (list.get(i).getStatus().equals("1")){
            myHolder.cek.setVisibility(View.VISIBLE);
        } else if(list.get(i).getStatus().equals("0")){
            myHolder.cek.setVisibility(View.INVISIBLE);
        }

        myHolder.judulListModul.setText(list.get(i).getModulJudul());
        myHolder.noListModul.setText(String.valueOf(number) + ".");
        myHolder.cardListModul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StartLearning.class);
                intent.putExtra(StartLearning.DATA,list.get(i).getModulId());
                context.startActivity(intent);
            }
        });

        number++;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_list_modul)
        CardView cardListModul;
        @BindView(R.id.no_list_modul)
        TextView noListModul;
        @BindView(R.id.judul_list_modul)
        TextView judulListModul;
        @BindView(R.id.cek)
        ImageView cek;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
