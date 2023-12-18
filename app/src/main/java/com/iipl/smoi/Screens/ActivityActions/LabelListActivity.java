package com.iipl.smoi.Screens.ActivityActions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.R;

public class LabelListActivity extends AppCompatActivity {


    private ImageView img_back_label;
    private LinearLayout ll_available_label;
    private LinearLayout ll_distribution_label;
    private LinearLayout ll_request_label;

    ImageView img_back, img_add_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_list);

        findViews();

        ll_available_label.setVisibility(View.VISIBLE);
        ll_distribution_label.setVisibility(View.VISIBLE);
        ll_request_label.setVisibility(View.VISIBLE);

        getAvailableLabel();
        getDistributionLabel();
        getRequestLabel();

        img_add_label.setOnClickListener(view -> {
            Intent intent = new Intent(LabelListActivity.this, CreateLabelActivity.class);
            startActivity(intent);
        });

        img_back.setOnClickListener(v -> {
            Intent intent = new Intent(LabelListActivity.this, MainActivity.class);
            startActivity(intent);
        });


        SharedPreferences sharedpreferences1 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String id = sharedpreferences1.getString(AppConstant.TAG_USER_ID, null);
        String roll_id = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);
        if (roll_id != null) {
            if (roll_id.matches(AppConstant.TAG_AU)||roll_id.matches(AppConstant.TAG_CHAPTER)) {
                ll_available_label.setVisibility(View.GONE);
                ll_distribution_label.setVisibility(View.GONE);
            }
        }
    }


    private void findViews() {

        img_back = findViewById(R.id.img_back_label);
        img_add_label = findViewById(R.id.img_add_label);

        ll_available_label = (LinearLayout) findViewById(R.id.ll_available_label);
        ll_distribution_label = (LinearLayout) findViewById(R.id.ll_distribution_label);
        ll_request_label = (LinearLayout) findViewById(R.id.ll_request_label);

    }


    private void getRequestLabel() {
        ll_request_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LabelListActivity.this, RequestLabelsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getDistributionLabel() {
        ll_distribution_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LabelListActivity.this, DistributionLabelsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getAvailableLabel() {
        ll_available_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LabelListActivity.this, AvailableLabelsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(LabelListActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }


}