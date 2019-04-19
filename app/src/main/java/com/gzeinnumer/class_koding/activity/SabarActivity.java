package com.gzeinnumer.class_koding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SabarActivity extends MyFunction {

    private static final String TAG = "SabarActivity";

    @BindView(R.id.btn_to_my_learn)
    Button btnToMyLearn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabar);
        ButterKnife.bind(this);
        setTitle(TAG);

        btnToMyLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyLearn.class);
                startActivity(intent);
            }
        });
    }
}
