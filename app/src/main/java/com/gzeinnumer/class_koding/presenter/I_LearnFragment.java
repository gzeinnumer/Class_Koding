package com.gzeinnumer.class_koding.presenter;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.gzeinnumer.class_koding.adapter.AdapterLearn;
import com.gzeinnumer.class_koding.model.DataMateriItem;

import java.util.ArrayList;

public interface I_LearnFragment {

//    interface Main{
//        void zein();
//    }

    void setAdapterLearn(AdapterLearn adapter);
    void setRecyclerViewLearn(RecyclerView rvLearn);
    void setAdapterFirst(AdapterLearn adapter);
    void startShimmer();
    void searchFunction(EditText edSearch);
}
