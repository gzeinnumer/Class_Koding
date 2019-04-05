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
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.model.ResponseLogin;
import com.gzeinnumer.class_koding.network.RetroServer;
import com.gzeinnumer.class_koding.presenter.C_Login;
import com.gzeinnumer.class_koding.presenter.I_Login;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends MyFunction {

    I_Login i_login;
    @BindView(R.id.email)
    TextInputEditText email;
    @BindView(R.id.pass)
    TextInputEditText pass;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.register)
    EmphasisTextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        i_login = new C_Login(this);
        i_login.setHighLightSingUp(register);
    }

    @OnClick({R.id.login, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                actionLogin();
                break;
            case R.id.register:
                i_login.actionRegister(Register.class);
                break;
        }
    }

    private void actionLogin() {
        String temp1 = email.getText().toString().trim();
        String temp2 = pass.getText().toString().trim();
        if(TextUtils.isEmpty(temp1)){
            email.setError(getString(R.string.isEmpty));
            email.requestFocus();
        } else if(TextUtils.isEmpty(temp2)){
            pass.setError(getString(R.string.isEmpty));
            pass.requestFocus();

        } else {
            RetroServer.getInstance().login(temp1, temp2).enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    if (response.body().isSukses()){
                        intent(Parent.class);
                    } else {
                        shortToast("Gagal Login");
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {

                }
            });
        }

    }


}
