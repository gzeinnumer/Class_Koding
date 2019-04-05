package com.gzeinnumer.class_koding.presenter;

import com.athkalia.emphasis.EmphasisTextView;
import com.gzeinnumer.class_koding.activity.Parent;
import com.gzeinnumer.class_koding.activity.Register;

public interface I_Login {
    void actionLogin(Class<Parent> parentClass);
    void actionRegister(Class<Register> registerClass);
    void setHighLightSingUp(EmphasisTextView register);
}
