package com.example.oke.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.oke.R;
import com.example.oke.model.Constant;
import com.example.oke.activity.detailFilm;
import com.example.oke.model.list_film;

import java.util.ArrayList;
import java.util.List;

public class ad_beranda extends RecyclerView.Adapter<ad_beranda.MyViewHolder> {

    View v;
    private List<list_film> daftarfilm;
    private ArrayList<String> filmarray;
    private List<list_film> contactList;
    Context context;


    public ad_beranda(List<list_film> contacts, Context context) {
        this.daftarfilm = contacts;
        this.context = context;
        this.contactList= contacts;
    }
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    daftarfilm = contactList;
//                } else {
//                    List<list_film> filteredList = new ArrayList<>();
//                    for (list_film row : contactList) {
//
//                        // name match condition. this might differ depending on your requirement
//                        // here we are looking for name or phone number match
//                        if (row.getJudul_film().toLowerCase().contains(charString.toLowerCase()) || row.getRilis().contains(charSequence)) {
//                            filteredList.add(row);
//                        }
//                    }
//
//                    daftarfilm = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = daftarfilm;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                daftarfilm = (ArrayList<list_film>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridberanda, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.judul.setText(daftarfilm.get(position).getJudul_film());

        String fileName = daftarfilm.get(position).getGambar();
        String fullUrlImage = "http://192.168.8.109/admin/upload/gbrfilm/" + fileName;

        Glide.with(holder.itemView.getContext())
                .load(fullUrlImage)
                .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(7)))
                .into(holder.gambar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //memanggil detailfilm
                String id = daftarfilm.get(position).getId();
                String judulFilm = daftarfilm.get(position).getJudul_film();
                String gambar = daftarfilm.get(position).getGambar();
                String sinopsis = daftarfilm.get(position).getSinopsis();
                String trailer = daftarfilm.get(position).getTrailer();
                String genre = daftarfilm.get(position).getId_genre();
                String status = daftarfilm.get(position).getStatus_film();
                String total = daftarfilm.get(position).getTotal_view();
                String durasi = daftarfilm.get(position).getDurasi();
                String rilis = daftarfilm.get(position).getRilis();

                Intent intent = new Intent(v.getContext(), detailFilm.class);
                intent.putExtra(Constant.KEY_ID_FILM, id);
                intent.putExtra(Constant.KEY_JUDUL_FILM, judulFilm);
                intent.putExtra(Constant.KEY_GAMBAR, gambar);
                intent.putExtra(Constant.KEY_SINOPSIS, sinopsis);
                intent.putExtra(Constant.KEY_TRAILER, trailer);
                intent.putExtra(Constant.KEY_GENRE, genre);
                intent.putExtra(Constant.KEY_STATUS, status);
                intent.putExtra(Constant.KEY_TOTAL, total);
                intent.putExtra(Constant.KEY_DURASI, durasi);
                intent.putExtra(Constant.KEY_RILIS, rilis);
                v.getContext().startActivity(intent);
            }
        });

    }

    private list_film getItem(int position) {
        return daftarfilm.get(position);
    }

    @Override
    public int getItemCount() {
        return daftarfilm.size();
    }


//    public Filter getFilter() {
//        return new Filter() {
//            public List<list_film> contactList;
//            public List<list_film> daftarfilm;
//
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    daftarfilm = contactList;
//                } else {
//                    List<list_film> filteredList = new ArrayList<>();
//                    for (list_film row : contactList) {
//
//                        // name match condition. this might differ depending on your requirement
//                        // here we are looking for name or phone number match
//                        if (row.getJudul_film().toLowerCase().contains(charString.toLowerCase()) || row.getRilis().contains(charSequence)) {
//                            filteredList.add(row);
//                        }
//                    }
//
//                    daftarfilm = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = daftarfilm;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                daftarfilm = (ArrayList<list_film>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView judul;
        ImageView gambar;

        public MyViewHolder(View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.gambar);
            judul = itemView.findViewById(R.id.judul);
        }
    }

    //This method will filter the list
    //here we are passing the filtered data
    //and assigning it to the list with notifydatasetchanged method

//    public void filterList(ArrayList<list_film> filterdNames) {
//        this.daftarfilm = filterdNames;
//        notifyDataSetChanged();
//    }
}
