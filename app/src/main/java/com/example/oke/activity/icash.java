package com.example.oke.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.oke.R;
import com.example.oke.adapter.ad_tab;
import com.example.oke.library.format_idr;
import com.example.oke.model.Constant;
import com.google.android.material.tabs.TabLayout;

public class icash extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private String mId, msaldo, mgambar, msinopsis, mtrailer, mgenre, mdurasi, mrilis, mstatus;
    TextView saldo;
    Button topup;
    TabLayout tabLayout;
    ViewPager viewPager;
    ad_tab adaptertab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icash);

//        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
//        mTabHost.setup(getActivity(),getChildFragmentManager(),R.id.realtabcontent);
//        mTabHost.addTab(mTabHost.newTabSpec("play").setIndicator("playing now"), play.class,null);
//        mTabHost.addTab(mTabHost.newTabSpec("coming").setIndicator("Coming soon"), coming.class,null);

        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_ICASH);
        msaldo = intent.getStringExtra(Constant.KEY_SALDO);

        topup = (Button) findViewById(R.id.topup);
        saldo = (TextView) findViewById(R.id.saldo);
        tabLayout =(TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupTabLayout();
        saldo.setText(format_idr.toRupiah("" + msaldo));

        topup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(icash.this, topup.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

    }

    private void setupTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Pemasukkan"));
        tabLayout.addTab(tabLayout.newTab().setText("Pengeluaran"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adaptertab = new ad_tab(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adaptertab);
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                /*
                Switch case disini berfungsi untuk mengatur warna dan hint ketika disuatu posisi.
                Contoh :
                jika tab berada di posisi 0 yang berarti posisi 0 ini adalah tab pada kategori Home,
                warnanya akan berubah dan juga hint pada searchview akan berubah.

                posisi 0 = HOME
                posisi 1 = GAMES
                posisi 2 = MOVIES
                posisi 3 = BOOKS
                 */
                switch (tab.getPosition()) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

}
