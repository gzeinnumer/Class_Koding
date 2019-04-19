package com.gzeinnumer.class_koding.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.activity.DetailMateri;
import com.gzeinnumer.class_koding.adapter.AdapterFreeLearn;
import com.gzeinnumer.class_koding.adapter.AdapterNewLearn;
import com.gzeinnumer.class_koding.adapter.AdapterPayLearn;
import com.gzeinnumer.class_koding.helper.MyFunctionFragment;
import com.gzeinnumer.class_koding.helper.sliderevent.SliderView;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends MyFunctionFragment {

    private MainInterface.I_HomeFragment i_homeFragment;

    @BindView(R.id.slider_iklan_event)
    SliderView sliderIklanEvent;
    @BindView(R.id.layout_top_event)
    RelativeLayout layoutTopEvent;
    @BindView(R.id.pages_container_event)
    LinearLayout pagesContainerEvent;
    @BindView(R.id.shimmer_event_item)
    ShimmerFrameLayout shimmerEventItem;

    @BindView(R.id.slider_iklan_materi)
    SliderView sliderIklanMateri;
    @BindView(R.id.layout_top_materi)
    RelativeLayout layoutTopMateri;
    @BindView(R.id.pages_container_materi)
    LinearLayout pagesContainerMateri;
    @BindView(R.id.shimmer_materi_item)
    ShimmerFrameLayout shimmerMateriItem;

    @BindView(R.id.slider_iklan_komersial)
    SliderView sliderIklanKomersial;
    @BindView(R.id.layout_top_komersial)
    RelativeLayout layoutTopKomersial;
    @BindView(R.id.pages_container_komersial)
    LinearLayout pagesContainerKomersial;
    @BindView(R.id.shimmer_komersial_item)
    ShimmerFrameLayout shimmerKomersialItem;


    @BindView(R.id.rv_materi_terbaru)
    RecyclerView rvNewLearn;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.rv_free_learn)
    RecyclerView rvFreeLearn;
    @BindView(R.id.rv_pay_learn)
    RecyclerView rvPayLearn;

    private ArrayList<DataMateriItem> listNewLearn = new ArrayList<>();
    private ArrayList<DataMateriItem> listFreeLearn = new ArrayList<>();
    private ArrayList<DataMateriItem> listPayLearn = new ArrayList<>();

    private AdapterNewLearn adapterNewLearn;
    private AdapterFreeLearn adapterFreeLearn;
    private AdapterPayLearn adapterPayLearn;

    private View view;
    private Unbinder unbinder;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        shimmerEventItem.startShimmer();

        contextFragment = view.getContext();

        i_homeFragment = new MainPresenter(contextFragment);

        //SLIDER IKLAN EVENT
        i_homeFragment.setViewForIklanEventHomeFragment(sliderIklanEvent, shimmerEventItem, pagesContainerEvent);
        i_homeFragment.setContexForIklanEventHomeFragment(getFragmentManager(), getActivity());
        i_homeFragment.iniDataEvent();

        //SLIDER IKLAN MATERI
        i_homeFragment.setViewForIklanMateriHomeFragment(sliderIklanMateri, shimmerMateriItem, pagesContainerMateri);
        i_homeFragment.iniDataMateri();

        //SLIDER IKLAN KOMERSIAL
        i_homeFragment.setViewForIklanKomersialHomeFragment(sliderIklanKomersial, shimmerKomersialItem, pagesContainerKomersial);
        i_homeFragment.iniDataKomersial();

        //RECYCLERVIEW MATERI BARU
        adapterNewLearn = new AdapterNewLearn(contextFragment, listNewLearn, true);
        i_homeFragment.setViewNewLearn(rvNewLearn);
        i_homeFragment.setAdapterFirstNewLearn(adapterNewLearn);
        i_homeFragment.startShimmerNewLearn();

        //RECYCLERVIEW MATERI FREE
        adapterFreeLearn = new AdapterFreeLearn(contextFragment, listFreeLearn, true);
        i_homeFragment.setViewFreeLearn(rvFreeLearn);
        i_homeFragment.setAdapterFirstFreeLearn(adapterFreeLearn);
        i_homeFragment.startShimmerFreeLearn();

        //RECYCLERVIEW MATERI BERBAYAR
        adapterPayLearn = new AdapterPayLearn(contextFragment, listPayLearn, true);
        i_homeFragment.setViewPayLearn(rvPayLearn);
        i_homeFragment.setAdapterFirstPayLearn(adapterPayLearn);
        i_homeFragment.startShimmerPayLearn();


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //RECYCLERVIEW MATERI BARU
        i_homeFragment.setAdapterNewLearn(adapterNewLearn);

        //RECYCLERVIEW MATERI FREE
        i_homeFragment.setAdapterFreeLearn(adapterFreeLearn);

        //RECYCLERVIEW MATERI BERBAYAR
        i_homeFragment.setAdapterPayLearn(adapterPayLearn);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static void myOnClickAdapter(DataMateriItem mList) {
        Intent intent = new Intent(contextFragment, DetailMateri.class);
        intent.putExtra(DetailMateri.DATA, mList);
        contextFragment.startActivity(intent);
    }

}
