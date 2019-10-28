package com.example.oke.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oke.R;
import com.example.oke.activity.icash;
import com.example.oke.adapter.ad_beranda;
import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.library.format_idr;
import com.example.oke.model.Constant;
import com.example.oke.model.list_film;
import com.example.oke.model.saldo_icash;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class beranda extends Fragment {
    private FragmentTabHost mTabHost;
    saldo_icash saldo;
    private Button btnEdit;
    private BaseApiService apiInterface;
    SharedPrefManager sharedPrefManager;
    private EditText searchView;
    private List<list_film> listfilm;
    private ad_beranda adapter;
    public beranda() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        sharedPrefManager = new SharedPrefManager(getActivity());

//        for (int i=0; i<listfilm.size(); i++) {
//            filmarray = new ArrayList<>();
//            filmarray.add(listfilm.get(i).getJudul_film());
//        }
//        listfilm = response.body();
//        adapter = new ad_beranda(listfilm, getActivity());

        mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("play").setIndicator("playing now"), play.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("coming").setIndicator("Coming soon"), coming.class, null);

        btnEdit = (Button) view.findViewById(R.id.saldo);
        searchView = (EditText) view.findViewById(R.id.cari);

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

//        searchView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                //after the change calling the method and passing the search input
//                filter(editable.toString());
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

//    private void filter(String text) {
//        //new array list that will hold the filtered data
//        ArrayList<list_film> filterdNames = new ArrayList<>();
//
//        //looping through existing elements
//        for (list_film s :listfilm) {
//            //if the existing elements contains the search input
//            if (s.getJudul_film().contains(text.toLowerCase())) {
//                //adding the element to filtered list
//                filterdNames.add(s);
//            }
//        }
//
//        //calling a method of the adapter class and passing the filtered list
//        adapter.filterList(filterdNames);
//    }
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
