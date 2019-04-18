package com.gzeinnumer.class_koding.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunctionFragment;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.DataUserItem;
import com.gzeinnumer.class_koding.model.ResponseDataUser;
import com.gzeinnumer.class_koding.network.RetroServer;
import com.squareup.picasso.Picasso;

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

    Unbinder unbinder;

    SessionManager sessionManager;

    ArrayList<DataUserItem> dataUser = new ArrayList<>();

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

        initDataUserForProfile(sessionManager.getUserId());
        return view;
    }

    private void initDataUserForProfile(String userId) {
        RetroServer.getInstance().gatDataUser(userId).enqueue(new Callback<ResponseDataUser>() {
            @Override
            public void onResponse(Call<ResponseDataUser> call, Response<ResponseDataUser> response) {
                List<DataUserItem> list = response.body().getDataUser();

                if (response.isSuccessful()) {
                    for (int i=0; i<list.size(); i++){
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
                initViewForProfil(imageUser,emailUser, idUser, asalUser, xpUser, dateUser);
            }

            @Override
            public void onFailure(Call<ResponseDataUser> call, Throwable t) {

            }
        });
    }

    private void initViewForProfil(ImageView imageUser, TextView emailUser, TextView idUser, TextView asalUser, TextView xpUser,
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
}
