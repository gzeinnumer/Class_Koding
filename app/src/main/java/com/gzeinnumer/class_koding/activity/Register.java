package com.gzeinnumer.class_koding.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.athkalia.emphasis.EmphasisTextView;
import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.model.ResponseRegister;
import com.gzeinnumer.class_koding.network.RetroServer;
import com.gzeinnumer.class_koding.presenter.C_Register;
import com.gzeinnumer.class_koding.presenter.I_Register;

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
        i_register = new C_Register(this);
        i_register.setHighLightLogin(login);

    }

    @OnClick({R.id.register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                actionResgister();
                break;
            case R.id.login:
                i_register.actionLogin(Login.class);
                break;
        }
    }

    private void actionResgister() {
        String temp1 = username.getText().toString().trim();
        String temp2 = email.getText().toString().trim();
        String temp3 = pass.getText().toString().trim();
        String temp3_1 = cPass.getText().toString().trim();
        String temp4 = asal.getText().toString().trim();
        if (TextUtils.isEmpty(temp1)){
            username.setError(getString(R.string.isEmpty));
            username.requestFocus();
        } else if (TextUtils.isEmpty(temp2)){
            email.setError(getString(R.string.isEmpty));
            email.requestFocus();
        } else if (TextUtils.isEmpty(temp3)){
            pass.setError(getString(R.string.isEmpty));
            pass.requestFocus();
        } else if (!temp3.equals(temp3_1)){
            cPass.setError("Not Match");
            cPass.requestFocus();
        } else if (TextUtils.isEmpty(temp4)){
            asal.setError(getString(R.string.isEmpty));
            asal.requestFocus();
        } else {
            RetroServer.getInstance().register(temp1, temp2, temp3, temp4).enqueue(new Callback<ResponseRegister>() {
                @Override
                public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                    if (response.body().isSukses()){
                        shortToast("SuksesDaftar");
                        intent(Login.class);
                    } else {
                        shortToast("Failed");
                    }
                }

                @Override
                public void onFailure(Call<ResponseRegister> call, Throwable t) {
                    shortToast("Chck Your Connection");
                }
            });
        }
    }

}
