package com.example.oke.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.oke.R;
import com.example.oke.activity.icash;
import com.example.oke.adapter.ad_beranda;
import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.library.format_idr;
import com.example.oke.model.Constant;
import com.example.oke.model.saldo_icash;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class beranda extends Fragment {
    private FragmentTabHost mTabHost;
    saldo_icash saldo;
    private Button btnEdit;
    private BaseApiService apiInterface;
    SharedPrefManager sharedPrefManager;
    private SearchView searchView;
    public beranda() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        sharedPrefManager = new SharedPrefManager(getActivity());
        mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("play").setIndicator("playing now"), play.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("coming").setIndicator("Coming soon"), coming.class, null);
        btnEdit = (Button) view.findViewById(R.id.saldo);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = saldo.getId();
                String icash = saldo.getSaldo_icash();

                Intent intent = new Intent(v.getContext(), icash.class);

                intent.putExtra(Constant.KEY_ID_ICASH, id);
                intent.putExtra(Constant.KEY_SALDO, icash);


                v.getContext().startActivity(intent);
            }
        });

//        btnEdit.setText( sharedPrefManager.getSPNama(SharedPrefManager.SP_NAMA,""));
        fetchContact("" + sharedPrefManager.getSPId(SharedPrefManager.SP_ID, ""));

//        // Associate searchable configuration with the SearchView
////        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        searchView = (SearchView) view.findViewById(R.id.cari);
////        searchView.setSearchableInfo(searchManager
////                .getSearchableInfo(getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//        // listening to search query text change
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // filter recycler view when query submitted
//                ad_beranda.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                // filter recycler view when text is changed
//                ad_beranda.getFilter().filter(query);
//                return false;
//            }
//        });


        return view;
    }

    private void fetchContact(String type) {
        apiInterface = UtilsApi.getAPIService();

        Call<saldo_icash> call = apiInterface.getsaldo(type);
        call.enqueue(new Callback<saldo_icash>() {

            @Override
            public void onResponse(Call<saldo_icash> call, Response<saldo_icash> response) {
                saldo = response.body();
                btnEdit.setText(format_idr.toRupiah("" + saldo.getSaldo_icash()));

            }

            @Override
            public void onFailure(Call<saldo_icash> call, Throwable t) {
                Toast.makeText(getActivity(), "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

//    private String toRupiah(String nominal){
//        String hasil = "";
//
//        DecimalFormat toRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
//        DecimalFormatSymbols formatAngka = new DecimalFormatSymbols();
//
//        formatAngka.setCurrencySymbol("Rp. ");
//        formatAngka.setMonetaryDecimalSeparator(',');
//        formatAngka.setGroupingSeparator('.');
//        toRupiah.setDecimalFormatSymbols(formatAngka);
//
//        hasil = toRupiah.format(Double.valueOf(nominal));
//
//        return hasil;
//    }

}
