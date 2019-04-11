package com.gzeinnumer.class_koding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends MyFunction {


    public static final String DATA = "data";
    @BindView(R.id.noreq)
    TextView noreq;
    @BindView(R.id.btn_upload_bukti)
    Button btnOploadBukti;


    MainInterface.I_PayActivity i_payActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);

        i_payActivity= new MainPresenter(context);

        final DataMateriItem dataMateriItem = getIntent().getParcelableExtra(DATA);

        i_payActivity.setViewForPayActivity(dataMateriItem ,noreq, btnOploadBukti);
    }
}
