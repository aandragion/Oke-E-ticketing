package com.example.oke.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oke.R;
import com.example.oke.adapter.ad_beranda;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.model.list_film;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class play extends Fragment {
    public play() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layout;
    private List<list_film> listfilm;
    private ad_beranda adapter;
    private BaseApiService apiInterface;
    ProgressBar progressBar;

    TextView search;
    String[] item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);


        progressBar = view.findViewById(R.id.prograss);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchContact("film");

        progressBar = view.findViewById(R.id.prograss);
        recycler = view.findViewById(R.id.popular);
        layout = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recycler.setLayoutManager(layout);
        recycler.setHasFixedSize(true);
        popular("popular");

        return view;
    }

    private void fetchContact(String type) {
        apiInterface = UtilsApi.getAPIService();

        Call<List<list_film>> call = apiInterface.getContact(type);
        call.enqueue(new Callback<List<list_film>>() {
            @Override
            public void onResponse(Call<List<list_film>> call, Response<List<list_film>> response) {
                progressBar.setVisibility(View.GONE);
                listfilm = response.body();
                adapter = new ad_beranda(listfilm, getActivity());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<list_film>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void popular(String type) {
        apiInterface = UtilsApi.getAPIService();

        Call<List<list_film>> call = apiInterface.getPopular(type);
        call.enqueue(new Callback<List<list_film>>() {
            @Override
            public void onResponse(Call<List<list_film>> call, Response<List<list_film>> response) {
                progressBar.setVisibility(View.GONE);
                listfilm = response.body();
                adapter = new ad_beranda(listfilm, getActivity());
                recycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<list_film>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(false);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                fetchContact("users", query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                fetchContact("users", newText);
//                return false;
//            }
//        });
//        return true;
//    }

}
