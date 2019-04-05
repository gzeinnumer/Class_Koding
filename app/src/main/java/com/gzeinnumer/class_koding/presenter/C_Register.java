package com.gzeinnumer.class_koding.presenter;

import android.content.Context;
import android.content.Intent;

import com.athkalia.emphasis.EmphasisTextView;
import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.activity.Login;

public class C_Register implements I_Register{
    private Context context;

    public C_Register(Context context) {
        this.context = context;
    }


    @Override
    public void actionRegister() {

    }

    @Override
    public void actionLogin(Class<Login> loginClass) {
        context.startActivity(new Intent(context, loginClass));
    }

    @Override
    public void setHighLightLogin(EmphasisTextView login) {
        login.setText("Have Account? Login!!");
        login.setTextToHighlight("Login!!");
        login.setTextHighlightColor(R.color.colorAccent);
        login.setCaseInsensitive(true);
        login.highlight();
    }
}
