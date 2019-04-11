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
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

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

    MainInterface.I_DaftarModul i_daftarModul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_modul);
        ButterKnife.bind(this);

        i_daftarModul = new MainPresenter(context);

        Intent intent = getIntent();
        materiId = intent.getStringExtra(DATA);

        i_daftarModul.setRecyclerViewListModulMateri(rvListModulMateri);

        i_daftarModul.initDataModulList(materiId);
    }


}
