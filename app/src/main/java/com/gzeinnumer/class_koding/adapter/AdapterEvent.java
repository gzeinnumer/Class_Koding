package com.gzeinnumer.class_koding.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.gzeinnumer.class_koding.fragment.EventFragment;
import com.gzeinnumer.class_koding.fragment.HomeFragment;
import com.gzeinnumer.class_koding.fragment.LearnFragment;
import com.gzeinnumer.class_koding.helper.MyConstant;
import com.gzeinnumer.class_koding.model.DataEventItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.MyHolder> implements Filterable {

    private Context context;
    private ArrayList<DataEventItem> list;
    private ArrayList<DataEventItem> listFilter;
    private View view;
    public boolean isShimmer;

    public AdapterEvent(Context context, ArrayList<DataEventItem> list, boolean shimmer) {
        this.context = context;
        this.list = list;
        this.isShimmer = shimmer;
        listFilter = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.item_event, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, @SuppressLint("RecyclerView") final int i) {
        if (isShimmer) {
            myHolder.shimmerMateriItem.startShimmer();
        } else {

            myHolder.shimmerMateriItem.setShimmer(null);
            myHolder.shimmerMateriItem.stopShimmer();

            Picasso.get().load(MyConstant.IMAGE_URL_MATERI + list.get(i).getEventGambar()).placeholder(R.color.shimmerbag).resize(399, 399).into(myHolder.imageMateriItem);

            myHolder.judulMateriItem.setBackground(null);
            myHolder.judulMateriItem.setText(list.get(i).getEventNama());

            myHolder.ratingbarMateriItem.setBackground(null);

            myHolder.hargaMateriItem.setBackground(null);

            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomeFragment.myOnClickAdapterEvent(list.get(i));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        //return list.size();
        int SHIMMER_ITEM_COUNT = 9;
        return isShimmer ? SHIMMER_ITEM_COUNT : list.size();
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
            List<DataEventItem> fildteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                Collections.sort(listFilter, new Comparator<DataEventItem>() {
                    @Override
                    public int compare(DataEventItem o1, DataEventItem o2) {
                        return o1.getEventNama().toLowerCase().compareTo(o2.getEventNama().toLowerCase());
                    }
                });
                fildteredList.addAll(listFilter);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (DataEventItem item : listFilter) {
                    if (item.getEventNama().toLowerCase().contains(filterPattern)) {
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
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}
