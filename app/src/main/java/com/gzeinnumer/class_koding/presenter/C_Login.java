package com.gzeinnumer.class_koding.presenter;

import android.content.Context;
import android.content.Intent;

import com.athkalia.emphasis.EmphasisTextView;
import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.activity.Parent;
import com.gzeinnumer.class_koding.activity.Register;

public class C_Login implements I_Login {
    private Context context;

    public C_Login(Context context) {
        this.context = context;
    }

    @Override
    public void actionLogin(Class<Parent> parentClass) {
        context.startActivity(new Intent(context, parentClass));
    }

    @Override
    public void actionRegister(Class<Register> registerClass) {
        context.startActivity(new Intent(context, registerClass));
    }

    @Override
    public void setHighLightSingUp(EmphasisTextView register) {
        register.setText("Havent Account? Sign Up!!");
        register.setTextToHighlight("Sign Up!!");
        register.setTextHighlightColor(R.color.colorAccent);
        register.setCaseInsensitive(true);
        register.highlight();
    }
}
