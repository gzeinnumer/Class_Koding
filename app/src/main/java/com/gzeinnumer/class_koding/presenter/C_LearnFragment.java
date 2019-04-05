package com.gzeinnumer.class_koding.presenter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.gzeinnumer.class_koding.adapter.AdapterLearn;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.model.ResponseMateri;
import com.gzeinnumer.class_koding.network.RetroServer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class C_LearnFragment implements I_LearnFragment{
    private Context context;

    public C_LearnFragment(Context context) {
        this.context = context;
    }



    private void shortToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
