package com.gzeinnumer.class_koding.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.adapter.AdapterMyLearnListForProfil;
import com.gzeinnumer.class_koding.adapter.AdapterMyLearnProgressForProfil;
import com.gzeinnumer.class_koding.helper.MyFunctionFragment;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.DataListMyLearnItem;
import com.gzeinnumer.class_koding.model.DataMyLearnProgressItem;
import com.gzeinnumer.class_koding.model.DataUserItem;
import com.gzeinnumer.class_koding.model.ResponseDataUser;
import com.gzeinnumer.class_koding.model.ResponseListMyLearn;
import com.gzeinnumer.class_koding.model.ResponseMyLearnProgress;
import com.gzeinnumer.class_koding.network.RetroServer;

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

    View view;
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

    Unbinder unbinder;

    SessionManager sessionManager;

    AdapterMyLearnProgressForProfil adapterMyLearnProgressForProfil;
    AdapterMyLearnListForProfil adapterMyLearnListForProfil;

    ArrayList<DataUserItem> dataUser = new ArrayList<>();
    ArrayList<DataMyLearnProgressItem> dataMyLearnProgress = new ArrayList<>();
    ArrayList<DataListMyLearnItem> dataMyLearnList = new ArrayList<>();

    public ProfilFragment() {
        // Required empty public constructor
    }

    public static void myOnClickAdapter(DataMyLearnProgressItem mList) {
//        Intent intent = new Intent(contextFragment, DetailMateri.class);
//        intent.putExtra(DetailMateri.DATA, mList);
//        contextFragment.startActivity(intent);
        Toast.makeText(contextFragment, mList.getProgressModul(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profil, container, false);
        unbinder = ButterKnife.bind(this, view);
        sessionManager = new SessionManager(contextFragment);

        initDataUserForProfile(sessionManager.getUserId());
        initDataMyLearnList(sessionManager.getUserId());
        initDataMyLearnListProgress(sessionManager.getUserId());
        return view;
    }

    private void initDataMyLearnList(String userId) {
        RetroServer.getInstance().getMyLearnList(userId).enqueue(new Callback<ResponseListMyLearn>() {
            @Override
            public void onResponse(Call<ResponseListMyLearn> call, Response<ResponseListMyLearn> response) {
                List<DataListMyLearnItem> list = response.body().getDataListMyLearn();
                for (int i=0; i<list.size(); i++){
                    dataMyLearnList.add(new DataListMyLearnItem(list.get(i).getPmbyBukti(),list.get(i).getPmbyId(),list.get(i).getMateriPlatform(),
                            list.get(i).getPmbyTanggal(),list.get(i).getMateriNama(),list.get(i).getUserId(),list.get(i).getPmbyBatas(),
                            list.get(i).getMateriId(),list.get(i).getPmbyStatus(),list.get(i).getMateriGambar()));
                }
                initDataMyLearnListToRvMyLearnProfil(dataMyLearnList);
            }

            @Override
            public void onFailure(Call<ResponseListMyLearn> call, Throwable t) {

            }
        });
    }

    private void initDataMyLearnListToRvMyLearnProfil(ArrayList<DataListMyLearnItem> dataMyLearnList) {
        adapterMyLearnListForProfil = new AdapterMyLearnListForProfil(contextFragment, dataMyLearnList);
        rvMyLearnTersediaForProfil.setLayoutManager(new LinearLayoutManager(contextFragment){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvMyLearnTersediaForProfil.setAdapter(adapterMyLearnListForProfil);
        rvMyLearnTersediaForProfil.setHasFixedSize(true);
    }

    private void initDataUserForProfile(String userId) {
        RetroServer.getInstance().gatDataUser(userId).enqueue(new Callback<ResponseDataUser>() {
            @Override
            public void onResponse(Call<ResponseDataUser> call, Response<ResponseDataUser> response) {
                List<DataUserItem> list = response.body().getDataUser();

                if (response.isSuccessful()) {
                    for (int i = 0; i < list.size(); i++) {
                        dataUser.add(new DataUserItem(
                                list.get(i).getUserEmail(),
                                list.get(i).getUserPassword(),
                                list.get(i).getUserId(),
                                list.get(i).getUserImage(),
                                list.get(i).getUserAsal(),
                                list.get(i).getUserName(),
                                list.get(i).getUserXp(),
                                list.get(i).getUserDate()));
                    }
                }
                initViewForProfil(imageUser, emailUser, idUser, asalUser, xpUser, dateUser);
            }

            @Override
            public void onFailure(Call<ResponseDataUser> call, Throwable t) {

            }
        });
    }

    private void initViewForProfil(ImageView imageUser,
                                   TextView emailUser,
                                   TextView idUser,
                                   TextView asalUser,
                                   TextView xpUser,
                                   TextView dateUser) {
//        Picasso.get().load(dataUser.get(0).getUserImage()).into(imageUser);
        emailUser.setText(dataUser.get(0).getUserEmail());
        idUser.setText(dataUser.get(0).getUserId());
        asalUser.setText(dataUser.get(0).getUserAsal());
        xpUser.setText(dataUser.get(0).getUserXp());
        dateUser.setText(dataUser.get(0).getUserDate());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initDataMyLearnListProgress(String userId) {
        RetroServer.getInstance().getMyLearnProgress(userId).enqueue(new Callback<ResponseMyLearnProgress>() {
            @Override
            public void onResponse(Call<ResponseMyLearnProgress> call, Response<ResponseMyLearnProgress> response) {
                List<DataMyLearnProgressItem> list = response.body().getDataMyLearn();
                for (int i=0; i<list.size(); i++){
                    dataMyLearnProgress.add(new DataMyLearnProgressItem(
                            list.get(i).getBelajarStatus(),
                            list.get(i).getMateriXp(),
                            list.get(i).getMateriWaktu(),
                            list.get(i).getProgressModul(),
                            list.get(i).getLastSeen(),
                            list.get(i).getMateriNama(),
                            list.get(i).getMateriDiskon(),
                            list.get(i).getMateriLevel(),
                            list.get(i).getMateriGambar(),
                            list.get(i).getBelajarDeadline(),
                            list.get(i).getMateriRating(),
                            list.get(i).getMateriPlatform(),
                            list.get(i).getMateriDeskripsi(),
                            list.get(i).getMateriTgl(),
                            list.get(i).getBelajarMulai(),
                            list.get(i).getMateriJmlModul(),
                            list.get(i).getMateriJumSiswa(),
                            list.get(i).getMateriId(),
                            list.get(i).getMitraId(),
                            list.get(i).getMateriVideo(),
                            list.get(i).getJenisKelasId(),
                            list.get(i).getMateriDeadline(),
                            list.get(i).getBelajarId(),
                            list.get(i).getUserId(),
                            list.get(i).getMateriHarga()));
                }
                initDataMyLearnToRvMyLearnProgressProfil(dataMyLearnProgress);
            }

            @Override
            public void onFailure(Call<ResponseMyLearnProgress> call, Throwable t) {

            }
        });
    }

    private void initDataMyLearnToRvMyLearnProgressProfil(ArrayList<DataMyLearnProgressItem> dataMyLearnProgress) {
        adapterMyLearnProgressForProfil = new AdapterMyLearnProgressForProfil(contextFragment, dataMyLearnProgress);
        rvMyLearnProgressUser.setAdapter(adapterMyLearnProgressForProfil);
        rvMyLearnProgressUser.setHasFixedSize(true);
        rvMyLearnProgressUser.setLayoutManager(new LinearLayoutManager(contextFragment){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }


}
