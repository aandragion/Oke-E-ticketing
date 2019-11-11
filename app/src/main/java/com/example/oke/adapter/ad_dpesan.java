package com.example.oke.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.oke.R;
import com.example.oke.bukti_pesan;
import com.example.oke.library.load;
import com.example.oke.model.Constant;
import com.example.oke.model.list_dpesan;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.List;

import static com.example.oke.R.drawable.rounstatusbiru;
import static com.example.oke.R.drawable.rounstatushijau;
import static com.example.oke.R.drawable.rounstatusmerah;
import static com.example.oke.R.drawable.rounstatusputih;

public class ad_dpesan extends RecyclerView.Adapter<ad_dpesan.MyViewHolder>{

    View v;
    List<list_dpesan> daftardpesan;
    Context context;
    public final static int QRcodeWidth = 500 ;
    Bitmap bitmap ;
    AppCompatActivity app;


    public  ad_dpesan(List<list_dpesan> contacts, Context context) {
        this.daftardpesan = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tanggal.setText(daftardpesan.get(position).getTgl_jadwal());
        holder.jam.setText(daftardpesan.get(position).getJam_ayang());
        holder.status.setText(daftardpesan.get(position).getStatus_pesanan());
        holder.studio.setText(daftardpesan.get(position).getStudio());
        holder.kursi.setText(daftardpesan.get(position).getKursi());

        if( daftardpesan.get(position).getStatus_pesanan().equals( "Berhasil" )  ) {
            holder.status.setBackgroundResource(rounstatushijau);
        }

        if( daftardpesan.get(position).getStatus_pesanan().equals( "Proses" )  ) {
            holder.status.setBackgroundResource(rounstatusbiru);
        }

        if( daftardpesan.get(position).getStatus_pesanan().equals( "Gagal" )  ) {
            holder.status.setBackgroundResource(rounstatusmerah);
        }
        if( daftardpesan.get(position).getStatus_pesanan().equals( "Selesai" )  ) {
            holder.status.setBackgroundResource(rounstatusputih);
        }
        String fileName = daftardpesan.get(position).getGambar();
        String qrcode1 = daftardpesan.get(position).getId();

        try {
            bitmap = textToImage(""+qrcode1,700,700);
            if (bitmap!= null) {
                holder.qrcode.setImageBitmap(bitmap);
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }


//        String fullUrlImage = "https://cobabioskop.000webhostapp.com/upload/gbrfilm/" + fileName;

        Glide.with(holder.itemView.getContext())
        .load(load.foto(fileName))
        .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(7)))
        .into(holder.gambar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //memanggil detailfilm
                String id = daftardpesan.get(position).getId();
                String id_user = daftardpesan.get(position).getId_user();
                String tanggal_pesan = daftardpesan.get(position).getTanggal_pesan();
                String id_film = daftardpesan.get(position).getId_film();
                String judul = daftardpesan.get(position).getJudul();
                String gambar = daftardpesan.get(position).getGambar();
                String id_jadwal = daftardpesan.get(position).getId_jadwal();
                String tgl_jadwal = daftardpesan.get(position).getTgl_jadwal();
                String jam_ayang = daftardpesan.get(position).getJam_ayang();
                String harga = daftardpesan.get(position).getHarga();
                String studio = daftardpesan.get(position).getStudio();
                String kursi = daftardpesan.get(position).getKursi();
                String jumlah_pesanan = daftardpesan.get(position).getJumlah_pesanan();
                String total_harga = daftardpesan.get(position).getTotal_harga();
                String status_pesanan = daftardpesan.get(position).getStatus_pesanan();

                Intent intent = new Intent(v.getContext(), bukti_pesan.class);
                intent.putExtra(Constant.KEY_ID_PESAN, id);
                intent.putExtra(Constant.KEY_JUDUL_FILM, id_user);
                intent.putExtra(Constant.KEY_TGL_PSN, tanggal_pesan);
                intent.putExtra(Constant.KEY_ID_FILM, id_film);
                intent.putExtra(Constant.KEY_JUDUL_FILM, judul);
                intent.putExtra(Constant.KEY_GAMBAR, gambar);
                intent.putExtra(Constant.KEY_ID_JWL, id_jadwal);
                intent.putExtra(Constant.KEY_TGL, tgl_jadwal);
                intent.putExtra(Constant.KEY_JM, jam_ayang);
                intent.putExtra(Constant.KEY_HARGA, harga);
                intent.putExtra(Constant.KEY_STUDIO, studio);
                intent.putExtra(Constant.KEY_KURSI, kursi);
                intent.putExtra(Constant.KEY_JML_PSN, jumlah_pesanan);
                intent.putExtra(Constant.KEY_TOTAL_PSN, total_harga);
                intent.putExtra(Constant.KEY_STATUS_PSN, status_pesanan);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return daftardpesan.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tanggal, jam, status, studio, kursi;
        ImageView qrcode, gambar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tgl_dpesan);
            jam     = itemView.findViewById(R.id.jam_dpesan);
            status  = itemView.findViewById(R.id.status_dpesan);
            studio  = itemView.findViewById(R.id.studio_dpesan);
            kursi   = itemView.findViewById(R.id.kursi_pesan);
            qrcode  = itemView.findViewById(R.id.qr_code);
            gambar  = itemView.findViewById(R.id.film_dpesan);
        }
    }

    private Bitmap textToImage(String text, int width, int height) throws WriterException, NullPointerException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.DATA_MATRIX.QR_CODE,
                width, height, null);
        } catch (IllegalArgumentException Illegalargumentexception) {
            return null;
        }

        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        int colorWhite = 0xFFFFFFFF;
        int colorBlack = 0xFF000000;

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? colorBlack : colorWhite;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, width, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

}
