package com.example.oke.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oke.R;
import com.example.oke.dpemasukkan;
import com.example.oke.library.format_idr;
import com.example.oke.model.Constant;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tanggal.setText(daftarpemasukkan.get(position).getTanggal());
        holder.nominal.setText(format_idr.toRupiah(""+daftarpemasukkan.get(position).getJumlah_transfer()));
        holder.status.setText(daftarpemasukkan.get(position).getStatus_topup());

        if( daftarpemasukkan.get(position).getStatus_topup().equals( "Berhasil" )  ) {
            holder.status.setBackgroundResource(rounstatushijau);
        }

        if( daftarpemasukkan.get(position).getStatus_topup().equals( "Proses" )  ) {
            holder.status.setBackgroundResource(rounstatusbiru);
        }

        if( daftarpemasukkan.get(position).getStatus_topup().equals( "Gagal" )  ) {
            holder.status.setBackgroundResource(rounstatusmerah);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //memanggil detailfilm
                String id = daftarpemasukkan.get(position).getId();
                String tanggal = daftarpemasukkan.get(position).getTanggal();
                String id_user = daftarpemasukkan.get(position).getId_user();
                String id_bank = daftarpemasukkan.get(position).getId_bank();
                String logo_bank = daftarpemasukkan.get(position).getLogo_bank();
                String nama_pemilik = daftarpemasukkan.get(position).getNama_pemilik();
                String no_rekening = daftarpemasukkan.get(position).getNo_rekening();
                String jumlah_transfer = daftarpemasukkan.get(position).getJumlah_transfer();
                String n_pemilik = daftarpemasukkan.get(position).getN_pemilik();
                String bts_topup = daftarpemasukkan.get(position).getBts_topup();
                String status_topup = daftarpemasukkan.get(position).getStatus_topup();
            
                Intent intent = new Intent(v.getContext(), dpemasukkan.class);
                intent.putExtra(Constant.KEY_ID_TOPUP, id);
                intent.putExtra(Constant.KEY_TGL_TOPUP, tanggal);
                intent.putExtra(Constant.KEY_ID_USER, id_user);
                intent.putExtra(Constant.KEY_ID_BANK, id_bank);
                intent.putExtra(Constant.KEY_LOGO, logo_bank);
                intent.putExtra(Constant.KEY_REK, nama_pemilik);
                intent.putExtra(Constant.KEY_PEMILIK, no_rekening);
                intent.putExtra(Constant.KEY_JML_TOPUP, jumlah_transfer);
                intent.putExtra(Constant.KEY_MILIK_TOPUP, n_pemilik);
                intent.putExtra(Constant.KEY_BTS_TOPUP, bts_topup);
                intent.putExtra(Constant.KEY_STATUS_TOPUP, status_topup);
                v.getContext().startActivity(intent);
            }
        });

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
