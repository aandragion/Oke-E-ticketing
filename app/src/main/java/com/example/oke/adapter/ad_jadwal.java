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
import com.example.oke.kursi;
import com.example.oke.library.format_idr;
import com.example.oke.model.Constant;
import com.example.oke.model.list_jadwal;
import com.example.oke.pesan;

import java.util.List;

public class ad_jadwal extends RecyclerView.Adapter<ad_jadwal.MyViewHolder>{

    public interface ChnageStatusListener{
        void onItemChangeListener(int position,list_jadwal model);
    }

    View v;
    List<list_jadwal> daftarjadwal;
    Context context;
    ChnageStatusListener chnageStatusListener;

    public ad_jadwal(List<list_jadwal> contacts, Context context) {
        this.daftarjadwal = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jadwal_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.tgl.setText(daftarjadwal.get(position).getTgl_jadwal());
        holder.jam.setText(daftarjadwal.get(position).getJam_tayang());
        holder.harga.setText(format_idr.toRupiah(""+daftarjadwal.get(position).getHarga()));
        holder.studio.setText(daftarjadwal.get(position).getStudio());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //memanggil detailfilm
                String id = daftarjadwal.get(position).getId();
                String tgl = daftarjadwal.get(position).getTgl_jadwal();
                String jm = daftarjadwal.get(position).getJam_tayang();
                String harga = daftarjadwal.get(position).getHarga();
                String studio = daftarjadwal.get(position).getStudio();

                Intent intent = new Intent(v.getContext(), kursi.class);
                Intent mntent = ((pesan)v.getContext()).getIntent();
                intent.putExtra(Constant.KEY_ID_JWL, id);
                intent.putExtra(Constant.KEY_TGL, tgl);
                intent.putExtra(Constant.KEY_JM, jm);
                intent.putExtra(Constant.KEY_HARGA, harga);
                intent.putExtra(Constant.KEY_STUDIO, studio);
                String mId = mntent.getStringExtra(Constant.KEY_ID_FILM);
                String mgambar = mntent.getStringExtra(Constant.KEY_GAMBAR);
                String mjudulfilm = mntent.getStringExtra(Constant.KEY_JUDUL_FILM);
                intent.putExtra(Constant.KEY_ID_FILM,mId);
                intent.putExtra(Constant.KEY_GAMBAR,mgambar);
                intent.putExtra(Constant.KEY_JUDUL_FILM,mjudulfilm);
                v.getContext().startActivity(intent);
            }
        });

//        list_jadwal model=daftarjadwal.get(i);
//        if(model!=null)
//        {
//            holder.tgl.setText(model.getTgl_jadwal());
//            holder.tgl.setText(model.getTgl_jadwal());
//            holder.jam.setText(model.getJam_tayang());
//            holder.harga.setText(model.getHarga());
//            holder.studio.setText(model.getStudio());
//            holder.position=i;
//            if(model.isSelect()){
//                holder.itemView.setBackgroundResource(R.drawable.roungreen);
//            }
//            else{
//                holder.itemView.setBackgroundResource(R.drawable.roun);
//            }
//        }
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                list_jadwal model1=daftarjadwal.get(i);
//                if(model1.isSelect()){
//                    model1.setSelect(false);
//                }else{
//                    model1.setSelect(true);
//                }
//                daftarjadwal.set(holder.position,model1);
//                if(chnageStatusListener!=null){
//                    chnageStatusListener.onItemChangeListener(holder.position,model1);
//                }
//                notifyItemChanged(holder.position);
//
//            }
//        });
//        if(! holder.itemView.isPressed()){
//            holder.itemView.setBackgroundColor((Color.parseColor("2c4770")));
//        }
//        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN)
//                {
//                    v.setBackgroundColor(Color.parseColor("#000000"));
//                }
//
//                return false;
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return daftarjadwal.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tgl, jam, harga, studio;
//        public int position;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tgl = itemView.findViewById(R.id.tgl_jadwal);
            jam = itemView.findViewById(R.id.jam_jadwal);
            harga = itemView.findViewById(R.id.jdl_keluar);
            studio = itemView.findViewById(R.id.studio_psn);
        }
    }
}
