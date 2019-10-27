package com.example.oke.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.oke.R;
import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.library.format_idr;
import com.example.oke.metode_bayar;
import com.example.oke.model.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class rincian extends AppCompatActivity {
    private String mId, mtgl, mjam, mharga, mstudio, mnokursi, mIdf, mgambar, mjudulfilm, mjumlah, mId_user,total_rincian;
    TextView jdul, duduk, studio, tgl, jml, harga, total;
    ImageView gambar;
    Button bayar;
    BaseApiService mApiService;
    Context mContext;
    private BaseApiService apiInterface;
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;
    public String strArray[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian);

        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(rincian.this);


        Intent intent = getIntent();
        mnokursi = intent.getStringExtra("nokursi");
        mjumlah = intent.getStringExtra("jumlah");
        mId = intent.getStringExtra(Constant.KEY_ID_JWL);
        mtgl = intent.getStringExtra(Constant.KEY_TGL);
        mjam = intent.getStringExtra(Constant.KEY_JM);
        mharga = intent.getStringExtra(Constant.KEY_HARGA);
        mstudio = intent.getStringExtra(Constant.KEY_STUDIO);
        mIdf = intent.getStringExtra(Constant.KEY_ID_FILM);
        mgambar = intent.getStringExtra(Constant.KEY_GAMBAR);
        mjudulfilm = intent.getStringExtra(Constant.KEY_JUDUL_FILM);
        mId_user =""+ sharedPrefManager.getSPId(SharedPrefManager.SP_ID, "");

        jdul = findViewById(R.id.nm_film);
        duduk = findViewById(R.id.t_duduk);
        studio = findViewById(R.id.studio_psn);
        tgl = findViewById(R.id.tgl);
        jml = findViewById(R.id.jml_psn);
        harga = findViewById(R.id.hrg_psan);
        total = findViewById(R.id.total);
        gambar = findViewById(R.id.gbr_psn);
        String fullUrlImage = "http://192.168.8.109/admin/upload/gbrfilm/" + mgambar;

        Glide.with(rincian.this)
                .load(fullUrlImage)
                .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(7)))
                .into(gambar);

        String str = "" + mnokursi;
        String delimiter ="";
        strArray = str.split(delimiter);
        jml.setText(mjumlah);
        duduk.setText(mnokursi);
        jdul.setText(mjudulfilm);
        studio.setText(mstudio);
        tgl.setText(mtgl);
        int jumlah = Integer.parseInt(mjumlah);
        int hrga = Integer.parseInt(mharga);
        int hasil = jumlah * hrga;
        total_rincian = "" + hasil;
        total.setText(format_idr.toRupiah("" + hasil));
        harga.setText(format_idr.toRupiah("" + mharga));

        bayar = findViewById(R.id.bayar_rincian);
        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rincian.this, metode_bayar.class);
                intent.putExtra("total", total_rincian);
                intent.putExtra("jumlah", mjumlah);
                intent.putExtra("nokursi", mnokursi);
                intent.putExtra(Constant.KEY_ID_JWL, mId);
                intent.putExtra(Constant.KEY_ID_FILM, mIdf);
                intent.putExtra(Constant.KEY_HARGA, mharga);
                startActivity(intent);
            }
        });
    }

//    private void requestbayar() {
//        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
//        mApiService.pesanRequest(
//                mId_user,
//                mIdf,
//                mId,
//                mnokursi,
//                mjumlah,
//                total_rincian
//        ).enqueue(new Callback<ResponseBody>() {
//                      @Override
//                      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                          if (response.isSuccessful()) {
//                              Log.i("debug", "onResponse: BERHASIL");
//                              loading.dismiss();
//                              try {
//                                  JSONObject jsonRESULTS = new JSONObject(response.body().string());
//                                  if (jsonRESULTS.getString("error").equals("false")) {
////                                                     Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();
//                                      Intent intent = new Intent(rincian.this, metode_bayar.class);
//                                      intent.putExtra("total", total_rincian);
//                                      intent.putExtra("jumlah", mjumlah);
//                                      intent.putExtra("Kursi", mnokursi);
//                                      intent.putExtra(Constant.KEY_ID_JWL, mId);
//                                      intent.putExtra(Constant.KEY_ID_FILM, mIdf);
//                                      intent.putExtra(Constant.KEY_HARGA, mharga);
////                intent.putExtra(Constant.KEY_TGL, mtgl);
////                intent.putExtra(Constant.KEY_JM, mjam);
////                intent.putExtra(Constant.KEY_STUDIO, mstudio);
////                intent.putExtra(Constant.KEY_GAMBAR,mgambar);
////                intent.putExtra(Constant.KEY_JUDUL_FILM,mjudulfilm);
//                                      startActivity(intent);
//                                  } else {
//                                      String error_message = jsonRESULTS.getString("error_msg");
//                                      Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
//                                  }
//                              } catch (JSONException e) {
//                                  e.printStackTrace();
//                              } catch (IOException e) {
//                                  e.printStackTrace();
//                              }
//                          } else {
//                              Log.i("debug", "onResponse: GA BERHASIL");
//                              loading.dismiss();
//                          }
//
//                      }
//
//                      @Override
//                      public void onFailure(Call<ResponseBody> call, Throwable t) {
//                          Log.e("debug", "onFailure: ERROR > " + t.getMessage());
//                          Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
//                      }
//                  }
//        );
//    }

}
