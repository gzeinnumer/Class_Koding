package com.gzeinnumer.class_koding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.DataEventItem;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailEvent extends MyFunction {

    public static final String DATA = "data";
    private MainInterface.I_DetailEvent i_detailEvent;
    private SessionManager sessionManager;

    @BindView(R.id.event_gambar)
    ImageView eventGambar;
    @BindView(R.id.event_video)
    WebView eventVideo;
    @BindView(R.id.event_nama)
    TextView eventNama;
    @BindView(R.id.event_tgl_mulai)
    TextView eventTglMulai;
    @BindView(R.id.event_mitra_id)
    TextView eventMitraId;
    @BindView(R.id.event_tgl_selesai)
    TextView eventTglSelesai;
    @BindView(R.id.event_xp)
    TextView eventXp;
    @BindView(R.id.event_alamat)
    TextView eventAlamat;
    @BindView(R.id.event_deskripsi)
    TextView eventDeskripsi;
    @BindView(R.id.event_kuota)
    TextView eventKuota;
    @BindView(R.id.event_jenis)
    TextView eventJenis;
    @BindView(R.id.event_id)
    TextView eventId;
    @BindView(R.id.event_tiket)
    ImageView eventTiket;
    @BindView(R.id.event_kota)
    TextView eventKota;
    @BindView(R.id.event_btn_join)
    Button eventBtnJoin;

    DataEventItem dataEventItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(context);
        i_detailEvent = new MainPresenter(context);
        dataEventItem = getIntent().getParcelableExtra(DATA);
        i_detailEvent.setViewForDetailEvent(eventGambar, eventVideo, eventNama, eventTglMulai, eventMitraId, eventTglSelesai, eventXp,
                eventAlamat, eventDeskripsi, eventKuota, eventJenis, eventId, eventTiket, eventKota);
        i_detailEvent.setValueForDetailEvent(dataEventItem);

        eventBtnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, JoinEvent.class);
                intent.putExtra(JoinEvent.DATA, dataEventItem.getEventId());
                startActivity(intent);
            }
        });
    }
}
