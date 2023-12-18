package com.iipl.smoi.Screens.Authorization;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.iipl.smoi.Model.ForgotPasswordResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    Button btn_sendotp, btn_login;
    EditText et_forgot_mno;

    String str_mobileNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        findView();

        goToLogin();


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            getOtp();
        } else {
            Toast.makeText(ForgotPasswordActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }


    }

    private void getOtp() {
        btn_sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_mobileNo = et_forgot_mno.getText().toString().trim();
                int i = et_forgot_mno.getText().toString().trim().length();

                if (str_mobileNo.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (!(i == 10)) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please Check Mobile Number", Toast.LENGTH_SHORT).show();
                } else {
//                        Intent intent = new Intent(getApplicationContext(), VerifyOtpActivity.class);
//                        intent.putExtra("otp", "str_otp");
//                        intent.putExtra("forgot_mno", str_mobileNo);
//                        startActivity(intent);

                    getOtpPassword();

                }


            }
        });
    }

    private void getOtpPassword() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("mobile_no", et_forgot_mno.getText().toString().trim());

//        Log.d("CP>requestBody>>", String.valueOf(requestBody));

        pDialog = new ProgressDialog(ForgotPasswordActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();


        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<ForgotPasswordResponse> call = request.getForgotPassword(requestBody);
        call.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {

                if (response.isSuccessful()) {

//                    Log.d("Forgot_Pwd>>>", String.valueOf(response.body().getMessage()));

                    if (response.body().getStatus().matches("200")) {

                        List<ForgotPasswordResponse.OtpInfo> otpInfoList = response.body().getOtpInfo();
                        if (otpInfoList != null && !otpInfoList.isEmpty() && otpInfoList.size() > 0) {
                            for (int i = 0; i < otpInfoList.size(); i++) {

                                String id = otpInfoList.get(i).getId();
                                String forgotOtp = otpInfoList.get(i).getForgotOtp();
                                String mobileNo = otpInfoList.get(i).getMobileNo();

                                Intent intent = new Intent(ForgotPasswordActivity.this, VerifyOtpActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("forgot_otp", forgotOtp);
                                intent.putExtra("mobile_no", mobileNo);
                                startActivity(intent);

                            }

                        }

                    }

                }

                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }

        });

    }

    private void goToLogin() {
        btn_login.setOnClickListener(v -> startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class)));
    }

    private void findView() {
        btn_sendotp = findViewById(R.id.btn_sendotp);
        btn_login = findViewById(R.id.btn_login_forgot);
        et_forgot_mno = findViewById(R.id.et_forgot_mno);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
    }


}