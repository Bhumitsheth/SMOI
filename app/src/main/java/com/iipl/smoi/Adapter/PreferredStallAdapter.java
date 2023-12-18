package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iipl.smoi.Model.OtherModel.PreferredStallModel;
import com.iipl.smoi.Model.OtherModel.PreferredStallModel;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;

public class PreferredStallAdapter extends RecyclerView.Adapter<PreferredStallAdapter.mydataviewholder> {

    Context context;
    ArrayList<String> preferredStallModelArrayList;
    private OnItemClickListener onItemClickListener;

    public PreferredStallAdapter(Context context, ArrayList<String> preferredStallModelArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.preferredStallModelArrayList = preferredStallModelArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public PreferredStallAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_stall_list, parent, false);
        return new mydataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

        String preferred_stall_no = preferredStallModelArrayList.get(position);

        holder.tv_preferred_stall_no.setText(preferred_stall_no);

        holder.tv_preferred_stall_no.setOnClickListener(view -> {
            onItemClickListener.onItemClick(position);
        });

    }

    @Override
    public int getItemCount() {
        return preferredStallModelArrayList == null ? 0 : preferredStallModelArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        TextView tv_preferred_stall_no;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);
            View view = itemView;
            tv_preferred_stall_no = (TextView) view.findViewById(R.id.tv_preferred_stall_no);
        }
    }

    public void updateList(ArrayList<String> preferredStallModelArrayList) {
        this.preferredStallModelArrayList = preferredStallModelArrayList;
        notifyDataSetChanged();
    }






}



