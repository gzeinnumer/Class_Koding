package com.gzeinnumer.class_koding.network;

import com.gzeinnumer.class_koding.model.ResponseLogin;
import com.gzeinnumer.class_koding.model.ResponseMateri;
import com.gzeinnumer.class_koding.model.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {
    @GET("page_list/list/materi")
    Call<ResponseMateri> getAllMateri();

    @FormUrlEncoded
    @POST("login_user")
    Call<ResponseLogin> login(@Field("user_email") String user_email,
                              @Field("user_password") String user_password);

    @FormUrlEncoded
    @POST("add_data/user")
    Call<ResponseRegister> register(@Field("user_name") String user_name,
                                    @Field("user_email") String user_email,
                                    @Field("user_password") String user_password,
                                    @Field("user_asal") String user_asal);
}
