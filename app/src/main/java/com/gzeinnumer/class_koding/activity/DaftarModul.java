package com.gzeinnumer.class_koding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.adapter.AdapterModulList;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.DataListModulByModulIdItem;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaftarModul extends MyFunction {

    private static final String TAG = "DaftarModul";

    public static final String DATA = "materi_id";

    @BindView(R.id.title_materi)
    TextView titleMateri;
    @BindView(R.id.rv_list_modul_materi)
    RecyclerView rvListModulMateri;

    AdapterModulList adapterModulList;
    ArrayList<DataListModulByModulIdItem> listDataListModul;
    String materiId;

    MainInterface.I_DaftarModul i_daftarModul;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_modul);
        ButterKnife.bind(this);

        setTitle(TAG);

        i_daftarModul = new MainPresenter(context);
        sessionManager = new SessionManager(context);

        Intent intent = getIntent();
        materiId = intent.getStringExtra(DATA);

        i_daftarModul.setRecyclerViewListModulMateri(rvListModulMateri);

        i_daftarModul.initDataModulList(materiId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listDataListModul = i_daftarModul.getListDataListModul();
        adapterModulList = i_daftarModul.getAdapterModulList();
    }
}
