package com.iipl.smoi.Screens.NavFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.R;

public class MyProfile extends Fragment {


    Button btn_update_profile;

    ImageView img_back,img_edit;

    EditText et_first_name, et_last_name, et_email, et_mobileno, et_department, et_address, et_city, et_state, et_pincode;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        btn_update_profile = view.findViewById(R.id.btn_update_profile);

        img_back = view.findViewById(R.id.img_back_profile);
        img_edit = view.findViewById(R.id.img_edit);
//        iv_user = view.findViewById(R.id.iv_user);

        et_first_name = view.findViewById(R.id.et_first_name);
        et_last_name = view.findViewById(R.id.et_last_name);
        et_email = view.findViewById(R.id.et_email);
        et_mobileno = view.findViewById(R.id.et_mobileno);
        et_department = view.findViewById(R.id.et_department);
        et_address = view.findViewById(R.id.et_address);
        et_city = view.findViewById(R.id.et_city);
        et_state = view.findViewById(R.id.et_state);
        et_pincode = view.findViewById(R.id.et_pincode);



        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*UpdateProfile updateProfile = new UpdateProfile();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, updateProfile);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
            }
        });


        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String first_name = sharedpreferences.getString(AppConstant.TAG_firstName, "NA");
        String last_name = sharedpreferences.getString(AppConstant.TAG_lastName, "NA");
        String email = sharedpreferences.getString(AppConstant.TAG_EMAIL, "NA");
        String mobileno = sharedpreferences.getString(AppConstant.TAG_mobileNo, "NA");
        String address = sharedpreferences.getString(AppConstant.TAG_address, "NA");
        String city = sharedpreferences.getString(AppConstant.TAG_city, "NA");
        String state = sharedpreferences.getString(AppConstant.TAG_state, "NA");
        String pincode = sharedpreferences.getString(AppConstant.TAG_pincode, "NA");

        et_first_name.setText(first_name);
        et_last_name.setText(last_name);
        et_email.setText(email);
        et_mobileno.setText(mobileno);
        et_address.setText(address);
        et_city.setText(city);
        et_state.setText(state);
        et_pincode.setText(pincode);



        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        Log.d("profile::>>", MainActivity.currentFragment = "profile");
        ((MainActivity) getActivity()).getFragmentTag(5);
    }
}