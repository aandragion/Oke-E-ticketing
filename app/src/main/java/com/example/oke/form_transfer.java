package com.example.oke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.model.Constant;
import com.squareup.picasso.Picasso;

public class form_transfer extends AppCompatActivity {
    Context mContext;
    BaseApiService mApiService;
    private String mId, mnama, mlogo,mjlm;
    private TextView namabank, jml;
    ImageView logo;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_transfer);

        namabank = (TextView) findViewById(R.id.nm_bank);
        logo = (ImageView) findViewById(R.id.logo_bank);
        jml= (TextView) findViewById(R.id.jml);

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_BANK);
        mnama = intent.getStringExtra(Constant.KEY_BANK);
        mlogo = intent.getStringExtra(Constant.KEY_LOGO);
        mjlm= intent.getStringExtra(Constant.KEY_NOM);


        String fullUrlImage = "http://192.168.8.109/admin/upload/gbrfilm/" + mlogo;

        namabank.setText(mnama);
        jml.setText(mjlm);
        Picasso.with(form_transfer.this)
                .load(fullUrlImage)
                .into(logo);
    }
}
