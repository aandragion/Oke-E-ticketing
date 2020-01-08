package com.example.oke.apihelper.api;

import com.example.oke.model.list_bank;
import com.example.oke.model.list_dpesan;
import com.example.oke.model.list_film;
import com.example.oke.model.list_jadwal;
import com.example.oke.model.list_kursi;
import com.example.oke.model.list_pemasukkan;
import com.example.oke.model.list_pengeluaran;
import com.example.oke.model.list_rating;
import com.example.oke.model.listtras;
import com.example.oke.model.saldo_icash;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface BaseApiService {
    // Fungsi ini untuk memanggil API http://192.168.8.109/admin-api/login.php
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    // Fungsi ini untuk memanggil API http://192.168.8.109/admin-api/register.php
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(@Field("username") String nama,
                                       @Field("email") String email,
                                       @Field("alamat") String alamat,
                                       @Field("no_tlp") String no_tlp,
                                       @Field("password") String password,
                                       @Field("repassword") String repassword);

    @FormUrlEncoded
    @POST("transfer.php")
    Call<ResponseBody> transferRequest(@Field("tanggal") String todayString,
                                       @Field("id_user") String id_user,
                                       @Field("id_bank") String mId,
                                       @Field("jumlah_transfer") String jumlah_transfer,
//                                       @Field("rek_pemilik") String rek_pemilik,
                                       @Field("n_pemilik") String n_pemilik,
                                       @Field("bts_topup") String tomorrowAsString);

    @FormUrlEncoded
    @POST("pesantrans.php")
    Call<ResponseBody> transRequest(@Field("id_user") String muser,
                                    @Field("id_film") String mfilm,
                                    @Field("id_jadwal") String mjadwal,
                                    @Field("kursi") String mkursi,
                                    @Field("jumlah") String mjumlah,
                                    @Field("total") String mJml,
                                    @Field("id_bank") String mId,
                                    @Field("n_pemilik") String n_pemilik,
                                    @Field("bts_tras") String tomorrowAsString);
//    @FormUrlEncoded
//    @POST("pengeluaran.php")
//    Call<ResponseBody> keluarRequest(@Field("id_user") String mId_user,
//                                       @Field("id_film") String mIdf,
//                                       @Field("harga") String mharga,
//                                       @Field("jumlah") String mjumlah,
//                                       @Field("total") String mtotal);

    @FormUrlEncoded
    @POST("pesan.php")
    Call<ResponseBody> pesanRequest(@Field("id_user") String mId_user,
                                    @Field("id_film") String mIdf,
                                    @Field("id_jadwal") String mId,
                                    @Field("kursi") String mnokursi,
                                    @Field("jumlah") String mjumlah,
                                    @Field("total") String mtotal,
                                    @Field("id_status") String mstatus);

    @FormUrlEncoded
    @POST("rating.php")
    Call<ResponseBody> ratingRequest(@Field("id_user") String id_user,
                                    @Field("id_film") String id_film,
                                    @Field("nilai") String nilai,
                                    @Field("ulasan") String ulasan);
    //    @FormUrlEncoded
//    @POST("up-profil.php")
//    Call<ResponseBody> editRequest(@Field("id_user") String id_user,
//                                   @Field("username") String nama,
//                                   @Field("email") String email,
//                                   @Field("alamat") String alamat,
//                                   @Field("no_tlp") String no_tlp,
//                                   @Field("password") String password,
//                                   @Field("photo") String photo
//    );

    @Multipart
    @POST("up-profil.php")
    Call<ResponseBody> editProfil(@Part("id_user") RequestBody id_user,
                                  @Part("username") RequestBody nama,
                                  @Part("email") RequestBody email,
                                  @Part("alamat") RequestBody alamat,
                                  @Part("no_tlp") RequestBody no_tlp,
                                  @Part("password") RequestBody password
            ,
                                  @Part MultipartBody.Part photo
    );

    @GET("datafilm.php")
    Call<List<list_film>> getContact(
            @Query("item_type") String item_type
    );

    @GET("datapesan.php")
    Call<List<list_dpesan>> getDpesan(
            @Query("item_type") String item_type
    );

    @GET("datapemasukkan.php")
    Call<List<list_pemasukkan>> getPemasukkan(
            @Query("item_type") String item_type
    );

    @GET("datapengeluaran.php")
    Call<List<list_pengeluaran>> getPengeluaran(
            @Query("item_type") String item_type
    );

    @GET("datacoming.php")
    Call<List<list_film>> getComing(
            @Query("item_type") String item_type
    );

    @GET("jadwal.php")
    Call<List<list_jadwal>>  getJadwal(
            @Query("item_type") String item_type
    );

    @GET("datarating.php")
    Call<List<list_rating>> getRating(
            @Query("item_type") String item_type
    );

//    @GET("listjam.php")
//    Call<List<list_jam>> getJam(
//            @Query("item_type") String item_type
//    );
//
//    @GET("liststudio.php")
//    Call<List<list_studio>> getStudio(
//            @Query("item_type") String item_type
//    );

    @GET("datapopular.php")
    Call<List<list_film>> getPopular(
            @Query("item_type") String item_type
    );

    @GET("data_bank.php")
    Call<List<list_bank>> getBank(
            @Query("item_type") String item_type
    );

    @GET("kursi.php")
    Call<List<list_kursi>> getKursi(
            @Query("item_type") String item_type
    );

    @GET("booking.php")
    Call<List<list_kursi>> getBooking(
            @Query("item_type") String item_type
    );

    @GET("ac_icash.php")
    Call<saldo_icash> getsaldo(
            @Query("item_type") String item_type
    );

    @GET("listtrasfer.php")
    Call<listtras> gettras(
            @Query("item_type") String item_type
    );

//    Call<ResponseBody> transferRequest(String toString, String toString1, String toString2, String tomorrowAsString);
}
