package com.gzeinnumer.class_koding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.DataDetailPembayaranItem;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.model.ResponseStatusPembayaran;
import com.gzeinnumer.class_koding.network.RetroServer;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyActivity extends MyFunction {
    private static final String TAG = "BuyActivity";

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

    DataMateriItem dataMateriItem;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ButterKnife.bind(this);
        setTitle(TAG);


        dataMateriItem = getIntent().getParcelableExtra(DATA);

        sessionManager = new SessionManager(context);
        i_buyActivity = new MainPresenter(context);

        i_buyActivity.setViewForBuyActivity(dataMateriItem,
                jumModul,
                xp,
                waktu,
                nama,
                diskon,
                level,
                jumSiswa,
                materiId,
                mitraId,
                jenisKelasId,
                materiPlatform,
                descripsi,
                harga);

        beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PayActivity.class);
                intent.putExtra(PayActivity.DATA, dataMateriItem);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        cekStatusPembelian(sessionManager.getUserId(),dataMateriItem.getMateriId());
    }

    private void cekStatusPembelian(String userId, String materiId) {
        RetroServer.getInstance().getStatusPembelian(userId, materiId).enqueue(new Callback<ResponseStatusPembayaran>() {
            @Override
            public void onResponse(Call<ResponseStatusPembayaran> call, Response<ResponseStatusPembayaran> response) {
                assert response.body() != null;
                List<DataDetailPembayaranItem> list = response.body().getDataDetailPembayaran();
                if (list.get(0).getPmbyStatus().equals("lunas")){
                    Intent intent =new Intent(context, StartLearning.class);
                    intent.putExtra(StartLearning.DATA, dataMateriItem.getMateriId());
                    startActivity(intent);
                    finish();
                } else if (list.get(0).getPmbyStatus().equals("cek")){
                    intent(SabarActivity.class);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusPembayaran> call, Throwable t) {

            }
        });
    }
}
