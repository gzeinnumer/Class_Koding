package com.gzeinnumer.class_koding.slider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gzeinnumer.class_koding.R;


/**
 * Created by bagicode on 12/04/17.
 */

public class FragmentSlider extends Fragment {

    private static final String ARG_PARAM1 = "params";
    private static final String ARG_PARAM2 = "params2";

    public FragmentSlider() {
    }

    public static FragmentSlider newInstance(String params, String params2) {
        FragmentSlider fragment = new FragmentSlider();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, params);
        args.putString(ARG_PARAM2, params2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        assert getArguments() != null;
        String imageUrls = getArguments().getString(ARG_PARAM1);
        String titleEvent = getArguments().getString(ARG_PARAM2);
        View view = inflater.inflate(R.layout.fragment_slider_item, container, false);
        ImageView img = view.findViewById(R.id.img_event);
        TextView title = view.findViewById(R.id.title_event);
        Glide.with(getActivity())
                .load(imageUrls)
                .placeholder(R.drawable.shimmer)
                .into(img);

        title.setText(titleEvent);
        return view;
    }
}