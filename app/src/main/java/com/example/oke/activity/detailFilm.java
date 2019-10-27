package com.example.oke.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.oke.R;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.model.Constant;
import com.example.oke.pesan;
import com.squareup.picasso.Picasso;

public class detailFilm extends AppCompatActivity {
    Context mContext;
    BaseApiService mApiService;
    private String mId, mjudulfilm, mgambar, msinopsis, mtrailer, mgenre, mdurasi, mrilis, mstatus,mrating;
    private TextView judulfilm, sinopsis, genre, durasi, rilis, rating;
    VideoView trailer;
    Button pesanan;
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
        rating  =(TextView) findViewById(R.id.rating);
        pesanan =(Button) findViewById(R.id.pesan);
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
        mrating = intent.getStringExtra(Constant.KEY_TOTAL);

        String FullUrlVideo = "http://192.168.8.109/admin/upload/vdfilm/" + mtrailer;
        String fullUrlImage = "http://192.168.8.109/admin/upload/gbrfilm/" + mgambar;

        judulfilm.setText(mjudulfilm);
        durasi.setText(mdurasi);
        rilis.setText(mrilis);
        rating.setText(mrating);

        sinopsis.setText(msinopsis);
        Glide.with(detailFilm.this)
                .load(fullUrlImage)
                .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(7)))
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

        pesanan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), pesan.class);
                            intent.putExtra(Constant.KEY_ID_FILM, mId);
                            intent.putExtra(Constant.KEY_GAMBAR, mgambar);
                            intent.putExtra(Constant.KEY_JUDUL_FILM, mjudulfilm);
//                            intent.putExtra(Constant.KEY_LOGO, mlogo);

                            startActivity(intent);
//                            intent.putExtra("variabelumur", mlogo.getText().toString());
            }
        });


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
