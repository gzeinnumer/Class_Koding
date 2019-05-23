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
import com.gzeinnumer.class_koding.activity.Register;
import com.gzeinnumer.class_koding.adapter.AdapterEvent;
import com.gzeinnumer.class_koding.adapter.AdapterEventHome;
import com.gzeinnumer.class_koding.adapter.AdapterFreeLearn;
import com.gzeinnumer.class_koding.adapter.AdapterLearn;
import com.gzeinnumer.class_koding.adapter.AdapterNewLearn;
import com.gzeinnumer.class_koding.adapter.AdapterPayLearn;
import com.gzeinnumer.class_koding.model.DataDetailPembayaranItem;
import com.gzeinnumer.class_koding.model.DataEventItem;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.helper.sliderevent.SliderView;
//done
import java.util.List;

public interface MainInterface {

    interface I_Login {
        void actionRegister(Class<Register> registerClass);

        void setHighLightSingUp(EmphasisTextView register);

        void actionLogin(TextInputEditText email,
                         TextInputEditText pass);
    }

    interface I_Register {
        void actionRegister(TextInputEditText username,
                            TextInputEditText email,
                            TextInputEditText pass,
                            TextInputEditText cPass,
                            TextInputEditText asal);

        void setHighLightLogin(EmphasisTextView login);
    }

    interface I_LearnFragment {
        void setAdapterLearn(AdapterLearn adapter);

        void setRecyclerViewLearn(RecyclerView rvLearn);

        void setAdapterFirst(AdapterLearn adapter);

        void startShimmerLearnFragment();

        void searchFunction(EditText edSearch);
    }

    interface I_EventFragment {
        void setAdapterEvent(AdapterEvent adapter);

        void setRecyclerViewEvent(RecyclerView rvEvent);

        void setAdapterFirstEvent(AdapterEvent adapter);

        void startShimmerEventFragmentEvent();

        void searchFunctionEvent(EditText edSearchEvent);
    }

    interface I_DetailLearn {
        void videoViewFunction(WebView videoDetailItem,
                               DataMateriItem current);

        void setViewDataDetail(DataMateriItem current,
                               ImageView gambarDetailItem,
                               TextView judulDetailItem,
                               TextView descDetailItem,
                               Button beliDetailItem,
                               Button mulaiDetailItem,
                               TextView olehDetailItem,
                               TextView platDetailItem,
                               TextView levelDetailItem,
                               TextView bonusDetailItem,
                               TextView waktuJamDetailItem,
                               TextView deadlineDetailItem, Button sabarDetailItem);

        void onBuyLearnViewed(String materiId,
                              String userId,
                              String materiHarga);
    }

    interface I_HomeFragment {
        void setViewForIklanEventHomeFragment(SliderView sliderIklanEvent,
                                              ShimmerFrameLayout shimmerEventItem,
                                              LinearLayout pagesContainerEvent);

        void setContexForIklanEventHomeFragment(FragmentManager fragmentManager,
                                                FragmentActivity activity);

        void iniDataEventHome();

        void setViewForIklanMateriHomeFragment(SliderView sliderIklanMateri,
                                               ShimmerFrameLayout shimmerMateriItem,
                                               LinearLayout pagesContainerMateri);

        //void setContexForIklanMateriHomeFragment(FragmentManager fragmentManager, FragmentActivity activity);
        void iniDataMateri();

        void setViewForIklanKomersialHomeFragment(SliderView sliderIklanKomersial,
                                                  ShimmerFrameLayout shimmerKomersialItem,
                                                  LinearLayout pagesContainerKomersial);

        //void setContexForIklanKomersialHomeFragment(FragmentManager fragmentManager, FragmentActivity activity);
        void iniDataKomersial();

        void setViewNewLearn(RecyclerView rvMateriTerbaru);

        void setAdapterNewLearn(AdapterNewLearn adapterNewLearn);

        void setAdapterFirstNewLearn(AdapterNewLearn adapter);

        void startShimmerNewLearn();

        void setViewFreeLearn(RecyclerView rvFreeLearn);

        void setAdapterFreeLearn(AdapterFreeLearn adapterFreeLearn);

        void setAdapterFirstFreeLearn(AdapterFreeLearn adapterFreeLearn);

        void startShimmerFreeLearn();

        void setViewPayLearn(RecyclerView rvPayLearn);

        void setAdapterPayLearn(AdapterPayLearn adapterPayLearn);

        void setAdapterFirstPayLearn(AdapterPayLearn adapterPayLearn);

        void startShimmerPayLearn();

        void setViewEventForHome(RecyclerView rvEventHome);

        void setAdapterEventHome(AdapterEventHome adapterEventHome);

        void setAdapterFirstEventHome(AdapterEventHome adapterEvent);

        void startShimmerEventHome();

    }

    interface I_StartLearning {
        void setViewForContent(RecyclerView rvContentByIdModul);

        void initDataContentList(String modul_id);
    }

    interface I_BuyActivity {
        void setViewForBuyActivity(DataMateriItem dataMateriItem,
                                   TextView jumModul,
                                   TextView xp,
                                   TextView waktu,
                                   TextView nama,
                                   TextView diskon,
                                   TextView level,
                                   TextView jumSiswa,
                                   TextView materiId,
                                   TextView mitraId,
                                   TextView jenisKelasId,
                                   TextView materiPlatform,
                                   TextView descripsi,
                                   TextView harga);
    }

    interface I_PayActivity {
        void setViewForPayActivity(TextView kodeBankReq,
                                   TextView noReq,
                                   TextView namaReq,
                                   TextView totalReq,
                                   TextView timeReq);

        void initDataForViewPayActivity(String materiId, String userId);

        List<DataDetailPembayaranItem> getListPembayaran();
    }

    interface I_DaftarModul {
        void setViewForDaftarModul(RecyclerView rvListModulMateri);

        void regisToTableBelajar(String userId, String materiId);

    }

    interface I_ProfilFragment {
        void initDataUserForProfile(String userId);

        void setViewForProfilFragment(ImageView imageUser,
                                      TextView emailUser,
                                      TextView idUser,
                                      TextView asalUser,
                                      TextView xpUser,
                                      TextView dateUser,
                                      TextView namaUser,
                                      RecyclerView rvMyLearnProgressUser,
                                      RecyclerView rvMyLearnTersediaForProfil);

        void initDataMyLearnList(String userId);

        void initDataMyLearnListProgress(String userId);
    }

    interface I_MyLearn {
        void initDataMyLearn(String userId);

        void setViewForMyLearn(RecyclerView rvMyLearn);
    }


    interface I_DetailEvent {

        void setViewForDetailEvent(ImageView eventGambar,
                                   WebView eventVideo,
                                   TextView eventNama,
                                   TextView eventTglMulai,
                                   TextView eventMitraId,
                                   TextView eventTglSelesai,
                                   TextView eventXp,
                                   TextView eventAlamat,
                                   TextView eventDeskripsi,
                                   TextView eventKuota,
                                   TextView eventJenis,
                                   TextView eventId,
                                   ImageView eventTiket,
                                   TextView eventKota);

        void setValueForDetailEvent(DataEventItem dataEventItem);
    }

    interface I_JoinEvent {


        void regisToTableJoinEvent(String userId, String event_id);
    }
}