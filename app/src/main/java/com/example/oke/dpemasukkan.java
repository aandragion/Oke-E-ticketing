package com.example.oke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.oke.activity.detailFilm;
import com.example.oke.library.format_idr;
import com.example.oke.library.load;
import com.example.oke.model.Constant;

public class dpemasukkan extends AppCompatActivity {
    private String mid_topup,mtanggal,mid_user,mid_bank,mlogo_bank,mnama_pemilik,mno_rekening,mjumlah_transfer,mn_pemilik,mbts_topup,mstatus_topup;
    private TextView bts,tgl,norek,nama,npemilik,jml,status;
    ImageView bank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpemasukkan);

        bts = (TextView) findViewById(R.id.bts_dmasuk);
        bank = (ImageView) findViewById(R.id.bank_dmasuk);
        tgl = (TextView) findViewById(R.id.tgl_dmasuk);
        norek =(TextView) findViewById(R.id.norek_dmasuk);
        nama=(TextView) findViewById(R.id.tujuan_dmasuk);
        npemilik =(TextView) findViewById(R.id.kirim_dmasuk);
        jml =(TextView) findViewById(R.id.jml_dmasuk);
        status  =(TextView) findViewById(R.id.status_dmasuk);

        Intent intent = getIntent();
        mid_topup = intent.getStringExtra(Constant.KEY_ID_TOPUP);
        mtanggal = intent.getStringExtra(Constant.KEY_TGL_TOPUP);
        mid_user = intent.getStringExtra(Constant.KEY_ID_USER);
        mid_bank = intent.getStringExtra(Constant.KEY_ID_BANK);
        mlogo_bank = intent.getStringExtra(Constant.KEY_LOGO);
        mnama_pemilik = intent.getStringExtra(Constant.KEY_REK);
        mno_rekening = intent.getStringExtra(Constant.KEY_PEMILIK);
        mjumlah_transfer = intent.getStringExtra(Constant.KEY_JML_TOPUP);
        mn_pemilik = intent.getStringExtra(Constant.KEY_MILIK_TOPUP);
        mbts_topup = intent.getStringExtra(Constant.KEY_BTS_TOPUP);
        mstatus_topup = intent.getStringExtra(Constant.KEY_STATUS_TOPUP);

//        String fullUrlImage = "https://cobabioskop.000webhostapp.com/upload/gbrfilm/" + mlogo_bank;

        Glide.with(dpemasukkan.this)
                .load(load.foto(mlogo_bank))
                .into(bank);

        if( mstatus_topup.equals( "Berhasil" )  ) {
            bts.setVisibility(View.GONE);
        }

//        if( mstatus_topup.equals( "Gagal" )  ) {
//            bts.setVisibility(View.GONE);
//        }

        tgl.setText(mtanggal);
        nama.setText(mnama_pemilik);
        norek.setText(mno_rekening);
        jml.setText( format_idr.toRupiah(""+mjumlah_transfer));
        npemilik.setText(mn_pemilik);
        bts.setText(mbts_topup);
        status.setText(mstatus_topup);

    }
}
