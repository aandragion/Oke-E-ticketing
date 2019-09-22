package com.example.oke.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.oke.R;
import com.example.oke.adapter.ad_nominal;
import com.example.oke.model.nominal;

import java.util.ArrayList;
import java.util.List;

public class topup extends AppCompatActivity {
    ad_nominal nominalAdapter;
    RecyclerView rvNominal;
    private Object nominal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);
        nominalAdapter = new ad_nominal(this, getnominalList(), this);
        rvNominal = findViewById(R.id.recycler);
        rvNominal.setLayoutManager(new LinearLayoutManager(this));
        rvNominal.setItemAnimator(new DefaultItemAnimator());
        rvNominal.setHasFixedSize(true);
        rvNominal.setAdapter(nominalAdapter);
        nominalAdapter.notifyDataSetChanged();


    }

    private List<nominal> getnominalList(){
        List<nominal> jumlah = new ArrayList<>();
        jumlah.add(new nominal( "10.000"));
        jumlah.add(new nominal( "25.000"));
        jumlah.add(new nominal( "50.000"));
        jumlah.add(new nominal( "100.000"));
        jumlah.add(new nominal( "250.000"));
        jumlah.add(new nominal( "500.000"));
        jumlah.add(new nominal( "1.000.000"));
        return jumlah;
    }
}
