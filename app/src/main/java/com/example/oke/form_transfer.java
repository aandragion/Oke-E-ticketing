package com.example.oke;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.library.format_idr;
import com.example.oke.model.Constant;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class form_transfer extends AppCompatActivity {
    Context mContext;
    BaseApiService mApiService;
    private String mId, mnama, mlogo, mjlm, mrek, mpemilik, tomorrowAsString, todayString;
    private TextView namabank, jml, mid_user;
    EditText norek, pemilik;
    Button bayar;
    ImageView logo;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_transfer);

        sharedPrefManager = new SharedPrefManager(form_transfer.this);

        namabank = (TextView) findViewById(R.id.nm_bank);
        logo = (ImageView) findViewById(R.id.logo_bank);
        jml = (TextView) findViewById(R.id.jml);
//        norek= (EditText) findViewById(R.id.tnorek);
        pemilik= (EditText) findViewById(R.id.tpemilik);
        bayar = (Button) findViewById(R.id.bayar);
        mid_user =(TextView) findViewById(R.id.textView23);


        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy h:m:s a");

        todayString = dateFormat.format(today);
        tomorrowAsString = dateFormat.format(tomorrow);

        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.bayar:
                        if (validasi())
//                        {
//
//                            Intent intent = new Intent(view.getContext(), transfer.class);
//                            intent.putExtra("norek", norek.getText().toString());
//                            intent.putExtra("pemilik", pemilik.getText().toString());
//                            intent.putExtra("jumlah", jml.getText().toString());
//                            intent.putExtra(Constant.KEY_BATAS, tomorrowAsString);
//                            intent.putExtra(Constant.KEY_PEMILIK, mpemilik);
//                            intent.putExtra(Constant.KEY_REK, mrek);
//                            intent.putExtra(Constant.KEY_LOGO, mlogo);
//
//                            startActivity(intent);
////                            intent.putExtra("variabelumur", mlogo.getText().toString());
//                        }

                        requestTransfer();
                        break;
                }
            }

            private void requestTransfer() {

                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                mApiService.transferRequest(
                        todayString,
                        mid_user.getText().toString(),
                        mId,
                        mjlm,
//                        norek.getText().toString(),
                        pemilik.getText().toString(),
                        tomorrowAsString

                )

                        .enqueue(new Callback<ResponseBody>() {
                                     @Override
                                     public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                         if (response.isSuccessful()) {
                                             Log.i("debug", "onResponse: BERHASIL");
                                             loading.dismiss();
                                             try {
                                                 JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                                 if (jsonRESULTS.getString("error").equals("false")) {
//                                                     Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();
                                                     Intent intent = new Intent(mContext, transfer.class);
//                                                     intent.putExtra("norek", norek.getText().toString());
                                                     intent.putExtra("pemilik", pemilik.getText().toString());
                                                     intent.putExtra("jumlah", mjlm);
                                                     intent.putExtra(Constant.KEY_BATAS, tomorrowAsString);
                                                     intent.putExtra(Constant.KEY_PEMILIK, mpemilik);
                                                     intent.putExtra(Constant.KEY_REK, mrek);
                                                     intent.putExtra(Constant.KEY_LOGO, mlogo);

                                                     startActivity(intent);
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
        });

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_BANK);
        mnama = intent.getStringExtra(Constant.KEY_BANK);
        mlogo = intent.getStringExtra(Constant.KEY_LOGO);
        mrek = intent.getStringExtra(Constant.KEY_REK);
        mpemilik = intent.getStringExtra(Constant.KEY_PEMILIK);
        mjlm = intent.getStringExtra(Constant.KEY_NOM);



        String fullUrlImage = "http://192.168.8.109/admin/upload/gbrfilm/" + mlogo;
        mid_user.setText(sharedPrefManager.getSPId(SharedPrefManager.SP_ID, ""));
        namabank.setText(mnama);
        jml.setText( format_idr.toRupiah(""+mjlm));
        Picasso.with(form_transfer.this)
                .load(fullUrlImage)
                .into(logo);
    }

    private boolean validasi() {
        return (!validate.cek(pemilik)) ? true : false;
    }
}
