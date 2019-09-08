package com.example.oke;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oke.activity.MainActivity;
import com.example.oke.activity.login;
import com.example.oke.apihelper.SharedPrefManager;

import java.util.HashMap;


public class profil extends Fragment {
    private ImageButton btnClickMe;
    SharedPrefManager sharedPrefManager;
    private TextView nama, email, alamat, no_telp;
    private Button edit;
    private String id;
    public profil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        btnClickMe = (ImageButton) view.findViewById(R.id.b_logout2);
        viewItems(view);
//        nama = (TextView) view.findViewById(R.id.nama);
        sharedPrefManager = new SharedPrefManager(getActivity());
        //logout
        btnClickMe.setOnClickListener(new View.OnClickListener() {
        public void onClick (View v){
            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
            getActivity().startActivity(new Intent(getActivity(), login.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            getActivity().finish();
        }
    });

        nama.setText(sharedPrefManager.getSPNama(SharedPrefManager.SP_NAMA,""));
        email.setText(sharedPrefManager.getSpEmail(SharedPrefManager.SP_EMAIL,""));
        alamat.setText(sharedPrefManager.getSpAlamat(SharedPrefManager.SP_ALAMAT,""));
        no_telp.setText(sharedPrefManager.getSpNoTlp(SharedPrefManager.SP_NO_TLP,""));
//        HashMap<String, String> map = sharedPrefManager.getDetailProfil();
//        nama.setText(map.get(sharedPrefManager.SP_NAMA));
//        email.setText(map.get(sharedPrefManager.SP_EMAIL));
//        id = map.get(sharedPrefManager.SP_NAMA);

        return view;
    }
    private void viewItems(View view) {
        nama = view.findViewById(R.id.nama);
        email = view.findViewById(R.id.email);
        alamat = view.findViewById(R.id.alamat);
        no_telp = view.findViewById(R.id.no_telp);
        edit = view.findViewById(R.id.bt_edit);

    }
}
