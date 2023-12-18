package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Model.ContactListResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.mydataviewholder> {

    Context context;
    ArrayList<ContactListResponse.Contact> contactArrayList;
    private OnItemClickListener onItemClickListener;
    FragmentManager fragmentManager;

    public ContactListAdapter(Context context, ArrayList<ContactListResponse.Contact> contactArrayList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.contactArrayList = contactArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ContactListAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_contact_item, parent, false);
        return new mydataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

        String str_firastname = (contactArrayList.get(position).getFirstname());
        String str_lastname = (contactArrayList.get(position).getLastname());
        String email = (contactArrayList.get(position).getEmail());
        String mobileNo = (contactArrayList.get(position).getMobileNo());
        String address = (contactArrayList.get(position).getAddress());
        String contactImage = (contactArrayList.get(position).getContactImage());


        holder.tv_username.setText(str_firastname + " " + str_lastname);
        holder.tv_email.setText(email);

//        String upperString = str_firastname.substring(0, 1).toUpperCase();
//        holder.tv_contact.setText("upperString");

        Log.d("ContactImage>>", contactArrayList.get(position).getContactImage());
//        Log.d("tv_contact>>", upperString);


        String img=contactArrayList.get(position).getContactImage();

        Glide.with(context)
                .load(img)
                .placeholder(R.drawable.bg)
                .error(R.drawable.bg)
                .into(holder.image_contact);


        holder.ll_memberlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return contactArrayList == null ? 0 : contactArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        private final CircleImageView image_contact;

        private final TextView tv_username, tv_email;

        private final LinearLayout ll_memberlist;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);

//            tv_sign = itemView.findViewById(R.id.tv_sign);
            tv_username = itemView.findViewById(R.id.tv_username_member);
            tv_email = itemView.findViewById(R.id.tv_email_member);

            ll_memberlist = itemView.findViewById(R.id.ll_memberlist);

            image_contact=itemView.findViewById(R.id.image_contact);


        }
    }


}
