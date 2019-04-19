package com.gzeinnumer.class_koding.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.fragment.ProfilFragment;
import com.gzeinnumer.class_koding.model.DataListMyLearnItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterMyLearnListForProfil extends RecyclerView.Adapter<AdapterMyLearnListForProfil.MyHolder>{
    private Context context;
    private ArrayList<DataListMyLearnItem> list;

    public AdapterMyLearnListForProfil(Context context, ArrayList<DataListMyLearnItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_learn_list_for_profil, viewGroup, false);
        return new MyHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        Picasso.get().load(list.get(i).getMateriGambar()).into(myHolder.gambarKelasMyProfil);
        myHolder.namaKelasMyProfil.setText(list.get(i).getMateriNama());
        myHolder.platformMyProfil.setText(list.get(i).getMateriPlatform());
        myHolder.showClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "hay", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.gambar_kelas_my_profil)
        ImageView gambarKelasMyProfil;
        @BindView(R.id.nama_kelas_my_profil)
        TextView namaKelasMyProfil;
        @BindView(R.id.platform_my_profil)
        TextView platformMyProfil;
        @BindView(R.id.show_class)
        Button showClass;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
