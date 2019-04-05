package com.gzeinnumer.class_koding.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import com.athkalia.emphasis.EmphasisTextView;
import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.activity.Login;
import com.gzeinnumer.class_koding.activity.Parent;
import com.gzeinnumer.class_koding.activity.Register;
import com.gzeinnumer.class_koding.adapter.AdapterLearn;
import com.gzeinnumer.class_koding.model.DataMateriItem;
import com.gzeinnumer.class_koding.model.ResponseLogin;
import com.gzeinnumer.class_koding.model.ResponseMateri;
import com.gzeinnumer.class_koding.model.ResponseRegister;
import com.gzeinnumer.class_koding.network.RetroServer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//I_LearnFragment.Main
public class MainPresenter implements
        MainInterface.I_Register,
        MainInterface.I_LearnFragment,
        MainInterface.I_Login,
        MainInterface.I_DetailMateri{

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

    private ArrayList<DataMateriItem> list = new ArrayList<>();
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
                list = new ArrayList<>();

                if (response.body().isSukses()) {
                    for (int i = 0; i < listData.size(); i++) {
                        list.add(new DataMateriItem(listData.get(i).getMateriJmlModul(),
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
                if (list.size() > 0) {
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
        adapterLearn = new AdapterLearn(context, list, false);
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

    /////////////////////////////////////////////////////////////////////////////////////////////////I_DETAILMATERI
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
}
