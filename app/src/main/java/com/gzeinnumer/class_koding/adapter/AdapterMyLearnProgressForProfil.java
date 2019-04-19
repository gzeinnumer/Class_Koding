package com.gzeinnumer.class_koding.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.model.DataMyLearnProgressItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterMyLearnProgressForProfil extends RecyclerView.Adapter<AdapterMyLearnProgressForProfil.MyHolder> {
    private Context context;
    private ArrayList<DataMyLearnProgressItem> list;

    public AdapterMyLearnProgressForProfil(Context context, ArrayList<DataMyLearnProgressItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_learn_progress_for_profil, viewGroup, false);
        return new MyHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.namaKelasMyProfil.setText(list.get(i).getMateriNama());
        myHolder.platformMyProfil.setText(list.get(i).getMateriPlatform());
        myHolder.progressMyProfil.setText(list.get(i).getProgressModul()+"/"+list.get(i).getMateriJmlModul());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nama_kelas_my_profil)
        TextView namaKelasMyProfil;
        @BindView(R.id.platform_my_profil)
        TextView platformMyProfil;
        @BindView(R.id.progress_my_profil)
        TextView progressMyProfil;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
