package com.example.oke.apihelper.api;

import com.example.oke.model.list_film;
import com.example.oke.model.saldo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BaseApiService {
    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/login.php
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/register.php
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(@Field("username") String nama,
                                       @Field("email") String email,
                                       @Field("alamat") String alamat,
                                       @Field("no_tlp") String no_tlp,
                                       @Field("password") String password,
                                       @Field("repassword") String repassword);

    @FormUrlEncoded
    @POST("up-profil.php")
    Call<ResponseBody> editRequest(@Field("id_user") String id_user,
                                        @Field("username") String nama,
                                        @Field("email") String email,
                                        @Field("alamat") String alamat,
                                        @Field("no_tlp") String no_tlp,
                                        @Field("password") String password);

    @GET("datafilm.php")
    Call<List<list_film>> getContact(
            @Query("item_type") String item_type
    );

    @GET("ac_icash.php")
    Call<saldo> getsaldo(
            @Query("item_type") String item_type
    );
}
