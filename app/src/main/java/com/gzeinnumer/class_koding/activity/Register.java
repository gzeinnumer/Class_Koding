package com.gzeinnumer.class_koding.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.athkalia.emphasis.EmphasisTextView;
import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.model.ResponseRegister;
import com.gzeinnumer.class_koding.network.RetroServer;
import com.gzeinnumer.class_koding.presenter.I_Register;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends MyFunction {

    @BindView(R.id.username)
    TextInputEditText username;
    @BindView(R.id.email)
    TextInputEditText email;
    @BindView(R.id.pass)
    TextInputEditText pass;
    @BindView(R.id.c_pass)
    TextInputEditText cPass;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.login)
    EmphasisTextView login;
    I_Register i_register;
    @BindView(R.id.asal)
    TextInputEditText asal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        i_register = new MainPresenter(this);
        i_register.setHighLightLogin(login);

    }

    @OnClick({R.id.register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                i_register.actionRegister(username, email, pass, cPass, asal);
                break;
            case R.id.login:
                i_register.actionLogin(Login.class);
                break;
        }
    }

}
