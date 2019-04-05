package com.gzeinnumer.class_koding.presenter;

import com.athkalia.emphasis.EmphasisTextView;
import com.gzeinnumer.class_koding.activity.Login;
import com.gzeinnumer.class_koding.activity.Register;

public interface I_Register {
    void actionRegister();
    void actionLogin(Class<Login> loginClass);
    void setHighLightLogin(EmphasisTextView login);
}
