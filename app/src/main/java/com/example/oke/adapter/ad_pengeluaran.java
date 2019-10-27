package com.example.oke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oke.R;
import com.example.oke.library.format_idr;
import com.example.oke.model.list_pengeluaran;

import java.util.List;

public class ad_pengeluaran extends RecyclerView.Adapter<ad_pengeluaran.MyViewHolder> {
    View v;
    List<list_pengeluaran> daftarpengeluaran;
    Context context;


    public  ad_pengeluaran(List<list_pengeluaran> contacts, Context context) {
        this.daftarpengeluaran = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keluar, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tanggal.setText(daftarpengeluaran.get(position).getTgl());
        holder.nominal.setText(format_idr.min(""+daftarpengeluaran.get(position).getTotal()));
        holder.judul.setText(daftarpengeluaran.get(position).getJudul());
    }

    @Override
    public int getItemCount() {
        return daftarpengeluaran.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tanggal, nominal, judul;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tgl_keluar);
            nominal = itemView.findViewById(R.id.nom_keluar);
            judul = itemView.findViewById(R.id.jdl_keluar);
        }
    }
}
