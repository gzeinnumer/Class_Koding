package com.gzeinnumer.class_koding.presenter;

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
import com.gzeinnumer.class_koding.activity.BuyActivity;
import com.gzeinnumer.class_koding.activity.DaftarModul;
import com.gzeinnumer.class_koding.activity.Login;
import com.gzeinnumer.class_koding.activity.Parent;
import com.gzeinnumer.class_koding.activity.PayActivity;
import com.gzeinnumer.class_koding.activity.Register;
import com.gzeinnumer.class_koding.adapter.AdapterContentList;
import com.gzeinnumer.class_koding.adapter.AdapterFreeLearn;
import com.gzeinnumer.class_koding.adapter.AdapterLearn;
import com.gzeinnumer.class_koding.adapter.AdapterModulList;
import com.gzeinnumer.class_koding.adapter.AdapterNewLearn;
import com.gzeinnumer.class_koding.adapter.AdapterPayLearn;
import com.gzeinnumer.class_koding.helper.MyConstant;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.model.DataDetailPembayaranItem;
import com.gzeinnumer.class_koding.model.DataEventItem;
import com.gzeinnumer.class_koding.model.DataListContentByModulIdItem;
import com.gzeinnumer.class_koding.model.DataListModulByModulIdItem;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.model.ResponseContentModul;
import com.gzeinnumer.class_koding.model.ResponseEvent;
import com.gzeinnumer.class_koding.model.ResponseGetPembayaran;
import com.gzeinnumer.class_koding.model.ResponseListModul;
import com.gzeinnumer.class_koding.model.ResponseLogin;
import com.gzeinnumer.class_koding.model.ResponseMateri;
import com.gzeinnumer.class_koding.model.ResponseRegister;
import com.gzeinnumer.class_koding.network.RetroServer;
import com.gzeinnumer.class_koding.helper.sliderevent.FragmentSlider;
import com.gzeinnumer.class_koding.helper.sliderevent.SliderIndicator;
import com.gzeinnumer.class_koding.helper.sliderevent.SliderPagerAdapter;
import com.gzeinnumer.class_koding.helper.sliderevent.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//start
public class MainPresenter implements
        MainInterface.I_Register,
        MainInterface.I_LearnFragment,
        MainInterface.I_Login,
        MainInterface.I_DetailLearn,
        MainInterface.I_HomeFragment,
        MainInterface.I_StartLearning,
        MainInterface.I_BuyActivity,
        MainInterface.I_PayActivity,
        MainInterface.I_DaftarModul{

    private Context context;
    private AdapterLearn adapterLearn;

    public MainPresenter(Context context) {
        this.context = context;
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

    @Override
    public void setHighLightSingUp(EmphasisTextView register) {
        register.setText("Havent Account? Sign Up!!");
        register.setTextToHighlight("Sign Up!!");
        register.setTextHighlightColor(R.color.colorAccent);
        register.setCaseInsensitive(true);
        register.highlight();
    }

    @Override
    public void actionLogin(TextInputEditText email, TextInputEditText pass) {
        String temp1 = email.getText().toString().trim();
        String temp2 = pass.getText().toString().trim();
        isValidateLogin(email, pass, temp1, temp2);
    }

    private void isValidateLogin(TextInputEditText email, TextInputEditText pass, String temp1, String temp2) {
        if(TextUtils.isEmpty(temp1)){
            email.setError(context.getString(R.string.isEmpty));
            email.requestFocus();
        } else if(TextUtils.isEmpty(temp2)){
            pass.setError(context.getString(R.string.isEmpty));
            pass.requestFocus();
        } else {
            checkLogin(temp1, temp2);
        }
    }

    private void checkLogin(String temp1, String temp2) {
        RetroServer.getInstance().login(temp1, temp2).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.body().isSukses()){
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
    public void actionRegister(TextInputEditText username, TextInputEditText email, TextInputEditText pass, TextInputEditText cPass, TextInputEditText asal) {
        isValidateRegister(username, email, pass,cPass, asal);

    }

    private void isValidateRegister(TextInputEditText username, TextInputEditText email, TextInputEditText pass, TextInputEditText cPass, TextInputEditText asal) {
        String temp1 = username.getText().toString().trim();
        String temp2 = email.getText().toString().trim();
        String temp3 = pass.getText().toString().trim();
        String temp3_1 = cPass.getText().toString().trim();
        String temp4 = asal.getText().toString().trim();
        if (TextUtils.isEmpty(temp1)){
            username.setError(context.getString(R.string.isEmpty));
            username.requestFocus();
        } else if (TextUtils.isEmpty(temp2)){
            email.setError(context.getString(R.string.isEmpty));
            email.requestFocus();
        } else if (TextUtils.isEmpty(temp3)){
            pass.setError(context.getString(R.string.isEmpty));
            pass.requestFocus();
        } else if (!temp3.equals(temp3_1)){
            cPass.setError("Not Match");
            cPass.requestFocus();
        } else if (TextUtils.isEmpty(temp4)){
            asal.setError(context.getString(R.string.isEmpty));
            asal.requestFocus();
        } else {
            registerUser(temp1, temp2, temp3, temp4);
        }
    }

    private void registerUser(String temp1, String temp2, String temp3, String temp4) {
        RetroServer.getInstance().register(temp1, temp2, temp3, temp4).enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if (response.body().isSukses()){
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

    @Override
    public void actionLogin(Class<Login> loginClass) {
        intent(loginClass);
    }

    @Override
    public void setHighLightLogin(EmphasisTextView login) {
        login.setText("Have Account? Login!!");
        login.setTextToHighlight("Login!!");
        login.setTextHighlightColor(R.color.colorAccent);
        login.setCaseInsensitive(true);
        login.highlight();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_LEARNFRAGMENT

    //lempar ini lagi
    private ArrayList<DataMateriItem> listMateri = new ArrayList<>();
    private RecyclerView rvLearn;

    @Override
    public void setAdapterLearn(AdapterLearn adapter) {
        this.adapterLearn = adapter;
    }

    @Override
    public void setRecyclerViewLearn(RecyclerView rvLearn) {
        this.rvLearn=rvLearn;
    }

    @Override
    public void setAdapterFirst(AdapterLearn adapter) {
        rvLearn.setAdapter(adapter);
        rvLearn.setHasFixedSize(true);
        rvLearn.setLayoutManager(new GridLayoutManager(context, 3));
    }

    @Override
    public void startShimmer() {
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
                    initToRecyclerLearn();
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

    private void initToRecyclerLearn() {
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

    @Override
    public ArrayList<DataMateriItem> getListMateri() {
        return listMateri;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_DETAILMATERI
    @Override
    public void videoViewFunction(WebView videoDetailItem, DataMateriItem current) {
        videoDetailItem.getSettings().setJavaScriptEnabled(true);
        videoDetailItem.loadData("<iframe width=\"100%\" height=\"100%\" src=\""+current.getMateriVideo()+"\" frameborder=\"0\" allowfullscreen></iframe>","text/html","utf-8");
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

    @Override
    public void initViewDataDetail(DataMateriItem current, ImageView gambarDetailItem, TextView judulDetailItem, TextView descDetailItem, Button beliDetailItem, Button mulaiDetailItem, TextView olehDetailItem, TextView platDetailItem, TextView levelDetailItem, TextView bonusDetailItem, TextView waktuJamDetailItem, TextView deadlineDetailItem) {
        Picasso.get().load(MyConstant.IMAGE_URL_MATERI + current.getMateriGambar())
                .placeholder(R.color.shimmerbag).resize(399, 399).into(gambarDetailItem);
        judulDetailItem.setText(current.getMateriNama());
        descDetailItem.setText(current.getMateriDeskripsi());
        olehDetailItem.setText(current.getMitraId());
        platDetailItem.setText(current.getMateriPlatform());
        levelDetailItem.setText(current.getMateriLevel());
        bonusDetailItem.setText(current.getMateriXp()+" Xp");
        waktuJamDetailItem.setText("Durasi total video "+current.getMateriWaktu() + " Jam");
        deadlineDetailItem.setText("Deadline pengerjaan Modul "+current.getMateriDeadline() + " Hari");

        if (current.getMateriHarga().equals("0")){
            mulaiDetailItem.setVisibility(View.VISIBLE);
            actionMulaiDetailItem(mulaiDetailItem, current);
        } else {
            beliDetailItem.setVisibility(View.VISIBLE);
            actionBeliDetailItem(beliDetailItem, current);
        }
    }

    private void actionMulaiDetailItem(Button mulaiDetailItem, final DataMateriItem current) {
        mulaiDetailItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DaftarModul.class);
                intent.putExtra(DaftarModul.DATA , current.getMateriId());
                context.startActivity(intent);
            }
        });
    }

    private void actionBeliDetailItem(Button beliDetailItem, final DataMateriItem current) {
        beliDetailItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BuyActivity.class);
                intent.putExtra(BuyActivity.DATA , current);
                context.startActivity(intent);
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_HOMEFRAGMENT

    private SliderView sliderIklan;
    private List<Fragment> fragmentsList;
    private ArrayList<DataEventItem> listEvent;
    private ShimmerFrameLayout shimmerEventItem;

    private SliderPagerAdapter mAdapter;
    private FragmentManager fragmentManager;
    private FragmentActivity fragmentActivity;
    private SliderIndicator mIndicator;
    private LinearLayout mLinearLayout;

    @Override
    public void setSliderIklan(SliderView sliderIklan) {
        this.sliderIklan = sliderIklan;
    }

    @Override
    public void setShimmerForIklan(ShimmerFrameLayout shimmerEventItem) {
        this.shimmerEventItem = shimmerEventItem;

    }

    @Override
    public void setFragmentContextForSliderPagerAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void setFragmentActivityForSliderIndikator(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public void setLinearForSliderIndikator(LinearLayout mLinearLayout) {
        this.mLinearLayout = mLinearLayout;
    }

    @Override
    public void iniDataEvent() {
        RetroServer.getInstance().getAllEvent().enqueue(new Callback<ResponseEvent>() {
            @Override
            public void onResponse(Call<ResponseEvent> call, Response<ResponseEvent> response) {
                List<DataEventItem> list = response.body().getDataEvent();
                listEvent = new ArrayList();
                sliderIklan.setDurationScroll(1000);
                fragmentsList = new ArrayList<>();
                if (response.body().isSukses()) {
                    for (int i = 0; i < list.size(); i++) {
                        listEvent.add(new DataEventItem(
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
                        fragmentsList.add(FragmentSlider.newInstance(MyConstant.IMAGE_URL_EVENT + list.get(i).getEventGambar(),list.get(i).getEventNama()));
                    }
                    iniDataEventToFlipper();
                }
            }

            @Override
            public void onFailure(Call<ResponseEvent> call, Throwable t) {

            }
        });

    }

    private void iniDataEventToFlipper() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 4999);
        shimmerEventItem.setShimmer(null);
        shimmerEventItem.stopShimmer();
        mAdapter = new SliderPagerAdapter(fragmentManager, fragmentsList, fragmentsList);
        sliderIklan.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(fragmentActivity, mLinearLayout, sliderIklan, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragmentsList.size());
        mIndicator.show();
    }

    private RecyclerView rvNewLearn;
    private AdapterNewLearn adapterNewLearn;
    private ArrayList<DataMateriItem> listNewLearn = new ArrayList<>();

    @Override
    public void setRecyclerViewNewLearn(RecyclerView rvNewLearn) {
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

    @Override
    public ArrayList<DataMateriItem> getListNewLearn() {
        return listNewLearn;
    }

    private void initDataNewLearn() {
        RetroServer.getInstance().getAllMateri().enqueue(new Callback<ResponseMateri>() {
            @Override
            public void onResponse(Call<ResponseMateri> call, Response<ResponseMateri> response) {
                List<DataMateriItem> listData = response.body().getDataMateri();
                listNewLearn = new ArrayList<>();

                if (response.body().isSukses()) {
                    for (int i = 0; i < listData.size(); i++) {
                        if(i >= (listData.size()-3)){
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
    public void setRecyclerViewFreeLearn(RecyclerView rvFreeLearn) {
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

    @Override
    public ArrayList<DataMateriItem> getListFreeLearn() {
        return listFreeLearn;
    }

    private void initDataFreeLearn() {
        RetroServer.getInstance().getAllMateri().enqueue(new Callback<ResponseMateri>() {
            @Override
            public void onResponse(Call<ResponseMateri> call, Response<ResponseMateri> response) {
                List<DataMateriItem> listData = response.body().getDataMateri();
                listNewLearn = new ArrayList<>();

                if (response.body().isSukses()) {
                    for (int i = 0; i < listData.size(); i++) {
                        if(listData.get(i).getMateriHarga().equals("0")){
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
    public void setRecyclerViewPayLearn(RecyclerView rvPayLearn) {
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

    @Override
    public ArrayList<DataMateriItem> getListPayLearn() {
        return listPayLearn;
    }

    private void initDataPayLearn() {
        RetroServer.getInstance().getAllMateri().enqueue(new Callback<ResponseMateri>() {
            @Override
            public void onResponse(Call<ResponseMateri> call, Response<ResponseMateri> response) {
                List<DataMateriItem> listData = response.body().getDataMateri();
                listPayLearn = new ArrayList<>();

                if (response.body().isSukses()) {
                    for (int i = 0; i < listData.size(); i++) {
                        if(!listData.get(i).getMateriHarga().equals("0")){
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
    public void setRecyclerViewContentByIdModul(RecyclerView rvContentByIdModul) {
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
        rvContentByIdModul.setLayoutManager(new LinearLayoutManager(context));
        rvContentByIdModul.setHasFixedSize(true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_BUYACTIVITY

    @Override
    public void setViewForBuyActivity(final DataMateriItem dataMateriItem, TextView jumModul, TextView xp, TextView waktu, TextView nama, TextView diskon, TextView level, TextView jumSiswa, TextView materiId, TextView mitraId, TextView jenisKelasId, TextView materiPlatform, TextView descripsi, TextView harga, Button beli) {
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

        beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PayActivity.class);
                intent.putExtra(PayActivity.DATA, dataMateriItem);
                context.startActivity(intent);
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////I_PAYACTIVITY


    private TextView kodeBankReq;
    private TextView noReq;
    private TextView namaReq;
    private TextView totalReq;
    private TextView timeReq;

    private List<DataDetailPembayaranItem> dataPembayaran;


    @Override
    public void setViewForPayActivity(TextView kodeBankReq, TextView noReq, TextView namaReq, TextView totalReq, TextView timeReq) {
        this.kodeBankReq = kodeBankReq;
        this.noReq = noReq;
        this.namaReq = namaReq;
        this.totalReq = totalReq;
        this.timeReq = timeReq;
    }

    @Override
    public void initDataForViewPayActivity(String materiId, String userId) {

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

    @Override
    public List<DataDetailPembayaranItem> getListPembayaran() {
        return dataPembayaran;
    }

    private void initViewForPayActivity() {
        timeReq.setText(dataPembayaran.get(0).getPmbyBatas());
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////I_DAFTARMODUL

    private AdapterModulList adapterModulList;
    private ArrayList<DataListModulByModulIdItem> listDataListModul;
    private RecyclerView rvListModulMateri;

    @Override
    public void setRecyclerViewListModulMateri(RecyclerView rvListModulMateri) {
        this.rvListModulMateri = rvListModulMateri;
    }

    @Override
    public void initDataModulList(String materiId) {
        RetroServer.getInstance().getAllListModul(materiId).enqueue(new Callback<ResponseListModul>() {
            @Override
            public void onResponse(Call<ResponseListModul> call, Response<ResponseListModul> response) {
                assert response.body() != null;
                List<DataListModulByModulIdItem> list = response.body().getDataListModulByModulId();
                listDataListModul = new ArrayList<>();
                if (response.body().isSukses()){
                    for (int i=0; i<list.size(); i++){
                        listDataListModul.add(new DataListModulByModulIdItem(list.get(i).getMateriId(), list.get(i).getModulId(), list.get(i).getModulJudul()));
                    }
                    initDataToRecyclerListModulMateri();
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

    @Override
    public ArrayList<DataListModulByModulIdItem> getListDataListModul() {
        return listDataListModul;
    }

    @Override
    public AdapterModulList getAdapterModulList() {
        return adapterModulList;
    }

    private void initDataToRecyclerListModulMateri() {
        adapterModulList = new AdapterModulList(context, listDataListModul);
        rvListModulMateri.setAdapter(adapterModulList);
        rvListModulMateri.setHasFixedSize(true);
        rvListModulMateri.setLayoutManager(new LinearLayoutManager(context));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////I_UPLOADSTRUCKACTIVITY
}
