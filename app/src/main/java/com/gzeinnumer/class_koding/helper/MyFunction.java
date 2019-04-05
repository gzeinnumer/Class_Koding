package com.gzeinnumer.class_koding.helper;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.fragment.HomeFragment;

@SuppressLint("Registered")
public class MyFunction extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @SuppressLint("StaticFieldLeak")
    public static FragmentTransaction fragmentTransaction = null;
    public static Fragment fragment = null;

    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MyFunction.this;
    }

    public void intent(Class destination) {
        startActivity(new Intent(context, destination));
    }

    public void shortToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void longToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog(String tittle) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle(tittle);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setIndeterminate(true);
        }

        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void setFragmentToParent(Fragment f){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, f,null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    
}
