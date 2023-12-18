package com.iipl.smoi.Screens.ActivityActions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.R;
import com.iipl.smoi.Screens.Authorization.ChangePasswordActivity;

public class ViewProfileActivity extends AppCompatActivity {

    ImageView img_back, img_edit, image_contact;

    TextView tv_username_val, tv_first_name_val, tv_last_name_val, tv_email_val,
            tv_mobile_no_val, tv_address_val, tv_city_val, tv_state_val, tv_pincode_val;

    Button btn_change_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        img_back = findViewById(R.id.img_back_profile);
        img_edit = findViewById(R.id.img_edit);
        image_contact = findViewById(R.id.image_contact);
//        iv_user = view.findViewById(R.id.iv_user);

        tv_username_val = findViewById(R.id.tv_username_val);
        tv_first_name_val = findViewById(R.id.tv_first_name_val);
        tv_last_name_val = findViewById(R.id.tv_last_name_val);
        tv_email_val = findViewById(R.id.tv_email_val);
        tv_mobile_no_val = findViewById(R.id.tv_mobile_no_val);
        tv_address_val = findViewById(R.id.tv_address_val);
        tv_city_val = findViewById(R.id.tv_city_val);
        tv_state_val = findViewById(R.id.tv_state_val);
        tv_pincode_val = findViewById(R.id.tv_pincode_val);

        btn_change_password = findViewById(R.id.btn_change_password);

        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String userName = sharedpreferences.getString(AppConstant.TAG_USER_NAME, "NA");
        String first_name = sharedpreferences.getString(AppConstant.TAG_firstName, "NA");
        String last_name = sharedpreferences.getString(AppConstant.TAG_lastName, "NA");
        String email = sharedpreferences.getString(AppConstant.TAG_EMAIL, "NA");
        String mobileno = sharedpreferences.getString(AppConstant.TAG_mobileNo, "NA");
        String address = sharedpreferences.getString(AppConstant.TAG_address, "NA");
        String city = sharedpreferences.getString(AppConstant.TAG_city, "NA");
        String state = sharedpreferences.getString(AppConstant.TAG_state, "NA");
        String pincode = sharedpreferences.getString(AppConstant.TAG_pincode, "NA");
        String profile_image = sharedpreferences.getString(AppConstant.TAG_PROFILE_IMAGE, "");


        Glide.with(ViewProfileActivity.this)
                .load(profile_image)
                .placeholder(R.drawable.bg)
                .error(R.drawable.bg)
                .into(image_contact);


        image_contact.setOnClickListener(view -> {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(ViewProfileActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.layout_profile_view, null);
            ImageView photoView = mView.findViewById(R.id.imageView);

            Glide.with(ViewProfileActivity.this)
                    .load(profile_image)
                    .placeholder(R.drawable.bg)
                    .error(R.drawable.bg)
                    .into(photoView);

//            photoView.setImageResource(R.drawable.bg);
            mBuilder.setView(mView);
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        });


       /* if (!profile_image.isEmpty()) {
            byte[] b = Base64.decode(profile_image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            image_contact.setImageBitmap(bitmap);
        }*/


        tv_username_val.setText(userName);
        tv_first_name_val.setText(first_name);
        tv_last_name_val.setText(last_name);
        tv_email_val.setText(email);
        tv_mobile_no_val.setText(mobileno);
        tv_address_val.setText(address);
        tv_city_val.setText(city);
        tv_state_val.setText(state);
        tv_pincode_val.setText(pincode);


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewProfileActivity.this, MainActivity.class));
                finish();
            }
        });

        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProfileActivity.this, UpdateProfileActivity.class));
            }
        });

        btn_change_password.setOnClickListener(v -> {
            startActivity(new Intent(ViewProfileActivity.this, ChangePasswordProfileActivity.class));
        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ViewProfileActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}