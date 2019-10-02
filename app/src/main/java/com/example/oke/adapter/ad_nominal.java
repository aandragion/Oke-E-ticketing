package com.example.oke.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oke.R;
import com.example.oke.activity.topup;
import com.example.oke.bank;
import com.example.oke.model.Constant;
import com.example.oke.model.nominal;

import java.util.List;

public class ad_nominal extends
        RecyclerView.Adapter<ad_nominal.ViewHolder>{
    private final Context context;
    private final List<nominal> list;
    private final topup mAdapterCallback;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nominal_item,
                parent, false);
        return new ViewHolder(view);
    }

    public ad_nominal(Context context, List<nominal> list, topup adapterCallback) {
        this.context = context;
        this.list = list;
        this.mAdapterCallback = adapterCallback;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        nominal item = list.get(position);

        String jumlah = item.getJumlah();

        holder.jml_nominal.setText(jumlah);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //memanggil detailfilm
                String jml = list.get(position).getJumlah();


                Intent intent = new Intent(v.getContext(), bank.class);
                intent.putExtra(Constant.KEY_NOMINAL, jml);

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView jml_nominal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            jml_nominal = itemView.findViewById(R.id.nominal);
        }
    }
}
