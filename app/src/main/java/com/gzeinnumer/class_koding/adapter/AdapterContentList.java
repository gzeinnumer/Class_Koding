package com.gzeinnumer.class_koding.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.gzeinnumer.class_koding.model.DataListContentByModulIdItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterContentList extends RecyclerView.Adapter<AdapterContentList.MyHolder> {

    private Context context;
    private ArrayList<DataListContentByModulIdItem> list;

    public AdapterContentList(Context context, ArrayList<DataListContentByModulIdItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_content_modul, viewGroup, false);
        return new MyHolder(view);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.hContent.setVisibility(View.GONE);
        myHolder.pContent.setVisibility(View.GONE);
        myHolder.videoContent.setVisibility(View.GONE);
        myHolder.imgContent.setVisibility(View.GONE);
        myHolder.aContent.setVisibility(View.GONE);
        myHolder.submitContent.setVisibility(View.GONE);
        myHolder.tableContent.setVisibility(View.GONE);

        String tipe = list.get(i).getModulTipe();
        if (tipe.equals("h")){
            myHolder.hContent.setText(list.get(i).getContentIsi());
            myHolder.hContent.setVisibility(View.VISIBLE);
        }else if(tipe.equals("p")){
            myHolder.pContent.setText(list.get(i).getContentIsi());
            myHolder.pContent.setVisibility(View.VISIBLE);
        } else if(tipe.equals("video")){
            myHolder.videoContent.getSettings().setJavaScriptEnabled(true);
            myHolder.videoContent.loadData("<iframe width=\"100%\" height=\"100%\" src=\""+list.get(i).getContentIsi()+"\" frameborder=\"0\" allowfullscreen></iframe>","text/html","utf-8");
            myHolder.videoContent.setWebViewClient(new WebViewClient() {
                //TODO cek dulu
                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    Toast.makeText(context, "URL ERROR", Toast.LENGTH_SHORT).show();
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
            myHolder.videoContent.setVisibility(View.VISIBLE);
        } else if(tipe.equals("img")){
            Picasso.get().load(MyConstant.IMAGE_URL_MODUL+list.get(i).getContentIsi()).placeholder(R.drawable.shimmer).into(myHolder.imgContent);
            myHolder.imgContent.setVisibility(View.VISIBLE);
        } else if(tipe.equals("a")){
            myHolder.aContent.setText(list.get(i).getContentIsi());
            myHolder.aContent.setVisibility(View.VISIBLE);
        } else if(tipe.equals("submit")){
            myHolder.submitContent.setVisibility(View.VISIBLE);
            myHolder.submitContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Hay Zein", Toast.LENGTH_SHORT).show();
                }
            });
            myHolder.submitContent.setVisibility(View.VISIBLE);
        } else if(tipe.equals("table")){
            myHolder.tableContent.setWebViewClient(new WebViewClient());
            myHolder.tableContent.getSettings().setJavaScriptEnabled(true);
            myHolder.tableContent.loadDataWithBaseURL("",list.get(i).getContentIsi(),"text/html", "UTF-8","");
            myHolder.tableContent.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.h_content)
        TextView hContent;
        @BindView(R.id.p_content)
        TextView pContent;
        @BindView(R.id.video_content)
        WebView videoContent;
        @BindView(R.id.table_content)
        WebView tableContent;
        @BindView(R.id.submit_content)
        Button submitContent;
        @BindView(R.id.a_content)
        TextView aContent;
        @BindView(R.id.img_content)
        ImageView imgContent;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
