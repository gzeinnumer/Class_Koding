package com.gzeinnumer.class_koding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyActivity extends MyFunction {

    public static final String DATA = "data";
    @BindView(R.id.jum_modul)
    TextView jumModul;
    @BindView(R.id.xp)
    TextView xp;
    @BindView(R.id.waktu)
    TextView waktu;
    @BindView(R.id.nama)
    TextView nama;
    @BindView(R.id.diskon)
    TextView diskon;
    @BindView(R.id.level)
    TextView level;
    @BindView(R.id.jum_siswa)
    TextView jumSiswa;
    @BindView(R.id.materi_id)
    TextView materiId;
    @BindView(R.id.mitra_id)
    TextView mitraId;
    @BindView(R.id.jenis_kelas_id)
    TextView jenisKelasId;
    @BindView(R.id.materi_platform)
    TextView materiPlatform;
    @BindView(R.id.descripsi)
    TextView descripsi;
    @BindView(R.id.harga)
    TextView harga;
    @BindView(R.id.beli)
    Button beli;

    MainInterface.I_BuyActivity i_buyActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ButterKnife.bind(this);

        final DataMateriItem dataMateriItem = getIntent().getParcelableExtra(DATA);

        i_buyActivity = new MainPresenter(context);

        i_buyActivity.setViewForBuyActivity(dataMateriItem, jumModul, xp, waktu, nama, diskon, level, jumSiswa, materiId, mitraId, jenisKelasId, materiPlatform, descripsi, harga, beli);

    }
}