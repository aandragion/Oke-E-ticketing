package com.example.oke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oke.library.format_idr;
import com.example.oke.model.Constant;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class bukti_pesan extends AppCompatActivity {
    private String mid, mid_user, mtanggal_pesan, mid_film, mjudul, mgambar, mid_jadwal, mtgl_jadwal, mjam_ayang, mharga, mstudio, mkursi, mjumlah_pesanan, mtotal_harga, mstatus_pesanan;
    private TextView id, id_user, tanggal_pesan, id_film, judul, gambar, id_jadwal, tgl_jadwal, jam_ayang, harga, studio, kursi, jumlah_pesanan, total_harga, status_pesanan;
    ImageView qrcode;
    Button detailtras, rating;
    Bitmap bitmap ;
    private Handler mHandler;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bukti_pesan);

//        this.mHandler = new Handler();

//        this.mHandler.postDelayed(m_Runnable,5000);

//        doTheAutoRefresh();



        Intent intent = getIntent();
        mid = intent.getStringExtra(Constant.KEY_ID_PESAN);
        mid_user = intent.getStringExtra(Constant.KEY_JUDUL_FILM);
        mtanggal_pesan = intent.getStringExtra(Constant.KEY_TGL_PSN);
        mid_film = intent.getStringExtra(Constant.KEY_ID_FILM);
        mjudul = intent.getStringExtra(Constant.KEY_JUDUL_FILM);
        mgambar = intent.getStringExtra(Constant.KEY_GAMBAR);
        mid_jadwal = intent.getStringExtra(Constant.KEY_ID_JWL);
        mtgl_jadwal = intent.getStringExtra(Constant.KEY_TGL);
        mjam_ayang = intent.getStringExtra(Constant.KEY_JM);
        mharga = intent.getStringExtra(Constant.KEY_HARGA);
        mstudio = intent.getStringExtra(Constant.KEY_STUDIO);
        mkursi = intent.getStringExtra(Constant.KEY_KURSI);
        mjumlah_pesanan = intent.getStringExtra(Constant.KEY_JML_PSN);
        mtotal_harga = intent.getStringExtra(Constant.KEY_TOTAL_PSN);
        mstatus_pesanan = intent.getStringExtra(Constant.KEY_STATUS_PSN);

        detailtras =(Button) findViewById(R.id.detailtras);
        rating =(Button) findViewById(R.id.rating);
        qrcode = (ImageView) findViewById(R.id.qr_bukti);
        tanggal_pesan = (TextView) findViewById(R.id.tgl_bukti);
        judul = (TextView) findViewById(R.id.nm_film_bukti);
        tgl_jadwal = (TextView) findViewById(R.id.tgl_nonton);
        jam_ayang = (TextView) findViewById(R.id.jam_bukti);
        harga = (TextView) findViewById(R.id.hrg_psan_bukti);
        studio = (TextView) findViewById(R.id.studio_psn_bukti);
        kursi = (TextView) findViewById(R.id.t_duduk_bukti);
        jumlah_pesanan = (TextView) findViewById(R.id.jml_psn_bukti);
        total_harga = (TextView) findViewById(R.id.total_bukti);
        status_pesanan = (TextView) findViewById(R.id.status_bukti);

        if( mstatus_pesanan.equals( "Proses" )  ) {
            detailtras.setVisibility(View.VISIBLE);
        }

        if( mstatus_pesanan.equals( "Selesai" )  ) {
            rating.setVisibility(View.VISIBLE);
        }

        if( mstatus_pesanan.equals( "Scan" )  ) {
            Toast.makeText(bukti_pesan.this, "berhasil", Toast.LENGTH_LONG).show();
        }

        detailtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //memanggil detailfilm
//                String jml = list.get(position).getJumlah();

                Intent intent = new Intent(v.getContext(), dtrans.class);
                intent.putExtra("id_pesan", mid);
                v.getContext().startActivity(intent);
            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //memanggil detailfilm
//                String jml = list.get(position).getJumlah();

                Intent intent = new Intent(v.getContext(), rating.class);
                intent.putExtra("judul", mjudul);
                intent.putExtra("id", mid_film);
                intent.putExtra("gambar", mgambar);
                v.getContext().startActivity(intent);
            }
        });

        tanggal_pesan.setText(mtanggal_pesan);
        judul.setText(mjudul);
        tgl_jadwal.setText(mtgl_jadwal);
        jam_ayang.setText(mjam_ayang);
        harga.setText(format_idr.toRupiah(mharga));
        studio.setText(mstudio);
        kursi.setText(mkursi);
        jumlah_pesanan.setText(mjumlah_pesanan);
        total_harga.setText(format_idr.toRupiah(mtotal_harga));
        status_pesanan.setText(mstatus_pesanan);

        try {
            bitmap = textToImage(""+mid,700,700);
            if (bitmap!= null) {
                qrcode.setImageBitmap(bitmap);
            }
        } catch (WriterException e) {
            e.printStackTrace();
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

//    private final Runnable m_Runnable = new Runnable()
//    {
//        public void run()
//
//        {
//            Toast.makeText(bukti_pesan.this,"in runnable",Toast.LENGTH_SHORT).show();
//
//            bukti_pesan.this.mHandler.postDelayed(m_Runnable, 5000);
//        }
//
//    };

//    private void doTheAutoRefresh() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Write code for your refresh logic
//                doTheAutoRefresh();
//            }
//        }, 5000);
//    }
}
