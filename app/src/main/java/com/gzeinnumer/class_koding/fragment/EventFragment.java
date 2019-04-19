package com.gzeinnumer.class_koding.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.adapter.AdapterLearn;
import com.gzeinnumer.class_koding.helper.MyFunctionFragment;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends MyFunctionFragment {

    private MainInterface.I_EventFragment i_eventFragment;

    @BindView(R.id.ed_search_event)
    EditText edSearchEvent;
    @BindView(R.id.rv_event)
    RecyclerView rvEvent;
    Unbinder unbinder;

    private ArrayList<DataMateriItem> list = new ArrayList<>();
    private AdapterLearn adapter;

    private View view;

    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_event, container, false);
        unbinder = ButterKnife.bind(this, view);
        contextFragment = view.getContext();

        i_eventFragment = new MainPresenter(contextFragment);

        adapter = new AdapterLearn(contextFragment, list, true);
        i_eventFragment.setRecyclerViewEvent(rvEvent);
        i_eventFragment.setAdapterFirstEvent(adapter);
        i_eventFragment.startShimmerEventFragmentEvent();
        i_eventFragment.searchFunctionEvent(edSearchEvent);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        i_eventFragment.setAdapterEvent(adapter);

    }
}
