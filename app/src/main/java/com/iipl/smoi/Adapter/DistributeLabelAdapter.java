package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iipl.smoi.Model.DistributeLabelResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;

public class DistributeLabelAdapter extends RecyclerView.Adapter<DistributeLabelAdapter.mydataviewholder> {

    Context context;
    ArrayList<DistributeLabelResponse.DistributedLabel> distributedLabelArrayList;
    private OnItemClickListener onItemClickListener;

    public DistributeLabelAdapter(Context context, ArrayList<DistributeLabelResponse.DistributedLabel> distributedLabelArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.distributedLabelArrayList = distributedLabelArrayList;
        this.onItemClickListener = onItemClickListener;
    }

   /* public void filterList(ArrayList<ChapterResponse.Chapter> filterllist) {
        distributedLabelArrayList = filterllist;
        notifyDataSetChanged();
    }*/

    @Override
    public DistributeLabelAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_distribute_label, parent, false);
        return new mydataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

        String id = distributedLabelArrayList.get(position).getId();
        String series = distributedLabelArrayList.get(position).getSeriesName();
        String createdAt = distributedLabelArrayList.get(position).getCreatedAt();
        String labelFrom = distributedLabelArrayList.get(position).getDistributedLabelFrom();
        String labelTo = distributedLabelArrayList.get(position).getDistributedLabelTo();
        String quantity = distributedLabelArrayList.get(position).getQuantity();
        String distributedOn = distributedLabelArrayList.get(position).getDistributedOn();
        String distributedTo = distributedLabelArrayList.get(position).getDistributedTo();

        holder.tv_series.setText(series + "-" + labelFrom + " To " + series + "-" + labelTo);
//        holder.tv_createdat.setText();
//        holder.tv_label_from.setText(labelFrom);
//        holder.tv_label_to.setText(labelTo);
//        holder.tv_quantity.setText(quantity);
        holder.tv_quantity.setText(quantity);

        holder.tv_distributed_to.setText(distributedTo);
        holder.tv_distributed_on.setText(distributedOn);

//        Log.d("labelFrom:>>",labelFrom);


    }


    @Override
    public int getItemCount() {
        return distributedLabelArrayList == null ? 0 : distributedLabelArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        private final TextView tv_series, tv_createdat, tv_quantity, tv_distributed_to, tv_distributed_on;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);

            tv_series = itemView.findViewById(R.id.tv_series);
            tv_createdat = itemView.findViewById(R.id.tv_createdat);
//            tv_label_from = itemView.findViewById(R.id.tv_label_from);
//            tv_label_to = itemView.findViewById(R.id.tv_label_to);
//            tv_quantity = itemView.findViewById(R.id.tv_quantity);
            tv_quantity = itemView.findViewById(R.id.tv_quantity);
            tv_distributed_to = itemView.findViewById(R.id.tv_distributed_to);
            tv_distributed_on = itemView.findViewById(R.id.tv_distributed_on);


        }
    }


}
