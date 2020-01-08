package com.example.oke;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oke.library.format_idr;
import com.example.oke.model.Constant;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class bukti_pesan extends AppCompatActivity {
    private String mid, mid_user, mtanggal_pesan, mid_film, mjudul, mgambar, mid_jadwal, mtgl_jadwal, mjam_ayang, mharga, mstudio, mkursi, mjumlah_pesanan, mtotal_harga, mstatus_pesanan;
    private TextView id, id_user, tanggal_pesan, id_film, judul, gambar, id_jadwal, tgl_jadwal, jam_ayang, harga, studio, kursi, jumlah_pesanan, total_harga, status_pesanan;
    ImageView qrcode;
    Button detailtras, rating, save;
    Bitmap bitmap ;
    RelativeLayout l_print;
    private Handler mHandler;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bukti_pesan);

//        this.mHandler = new Handler();

//        this.mHandler.postDelayed(m_Runnable,5000);

//        doTheAutoRefresh();



        Intent intent = getIntent();
        mid = intent.getStringExtra(Constant.KEY_ID_PESAN);
        mid_user = intent.getStringExtra(Constant.KEY_JUDUL_FILM);
        mtanggal_pesan = intent.getStringExtra(Constant.KEY_TGL_PSN);
        mid_film = intent.getStringExtra(Constant.KEY_ID_FILM);
        mjudul = intent.getStringExtra(Constant.KEY_JUDUL_FILM);
        mgambar = intent.getStringExtra(Constant.KEY_GAMBAR);
        mid_jadwal = intent.getStringExtra(Constant.KEY_ID_JWL);
        mtgl_jadwal = intent.getStringExtra(Constant.KEY_TGL);
        mjam_ayang = intent.getStringExtra(Constant.KEY_JM);
        mharga = intent.getStringExtra(Constant.KEY_HARGA);
        mstudio = intent.getStringExtra(Constant.KEY_STUDIO);
        mkursi = intent.getStringExtra(Constant.KEY_KURSI);
        mjumlah_pesanan = intent.getStringExtra(Constant.KEY_JML_PSN);
        mtotal_harga = intent.getStringExtra(Constant.KEY_TOTAL_PSN);
        mstatus_pesanan = intent.getStringExtra(Constant.KEY_STATUS_PSN);

        detailtras =(Button) findViewById(R.id.detailtras);
        rating =(Button) findViewById(R.id.rating);
        save =(Button) findViewById(R.id.save);
        l_print =(RelativeLayout) findViewById(R.id.l_print);
        qrcode = (ImageView) findViewById(R.id.qr_bukti);
        tanggal_pesan = (TextView) findViewById(R.id.tgl_bukti);
        judul = (TextView) findViewById(R.id.nm_film_bukti);
        tgl_jadwal = (TextView) findViewById(R.id.tgl_nonton);
        jam_ayang = (TextView) findViewById(R.id.jam_bukti);
        harga = (TextView) findViewById(R.id.hrg_psan_bukti);
        studio = (TextView) findViewById(R.id.studio_psn_bukti);
        kursi = (TextView) findViewById(R.id.t_duduk_bukti);
        jumlah_pesanan = (TextView) findViewById(R.id.jml_psn_bukti);
        total_harga = (TextView) findViewById(R.id.total_bukti);
        status_pesanan = (TextView) findViewById(R.id.status_bukti);

        if( mstatus_pesanan.equals( "Proses" )  ) {
            detailtras.setVisibility(View.VISIBLE);
        }

        if( mstatus_pesanan.equals( "Selesai" )  ) {
            rating.setVisibility(View.VISIBLE);
        }

        if( mstatus_pesanan.equals( "Berhasil" )  ) {
            save.setVisibility(View.VISIBLE);
        }

        if( mstatus_pesanan.equals( "Scan" )  ) {
            Toast.makeText(bukti_pesan.this, "berhasil", Toast.LENGTH_LONG).show();
        }

        detailtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //memanggil detailfilm
//                String jml = list.get(position).getJumlah();

                Intent intent = new Intent(v.getContext(), dtrans.class);
                intent.putExtra("id_pesan", mid);
                v.getContext().startActivity(intent);
            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //memanggil detailfilm
//                String jml = list.get(position).getJumlah();

                Intent intent = new Intent(v.getContext(), rating.class);
                intent.putExtra("judul", mjudul);
                intent.putExtra("id", mid_film);
                intent.putExtra("gambar", mgambar);
                v.getContext().startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Log.d("size"," "+l_print.getWidth() +"  "+l_print.getWidth());
                bitmap = loadBitmapFromView(l_print, l_print.getWidth(), l_print.getHeight());
                createPdf();
            }
        });


        tanggal_pesan.setText(mtanggal_pesan);
        judul.setText(mjudul);
        tgl_jadwal.setText(mtgl_jadwal);
        jam_ayang.setText(mjam_ayang+" WIB");
        harga.setText(format_idr.toRupiah(mharga));
        studio.setText(mstudio);
        kursi.setText(mkursi);
        jumlah_pesanan.setText(mjumlah_pesanan);
        total_harga.setText(format_idr.toRupiah(mtotal_harga));
        status_pesanan.setText(mstatus_pesanan);

        try {
            bitmap = textToImage(""+mid,700,700);
            if (bitmap!= null) {
                qrcode.setImageBitmap(bitmap);
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String targetPdf = "/sdcard/Bukti_pembayaran_"+""+mid +".pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF of Scroll is created!!!", Toast.LENGTH_SHORT).show();

        openGeneratedPDF();
//        sharePdf();
    }

    private void openGeneratedPDF(){
        File file = new File("/sdcard/Bukti_pembayaran_"+""+mid +".pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(bukti_pesan.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void sharePdf() {
        File file = new File("/sdcard/Bukti_pembayaran_"+""+mid +".pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_SEND);
            Uri uri = Uri.fromFile(file);
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.setType("application/pdf");
//            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(bukti_pesan.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }

    private Bitmap textToImage(String text, int width, int height) throws WriterException, NullPointerException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.DATA_MATRIX.QR_CODE,
                    width, height, null);
        } catch (IllegalArgumentException Illegalargumentexception) {
            return null;
        }

        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        int colorWhite = 0xFFFFFFFF;
        int colorBlack = 0xFF000000;

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? colorBlack : colorWhite;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, width, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

//    private final Runnable m_Runnable = new Runnable()
//    {
//        public void run()
//
//        {
//            Toast.makeText(bukti_pesan.this,"in runnable",Toast.LENGTH_SHORT).show();
//
//            bukti_pesan.this.mHandler.postDelayed(m_Runnable, 5000);
//        }
//
//    };

//    private void doTheAutoRefresh() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Write code for your refresh logic
//                doTheAutoRefresh();
//            }
//        }, 5000);
//    }
}
