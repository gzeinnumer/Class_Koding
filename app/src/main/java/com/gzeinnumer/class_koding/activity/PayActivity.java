package com.gzeinnumer.class_koding.activity;
//done
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.DataDetailPembayaranItem;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayActivity extends MyFunction {

    private static final String TAG = "PayActivity";
    public static final String DATA = "data";
    private MainInterface.I_PayActivity i_payActivity;
    private SessionManager sessionManager;

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

    private DataMateriItem dataMateriItem;
    private List<DataDetailPembayaranItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        setTitle(TAG);
        sessionManager = new SessionManager(context);
        dataMateriItem = getIntent().getParcelableExtra(DATA);
        i_payActivity = new MainPresenter(context);
        i_payActivity.setViewForPayActivity(kodeBankReq, noReq, namaReq, totalReq, timeReq);
        i_payActivity.initDataForViewPayActivity(dataMateriItem.getMateriId(), sessionManager.getUserId());
        list = i_payActivity.getListPembayaran();

        btnOploadBukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UploadStruckActivity.class);
                intent.putExtra(UploadStruckActivity.DATA, dataMateriItem);
                startActivity(intent);
            }
        });

    }
}
