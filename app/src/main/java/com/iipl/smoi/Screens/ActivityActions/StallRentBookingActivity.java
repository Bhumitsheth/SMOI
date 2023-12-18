package com.iipl.smoi.Screens.ActivityActions;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.iipl.smoi.Adapter.BookedStallListAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.AddExpoResponse;
import com.iipl.smoi.Model.OtherModel.BookStallRentModel;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StallRentBookingActivity extends AppCompatActivity implements View.OnClickListener {


    BookedStallListAdapter bookStallRentAdapter;

    ArrayList<BookStallRentModel> bookStallRentArrayList = new ArrayList<>();

    RecyclerView recyclerView;

    TextInputLayout et_gst_amount_ll;

    MaterialCardView mcv_stall_details;

    private ImageView img_back_stall_rent;
    private ImageView image_view_stall_list;
    private TextInputEditText et_stall_no_from;
    private TextInputEditText et_stall_no_to;
    private TextInputEditText et_stall_rent;
    private TextInputEditText et_gst_per;
    private TextInputEditText et_gst_amount;
    private TextInputEditText et_total;
    private MaterialButton btn_addmore_stall;
    private TextView tv_view_stall_details, tv_reset;
    private MaterialButton btn_submit;

//    BookedStallListAdapter bookStallRentAdapter;

//    private ArrayList<BookStallRentModel> bookStallRentArrayList = new ArrayList<>();
//    private ArrayList<BookStallRentModel> UpdatedStallArrayList = new ArrayList<>();

//    private ArrayList<String> string = new ArrayList<>();

   /* List<MultipartBody.Part> stallNoFromPartList = new ArrayList<>();
    List<MultipartBody.Part> stallNoToPartList = new ArrayList<>();
    List<MultipartBody.Part> stallRentPartList = new ArrayList<>();
    List<MultipartBody.Part> gstPartList = new ArrayList<>();
    List<MultipartBody.Part> gstAmountPartList = new ArrayList<>();
    List<MultipartBody.Part> totalPartList = new ArrayList<>();*/


    ArrayList<String> stringStallNoFromArrayList = new ArrayList<>();
    ArrayList<String> stringStallNoToArrayList = new ArrayList<>();
    ArrayList<String> stringStallRentArrayList = new ArrayList<>();
    ArrayList<String> stringGstArrayList = new ArrayList<>();
    ArrayList<String> stringGstAmountArrayList = new ArrayList<>();
    ArrayList<String> stringTotalArrayList = new ArrayList<>();


    String chapterId, expoName, startDate, endDate, avilStall, stallSize, desc, slug,
            venue_address, venue_area, venue_state, venue_city, venue_pincode,
            chapter_contact_person, chapter_contact_no,
            smoi_letter_no,
            police_station_details,
            fire_station_details,
            income_tax_office_details,
            local_municipal_corp_details,
            insurance_company_details,
            expo_path,
            floor_path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stall_rent_booking);

        //view id
        findViews();

        //reset all data
        resetAll();

        //Mobile text watcher
        textWatcher();

        //get Expo Booking Data
        getAddExpoData();

        recyclerView = findViewById(R.id.rv_view_stall_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(StallRentBookingActivity.this, LinearLayoutManager.VERTICAL, false));


        bookStallRentAdapter = new BookedStallListAdapter(StallRentBookingActivity.this, bookStallRentArrayList, position -> {
            bookStallRentArrayList.remove(bookStallRentArrayList.get(position));
            bookStallRentAdapter.notifyDataSetChanged();
            bookStallRentAdapter.updateList(bookStallRentArrayList);
            stringStallNoFromArrayList.remove(position);
            stringStallNoToArrayList.remove(position);
            stringStallRentArrayList.remove(position);
            stringGstArrayList.remove(position);
            stringGstAmountArrayList.remove(position);
            stringTotalArrayList.remove(position);
            Log.d("DeleteAfter:::>>", String.valueOf(stringTotalArrayList));
            Log.d("DeleteAftermain:::>>", String.valueOf(bookStallRentArrayList));
            Log.d("pos:::>>", String.valueOf(position));
        });
        recyclerView.setAdapter(bookStallRentAdapter);
        bookStallRentAdapter.updateList(bookStallRentArrayList);


        tv_reset.setOnClickListener(view -> {
            resetAll();
        });


        img_back_stall_rent.setOnClickListener(view1 -> {
            finish();
//            startActivity(new Intent(StallRentBookingActivity.this, ExpoBookingActivity.class));
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {


        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }


    }

    private void getAddExpoData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            chapterId = bundle.getString("chapterId");
            expoName = bundle.getString("expoName");
            startDate = bundle.getString("startDate");
            endDate = bundle.getString("endDate");
            avilStall = bundle.getString("avilStall");
            stallSize = bundle.getString("stallSize");
            desc = bundle.getString("desc");
            slug = bundle.getString("slug");

            venue_address = bundle.getString("venue_address");
            venue_area = bundle.getString("venue_area");
            venue_state = bundle.getString("venue_state");
            venue_city = bundle.getString("venue_city");
            venue_pincode = bundle.getString("venue_pincode");

            chapter_contact_person = bundle.getString("chapter_contact_person");
            chapter_contact_no = bundle.getString("chapter_contact_no");

            smoi_letter_no = bundle.getString("smoi_letter_no");
            police_station_details = bundle.getString("police_station_details");
            fire_station_details = bundle.getString("fire_station_details");
            income_tax_office_details = bundle.getString("income_tax_office_details");
            local_municipal_corp_details = bundle.getString("local_municipal_corp_details");
            insurance_company_details = bundle.getString("insurance_company_details");
            expo_path = bundle.getString("expo_path");
            floor_path = bundle.getString("floor_path");

        }

    }

    private void AddExpo() {
        final ProgressDialog pDialog = new ProgressDialog(StallRentBookingActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);
        String userId = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String rollId = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);

        String chapter_contactPerson = sharedpreferences.getString(AppConstant.TAG_CONTACT_PERSON, null);
        String chapter_mobileNo = sharedpreferences.getString(AppConstant.TAG_mobileNo, null);
        String chapter_address = sharedpreferences.getString(AppConstant.TAG_address, null);
        String chapter_area = sharedpreferences.getString(AppConstant.TAG_AREA, null);
        String chapter_state = sharedpreferences.getString(AppConstant.TAG_state, null);
        String chapter_city = sharedpreferences.getString(AppConstant.TAG_city, null);
        String chapter_pincode = sharedpreferences.getString(AppConstant.TAG_pincode, null);

        final RequestBody rb_chapterId = RequestBody.create(MediaType.parse("multipart/form-file"), chapterId);
        final RequestBody rb_expoName = RequestBody.create(MediaType.parse("multipart/form-file"), expoName);
        final RequestBody rb_startDate = RequestBody.create(MediaType.parse("multipart/form-file"), startDate);
        final RequestBody rb_endDate = RequestBody.create(MediaType.parse("multipart/form-file"), endDate);
        final RequestBody rb_avilStall = RequestBody.create(MediaType.parse("multipart/form-file"), avilStall);
        final RequestBody rb_stallSize = RequestBody.create(MediaType.parse("multipart/form-file"), stallSize);
        final RequestBody rb_desc = RequestBody.create(MediaType.parse("multipart/form-file"), desc);
        final RequestBody rb_slug = RequestBody.create(MediaType.parse("multipart/form-file"), slug);

        final RequestBody rb_venue_address = RequestBody.create(MediaType.parse("multipart/form-file"), venue_address);
        final RequestBody rb_venue_area = RequestBody.create(MediaType.parse("multipart/form-file"), venue_area);
        final RequestBody rb_venue_state = RequestBody.create(MediaType.parse("multipart/form-file"), venue_state);
        final RequestBody rb_venue_city = RequestBody.create(MediaType.parse("multipart/form-file"), venue_city);
        final RequestBody rb_venue_pincode = RequestBody.create(MediaType.parse("multipart/form-file"), venue_pincode);

        final RequestBody rb_chapter_contactPerson = RequestBody.create(MediaType.parse("multipart/form-file"), chapter_contact_person);
        final RequestBody rb_chapter_mobileNo = RequestBody.create(MediaType.parse("multipart/form-file"), chapter_contact_no);
        final RequestBody rb_chapter_address = RequestBody.create(MediaType.parse("multipart/form-file"), chapter_address);
        final RequestBody rb_chapter_area = RequestBody.create(MediaType.parse("multipart/form-file"), chapter_area);
        final RequestBody rb_chapter_state = RequestBody.create(MediaType.parse("multipart/form-file"), chapter_state);
        final RequestBody rb_chapter_city = RequestBody.create(MediaType.parse("multipart/form-file"), chapter_city);
        final RequestBody rb_chapter_pincode = RequestBody.create(MediaType.parse("multipart/form-file"), chapter_pincode);

        final RequestBody rb_smoi_letter_no = RequestBody.create(MediaType.parse("multipart/form-file"), smoi_letter_no);
        final RequestBody rb_police_station_details = RequestBody.create(MediaType.parse("multipart/form-file"), police_station_details);
        final RequestBody rb_fire_station_details = RequestBody.create(MediaType.parse("multipart/form-file"), fire_station_details);
        final RequestBody rb_income_tax_office_details = RequestBody.create(MediaType.parse("multipart/form-file"), income_tax_office_details);
        final RequestBody rb_local_municipal_corp_details = RequestBody.create(MediaType.parse("multipart/form-file"), local_municipal_corp_details);
        final RequestBody rb_insuraance_company_details = RequestBody.create(MediaType.parse("multipart/form-file"), insurance_company_details);

        final RequestBody rb_userId = RequestBody.create(MediaType.parse("multipart/form-file"), userId);
        final RequestBody rb_rollId = RequestBody.create(MediaType.parse("multipart/form-file"), rollId);
        final RequestBody rb_user_type = RequestBody.create(MediaType.parse("multipart/form-file"), "1");
        final RequestBody rb_status = RequestBody.create(MediaType.parse("multipart/form-file"), "1");

        File file = new File(expo_path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-file"), file);
        MultipartBody.Part expo_body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        File file1 = new File(floor_path);
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-file"), file1);
        MultipartBody.Part floor_body = MultipartBody.Part.createFormData("floor_plan", file1.getName(), requestFile1);

        Gson gson = new GsonBuilder().create();
        JsonArray jsonArray_StallNoFrom = gson.toJsonTree(stringStallNoFromArrayList).getAsJsonArray();
        JsonArray jsonArray_StallNoTo = gson.toJsonTree(stringStallNoToArrayList).getAsJsonArray();
        JsonArray jsonArray_stallRent = gson.toJsonTree(stringStallRentArrayList).getAsJsonArray();
        JsonArray jsonArray_gst = gson.toJsonTree(stringGstArrayList).getAsJsonArray();
        JsonArray jsonArray_gstAmount = gson.toJsonTree(stringGstAmountArrayList).getAsJsonArray();
        JsonArray jsonArray_total = gson.toJsonTree(stringTotalArrayList).getAsJsonArray();

        String str_jsonArray_StallNoFrom = jsonArray_StallNoFrom.toString();
        String str_jsonArray_StallNoTo = jsonArray_StallNoTo.toString();
        String str_jsonArray_stallRent = jsonArray_stallRent.toString();
        String str_jsonArray_gst = jsonArray_gst.toString();
        String str_jsonArray_gstAmount = jsonArray_gstAmount.toString();
        String str_jsonArray_total = jsonArray_total.toString();

        final RequestBody rb_StallNoFrom = RequestBody.create(MediaType.parse("multipart/form-file"), str_jsonArray_StallNoFrom);
        final RequestBody rb_StallNoTo = RequestBody.create(MediaType.parse("multipart/form-file"), str_jsonArray_StallNoTo);
        final RequestBody rb_stallRent = RequestBody.create(MediaType.parse("multipart/form-file"), str_jsonArray_stallRent);
        final RequestBody rb_gst = RequestBody.create(MediaType.parse("multipart/form-file"), str_jsonArray_gst);
        final RequestBody rb_gstAmount = RequestBody.create(MediaType.parse("multipart/form-file"), str_jsonArray_gstAmount);
        final RequestBody rb_total = RequestBody.create(MediaType.parse("multipart/form-file"), str_jsonArray_total);

//        Log.d("rb_chapterId>>>>>", String.valueOf(rb_chapterId));
//        Log.d("jsonArray_StallNoFrom>>>>>", String.valueOf(jsonArray_StallNoFrom));
//        Log.d("rb_jsonArray_StallNoFrom>>>>>", String.valueOf(rb_jsonArray_StallNoFrom));
//        Log.d("str_jsonArray_StallNoFrom>>>>>",str_jsonArray_StallNoFrom);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<AddExpoResponse> call = request.AddExpo(token, rb_chapterId, rb_expoName,
                rb_startDate, rb_endDate, rb_avilStall, rb_stallSize, rb_desc, rb_slug,
                rb_venue_address, rb_venue_area, rb_venue_state, rb_venue_city, rb_venue_pincode,
                rb_chapter_contactPerson, rb_chapter_mobileNo, rb_chapter_address, rb_chapter_area, rb_chapter_state, rb_chapter_city, rb_chapter_pincode,
                rb_smoi_letter_no, rb_police_station_details, rb_fire_station_details, rb_income_tax_office_details, rb_local_municipal_corp_details, rb_insuraance_company_details,
                rb_StallNoFrom, rb_StallNoTo, rb_stallRent, rb_gst, rb_gstAmount, rb_total,
                rb_userId, rb_rollId, rb_user_type, rb_status, expo_body, floor_body);
        call.enqueue(new Callback<AddExpoResponse>() {
            @Override
            public void onResponse(Call<AddExpoResponse> call, Response<AddExpoResponse> response) {

                Log.d("AddExpoTAG_Success::>>", response.body().getMessage());

                if (response.body().getStatus().matches("400")) {
                    Toast.makeText(StallRentBookingActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {
                        Log.d("AddExpo>>", response.body().getStatus());
                        String s = response.body().getStatus();
                        String msg = response.body().getMessage();

                        Toast.makeText(StallRentBookingActivity.this, msg, Toast.LENGTH_LONG).show();

                        startActivity(new Intent(StallRentBookingActivity.this, ExpoListForStallBookingActivity.class));
                        finish();

                    } else {
//                        Snackbar snackbar = Snackbar.make(parent_view, "Faqs Failed", Snackbar.LENGTH_LONG);
//                        snackbar.show();
                    }

                } else {
//                    Snackbar snackbar = Snackbar.make(parent_view, "Poor Connection", Snackbar.LENGTH_LONG);
//                    snackbar.show();
                }

                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<AddExpoResponse> call, Throwable t) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Log.d("AddExpoTAG_Success::>>", call.toString() + " " + t.toString());
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
//                Log.e("tag::>>>>", t.toString());
//                Snackbar snackbar = Snackbar.make(parent_view, "Faqs Failure", Snackbar.LENGTH_LONG);
//                snackbar.show();
            }
        });


    }

    private void textWatcher() {
        et_gst_per.addTextChangedListener(new TextWatcher() {
            int c;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                c = count;
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable s) {
                if (c > 0) {
                    int gstPer = Integer.parseInt(et_gst_per.getText().toString());
                    int rent = Integer.parseInt(et_stall_rent.getText().toString());
                    String res = String.valueOf((rent * gstPer) / 100);
                    et_gst_amount.setText(res);
                }
            }
        });

        et_gst_per.addTextChangedListener(new TextWatcher() {
            int c;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                c = count;
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable s) {
                if (c > 0) {
                    int gstAmount = Integer.parseInt(et_gst_amount.getText().toString());
                    int rent = Integer.parseInt(et_stall_rent.getText().toString());
                    String res = String.valueOf((rent + gstAmount));
                    et_total.setText(res);
                }
            }
        });
    }


    private void resetAll() {
        et_stall_no_from.setText("");
        et_stall_no_to.setText("");
        et_stall_rent.setText("");
        et_gst_per.setText("");
        et_gst_amount.setText("");
        et_total.setText("");
        et_gst_per.clearFocus();
    }

    @Override
    public void onClick(View v) {
        if (v == btn_addmore_stall) {
            // Handle clicks for btn_addmore_stall

            if (recyclerView.getVisibility() == View.VISIBLE) {
                getDialogBox();
            } else {
                getValidation();
            }

        } else if (v == btn_submit) {
            // Handle clicks for btn_submit
            AddExpo();
        } else if (v == image_view_stall_list) {
            // Handle clicks for image view stall list
            Intent intent = new Intent(StallRentBookingActivity.this, StallListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("key", bookStallRentArrayList);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private void getDialogBox() {
        final AlertDialog.Builder alert = new MaterialAlertDialogBuilder(StallRentBookingActivity.this, R.style.MyRounded_MaterialComponents_MaterialAlertDialog);
        View customLayout = getLayoutInflater().inflate(R.layout.dialog_expo_stall_rent_book, null);
//            alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            customLayout.setBackgroundResource(android.R.color.transparent);

        EditText et_stall_no_from = customLayout.findViewById(R.id.et_stall_no_from);
        EditText et_stall_no_to = customLayout.findViewById(R.id.et_stall_no_to);
        EditText et_stall_rent = customLayout.findViewById(R.id.et_stall_rent);
        EditText et_gst_per = customLayout.findViewById(R.id.et_gst_per);
        MaterialButton btn_cancel = customLayout.findViewById(R.id.btn_cancel);
        MaterialButton btn_submit = customLayout.findViewById(R.id.btn_submit);
        alert.setView(customLayout);

        final AlertDialog alertDialog = alert.create();


        alertDialog.setCanceledOnTouchOutside(false);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

                String stall_no_from = et_stall_no_from.getText().toString();
                String stall_no_to = et_stall_no_to.getText().toString();
                String gst_per = et_gst_per.getText().toString();
                String stall_rent = et_stall_rent.getText().toString();

                int gstPer = Integer.parseInt(et_gst_per.getText().toString());
                int rent = Integer.parseInt(et_stall_rent.getText().toString());
                String gst_amount = String.valueOf((rent * gstPer) / 100);

                int gstAmount = Integer.parseInt(gst_amount);
                String total = String.valueOf((rent + gstAmount));

                bookStallRentArrayList.add(new BookStallRentModel(stall_no_from, stall_no_to, gst_per, stall_rent, gst_amount, total));
                bookStallRentAdapter.notifyDataSetChanged();
                bookStallRentAdapter.updateList(bookStallRentArrayList);

                stringStallNoFromArrayList.add(stall_no_from);
                stringStallNoToArrayList.add(stall_no_to);
                stringStallRentArrayList.add(stall_rent);
                stringGstArrayList.add(gst_per);
                stringGstAmountArrayList.add(gst_amount);
                stringTotalArrayList.add(total);

                Log.d("Added:::>>", String.valueOf(stringTotalArrayList));

                Log.d("Data>>>>", stall_no_from + " " +
                        stall_no_to + " " +
                        gst_per + " " +
                        stall_rent + " " + gst_amount + " " + total);


            }
        });
        alertDialog.show();
    }

    private void getValidation() {

        if (et_stall_no_from.getText().toString().isEmpty()) {
            Toast.makeText(StallRentBookingActivity.this, "Type Stall No From", Toast.LENGTH_SHORT).show();
            et_stall_no_from.requestFocus();
        } else if (et_stall_no_to.getText().toString().isEmpty()) {
            Toast.makeText(StallRentBookingActivity.this, "Type Stall No To", Toast.LENGTH_SHORT).show();
            et_stall_no_to.requestFocus();
        } else if (et_stall_rent.getText().toString().isEmpty()) {
            Toast.makeText(StallRentBookingActivity.this, "Type Stall Rent", Toast.LENGTH_SHORT).show();
            et_stall_rent.requestFocus();
        } else if (et_gst_per.getText().toString().isEmpty()) {
            Toast.makeText(StallRentBookingActivity.this, "Type GST Percentage", Toast.LENGTH_SHORT).show();
            et_gst_per.requestFocus();
        } else {
            String stall_no_from = et_stall_no_from.getText().toString();
            String stall_no_to = et_stall_no_to.getText().toString();
            String gst_per = et_gst_per.getText().toString();
            String stall_rent = et_stall_rent.getText().toString();
            String gst_amount = et_gst_amount.getText().toString();
            String total = et_total.getText().toString();

            bookStallRentArrayList.add(new BookStallRentModel(stall_no_from, stall_no_to, gst_per, stall_rent, gst_amount, total));
            bookStallRentAdapter.notifyDataSetChanged();

            stringStallNoFromArrayList.add(stall_no_from);
            stringStallNoToArrayList.add(stall_no_to);
            stringStallRentArrayList.add(stall_rent);
            stringGstArrayList.add(gst_per);
            stringGstAmountArrayList.add(gst_amount);
            stringTotalArrayList.add(total);

            Log.d("Added:::>>", String.valueOf(stringTotalArrayList));

            //Check Stall ArrayList For Null val
            if (bookStallRentArrayList.size() > 0) {
//                tv_view_stall_details.setVisibility(View.VISIBLE);
//                image_view_stall_list.setVisibility(View.VISIBLE);

                //Reset Edittext String
                resetAll();

                btn_submit.setVisibility(View.VISIBLE);
                mcv_stall_details.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                //View all stall List
               /* tv_view_stall_details.setOnClickListener(view -> {
                    Intent intent = new Intent(StallRentBookingActivity.this, StallListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("key", bookStallRentArrayList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                });*/


            }

        }

    }

    private void findViews() {
        img_back_stall_rent = (ImageView) findViewById(R.id.img_back_stall_rent);
        image_view_stall_list = (ImageView) findViewById(R.id.image_view_stall_list);
        et_stall_no_from = (TextInputEditText) findViewById(R.id.et_stall_no_from);
        et_stall_no_to = (TextInputEditText) findViewById(R.id.et_stall_no_to);
        et_stall_rent = (TextInputEditText) findViewById(R.id.et_stall_rent);
        et_gst_per = (TextInputEditText) findViewById(R.id.et_gst_per);
        et_gst_amount = (TextInputEditText) findViewById(R.id.et_gst_amount);
        et_total = (TextInputEditText) findViewById(R.id.et_total);
        btn_addmore_stall = (MaterialButton) findViewById(R.id.btn_addmore_stall);
        tv_view_stall_details = (TextView) findViewById(R.id.tv_view_stall_details);
        tv_reset = (TextView) findViewById(R.id.tv_reset);
        btn_submit = (MaterialButton) findViewById(R.id.btn_submit);

        et_gst_amount_ll = (TextInputLayout) findViewById(R.id.et_gst_amount_ll);

        mcv_stall_details = (MaterialCardView) findViewById(R.id.mcv_stall_details);


        btn_addmore_stall.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        image_view_stall_list.setOnClickListener(this);
    }


}