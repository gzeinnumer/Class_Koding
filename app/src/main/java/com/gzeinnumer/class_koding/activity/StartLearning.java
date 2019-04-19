package com.gzeinnumer.class_koding.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StartLearning extends MyFunction {

    private static final String TAG = "StartLearning";
    public static final String DATA = "modul_id";
    private MainInterface.I_StartLearning i_startLearning;

    @BindView(R.id.rv_content_by_id_modul)
    RecyclerView rvContentByIdModul;

    private String modul_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_learning);
        ButterKnife.bind(this);
        setTitle(TAG);

        i_startLearning = new MainPresenter(context);

        modul_id = getIntent().getStringExtra(DATA);
        i_startLearning.setRecyclerViewContentByIdModul(rvContentByIdModul);
        i_startLearning.initDataContentList(modul_id);
    }

}
