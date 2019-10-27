package com.example.oke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.oke.activity.rincian;
import com.example.oke.adapter.ad_kursi;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.model.Constant;
import com.example.oke.model.list_booking;
import com.example.oke.model.list_kursi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kursi extends AppCompatActivity implements ad_kursi.ChnageStatusListener {
    private BaseApiService apiInterface;
    private List<list_kursi> listkursi;
    private List<list_booking> listbooking;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ad_kursi adapter;
    private String mId, mtgl, mjam, mharga, mstudio,mIdf,mgambar,mjudulfilm;
    Button pesan;

    List<String> bookingan;
    StringBuilder appendBooked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kursi);

        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_JWL);
        mtgl = intent.getStringExtra(Constant.KEY_TGL);
        mjam = intent.getStringExtra(Constant.KEY_JM);
        mharga = intent.getStringExtra(Constant.KEY_HARGA);
        mstudio = intent.getStringExtra(Constant.KEY_STUDIO);
        mIdf = intent.getStringExtra(Constant.KEY_ID_FILM);
        mgambar = intent.getStringExtra(Constant.KEY_GAMBAR);
        mjudulfilm = intent.getStringExtra(Constant.KEY_JUDUL_FILM);

        recyclerView = findViewById(R.id.seat);
        layoutManager = new GridLayoutManager(kursi.this, 10);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        pesan = findViewById(R.id.psn_tiket);
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kursi.this, rincian.class);
                if(adapter.getSelected().size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int j=0; j<adapter.getSelected().size(); j++) {
                        stringBuilder.append(adapter.getSelected().get(j).getKursi());
                        stringBuilder.append(" ");
                    }
                intent.putExtra("nokursi",stringBuilder.toString().trim());
                }

                String myArray = "" +adapter.getSelected().size();
                intent.putExtra("jumlah",myArray);
                intent.putExtra(Constant.KEY_ID_JWL, mId);
                intent.putExtra(Constant.KEY_TGL, mtgl);
                intent.putExtra(Constant.KEY_JM, mjam);
                intent.putExtra(Constant.KEY_HARGA, mharga);
                intent.putExtra(Constant.KEY_STUDIO, mstudio);
                intent.putExtra(Constant.KEY_ID_FILM,mIdf);
                intent.putExtra(Constant.KEY_GAMBAR,mgambar);
                intent.putExtra(Constant.KEY_JUDUL_FILM,mjudulfilm);
                startActivity(intent);

            }
        });

        createbooking("" + mId);
        createTable("kursi");

    }

    private void createTable(String type) {
        apiInterface = UtilsApi.getAPIService();

        Call<List<list_kursi>> call = apiInterface.getKursi(type);
        call.enqueue(new Callback<List<list_kursi>>() {
            @Override
            public void onResponse(Call<List<list_kursi>> call, Response<List<list_kursi>> response) {
                listkursi = response.body();
                adapter = new ad_kursi(listkursi, kursi.this, appendBooked);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<list_kursi>> call, Throwable t) {

                Toast.makeText(kursi.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
//        room = new Room(this,tableLayout, titleTextView, ROW, COL, filmDatabase.getSeats(showId));
//
    }

    @Override
    public void onItemChangeListener(int position, list_kursi model) {
        try {
            listkursi.set(position, model);

        } catch (Exception e) {

        }
    }

    private void createbooking(String type) {
        apiInterface = UtilsApi.getAPIService();

        Call<List<list_kursi>> call = apiInterface.getBooking(type);
        call.enqueue(new Callback<List<list_kursi>>() {
            @Override
            public void onResponse(Call<List<list_kursi>> call, Response<List<list_kursi>> response) {
                listkursi = response.body();
                appendBooked = new  StringBuilder();
                for (int i=0; i<listkursi.size(); i++) {
                    bookingan = new ArrayList<>();
                    bookingan.add(listkursi.get(i).getS_kursi());

                    appendBooked.append(listkursi.get(i).getS_kursi());
                    appendBooked.append(" ");

                }
                adapter = new ad_kursi(listkursi, kursi.this, appendBooked);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<list_kursi>> call, Throwable t) {

                Toast.makeText(kursi.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
//        room = new Room(this,tableLayout, titleTextView, ROW, COL, filmDatabase.getSeats(showId));
//
    }

}
