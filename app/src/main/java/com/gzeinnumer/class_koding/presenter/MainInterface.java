package com.gzeinnumer.class_koding.presenter;

import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.athkalia.emphasis.EmphasisTextView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.gzeinnumer.class_koding.activity.Login;
import com.gzeinnumer.class_koding.activity.Register;
import com.gzeinnumer.class_koding.adapter.AdapterFreeLearn;
import com.gzeinnumer.class_koding.adapter.AdapterLearn;
import com.gzeinnumer.class_koding.adapter.AdapterNewLearn;
import com.gzeinnumer.class_koding.adapter.AdapterPayLearn;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.slider.SliderView;

public interface MainInterface {

    interface I_Login {
        void actionRegister(Class<Register> registerClass);
        void setHighLightSingUp(EmphasisTextView register);
        void actionLogin(TextInputEditText email, TextInputEditText pass);
    }

    interface I_Register {
        void actionRegister(TextInputEditText username, TextInputEditText email, TextInputEditText pass, TextInputEditText cPass, TextInputEditText asal);
        void actionLogin(Class<Login> loginClass);
        void setHighLightLogin(EmphasisTextView login);
    }

    interface I_LearnFragment {
        void setAdapterLearn(AdapterLearn adapter);
        void setRecyclerViewLearn(RecyclerView rvLearn);
        void setAdapterFirst(AdapterLearn adapter);
        void startShimmer();
        void searchFunction(EditText edSearch);
    }

    interface I_DetailLearn {
        void videoViewFunction(WebView videoDetailItem, DataMateriItem current);
    }

    interface I_HomeFragment {
        void setSliderIklan(SliderView sliderIklan);
        void setShimmerForIklan(ShimmerFrameLayout shimmerEventItem);
        void setFragmentContextForSliderPagerAdapter(FragmentManager fragmentManager);
        void setFragmentActivityForSliderIndikator(FragmentActivity fragmentActivity);
        void setLinearForSliderIndikator(LinearLayout mLinearLayout);
        void iniDataEvent();

        void setRecyclerViewNewLearn(RecyclerView rvMateriTerbaru);
        void setAdapterNewLearn(AdapterNewLearn adapterNewLearn);
        void setAdapterFirstNewLearn(AdapterNewLearn adapter);
        void startShimmerNewLearn();

        void setRecyclerViewFreeLearn(RecyclerView rvFreeLearn);
        void setAdapterFreeLearn(AdapterFreeLearn adapterFreeLearn);
        void setAdapterFirstFreeLearn(AdapterFreeLearn adapterFreeLearn);
        void startShimmerFreeLearn();

        void setRecyclerViewPayLearn(RecyclerView rvPayLearn);
        void setAdapterPayLearn(AdapterPayLearn adapterPayLearn);
        void setAdapterFirstPayLearn(AdapterPayLearn adapterPayLearn);
        void startShimmerPayLearn();

    }
}
