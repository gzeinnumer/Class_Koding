package com.gzeinnumer.class_koding.presenter;

import android.support.design.widget.TextInputEditText;

import com.athkalia.emphasis.EmphasisTextView;
import com.gzeinnumer.class_koding.activity.Parent;
import com.gzeinnumer.class_koding.activity.Register;

public interface I_Login {
    void actionRegister(Class<Register> registerClass);
    void setHighLightSingUp(EmphasisTextView register);
    void actionLogin(TextInputEditText email, TextInputEditText pass);
}
