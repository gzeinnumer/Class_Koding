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
public class LearnFragment extends MyFunctionFragment {

    //I_LearnFragment.Main test;
    @BindView(R.id.rv_learn)
    RecyclerView rvLearn;
    @BindView(R.id.ed_search)
    EditText edSearch;

    Unbinder unbinder;
    AdapterLearn adapter;
    View view;
    ArrayList<DataMateriItem> list = new ArrayList<>();

    MainInterface.I_LearnFragment i_learnFragment;

    public LearnFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_learn, container, false);
        unbinder = ButterKnife.bind(this, view);

        contextFragment = view.getContext();

        i_learnFragment = new MainPresenter(contextFragment);

        adapter = new AdapterLearn(contextFragment, list, true);
        i_learnFragment.setRecyclerViewLearn(rvLearn);
        i_learnFragment.setAdapterFirst(adapter);
        i_learnFragment.startShimmer();
        i_learnFragment.searchFunction(edSearch);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        i_learnFragment.setAdapterLearn(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



}
