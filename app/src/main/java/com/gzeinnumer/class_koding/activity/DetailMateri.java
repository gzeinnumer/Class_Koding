package com.gzeinnumer.class_koding.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyConstant;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailMateri extends MyFunction {
    public static String DATA = "data";
    ArrayList<DataMateriItem> list;
    MainInterface.I_DetailMateri i_detailMateri;

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

    DataMateriItem current;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_materi);
        ButterKnife.bind(this);

        i_detailMateri = new MainPresenter(context);

        list = new ArrayList<>();

        Intent intent = getIntent();
        current = intent.getParcelableExtra(DATA);

        initView();

        i_detailMateri.videoViewFunction(videoDetailItem,current);

    }

    private void initView() {
        Picasso.get().load(MyConstant.IMAGE_URL_MATERI + current.getMateriGambar()).placeholder(R.color.shimmerbag).resize(399, 399).into(gambarDetailItem);
        judulDetailItem.setText(current.getMateriNama());
        descDetailItem.setText(current.getMateriDeskripsi());
        beliDetailItem.setVisibility(View.VISIBLE);
        mulaiDetailItem.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.beli_detail_item, R.id.mulai_detail_item})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.beli_detail_item:
                break;
            case R.id.mulai_detail_item:
                break;
        }
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
        i_detailMateri.videoViewFunction(videoDetailItem,current);
    }
}
