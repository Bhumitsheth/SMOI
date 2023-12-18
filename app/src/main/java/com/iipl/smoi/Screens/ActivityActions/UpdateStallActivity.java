package com.iipl.smoi.Screens.ActivityActions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.iipl.smoi.R;

public class UpdateStallActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageView img_back;
    private ImageView image_view_stall_list;
    private NestedScrollView scrollview;
    private LinearLayout ll_main;
    private TextInputEditText et_stall_no_from;
    private TextInputEditText et_stall_no_to;
    private TextInputEditText et_stall_rent;
    private TextInputEditText et_gst_per;
    private TextInputEditText et_gst_amount;
    private TextInputEditText et_total;
    private MaterialButton btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_stall);


        findViews();

        Clicks();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String stallNoFrom = bundle.getString("stallNoFrom");
            String stallNoTo = bundle.getString("stallNoTo");
            String stallRent = bundle.getString("stallRent");
            String gstPer = bundle.getString("gstPer");

            et_stall_no_from.setText(stallNoFrom);
            et_stall_no_to.setText(stallNoTo);
            et_stall_rent.setText(stallRent);
            et_gst_per.setText(gstPer);

        }



    }


    private void Clicks() {
        img_back.setOnClickListener(view -> {
//            getActivity().onBackPressed();
            finish();
        });
    }


    private void findViews() {
        img_back = (ImageView) findViewById(R.id.img_back_added_stalls);
        image_view_stall_list = (ImageView) findViewById(R.id.image_view_stall_list);
        scrollview = (NestedScrollView) findViewById(R.id.scrollview);
        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        et_stall_no_from = (TextInputEditText) findViewById(R.id.et_stall_no_from);
        et_stall_no_to = (TextInputEditText) findViewById(R.id.et_stall_no_to);
        et_stall_rent = (TextInputEditText) findViewById(R.id.et_stall_rent);
        et_gst_per = (TextInputEditText) findViewById(R.id.et_gst_per);
        et_gst_amount = (TextInputEditText) findViewById(R.id.et_gst_amount);
        et_total = (TextInputEditText) findViewById(R.id.et_total);
        btn_update = (MaterialButton) findViewById(R.id.btn_update);

        btn_update.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == btn_update) {
            // Handle clicks for btn_update

            Intent intent = new Intent(UpdateStallActivity.this, StallListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("stallNoFrom", et_stall_no_from.getText().toString());
            bundle.putString("stallNoTo", et_stall_no_to.getText().toString());
            bundle.putString("stallRent", et_gst_per.getText().toString());
            bundle.putString("gstPer", et_stall_rent.getText().toString());
            bundle.putString("gstPer", et_gst_amount.getText().toString());
            bundle.putString("gstPer", et_total.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);

        }
    }

}