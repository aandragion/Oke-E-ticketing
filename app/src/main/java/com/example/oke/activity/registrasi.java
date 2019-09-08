package com.example.oke.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oke.R;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cyd.awesome.material.AwesomeText;
import cyd.awesome.material.FontCharacterMaps;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registrasi extends AppCompatActivity {
    EditText etNama,
            etEmail,
            etalamat,
            etno_tlp,
            EdPassword,
            EdRPassword;
    AwesomeText ImgShowHidePassword, ImgShowHidePassword1 ;
    TextView ConfirmPass;
    Button btnRegister;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;
    boolean pwd_status = true, passwordSame, pwd_status1 = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        etNama      = (EditText) findViewById(R.id.v_nama);
        etEmail     = (EditText) findViewById(R.id.t_email);
        etalamat    = (EditText) findViewById(R.id.t_alamat);
        etno_tlp    = (EditText) findViewById(R.id.t_tlp);
        EdPassword  = (EditText) findViewById(R.id.t_pass);
        EdRPassword = (EditText) findViewById(R.id.t_rpass);
        btnRegister = (Button)   findViewById(R.id.b_daftar);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                       case R.id.b_daftar:
                            if(validasi())
                           requestRegister();
                            break;
                }
            }
        });

        ImgShowHidePassword = (AwesomeText) findViewById(R.id.ImgShowPassword);
        ImgShowHidePassword1 = (AwesomeText) findViewById(R.id.ImgShowPassword1);

        ImgShowHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pwd_status) {
                    EdPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pwd_status = false;
                    ImgShowHidePassword.setMaterialDesignIcon(FontCharacterMaps.MaterialDesign.MD_VISIBILITY);
                    EdPassword.setSelection(EdPassword.length());
                } else {
                    EdPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    pwd_status = true;
                    ImgShowHidePassword.setMaterialDesignIcon(FontCharacterMaps.MaterialDesign.MD_VISIBILITY_OFF);
                    EdPassword.setSelection(EdPassword.length());
                }
            }
        });

        ImgShowHidePassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pwd_status1) {
                    EdRPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pwd_status1 = false;
                    ImgShowHidePassword1.setMaterialDesignIcon(FontCharacterMaps.MaterialDesign.MD_VISIBILITY);
                    EdRPassword.setSelection(EdRPassword.length());
                } else {
                    EdRPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    pwd_status1 = true;
                    ImgShowHidePassword1.setMaterialDesignIcon(FontCharacterMaps.MaterialDesign.MD_VISIBILITY_OFF);
                    EdRPassword.setSelection(EdRPassword.length());
                }
            }
        });
    }

    private boolean validasi() {
        if (!validate.cek(etNama)
                &&!validate.cek(etEmail)
                &&!validate.cek(etalamat)
                &&!validate.cek(etno_tlp)
                &&!validate.cek(EdPassword)
                &&!validate.cek(EdRPassword)) {
            if (validate.cekPassword(EdRPassword,EdPassword.getText().toString(),EdRPassword.getText().toString())){
                return false;
            }else{
                return true;
            }
        } else{ return false; }
    }

    private void requestRegister() {

        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
        mApiService.registerRequest(
                etNama.getText().toString(),
                etEmail.getText().toString(),
                etalamat.getText().toString(),
                etno_tlp.getText().toString(),
                EdPassword.getText().toString(),
                EdRPassword.getText().toString())

                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.i("debug", "onResponse: BERHASIL");
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")) {
                                    Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mContext, login.class));
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

    public void Login(View view) {
        Intent intent = new Intent(registrasi.this, login.class);
        startActivity(intent);
    }
    
}
