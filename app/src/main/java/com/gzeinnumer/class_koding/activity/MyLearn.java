package com.gzeinnumer.class_koding.activity;
//done
import android.content.Intent;
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
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyLearn extends MyFunction {
    private static final String TAG = "MyLearn";
    private MainInterface.I_MyLearn i_myLearn;
    private SessionManager sessionManager;

    @BindView(R.id.rv_my_learn_tersedia)
    RecyclerView rvMyLearn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_learn);
        ButterKnife.bind(this);
        setTitle(TAG);
        sessionManager = new SessionManager(context);
        i_myLearn = new MainPresenter(context);
        i_myLearn.setViewForMyLearn(rvMyLearn);
        i_myLearn.initDataMyLearn(sessionManager.getUserId());
    }

    public static void myOnCLickAdapterHome() {
        Intent intent = new Intent(context, Parent.class);
        context.startActivity(intent);
    }

    public static void myOnCLickAdapterLihatKelas() {

    }
}
