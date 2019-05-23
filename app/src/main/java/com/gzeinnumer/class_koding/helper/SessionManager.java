package com.gzeinnumer.class_koding.helper;
//done
import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    //TODO kenalin
    private String PREF_NAME = "SIMPAN";
    private String KEY_EMAIL = "EMAIL";
    private String KEY_ID = "ID";
    private String KEY_IMAGE = "IMAGE";
    private String KEY_ASAL = "ASAL";
    private String KEY_NAME = "NAME";
    private String KEY_XP = "XP";
    private String KEY_DATE = "DATE";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public SessionManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setUserEmail(String email) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_EMAIL, email).apply();
    }

    public String getEmail() {
        return mSharedPreferences.getString(KEY_EMAIL, null);
    }

    public void setUserId(String s) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_ID, s).apply();
    }

    public String getUserId() {
        return mSharedPreferences.getString(KEY_ID, null);
    }

    public void setUserImage(String s) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_IMAGE, s).apply();
    }

    public String getUserImage() {
        return mSharedPreferences.getString(KEY_IMAGE, null);
    }

    public void setUserAsal(String s) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_ASAL, s).apply();
    }

    public String getAsal() {
        return mSharedPreferences.getString(KEY_ASAL, null);
    }

    public void setUserName(String s) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_NAME, s).apply();
    }

    public String getName() {
        return mSharedPreferences.getString(KEY_NAME, null);
    }

    public void setUserXP(String xp) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_XP, xp).apply();
    }

    public String getXP() {
        return mSharedPreferences.getString(KEY_XP, null);
    }

    public void setUserDate(String s) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_DATE, s).apply();
    }

    public String getDate() {
        return mSharedPreferences.getString(KEY_DATE, null);
    }

    public void logout() {
        mEditor = mSharedPreferences.edit();
        mEditor.clear().commit();
    }
}
