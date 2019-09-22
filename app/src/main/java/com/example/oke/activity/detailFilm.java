package com.example.oke.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.oke.R;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.model.Constant;
import com.squareup.picasso.Picasso;

public class detailFilm extends AppCompatActivity {
    Context mContext;
    BaseApiService mApiService;
    private String mId, mjudulfilm, mgambar, msinopsis, mtrailer, mgenre, mdurasi, mrilis, mstatus;
    private TextView judulfilm, sinopsis, genre, durasi, rilis;
    VideoView trailer;
    ImageView gambar;
    DisplayMetrics dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        judulfilm = (TextView) findViewById(R.id.judul);
        gambar = (ImageView) findViewById(R.id.gambardetail);
        sinopsis = (TextView) findViewById(R.id.sinopsis);
        genre =(TextView) findViewById(R.id.genre);
        trailer=(VideoView) findViewById(R.id.trailer);
        durasi =(TextView) findViewById(R.id.durasi);
        rilis =(TextView) findViewById(R.id.rilis);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_FILM);
        mjudulfilm = intent.getStringExtra(Constant.KEY_JUDUL_FILM);
        mgambar = intent.getStringExtra(Constant.KEY_GAMBAR);
        msinopsis = intent.getStringExtra(Constant.KEY_SINOPSIS);
        mtrailer = intent.getStringExtra(Constant.KEY_TRAILER);
        mgenre = intent.getStringExtra(Constant.KEY_GENRE);
        mdurasi = intent.getStringExtra(Constant.KEY_DURASI);
        mrilis = intent.getStringExtra(Constant.KEY_RILIS);

        String FullUrlVideo = "http://192.168.8.109/admin/upload/vdfilm/" + mtrailer;
        String fullUrlImage = "http://192.168.8.109/admin/upload/gbrfilm/" + mgambar;

        judulfilm.setText(mjudulfilm);
        durasi.setText(mdurasi);
        rilis.setText(mrilis);

        sinopsis.setText(msinopsis);
        Picasso.with(detailFilm.this)
                .load(fullUrlImage)
                .into(gambar);
        genre.setText(mgenre);

        dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int Tinggi= dm.heightPixels;
        int Lebar= dm.widthPixels;

        trailer.setMinimumHeight(Tinggi);
        trailer.setMinimumWidth(Lebar);

        trailer.setVideoURI(Uri.parse(FullUrlVideo));
        trailer.setMediaController(new MediaController(this));
//        trailer.start();
//        Picasso.with(detailFilm.this)
//                .load(FullUrlVideo)
//                .into((Target) trailer);
//        String firstCharNamaMatkul = mNamaMatkul.substring(0,1);
//        TextDrawable drawable = TextDrawable.builder()
//                .buildRound(firstCharNamaMatkul, getColor());
//        ivTextDrawable.setImageDrawable(drawable);
    }
}
