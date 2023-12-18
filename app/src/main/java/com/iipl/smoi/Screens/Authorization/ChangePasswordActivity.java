package com.iipl.smoi.Screens.Authorization;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.ChangePasswordResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Screens.ActivityActions.ViewProfileActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    ProgressDialog pDialog;

    boolean doubleBackToExitPressedOnce = false;

    Button btnChangePassword;
    EditText et_password, et_confirm_password;


    String str_et_password, str_et_confirm_password;

    private View parent_view;

    boolean isPasswordok = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        btnChangePassword = findViewById(R.id.btnChangePassword);

        et_password = findViewById(R.id.et_password);
        et_confirm_password = findViewById(R.id.et_c_password);

        parent_view = findViewById(android.R.id.content);

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getChangePassword();

            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //Internet Available
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }

    private void getChangePassword() {
        str_et_password = et_password.getText().toString().trim();
        str_et_confirm_password = et_confirm_password.getText().toString().trim();

        if (str_et_password.isEmpty() || str_et_confirm_password.isEmpty()) {
            Toast.makeText(ChangePasswordActivity.this, "Please Type Password", Toast.LENGTH_SHORT).show();
        } else {
            if (!str_et_password.matches(str_et_confirm_password)) {
                Toast.makeText(ChangePasswordActivity.this, "Password Doesn't Matched ", Toast.LENGTH_SHORT).show();
            } else {
                isPasswordok = checkpassword();
                if (isPasswordok) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                        String userId = sharedpreferences.getString(AppConstant.TAG_USER_ID, "");

                        Map<String, String> requestBody = new HashMap<>();
                        requestBody.put("user_id", userId);
                        requestBody.put("password", str_et_password);
                        requestBody.put("confirm_password", str_et_confirm_password);

                        Log.d("CP>requestBody>>", String.valueOf(requestBody));

                        pDialog = new ProgressDialog(ChangePasswordActivity.this, R.style.MyAlertDialogStyle);
                        pDialog.setMessage("Please wait...");
                        pDialog.setCancelable(false);
                        pDialog.show();


                        APIInterface request = APIClient.getClient().create(APIInterface.class);
                        Call<ChangePasswordResponse> call = request.getChangePassword(requestBody);
                        call.enqueue(new Callback<ChangePasswordResponse>() {
                            @Override
                            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {

                                if (response.isSuccessful()) {

                                    Log.d("CP>>>", String.valueOf(response.body().getMessage()));

                                    if (response.body().getStatus().matches("200")) {

                                        startActivity(new Intent(ChangePasswordActivity.this, LoginActivity.class));

                                    }

                                }

                                if (pDialog.isShowing()) {
                                    pDialog.dismiss();
                                }

                            }

                            @Override
                            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                                if (pDialog.isShowing()) {
                                    pDialog.dismiss();
                                }
                                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
                            }

                        });


                    } else {

                        Toast.makeText(ChangePasswordActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();

                    }
                }
            }

        }

    }

    private boolean checkpassword() {

        if (et_password.length() < 6) {
//            et_password.setError("Password must be minimum 6 characters");
            et_password.requestFocus();
            Toast.makeText(ChangePasswordActivity.this, "Password must be minimum 6 characters", Toast.LENGTH_SHORT).show();

            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
//            System.gc();
//            finishAffinity();
//            System.exit(0);
            startActivity(new Intent(ChangePasswordActivity.this, MainActivity.class));
            finish();
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tap again, go to Home", Toast.LENGTH_SHORT).show();


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ChangePasswordActivity.this, ViewProfileActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }*/


}