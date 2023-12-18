package com.iipl.smoi.Screens.Authorization;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iipl.smoi.BroadcastReceiver.SmsBroadcastReceiver;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.ForgotPasswordResponse;
import com.iipl.smoi.R;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    ProgressDialog pDialog;

    private EditText[] editTexts;

    private static final int REQ_USER_CONSENT = 200;
    SmsBroadcastReceiver smsBroadcastReceiver;


    EditText otpET1, otpET2, otpET3, otpET4;

    String otp1, otp2, otp3, otp4;

    Button btn_verify;

    TextView tv_forgot_mno,tv_resendOTP;

    String TAG_userId, TAG_forgotOtp, TAG_mobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        btn_verify = findViewById(R.id.btn_verify);
        tv_forgot_mno = findViewById(R.id.tv_forgot_mno);
        tv_resendOTP = findViewById(R.id.tv_resendOTP);

        otpET1 = findViewById(R.id.otpET1);
        otpET2 = findViewById(R.id.otpET2);
        otpET3 = findViewById(R.id.otpET3);
        otpET4 = findViewById(R.id.otpET4);

        Intent intent = getIntent();

        if (!(intent == null)) {
            TAG_userId = intent.getStringExtra("id");
            TAG_forgotOtp = intent.getStringExtra("forgot_otp");
            TAG_mobileNo = intent.getStringExtra("mobile_no");
        }

        tv_resendOTP.setOnClickListener( v -> {
            reSendOtp();
        });


//        Log.d("otp>>",str_otp);
//        getOtpFromMessage(str_otp);
// Temporarily  set otp to function

//        getOtpFromMessage("4321");

//        startSmsUserConsent();

        editTexts = new EditText[]{otpET1, otpET2, otpET3, otpET4};

        otpET1.addTextChangedListener(new PinTextWatcher(0));
        otpET2.addTextChangedListener(new PinTextWatcher(1));
        otpET3.addTextChangedListener(new PinTextWatcher(2));
        otpET4.addTextChangedListener(new PinTextWatcher(3));

        otpET1.setOnKeyListener(new PinOnKeyListener(0));
        otpET2.setOnKeyListener(new PinOnKeyListener(1));
        otpET3.setOnKeyListener(new PinOnKeyListener(2));
        otpET4.setOnKeyListener(new PinOnKeyListener(3));


        //add blank space in mobile no
        String x = TAG_mobileNo;
        x = x.substring(0, 5) + " " + x.substring(5, 10);
        tv_forgot_mno.setText(x);


        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                otp1 = otpET1.getText().toString();
                otp2 = otpET2.getText().toString();
                otp3 = otpET3.getText().toString();
                otp4 = otpET4.getText().toString();

                String otp = otp1 + otp2 + otp3 + otp4;

                if (otp.equalsIgnoreCase(TAG_forgotOtp)) {
                    Intent i = new Intent(VerifyOtpActivity.this, ChangePasswordActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(VerifyOtpActivity.this, "Please Enter Correct Otp", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void reSendOtp() {
        pDialog = new ProgressDialog(VerifyOtpActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("mobile_no", TAG_mobileNo);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<ForgotPasswordResponse> call = request.getForgotPassword(requestBody);
        call.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgotPasswordResponse> call,
                                   @NonNull Response<ForgotPasswordResponse> response) {

                if (response.isSuccessful()) {

//                    Log.d("Forgot_Pwd>>>", String.valueOf(response.body().getMessage()));

                    if (response.body().getStatus().matches("200")) {

                        Toast.makeText(VerifyOtpActivity.this, "Otp Resend Successfully", Toast.LENGTH_SHORT).show();

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

    public class PinTextWatcher implements TextWatcher {

        private int currentIndex;
        private boolean isFirst = false, isLast = false;
        private String newTypedString = "";

        PinTextWatcher(int currentIndex) {
            this.currentIndex = currentIndex;

            if (currentIndex == 0)
                this.isFirst = true;
            else if (currentIndex == editTexts.length - 1)
                this.isLast = true;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            newTypedString = s.subSequence(start, start + count).toString().trim();
        }

        @Override
        public void afterTextChanged(Editable s) {

            String text = newTypedString;

            /* Detect paste event and set first char */

            if (text.length() > 1)
                text = String.valueOf(text.charAt(0)); // TODO: We can fill out other EditTexts

            editTexts[currentIndex].removeTextChangedListener(this);
            editTexts[currentIndex].setText(text);
            editTexts[currentIndex].setSelection(text.length());
            editTexts[currentIndex].addTextChangedListener(this);

            if (text.length() == 1)
                moveToNext();
            else if (text.length() == 0)
                moveToPrevious();
        }

        private void moveToNext() {
            if (!isLast)
                editTexts[currentIndex + 1].requestFocus();

            if (isAllEditTextsFilled() && isLast) { // isLast is optional
//                editTexts[currentIndex].clearFocus();
                hideKeyboard();
            }
        }

        private void moveToPrevious() {
            if (!isFirst)
                editTexts[currentIndex - 1].requestFocus();
        }

        private boolean isAllEditTextsFilled() {
            for (EditText editText : editTexts)
                if (editText.getText().toString().trim().length() == 0)
                    return false;
            return true;
        }

        private void hideKeyboard() {
            if (getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }

    }

    public class PinOnKeyListener implements View.OnKeyListener {

        private int currentIndex;

        PinOnKeyListener(int currentIndex) {
            this.currentIndex = currentIndex;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (editTexts[currentIndex].getText().toString().isEmpty() && currentIndex != 0)
                    editTexts[currentIndex - 1].requestFocus();
            }
            return false;
        }

    }

    private void startSmsUserConsent() {
        SmsRetrieverClient client = SmsRetriever.getClient(this);

        client.startSmsUserConsent(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
//                Toast.makeText(getApplicationContext(), "On Success", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(), "On OnFailure", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_USER_CONSENT) {
            if ((resultCode == RESULT_OK) && (data != null)) {
                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//                textViewMessage.setText(String.format("%s - %s", getString(R.string.received_message), message));
                getOtpFromMessage(message);

            }
        }
    }

    private void getOtpFromMessage(String message) {
        Pattern pattern = Pattern.compile("(|^)\\d{4}");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            Log.e("otp>>", matcher.group(0));

            for (int i = 0; i < matcher.group().length(); i++) {

                String s1 = String.valueOf(matcher.group().charAt(0));
                String s2 = String.valueOf(matcher.group().charAt(1));
                String s3 = String.valueOf(matcher.group().charAt(2));
                String s4 = String.valueOf(matcher.group().charAt(3));

                otpET1.setText(s1);
                otpET2.setText(s2);
                otpET3.setText(s3);
                otpET4.setText(s4);

//                onClickbtndone();

            }
        }
    }

    private void registerBroadcastReceiver() {
        smsBroadcastReceiver = new SmsBroadcastReceiver();
        smsBroadcastReceiver.smsBroadcastReceiverListener =
                new SmsBroadcastReceiver.SmsBroadcastReceiverListener() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, REQ_USER_CONSENT);
                    }

                    @Override
                    public void onFailure() {
                    }
                };
        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(smsBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBroadcastReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(smsBroadcastReceiver);
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
//            System.gc();
//            finishAffinity();
//            System.exit(0);
            startActivity(new Intent(VerifyOtpActivity.this, MainActivity.class));
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

}