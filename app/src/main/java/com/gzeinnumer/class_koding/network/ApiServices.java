package com.gzeinnumer.class_koding.network;

import com.gzeinnumer.class_koding.model.ResponseBuyViewed;
import com.gzeinnumer.class_koding.model.ResponseContentModul;
import com.gzeinnumer.class_koding.model.ResponseDataUser;
import com.gzeinnumer.class_koding.model.ResponseDetailPembayaranUser;
import com.gzeinnumer.class_koding.model.ResponseEvent;
import com.gzeinnumer.class_koding.model.ResponseGetPembayaran;
import com.gzeinnumer.class_koding.model.ResponseListModul;
import com.gzeinnumer.class_koding.model.ResponseLogin;
import com.gzeinnumer.class_koding.model.ResponseMateri;
import com.gzeinnumer.class_koding.model.ResponsePembayaran;
import com.gzeinnumer.class_koding.model.ResponseRegister;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    @GET("page_list/list/event")
    Call<ResponseEvent> getAllEvent();

    @FormUrlEncoded
    @POST("get_modul_judul_by_materi_id")
    Call<ResponseListModul> getAllListModul(@Field("materi_id") String materi_id,@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("get_content_by_modul_id")
    Call<ResponseContentModul> getContentByModulId(@Field("modul_id") String modul_id);

    @FormUrlEncoded
    @POST("on_buy_learn_viewed")
    Call<ResponseBuyViewed> setOnBuyLearnViewed(@Field("materi_id") String materi_id,
                                                @Field("user_id") String user_id,
                                                @Field("materi_harga") String materi_harga);

    @FormUrlEncoded
    @POST("get_detail_pmby_by_id")
    Call<ResponseGetPembayaran> getDetailPmbyById(@Field("materi_id") String materi_id,
                                                  @Field("user_id") String user_id);

    @Multipart
    @POST("upload_image_bukti")
    Call<ResponsePembayaran> uploadBuktiPembayaran (@Part("materi_id") RequestBody materi_id,
                                                 @Part("user_id") RequestBody user_id,
                                                 @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("get_detail_pmby_by_id_user")
    Call<ResponseDetailPembayaranUser> getDetailPmbyByIdUser(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("get_user")
    Call<ResponseDataUser> gatDataUser(@Field("user_id") String user_id);
}
