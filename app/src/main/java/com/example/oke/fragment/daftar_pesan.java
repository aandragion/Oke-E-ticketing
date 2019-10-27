package com.example.oke.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oke.R;
import com.example.oke.adapter.ad_beranda;
import com.example.oke.adapter.ad_dpesan;
import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.model.list_dpesan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class daftar_pesan extends Fragment {

  public daftar_pesan() {
    // Required empty public constructor
  }

  private RecyclerView recyclerView;
  private RecyclerView recycler;
  private RecyclerView.LayoutManager layoutManager;
  private RecyclerView.LayoutManager layout;
  private List<list_dpesan> listdpesan;
  private ad_dpesan adapter;
  private BaseApiService apiInterface;
  ProgressBar progressBar;
  SharedPrefManager sharedPrefManager;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_daftar_pesan, container, false);

    sharedPrefManager = new SharedPrefManager(getActivity());
    recyclerView = view.findViewById(R.id.daftar_pesan);
    layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setHasFixedSize(true);
    fetchContact(""+ sharedPrefManager.getSPId(SharedPrefManager.SP_ID, ""));
    return view;
  }

  private void fetchContact(String type) {
    apiInterface = UtilsApi.getAPIService();

    Call<List<list_dpesan>> call = apiInterface.getDpesan(type);
    call.enqueue(new Callback<List<list_dpesan>>() {
      @Override
      public void onResponse(Call<List<list_dpesan>> call, Response<List<list_dpesan>> response) {
        listdpesan = response.body();
        adapter = new ad_dpesan(listdpesan, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
      }

      @Override
      public void onFailure(Call<List<list_dpesan>> call, Throwable t) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
      }
    });
  }

}