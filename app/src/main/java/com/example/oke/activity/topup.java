package com.example.oke.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.oke.R;
import com.example.oke.adapter.ad_bank;
import com.example.oke.adapter.ad_nominal;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.bank;
import com.example.oke.model.list_bank;
import com.example.oke.model.nominal;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class topup extends AppCompatActivity {
    ad_nominal nominalAdapter;
    RecyclerView rvNominal;
    private Object nominal;
    Button btkirim;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<list_bank> listbank;
    ad_bank adapter;
    BaseApiService apiInterface;
    ProgressBar progressBar;
    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;

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

//        btkirim = findViewById(R.id.kirim);
//        btkirim.setEnabled(true);
//        btkirim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                showBottomSheetDialog();
//
//                startActivity(new Intent(topup.this, bank.class)
//                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                finish();
//            }
//        });
//        bottom_sheet = findViewById(R.id.bottom_sheet);
//        mBehavior = BottomSheetBehavior.from(bottom_sheet);
    }

    private void showBottomSheetDialog() {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        ViewGroup container = null;
        final View view = getLayoutInflater().inflate(R.layout.activity_icash, container,false);


        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(view);

//        progressBar = findViewById(R.id.prograss);
//        recyclerView = findViewById(R.id.listview);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        fetchContact("bank");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
    }

//    private void fetchContact(String type) {
//        apiInterface = UtilsApi.getAPIService();
//
//        Call<List<list_bank>> call = apiInterface.getBank(type);
//        call.enqueue(new Callback<List<list_bank>>() {
//            @Override
//            public void onResponse(Call<List<list_bank>> call, Response<List<list_bank>> response) {
//
//                listbank = response.body();
//                adapter = new ad_bank(listbank, topup.this);
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onFailure(Call<List<list_bank>> call, Throwable t) {
//                Toast.makeText(topup.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    private List<nominal> getnominalList() {
        List<nominal> jumlah = new ArrayList<>();
        jumlah.add(new nominal("10000"));
        jumlah.add(new nominal("25000"));
        jumlah.add(new nominal("50000"));
        jumlah.add(new nominal("100000"));
        jumlah.add(new nominal("250000"));
        jumlah.add(new nominal("500000"));
        jumlah.add(new nominal("1000000"));
        return jumlah;
    }
}
