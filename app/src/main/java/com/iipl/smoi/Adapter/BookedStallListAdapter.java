package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.iipl.smoi.Model.EventsResponse;
import com.iipl.smoi.Model.OtherModel.BookStallRentModel;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Screens.ActivityActions.StallListActivity;
import com.iipl.smoi.Screens.ActivityActions.UpdateStallActivity;

import java.util.ArrayList;


public class BookedStallListAdapter extends RecyclerView.Adapter<BookedStallListAdapter.mydataviewholder> {


    Context context;
    ArrayList<BookStallRentModel> bookStallRentModelArrayList;
    private OnItemClickListener onItemClickListener;

    public BookedStallListAdapter(Context context, ArrayList<BookStallRentModel> bookStallRentModelArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.bookStallRentModelArrayList = bookStallRentModelArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public BookedStallListAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_expo_stall_rent, parent, false);
        return new mydataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

        String stallNoFrom = bookStallRentModelArrayList.get(position).getStallNoFrom();
        String stallNoTo = bookStallRentModelArrayList.get(position).getGetStallNoTo();
        String stallRent = bookStallRentModelArrayList.get(position).getStallRent();
        String gstPer = bookStallRentModelArrayList.get(position).getGstPer();
        String gstAmount = bookStallRentModelArrayList.get(position).getGstAmount();
        String total = bookStallRentModelArrayList.get(position).getTotal();

        holder.tv_stall_no.setText(stallNoFrom + " - " + stallNoTo);
        holder.tv_stall_rent.setText(stallRent);
        holder.tv_gst_per.setText(gstPer);
        holder.tv_gst_amount.setText(gstAmount);
        holder.tv_total.setText(total);

        holder.cv_delete.setOnClickListener(view -> {
            onItemClickListener.onItemClick(position);
        });

        holder.cv_edit.setOnClickListener(view -> {
//            onItemClickListener.onItemClick(position);
//            context.startActivity(new Intent(context, UpdateStallActivity.class));

            Intent intent = new Intent(context, UpdateStallActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("stallNoFrom", stallNoFrom);
            bundle.putString("stallNoTo", stallNoTo);
            bundle.putString("stallRent", stallRent);
            bundle.putString("gstPer", gstPer);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return bookStallRentModelArrayList == null ? 0 : bookStallRentModelArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        TextView tv_stall_no;
        TextView tv_gst_per;
        TextView tv_stall_rent;
        TextView tv_gst_amount;
        TextView tv_total;
        MaterialCardView cv_edit, cv_delete;
        TextView tv_edit;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);
            View view = itemView;

            tv_stall_no = (TextView) view.findViewById(R.id.tv_stall_no);
            tv_gst_per = (TextView) view.findViewById(R.id.tv_gst_per);
            tv_stall_rent = (TextView) view.findViewById(R.id.tv_stall_rent);
            tv_gst_amount = (TextView) view.findViewById(R.id.tv_gst_amount);
            tv_total = (TextView) view.findViewById(R.id.tv_total);
            cv_edit = (MaterialCardView) view.findViewById(R.id.cv_edit);
            cv_delete = (MaterialCardView) view.findViewById(R.id.cv_delete);
            tv_edit = (TextView) view.findViewById(R.id.tv_edit);
        }
    }

    public void updateList(ArrayList<BookStallRentModel> bookStallRentModelArrayList) {
        this.bookStallRentModelArrayList = bookStallRentModelArrayList;
        notifyDataSetChanged();
    }






}


