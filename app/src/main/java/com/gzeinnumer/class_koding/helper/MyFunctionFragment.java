package com.gzeinnumer.class_koding.helper;
//done
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gzeinnumer.class_koding.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyFunctionFragment extends Fragment {

    @SuppressLint("StaticFieldLeak")
    public static Context contextFragment;


    public ProgressDialog progressDialog;


    public void intent(Class destination) {
        startActivity(new Intent(contextFragment, destination));
    }

    public void shortToast(String msg) {
        Toast.makeText(contextFragment, msg, Toast.LENGTH_SHORT).show();
    }

    public void longToast(String msg) {
        Toast.makeText(contextFragment, msg, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog(String tittle) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(contextFragment);
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

}
