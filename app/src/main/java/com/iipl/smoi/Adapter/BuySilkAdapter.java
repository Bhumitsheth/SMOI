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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.GMap.BuySilkMapsActivity;
import com.iipl.smoi.GMap.MemberMapsActivity;
import com.iipl.smoi.Model.MemberResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;



public class BuySilkAdapter extends RecyclerView.Adapter<BuySilkAdapter.mydataviewholder> {

    Context context;
    ArrayList<MemberResponse.Member> memberArrayList;
    private OnItemClickListener onItemClickListener;
    FragmentManager fragmentManager;

    public BuySilkAdapter(Context context, ArrayList<MemberResponse.Member> memberArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.memberArrayList = memberArrayList;
        this.onItemClickListener = onItemClickListener;
    }

   /* public void filterList(ArrayList<MemberResponse.Member> filterllist) {
        memberArrayList = filterllist;
        notifyDataSetChanged();
    }*/

    @Override
    public BuySilkAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_member_item, parent, false);
        return new mydataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

//        String str_id = (memberArrayList.get(position).getId());
//        Log.d("user id>>",str_id);

        String username = (memberArrayList.get(position).getUsername());
        String str_firastname = (memberArrayList.get(position).getFirstName());
        String str_lastname = (memberArrayList.get(position).getLastName());

        String str_email = (memberArrayList.get(position).getEmail());

        String city = (memberArrayList.get(position).getCity());

        holder.tv_username.setText(str_firastname);
//        holder.tv_username.setText(str_firastname+" "+str_lastname);
        holder.tv_email.setText(city);

        String upperString = str_firastname.substring(0, 1).toUpperCase();
        holder.tv_sign.setText(upperString);

//        Log.d("upperString>>",str_firastname);

//        holder.tv_id.setText(str_id);
//        holder.tv_username.setText(str_username);

        holder.ll_memberlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });


        holder.img_google_map.setOnClickListener(view -> {
            String latitude = memberArrayList.get(position).getLatitude();
            String longitude = memberArrayList.get(position).getLongitude();
            Log.d("desti_loc>>>", latitude + " " + longitude);
            if ((latitude != null) && longitude != null) {
                if (!latitude.isEmpty()){
                    SharedPreferences sharedpreferences = context.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(AppConstant.TAG_LATITUDE, latitude);
                    editor.putString(AppConstant.TAG_LONGITUDE, longitude);
                    editor.commit();

                    Intent intent = new Intent(context, BuySilkMapsActivity.class);
                    context.startActivity(intent);
                }else {
                    Toast.makeText(context, "Not Available Latitude Longitude", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Not Available Latitude Longitude", Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public int getItemCount() {
        return memberArrayList == null ? 0 : memberArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {
        private final TextView tv_sign, tv_username, tv_email;
        private final LinearLayout ll_memberlist;
        ImageView img_google_map;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);
            tv_sign = itemView.findViewById(R.id.tv_sign);
            tv_username = itemView.findViewById(R.id.tv_username_member);
            tv_email = itemView.findViewById(R.id.tv_email_member);
            ll_memberlist = itemView.findViewById(R.id.ll_memberlist);
            img_google_map = itemView.findViewById(R.id.img_google_map);
        }
    }
    public void updateList(ArrayList<MemberResponse.Member> memberArrayList) {
        this.memberArrayList = memberArrayList;
        notifyDataSetChanged();
    }


}
