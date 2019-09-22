package com.example.oke.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.oke.R;
import com.example.oke.model.Constant;

public class icash extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private String mId, msaldo, mgambar, msinopsis, mtrailer, mgenre, mdurasi, mrilis, mstatus;
    TextView saldo;
    Button topup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icash);
//
//        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
//        mTabHost.setup(getActivity(),getChildFragmentManager(),R.id.realtabcontent);
//        mTabHost.addTab(mTabHost.newTabSpec("play").setIndicator("playing now"), play.class,null);
//        mTabHost.addTab(mTabHost.newTabSpec("coming").setIndicator("Coming soon"), coming.class,null);

        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_ICASH);
        msaldo = intent.getStringExtra(Constant.KEY_SALDO);

        topup= (Button) findViewById(R.id.topup);
        saldo = (TextView) findViewById(R.id.saldo);
        saldo.setText("Rp." +msaldo);

        topup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(icash.this, topup.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

    }


}
