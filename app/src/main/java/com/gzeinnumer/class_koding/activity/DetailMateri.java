package com.gzeinnumer.class_koding.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailMateri extends AppCompatActivity {
    public static String DATA = "data";
    ArrayList<DataMateriItem> list;
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

        list = new ArrayList<>();
        Intent intent = getIntent();

        current = intent.getParcelableExtra(DATA);

        Picasso.get().load(MyConstant.IMAGE_URL_MATERI + current.getMateriGambar()).placeholder(R.color.shimmerbag).resize(399, 399).into(gambarDetailItem);

        judulDetailItem.setText(current.getMateriNama());
        descDetailItem.setText(current.getMateriDeskripsi());

        videoViewFunction();
        beliDetailItem.setVisibility(View.VISIBLE);
        mulaiDetailItem.setVisibility(View.VISIBLE);
    }

    private void videoViewFunction() {
        videoDetailItem.getSettings().setJavaScriptEnabled(true);
        videoDetailItem.loadData("<iframe width=\"100%\" height=\"100%\" src=\""+current.getMateriVideo()+"\" frameborder=\"0\" allowfullscreen></iframe>","text/html","utf-8");
        videoDetailItem.setWebViewClient(new WebViewClient() {
            //TODO cek dulu
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(getApplicationContext(), "URL ERROR", Toast.LENGTH_SHORT).show();
            }
            //TODO start page
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }
            //TODO finish page
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
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
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoViewFunction();
    }
}
