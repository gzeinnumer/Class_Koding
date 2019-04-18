package com.gzeinnumer.class_koding.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.adapter.AdapterMyLearn;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.DataDetailPembayaranUserItem;
import com.gzeinnumer.class_koding.model.ResponseDetailPembayaranUser;
import com.gzeinnumer.class_koding.network.RetroServer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyLearn extends MyFunction {
    private static final String TAG = "MyLearn";

    @BindView(R.id.rv_my_learn)
    RecyclerView rvMyLearn;

    SessionManager sessionManager;

    ArrayList<DataDetailPembayaranUserItem> listDataDetailPembayaranUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_learn);
        setTitle(TAG);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        Toast.makeText(this, sessionManager.getUserId(), Toast.LENGTH_SHORT).show();

        initDataMyLearn();
    }

    private void initDataMyLearn() {
        RetroServer.getInstance().getDetailPmbyByIdUser(sessionManager.getUserId()).enqueue(new Callback<ResponseDetailPembayaranUser>() {
            @Override
            public void onResponse(Call<ResponseDetailPembayaranUser> call, Response<ResponseDetailPembayaranUser> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    List<DataDetailPembayaranUserItem> data = response.body().getDataDetailPembayaranUser();
                    assert data != null;
                    for (int i=0; i<data.size(); i++){
                        listDataDetailPembayaranUser.add(new DataDetailPembayaranUserItem(
                                data.get(0).getPmbyBukti(),
                                data.get(0).getMateriXp(),
                                data.get(0).getMateriWaktu(),
                                data.get(0).getUserPassword(),
                                data.get(0).getMateriNama(),
                                data.get(0).getMateriDiskon(),
                                data.get(0).getMateriLevel(),
                                data.get(0).getMateriGambar(),
                                data.get(0).getUserName(),
                                data.get(0).getPmbyStatus(),
                                data.get(0).getMateriRating(),
                                data.get(0).getMateriPlatform(),
                                data.get(0).getMateriDeskripsi(),
                                data.get(0).getUserXp(),
                                data.get(0).getUserDate(),
                                data.get(0).getMateriTgl(),
                                data.get(0).getMateriJmlModul(),
                                data.get(0).getUserEmail(),
                                data.get(0).getPmbyBatas(),
                                data.get(0).getMateriJumSiswa(),
                                data.get(0).getUserImage(),
                                data.get(0).getUserAsal(),
                                data.get(0).getMateriId(),
                                data.get(0).getMitraId(),
                                data.get(0).getMateriVideo(),
                                data.get(0).getJenisKelasId(),
                                data.get(0).getMateriDeadline(),
                                data.get(0).getPmbyId(),
                                data.get(0).getPmbyTanggal(),
                                data.get(0).getUserId(),
                                data.get(0).getMateriHarga()));
                    }
                    initDataToRvMyLearn(listDataDetailPembayaranUser);
                }

            }

            @Override
            public void onFailure(Call<ResponseDetailPembayaranUser> call, Throwable t) {

            }
        });
    }

    private void initDataToRvMyLearn(ArrayList<DataDetailPembayaranUserItem> listDataDetailPembayaranUser) {
        AdapterMyLearn adapter = new AdapterMyLearn(context, listDataDetailPembayaranUser);
        rvMyLearn.setAdapter(adapter);
        rvMyLearn.setLayoutManager(new LinearLayoutManager(context));
        rvMyLearn.setHasFixedSize(true);
    }
}
