package com.example.oke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.oke.R;
import com.example.oke.library.load;
import com.example.oke.model.list_rating;

import java.util.List;

public class ad_rating extends RecyclerView.Adapter<ad_rating.MyViewHolder> {

    View v;
    List<list_rating> daftarrating;
    Context context;

    public ad_rating(List<list_rating> contacts, Context context) {
        this.daftarrating = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public ad_rating.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rating, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ad_rating.MyViewHolder holder, int position) {
        holder.tgl.setText(daftarrating.get(position).getTanggal());
        holder.nama.setText(daftarrating.get(position).getNama());
        holder.ulasan.setText(daftarrating.get(position).getUlasan());
        holder.rating.setRating((daftarrating.get(position).getNilai())/2);

        String fileName = daftarrating.get(position).getPhoto();
//        String fullUrlImage = "https://cobabioskop.000webhostapp.com/gambar/" + fileName;

        Glide.with(holder.itemView.getContext())
                .load(load.gambar(fileName))
                .apply(RequestOptions.circleCropTransform())
                .into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return daftarrating.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tgl, nama, ulasan;
        RatingBar rating;
        ImageView photo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tgl = itemView.findViewById(R.id.tgl_rating);
            nama = itemView.findViewById(R.id.nama_rating);
            ulasan = itemView.findViewById(R.id.ulas_rating);
            rating = itemView.findViewById(R.id.rat_rating);
            photo = itemView.findViewById(R.id.photo_rating);
        }
    }
}
