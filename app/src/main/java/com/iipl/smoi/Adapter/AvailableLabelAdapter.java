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

import com.iipl.smoi.Model.AvailableLabelResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;

public class AvailableLabelAdapter extends RecyclerView.Adapter<AvailableLabelAdapter.mydataviewholder> {

    Context context;
    ArrayList<AvailableLabelResponse.AvailableLabel> availableLabelArrayList;
    private OnItemClickListener onItemClickListener;

    public AvailableLabelAdapter(Context context, ArrayList<AvailableLabelResponse.AvailableLabel> availableLabelArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.availableLabelArrayList = availableLabelArrayList;
        this.onItemClickListener=onItemClickListener;
    }

   /* public void filterList(ArrayList<ChapterResponse.Chapter> filterllist) {
        availableLabelArrayList = filterllist;
        notifyDataSetChanged();
    }*/

    @Override
    public AvailableLabelAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_avail_label, parent, false);
        return new mydataviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

        String id = availableLabelArrayList.get(position).getId();
        String series = availableLabelArrayList.get(position).getSeries();
        String createdAt = availableLabelArrayList.get(position).getCreatedAt();
        String labelFrom = availableLabelArrayList.get(position).getLabelFrom();
        String labelTo = availableLabelArrayList.get(position).getLabelTo();
        String quantity = availableLabelArrayList.get(position).getQuantity();
        String remainingQty = availableLabelArrayList.get(position).getRemainingQty();
        String typeOfLabel = availableLabelArrayList.get(position).getTypeOfLabel();

        holder.tv_series.setText(series+"-"+labelFrom+" to "+labelTo);
        holder.tv_createdat.setText(typeOfLabel);
//        holder.tv_label_from.setText(labelFrom);
//        holder.tv_label_to.setText(labelTo);
//        holder.tv_quantity.setText(quantity);
        holder.tv_remain_quantity.setText(remainingQty);

        Log.d("label id>>",id);

    }


    @Override
    public int getItemCount() {
        return availableLabelArrayList == null ? 0 : availableLabelArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        private final TextView tv_series,tv_createdat,tv_remain_quantity;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);

            tv_series = itemView.findViewById(R.id.tv_series);
            tv_createdat = itemView.findViewById(R.id.tv_createdat);
//            tv_label_from = itemView.findViewById(R.id.tv_label_from);
//            tv_label_to = itemView.findViewById(R.id.tv_label_to);
//            tv_quantity = itemView.findViewById(R.id.tv_quantity);
            tv_remain_quantity = itemView.findViewById(R.id.tv_remain_quantity);




        }
    }


}
