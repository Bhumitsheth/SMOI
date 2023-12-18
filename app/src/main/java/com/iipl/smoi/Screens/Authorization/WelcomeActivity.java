package com.iipl.smoi.Screens.Authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.R;

public class WelcomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String once_run = sharedpreferences.getString(AppConstant.TAG_ONCE_RUN, null);

        if (once_run==null){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(AppConstant.TAG_ONCE_RUN, "hello");
            editor.commit();
            Intent intent = new Intent(WelcomeActivity.this, SplashActivity.class);
            startActivity(intent);
        }else  {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
        }


    }




}