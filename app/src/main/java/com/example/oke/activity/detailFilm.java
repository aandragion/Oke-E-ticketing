package com.example.oke.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oke.R;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;

public class detailFilm extends AppCompatActivity {
    Context mContext;
    BaseApiService mApiService;
    private String mId;
    private String mjudulfilm;
    private String mgambar;
    private TextView judulfilm,gambar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        judulfilm = (TextView) findViewById(R.id.judul);
        gambar = (TextView) findViewById(R.id.t_pass);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_FILM);
        mjudulfilm = intent.getStringExtra(Constant.KEY_JUDUL_FILM);
        mgambar = intent.getStringExtra(Constant.KEY_GAMBAR);

        judulfilm.setText(mjudulfilm);
//        tvNamaMatkul.setText(mgambar);
//        String firstCharNamaMatkul = mNamaMatkul.substring(0,1);
//        TextDrawable drawable = TextDrawable.builder()
//                .buildRound(firstCharNamaMatkul, getColor());
//        ivTextDrawable.setImageDrawable(drawable);
    }
}
