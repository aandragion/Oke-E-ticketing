package com.example.oke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oke.activity.MainActivity;
import com.example.oke.activity.icash;
import com.example.oke.fragment.play;
import com.example.oke.library.format_idr;
import com.example.oke.model.Constant;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class transfer extends AppCompatActivity {
    String mjml, mpemilik, mlogo, mrek, mnorek, mmilik,mbatas;
    TextView  rek, jml, nama, tgl;
    ImageView logo;
    Button kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        jml = (TextView) findViewById(R.id.nominalsaldo);
        nama = (TextView) findViewById(R.id.namapemilik);
        rek = (TextView) findViewById(R.id.rek);
        logo = (ImageView) findViewById(R.id.logotrans);
        tgl = (TextView) findViewById(R.id.tanggal);
        kembali=  (Button) findViewById(R.id.kembali);

        kembali.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(transfer.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        Intent intent = getIntent();
//        mnorek = intent.getStringExtra("norek");
        mmilik = intent.getStringExtra("pemilik");
        mjml = intent.getStringExtra("jumlah");
        mpemilik = intent.getStringExtra(Constant.KEY_PEMILIK);
        mbatas = intent.getStringExtra(Constant.KEY_BATAS);
        mlogo = intent.getStringExtra(Constant.KEY_LOGO);
        mrek = intent.getStringExtra(Constant.KEY_REK);

        String fullUrlImage = "http://192.168.8.109/admin/upload/gbrfilm/" + mlogo;

        jml.setText(format_idr.toRupiah("" + mjml));
        nama.setText(mpemilik);
        rek.setText(mrek);

        Picasso.with(transfer.this)
                .load(fullUrlImage)
                .into(logo);

//
//        Calendar calendar = Calendar.getInstance();
//        Date today = calendar.getTime();
//
//        calendar.add(Calendar.DAY_OF_YEAR, 2);
//        Date tomorrow = calendar.getTime();
//        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy h:m:s a");
//
//        String todayAsString = dateFormat.format(today);
//        String tomorrowAsString = dateFormat.format(tomorrow);

        tgl.setText(mbatas);

    }
}
