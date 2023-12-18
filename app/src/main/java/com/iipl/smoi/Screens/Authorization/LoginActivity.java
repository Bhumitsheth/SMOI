package com.iipl.smoi.Screens.Authorization;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.LoginResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    boolean isPasswordok = false;
    LinearLayout ll_go_to_home;
    Button btn_login;
    TextView tv_forgotpwd;
    EditText et_username, et_password;
    String str_et_username, str_et_password;
    ConstraintLayout constraint_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        constraint_layout = findViewById(R.id.constraint_layout);

        btn_login = findViewById(R.id.btn_login);
        tv_forgotpwd = findViewById(R.id.tv_forgotpwd);
        ll_go_to_home = findViewById(R.id.ll_go_to_home);
        et_username = findViewById(R.id.etUsername);
        et_password = findViewById(R.id.etPassword);

        tv_forgotpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        ll_go_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLogin();
            }
        });


    }

    private void getLogin() {

        str_et_username = et_username.getText().toString().trim();
        str_et_password = et_password.getText().toString().trim();


        if (str_et_username.isEmpty()) {
            et_username.requestFocus();
            Toast.makeText(LoginActivity.this, "Please Enter Your Username", Toast.LENGTH_LONG).show();
        } else if (str_et_password.isEmpty()) {
            et_password.requestFocus();
            Toast.makeText(LoginActivity.this, "Please Enter Your Password", Toast.LENGTH_LONG).show();
        } else {

            isPasswordok = checkpassword();

            if (isPasswordok) {
                ConnectivityManager connectivityManager = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                }

                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("email", str_et_username);
                    requestBody.put("password", str_et_password);

//                        Log.d("Login>>>", String.valueOf(requestBody));

                    pDialog = new ProgressDialog(LoginActivity.this, R.style.MyAlertDialogStyle);
                    pDialog.setMessage("Please wait...");
                    pDialog.setCancelable(false);
                    pDialog.show();

                    APIInterface request = APIClient.getClient().create(APIInterface.class);
                    Call<LoginResponse> call = request.getLogin(requestBody);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                            if (response.isSuccessful()) {

                                if (response.body().getStatusCode().matches("400")) {
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                                }

                                if (response.body().getStatusCode().matches("200")) {

//                                        Log.d("Login>>>", response.body().getMessage());

                                    List<LoginResponse.UserInfo> userInfoList = response.body().getUserInfo();
                                    if (userInfoList != null && !userInfoList.isEmpty() && userInfoList.size() > 0) {
                                        for (int i = 0; i < userInfoList.size(); i++) {

                                            String str_id = userInfoList.get(i).getId();
                                            String str_roll_id = userInfoList.get(i).getRoleId();
                                            String str_token = userInfoList.get(i).getSessionToken();
                                            String str_username = userInfoList.get(i).getUsername();
                                            String firstName = userInfoList.get(i).getFirstName();
                                            String lastName = userInfoList.get(i).getLastName();
                                            String address = userInfoList.get(i).getAddress();
                                            String mobileNo = userInfoList.get(i).getMobileNo();
                                            String city = userInfoList.get(i).getCity();
                                            String state = userInfoList.get(i).getState();
                                            String pincode = userInfoList.get(i).getPincode();
                                            String str_email = userInfoList.get(i).getEmail();
                                            String str_area = userInfoList.get(i).getArea();
                                            String profileImage = userInfoList.get(i).getProfileImage();

                                            String contactPerson = userInfoList.get(i).getContactPerson();

                                            String companyName = userInfoList.get(i).getCompanyName();
                                            String area = userInfoList.get(i).getArea();
                                            String telephone1 = userInfoList.get(i).getTelephone1();
                                            String referenceNo = userInfoList.get(i).getReferenceNo();

//                                                Log.d("str_roll_id::>>", str_roll_id + " " + str_id);

//                                                sendData(str_token);

                                            SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                            editor.putString(AppConstant.TAG_USER_ID, str_id);
                                            editor.putString(AppConstant.TAG_ROLL_ID, str_roll_id);
                                            editor.putString(AppConstant.TAG_TOKEN, str_token);
                                            editor.putString(AppConstant.TAG_USER_NAME, str_username);
                                            editor.putString(AppConstant.TAG_firstName, firstName);
                                            editor.putString(AppConstant.TAG_lastName, lastName);
                                            editor.putString(AppConstant.TAG_address, address);
                                            editor.putString(AppConstant.TAG_mobileNo, mobileNo);
                                            editor.putString(AppConstant.TAG_city, city);
                                            editor.putString(AppConstant.TAG_state, state);
                                            editor.putString(AppConstant.TAG_pincode, pincode);
                                            editor.putString(AppConstant.TAG_EMAIL, str_email);
                                            editor.putString(AppConstant.TAG_AREA, str_area);
                                            editor.putString(AppConstant.TAG_PROFILE_IMAGE, profileImage);
                                            //New
                                            editor.putString(AppConstant.TAG_CONTACT_PERSON, contactPerson);
                                            editor.putString(AppConstant.TAG_companyName, companyName);
                                            editor.putString(AppConstant.TAG_telephone1, telephone1);
                                            editor.putString(AppConstant.TAG_Reference_No, referenceNo);
                                            editor.commit();

                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            if (pDialog.isShowing()) {
                                pDialog.dismiss();
                            }
                            Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
                        }

                    });


                } else {

                    Toast.makeText(LoginActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();

                }


            }

        }

    }



  /*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (FragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentToActivity");
        }
    }


    public void onDetach() {
        this.mCallback = null;
        super.onDetach();
    }*/

    private boolean checkpassword() {
        if (et_password.length() < 6) {
//            et_password.setError("Password must be minimum 6 characters");
            Toast.makeText(getApplicationContext(), "Password must be minimum 6 characters", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }


}