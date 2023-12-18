package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Model.EventsResponse;
import com.iipl.smoi.Model.FaqsResponse;
import com.iipl.smoi.Model.NaturalTypeResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.mydataviewholder> {

    Context context;
    ArrayList<EventsResponse.Event> eventArrayList;
    private OnItemClickListener onItemClickListener;

    public EventsAdapter(Context context, ArrayList<EventsResponse.Event> eventArrayList,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.eventArrayList = eventArrayList;
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public EventsAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_events_list, parent, false);
        return new mydataviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

        String str_title = (eventArrayList.get(position).getTitle());
        String desc = (eventArrayList.get(position).getDesc());

        String eventDate = eventArrayList.get(position).getEventDate();

        holder.tv_event_date_time.setText(eventDate);

        holder.tv_readmore.setOnClickListener(view -> {
            int i=position;
            String title = eventArrayList.get(i).getTitle();
            String des = eventArrayList.get(i).getDesc();
//            String eventDate = eventArrayList.get(i).getEventDate();

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);

            View mView = LayoutInflater.from(context).inflate(R.layout.layout_view_events, null);
//            View mView = context.getLayoutInflater().inflate(R.layout.layout_view_natural_types, null);

            TextView tv_title = mView.findViewById(R.id.tv_title);
            TextView tv_desc = mView.findViewById(R.id.tv_desc);
            TextView tv_timedate = mView.findViewById(R.id.tv_timedate);

            tv_title.setText(title);
            tv_timedate.setText(eventDate);
            tv_desc.setText((HtmlCompat.fromHtml(des, 0)));

            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();



        });


        Glide.with(context).load(eventArrayList.get(position).getGalleryimage())
                .placeholder(R.drawable.bg)
                .error(R.drawable.bg)
                .into(holder.img_events);



    }

    @Override
    public int getItemCount() {
        return eventArrayList == null ? 0 : eventArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        public final TextView tv_readmore,tv_event_date_time;
        public final ImageView img_events;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);
            tv_readmore = itemView.findViewById(R.id.tv_readmore);
            tv_event_date_time = itemView.findViewById(R.id.tv_event_date_time);
            img_events = itemView.findViewById(R.id.img_events);

        }
    }

    public void updateList(ArrayList<EventsResponse.Event> eventArrayList) {
        this.eventArrayList = eventArrayList;
        notifyDataSetChanged();

    }
}


