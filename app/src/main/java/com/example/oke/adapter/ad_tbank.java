package com.example.oke.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oke.R;
import com.example.oke.bank;
import com.example.oke.banktras;
import com.example.oke.form_trans;
import com.example.oke.form_transfer;
import com.example.oke.library.load;
import com.example.oke.model.Constant;
import com.example.oke.model.list_bank;
import com.example.oke.model.nominal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ad_tbank extends RecyclerView.Adapter<ad_tbank.ViewHolder> {
    View v;
    List<list_bank> daftarbank;
    List<nominal> list;
    Context context;


    public ad_tbank(List<list_bank> contacts, Context context) {
        this.daftarbank = contacts;
        this.context = context;
    }
    @NonNull
    @Override
    public ad_tbank.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_item, parent, false);
        return new ad_tbank.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ad_tbank.ViewHolder holder, final int position) {
        holder.nama.setText(daftarbank.get(position).getNama_bank());

        String fileName = daftarbank.get(position).getLogo_bank();
//        String fullUrlImage = "https://cobabioskop.000webhostapp.com/upload/gbrfilm/" + fileName;

        Picasso.with(holder.itemView.getContext())
        .load(load.foto(fileName))
        .into(holder.logo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //memanggil detailfilm
                String id = daftarbank.get(position).getId();
                String nama_bank = daftarbank.get(position).getNama_bank();
                String logo_bank = daftarbank.get(position).getLogo_bank();
                String no_rekening = daftarbank.get(position).getNo_rekening();
                String nama_pemilik = daftarbank.get(position).getNama_pemilik();


                Intent intent = new Intent(v.getContext(), form_trans.class);
                Intent mntent = ((banktras)v.getContext()).getIntent();
                intent.putExtra(Constant.KEY_ID_BANK, id);
                intent.putExtra(Constant.KEY_BANK, nama_bank);
                intent.putExtra(Constant.KEY_LOGO, logo_bank);
                intent.putExtra(Constant.KEY_REK, no_rekening);
                intent.putExtra(Constant.KEY_PEMILIK, nama_pemilik);
                String mJml = mntent.getStringExtra(Constant.KEY_NOMINAL);
                String muser = mntent.getStringExtra("user");
                String mfilm = mntent.getStringExtra("film");
                String mjadwal = mntent.getStringExtra("jadwal");
                String mkursi = mntent.getStringExtra("kursi");
                String mjumlah = mntent.getStringExtra("jumlah");
                intent.putExtra(Constant.KEY_NOM,mJml);
                intent.putExtra("user", muser);
                intent.putExtra("film", mfilm);
                intent.putExtra("jadwal", mjadwal);
                intent.putExtra("kursi", mkursi);
                intent.putExtra("jumlah", mjumlah);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return daftarbank.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama;
        ImageView logo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.logo);
            nama = itemView.findViewById(R.id.nama_bank);
        }
    }
}
