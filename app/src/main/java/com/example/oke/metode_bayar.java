package com.example.oke;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oke.activity.MainActivity;
import com.example.oke.activity.topup;
import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.library.format_idr;
import com.example.oke.model.Constant;
import com.example.oke.model.saldo_icash;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class metode_bayar extends AppCompatActivity {
    private String mId, mtgl, mjam, mharga, mstudio, mnokursi, mIdf, mgambar, mjudulfilm, mjumlah, mtotal, mId_user, mstatus, stringsaldo, total_rincian;
    TextView total, total2, md_saldo, vtotal, tidak;
    saldo_icash saldo;
    Button bayar, topup, transfer;
    RelativeLayout layout;
    BaseApiService mApiService;
    Context mContext;
    private BaseApiService apiInterface;
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode_bayar);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(metode_bayar.this);
        Intent intent = getIntent();
        mnokursi = intent.getStringExtra("nokursi");
        mtotal = intent.getStringExtra("total");
        mjumlah = intent.getStringExtra("jumlah");
        mId = intent.getStringExtra(Constant.KEY_ID_JWL);
//        mtgl = intent.getStringExtra(Constant.KEY_TGL);
//        mjam = intent.getStringExtra(Constant.KEY_JM);
        mharga = intent.getStringExtra(Constant.KEY_HARGA);
//        mstudio = intent.getStringExtra(Constant.KEY_STUDIO);
        mIdf = intent.getStringExtra(Constant.KEY_ID_FILM);
        mId_user = "" + sharedPrefManager.getSPId(SharedPrefManager.SP_ID, "");
//        mgambar = intent.getStringExtra(Constant.KEY_GAMBAR);
//        mjudulfilm = intent.getStringExtra(Constant.KEY_JUDUL_FILM);
        mstatus = "1";
        fetchContact("" + sharedPrefManager.getSPId(SharedPrefManager.SP_ID, ""));
        total = findViewById(R.id.md_total);
        total2 = findViewById(R.id.md_total2);
        vtotal = findViewById(R.id.textView8);
        tidak = findViewById(R.id.tidk);
        md_saldo = findViewById(R.id.md_nominal);
        bayar = findViewById(R.id.md_bayar);
        topup = findViewById(R.id.topup_md);
        transfer = findViewById(R.id.tranfer);
        layout = findViewById(R.id.layout4);

        total.setText(format_idr.min("" + mtotal));
        total2.setText(format_idr.toRupiah("" + mtotal));

        topup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(metode_bayar.this, topup.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        transfer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //memanggil detailfilm
//                String jml = list.get(position).getJumlah();

                Intent intent = new Intent(v.getContext(), banktras.class);
                intent.putExtra(Constant.KEY_NOMINAL, mtotal);
                intent.putExtra("user", mId_user);
                intent.putExtra("film", mIdf);
                intent.putExtra("jadwal", mId);
                intent.putExtra("kursi", mnokursi);
                intent.putExtra("jumlah", mjumlah);

                v.getContext().startActivity(intent);
            }
        });

        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestbayar();
            }


        });
    }

    private void fetchContact(String type) {
        apiInterface = UtilsApi.getAPIService();

        Call<saldo_icash> call = apiInterface.getsaldo(type);
        call.enqueue(new Callback<saldo_icash>() {

            @Override
            public void onResponse(Call<saldo_icash> call, Response<saldo_icash> response) {
                saldo = response.body();
                md_saldo.setText(format_idr.toRupiah("" + saldo.getSaldo_icash()));
                stringsaldo = "" + saldo.getSaldo_icash();

                int jumlah = Integer.parseInt(stringsaldo);
                int hrga = Integer.parseInt(mtotal);
                if (hrga > jumlah) {
                    total.setVisibility(View.GONE);
                    vtotal.setVisibility(View.GONE);
                    layout.setVisibility(View.GONE);
                    tidak.setVisibility(View.VISIBLE);
                    topup.setVisibility(View.VISIBLE);
                }

//                if (hrga < jumlah   ){
//                    tidak.setVisibility(View.VISIBLE);
//                }
            }

            @Override
            public void onFailure(Call<saldo_icash> call, Throwable t) {
                Toast.makeText(metode_bayar.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }


    private void requestbayar() {
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
        mApiService.pesanRequest(
                mId_user,
                mIdf,
                mId,
                mnokursi,
                mjumlah,
                mtotal,
                mstatus

        ).enqueue(new Callback<ResponseBody>() {
                      @Override
                      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                          if (response.isSuccessful()) {
                              Log.i("debug", "onResponse: BERHASIL");
                              loading.dismiss();
                              try {
                                  JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                  if (jsonRESULTS.getString("error").equals("false")) {
                                      Toast.makeText(mContext, "TIKET BERHASIL DIPESAN", Toast.LENGTH_SHORT).show();
                                      startActivity(new Intent(mContext, MainActivity.class));
                                  } else {
                                      String error_message = jsonRESULTS.getString("error_msg");
                                      Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                  }
                              } catch (JSONException e) {
                                  e.printStackTrace();
                              } catch (IOException e) {
                                  e.printStackTrace();
                              }
                          } else {
                              Log.i("debug", "onResponse: GA BERHASIL");
                              loading.dismiss();
                          }

                      }

                      @Override
                      public void onFailure(Call<ResponseBody> call, Throwable t) {
                          Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                          Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                      }
                  }
        );
    }
}
