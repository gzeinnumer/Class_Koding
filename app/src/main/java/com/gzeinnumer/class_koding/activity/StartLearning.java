package com.gzeinnumer.class_koding.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.ResponseSaveProgress;
import com.gzeinnumer.class_koding.model.ResponseUpdateProgress;
import com.gzeinnumer.class_koding.network.RetroServer;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartLearning extends MyFunction {

    public static final String DATA_MATERI_ID = "materi_id";
    private static final String TAG = "StartLearning";
    public static final String DATA = "modul_id";

    private MainInterface.I_StartLearning i_startLearning;
    private SessionManager sessionManager;

    @BindView(R.id.rv_content_by_id_modul)
    RecyclerView rvContentByIdModul;
    @BindView(R.id.btn_next_learning)
    Button btnNextStartLearning;

    private String modul_id;
    private String materi_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_learning);
        ButterKnife.bind(this);
        setTitle(TAG);
        sessionManager = new SessionManager(context);

        i_startLearning = new MainPresenter(context);

        modul_id = getIntent().getStringExtra(DATA);
        materi_id = getIntent().getStringExtra(DATA_MATERI_ID);
        i_startLearning.setViewForContent(rvContentByIdModul);
        i_startLearning.initDataContentList(modul_id);

        initSaveProgressToTable(sessionManager.getUserId(), materi_id, modul_id);

        btnNextStartLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initUpdateProgressToTable(sessionManager.getUserId(), materi_id, modul_id);
            }
        });
    }

    private void initUpdateProgressToTable(final String userId, final String materi_id, final String modul_id) {
        RetroServer.getInstance().updateProgress(userId, materi_id, modul_id).enqueue(new Callback<ResponseUpdateProgress>() {
            @Override
            public void onResponse(Call<ResponseUpdateProgress> call, Response<ResponseUpdateProgress> response) {
                Intent intent = new Intent(context, DaftarModul.class);
                intent.putExtra(DaftarModul.DATA, materi_id);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseUpdateProgress> call, Throwable t) {

            }
        });
    }

    private void initSaveProgressToTable(String userId, String materi_id, String modul_id) {
        RetroServer.getInstance().saveProgress(userId, materi_id, modul_id).enqueue(new Callback<ResponseSaveProgress>() {
            @Override
            public void onResponse(Call<ResponseSaveProgress> call, Response<ResponseSaveProgress> response) {


            }

            @Override
            public void onFailure(Call<ResponseSaveProgress> call, Throwable t) {
                shortToast(t.getMessage());
            }
        });
    }
}
