package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.iipl.smoi.Model.OtherModel.StallSelectionModel;
import com.iipl.smoi.Model.OtherModel.StallSelectionModel;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Screens.ActivityActions.UpdateStallActivity;

import java.util.ArrayList;

public class StallSelectionAdapter extends RecyclerView.Adapter<StallSelectionAdapter.mydataviewholder> {

    Context context;
    ArrayList<StallSelectionModel> StallSelectionModelArrayList;
    private OnItemClickListener onItemClickListener;

    public StallSelectionAdapter(Context context, ArrayList<StallSelectionModel> StallSelectionModelArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.StallSelectionModelArrayList = StallSelectionModelArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public StallSelectionAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_stall_selection, parent, false);
        return new mydataviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

        String preferred_stall_no = StallSelectionModelArrayList.get(position).getPreferredStallNo();
        String stallRent = StallSelectionModelArrayList.get(position).getStallRent();
        String gstPer = StallSelectionModelArrayList.get(position).getGst();
        String gstAmount = StallSelectionModelArrayList.get(position).getGstAmount();
        String total = StallSelectionModelArrayList.get(position).getTotal();

        holder.tv_preferred_stall_no.setText(preferred_stall_no);
        holder.tv_stall_rent.setText(stallRent);
        holder.tv_gst_per.setText(gstPer);
        holder.tv_gst_amount.setText(gstAmount);
        holder.tv_total.setText(total);

        holder.img_delete.setOnClickListener(view -> {
//            onItemClickListener.onItemClick(position);
        });

    }

    @Override
    public int getItemCount() {
        return StallSelectionModelArrayList == null ? 0 : StallSelectionModelArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        TextView tv_preferred_stall_no,tv_gst_per;
        TextView tv_stall_rent;
        TextView tv_gst_amount;
        TextView tv_total;
        ImageView img_delete;


        public mydataviewholder(@NonNull View itemView) {
            super(itemView);
            View view = itemView;

            tv_preferred_stall_no = (TextView) view.findViewById(R.id.tv_preferred_stall_no);
            tv_stall_rent = (TextView) view.findViewById(R.id.tv_stall_rent);
            tv_gst_per = (TextView) view.findViewById(R.id.tv_gst_per);
            tv_gst_amount = (TextView) view.findViewById(R.id.tv_gst_amount);
            tv_total = (TextView) view.findViewById(R.id.tv_total);
            img_delete = (ImageView) view.findViewById(R.id.img_delete);

        }
    }

    public void updateList(ArrayList<StallSelectionModel> StallSelectionModelArrayList) {
        this.StallSelectionModelArrayList = StallSelectionModelArrayList;
        notifyDataSetChanged();
    }






}



