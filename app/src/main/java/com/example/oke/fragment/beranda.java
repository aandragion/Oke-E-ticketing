package com.example.oke.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.oke.R;
import com.example.oke.activity.icash;
import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.model.saldo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class beranda extends Fragment {
    private FragmentTabHost mTabHost;
    saldo saldo;
    private Button btnEdit;
    private BaseApiService apiInterface;
    SharedPrefManager sharedPrefManager;
    public beranda() {
        // Required empty public constructor

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        sharedPrefManager = new SharedPrefManager(getActivity());
        mTabHost = (FragmentTabHost)view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(),getChildFragmentManager(),R.id.realtabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("play").setIndicator("playing now"),play.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("coming").setIndicator("Coming soon"),coming.class,null);
        btnEdit = (Button) view.findViewById(R.id.saldo);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                getActivity().startActivity(new Intent(getActivity(), icash.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
            }
        });

//        btnEdit.setText( sharedPrefManager.getSPNama(SharedPrefManager.SP_NAMA,""));
        fetchContact("1023");
        return view;
    }

    private void fetchContact(String type) {
        apiInterface = UtilsApi.getAPIService();

        Call<saldo> call = apiInterface.getsaldo(type);
        call.enqueue(new Callback<saldo>() {

            @Override
            public void onResponse(Call<saldo> call, Response<saldo> response) {
//                if (response.isSuccessful()){
//                    try {
//                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
//                        if (jsonRESULTS.getString("error").equals("false")){
//                            // Jika login berhasil maka data nama yang ada di response API
//                            // akan diparsing ke activity selanjutnya.
//
//                            String id_user = jsonRESULTS.getJSONObject("saldo").getString("saldo_icash");
//                            btnEdit.setText(id_user);
//                        } else {
//                            // Jika login gagal
////
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//
//                }

            }

            @Override
            public void onFailure(Call<saldo> call, Throwable t) {
                Toast.makeText(getActivity(), "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }



}
