package com.example.jandi.bolaapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class bolaAdapter extends RecyclerView.Adapter<bolaAdapter.BolaViewHolder> {


    private ArrayList<modelBola> dataList;
    private Context context;

    public bolaAdapter(ArrayList<modelBola> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public BolaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_pertandingan, parent, false);
        return new BolaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BolaViewHolder holder, final int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, detailPertandingan.class);
                intent.putExtra("ID", dataList.get(position).getID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class BolaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama;
        private LinearLayout linearLayout;

        public BolaViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.judulpertandingan);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
