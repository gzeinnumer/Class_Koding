package com.gzeinnumer.class_koding.presenter;

import android.support.design.widget.TextInputEditText;

import com.athkalia.emphasis.EmphasisTextView;
import com.gzeinnumer.class_koding.activity.Login;

public interface I_Register {
    void actionRegister(TextInputEditText username, TextInputEditText email, TextInputEditText pass, TextInputEditText cPass, TextInputEditText asal);
    void actionLogin(Class<Login> loginClass);
    void setHighLightLogin(EmphasisTextView login);
}
