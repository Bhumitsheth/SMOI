package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.iipl.smoi.Model.EventsResponse;
import com.iipl.smoi.Model.NotificationsResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.mydataviewholder> {

    Context context;
    ArrayList<NotificationsResponse> notificationsList;
    private OnItemClickListener onItemClickListener;

    public NotificationAdapter(Context context, ArrayList<NotificationsResponse> notificationsList/*,OnItemClickListener onItemClickListener*/) {
        this.context = context;
        this.notificationsList = notificationsList;
//        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public NotificationAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_notification, parent, false);
        return new mydataviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

    }

    @Override
    public int getItemCount() {
        return notificationsList == null ? 0 : notificationsList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        public final TextView tv_readmore;
        public final ImageView img_events;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);
            tv_readmore = itemView.findViewById(R.id.tv_readmore);
            img_events = itemView.findViewById(R.id.img_events);

        }

    }

    public void updateList(ArrayList<NotificationsResponse> notificationsList) {
        this.notificationsList = notificationsList;
        notifyDataSetChanged();


    }

}



