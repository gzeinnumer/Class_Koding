package com.gzeinnumer.class_koding.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;

import com.athkalia.emphasis.EmphasisTextView;
import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends MyFunction {
    private static final String TAG = "Login";

    MainInterface.I_Login i_login;

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
        i_login = new MainPresenter(this);
        i_login.setHighLightSingUp(register);
        setTitle(TAG);

    }

    @OnClick({R.id.login, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                i_login.actionLogin(email, pass);
                break;
            case R.id.register:
                i_login.actionRegister(Register.class);
                break;
        }
    }
}
