package com.gzeinnumer.class_koding.adapter;
//done
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.activity.MyLearn;
import com.gzeinnumer.class_koding.activity.Parent;
import com.gzeinnumer.class_koding.model.DataDetailPembayaranUserItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterMyLearn extends RecyclerView.Adapter<AdapterMyLearn.MyHolder> {
    private Context context;
    private ArrayList<DataDetailPembayaranUserItem> list;

    public AdapterMyLearn(Context context, ArrayList<DataDetailPembayaranUserItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_learn, viewGroup, false);
        return new MyHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.namaKelasMyLearn.setText(list.get(i).getMateriNama());
        myHolder.tglPmbyMyLearn.setText(list.get(i).getPmbyTanggal());
        myHolder.keteranganMyLearn.setText(list.get(i).getPmbyStatus());
        if (list.get(i).getPmbyStatus().equals("cek")){
            myHolder.btnGetLihatKelas.setText("Home");
            myHolder.btnGetLihatKelas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyLearn.myOnCLickAdapterHome();
                }
            });
        } else if(list.get(i).getPmbyStatus().equals("lunas")){
            myHolder.btnGetLihatKelas.setText("Lihat Kelas");
            myHolder.btnGetLihatKelas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyLearn.myOnCLickAdapterLihatKelas();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nama_kelas_my_learn)
        TextView namaKelasMyLearn;
        @BindView(R.id.tgl_pmby_my_learn)
        TextView tglPmbyMyLearn;
        @BindView(R.id.keterangan_my_learn)
        TextView keteranganMyLearn;
        @BindView(R.id.btn_lihat_kelas)
        Button btnGetLihatKelas;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
