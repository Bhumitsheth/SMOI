package com.iipl.smoi.Screens.ActivityActions;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.iipl.smoi.Adapter.PreferredStallAdapter;
import com.iipl.smoi.Adapter.StallSelectionAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.AddStallResponse;
import com.iipl.smoi.Model.OtherModel.StallSelectionModel;
import com.iipl.smoi.Model.StallsBookResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Utils.RealPathUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StallNoBookingActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_back_stall_rent, img_transaction;
//    private ImageView image_view_Booked_stall_list;
//    private NestedScrollView scrollview;
//    private LinearLayout ll_main;
//    private LinearLayout ll_stall_no;
//    private TextView tv_stall_no;
//    private TextInputEditText et_stall_rent;

    private TextInputEditText et_transaction_id;
    private TextInputEditText et_transaction_image;

    //    private TextInputEditText et_gst_per;
//    private TextInputLayout et_gst_amount_ll;
//    private TextInputEditText et_gst_amount;
//    private TextInputEditText et_total;
    private MaterialButton btn_add_more_stall, btn_stall_booking_submit;
    //    private TextView tv_view_booked_stall;
    private LinearLayout ll_transaction, ll_payment_method;

    private RadioGroup radioGroup;
//    private RadioButton rb_qrcode_upi;
//    private RadioButton rb_neft;
//    private RadioButton rb_rtgs;
//    private RadioButton rb_bank_transfer;
//    private RadioButton rb_other;


    static String TAG_EXPO_ID;
    int STALL_DELETE_POSITION;

    MaterialCardView mcv_book_stall_no;


    //ArrayList
    ArrayList<String> stringNoOfStallArrayList = new ArrayList<>();
    ArrayList<String> stringPreferredStallNoArrayList = new ArrayList<>();
    ArrayList<String> stringStallRentArrayList = new ArrayList<>();
    ArrayList<String> stringGstPerArrayList = new ArrayList<>();
    ArrayList<String> stringGstAmountArrayList = new ArrayList<>();
    ArrayList<String> stringTotalArrayList = new ArrayList<>();

    ArrayList<String> stallBucketArrayList = new ArrayList<>();

    ArrayList<Integer> integerTotalArrayList = new ArrayList<>();

    private final ArrayList<StallsBookResponse.ExpoBooking.RemainingStall> stallsBookArrayList = new ArrayList<>();

    ArrayList<StallSelectionModel> stallSelectionArrayList = new ArrayList<>();

    //Adapter
    StallSelectionAdapter stallSelectionAdapter;
    PreferredStallAdapter preferredStallAdapter;

    //Variables
    int TAG_TOTAL_PAYMENT = 0;

    String TAG_PAYMENT_METHOD = "";

    int TRANSACTION_IMAGE = 101;
    static String transaction_image_path = "";

    RecyclerView recyclerView;

    String str_expo_id,
            str_reference_no,
            str_name_of_the_owner,
            str_name_of_the_company,
            str_door_no,
            str_street_no_or_name,
            str_location,
            str_area,
            str_city,
            str_district,
            str_state,
            str_pincode,
            str_contact_person,
            str_telephone_no,
            str_mobileno,
            str_email,
            str_company_status,
            str_product_covered,
            str_product_speciality,
            str_awards,
            str_specific_info,
            str_signature_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stall_no_booking);

        //View id
        findViews();

        //get Stall Booking Data
        getAddStallBookingData();

        //Payment Radio Group
        setRadioGroup();

//        getTransactionImage();


        recyclerView = findViewById(R.id.rv_book_stall_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(StallNoBookingActivity.this, LinearLayoutManager.VERTICAL, false));

        stallSelectionAdapter = new StallSelectionAdapter(StallNoBookingActivity.this, stallSelectionArrayList, position -> {
//            stallSelectionArrayList.remove(stallSelectionArrayList.get(position));
//            stallSelectionAdapter.notifyDataSetChanged();
//            stallSelectionAdapter.updateList(stallSelectionArrayList);

//           /* stringNoOfStallArrayList.remove(position);
//            stallBucketArrayList.remove(position);
//            stringStallRentArrayList.remove(position);
//            stringGstPerArrayList.remove(position);
//            stringGstAmountArrayList.remove(position);
//            stringTotalArrayList.remove(position);
//
//            for (String s : stringPreferredStallNoArrayList) {
//                if (!stringPreferredStallNoArrayList.contains(stallBucketArrayList)) {
//                    Log.d("new_spinner::>>>>>", String.valueOf(stringPreferredStallNoArrayList));
//                }
//            }*/
//            Log.d("DeleteAfter:::>>", (stallBucketArrayList)+" "+(stringTotalArrayList));
//            Log.d("DeleteAftermain:::>>", String.valueOf(stringPreferredStallNoArrayList));
        });
        recyclerView.setAdapter(stallSelectionAdapter);
