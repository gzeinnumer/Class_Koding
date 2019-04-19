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

public class HomeFragment extends MyFunctionFragment{

    @BindView(R.id.shimmer_event_item)
    ShimmerFrameLayout shimmerEventItem;
    @BindView(R.id.layout_top)
    RelativeLayout layoutTop;
    @BindView(R.id.rv_materi_terbaru)
    RecyclerView rvNewLearn;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.rv_free_learn)
    RecyclerView rvFreeLearn;
    @BindView(R.id.rv_pay_learn)
    RecyclerView rvPayLearn;
    @BindView(R.id.slider_iklan_event)
    SliderView sliderIklanEvent;

    @BindView(R.id.pages_container_event)
    LinearLayout pagesContainerEvent;

    MainInterface.I_HomeFragment i_homeFragment;
    View view;
    Unbinder unbinder;

    AdapterNewLearn adapterNewLearn;
    AdapterFreeLearn adapterFreeLearn;
    AdapterPayLearn adapterPayLearn;
    ArrayList<DataMateriItem> listNewLearn = new ArrayList<>();
    ArrayList<DataMateriItem> listFreeLearn = new ArrayList<>();
    ArrayList<DataMateriItem> listPayLearn = new ArrayList<>();

    public static void myOnClickAdapter(DataMateriItem mList){
                    Intent intent = new Intent(contextFragment, DetailMateri.class);
                    intent.putExtra(DetailMateri.DATA, mList);
                    contextFragment.startActivity(intent);
    }

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
        i_homeFragment.setSliderIklanEvent(sliderIklanEvent);
        i_homeFragment.setShimmerForIklanEvent(shimmerEventItem);
        i_homeFragment.setFragmentContextForSliderPagerAdapterIklanEvent(getFragmentManager());
        i_homeFragment.setFragmentActivityForSliderIndikatorIklanEvent(getActivity());
        i_homeFragment.setLinearForSliderIndikatorIklanEvent(pagesContainerEvent);
        i_homeFragment.iniDataEvent();

        //SLIDER IKLAN MATERI

        //RECYCLERVIEW MATERI BARU
        adapterNewLearn = new AdapterNewLearn(contextFragment, listNewLearn, true);
        i_homeFragment.setRecyclerViewNewLearn(rvNewLearn);
        i_homeFragment.setAdapterFirstNewLearn(adapterNewLearn);
        i_homeFragment.startShimmerNewLearn();

        //RECYCLERVIEW MATERI FREE
        adapterFreeLearn = new AdapterFreeLearn(contextFragment, listFreeLearn, true);
        i_homeFragment.setRecyclerViewFreeLearn(rvFreeLearn);
        i_homeFragment.setAdapterFirstFreeLearn(adapterFreeLearn);
        i_homeFragment.startShimmerFreeLearn();

        //RECYCLERVIEW MATERI BERBAYAR
        adapterPayLearn = new AdapterPayLearn(contextFragment, listPayLearn, true);
        i_homeFragment.setRecyclerViewPayLearn(rvPayLearn);
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
    public void onResume() {
        super.onResume();
        listNewLearn = i_homeFragment.getListNewLearn();
        listFreeLearn = i_homeFragment.getListFreeLearn();
        listPayLearn = i_homeFragment.getListPayLearn();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
