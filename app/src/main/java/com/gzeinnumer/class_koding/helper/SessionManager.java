package com.gzeinnumer.class_koding.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;

import com.gzeinnumer.class_koding.activity.Login;
import com.gzeinnumer.class_koding.activity.Parent;

public class SessionManager extends MyFunction {
    @VisibleForTesting

    /*variable sharepreference*/
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    public SessionManager sessionManager;

    /*mode share preference*/
    int mode = 0;

    /*nama dari share preference*/
    private static final String pref_name = "crudpref";

    /*kunci share preference*/
    private static final String is_login = "islogin";
    public static final String kunci_email = "keyemail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(getApplicationContext());
    }

    public SessionManager() {

    }

    /*construktor*/
    public SessionManager(Context c) {
        context = c;
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    /*methode membuat session*/
    public void createSession(String email) {
        editor.putBoolean(is_login, true);
        editor.putString(kunci_email, email);
        editor.commit();
    }

    public void setIdUser(String iduser) {
        editor.putBoolean(is_login, true);
        editor.putString("iduser", iduser);
        editor.commit();
    }

    public String getIdUser() {
        return pref.getString("iduser", "");
    }

    public void checkLogin() {
        if (!this.islogin()) {
            Intent i = new Intent(context, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Intent i = new Intent(context, Parent.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public boolean islogin() {
        return pref.getBoolean(is_login, false);
    }

    public void logout() {
        editor.clear();
        editor.commit();

        Intent i = new Intent(context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
