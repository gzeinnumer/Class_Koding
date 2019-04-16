package com.gzeinnumer.class_koding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.DataDetailPembayaranItem;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.model.ResponseGetPembayaran;
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

public class PayActivity extends MyFunction {


    public static final String DATA = "data";

    @BindView(R.id.btn_upload_bukti)
    Button btnOploadBukti;
    @BindView(R.id.kode_bank_req)
    TextView kodeBankReq;
    @BindView(R.id.no_req)
    TextView noReq;
    @BindView(R.id.nama_req)
    TextView namaReq;
    @BindView(R.id.total_req)
    TextView totalReq;
    @BindView(R.id.time_req)
    TextView timeReq;

    MainInterface.I_PayActivity i_payActivity;

    DataMateriItem dataMateriItem;
    List<DataDetailPembayaranItem> list;

    SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);

        i_payActivity = new MainPresenter(context);

        sessionManager = new SessionManager(context);

        dataMateriItem = getIntent().getParcelableExtra(DATA);

        i_payActivity.setViewForPayActivity(kodeBankReq, noReq, namaReq, totalReq, timeReq);

        i_payActivity.initDataForViewPayActivity(dataMateriItem.getMateriId(), sessionManager.getUserId());

        list = i_payActivity.getListPembayaran();

        btnOploadBukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UploadStruckActivity.class);
                intent.putExtra(UploadStruckActivity.DATA, (Parcelable) list);
                startActivity(intent);
            }
        });

    }


}
