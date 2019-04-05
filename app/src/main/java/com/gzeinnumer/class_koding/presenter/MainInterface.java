package com.gzeinnumer.class_koding.presenter;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.athkalia.emphasis.EmphasisTextView;
import com.gzeinnumer.class_koding.activity.Login;
import com.gzeinnumer.class_koding.activity.Register;
import com.gzeinnumer.class_koding.adapter.AdapterLearn;

public interface MainInterface {

    interface I_Login {
        void actionRegister(Class<Register> registerClass);
        void setHighLightSingUp(EmphasisTextView register);
        void actionLogin(TextInputEditText email, TextInputEditText pass);
    }

    interface I_Register {
        void actionRegister(TextInputEditText username, TextInputEditText email, TextInputEditText pass, TextInputEditText cPass, TextInputEditText asal);
        void actionLogin(Class<Login> loginClass);
        void setHighLightLogin(EmphasisTextView login);
    }

    interface I_LearnFragment {
        void setAdapterLearn(AdapterLearn adapter);
        void setRecyclerViewLearn(RecyclerView rvLearn);
        void setAdapterFirst(AdapterLearn adapter);
        void startShimmer();
        void searchFunction(EditText edSearch);
    }
}
