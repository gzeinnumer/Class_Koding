package com.gzeinnumer.class_koding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.DataListModulByModulIdItem;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaftarModul extends MyFunction {

    private static final String TAG = "DaftarModul";
    public static final String DATA = "materi_id";
    private MainInterface.I_DaftarModul i_daftarModul;
    private SessionManager sessionManager;

    @BindView(R.id.title_materi)
    TextView titleMateri;
    @BindView(R.id.rv_list_modul_materi)
    RecyclerView rvListModulMateri;

    private String materiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_modul);
        ButterKnife.bind(this);
        setTitle(TAG);
        sessionManager = new SessionManager(context);
        materiId = getIntent().getStringExtra(DATA);
        i_daftarModul = new MainPresenter(context);
        i_daftarModul.regisToTableBelajar(sessionManager.getUserId(),materiId);
        i_daftarModul.setViewForDaftarModul(rvListModulMateri);
        i_daftarModul.initDataModulList(materiId);
    }

    public static void myOnClickAdapter(DataListModulByModulIdItem mList){
        Intent intent = new Intent(context, StartLearning.class);
        intent.putExtra(StartLearning.DATA,mList.getModulId());
        context.startActivity(intent);
    }
}
