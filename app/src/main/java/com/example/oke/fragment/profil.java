package com.example.oke.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.oke.R;
import com.example.oke.activity.edit_profil;
import com.example.oke.activity.login;
import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.library.load;


public class profil extends Fragment {
    private ImageButton btnClickMe;
    SharedPrefManager sharedPrefManager;
    private TextView nama, email, alamat, no_telp;
    ImageView photo;
    private Button btnEdit;
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
        btnEdit = (Button) view.findViewById(R.id.bt_edit);
        viewItems(view);

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
//        mgambar.setText(sharedPrefManager.getSpPhoto(SharedPrefManager.SP_PHOTO,""));
//        String fullUrlImage = "https://cobabioskop.000webhostapp.com/gambar/" + sharedPrefManager.getSpPhoto(SharedPrefManager.SP_PHOTO,"");
        Glide.with(getActivity())
                .load(load.gambar(sharedPrefManager.getSpPhoto(SharedPrefManager.SP_PHOTO,"")))
                .apply(RequestOptions.circleCropTransform())
                .into(photo);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), edit_profil.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
    private void viewItems(View view) {
        nama = view.findViewById(R.id.nama_rating);
        email = view.findViewById(R.id.email);
        alamat = view.findViewById(R.id.alamat);
        no_telp = view.findViewById(R.id.no_telp);
        photo = view.findViewById(R.id.photoprofil);
        btnEdit = view.findViewById(R.id.bt_edit);

    }
}
