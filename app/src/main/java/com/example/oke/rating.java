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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.oke.activity.MainActivity;
import com.example.oke.activity.detailFilm;
import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.library.load;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class rating extends AppCompatActivity {
    String mid_film, mjudul, mgambar, mId_user, mrating;
    private RatingBar ratingBar;
    private Button btRating;
    private TextView judul;
    EditText diskripsi;
    ImageView gambar;
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;
    BaseApiService mApiService;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(rating.this);

        ratingBar = (RatingBar) findViewById(R.id.rt_bar);
        btRating = (Button) findViewById(R.id.b_rating);
        judul = (TextView) findViewById(R.id.judulrating);
        diskripsi=(EditText) findViewById(R.id.deskrip);
        gambar=(ImageView) findViewById(R.id.g_rating);

        Intent intent = getIntent();
        mid_film = intent.getStringExtra("id");
        mjudul = intent.getStringExtra("judul");
        mgambar = intent.getStringExtra("gambar");
        mId_user = "" + sharedPrefManager.getSPId(SharedPrefManager.SP_ID, "");
        mrating = ""+ratingBar.getRating();

        judul.setText(mjudul);

//        String fullUrlImage = "https://cobabioskop.000webhostapp.com/upload/gbrfilm/" + mgambar;
        Glide.with(rating.this)
                .load(load.foto(mgambar))
                .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(7)))
                .into(gambar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(rating.this," Rating "+ratingBar.getRating(), Toast.LENGTH_SHORT).show();
                mrating = ""+rating;
            }
        });

        btRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestbayar();
            }


        });
    }

    private void requestbayar() {
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
        mApiService.ratingRequest(
                mId_user,
                mid_film,
                mrating,
                diskripsi.getText().toString()

        ).enqueue(new Callback<ResponseBody>() {
                      @Override
                      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                          if (response.isSuccessful()) {
                              Log.i("debug", "onResponse: BERHASIL");
                              loading.dismiss();
                              try {
                                  JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                  if (jsonRESULTS.getString("error").equals("false")) {
//                                                     Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();
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
