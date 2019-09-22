package com.example.oke.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oke.R;
import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.fragment.profil;
import com.example.oke.validate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cyd.awesome.material.AwesomeText;
import cyd.awesome.material.FontCharacterMaps;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_profil extends AppCompatActivity {
    EditText nama,
            email,
            alamat,
            no_tlp,
            password;
    TextView id_user,passwordlama;
    Button btnEdit;
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    AwesomeText ImgShowHidePassword;
    boolean pwd_status = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        sharedPrefManager = new SharedPrefManager(this);
        final profil profilFragment = new profil();
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        id_user = (TextView) findViewById(R.id.id_user);
        nama = (EditText) findViewById(R.id.tnama);
        email = (EditText) findViewById(R.id.temail);
        alamat = (EditText) findViewById(R.id.talamat);
        no_tlp = (EditText) findViewById(R.id.tnotelp);
        password = (EditText) findViewById(R.id.tpass);
        passwordlama= (TextView) findViewById(R.id.password);
        btnEdit = (Button) findViewById(R.id.b_edit);

        ImgShowHidePassword = (AwesomeText) findViewById(R.id.ShowPassword);

        ImgShowHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pwd_status) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pwd_status = false;
                    ImgShowHidePassword.setMaterialDesignIcon(FontCharacterMaps.MaterialDesign.MD_VISIBILITY);
                    password.setSelection(password.length());
                } else {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    pwd_status = true;
                    ImgShowHidePassword.setMaterialDesignIcon(FontCharacterMaps.MaterialDesign.MD_VISIBILITY_OFF);
                    password.setSelection(password.length());
                }
            }
        });

        id_user.setText(sharedPrefManager.getSPId(SharedPrefManager.SP_ID,""));
        nama.setText(sharedPrefManager.getSPNama(SharedPrefManager.SP_NAMA,""));
        email.setText(sharedPrefManager.getSpEmail(SharedPrefManager.SP_EMAIL,""));
        alamat.setText(sharedPrefManager.getSpAlamat(SharedPrefManager.SP_ALAMAT,""));
        no_tlp.setText(sharedPrefManager.getSpNoTlp(SharedPrefManager.SP_NO_TLP,""));
        passwordlama.setText(sharedPrefManager.getSpPass(SharedPrefManager.SP_PASS,""));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.b_edit:
                        if (validasi())
                            requestEdit();
                        break;
                }
            }
        });
    }

    private void requestEdit() {

        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
        mApiService.editRequest(
                id_user.getText().toString(),
                nama.getText().toString(),
                email.getText().toString(),
                alamat.getText().toString(),
                no_tlp.getText().toString(),
                password.getText().toString())

                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.i("debug", "onResponse: BERHASIL");
                            loading.dismiss();
                            try {
                                 JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")) {
                                    Toast.makeText(mContext, "BERHASIL UPDATE", Toast.LENGTH_SHORT).show();
                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                                    startActivity(new Intent(mContext, login.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();
                                    finish();
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: GA BERHASIL");
                            loading.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean validasi() {
        if (!validate.cek(nama)
                && !validate.cek(email)
                && !validate.cek(alamat)
                && !validate.cek(no_tlp)
                && !validate.cek(password)) {
            if (validate.cekPassword(password, passwordlama.getText().toString(), md5Java(password.getText().toString()))) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public static String md5Java(String message)
    {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            //merubah byte array ke dalam String Hexadecimal
            StringBuilder sb = new StringBuilder(2*hash.length);
            for(byte b : hash)
            {
                sb.append(String.format("%02x", b&0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException ex)
        {
            Logger.getLogger(edit_profil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(edit_profil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digest;
    }
}
