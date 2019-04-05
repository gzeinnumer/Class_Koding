package com.gzeinnumer.class_koding.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.adapter.AdapterLearn;
import com.gzeinnumer.class_koding.helper.MyFunctionFragment;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.model.ResponseMateri;
import com.gzeinnumer.class_koding.network.RetroServer;
import com.gzeinnumer.class_koding.presenter.C_LearnFragment;
import com.gzeinnumer.class_koding.presenter.I_LearnFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragment extends MyFunctionFragment {

    @BindView(R.id.rv_learn)
    RecyclerView rvLearn;
    @BindView(R.id.ed_search)
    EditText edSearch;

    Unbinder unbinder;
    AdapterLearn adapter;
    View view;
    ArrayList<DataMateriItem> list = new ArrayList<>();

    I_LearnFragment i_learnFragment;
    public LearnFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_learn, container, false);
        unbinder = ButterKnife.bind(this, view);

        contextFragment = view.getContext();
        i_learnFragment = new C_LearnFragment(contextFragment);

        startShimmer();
        searchFunction(edSearch);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setAdapterFirst();
    }

    public void setAdapterFirst() {
        adapter = new AdapterLearn(contextFragment, list, true);
        rvLearn.setAdapter(adapter);
        rvLearn.setHasFixedSize(true);
        rvLearn.setLayoutManager(new GridLayoutManager(contextFragment, 3));
    }
    public void startShimmer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
                adapter.isShimmer = false;
                adapter.notifyDataSetChanged();
            }
        }, 4999);
    }

    private void initData() {
        RetroServer.getInstance().getAllMateri().enqueue(new Callback<ResponseMateri>() {
            @Override
            public void onResponse(Call<ResponseMateri> call, Response<ResponseMateri> response) {
                List<DataMateriItem> listData = response.body().getDataMateri();
                list = new ArrayList<>();

                if (response.body().isSukses()) {
                    for (int i = 0; i < listData.size(); i++) {
                        list.add(new DataMateriItem(listData.get(i).getMateriJmlModul(),
                                listData.get(i).getMateriXp(),
                                listData.get(i).getMateriWaktu(),
                                listData.get(i).getMateriNama(),
                                listData.get(i).getMateriDiskon(),
                                listData.get(i).getMateriLevel(),
                                listData.get(i).getMateriJumSiswa(),
                                listData.get(i).getMateriGambar(),
                                listData.get(i).getMateriId(),
                                listData.get(i).getMitraId(),
                                listData.get(i).getMateriVideo(),
                                listData.get(i).getMateriRating(),
                                listData.get(i).getJenisKelasId(),
                                listData.get(i).getMateriDeadline(),
                                listData.get(i).getMateriPlatform(),
                                listData.get(i).getMateriDeskripsi(),
                                listData.get(i).getMateriHarga(),
                                listData.get(i).getMateriTgl()));
                    }
                    if (list.size() > 0) {
                        initToRecycler();
                    } else {
                        shortToast("data ada!!");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMateri> call, Throwable t) {
                shortToast("data tidak ada!!");
            }
        });
    }

    public void searchFunction(EditText edSearch) {
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(String.valueOf(s));
            }
        });
    }


    private void initToRecycler() {
        adapter = new AdapterLearn(contextFragment, list, false);
        rvLearn.setAdapter(adapter);
        rvLearn.setHasFixedSize(true);
        rvLearn.setLayoutManager(new GridLayoutManager(contextFragment, 3));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
