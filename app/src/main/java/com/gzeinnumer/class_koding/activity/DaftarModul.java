package com.gzeinnumer.class_koding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.adapter.AdapterModulList;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.model.DataListModulByModulIdItem;
import com.gzeinnumer.class_koding.model.ResponseListModul;
import com.gzeinnumer.class_koding.network.RetroServer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarModul extends MyFunction {

    public static final String DATA = "materi_id";
    @BindView(R.id.title_materi)
    TextView titleMateri;
    @BindView(R.id.rv_list_modul_materi)
    RecyclerView rvListModulMateri;
    AdapterModulList adapterModulList;
    ArrayList<DataListModulByModulIdItem> listDataListModul;
    String materiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_modul);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        materiId = intent.getStringExtra(DATA);

        initDataModulList(materiId);
    }

    private void initDataModulList(String materiId) {
        RetroServer.getInstance().getAllListModul(materiId).enqueue(new Callback<ResponseListModul>() {
            @Override
            public void onResponse(Call<ResponseListModul> call, Response<ResponseListModul> response) {
                List<DataListModulByModulIdItem> list = response.body().getDataListModulByModulId();
                listDataListModul = new ArrayList<>();
                if (response.body().isSukses()){
                    for (int i=0; i<list.size(); i++){
                        listDataListModul.add(new DataListModulByModulIdItem(list.get(i).getMateriId(), list.get(i).getModulId(), list.get(i).getModulJudul()));
                    }
                    initDataToRecyclerListModulMateri();
                } else {
                    shortToast("Data tidak ada!!!");
                }
            }

            @Override
            public void onFailure(Call<ResponseListModul> call, Throwable t) {
                shortToast("Cek Koneksi kamu!!");
            }
        });
    }

    private void initDataToRecyclerListModulMateri() {
        adapterModulList = new AdapterModulList(context, listDataListModul);
        rvListModulMateri.setAdapter(adapterModulList);
        rvListModulMateri.setHasFixedSize(true);
        rvListModulMateri.setLayoutManager(new LinearLayoutManager(this));
    }

}
