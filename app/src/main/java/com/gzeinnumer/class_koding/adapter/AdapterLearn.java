package com.gzeinnumer.class_koding.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.activity.DetailMateri;
import com.gzeinnumer.class_koding.fragment.LearnFragment;
import com.gzeinnumer.class_koding.helper.MyConstant;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterLearn extends RecyclerView.Adapter<AdapterLearn.MyHolder> implements Filterable {

    private Context context;
    private ArrayList<DataMateriItem> list;
    private ArrayList<DataMateriItem> listFilter;
    private View view;
    public boolean isShimmer;

    public AdapterLearn(Context context, ArrayList<DataMateriItem> list, boolean shimmer) {
        this.context = context;
        this.list = list;
        this.isShimmer = shimmer;
        listFilter = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.item_materi, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, @SuppressLint("RecyclerView") final int i) {
        if (isShimmer){
            myHolder.shimmerMateriItem.startShimmer();
        } else {

            myHolder.shimmerMateriItem.setShimmer(null);
            myHolder.shimmerMateriItem.stopShimmer();

            Picasso.get().load(MyConstant.IMAGE_URL_MATERI + list.get(i).getMateriGambar()).placeholder(R.color.shimmerbag).resize(399, 399).into(myHolder.imageMateriItem);

            myHolder.judulMateriItem.setBackground(null);
            myHolder.judulMateriItem.setText(list.get(i).getMateriNama());

            myHolder.ratingbarMateriItem.setBackground(null);
            myHolder.ratingbarMateriItem.setRating(Float.parseFloat(list.get(i).getMateriRating()));

            myHolder.hargaMateriItem.setBackground(null);
            myHolder.hargaMateriItem.setText(list.get(i).getMateriHarga());

            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LearnFragment.myOnClickAdapter(list.get(i));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        //return list.size();
        int SHIMMER_ITEM_COUNT = 9;
        return isShimmer? SHIMMER_ITEM_COUNT : list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_materi_item)
        ImageView imageMateriItem;
        @BindView(R.id.judul_materi_item)
        TextView judulMateriItem;
        @BindView(R.id.ratingbar_materi_item)
        RatingBar ratingbarMateriItem;
        @BindView(R.id.harga_materi_item)
        TextView hargaMateriItem;
        @BindView(R.id.shimmer_materi_item)
        ShimmerFrameLayout shimmerMateriItem;

        MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DataMateriItem> fildteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                Collections.sort(listFilter, new Comparator<DataMateriItem>() {
                    @Override
                    public int compare(DataMateriItem o1, DataMateriItem o2) {
                        return o1.getMateriNama().toLowerCase().compareTo(o2.getMateriNama().toLowerCase());
                    }
                });
                fildteredList.addAll(listFilter);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (DataMateriItem item : listFilter){
                    if( item.getMateriNama().toLowerCase().contains(filterPattern)){
                        fildteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = fildteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };




}
