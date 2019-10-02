package com.example.oke.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oke.R;
import com.example.oke.apihelper.SharedPrefManager;
import com.example.oke.apihelper.api.BaseApiService;
import com.example.oke.apihelper.api.UtilsApi;
import com.example.oke.fragment.profil;
import com.example.oke.library.FileUtils2;
import com.example.oke.validate;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cyd.awesome.material.AwesomeText;
import cyd.awesome.material.FontCharacterMaps;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    TextView id_user, passwordlama;
    Button btnEdit;
    ImageButton btnKamera;
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    AwesomeText ImgShowHidePassword;
    boolean pwd_status = true;
    ImageView photo;

    // Profil File
    Uri contentURI;
    String namaFile;

    private int GALLERY = 1, CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        sharedPrefManager = new SharedPrefManager(this);
        final profil profilFragment = new profil();
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        requestMultiplePermissions();

        id_user = (TextView) findViewById(R.id.id_user);
        nama = (EditText) findViewById(R.id.tnama);
        email = (EditText) findViewById(R.id.temail);
        alamat = (EditText) findViewById(R.id.talamat);
        no_tlp = (EditText) findViewById(R.id.tnotelp);
        password = (EditText) findViewById(R.id.tpass);
        passwordlama = (TextView) findViewById(R.id.password);
        btnEdit = (Button) findViewById(R.id.b_edit);
        btnKamera = (ImageButton) findViewById(R.id.kamera);
        photo = (ImageView) findViewById(R.id.photo);

        btnKamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

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

        id_user.setText(sharedPrefManager.getSPId(SharedPrefManager.SP_ID, ""));
        nama.setText(sharedPrefManager.getSPNama(SharedPrefManager.SP_NAMA, ""));
        email.setText(sharedPrefManager.getSpEmail(SharedPrefManager.SP_EMAIL, ""));
        alamat.setText(sharedPrefManager.getSpAlamat(SharedPrefManager.SP_ALAMAT, ""));
        no_tlp.setText(sharedPrefManager.getSpNoTlp(SharedPrefManager.SP_NO_TLP, ""));
        passwordlama.setText(sharedPrefManager.getSpPass(SharedPrefManager.SP_PASS, ""));

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

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                contentURI = data.getData();
                try {
                    namaFile = FileUtils2.getRealPath(edit_profil.this, contentURI);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
//                    String path = saveImage(bitmap);
//                    Toast.makeText(edit_profil.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    photo.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(edit_profil.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            contentURI = data.getData();
            namaFile = FileUtils2.getRealPath(edit_profil.this, contentURI);

            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            photo.setImageBitmap(thumbnail);
//            saveImage(thumbnail);
//            Toast.makeText(edit_profil.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

//    public String saveImage(Bitmap myBitmap) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//        return "";
//    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
//                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void requestEdit() {

        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

//        if (namaFile != null ) {
            File filePhoto = new File(namaFile);
//        }

        RequestBody id_User = RequestBody.create(MediaType.parse("multipart/form-data"), id_user.getText().toString());
        RequestBody namaUser = RequestBody.create(MediaType.parse("multipart/form-data"), nama.getText().toString());
        RequestBody emailUser = RequestBody.create(MediaType.parse("multipart/form-data"), email.getText().toString());
        RequestBody alamatUser = RequestBody.create(MediaType.parse("multipart/form-data"), alamat.getText().toString());
        RequestBody no_tlpUser = RequestBody.create(MediaType.parse("multipart/form-data"), no_tlp.getText().toString());
        RequestBody passwordUser = RequestBody.create(MediaType.parse("multipart/form-data"), password.getText().toString());
        RequestBody photoUser = RequestBody.create(MediaType.parse("multipart/form-data"), filePhoto);
        MultipartBody.Part profilUser = MultipartBody.Part.createFormData("photo", filePhoto.getName(), photoUser);

        mApiService.editProfil(
                id_User,
                namaUser,
                emailUser,
                alamatUser,
                no_tlpUser,
                passwordUser
                ,
                profilUser
        )
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

//    private void requestEdit() {
//
//        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
//        mApiService.editRequest(
//                id_user.getText().toString(),
//                nama.getText().toString(),
//                email.getText().toString(),
//                alamat.getText().toString(),
//                no_tlp.getText().toString(),
//                password.getText().toString(),
//                photo.toString()
//        )
//
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if (response.isSuccessful()) {
//                            Log.i("debug", "onResponse: BERHASIL");
//                            loading.dismiss();
//                            try {
//                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
//                                if (jsonRESULTS.getString("error").equals("false")) {
//                                    Toast.makeText(mContext, "BERHASIL UPDATE", Toast.LENGTH_SHORT).show();
//                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
//                                    startActivity(new Intent(mContext, login.class)
//                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                                    finish();
//                                    finish();
//                                } else {
//                                    String error_message = jsonRESULTS.getString("error_msg");
//                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        } else {
//                            Log.i("debug", "onResponse: GA BERHASIL");
//                            loading.dismiss();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
//                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

    private boolean validasi() {
        if (!validate.cek(nama)
                && !validate.cek(email)
                && !validate.cek(alamat)
                && !validate.cek(no_tlp)
                && !validate.cek(password)
            ) {
            if (validate.cekPassword(password, passwordlama.getText().toString(), md5Java(password.getText().toString()))) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static String md5Java(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            //merubah byte array ke dalam String Hexadecimal
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(edit_profil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(edit_profil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digest;
    }
}