//        stallSelectionAdapter.updateList(stallSelectionArrayList);


        //from Expo Adapter
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            TAG_EXPO_ID = bundle.getString("expo_id");
        }

        img_back_stall_rent.setOnClickListener(view -> finish());

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            stallSelectionApi();

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }


    }

    @SuppressLint("NonConstantResourceId")
    private void setRadioGroup() {
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
            TAG_PAYMENT_METHOD = radioButton.getText().toString();
            Log.d("radioButton>>", radioButton.getText().toString());

            switch (checkedId) {
                case R.id.rb_qrcode_upi:
                    ll_transaction.setVisibility(View.VISIBLE);
                    break;
                case R.id.rb_neft:
                case R.id.rb_rtgs:
                case R.id.rb_bank_transfer:
                case R.id.rb_other:
                    ll_transaction.setVisibility(View.GONE);
                    break;
            }

        });
    }

    private void stallSelectionApi() {

        //Start Stall Selection Api Call
        final ProgressDialog pDialog = new ProgressDialog(StallNoBookingActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = StallNoBookingActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);
        String userId = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String rollId = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("login_id", userId);
        requestBody.put("role_id", rollId);
        requestBody.put("expo_id", TAG_EXPO_ID);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<StallsBookResponse> call = request.AddStallBooking(str_token, requestBody);
        call.enqueue(new Callback<StallsBookResponse>() {
            @Override
            public void onResponse(@NonNull Call<StallsBookResponse> call, @NonNull Response<StallsBookResponse> response) {

                stallsBookArrayList.clear();

                if (response.isSuccessful()) {


                    if (response.body().getStatus().matches("200")) {

                        List<StallsBookResponse.ExpoBooking> expoBookings = response.body().getExpoBookings();
                        if (expoBookings != null && !expoBookings.isEmpty()) {
                            for (int i = 0; i < expoBookings.size(); i++) {
                                String availableStalls = expoBookings.get(i).getAvailableStalls();
                                String titleEn = expoBookings.get(i).getTitleEn();

                                StallsBookResponse.ExpoBooking expoBooking = new StallsBookResponse.ExpoBooking();
                                expoBooking.setAvailableStalls(availableStalls);
                                expoBooking.setTitleEn(titleEn);

                                stringPreferredStallNoArrayList.clear();

                                List<StallsBookResponse.ExpoBooking.RemainingStall> remainingStall = expoBookings.get(i).getRemainingStalls();
                                if (remainingStall != null && !remainingStall.isEmpty()) {
                                    remainingStall.size();
                                    for (int j = 0; j < remainingStall.size(); j++) {
                                        String stallNo1 = remainingStall.get(j).getStallNo();
                                        String stallRent = remainingStall.get(j).getStallRent();
                                        String gst = remainingStall.get(j).getGst();
                                        String gstAmount = remainingStall.get(j).getGstAmount();
                                        String total = remainingStall.get(j).getTotal();

                                        stringPreferredStallNoArrayList.add(stallNo1);//No of Stall Number

                                        StallsBookResponse.ExpoBooking.RemainingStall remainingStall1 = new StallsBookResponse.ExpoBooking.RemainingStall();
                                        remainingStall1.setStallNo(stallNo1);
                                        remainingStall1.setStallRent(stallRent);
                                        remainingStall1.setGst(gst);
                                        remainingStall1.setGstAmount(gstAmount);
                                        remainingStall1.setTotal(total);

                                        stallsBookArrayList.add(remainingStall1);

                                   /* tv_stall_no.setText(remainingStall.get(0).getStallNo());
                                    et_stall_rent.setText(remainingStall.get(0).getStallRent());
                                    et_gst_per.setText(remainingStall.get(0).getGst());
                                    et_gst_amount.setText(remainingStall.get(0).getGstAmount());
                                    et_total.setText(remainingStall.get(0).getTotal());*/

                                    }

                                }

                            }

                        } else {

                            Toast.makeText(StallNoBookingActivity.this, "Stall List Not Available", Toast.LENGTH_SHORT).show();

                        }

                    }

                }

                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }

            @Override
            public void onFailure(@NonNull Call<StallsBookResponse> call, @NonNull Throwable t) {
                Log.e("tag::>>>>", "expoBookings", t);
//                Log.d("AddExpoTAG_Success::>>", call.toString() + " " + t.toString());
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
            }
        });
        //End Stall Selection Api Call


    }

    private void getAddStallBookingData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            str_expo_id = bundle.getString("expo_id");
            str_reference_no = bundle.getString("et_reference_no");
            str_name_of_the_owner = bundle.getString("et_name_of_the_owner");
            str_name_of_the_company = bundle.getString("et_name_of_the_company");
            str_door_no = bundle.getString("et_door_no");
            str_street_no_or_name = bundle.getString("et_street_no_or_name");
            str_location = bundle.getString("et_location");
            str_area = bundle.getString("et_area");
            str_city = bundle.getString("et_city");
            str_district = bundle.getString("et_district");
            str_state = bundle.getString("tv_state");
            str_pincode = bundle.getString("et_pincode");
            str_contact_person = bundle.getString("et_contact_person");
            str_telephone_no = bundle.getString("et_telephone_no");
            str_mobileno = bundle.getString("et_mobileno");
            str_email = bundle.getString("et_email");
            str_company_status = bundle.getString("tv_company_status");
            str_product_covered = bundle.getString("et_product_covered");
            str_product_speciality = bundle.getString("et_product_speciality");
            str_awards = bundle.getString("et_awards");
            str_specific_info = bundle.getString("et_specific_info");
            str_signature_path = bundle.getString("signature_path");
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private void getDialogBox() {
        final AlertDialog.Builder alert = new MaterialAlertDialogBuilder(StallNoBookingActivity.this, R.style.MyRounded_MaterialComponents_MaterialAlertDialog);
        View customLayout = getLayoutInflater().inflate(R.layout.stall_selection_dialog, null);
//            alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            customLayout.setBackgroundResource(android.R.color.transparent);

        TextView tv_stall_no = customLayout.findViewById(R.id.tv_stall_no);
        LinearLayout ll_stall_no = customLayout.findViewById(R.id.ll_stall_no);

        EditText et_stall_rent = customLayout.findViewById(R.id.et_stall_rent);
        EditText et_gst_per = customLayout.findViewById(R.id.et_gst_per);
        EditText et_gst_amount = customLayout.findViewById(R.id.et_gst_amount);
        EditText et_total = customLayout.findViewById(R.id.et_total);


        MaterialButton btn_cancel = customLayout.findViewById(R.id.btn_cancel);
        MaterialButton btn_submit = customLayout.findViewById(R.id.btn_submit);

        alert.setView(customLayout);
        final AlertDialog alertDialog = alert.create();

        alertDialog.setCanceledOnTouchOutside(false);

        //Start Set Spinner String
        ll_stall_no.setOnClickListener(view -> {

            Dialog dialog = new Dialog(StallNoBookingActivity.this);
            dialog.setContentView(R.layout.dialog_searchable_spinner);
            dialog.getWindow().setLayout(700, 1500);
//            dialog.getWindow().setStatusBarColor(R.color.appcolor);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                dialog.getWindow().setStatusBarColor(getResources().getColor(R.color.appcolor));
                dialog.getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.show();
            TextView selectState = dialog.findViewById(R.id.tv_select);
            EditText editText = dialog.findViewById(R.id.edit_text);
//            ProgressBar progressBar = dialog.findViewById(R.id.progress_circular);
//            ListView listView = dialog.findViewById((R.id.list_view);

            RecyclerView recyclerView_stall_list = dialog.findViewById(R.id.rv_stall_list);

            editText.setVisibility(View.GONE);

            selectState.setText(R.string.preferred_stall_no);

            tv_stall_no.setText(R.string.preferred_stall_no);


            //Start New Model Class Attached Recyclerview
            recyclerView_stall_list.setLayoutManager(new LinearLayoutManager(StallNoBookingActivity.this, LinearLayoutManager.VERTICAL, false));

            preferredStallAdapter = new PreferredStallAdapter(StallNoBookingActivity.this, stringPreferredStallNoArrayList, position -> {

                STALL_DELETE_POSITION = position;

                String s = stringPreferredStallNoArrayList.get(position);

                tv_stall_no.setText(s);
//                Log.d("tv_stall_no::>>>>>", s);

                for (StallsBookResponse.ExpoBooking.RemainingStall remainingStall : stallsBookArrayList) {
                    String stallNo = remainingStall.getStallNo();
//                    String stallRent = remainingStall.getStallRent();
//                    String gst = remainingStall.getGst();
//                    String gstAmount = remainingStall.getGstAmount();
//                    String total = remainingStall.getTotal();

                    if (stallNo.equalsIgnoreCase(tv_stall_no.getText().toString())) {
                        String stallNo1 = remainingStall.getStallNo();
                        String stallRent1 = remainingStall.getStallRent();
                        String gst1 = remainingStall.getGst();
                        String gstAmount1 = remainingStall.getGstAmount();
                        String total1 = remainingStall.getTotal();

                        tv_stall_no.setText(stallNo1);
                        et_stall_rent.setText(stallRent1);
                        et_gst_per.setText(gst1);
                        et_gst_amount.setText(gstAmount1);
                        et_total.setText(total1);
                    }
                }
                dialog.dismiss();
            });
            recyclerView_stall_list.setAdapter(preferredStallAdapter);
            //End New Model Class Attached Recyclerview


            //End Set Spinner String
        });

        btn_cancel.setOnClickListener(v -> alertDialog.dismiss());
        btn_submit.setOnClickListener(v -> {
            alertDialog.dismiss();

            btn_stall_booking_submit.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);

            String noOfStall = "1";//No Of Stall By Default
            String preferredStallNo = tv_stall_no.getText().toString();
            String stall_rent = et_stall_rent.getText().toString();
            String gst_per = et_gst_per.getText().toString();
//                String gst_amount = et_gst_amount.getText().toString();
//                String total = et_total.getText().toString();

            int gstPer = Integer.parseInt(et_gst_per.getText().toString());
            int rent = Integer.parseInt(et_stall_rent.getText().toString());
            String gst_amount = String.valueOf((rent * gstPer) / 100);

            int gstAmount = Integer.parseInt(gst_amount);
            String total = String.valueOf((rent + gstAmount));

            stallSelectionArrayList.add(new StallSelectionModel(noOfStall, preferredStallNo, stall_rent, gst_per, gst_amount, total));
            stallSelectionAdapter.notifyDataSetChanged();
            stallSelectionAdapter.updateList(stallSelectionArrayList);

            stringNoOfStallArrayList.add(noOfStall);
            stallBucketArrayList.add(preferredStallNo);
            stringStallRentArrayList.add(stall_rent);
            stringGstPerArrayList.add(gst_per);
            stringGstAmountArrayList.add(gst_amount);
            stringTotalArrayList.add(total);

            Log.d("Final_stallBucketArrayList>>", String.valueOf(stallBucketArrayList));

            integerTotalArrayList.clear();
            integerTotalArrayList.add(Integer.valueOf(total));
            for (int i = 0; i < integerTotalArrayList.size(); i++) {
                TAG_TOTAL_PAYMENT += integerTotalArrayList.get(i);
            }

            Log.d("TAG_TOTAL_PAYMENT>>>", String.valueOf(TAG_TOTAL_PAYMENT));

            Log.d("integerTotalArrayList:::>>", String.valueOf(integerTotalArrayList));

            Log.d("BookStall>>>", noOfStall + " " +
                    preferredStallNo + " " +
                    stall_rent + " " +
                    gst_per + " " +
                    gst_amount + " " +
                    total);

            Log.d("total>>", String.valueOf(stringTotalArrayList));

            stringPreferredStallNoArrayList.remove(STALL_DELETE_POSITION);
            preferredStallAdapter.notifyDataSetChanged();
//                Log.d("stringPreferredStallNoArrayList::>>>>>", String.valueOf(stringPreferredStallNoArrayList));


            for (int i = 0; i < stringPreferredStallNoArrayList.size(); i++) {
                if (stringPreferredStallNoArrayList.get(i).equalsIgnoreCase(tv_stall_no.getText().toString().trim())) {
                    String bucket_data = stringPreferredStallNoArrayList.get(i);
//                        Log.d("delete_data::>>>>>",bucket_data);
                    stallBucketArrayList.add(bucket_data);
//                        Log.d("stallBucketArrayList::>>>>>", String.valueOf(stallBucketArrayList));
                }

            }

            //Same Element Remove from stringPreferredStallNoArrayList
            for (String s : stringPreferredStallNoArrayList) {
//                    System.out.println(s);
                if (!stringPreferredStallNoArrayList.contains(stallBucketArrayList)) {
                    Log.d("spinner::>>>>>", String.valueOf(stringPreferredStallNoArrayList));
                }
            }


        });
        alertDialog.show();
    }


    private void findViews() {
        img_back_stall_rent = (ImageView) findViewById(R.id.img_back_stall_rent);
//        image_view_Booked_stall_list = (ImageView) findViewById(R.id.image_view_Booked_stall_list);
//        scrollview = (NestedScrollView) findViewById(R.id.scrollview);
//        ll_main = (LinearLayout) findViewById(R.id.ll_main);
//        ll_stall_no = (LinearLayout) findViewById(R.id.ll_stall_no);
//        tv_stall_no = (TextView) findViewById(R.id.tv_stall_no);
//        et_stall_rent = (TextInputEditText) findViewById(R.id.et_stall_rent);
//        et_gst_per = (TextInputEditText) findViewById(R.id.et_gst_per);
//        et_gst_amount_ll = (TextInputLayout) findViewById(R.id.et_gst_amount_ll);
//        et_gst_amount = (TextInputEditText) findViewById(R.id.et_gst_amount);
//        et_total = (TextInputEditText) findViewById(R.id.et_total);

        et_transaction_id = (TextInputEditText) findViewById(R.id.et_transaction_id);
        et_transaction_image = (TextInputEditText) findViewById(R.id.et_transaction_image);

        img_transaction = (ImageView) findViewById(R.id.img_transaction);

        btn_add_more_stall = (MaterialButton) findViewById(R.id.btn_add_more_stall);
        btn_stall_booking_submit = (MaterialButton) findViewById(R.id.btn_stall_booking_submit);

        ll_transaction = (LinearLayout) findViewById(R.id.ll_transaction);

        ll_payment_method = (LinearLayout) findViewById(R.id.ll_payment_method);

//        tv_view_booked_stall = (TextView) findViewById(R.id.tv_view_booked_stall);
        mcv_book_stall_no = (MaterialCardView) findViewById(R.id.mcv_book_stall_no);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
//        rb_qrcode_upi = (RadioButton) findViewById(R.id.rb_qrcode_upi);
//        rb_neft = (RadioButton) findViewById(R.id.rb_neft);
//        rb_rtgs = (RadioButton) findViewById(R.id.rb_rtgs);
//        rb_bank_transfer = (RadioButton) findViewById(R.id.rb_bank_transfer);
//        rb_other = (RadioButton) findViewById(R.id.rb_other);


        btn_add_more_stall.setOnClickListener(this);
        btn_stall_booking_submit.setOnClickListener(this);
        et_transaction_image.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if (v == btn_add_more_stall) {
            // Handle clicks for btn_add_more_stall
            //get DialogBox
            getDialogBox();
        } else if (v == btn_stall_booking_submit) {
            // Handle clicks for btn_submit
//            submitStall();

            if (TAG_PAYMENT_METHOD.length() == 0) {
                Toast.makeText(StallNoBookingActivity.this, "Select Payment Method", Toast.LENGTH_SHORT).show();
            } else {
                submitStall();
            }


        } else if (v == et_transaction_image) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, TRANSACTION_IMAGE);
        }


    }

    private void submitStall() {
        final ProgressDialog pDialog = new ProgressDialog(StallNoBookingActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);
        String userId = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String rollId = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);

        final RequestBody rb_expo_id = RequestBody.create(str_expo_id, MediaType.parse("multipart/form-file"));
        final RequestBody rb_userId = RequestBody.create(MediaType.parse("multipart/form-file"), userId);
        final RequestBody rb_reference_no = RequestBody.create(MediaType.parse("multipart/form-file"), str_reference_no);
        final RequestBody rb_name_of_the_owner = RequestBody.create(MediaType.parse("multipart/form-file"), str_name_of_the_owner);
        final RequestBody rb_name_of_the_company = RequestBody.create(MediaType.parse("multipart/form-file"), str_name_of_the_company);
        final RequestBody rb_door_no = RequestBody.create(MediaType.parse("multipart/form-file"), str_door_no);
        final RequestBody rb_street_no_or_name = RequestBody.create(MediaType.parse("multipart/form-file"), str_street_no_or_name);
        final RequestBody rb_location = RequestBody.create(MediaType.parse("multipart/form-file"), str_location);
        final RequestBody rb_area = RequestBody.create(MediaType.parse("multipart/form-file"), str_area);
        final RequestBody rb_city = RequestBody.create(MediaType.parse("multipart/form-file"), str_city);
        final RequestBody rb_district = RequestBody.create(MediaType.parse("multipart/form-file"), str_district);
        final RequestBody rb_state = RequestBody.create(MediaType.parse("multipart/form-file"), str_state);
        final RequestBody rb_pincode = RequestBody.create(MediaType.parse("multipart/form-file"), str_pincode);
        final RequestBody rb_contact_person = RequestBody.create(MediaType.parse("multipart/form-file"), str_contact_person);
        final RequestBody rb_telephone_no = RequestBody.create(MediaType.parse("multipart/form-file"), str_telephone_no);
        final RequestBody rb_mobileno = RequestBody.create(MediaType.parse("multipart/form-file"), str_mobileno);
        final RequestBody rb_email = RequestBody.create(MediaType.parse("multipart/form-file"), str_email);
        final RequestBody rb_company_status = RequestBody.create(MediaType.parse("multipart/form-file"), str_company_status);
        final RequestBody rb_product_covered = RequestBody.create(MediaType.parse("multipart/form-file"), str_product_covered);
        final RequestBody rb_product_speciality = RequestBody.create(MediaType.parse("multipart/form-file"), str_product_speciality);
        final RequestBody rb_awards = RequestBody.create(MediaType.parse("multipart/form-file"), str_awards);
        final RequestBody rb_specific_info = RequestBody.create(MediaType.parse("multipart/form-file"), str_specific_info);


        //Start List Data
        Gson gson = new GsonBuilder().create();
        JsonArray jsonArray_no_of_stall = gson.toJsonTree(stringNoOfStallArrayList).getAsJsonArray();
        JsonArray jsonArray_preferred_stall = gson.toJsonTree(stallBucketArrayList).getAsJsonArray();
        JsonArray jsonArray_stallRent = gson.toJsonTree(stringStallRentArrayList).getAsJsonArray();
        JsonArray jsonArray_gst = gson.toJsonTree(stringGstPerArrayList).getAsJsonArray();
        JsonArray jsonArray_gstAmount = gson.toJsonTree(stringGstAmountArrayList).getAsJsonArray();
        JsonArray jsonArray_total = gson.toJsonTree(stringTotalArrayList).getAsJsonArray();

        String str_jsonArray_no_of_stall = jsonArray_no_of_stall.toString();
        String str_jsonArray_preferred_stall = jsonArray_preferred_stall.toString();
        String str_jsonArray_stallRent = jsonArray_stallRent.toString();
        String str_jsonArray_gst = jsonArray_gst.toString();
        String str_jsonArray_gstAmount = jsonArray_gstAmount.toString();
        String str_jsonArray_total = jsonArray_total.toString();

        final RequestBody rb_no_of_stall = RequestBody.create(MediaType.parse("multipart/form-file"), str_jsonArray_no_of_stall);
        final RequestBody rb_preferred_stall = RequestBody.create(MediaType.parse("multipart/form-file"), str_jsonArray_preferred_stall);
        final RequestBody rb_stallRent = RequestBody.create(MediaType.parse("multipart/form-file"), str_jsonArray_stallRent);
        final RequestBody rb_gst = RequestBody.create(MediaType.parse("multipart/form-file"), str_jsonArray_gst);
        final RequestBody rb_gstAmount = RequestBody.create(MediaType.parse("multipart/form-file"), str_jsonArray_gstAmount);
        final RequestBody rb_total = RequestBody.create(MediaType.parse("multipart/form-file"), str_jsonArray_total);
        //End List Data

        final RequestBody rb_total_payment = RequestBody.create(MediaType.parse("multipart/form-file"), String.valueOf(TAG_TOTAL_PAYMENT));
        final RequestBody rb_declared = RequestBody.create(MediaType.parse("multipart/form-file"), "1");
        final RequestBody rb_currency = RequestBody.create(MediaType.parse("multipart/form-file"), "rupees");
        final RequestBody rb_payment_description = RequestBody.create(MediaType.parse("multipart/form-file"), "payment for booking stall");
        final RequestBody rb_payment_method = RequestBody.create(MediaType.parse("multipart/form-file"), TAG_PAYMENT_METHOD);

        File file = new File(str_signature_path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-file"), file);
        MultipartBody.Part part_signature = MultipartBody.Part.createFormData("signature", file.getName(), requestFile);

//        String str_transaction_id = et_transaction_id.getText().toString().trim();
//        final RequestBody rb_transaction_id = RequestBody.create(MediaType.parse("multipart/form-file"), str_transaction_id);


        RequestBody rb_transaction_Id;
        String tr_id = et_transaction_id.getText().toString();
        if (tr_id != null) {
            Log.d("tr_id_not_Null:>>", tr_id);
            rb_transaction_Id = RequestBody.create(MediaType.parse("multipart/form-file"), tr_id);

        } else {
            Log.d("tr_id:Null:>>", tr_id);
            rb_transaction_Id = RequestBody.create(MediaType.parse("multipart/form-file"), "");
            Log.d("tr_id::>>", String.valueOf(rb_transaction_Id));
        }

//        Log.d("TAG_PAYMENT_METHOD>>", TAG_PAYMENT_METHOD);
//        Log.d("rb_transaction_id>>",str_transaction_id);
//        Log.d("transaction_image_path>>",transaction_image_path);

//        File file1 = new File(transaction_image_path);
//        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-file"), file1);
//        MultipartBody.Part part_transaction_image = MultipartBody.Part.createFormData("transaction_image", file.getName(), requestFile1);


        MultipartBody.Part part_transaction_image = null;

        if (!transaction_image_path.isEmpty()) {
            try {
                File file1 = new File(transaction_image_path);
                if (file1.exists()) {
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"), file1);
                    part_transaction_image = MultipartBody.Part.createFormData("transaction_image", file1.getName(), requestBody);
                    Log.d("requestBody::>>", String.valueOf(part_transaction_image));
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            RequestBody attachmentEmpty = RequestBody.create(MediaType.parse("multipart/form-file"), "");
            part_transaction_image = MultipartBody.Part.createFormData("transaction_image", "", attachmentEmpty);
            Log.d("attachmentEmpty::>>", String.valueOf(part_transaction_image));
        }


        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<AddStallResponse> call = request.AddStall(token, rb_expo_id, rb_userId,
                rb_reference_no, rb_name_of_the_owner, rb_name_of_the_company, rb_door_no, rb_street_no_or_name, rb_location,
                rb_area, rb_city, rb_district, rb_state, rb_pincode, rb_contact_person, rb_telephone_no, rb_mobileno, rb_email,
                rb_company_status, rb_product_covered, rb_product_speciality, rb_awards, rb_specific_info,
                rb_no_of_stall, rb_preferred_stall, rb_stallRent, rb_gst, rb_gstAmount, rb_total,
                rb_total_payment, rb_declared, rb_currency, rb_payment_description, rb_payment_method,
                part_signature, rb_transaction_Id, part_transaction_image);
        call.enqueue(new Callback<AddStallResponse>() {
            @Override
            public void onResponse(Call<AddStallResponse> call, Response<AddStallResponse> response) {

                Log.d("AddExpoTAG_Success::>>", response.body().getMessage());

                if (response.body().getStatus().matches("400")) {
                    Toast.makeText(StallNoBookingActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {
                        Log.d("AddExpo>>", response.body().getStatus());
                        String s = response.body().getStatus();
                        String msg = response.body().getMessage();

                        Toast.makeText(StallNoBookingActivity.this, msg, Toast.LENGTH_LONG).show();

                        startActivity(new Intent(StallNoBookingActivity.this, ExpoListForStallBookingActivity.class));
                        finish();

                    }

                }

                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<AddStallResponse> call, Throwable t) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Log.d("AddExpoTAG_Success::>>", call + " " + t);
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
//                Log.e("tag::>>>>", t.toString());
//                Snackbar snackbar = Snackbar.make(parent_view, "Faqs Failure", Snackbar.LENGTH_LONG);
//                snackbar.show();
            }
        });

    }


    //Get Image From Mobile
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TRANSACTION_IMAGE && resultCode == Activity.RESULT_OK) {
            assert data != null;
            Uri uri = data.getData();
            Context context = StallNoBookingActivity.this;
            transaction_image_path = RealPathUtil.getRealPath(context, uri);
            Bitmap bitmap = BitmapFactory.decodeFile(transaction_image_path);
            img_transaction.setVisibility(View.VISIBLE);
            img_transaction.setImageBitmap(bitmap);
        }
    }


}