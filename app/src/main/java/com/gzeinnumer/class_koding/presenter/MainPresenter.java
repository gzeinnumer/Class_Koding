package com.gzeinnumer.class_koding.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.athkalia.emphasis.EmphasisTextView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.activity.Login;
import com.gzeinnumer.class_koding.activity.Parent;
import com.gzeinnumer.class_koding.activity.Register;
import com.gzeinnumer.class_koding.adapter.AdapterContentList;
import com.gzeinnumer.class_koding.adapter.AdapterEvent;
import com.gzeinnumer.class_koding.adapter.AdapterEventHome;
import com.gzeinnumer.class_koding.adapter.AdapterFreeLearn;
import com.gzeinnumer.class_koding.adapter.AdapterLearn;
import com.gzeinnumer.class_koding.adapter.AdapterModulList;
import com.gzeinnumer.class_koding.adapter.AdapterMyLearn;
import com.gzeinnumer.class_koding.adapter.AdapterMyLearnListForProfil;
import com.gzeinnumer.class_koding.adapter.AdapterMyLearnProgressForProfil;
import com.gzeinnumer.class_koding.adapter.AdapterNewLearn;
import com.gzeinnumer.class_koding.adapter.AdapterPayLearn;
import com.gzeinnumer.class_koding.helper.MyConstant;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.DataDetailPembayaranItem;
import com.gzeinnumer.class_koding.model.DataDetailPembayaranUserItem;
import com.gzeinnumer.class_koding.model.DataEventItem;
import com.gzeinnumer.class_koding.model.DataListContentByModulIdItem;
import com.gzeinnumer.class_koding.model.DataListModulByModulIdItem;
import com.gzeinnumer.class_koding.model.DataListModulByModulIdStatusItem;
import com.gzeinnumer.class_koding.model.DataListMyLearnItem;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.model.DataMyLearnProgressItem;
import com.gzeinnumer.class_koding.model.DataUserItem;
import com.gzeinnumer.class_koding.model.ResponseBuyViewed;
import com.gzeinnumer.class_koding.model.ResponseContentModul;
import com.gzeinnumer.class_koding.model.ResponseDataUser;
import com.gzeinnumer.class_koding.model.ResponseDetailPembayaranUser;
import com.gzeinnumer.class_koding.model.ResponseEvent;
import com.gzeinnumer.class_koding.model.ResponseGetPembayaran;
import com.gzeinnumer.class_koding.model.ResponseJoinEvent;
import com.gzeinnumer.class_koding.model.ResponseListModul;
import com.gzeinnumer.class_koding.model.ResponseListMyLearn;
import com.gzeinnumer.class_koding.model.ResponseLogin;
import com.gzeinnumer.class_koding.model.ResponseMateri;
import com.gzeinnumer.class_koding.model.ResponseMyLearnProgress;
import com.gzeinnumer.class_koding.model.ResponseRegisToTableBelajar;
import com.gzeinnumer.class_koding.model.ResponseRegister;
import com.gzeinnumer.class_koding.network.RetroServer;
import com.gzeinnumer.class_koding.helper.sliderevent.FragmentSlider;
import com.gzeinnumer.class_koding.helper.sliderevent.SliderIndicator;
import com.gzeinnumer.class_koding.helper.sliderevent.SliderPagerAdapter;
import com.gzeinnumer.class_koding.helper.sliderevent.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//awal
public class MainPresenter implements
        MainInterface.I_Register,
        MainInterface.I_LearnFragment,
        MainInterface.I_Login,
        MainInterface.I_DetailLearn,
        MainInterface.I_HomeFragment,
        MainInterface.I_StartLearning,
        MainInterface.I_BuyActivity,
        MainInterface.I_PayActivity,
        MainInterface.I_DaftarModul,
        MainInterface.I_ProfilFragment,
        MainInterface.I_MyLearn,
        MainInterface.I_EventFragment,
        MainInterface.I_DetailEvent,
        MainInterface.I_JoinEvent {

    private Context context;

    private SessionManager sessionManager;

    public MainPresenter(Context context) {
        this.context = context;
        sessionManager = new SessionManager(context);
    }

    /**
     * global function
     */
    private void shortToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    private void intent(Class destination) {
        context.startActivity(new Intent(context, destination));
    }
    /**
     * end global function
     */

    ////////////////////////////////////////////////////////////////////////////////////////////////I_LOGIN
    @Override
    public void actionRegister(Class<Register> registerClass) {
        context.startActivity(new Intent(context, registerClass));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setHighLightSingUp(EmphasisTextView register) {
        register.setText("Havent Account? Sign Up!!");
        register.setTextToHighlight("Sign Up!!");
        register.setTextHighlightColor(R.color.colorAccent);
        register.setCaseInsensitive(true);
        register.highlight();
    }

    @Override
    public void actionLogin(TextInputEditText email,
                            TextInputEditText pass) {
        String temp1 = email.getText().toString().trim();
        String temp2 = pass.getText().toString().trim();
        isValidateLogin(email, pass, temp1, temp2);
    }

    private void isValidateLogin(TextInputEditText email,
                                 TextInputEditText pass,
                                 String temp1,
                                 String temp2) {
        if (TextUtils.isEmpty(temp1)) {
            email.setError(context.getString(R.string.isEmpty));
            email.requestFocus();
        } else if (TextUtils.isEmpty(temp2)) {
            pass.setError(context.getString(R.string.isEmpty));
            pass.requestFocus();
        } else {
            checkLogin(temp1, temp2);
        }
    }

    private void checkLogin(String temp1,
                            String temp2) {
        RetroServer.getInstance().login(temp1, temp2).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.body().isSukses()) {
                    SessionManager mSession = new SessionManager(context);

                    mSession.setUserEmail(response.body().getDataLogin().get(0).getUserEmail());
                    mSession.setUserId(response.body().getDataLogin().get(0).getUserId());
                    mSession.setUserImage(response.body().getDataLogin().get(0).getUserImage());
                    mSession.setUserAsal(response.body().getDataLogin().get(0).getUserAsal());
                    mSession.setUserName(response.body().getDataLogin().get(0).getUserName());
                    mSession.setUserXP(response.body().getDataLogin().get(0).getUserXp());
                    mSession.setUserDate(response.body().getDataLogin().get(0).getUserDate());

                    intent(Parent.class);
                } else {
                    shortToast("Gagal Login");
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                shortToast("Gagal Login, Ccho Koneksi");
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_Register
    @Override
    public void actionRegister(TextInputEditText username,
                               TextInputEditText email,
                               TextInputEditText pass,
                               TextInputEditText cPass,
                               TextInputEditText asal) {
        isValidateRegister(username, email, pass, cPass, asal);

    }

    private void isValidateRegister(TextInputEditText username,
                                    TextInputEditText email,
                                    TextInputEditText pass,
                                    TextInputEditText cPass,
                                    TextInputEditText asal) {
        String temp1 = username.getText().toString().trim();
        String temp2 = email.getText().toString().trim();
        String temp3 = pass.getText().toString().trim();
        String temp3_1 = cPass.getText().toString().trim();
        String temp4 = asal.getText().toString().trim();
        if (TextUtils.isEmpty(temp1)) {
            username.setError(context.getString(R.string.isEmpty));
            username.requestFocus();
        } else if (TextUtils.isEmpty(temp2)) {
            email.setError(context.getString(R.string.isEmpty));
            email.requestFocus();
        } else if (TextUtils.isEmpty(temp3)) {
            pass.setError(context.getString(R.string.isEmpty));
            pass.requestFocus();
        } else if (!temp3.equals(temp3_1)) {
            cPass.setError("Not Match");
            cPass.requestFocus();
        } else if (TextUtils.isEmpty(temp4)) {
            asal.setError(context.getString(R.string.isEmpty));
            asal.requestFocus();
        } else {
            registerUser(temp1, temp2, temp3, temp4);
        }
    }

    private void registerUser(String temp1,
                              String temp2,
                              String temp3,
                              String temp4) {
        RetroServer.getInstance().register(temp1, temp2, temp3, temp4).enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if (response.body().isSukses()) {
                    shortToast("SuksesDaftar");
                    intent(Login.class);
                } else {
                    shortToast("Failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                shortToast("Chck Your Connection");
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setHighLightLogin(EmphasisTextView login) {
        login.setText("Have Account? Login!!");
        login.setTextToHighlight("Login!!");
        login.setTextHighlightColor(R.color.colorAccent);
        login.setCaseInsensitive(true);
        login.highlight();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_LEARNFRAGMENT
    private AdapterLearn adapterLearn;
    private RecyclerView rvLearn;

    @Override
    public void setAdapterLearn(AdapterLearn adapter) {
        this.adapterLearn = adapter;
    }

    @Override
    public void setRecyclerViewLearn(RecyclerView rvLearn) {
        this.rvLearn = rvLearn;
    }

    @Override
    public void setAdapterFirst(AdapterLearn adapter) {
        rvLearn.setAdapter(adapter);
        rvLearn.setHasFixedSize(true);
        rvLearn.setLayoutManager(new GridLayoutManager(context, 3));
    }

    @Override
    public void startShimmerLearnFragment() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initDataLearn();
                adapterLearn.isShimmer = false;
                adapterLearn.notifyDataSetChanged();
            }
        }, 4999);
    }

    private void initDataLearn() {
        RetroServer.getInstance().getAllMateri().enqueue(new Callback<ResponseMateri>() {
            @Override
            public void onResponse(Call<ResponseMateri> call, Response<ResponseMateri> response) {
                List<DataMateriItem> listData = response.body().getDataMateri();
                ArrayList<DataMateriItem> listMateri = new ArrayList<>();
                listMateri = new ArrayList<>();

                if (response.body().isSukses()) {
                    for (int i = 0; i < listData.size(); i++) {
                        listMateri.add(new DataMateriItem(listData.get(i).getMateriJmlModul(),
                                listData.get(i).getMateriXp(),
                                listData.get(i).getMateriWaktu(),
                                listData.get(i).getMateriNama(),
                                listData.get(i).getMateriDiskon(),
                                listData.get(i).getMateriLevel(),
                                listData.get(i).getMateriJumSiswa(),
                                listData.get(i).getMateriGambar(),
                                listData.get(i).getMateriId(),
                                listData.get(i).getMitraId(),
                                listData.get(i).getMateriVideo(),
                                listData.get(i).getMateriRating(),
                                listData.get(i).getJenisKelasId(),
                                listData.get(i).getMateriDeadline(),
                                listData.get(i).getMateriPlatform(),
                                listData.get(i).getMateriDeskripsi(),
                                listData.get(i).getMateriHarga(),
                                listData.get(i).getMateriTgl()));
                    }
                }
                if (listMateri.size() > 0) {
                    initToRecyclerLearn(listMateri);
                } else {
                    shortToast("data tidak ada!!");
                }
            }

            @Override
            public void onFailure(Call<ResponseMateri> call, Throwable t) {
                shortToast("response tidak ada!!");
            }
        });
    }

    private void initToRecyclerLearn(ArrayList<DataMateriItem> listMateri) {
        adapterLearn = new AdapterLearn(context, listMateri, false);
        rvLearn.setAdapter(adapterLearn);
        rvLearn.setHasFixedSize(true);
        rvLearn.setLayoutManager(new GridLayoutManager(context, 3));
    }

    @Override
    public void searchFunction(EditText edSearch) {
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapterLearn.getFilter().filter(String.valueOf(s));
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_EVENTFRAGMENT
    private AdapterEvent adapterEvent;
    private RecyclerView rvEvent;

    @Override
    public void setAdapterEvent(AdapterEvent adapter) {
        this.adapterEvent = adapter;
    }

    @Override
    public void setRecyclerViewEvent(RecyclerView rvEvent) {
        this.rvEvent = rvEvent;

    }

    @Override
    public void setAdapterFirstEvent(AdapterEvent adapter) {
        rvEvent.setAdapter(adapter);
        rvEvent.setHasFixedSize(true);
        rvEvent.setLayoutManager(new GridLayoutManager(context, 3));
    }

    @Override
    public void startShimmerEventFragmentEvent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initDataEventFragment();
                adapterEvent.isShimmer = false;
                adapterEvent.notifyDataSetChanged();
            }
        }, 4999);
    }


    private void initDataEventFragment() {
        RetroServer.getInstance().getAllEvent().enqueue(new Callback<ResponseEvent>() {
            @Override
            public void onResponse(Call<ResponseEvent> call, Response<ResponseEvent> response) {
                List<DataEventItem> list = response.body().getDataEvent();
                ArrayList<DataEventItem> listDataEventFragment = new ArrayList();
                if (response.body().isSukses()) {
                    for (int i = 0; i < list.size(); i++) {
                        listDataEventFragment.add(new DataEventItem(
                                list.get(i).getEventGambar(),
                                list.get(i).getEventVideo(),
                                list.get(i).getEventNama(),
                                list.get(i).getEventTglMulai(),
                                list.get(i).getMitraId(),
                                list.get(i).getEventTglSelesai(),
                                list.get(i).getEventXp(),
                                list.get(i).getEventAlamat(),
                                list.get(i).getEventDeskripsi(),
                                list.get(i).getEventKuota(),
                                list.get(i).getEventJenis(),
                                list.get(i).getEventId(),
                                list.get(i).getEventTiket(),
                                list.get(i).getEventKota()));
                    }
                    initListDataEventToEventFragment(listDataEventFragment);
                }
            }

            @Override
            public void onFailure(Call<ResponseEvent> call, Throwable t) {

            }
        });
    }

    private void initListDataEventToEventFragment(ArrayList<DataEventItem> listDataEventFragment) {
        AdapterEvent adapter = new AdapterEvent(context, listDataEventFragment, false);
        rvEvent.setAdapter(adapter);
        rvEvent.setHasFixedSize(true);
        rvEvent.setLayoutManager(new GridLayoutManager(context, 3));
    }

    @Override
    public void searchFunctionEvent(EditText edSearchEvent) {
        edSearchEvent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapterEvent.getFilter().filter(String.valueOf(s));
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_DETAILMATERI
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void videoViewFunction(WebView videoDetailItem,
                                  DataMateriItem current) {
        videoDetailItem.getSettings().setJavaScriptEnabled(true);
        videoDetailItem.loadData("<iframe width=\"100%\" height=\"100%\" src=\"" + current.getMateriVideo() + "\" frameborder=\"0\" allowfullscreen></iframe>", "text/html", "utf-8");
        videoDetailItem.setWebViewClient(new WebViewClient() {
            //TODO cek dulu
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                shortToast("URL ERROR");
            }

            //TODO start page
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }

            //TODO finish page
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setViewDataDetail(DataMateriItem current,
                                  ImageView gambarDetailItem,
                                  TextView judulDetailItem,
                                  TextView descDetailItem,
                                  final Button beliDetailItem,
                                  final Button mulaiDetailItem,
                                  TextView olehDetailItem,
                                  TextView platDetailItem,
                                  TextView levelDetailItem,
                                  TextView bonusDetailItem,
                                  TextView waktuJamDetailItem,
                                  TextView deadlineDetailItem, Button sabarDetailItem) {
        Picasso.get().load(MyConstant.IMAGE_URL_MATERI + current.getMateriGambar())
                .placeholder(R.color.shimmerbag).resize(399, 399).into(gambarDetailItem);
        judulDetailItem.setText(current.getMateriNama());
        descDetailItem.setText(current.getMateriDeskripsi());
        olehDetailItem.setText(current.getMitraId());
        platDetailItem.setText(current.getMateriPlatform());
        levelDetailItem.setText(current.getMateriLevel());
        bonusDetailItem.setText(current.getMateriXp() + " Xp");
        waktuJamDetailItem.setText("Durasi total video " + current.getMateriWaktu() + " Jam");
        deadlineDetailItem.setText("Deadline pengerjaan Modul " + current.getMateriDeadline() + " Hari");

        initVisibilitiButtonForDetail(current, mulaiDetailItem, beliDetailItem, sabarDetailItem);

    }

    @Override
    public void onBuyLearnViewed(String materiId,
                                 String userId,
                                 String materiHarga) {
        RetroServer.getInstance().setOnBuyLearnViewed(materiId, userId, materiHarga).enqueue(new Callback<ResponseBuyViewed>() {
            @Override
            public void onResponse(Call<ResponseBuyViewed> call, Response<ResponseBuyViewed> response) {
                shortToast(response.body().getPesan());
            }

            @Override
            public void onFailure(Call<ResponseBuyViewed> call, Throwable t) {
                shortToast(t.getMessage());
            }
        });
    }

    private void initVisibilitiButtonForDetail(DataMateriItem current,
                                               final Button mulaiDetailItem,
                                               final Button beliDetailItem,
                                               final Button sabarDetailItem) {
        if (current.getMateriHarga().equals("0")) {
            mulaiDetailItem.setVisibility(View.VISIBLE);
        } else {
            RetroServer.getInstance().getDetailPmbyById(current.getMateriId(), sessionManager.getUserId()).enqueue(new Callback<ResponseGetPembayaran>() {
                @Override
                public void onResponse(Call<ResponseGetPembayaran> call, Response<ResponseGetPembayaran> response) {
                    assert response.body() != null;
                    if (response.body().getDataDetailPembayaran().get(0).getPmbyStatus().equals("lunas")) {
                        mulaiDetailItem.setVisibility(View.VISIBLE);
                    } else if (response.body().getDataDetailPembayaran().get(0).getPmbyStatus().equals("cek")) {
                        sabarDetailItem.setVisibility(View.VISIBLE);
                    } else {
                        beliDetailItem.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ResponseGetPembayaran> call, Throwable t) {

                }
            });
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_HOMEFRAGMENT

    private SliderView sliderIklanEvent;
    private ArrayList<DataEventItem> listIklanEvent;
    private ShimmerFrameLayout shimmerIklanEventItem;

    private SliderPagerAdapter mAdapterIklanEvent;
    private FragmentManager fragmentManager;
    private FragmentActivity fragmentActivity;
    private SliderIndicator mIndicatorIklanEvent;
    private LinearLayout mLinearLayoutIklanEvent;

    @Override
    public void setViewForIklanEventHomeFragment(SliderView sliderIklanEvent,
                                                 ShimmerFrameLayout shimmerEventItem,
                                                 LinearLayout pagesContainerEvent) {
        this.sliderIklanEvent = sliderIklanEvent;
        this.shimmerIklanEventItem = shimmerEventItem;
        this.mLinearLayoutIklanEvent = pagesContainerEvent;
    }

    @Override
    public void setContexForIklanEventHomeFragment(FragmentManager fragmentManager,
                                                   FragmentActivity activity) {
        this.fragmentManager = fragmentManager;
        this.fragmentActivity = activity;
    }

    @Override
    public void iniDataEventHome() {
        RetroServer.getInstance().getAllEvent().enqueue(new Callback<ResponseEvent>() {
            @Override
            public void onResponse(Call<ResponseEvent> call, Response<ResponseEvent> response) {
                List<DataEventItem> list = response.body().getDataEvent();
                listIklanEvent = new ArrayList();
                sliderIklanEvent.setDurationScroll(1000);
                List<Fragment> fragmentsListIklanEvent = new ArrayList<>();
                if (response.body().isSukses()) {
                    for (int i = 0; i < list.size(); i++) {
                        listIklanEvent.add(new DataEventItem(
                                list.get(i).getEventGambar(),
                                list.get(i).getEventVideo(),
                                list.get(i).getEventNama(),
                                list.get(i).getEventTglMulai(),
                                list.get(i).getMitraId(),
                                list.get(i).getEventTglSelesai(),
                                list.get(i).getEventXp(),
                                list.get(i).getEventAlamat(),
                                list.get(i).getEventDeskripsi(),
                                list.get(i).getEventKuota(),
                                list.get(i).getEventJenis(),
                                list.get(i).getEventId(),
                                list.get(i).getEventTiket(),
                                list.get(i).getEventKota()));
                        fragmentsListIklanEvent.add(FragmentSlider.newInstance(MyConstant.IMAGE_URL_EVENT + list.get(i).getEventGambar(), list.get(i).getEventNama()));
                    }
                    iniDataIklanEventToFlipper(fragmentsListIklanEvent);
                }
            }

            @Override
            public void onFailure(Call<ResponseEvent> call, Throwable t) {

            }
        });
    }

    private void iniDataIklanEventToFlipper(List<Fragment> fragmentsListIklanEvent) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 4999);
        shimmerIklanEventItem.setShimmer(null);
        shimmerIklanEventItem.stopShimmer();
        mAdapterIklanEvent = new SliderPagerAdapter(fragmentManager, fragmentsListIklanEvent, fragmentsListIklanEvent);
        sliderIklanEvent.setAdapter(mAdapterIklanEvent);
        mIndicatorIklanEvent = new SliderIndicator(fragmentActivity, mLinearLayoutIklanEvent, sliderIklanEvent, R.drawable.indicator_circle);
        mIndicatorIklanEvent.setPageCount(fragmentsListIklanEvent.size());
        mIndicatorIklanEvent.show();
    }

    private SliderView sliderIklanMateri;
    private List<Fragment> fragmentsListIklanMateri;
    private ArrayList<DataEventItem> listIklanMateri;
    private ShimmerFrameLayout shimmerIklanMateriItem;

    private SliderPagerAdapter mAdapterIklanMateri;
    private SliderIndicator mIndicatorIklanMateri;
    private LinearLayout mLinearLayoutIklanMateri;

    @Override
    public void setViewForIklanMateriHomeFragment(SliderView sliderIklanMateri,
                                                  ShimmerFrameLayout shimmerMateriItem,
                                                  LinearLayout pagesContainerMateri) {
        this.sliderIklanMateri = sliderIklanMateri;
        this.shimmerIklanMateriItem = shimmerMateriItem;
        this.mLinearLayoutIklanMateri = pagesContainerMateri;
    }


    @Override
    public void iniDataMateri() {
        RetroServer.getInstance().getAllEvent().enqueue(new Callback<ResponseEvent>() {
            @Override
            public void onResponse(Call<ResponseEvent> call, Response<ResponseEvent> response) {
                List<DataEventItem> list = response.body().getDataEvent();
                listIklanMateri = new ArrayList();
                sliderIklanMateri.setDurationScroll(1000);
                fragmentsListIklanMateri = new ArrayList<>();
                if (response.body().isSukses()) {
                    for (int i = 0; i < list.size(); i++) {
                        listIklanMateri.add(new DataEventItem(
                                list.get(i).getEventGambar(),
                                list.get(i).getEventVideo(),
                                list.get(i).getEventNama(),
                                list.get(i).getEventTglMulai(),
                                list.get(i).getMitraId(),
                                list.get(i).getEventTglSelesai(),
                                list.get(i).getEventXp(),
                                list.get(i).getEventAlamat(),
                                list.get(i).getEventDeskripsi(),
                                list.get(i).getEventKuota(),
                                list.get(i).getEventJenis(),
                                list.get(i).getEventId(),
                                list.get(i).getEventTiket(),
                                list.get(i).getEventKota()));
                        fragmentsListIklanMateri.add(FragmentSlider.newInstance(MyConstant.IMAGE_URL_EVENT + list.get(i).getEventGambar(), list.get(i).getEventNama()));
                    }
                    iniDataMateriEventToFlipper();
                }
            }

            @Override
            public void onFailure(Call<ResponseEvent> call, Throwable t) {

            }
        });
    }

    private void iniDataMateriEventToFlipper() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 4999);
        shimmerIklanMateriItem.setShimmer(null);
        shimmerIklanMateriItem.stopShimmer();
        mAdapterIklanMateri = new SliderPagerAdapter(fragmentManager, fragmentsListIklanMateri, fragmentsListIklanMateri);
        sliderIklanMateri.setAdapter(mAdapterIklanMateri);
        mIndicatorIklanMateri = new SliderIndicator(fragmentActivity, mLinearLayoutIklanMateri, sliderIklanMateri, R.drawable.indicator_circle);
        mIndicatorIklanMateri.setPageCount(fragmentsListIklanMateri.size());
        mIndicatorIklanMateri.show();
    }

    private SliderView sliderIklanKomersial;
    private List<Fragment> fragmentsListIklanKomersial;
    private ArrayList<DataEventItem> listIklanKomersial;
    private ShimmerFrameLayout shimmerIklanKomersialItem;

    private SliderPagerAdapter mAdapterIklanKomersial;
    private SliderIndicator mIndicatorIklanKomersial;
    private LinearLayout mLinearLayoutIklanKomersial;

    @Override
    public void setViewForIklanKomersialHomeFragment(SliderView sliderIklanKomersial,
                                                     ShimmerFrameLayout shimmerKomersialItem,
                                                     LinearLayout pagesContainerKomersial) {
        this.sliderIklanKomersial = sliderIklanKomersial;
        this.shimmerIklanKomersialItem = shimmerKomersialItem;
        this.mLinearLayoutIklanKomersial = pagesContainerKomersial;
    }

    @Override
    public void iniDataKomersial() {
        RetroServer.getInstance().getAllEvent().enqueue(new Callback<ResponseEvent>() {
            @Override
            public void onResponse(Call<ResponseEvent> call, Response<ResponseEvent> response) {
                List<DataEventItem> list = response.body().getDataEvent();
                listIklanKomersial = new ArrayList();
                sliderIklanKomersial.setDurationScroll(1000);
                fragmentsListIklanKomersial = new ArrayList<>();
                if (response.body().isSukses()) {
                    for (int i = 0; i < list.size(); i++) {
                        listIklanKomersial.add(new DataEventItem(
                                list.get(i).getEventGambar(),
                                list.get(i).getEventVideo(),
                                list.get(i).getEventNama(),
                                list.get(i).getEventTglMulai(),
                                list.get(i).getMitraId(),
                                list.get(i).getEventTglSelesai(),
                                list.get(i).getEventXp(),
                                list.get(i).getEventAlamat(),
                                list.get(i).getEventDeskripsi(),
                                list.get(i).getEventKuota(),
                                list.get(i).getEventJenis(),
                                list.get(i).getEventId(),
                                list.get(i).getEventTiket(),
                                list.get(i).getEventKota()));
                        fragmentsListIklanKomersial.add(FragmentSlider.newInstance(MyConstant.IMAGE_URL_EVENT + list.get(i).getEventGambar(), list.get(i).getEventNama()));
                    }
                    iniDataMateriKomersialToFlipper();
                }
            }

            @Override
            public void onFailure(Call<ResponseEvent> call, Throwable t) {

            }
        });
    }

    private void iniDataMateriKomersialToFlipper() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 4999);
        shimmerIklanKomersialItem.setShimmer(null);
        shimmerIklanKomersialItem.stopShimmer();
        mAdapterIklanKomersial = new SliderPagerAdapter(fragmentManager, fragmentsListIklanKomersial, fragmentsListIklanKomersial);
        sliderIklanKomersial.setAdapter(mAdapterIklanKomersial);
        mIndicatorIklanKomersial = new SliderIndicator(fragmentActivity, mLinearLayoutIklanKomersial, sliderIklanKomersial, R.drawable.indicator_circle);
        mIndicatorIklanKomersial.setPageCount(fragmentsListIklanKomersial.size());
        mIndicatorIklanKomersial.show();
    }


    private RecyclerView rvNewLearn;
    private AdapterNewLearn adapterNewLearn;
    private ArrayList<DataMateriItem> listNewLearn = new ArrayList<>();

    @Override
    public void setViewNewLearn(RecyclerView rvNewLearn) {
        this.rvNewLearn = rvNewLearn;
    }

    @Override
    public void setAdapterNewLearn(AdapterNewLearn adapterNewLearn) {
        this.adapterNewLearn = adapterNewLearn;
    }

    @Override
    public void setAdapterFirstNewLearn(AdapterNewLearn adapter) {
        rvNewLearn.setAdapter(adapter);
        rvNewLearn.setHasFixedSize(true);
        rvNewLearn.setLayoutManager(new GridLayoutManager(context, 3));
    }

    @Override
    public void startShimmerNewLearn() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initDataNewLearn();
                adapterNewLearn.isShimmer = false;
                adapterNewLearn.notifyDataSetChanged();
            }
        }, 4999);
    }


    private void initDataNewLearn() {
        RetroServer.getInstance().getAllMateri().enqueue(new Callback<ResponseMateri>() {
            @Override
            public void onResponse(Call<ResponseMateri> call, Response<ResponseMateri> response) {
                List<DataMateriItem> listData = response.body().getDataMateri();
                listNewLearn = new ArrayList<>();

                if (response.body().isSukses()) {
                    for (int i = 0; i < listData.size(); i++) {
                        if (i >= (listData.size() - 3)) {
                            listNewLearn.add(new DataMateriItem(listData.get(i).getMateriJmlModul(),
                                    listData.get(i).getMateriXp(),
                                    listData.get(i).getMateriWaktu(),
                                    listData.get(i).getMateriNama(),
                                    listData.get(i).getMateriDiskon(),
                                    listData.get(i).getMateriLevel(),
                                    listData.get(i).getMateriJumSiswa(),
                                    listData.get(i).getMateriGambar(),
                                    listData.get(i).getMateriId(),
                                    listData.get(i).getMitraId(),
                                    listData.get(i).getMateriVideo(),
                                    listData.get(i).getMateriRating(),
                                    listData.get(i).getJenisKelasId(),
                                    listData.get(i).getMateriDeadline(),
                                    listData.get(i).getMateriPlatform(),
                                    listData.get(i).getMateriDeskripsi(),
                                    listData.get(i).getMateriHarga(),
                                    listData.get(i).getMateriTgl()));
                        }
                    }
                }
                if (listNewLearn.size() > 0) {
                    initToRecyclerNewLearn();
                } else {
                    shortToast("data tidak ada!!");
                }
            }

            @Override
            public void onFailure(Call<ResponseMateri> call, Throwable t) {
                shortToast("response tidak ada!!");
            }
        });
    }

    private void initToRecyclerNewLearn() {
        adapterNewLearn = new AdapterNewLearn(context, listNewLearn, false);
        rvNewLearn.setAdapter(adapterNewLearn);
        rvNewLearn.setHasFixedSize(true);
        rvNewLearn.setLayoutManager(new GridLayoutManager(context, 3));
    }

    private RecyclerView rvFreeLearn;
    private AdapterFreeLearn adapterFreeLearn;
    private ArrayList<DataMateriItem> listFreeLearn = new ArrayList<>();

    @Override
    public void setViewFreeLearn(RecyclerView rvFreeLearn) {
        this.rvFreeLearn = rvFreeLearn;

    }

    @Override
    public void setAdapterFreeLearn(AdapterFreeLearn adapterFreeLearn) {
        this.adapterFreeLearn = adapterFreeLearn;
    }

    @Override
    public void setAdapterFirstFreeLearn(AdapterFreeLearn adapter) {
        rvFreeLearn.setAdapter(adapter);
        rvFreeLearn.setHasFixedSize(true);
        rvFreeLearn.setLayoutManager(new GridLayoutManager(context, 3));
    }

    @Override
    public void startShimmerFreeLearn() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initDataFreeLearn();
                adapterFreeLearn.isShimmer = false;
                adapterFreeLearn.notifyDataSetChanged();
            }
        }, 4999);
    }


    private void initDataFreeLearn() {
        RetroServer.getInstance().getAllMateri().enqueue(new Callback<ResponseMateri>() {
            @Override
            public void onResponse(Call<ResponseMateri> call, Response<ResponseMateri> response) {
                List<DataMateriItem> listData = response.body().getDataMateri();
                listNewLearn = new ArrayList<>();

                if (response.body().isSukses()) {
                    for (int i = 0; i < listData.size(); i++) {
                        if (listData.get(i).getMateriHarga().equals("0")) {
                            listFreeLearn.add(new DataMateriItem(listData.get(i).getMateriJmlModul(),
                                    listData.get(i).getMateriXp(),
                                    listData.get(i).getMateriWaktu(),
                                    listData.get(i).getMateriNama(),
                                    listData.get(i).getMateriDiskon(),
                                    listData.get(i).getMateriLevel(),
                                    listData.get(i).getMateriJumSiswa(),
                                    listData.get(i).getMateriGambar(),
                                    listData.get(i).getMateriId(),
                                    listData.get(i).getMitraId(),
                                    listData.get(i).getMateriVideo(),
                                    listData.get(i).getMateriRating(),
                                    listData.get(i).getJenisKelasId(),
                                    listData.get(i).getMateriDeadline(),
                                    listData.get(i).getMateriPlatform(),
                                    listData.get(i).getMateriDeskripsi(),
                                    listData.get(i).getMateriHarga(),
                                    listData.get(i).getMateriTgl()));
                        }
                    }
                }
                if (listFreeLearn.size() > 0) {
                    initToRecyclerFreeLearn();
                } else {
                    shortToast("data tidak ada!!");
                }
            }

            @Override
            public void onFailure(Call<ResponseMateri> call, Throwable t) {
                shortToast("response tidak ada!!");
            }
        });
    }

    private void initToRecyclerFreeLearn() {
        adapterFreeLearn = new AdapterFreeLearn(context, listFreeLearn, false);
        rvFreeLearn.setAdapter(adapterFreeLearn);
        rvFreeLearn.setHasFixedSize(true);
        rvFreeLearn.setLayoutManager(new GridLayoutManager(context, 3));
    }

    private RecyclerView rvPayLearn;
    private AdapterPayLearn adapterPayLearn;
    private ArrayList<DataMateriItem> listPayLearn = new ArrayList<>();

    @Override
    public void setViewPayLearn(RecyclerView rvPayLearn) {
        this.rvPayLearn = rvPayLearn;

    }

    @Override
    public void setAdapterPayLearn(AdapterPayLearn adapterPayLearn) {
        this.adapterPayLearn = adapterPayLearn;
    }

    @Override
    public void setAdapterFirstPayLearn(AdapterPayLearn adapter) {
        rvPayLearn.setAdapter(adapter);
        rvPayLearn.setHasFixedSize(true);
        rvPayLearn.setLayoutManager(new GridLayoutManager(context, 3));
    }

    @Override
    public void startShimmerPayLearn() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initDataPayLearn();
                adapterPayLearn.isShimmer = false;
                adapterPayLearn.notifyDataSetChanged();
            }
        }, 4999);
    }

    private void initDataPayLearn() {
        RetroServer.getInstance().getAllMateri().enqueue(new Callback<ResponseMateri>() {
            @Override
            public void onResponse(Call<ResponseMateri> call, Response<ResponseMateri> response) {
                List<DataMateriItem> listData = response.body().getDataMateri();
                listPayLearn = new ArrayList<>();

                if (response.body().isSukses()) {
                    for (int i = 0; i < listData.size(); i++) {
                        if (!listData.get(i).getMateriHarga().equals("0")) {
                            listPayLearn.add(new DataMateriItem(listData.get(i).getMateriJmlModul(),
                                    listData.get(i).getMateriXp(),
                                    listData.get(i).getMateriWaktu(),
                                    listData.get(i).getMateriNama(),
                                    listData.get(i).getMateriDiskon(),
                                    listData.get(i).getMateriLevel(),
                                    listData.get(i).getMateriJumSiswa(),
                                    listData.get(i).getMateriGambar(),
                                    listData.get(i).getMateriId(),
                                    listData.get(i).getMitraId(),
                                    listData.get(i).getMateriVideo(),
                                    listData.get(i).getMateriRating(),
                                    listData.get(i).getJenisKelasId(),
                                    listData.get(i).getMateriDeadline(),
                                    listData.get(i).getMateriPlatform(),
                                    listData.get(i).getMateriDeskripsi(),
                                    listData.get(i).getMateriHarga(),
                                    listData.get(i).getMateriTgl()));
                        }
                    }
                }
                if (listPayLearn.size() > 0) {
                    initToRecyclerPayLearn();
                } else {
                    shortToast("data tidak ada!!");
                }
            }

            @Override
            public void onFailure(Call<ResponseMateri> call, Throwable t) {
                shortToast("response tidak ada!!");
            }
        });
    }

    private RecyclerView rvEventHome;
    private AdapterEventHome adapterEventHome;
    private ArrayList<DataEventItem> listEventHome = new ArrayList<>();

    @Override
    public void setViewEventForHome(RecyclerView rvEventHome) {
        this.rvEventHome = rvEventHome;
    }

    @Override
    public void setAdapterEventHome(AdapterEventHome adapterEventHome) {
        this.adapterEventHome = adapterEventHome;
    }

    @Override
    public void setAdapterFirstEventHome(AdapterEventHome adapter) {
        rvEventHome.setAdapter(adapter);
        rvEventHome.setHasFixedSize(true);
        rvEventHome.setLayoutManager(new GridLayoutManager(context, 3));
    }

    @Override
    public void startShimmerEventHome() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initDataEventHome();
                adapterEventHome.isShimmer = false;
                adapterEventHome.notifyDataSetChanged();
            }
        }, 4999);
    }

    private void initDataEventHome() {
        RetroServer.getInstance().getAllEvent().enqueue(new Callback<ResponseEvent>() {
            @Override
            public void onResponse(Call<ResponseEvent> call, Response<ResponseEvent> response) {
                List<DataEventItem> list = response.body().getDataEvent();
                listEventHome = new ArrayList();
                if (response.body().isSukses()) {
                    for (int i = 0; i < list.size(); i++) {
                        listEventHome.add(new DataEventItem(
                                list.get(i).getEventGambar(),
                                list.get(i).getEventVideo(),
                                list.get(i).getEventNama(),
                                list.get(i).getEventTglMulai(),
                                list.get(i).getMitraId(),
                                list.get(i).getEventTglSelesai(),
                                list.get(i).getEventXp(),
                                list.get(i).getEventAlamat(),
                                list.get(i).getEventDeskripsi(),
                                list.get(i).getEventKuota(),
                                list.get(i).getEventJenis(),
                                list.get(i).getEventId(),
                                list.get(i).getEventTiket(),
                                list.get(i).getEventKota()));
                    }
                    initListDataEventToEventHome(listEventHome);
                }
            }

            @Override
            public void onFailure(Call<ResponseEvent> call, Throwable t) {

            }
        });
    }

    private void initListDataEventToEventHome(ArrayList<DataEventItem> listEventHome) {
        AdapterEventHome adapter = new AdapterEventHome(context, listEventHome, false);
        rvEventHome.setAdapter(adapter);
        rvEventHome.setHasFixedSize(true);
        rvEventHome.setLayoutManager(new GridLayoutManager(context, 3));
    }


    private void initToRecyclerPayLearn() {
        adapterPayLearn = new AdapterPayLearn(context, listPayLearn, false);
        rvPayLearn.setAdapter(adapterPayLearn);
        rvPayLearn.setHasFixedSize(true);
        rvPayLearn.setLayoutManager(new GridLayoutManager(context, 3));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_STARTLEARN
    private ArrayList<DataListContentByModulIdItem> listContentByModul;
    private AdapterContentList adapterContentList;
    private RecyclerView rvContentByIdModul;

    @Override
    public void setViewForContent(RecyclerView rvContentByIdModul) {
        this.rvContentByIdModul = rvContentByIdModul;
    }

    @Override
    public void initDataContentList(String modul_id) {
        RetroServer.getInstance().getContentByModulId(modul_id).enqueue(new Callback<ResponseContentModul>() {
            @Override
            public void onResponse(Call<ResponseContentModul> call, Response<ResponseContentModul> response) {
                assert response.body() != null;
                List<DataListContentByModulIdItem> list = response.body().getDataListContentByModulId();
                listContentByModul = new ArrayList<>();
                if (response.body().isSukses()) {
                    for (int i = 0; i < list.size(); i++) {
                        listContentByModul.add(new DataListContentByModulIdItem(list.get(i).getContentUrutan(), list.get(i).getModulId(), list.get(i).getContentIsi(), list.get(i).getModulTipe()));
                    }
                    initDataToRecyclerContentModul();
                } else {
                    shortToast("data tidak ada!!");
                }
            }

            @Override
            public void onFailure(Call<ResponseContentModul> call, Throwable t) {
                shortToast("Cek Koneksi!!");
            }
        });
    }

    private void initDataToRecyclerContentModul() {
        adapterContentList = new AdapterContentList(context, listContentByModul);
        rvContentByIdModul.setAdapter(adapterContentList);
        rvContentByIdModul.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvContentByIdModul.setHasFixedSize(true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_BUYACTIVITY

    @Override
    public void setViewForBuyActivity(final DataMateriItem dataMateriItem,
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
                                      TextView harga) {

        jumModul.setText(dataMateriItem.getMateriJmlModul());
        xp.setText(dataMateriItem.getMateriXp());
        waktu.setText(dataMateriItem.getMateriWaktu());
        nama.setText(dataMateriItem.getMateriNama());
        diskon.setText(dataMateriItem.getMateriDiskon());
        level.setText(dataMateriItem.getMateriLevel());
        jumSiswa.setText(dataMateriItem.getMateriJumSiswa());
        //hide
        materiId.setVisibility(View.GONE);
        materiId.setText(dataMateriItem.getMateriId());
        //hide
        mitraId.setVisibility(View.GONE);
        mitraId.setText(dataMateriItem.getMitraId());
        //hide
        jenisKelasId.setVisibility(View.GONE);
        jenisKelasId.setText(dataMateriItem.getJenisKelasId());

        materiPlatform.setText(dataMateriItem.getMateriPlatform());
        descripsi.setText(dataMateriItem.getMateriDeskripsi());
        harga.setText(dataMateriItem.getMateriHarga());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////I_PAYACTIVITY

    private TextView kodeBankReq;
    private TextView noReq;
    private TextView namaReq;
    private TextView totalReq;
    private TextView timeReq;

    private List<DataDetailPembayaranItem> dataPembayaran;

    @Override
    public void setViewForPayActivity(TextView kodeBankReq,
                                      TextView noReq,
                                      TextView namaReq,
                                      TextView totalReq,
                                      TextView timeReq) {
        this.kodeBankReq = kodeBankReq;
        this.noReq = noReq;
        this.namaReq = namaReq;
        this.totalReq = totalReq;
        this.timeReq = timeReq;
    }

    @Override
    public void initDataForViewPayActivity(String materiId,
                                           String userId) {

        RetroServer.getInstance().getDetailPmbyById(materiId, userId).enqueue(new Callback<ResponseGetPembayaran>() {
            @Override
            public void onResponse(Call<ResponseGetPembayaran> call, Response<ResponseGetPembayaran> response) {
                assert response.body() != null;
                dataPembayaran = response.body().getDataDetailPembayaran();
                initViewForPayActivity();
            }

            @Override
            public void onFailure(Call<ResponseGetPembayaran> call, Throwable t) {

            }
        });
    }

    private void initViewForPayActivity() {
        timeReq.setText(dataPembayaran.get(0).getPmbyBatas());
    }

    @Override
    public List<DataDetailPembayaranItem> getListPembayaran() {
        return dataPembayaran;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////I_DAFTARMODUL

    @Override
    public void regisToTableBelajar(String userId, String materiId) {
        RetroServer.getInstance().regisToTableBelajar(userId, materiId).enqueue(new Callback<ResponseRegisToTableBelajar>() {
            @Override
            public void onResponse(Call<ResponseRegisToTableBelajar> call, Response<ResponseRegisToTableBelajar> response) {
                if (response.body().isSukses()) {
                    shortToast(response.body().getPesan());
                } else {
                    shortToast(response.body().getPesan());
                }
            }

            @Override
            public void onFailure(Call<ResponseRegisToTableBelajar> call, Throwable t) {
                shortToast("Cek your Connection");
            }
        });

    }

    private RecyclerView rvListModulMateri;

    @Override
    public void setViewForDaftarModul(RecyclerView rvListModulMateri) {
        this.rvListModulMateri = rvListModulMateri;
    }

    @Override
    public void initDataModulList(final String materiId) {
        RetroServer.getInstance().getAllListModul(materiId, sessionManager.getUserId()).enqueue(new Callback<ResponseListModul>() {
            @Override
            public void onResponse(Call<ResponseListModul> call, Response<ResponseListModul> response) {
                assert response.body() != null;
                List<DataListModulByModulIdItem> list = response.body().getDataListModulByModulId();
                List<DataListModulByModulIdStatusItem> list1 = response.body().getDataListModulByModulIdStatus();
                ArrayList<DataListModulByModulIdItem> listDataListModul = new ArrayList<>();
                ArrayList<DataListModulByModulIdStatusItem> listDataListModulStatus = new ArrayList<>();
                if (response.body().isSukses()) {
                    for (int i = 0; i < list.size(); i++) {
                        listDataListModul.add(new DataListModulByModulIdItem(
                                list.get(i).getMateriId(),
                                list.get(i).getModulId(),
                                list.get(i).getModulJudul()));
                        if (i > (list1.size() - 1)) {
                            listDataListModulStatus.add(new DataListModulByModulIdStatusItem(
                                    "",
                                    "",
                                    "",
                                    "",
                                    ""));
                        } else {
                            listDataListModulStatus.add(new DataListModulByModulIdStatusItem(
                                    list1.get(i).getUserId(),
                                    list1.get(i).getMateriId(),
                                    list1.get(i).getModulId(),
                                    list1.get(i).getProgressId(),
                                    list1.get(i).getStatus()));
                        }

                    }
                    initDataToRecyclerListModulMateri(listDataListModul, listDataListModulStatus);
                } else {
                    shortToast("Data tidak ada!!!");
                }
            }

            @Override
            public void onFailure(Call<ResponseListModul> call, Throwable t) {
                shortToast("Cek Koneksi kamu!!");
            }
        });
    }

    private void initDataToRecyclerListModulMateri(ArrayList<DataListModulByModulIdItem> listDataListModul,
                                                   ArrayList<DataListModulByModulIdStatusItem> listDataListModulStatus) {
        AdapterModulList adapterModulList = new AdapterModulList(context, listDataListModul, listDataListModulStatus);
        rvListModulMateri.setAdapter(adapterModulList);
        rvListModulMateri.setHasFixedSize(true);
        rvListModulMateri.setLayoutManager(new LinearLayoutManager(context));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_UPLOADSTRUCKACTIVITY


    ////////////////////////////////////////////////////////////////////////////////////////////////I_PROFILFRAGMENT

    @Override
    public void initDataUserForProfile(String userId) {
        RetroServer.getInstance().gatDataUser(userId).enqueue(new Callback<ResponseDataUser>() {
            @Override
            public void onResponse(Call<ResponseDataUser> call, Response<ResponseDataUser> response) {
                List<DataUserItem> list = response.body().getDataUser();
                ArrayList<DataUserItem> dataUser = new ArrayList<>();
                if (response.isSuccessful()) {
                    for (int i = 0; i < list.size(); i++) {
                        dataUser.add(new DataUserItem(
                                list.get(i).getUserEmail(),
                                list.get(i).getUserPassword(),
                                list.get(i).getUserId(),
                                list.get(i).getUserImage(),
                                list.get(i).getUserAsal(),
                                list.get(i).getUserName(),
                                list.get(i).getUserXp(),
                                list.get(i).getUserDate()));
                    }
                }
                initViewForProfil(dataUser);
            }

            @Override
            public void onFailure(Call<ResponseDataUser> call, Throwable t) {

            }
        });
    }

    private void initViewForProfil(ArrayList<DataUserItem> dataUser) {
        //        Picasso.get().load(dataUser.get(0).getUserImage()).into(imageUser);
        emailUser.setText(dataUser.get(0).getUserEmail());
        idUser.setText(dataUser.get(0).getUserId());
        asalUser.setText(dataUser.get(0).getUserAsal());
        xpUser.setText(dataUser.get(0).getUserXp());
        dateUser.setText(dataUser.get(0).getUserDate());
        namaUser.setText(dataUser.get(0).getUserName());
    }

    private TextView emailUser;
    private TextView idUser;
    private TextView asalUser;
    private TextView namaUser;
    private TextView xpUser;
    private TextView dateUser;
    private ImageView imageUser;
    private RecyclerView rvMyLearnProgressUser;
    private RecyclerView rvMyLearnTersediaForProfil;

    @Override
    public void setViewForProfilFragment(ImageView imageUser,
                                         TextView emailUser,
                                         TextView idUser,
                                         TextView asalUser,
                                         TextView xpUser,
                                         TextView dateUser,
                                         TextView namaUser,
                                         RecyclerView rvMyLearnProgressUser,
                                         RecyclerView rvMyLearnTersediaForProfil) {
        this.imageUser = imageUser;
        this.emailUser = emailUser;
        this.idUser = idUser;
        this.asalUser = asalUser;
        this.xpUser = xpUser;
        this.dateUser = dateUser;
        this.namaUser = namaUser;
        this.rvMyLearnProgressUser = rvMyLearnProgressUser;
        this.rvMyLearnTersediaForProfil = rvMyLearnTersediaForProfil;
    }

    @Override
    public void initDataMyLearnList(String userId) {
        RetroServer.getInstance().getMyLearnList(userId).enqueue(new Callback<ResponseListMyLearn>() {
            @Override
            public void onResponse(Call<ResponseListMyLearn> call, Response<ResponseListMyLearn> response) {
                List<DataListMyLearnItem> list = response.body().getDataListMyLearn();
                ArrayList<DataListMyLearnItem> dataMyLearnList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    dataMyLearnList.add(new DataListMyLearnItem(list.get(i).getPmbyBukti(), list.get(i).getPmbyId(), list.get(i).getMateriPlatform(),
                            list.get(i).getPmbyTanggal(), list.get(i).getMateriNama(), list.get(i).getUserId(), list.get(i).getPmbyBatas(),
                            list.get(i).getMateriId(), list.get(i).getPmbyStatus(), list.get(i).getMateriGambar()));
                }
                initDataMyLearnListToRvMyLearnProfil(dataMyLearnList);
            }

            @Override
            public void onFailure(Call<ResponseListMyLearn> call, Throwable t) {

            }
        });
    }

    private void initDataMyLearnListToRvMyLearnProfil(ArrayList<DataListMyLearnItem> dataMyLearnList) {
        AdapterMyLearnListForProfil adapterMyLearnListForProfil = new AdapterMyLearnListForProfil(context, dataMyLearnList);
        rvMyLearnTersediaForProfil.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvMyLearnTersediaForProfil.setAdapter(adapterMyLearnListForProfil);
        rvMyLearnTersediaForProfil.setHasFixedSize(true);
    }

    @Override
    public void initDataMyLearnListProgress(String userId) {
        RetroServer.getInstance().getMyLearnProgress(userId).enqueue(new Callback<ResponseMyLearnProgress>() {
            @Override
            public void onResponse(Call<ResponseMyLearnProgress> call, Response<ResponseMyLearnProgress> response) {
                List<DataMyLearnProgressItem> list = response.body().getDataMyLearn();
                ArrayList<DataMyLearnProgressItem> dataMyLearnProgress = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    dataMyLearnProgress.add(new DataMyLearnProgressItem(
                            list.get(i).getBelajarStatus(),
                            list.get(i).getMateriXp(),
                            list.get(i).getMateriWaktu(),
                            list.get(i).getProgressModul(),
                            list.get(i).getLastSeen(),
                            list.get(i).getMateriNama(),
                            list.get(i).getMateriDiskon(),
                            list.get(i).getMateriLevel(),
                            list.get(i).getMateriGambar(),
                            list.get(i).getBelajarDeadline(),
                            list.get(i).getMateriRating(),
                            list.get(i).getMateriPlatform(),
                            list.get(i).getMateriDeskripsi(),
                            list.get(i).getMateriTgl(),
                            list.get(i).getBelajarMulai(),
                            list.get(i).getMateriJmlModul(),
                            list.get(i).getMateriJumSiswa(),
                            list.get(i).getMateriId(),
                            list.get(i).getMitraId(),
                            list.get(i).getMateriVideo(),
                            list.get(i).getJenisKelasId(),
                            list.get(i).getMateriDeadline(),
                            list.get(i).getBelajarId(),
                            list.get(i).getUserId(),
                            list.get(i).getMateriHarga()));
                }
                initDataMyLearnToRvMyLearnProgressProfil(dataMyLearnProgress);
            }

            @Override
            public void onFailure(Call<ResponseMyLearnProgress> call, Throwable t) {

            }
        });
    }

    private void initDataMyLearnToRvMyLearnProgressProfil(ArrayList<DataMyLearnProgressItem> dataMyLearnProgress) {
        AdapterMyLearnProgressForProfil adapterMyLearnProgressForProfil = new AdapterMyLearnProgressForProfil(context, dataMyLearnProgress);
        rvMyLearnProgressUser.setAdapter(adapterMyLearnProgressForProfil);
        rvMyLearnProgressUser.setHasFixedSize(true);
        rvMyLearnProgressUser.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_MYLEARN

    private RecyclerView rvMyLearn;

    @Override
    public void setViewForMyLearn(RecyclerView rvMyLearn) {
        this.rvMyLearn = rvMyLearn;
    }

    @Override
    public void initDataMyLearn(String userId) {
        RetroServer.getInstance().getDetailPmbyByIdUser(sessionManager.getUserId()).enqueue(new Callback<ResponseDetailPembayaranUser>() {
            @Override
            public void onResponse(Call<ResponseDetailPembayaranUser> call, Response<ResponseDetailPembayaranUser> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<DataDetailPembayaranUserItem> data = response.body().getDataDetailPembayaranUser();
                    ArrayList<DataDetailPembayaranUserItem> listDataDetailPembayaranUser = new ArrayList<>();
                    assert data != null;
                    for (int i = 0; i < data.size(); i++) {
                        listDataDetailPembayaranUser.add(new DataDetailPembayaranUserItem(
                                data.get(0).getPmbyBukti(),
                                data.get(0).getMateriXp(),
                                data.get(0).getMateriWaktu(),
                                data.get(0).getUserPassword(),
                                data.get(0).getMateriNama(),
                                data.get(0).getMateriDiskon(),
                                data.get(0).getMateriLevel(),
                                data.get(0).getMateriGambar(),
                                data.get(0).getUserName(),
                                data.get(0).getPmbyStatus(),
                                data.get(0).getMateriRating(),
                                data.get(0).getMateriPlatform(),
                                data.get(0).getMateriDeskripsi(),
                                data.get(0).getUserXp(),
                                data.get(0).getUserDate(),
                                data.get(0).getMateriTgl(),
                                data.get(0).getMateriJmlModul(),
                                data.get(0).getUserEmail(),
                                data.get(0).getPmbyBatas(),
                                data.get(0).getMateriJumSiswa(),
                                data.get(0).getUserImage(),
                                data.get(0).getUserAsal(),
                                data.get(0).getMateriId(),
                                data.get(0).getMitraId(),
                                data.get(0).getMateriVideo(),
                                data.get(0).getJenisKelasId(),
                                data.get(0).getMateriDeadline(),
                                data.get(0).getPmbyId(),
                                data.get(0).getPmbyTanggal(),
                                data.get(0).getUserId(),
                                data.get(0).getMateriHarga()));
                    }
                    initDataToRvMyLearn(listDataDetailPembayaranUser);
                }

            }

            @Override
            public void onFailure(Call<ResponseDetailPembayaranUser> call, Throwable t) {

            }
        });
    }


    private void initDataToRvMyLearn(ArrayList<DataDetailPembayaranUserItem> listDataDetailPembayaranUser) {
        AdapterMyLearn adapter = new AdapterMyLearn(context, listDataDetailPembayaranUser);
        rvMyLearn.setAdapter(adapter);
        rvMyLearn.setLayoutManager(new LinearLayoutManager(context));
        rvMyLearn.setHasFixedSize(true);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////I_DETAILEVENT

    private ImageView eventGambar;
    private WebView eventVideo;
    private TextView eventNama;
    private TextView eventTglMulai;
    private TextView eventMitraId;
    private TextView eventTglSelesai;
    private TextView eventXp;
    private TextView eventAlamat;
    private TextView eventDeskripsi;
    private TextView eventKuota;
    private TextView eventJenis;
    private TextView eventId;
    private ImageView eventTiket;
    private TextView eventKota;

    @Override
    public void setViewForDetailEvent(ImageView eventGambar,
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
                                      TextView eventKota) {
        this.eventGambar = eventGambar;
        this.eventVideo = eventVideo;
        this.eventNama = eventNama;
        this.eventTglMulai = eventTglMulai;
        this.eventMitraId = eventMitraId;
        this.eventTglSelesai = eventTglSelesai;
        this.eventXp = eventXp;
        this.eventAlamat = eventAlamat;
        this.eventDeskripsi = eventDeskripsi;
        this.eventKuota = eventKuota;
        this.eventJenis = eventJenis;
        this.eventId = eventId;
        this.eventTiket = eventTiket;
        this.eventKota = eventKota;

    }

    @Override
    public void setValueForDetailEvent(DataEventItem dataEventItem) {
//        eventGambar
//        eventVideo
        eventNama.setText(dataEventItem.getEventNama());
        eventTglMulai.setText(dataEventItem.getEventTglMulai());
        eventMitraId.setText(dataEventItem.getMitraId());
        eventTglSelesai.setText(dataEventItem.getEventTglSelesai());
        eventXp.setText(dataEventItem.getEventXp());
        eventAlamat.setText(dataEventItem.getEventAlamat());
        eventDeskripsi.setText(dataEventItem.getEventDeskripsi());
        eventKuota.setText(dataEventItem.getEventKuota());
        eventJenis.setText(dataEventItem.getEventJenis());
        eventId.setText(dataEventItem.getEventId());
//        eventTiket
        eventKota.setText(dataEventItem.getEventKota());

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_JOINEVENT

    @Override
    public void regisToTableJoinEvent(String userId, String event_id) {
        RetroServer.getInstance().joinEvent(userId, event_id).enqueue(new Callback<ResponseJoinEvent>() {
            @Override
            public void onResponse(Call<ResponseJoinEvent> call, Response<ResponseJoinEvent> response) {
                shortToast(response.body().getPesan());
                if (response.body().isSukses()) {
                    intent(Parent.class);
                }
            }

            @Override
            public void onFailure(Call<ResponseJoinEvent> call, Throwable t) {

            }
        });
    }

}
