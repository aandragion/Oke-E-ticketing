package com.example.oke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.oke.adapter.ad_jadwal;
import com.example.oke.adapter.ad_jam;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.model.Constant;
import com.example.oke.model.list_jadwal;
import com.example.oke.model.list_jam;
import com.example.oke.model.list_studio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class pesan extends AppCompatActivity  implements ad_jadwal.ChnageStatusListener{
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView recycler;
    Spinner Listspinner;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layout;
    private BaseApiService apiInterface;
    ad_jadwal adapter;
    ad_jam adapterjam;
    ImageView gambar;
    private String mId, mjudulfilm, mgambar, msinopsis, mtrailer, mgenre, mdurasi, mrilis, mstatus;
    private List<list_jadwal> listjadwal;
    private List<list_jam> listjam;
    List<list_studio> liststudio;
    TextView jumlah;
    Button plus, min, lanjud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        gambar = (ImageView) findViewById(R.id.gbr);
//        jumlah = (TextView) findViewById(R.id.jumlah);
//        plus = (Button) findViewById(R.id.buttonplus);
//        min = (Button) findViewById(R.id.buttonmin);
//        lanjud = (Button) findViewById(R.id.lanjut);
        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_FILM);
        mgambar = intent.getStringExtra(Constant.KEY_GAMBAR);
        mjudulfilm = intent.getStringExtra(Constant.KEY_JUDUL_FILM);

//                lanjud.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(pesan.this, kursi.class));
//
//            }
//        });

//        final int[] i = {0};
//        plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                i[0] = i[0] + 1;
//                jumlah.setText("" + i[0]);
//
//            }
//        });
//        min.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                i[0] = i[0] - 1;
//
//                jumlah.setText("" + i[0]);
//
//            }
//        });
        String fullUrlImage = "http://192.168.8.109/admin/upload/gbrfilm/" + mgambar;

        Glide.with(pesan.this)
                .load(fullUrlImage)
                .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(7)))
                .into(gambar);

        recyclerView = findViewById(R.id.listjadwal);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchContact("" + mId);



//        recycler = findViewById(R.id.listjam);
//        layout = new LinearLayoutManager(pesan.this, LinearLayoutManager.HORIZONTAL, false);
//        recycler.setLayoutManager(layout);
//        recycler.setHasFixedSize(true);
//        jam("" + mId);
//
//        Listspinner = findViewById(R.id.spinner);
//        studio("" + mId);

    }

    private void fetchContact(String type) {
        apiInterface = UtilsApi.getAPIService();

        Call<List<list_jadwal>> call = apiInterface.getJadwal(type);
        call.enqueue(new Callback<List<list_jadwal>>() {
            @Override
            public void onResponse(Call<List<list_jadwal>> call, Response<List<list_jadwal>> response) {
                listjadwal = response.body();
                adapter = new ad_jadwal(listjadwal, pesan.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<list_jadwal>> call, Throwable t) {

                Toast.makeText(pesan.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemChangeListener(int position, list_jadwal model) {
        try{
            listjadwal.set(position,model);

        }catch (Exception e){

        }
    }


//    private void jam(String jam) {
//
//        apiInterface = UtilsApi.getAPIService();
//
//        Call<List<list_jam>> call = apiInterface.getJam(jam);
//        call.enqueue(new Callback<List<list_jam>>() {
//            @Override
//            public void onResponse(Call<List<list_jam>> call, Response<List<list_jam>> response) {
//                listjam = response.body();
//                adapterjam = new ad_jam(listjam, pesan.this);
//                recycler.setAdapter(adapterjam);
//                adapterjam.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onFailure(Call<List<list_jam>> call, Throwable t) {
////                progressBar.setVisibility(View.GONE);
//                Toast.makeText(pesan.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    private void studio(String type) {
//        apiInterface = UtilsApi.getAPIService();
//
//        Call<List<list_studio>> call = apiInterface.getStudio(type);
//        call.enqueue(new Callback<List<list_studio>>() {
//            @Override
//            public void onResponse(Call<List<list_studio>> call, Response<List<list_studio>> response) {
////                liststudio = response.body();
////                String[] item = new String[]{
////                        "" + liststudio.getId_studio()
////                };
//                List<list_studio> studioitem = response.body();
//                List<String> listSpinner = new ArrayList<String>();
//                for (int i = 0; i < studioitem.size(); i++) {
//                    listSpinner.add(studioitem.get(i).getId_studio());
//                }
////                Listspinner.setText(""+liststudio.ge)
//                final ArrayAdapter<String> adapterspinner = new ArrayAdapter<>(pesan.this,
//                        android.R.layout.simple_spinner_item, listSpinner);
//                adapterspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                Listspinner.setAdapter(adapterspinner);
//                adapterspinner.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<list_studio>> call, Throwable t) {
//
//                Toast.makeText(pesan.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}
