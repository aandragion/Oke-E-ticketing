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
import com.example.oke.model.list_pemasukkan;

import java.util.List;

import static com.example.oke.R.drawable.rounstatusbiru;
import static com.example.oke.R.drawable.rounstatushijau;
import static com.example.oke.R.drawable.rounstatusmerah;

public class ad_pemasukkan extends RecyclerView.Adapter<ad_pemasukkan.MyViewHolder> {

    View v;
    List<list_pemasukkan> daftarpemasukkan;
    Context context;


    public  ad_pemasukkan(List<list_pemasukkan> contacts, Context context) {
        this.daftarpemasukkan = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pemasukkan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tanggal.setText(daftarpemasukkan.get(position).getTanggal());
        holder.nominal.setText(format_idr.toRupiah(""+daftarpemasukkan.get(position).getJumlah_transfer()));
        holder.status.setText(daftarpemasukkan.get(position).getStatus_topup());

        if( daftarpemasukkan.get(position).getStatus_topup().equals( "Selesai" )  ) {
            holder.status.setBackgroundResource(rounstatushijau);
        }

        if( daftarpemasukkan.get(position).getStatus_topup().equals( "Proses" )  ) {
            holder.status.setBackgroundResource(rounstatusbiru);
        }

        if( daftarpemasukkan.get(position).getStatus_topup().equals( "Gagal" )  ) {
            holder.status.setBackgroundResource(rounstatusmerah);
        }
    }

    @Override
    public int getItemCount() {
        return daftarpemasukkan.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tanggal, nominal, status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tgl_masuk);
            nominal = itemView.findViewById(R.id.nominal_masuk);
            status = itemView.findViewById(R.id.status_pemasukkan);
        }
    }
}
