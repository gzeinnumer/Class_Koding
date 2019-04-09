package com.gzeinnumer.class_koding.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.adapter.AdapterContentList;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.model.DataListContentByModulIdItem;
import com.gzeinnumer.class_koding.model.ResponseContentModul;
import com.gzeinnumer.class_koding.network.RetroServer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartLearning extends MyFunction {

    public static final String DATA = "modul_id";

    String modul_id;

    ArrayList<DataListContentByModulIdItem> listContentByModul;

    AdapterContentList adapterContentList;
    @BindView(R.id.rv_content_by_id_modul)
    RecyclerView rvContentByIdModul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_learning);
        ButterKnife.bind(this);

        modul_id = getIntent().getStringExtra(DATA);
        shortToast(modul_id);

        initDataContentList(modul_id);
    }

    private void initDataContentList(String modul_id) {
        RetroServer.getInstance().getContentByModulId(modul_id).enqueue(new Callback<ResponseContentModul>() {
            @Override
            public void onResponse(Call<ResponseContentModul> call, Response<ResponseContentModul> response) {
                List<DataListContentByModulIdItem> list = response.body().getDataListContentByModulId();
                listContentByModul = new ArrayList<>();
                if (response.body().isSukses()) {
                    for (int i = 0; i < list.size(); i++) {
                        listContentByModul.add(new DataListContentByModulIdItem(list.get(i).getContentUrutan(), list.get(i).getModulId(), list.get(i).getContentIsi(), list.get(i).getModulTipe()));
                    }
                    initDataToRecyclerContentModul();
                } else {
                    shortToast("data tidak ada!!");
                }
            }

            @Override
            public void onFailure(Call<ResponseContentModul> call, Throwable t) {
                shortToast("Cek Koneksi!!");
            }
        });

    }

    private void initDataToRecyclerContentModul() {
        adapterContentList = new AdapterContentList(context, listContentByModul);
        rvContentByIdModul.setAdapter(adapterContentList);
        rvContentByIdModul.setLayoutManager(new LinearLayoutManager(this));
        rvContentByIdModul.setHasFixedSize(true);
    }
}
