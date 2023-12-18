package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.GMap.ChapterMapsActivity;
import com.iipl.smoi.Model.ChapterResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;


public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.myDataViewHolder> {

    Context context;
    ArrayList<ChapterResponse.Chapter> chapterArrayList;
    private final OnItemClickListener onItemClickListener;
    private OnItemGMapClickListener onItemGMapClickListener;

    public ChapterAdapter(Context context, ArrayList<ChapterResponse.Chapter> chapterArrayList,
                          OnItemClickListener onItemClickListener) {
        this.context = context;
        this.chapterArrayList = chapterArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemGMap(OnItemGMapClickListener onItemGMapClickListener){
        this.onItemGMapClickListener=onItemGMapClickListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<ChapterResponse.Chapter> filterList) {
        chapterArrayList = filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChapterAdapter.myDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_member_item, parent, false);
        return new myDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myDataViewHolder holder, @SuppressLint("RecyclerView") final int position) {
//        String str_id = (chapterArrayList.get(position).getId());
//        Log.d("user id>>",str_id);

        String username = (chapterArrayList.get(position).getUsername());
//        String str_firastname = (chapterArrayList.get(position).getFirstName());
//        String str_lastname = (chapterArrayList.get(position).getLastName());

        String str_email = (chapterArrayList.get(position).getEmail());

        holder.tv_username.setText(username);
//        holder.tv_username.setText(str_firastname+" "+str_lastname);
        holder.tv_email.setText(str_email);

        String upperString = username.substring(0, 1).toUpperCase();
        holder.tv_sign.setText(upperString);

//        Log.d("upperString>>", upperString);

//        holder.tv_id.setText(str_id);
//        holder.tv_username.setText(str_username);

        holder.ll_memberlist.setOnClickListener(v -> onItemClickListener.onItemClick(position));

        holder.img_google_map.setOnClickListener(view -> {
            onItemGMapClickListener.onItemGMapClick(position);
        });

    }


    @Override
    public int getItemCount() {
        return chapterArrayList == null ? 0 : chapterArrayList.size();
    }

    public static class myDataViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_sign, tv_username, tv_email;

        private final LinearLayout ll_memberlist;

        private final ImageView img_google_map;

        public myDataViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_sign = itemView.findViewById(R.id.tv_sign);
            tv_username = itemView.findViewById(R.id.tv_username_member);
            tv_email = itemView.findViewById(R.id.tv_email_member);

            img_google_map = itemView.findViewById(R.id.img_google_map);

            ll_memberlist = itemView.findViewById(R.id.ll_memberlist);

        }
    }


    public interface OnItemGMapClickListener {
        void onItemGMapClick(int position);
    }


}
