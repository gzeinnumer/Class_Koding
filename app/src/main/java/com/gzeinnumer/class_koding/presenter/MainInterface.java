package com.gzeinnumer.class_koding.presenter;

import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.athkalia.emphasis.EmphasisTextView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.gzeinnumer.class_koding.activity.Login;
import com.gzeinnumer.class_koding.activity.Register;
import com.gzeinnumer.class_koding.adapter.AdapterFreeLearn;
import com.gzeinnumer.class_koding.adapter.AdapterLearn;
import com.gzeinnumer.class_koding.adapter.AdapterModulList;
import com.gzeinnumer.class_koding.adapter.AdapterNewLearn;
import com.gzeinnumer.class_koding.adapter.AdapterPayLearn;
import com.gzeinnumer.class_koding.model.DataDetailPembayaranItem;
import com.gzeinnumer.class_koding.model.DataListModulByModulIdItem;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.helper.sliderevent.SliderView;

import java.util.ArrayList;
import java.util.List;

public interface MainInterface {

    interface I_Login {
        void actionRegister(Class<Register> registerClass);
        void setHighLightSingUp(EmphasisTextView register);
        void actionLogin(TextInputEditText email, TextInputEditText pass);
    }

    interface I_Register {
        void actionRegister(TextInputEditText username, TextInputEditText email, TextInputEditText pass, TextInputEditText cPass, TextInputEditText asal);

        void setHighLightLogin(EmphasisTextView login);
    }

    interface I_LearnFragment {
        void setAdapterLearn(AdapterLearn adapter);
        void setRecyclerViewLearn(RecyclerView rvLearn);
        void setAdapterFirst(AdapterLearn adapter);
        void startShimmer();
        void searchFunction(EditText edSearch);
        ArrayList<DataMateriItem> getListMateri();
    }

    interface I_DetailLearn {
        void videoViewFunction(WebView videoDetailItem, DataMateriItem current);
        void initViewDataDetail(DataMateriItem current, ImageView gambarDetailItem, TextView judulDetailItem, TextView descDetailItem, Button beliDetailItem, Button mulaiDetailItem, TextView olehDetailItem, TextView platDetailItem, TextView levelDetailItem, TextView bonusDetailItem, TextView waktuJamDetailItem, TextView deadlineDetailItem);
    }

    interface I_HomeFragment {
        void setSliderIklanEvent(SliderView sliderIklan);
        void setShimmerForIklanEvent(ShimmerFrameLayout shimmerEventItem);
        void setFragmentContextForSliderPagerAdapterIklanEvent(FragmentManager fragmentManager);
        void setFragmentActivityForSliderIndikatorIklanEvent(FragmentActivity fragmentActivity);
        void setLinearForSliderIndikatorIklanEvent(LinearLayout mLinearLayout);
        void iniDataEvent();

        void setSliderIklanMateri(SliderView sliderIklanMateri);
        void setShimmerForIklanMateri(ShimmerFrameLayout shimmerMateriItem);
        void setFragmentContextForSliderPagerAdapterIklanMateri(FragmentManager fragmentManager);
        void setFragmentActivityForSliderIndikatorIklanMateri(FragmentActivity activity);
        void setLinearForSliderIndikatorIklanMateri(LinearLayout pagesContainerMateri);
        void iniDataMateri();

        void setRecyclerViewNewLearn(RecyclerView rvMateriTerbaru);
        void setAdapterNewLearn(AdapterNewLearn adapterNewLearn);
        void setAdapterFirstNewLearn(AdapterNewLearn adapter);
        void startShimmerNewLearn();
        ArrayList<DataMateriItem> getListNewLearn();

        void setRecyclerViewFreeLearn(RecyclerView rvFreeLearn);
        void setAdapterFreeLearn(AdapterFreeLearn adapterFreeLearn);
        void setAdapterFirstFreeLearn(AdapterFreeLearn adapterFreeLearn);
        void startShimmerFreeLearn();
        ArrayList<DataMateriItem> getListFreeLearn();

        void setRecyclerViewPayLearn(RecyclerView rvPayLearn);
        void setAdapterPayLearn(AdapterPayLearn adapterPayLearn);
        void setAdapterFirstPayLearn(AdapterPayLearn adapterPayLearn);
        void startShimmerPayLearn();
        ArrayList<DataMateriItem> getListPayLearn();
    }

    interface I_StartLearning{
        void setRecyclerViewContentByIdModul(RecyclerView rvContentByIdModul);
        void initDataContentList(String modul_id);
    }

    interface I_BuyActivity {
        void setViewForBuyActivity(DataMateriItem dataMateriItem, TextView jumModul, TextView xp, TextView waktu, TextView nama, TextView diskon, TextView level, TextView jumSiswa, TextView materiId, TextView mitraId, TextView jenisKelasId, TextView materiPlatform, TextView descripsi, TextView harga);
    }

    interface I_PayActivity{
        void setViewForPayActivity(TextView kodeBankReq, TextView noReq, TextView namaReq, TextView totalReq, TextView timeReq);
        void initDataForViewPayActivity(String materiId, String userId);

        List<DataDetailPembayaranItem> getListPembayaran();
    }

    interface I_DaftarModul{
        void setRecyclerViewListModulMateri(RecyclerView rvListModulMateri);
        void initDataModulList(String materiId);

        ArrayList<DataListModulByModulIdItem> getListDataListModul();
        AdapterModulList getAdapterModulList();
    }
}
