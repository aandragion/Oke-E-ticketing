package com.example.oke.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.oke.R;
import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.fragment.beranda;
import com.example.oke.fragment.daftar_pesan;
import com.example.oke.fragment.profil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
Button btnLogout;

    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        btnLogout    = (Button) findViewById(R.id.b_logout1);
//        sharedPrefManager = new SharedPrefManager(this);
//
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
//                startActivity(new Intent(MainActivity.this, login.class)
//                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                finish();
//            }
//        });

        //BottomNavigation
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        final beranda berandaFragment = new beranda();
        final daftar_pesan pesanFragment = new daftar_pesan();
        final profil profilFragment = new profil();


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home_menu) {
                    setFragment(berandaFragment);
                    return true;
                }else if (id == R.id.pesan_menu){
                    setFragment(pesanFragment);
                    return true;
                }else if (id == R.id.profil_menu){
                    setFragment(profilFragment);
                    return true;
                }
                return false;
            }
        });

        navigationView.setSelectedItemId(R.id.home_menu);

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}

