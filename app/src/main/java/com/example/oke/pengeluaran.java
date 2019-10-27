package com.example.oke;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.oke.adapter.ad_pemasukkan;
import com.example.oke.adapter.ad_pengeluaran;
import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.model.list_pengeluaran;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pengeluaran extends Fragment {

    public pengeluaran() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private BaseApiService apiInterface;
    private List<list_pengeluaran> listpengeluaran;
    private ad_pengeluaran adapter;
    ProgressBar progressBar;
    SharedPrefManager sharedPrefManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_pengeluaran, container, false);
        sharedPrefManager = new SharedPrefManager(getActivity());

        recyclerView = view.findViewById(R.id.daf_keluar);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchContact(""+ sharedPrefManager.getSPId(SharedPrefManager.SP_ID, ""));
        return view;
    }
    private void fetchContact(String type) {
        apiInterface = UtilsApi.getAPIService();

        Call<List<list_pengeluaran>> call = apiInterface.getPengeluaran(type);
        call.enqueue(new Callback<List<list_pengeluaran>>() {
            @Override
            public void onResponse(Call<List<list_pengeluaran>> call, Response<List<list_pengeluaran>> response) {
                listpengeluaran = response.body();
                adapter = new ad_pengeluaran(listpengeluaran, getActivity());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<list_pengeluaran>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
