package com.example.oke.activity;

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
import com.example.oke.activity.Constant;
import com.example.oke.apihelper.api.list_beranda;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ad_beranda extends RecyclerView.Adapter<ad_beranda.MyViewHolder> {

    View v;
    private List<list_beranda> contacts;
    private Context context;
    private Activity activity;

    public ad_beranda(List<list_beranda> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridberanda, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.judul.setText(contacts.get(position).getJudul_film());

        String fileName = contacts.get(position).getGambar();
        String fullUrlImage = "http://192.168.8.109/admin/upload/gbrfilm/" + fileName;

        Picasso.with(holder.itemView.getContext())
                .load(fullUrlImage)
                .into(holder.gambar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //memanggil detailfilm
                String id = contacts.get(position).getId();
                String judulFilm = contacts.get(position).getJudul_film();
                String gambar = contacts.get(position).getGambar();

                Intent intent = new Intent(activity, detailFilm.class);
                intent.putExtra(Constant.KEY_ID_FILM, id);
                intent.putExtra(Constant.KEY_JUDUL_FILM, judulFilm);
                intent.putExtra(Constant.KEY_GAMBAR, gambar);
                activity.startActivity(intent);
            }
        });

    }

    private list_beranda getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView judul;
        ImageView gambar;
        public MyViewHolder(View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.gambar);
            judul = itemView.findViewById(R.id.judul);
        }
    }
}
