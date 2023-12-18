package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iipl.smoi.Model.RequestLabelResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;

public class RequestLabelAdapter extends RecyclerView.Adapter<RequestLabelAdapter.mydataviewholder> {

    Context context;
    ArrayList<RequestLabelResponse.RequestLabel> requestLabelArrayList;
    private OnItemClickListener onItemClickListener;
    FragmentManager fragmentManager;

    public RequestLabelAdapter(Context context, ArrayList<RequestLabelResponse.RequestLabel> requestLabelArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.requestLabelArrayList = requestLabelArrayList;
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public RequestLabelAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_request_label, parent, false);
        return new mydataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {
        String id = requestLabelArrayList.get(position).getId();
        String requestedByName = requestLabelArrayList.get(position).getRequestedByName();
        String requestedToName = requestLabelArrayList.get(position).getRequestedToName();
        String typeOfLabel = requestLabelArrayList.get(position).getTypeOfLabel();
        String requiredLabel = requestLabelArrayList.get(position).getRequiredLabel();

        String createdAt = requestLabelArrayList.get(position).getCreatedAt();
//        String labelTo = requestLabelArrayList.get(position).getLabelTo();
//        String quantity = requestLabelArrayList.get(position).getQuantity();
//        String remainingQty = requestLabelArrayList.get(position).getRemainingQty();


        holder.tv_req_by.setText(requestedByName);
        holder.tv_req_to.setText(requestedToName);
        holder.tv_qty.setText(requiredLabel);
        holder.tv_createdat.setText(createdAt);
        holder.tv_label_type.setText(typeOfLabel);

    }

    @Override
    public int getItemCount() {
        return requestLabelArrayList == null ? 0 : requestLabelArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        private final TextView tv_req_by,tv_createdat,tv_req_to,tv_qty,tv_label_type;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);


            tv_req_by = itemView.findViewById(R.id.tv_req_by);
            tv_createdat = itemView.findViewById(R.id.tv_createdat);
//            tv_label_from = itemView.findViewById(R.id.tv_label_from);
//            tv_label_to = itemView.findViewById(R.id.tv_label_to);
//            tv_quantity = itemView.findViewById(R.id.tv_quantity);
            tv_req_to = itemView.findViewById(R.id.tv_req_to);
            tv_qty = itemView.findViewById(R.id.tv_qty);
            tv_label_type = itemView.findViewById(R.id.tv_label_type);




        }
    }


}
