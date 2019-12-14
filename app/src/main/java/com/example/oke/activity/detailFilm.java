package com.example.oke.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.oke.R;
import com.example.oke.adapter.ad_rating;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.library.load;
import com.example.oke.model.Constant;
import com.example.oke.model.list_rating;
import com.example.oke.pesan;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detailFilm extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    Context mContext;
    BaseApiService mApiService;
    private String mId, mjudulfilm, mgambar, msinopsis, mtrailer, mgenre, mdurasi, mrilis, mstatus,mrating;
    private TextView judulfilm, sinopsis, genre, durasi, rilis, rating, ulas;
    VideoView trailer;
    Button pesanan;
    private List<list_rating> listrating;
    ad_rating adapter;
    ImageView gambar, ratingbar;
    DisplayMetrics dm;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private BaseApiService apiInterface;
    private static String GOOGLE_YOUTUBE_API = "AIzaSyBH8szUCt1ctKQabVeQuvWgowaKxHVjn8E";
    public static final String VIDEO_ID = "c2UNv38V6y4";
    private YouTubePlayer mYoutubePlayer = null;

    private YouTubePlayerView mYoutubePlayerView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        mYoutubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        mYoutubePlayerView.initialize(GOOGLE_YOUTUBE_API, (YouTubePlayer.OnInitializedListener) this);

        judulfilm = (TextView) findViewById(R.id.judul);
        gambar = (ImageView) findViewById(R.id.gambardetail);
        sinopsis = (TextView) findViewById(R.id.sinopsis);
        genre =(TextView) findViewById(R.id.genre);
//        trailer=(VideoView) findViewById(R.id.trailer);
        durasi =(TextView) findViewById(R.id.durasi);
        rilis =(TextView) findViewById(R.id.rilis);
        rating  =(TextView) findViewById(R.id.rating);
        pesanan =(Button) findViewById(R.id.pesan);
        ratingbar = (ImageView) findViewById(R.id.ratingBar);
        ulas = (TextView) findViewById(R.id.ulas);
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
        mstatus= intent.getStringExtra(Constant.KEY_STATUS);
//
//        String FullUrlVideo = load.video(mtrailer);
//        String fullUrlImage = load.foto(mgambar);
        Resources res = getResources();
        Drawable drawable = ((Resources) res).getDrawable(R.drawable.ic_star_black_24dp);
        ratingbar.setBackground(drawable);
        judulfilm.setText(mjudulfilm);
        durasi.setText( mdurasi +" Menit");
        rilis.setText(mrilis);
        rating.setText(mrating);

        sinopsis.setText(msinopsis);
        Glide.with(detailFilm.this)
                .load(load.foto(mgambar))
                .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(7)))
                .into(gambar);
        genre.setText(mgenre);

        dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int Tinggi= dm.heightPixels;
        int Lebar= dm.widthPixels;

//        trailer.setMinimumHeight(Tinggi);
//        trailer.setMinimumWidth(Lebar);
//
//        trailer.setVideoURI(Uri.parse(load.video(mtrailer)));
//        trailer.setMediaController(new MediaController(this));

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

        recyclerView = findViewById(R.id.datarating);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchContact("" + mId);

        if( mstatus.equals( "coming soon" )  ) {
            pesanan.setVisibility(View.GONE);
            rating.setVisibility(View.GONE);
            ratingbar.setVisibility(View.GONE);
            ulas.setVisibility(View.GONE);
        }
//        trailer.start();
//        Picasso.with(detailFilm.this)
//                .load(FullUrlVideo)
//                .into((Target) trailer);
//        String firstCharNamaMatkul = mNamaMatkul.substring(0,1);
//        TextDrawable drawable = TextDrawable.builder()
//                .buildRound(firstCharNamaMatkul, getColor());
//        ivTextDrawable.setImageDrawable(drawable);
    }

    private void fetchContact(String type) {
        apiInterface = UtilsApi.getAPIService();

        Call<List<list_rating>> call = apiInterface.getRating(type);
        call.enqueue(new Callback<List<list_rating>>() {
            @Override
            public void onResponse(Call<List<list_rating>> call, Response<List<list_rating>> response) {
                listrating = response.body();
                adapter = new ad_rating(listrating, detailFilm.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<list_rating>> call, Throwable t) {

                Toast.makeText(detailFilm.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        if (!wasRestored) {
            youTubePlayer.cueVideo(mtrailer);
        }
        mYoutubePlayer = youTubePlayer;
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
