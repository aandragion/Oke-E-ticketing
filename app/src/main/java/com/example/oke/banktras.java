package com.example.oke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oke.adapter.ad_bank;
import com.example.oke.adapter.ad_tbank;
import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.model.list_bank;
import com.example.oke.model.saldo_icash;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class banktras extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<list_bank> listbank;
    ad_tbank adapter;
    BaseApiService apiInterface;
    ProgressBar progressBar;
    TextView coba;
    private String mJml;
    saldo_icash saldo;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banktras);
        recyclerView = findViewById(R.id.recycle);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchContact("bank");
    }

    private void fetchContact(String type) {
        apiInterface = UtilsApi.getAPIService();

        Call<List<list_bank>> call = apiInterface.getBank(type);
        call.enqueue(new Callback<List<list_bank>>() {
            @Override
            public void onResponse(Call<List<list_bank>> call, Response<List<list_bank>> response) {

                listbank = response.body();
                adapter = new ad_tbank(listbank, banktras.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<list_bank>> call, Throwable t) {
                Toast.makeText(banktras.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
