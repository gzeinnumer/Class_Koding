package com.gzeinnumer.class_koding.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyLearn extends MyFunction {

    @BindView(R.id.rv_my_learn)
    RecyclerView rvMyLearn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_learn);
        ButterKnife.bind(this);
    }
}
