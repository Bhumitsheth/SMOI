package com.iipl.smoi.Screens.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iipl.smoi.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactDetails extends Fragment {

    CircleImageView circleImageView;

    TextView tv_toolbar_title;

    ImageView img_toolbar_back;

    TextView tv_firstname,tv_lastname,tv_email,tv_mobileno,tv_address;

    String str_firstname,str_lastname,str_email,str_mobileno,str_address;

    String contactImage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_contact_details, container, false);

        img_toolbar_back=view.findViewById(R.id.img_toolbar_back);
        tv_toolbar_title=view.findViewById(R.id.tv_toolbar_title);

        circleImageView=view.findViewById(R.id.image);

        tv_toolbar_title.setText(R.string.contact_details);

        tv_firstname=view.findViewById(R.id.tv_firstname);
        tv_lastname=view.findViewById(R.id.tv_lastname);
        tv_email=view.findViewById(R.id.tv_email);
        tv_mobileno=view.findViewById(R.id.tv_mobileno);
        tv_address=view.findViewById(R.id.tv_address);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            str_firstname = bundle.getString("str_firstname","NA");
            str_lastname = bundle.getString("str_lastname","NA");
            str_email = bundle.getString("str_email","NA");
            str_mobileno = bundle.getString("str_mobileno","NA");
            str_address = bundle.getString("str_address","NA");
            contactImage = bundle.getString("contactImage");
        }

        tv_firstname.setText(str_firstname);
        tv_lastname.setText(str_lastname);
        tv_email.setText(str_email);
        tv_mobileno.setText(str_mobileno);
        tv_address.setText(str_address);
//        circleImageView.setImageResource(Integer.parseInt(contactImage));

           Glide.with(getActivity())
                .load(contactImage)
                .error(R.drawable.bg)
                .into(circleImageView);


        img_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
//                Toast.makeText(getContext(), "Members...", Toast.LENGTH_SHORT).show();
            }
        });





        return view;
    }
}