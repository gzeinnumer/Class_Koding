package com.gzeinnumer.class_koding.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.model.ResponseBuyViewed;
import com.gzeinnumer.class_koding.model.ResponseContentModul;
import com.gzeinnumer.class_koding.network.RetroServer;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMateri extends MyFunction {
    private static final String TAG = "DetailMateri";

    public static String DATA = "data";

    @BindView(R.id.gambar_detail_item)
    ImageView gambarDetailItem;
    @BindView(R.id.judul_detail_item)
    TextView judulDetailItem;
    @BindView(R.id.desc_detail_item)
    TextView descDetailItem;
    @BindView(R.id.beli_detail_item)
    Button beliDetailItem;
    @BindView(R.id.mulai_detail_item)
    Button mulaiDetailItem;
    @BindView(R.id.oleh_detail_item)
    TextView olehDetailItem;
    @BindView(R.id.plat_detail_item)
    TextView platDetailItem;
    @BindView(R.id.level_detail_item)
    TextView levelDetailItem;
    @BindView(R.id.bonus_detail_item)
    TextView bonusDetailItem;
    @BindView(R.id.video_detail_item)
    WebView videoDetailItem;
    @BindView(R.id.waktu_jam_detail_item)
    TextView waktuJamDetailItem;
    @BindView(R.id.deadline_detail_item)
    TextView deadlineDetailItem;

    DataMateriItem current;
    SessionManager sessionManager;
    ArrayList<DataMateriItem> list;
    MainInterface.I_DetailLearn i_detailLearn;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_materi);
        ButterKnife.bind(this);
        setTitle(TAG);

        sessionManager = new SessionManager(context);
        i_detailLearn = new MainPresenter(context);

        list = new ArrayList<>();

        Intent intent = getIntent();
        current = intent.getParcelableExtra(DATA);

        i_detailLearn.initViewDataDetail(current,
                gambarDetailItem,
                judulDetailItem,
                descDetailItem,
                beliDetailItem,
                mulaiDetailItem,
                olehDetailItem,
                platDetailItem,
                levelDetailItem,
                bonusDetailItem,
                waktuJamDetailItem,
                deadlineDetailItem
        );

        i_detailLearn.videoViewFunction(videoDetailItem, current);

        mulaiDetailItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DaftarModul.class);
                intent.putExtra(DaftarModul.DATA , current.getMateriId());
                context.startActivity(intent);
            }
        });

        beliDetailItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BuyActivity.class);
                intent.putExtra(BuyActivity.DATA , current);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        i_detailLearn.videoViewFunction(videoDetailItem, current);
        onBuyLearnViewed(current.getMateriId(), sessionManager.getUserId(), current.getMateriHarga());
    }

    private void onBuyLearnViewed(String materiId, String userId, String materiHarga) {
        RetroServer.getInstance().setOnBuyLearnViewed(materiId, userId, materiHarga).enqueue(new Callback<ResponseBuyViewed>() {
            @Override
            public void onResponse(Call<ResponseBuyViewed> call, Response<ResponseBuyViewed> response) {
                shortToast(response.body().getPesan());
            }

            @Override
            public void onFailure(Call<ResponseBuyViewed> call, Throwable t) {
                shortToast(t.getMessage());
            }
        });
    }

}
