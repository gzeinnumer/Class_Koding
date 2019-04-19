package com.gzeinnumer.class_koding.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.activity.DetailMateri;
import com.gzeinnumer.class_koding.adapter.AdapterMyLearnListForProfil;
import com.gzeinnumer.class_koding.adapter.AdapterMyLearnProgressForProfil;
import com.gzeinnumer.class_koding.helper.MyFunctionFragment;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.DataDetailMateriFromProfilItem;
import com.gzeinnumer.class_koding.model.DataListMyLearnItem;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.model.DataMyLearnProgressItem;
import com.gzeinnumer.class_koding.model.DataUserItem;
import com.gzeinnumer.class_koding.model.ResponseMateriByIdFromProfil;
import com.gzeinnumer.class_koding.network.RetroServer;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

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
public class ProfilFragment extends MyFunctionFragment {

    private MainInterface.I_ProfilFragment i_profilFragment;
    private SessionManager sessionManager;

    @BindView(R.id.email_user)
    TextView emailUser;
    @BindView(R.id.id_user)
    TextView idUser;
    @BindView(R.id.asal_user)
    TextView asalUser;
    @BindView(R.id.nama_user)
    TextView namaUser;
    @BindView(R.id.xp_user)
    TextView xpUser;
    @BindView(R.id.date_user)
    TextView dateUser;
    @BindView(R.id.image_user)
    ImageView imageUser;
    @BindView(R.id.rv_my_learn_progress_user)
    RecyclerView rvMyLearnProgressUser;
    @BindView(R.id.rv_my_learn_tersedia_for_profil)
    RecyclerView rvMyLearnTersediaForProfil;

    private Unbinder unbinder;
    private View view;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profil, container, false);
        unbinder = ButterKnife.bind(this, view);
        sessionManager = new SessionManager(contextFragment);
        i_profilFragment = new MainPresenter(contextFragment);
        i_profilFragment.initDataUserForProfile(sessionManager.getUserId());
        i_profilFragment.setViewForProfilFragment(imageUser,
                emailUser,
                idUser,
                asalUser,
                xpUser,
                dateUser,
                namaUser,
                rvMyLearnProgressUser,
                rvMyLearnTersediaForProfil);

        i_profilFragment.initDataMyLearnList(sessionManager.getUserId());
        i_profilFragment.initDataMyLearnListProgress(sessionManager.getUserId());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static void myOnClickAdapterMyLearnList(DataListMyLearnItem dataListMyLearnItem) {
        RetroServer.getInstance().getMateriDetailFromProfil(dataListMyLearnItem.getMateriId()).enqueue(new Callback<ResponseMateriByIdFromProfil>() {
            @Override
            public void onResponse(Call<ResponseMateriByIdFromProfil> call, Response<ResponseMateriByIdFromProfil> response) {
                List<DataDetailMateriFromProfilItem> list = response.body().getDataDetailMateriFromProfil();
                ArrayList<DataMateriItem> data = new ArrayList<>();
                for (int i=0; i<list.size(); i++){
                    data.add(new DataMateriItem(
                            list.get(i).getMateriJmlModul(),
                            list.get(i).getMateriXp(),
                            list.get(i).getMateriWaktu(),
                            list.get(i).getMateriNama(),
                            list.get(i).getMateriDiskon(),
                            list.get(i).getMateriLevel(),
                            list.get(i).getMateriJumSiswa(),
                            list.get(i).getMateriGambar(),
                            list.get(i).getMateriId(),
                            list.get(i).getMitraId(),
                            list.get(i).getMateriVideo(),
                            list.get(i).getMateriRating(),
                            list.get(i).getJenisKelasId(),
                            list.get(i).getMateriDeadline(),
                            list.get(i).getMateriPlatform(),
                            list.get(i).getMateriDeskripsi(),
                            list.get(i).getMateriHarga(),
                            list.get(i).getMateriTgl()));
                }
                Intent intent = new Intent(contextFragment, DetailMateri.class);
                intent.putExtra(DetailMateri.DATA, data.get(0));
                contextFragment.startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseMateriByIdFromProfil> call, Throwable t) {

            }
        });
    }

}
