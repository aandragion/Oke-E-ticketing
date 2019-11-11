package com.example.oke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.library.format_idr;
import com.example.oke.library.load;
import com.example.oke.model.listtras;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dtrans extends AppCompatActivity {
    String midpesan,mtanggal,mid_user,mid_bank,mlogo_bank,mnama_pemilik,mno_rekening,mjumlah_transfer,mn_pemilik,mbts_topup,mstatus_topup;
    TextView bts,tgl,norek,nama,npemilik,jml,status;
    ImageView bank;
    listtras ad_trasfer;
    private BaseApiService apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtrans);

        bts = (TextView) findViewById(R.id.bts_dmasuk);
        bank = (ImageView) findViewById(R.id.bank_dmasuk);
        tgl = (TextView) findViewById(R.id.tgl_dmasuk);
        norek =(TextView) findViewById(R.id.norek_dmasuk);
        nama=(TextView) findViewById(R.id.tujuan_dmasuk);
        npemilik =(TextView) findViewById(R.id.kirim_dmasuk);
        jml =(TextView) findViewById(R.id.jml_dmasuk);
        status  =(TextView) findViewById(R.id.status_dmasuk);

        Intent intent = getIntent();
        midpesan = intent.getStringExtra("id_pesan");
        fetchContact("" + midpesan);


    }

    private void fetchContact(String type) {
        apiInterface = UtilsApi.getAPIService();

        Call<listtras> call = apiInterface.gettras(type);
        call.enqueue(new Callback<listtras>() {

            @Override
            public void onResponse(Call<listtras> call, Response<listtras> response) {
                ad_trasfer = response.body();
//                btnEdit.setText(format_idr.toRupiah("" + ad_trasfer.getSaldo_icash()));
                bts.setText(""+ad_trasfer.getBts_transfer());
//                bank.setText(ad_trasfer.);
//                String fullUrlImage = "https://cobabioskop.000webhostapp.com/upload/gbrfilm/" + ad_trasfer.getLogo_bank();

                Glide.with(dtrans.this)
                        .load(load.foto(ad_trasfer.getLogo_bank()))
                        .into(bank);

                tgl.setText(""+ad_trasfer.getTanggal());
                norek.setText(ad_trasfer.getNo_rekening());
                nama.setText(ad_trasfer.getNama_pemilik());
                npemilik.setText(ad_trasfer.getN_pemilik());
                jml.setText(format_idr.toRupiah("" +  ad_trasfer.getTotal_harga()));
                status.setText(ad_trasfer.getId_status());
            }

            @Override
            public void onFailure(Call<listtras> call, Throwable t) {
                Toast.makeText(dtrans.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
