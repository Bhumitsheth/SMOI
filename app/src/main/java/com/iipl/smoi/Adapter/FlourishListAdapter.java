package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.iipl.smoi.Model.FlourishListResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Screens.Fragment.WebviewFlorish;
import com.iipl.smoi.Screens.NavFragments.Faqs;

import java.util.ArrayList;

public class FlourishListAdapter extends RecyclerView.Adapter<FlourishListAdapter.mydataviewholder> {

    Context context;
    ArrayList<FlourishListResponse.FlourishMaster> flourishMasterArrayList;
    private OnItemClickListener onItemClickListener;

    public FlourishListAdapter(Context context, ArrayList<FlourishListResponse.FlourishMaster> flourishMasterArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.flourishMasterArrayList = flourishMasterArrayList;
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public FlourishListAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_flourish_list, parent, false);
        return new mydataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {
        String id = flourishMasterArrayList.get(position).getId();
        String topic = flourishMasterArrayList.get(position).getTopic();
        String eventPlace = flourishMasterArrayList.get(position).getEventPlace();
        String url = flourishMasterArrayList.get(position).getUrl();
        String address = flourishMasterArrayList.get(position).getAddress();
        String des = flourishMasterArrayList.get(position).getDes();
        String scheduleddate = flourishMasterArrayList.get(position).getScheduleddate();

        holder.tv_topic.setText(topic);
        holder.tv_tag.setText(eventPlace);
        holder.tv_desc.setText(des);
        holder.tv_date.setText(scheduleddate);

        if (url.isEmpty()){
            holder.tv_url_val.setText(address);
            holder.tv_url.setText("Address: ");
        }else {
//            holder.tv_url_val.setTextIsSelectable(true);
            holder.tv_url_val.setText(url);
            holder.tv_url.setText("URL: ");
        }

        holder.tv_url_val.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!url.isEmpty()){
                    Bundle bundle = new Bundle();
                    bundle.putString("url", url);
                    WebviewFlorish webviewFlorish = new WebviewFlorish();
                    webviewFlorish.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, webviewFlorish);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return flourishMasterArrayList == null ? 0 : flourishMasterArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        private final TextView tv_topic,tv_tag,tv_desc,tv_url,tv_url_val,tv_date;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);

            tv_topic = itemView.findViewById(R.id.tv_topic);
            tv_tag = itemView.findViewById(R.id.tv_tag);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_url = itemView.findViewById(R.id.tv_url);
            tv_url_val = itemView.findViewById(R.id.tv_url_val);
            tv_date = itemView.findViewById(R.id.tv_date);




        }
    }


}
