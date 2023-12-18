package com.iipl.smoi.Screens.ActivityActions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iipl.smoi.R;

public class ChapterDetails extends AppCompatActivity {

    TextView tv_toolbar_title;
    ImageView img_toolbar_back,img_profileImage;

    TextView tv_firstname,tv_lastname,tv_email,tv_mobileno,tv_address;

    String str_firstname,str_lastname,str_email,str_mobileno,str_address,profileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_details);

        img_toolbar_back=findViewById(R.id.img_toolbar_back);
        img_profileImage=findViewById(R.id.profileImage);

        tv_toolbar_title=findViewById(R.id.tv_toolbar_title);

        tv_toolbar_title.setText(R.string.chapterdetails);

        tv_firstname=findViewById(R.id.tv_firstname);
        tv_lastname=findViewById(R.id.tv_lastname);
        tv_email=findViewById(R.id.tv_email);
        tv_mobileno=findViewById(R.id.tv_mobileno);
        tv_address=findViewById(R.id.tv_address);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            str_firstname = bundle.getString("str_firstname","NA");
            str_lastname = bundle.getString("str_lastname","NA");
            str_email = bundle.getString("str_email","NA");
            str_mobileno = bundle.getString("str_mobileno","NA");
            str_address = bundle.getString("str_address","NA");
            profileImage = bundle.getString("profileImage");
        }

        tv_firstname.setText(str_firstname);
        tv_lastname.setText(str_lastname);
        tv_email.setText(str_email);
        tv_mobileno.setText(str_mobileno);
        tv_address.setText(str_address);

        Glide.with(ChapterDetails.this)
                .load(profileImage)
                .placeholder(R.drawable.bg)
                .error(R.drawable.bg)
                .into(img_profileImage);

        img_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterDetails.this.onBackPressed();
//                Toast.makeText(getContext(), "Members...", Toast.LENGTH_SHORT).show();
            }
        });



    }
}