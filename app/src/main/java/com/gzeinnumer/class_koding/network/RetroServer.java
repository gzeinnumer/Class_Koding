package com.gzeinnumer.class_koding.network;

import com.gzeinnumer.class_koding.BuildConfig;
import com.gzeinnumer.class_koding.helper.MyConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    public static Retrofit setInit(){
        return new Retrofit.Builder()
                .baseUrl(MyConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServices getInstance(){
        return setInit().create(ApiServices.class);
    }
}
