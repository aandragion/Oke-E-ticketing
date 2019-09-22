package com.example.oke.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.oke.R;
import com.example.oke.model.Constant;
import com.example.oke.model.DataModel;
import com.example.oke.activity.detailFilm;
import com.example.oke.model.list_film;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ad_beranda extends RecyclerView.Adapter<ad_beranda.MyViewHolder> {

    View v;
    List<list_film> daftarfilm;
    Context context;


    public ad_beranda(List<list_film> contacts, Context context) {
        this.daftarfilm = contacts;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridberanda, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.judul.setText(daftarfilm.get(position).getJudul_film());

        String fileName = daftarfilm.get(position).getGambar();
        String fullUrlImage = "http://192.168.8.109/admin/upload/gbrfilm/" + fileName;

        Picasso.with(holder.itemView.getContext())
                .load(fullUrlImage)
                .into(holder.gambar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //memanggil detailfilm
                String id = daftarfilm.get(position).getId();
                String judulFilm = daftarfilm.get(position).getJudul_film();
                String gambar = daftarfilm.get(position).getGambar();
                String sinopsis = daftarfilm.get(position).getSinopsis();
                String trailer = daftarfilm.get(position).getTrailer();
                String genre = daftarfilm.get(position).getId_genre();
                String status = daftarfilm.get(position).getStatus_film();
                String total = daftarfilm.get(position).getTotal_view();
                String durasi = daftarfilm.get(position).getDurasi();
                String rilis = daftarfilm.get(position).getRilis();

                Intent intent = new Intent(v.getContext(), detailFilm.class);
                intent.putExtra(Constant.KEY_ID_FILM, id);
                intent.putExtra(Constant.KEY_JUDUL_FILM, judulFilm);
                intent.putExtra(Constant.KEY_GAMBAR, gambar);
                intent.putExtra(Constant.KEY_SINOPSIS, sinopsis);
                intent.putExtra(Constant.KEY_TRAILER, trailer);
                intent.putExtra(Constant.KEY_GENRE, genre);
                intent.putExtra(Constant.KEY_STATUS, status);
                intent.putExtra(Constant.KEY_TOTAL, total);
                intent.putExtra(Constant.KEY_DURASI, durasi);
                intent.putExtra(Constant.KEY_RILIS, rilis);
                v.getContext().startActivity(intent);
            }
        });

    }

    private list_film getItem(int position) {
        return daftarfilm.get(position);
    }

    @Override
    public int getItemCount() {
        return daftarfilm.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView judul;
        ImageView gambar;

        public MyViewHolder(View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.gambar);
            judul = itemView.findViewById(R.id.judul);
        }
    }
}
