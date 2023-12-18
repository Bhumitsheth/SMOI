package com.iipl.smoi.Screens.ActivityActions;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.ChapterResponse;
import com.iipl.smoi.Model.StateResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Utils.RealPathUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpoBookingActivity extends AppCompatActivity {


    //5______
    private TextInputEditText et_police_station_details;
    private TextInputEditText et_fire_station_details;
    private TextInputEditText et_income_tax_office_details;
    private TextInputEditText et_local_municipal_corp_details;
    private TextInputEditText et_insuraance_company_details;

    //4________
    private TextInputEditText et_smoi_letter_no;
    private TextInputEditText et_floor_plan;
    private ImageViewZoom img_floor_plan;

    //3_______
    private TextInputEditText et_chapter_contact_person;
    private TextInputEditText et_chapter_contact_no;
    private TextInputEditText et_chapter_address;
    private TextInputEditText et_chapter_area;
    private TextInputEditText et_chapter_state;
    private LinearLayout ll_chapter_state;
    private TextView tv_chapter_state;
    private TextInputEditText et_chapter_city;
    private TextInputEditText et_chapter_pincode;

    //2_______
    private TextInputEditText et_venue_address;
    private TextInputEditText et_venue_area;
    private TextInputEditText et_venue_state;
    private LinearLayout ll_venue_state;
    private TextView tv_venue_state;
    private TextInputEditText et_venue_city;
    private TextInputEditText et_venue_pincode;

    //1__________
    private TextView tv_toolbar_title;
    private LinearLayout ll_chapters;
    private TextView tv_chapters;
    private TextInputEditText et_expo_name;
    private TextInputEditText et_expo_start_date;
    private TextInputEditText et_expo_end_date;
    private TextInputEditText et_available_stalls;
    private TextInputEditText et_stall_size;
    private TextInputEditText et_description;
    private TextInputEditText et_expo_image;
    private ImageViewZoom img_expo_poster;

    static String TAG_CHAPTER;

    static String TAG_STATE;

    private ArrayList<ChapterResponse.Chapter> chapterArrayList = new ArrayList<>();
    private ArrayList<String> stringChapterArrayList = new ArrayList<>();

    private ArrayList<StateResponse.State> stateArrayList = new ArrayList<>();
    private ArrayList<String> stringStateArrayList = new ArrayList<>();

    Button btn_next;

    ImageView img_toolbar_back;

    String str_number_date;

    final int EXPO_IMAGE = 100;
    final int FLOOR_PLAN_IMAGE = 101;

    static String expo_path = "";
    static String floor_path = "";

    String chapterId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expo_booking);


        findViews();

        //Set Chapter Address
        setChapterAddress();


        et_expo_start_date.setOnClickListener(view1 -> {
            String start = "start";
            getCalender(start);
        });

        et_expo_end_date.setOnClickListener(view1 -> {
            String end = "end";
            getCalender(end);
        });

        et_expo_image.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, EXPO_IMAGE);
        });

        et_floor_plan.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, FLOOR_PLAN_IMAGE);
        });


        ll_chapters.setOnClickListener(view1 -> {
            setChapter();
        });


        img_toolbar_back.setOnClickListener(view1 -> {
            finish();
        });


        btn_next.setOnClickListener(view1 -> {
//            startActivity(new Intent(ExpoBookingActivity.this, StallRentBookingActivity.class));

            //Expo Form Validation
            getValidation();

            /*String slug = et_expo_name.getText().toString().replace(" ", "-");
            Intent intent = new Intent(ExpoBookingActivity.this, StallRentBookingActivity.class);
            intent.putExtra("chapterId", chapterId);
            intent.putExtra("expoName", et_expo_name.getText().toString());
            intent.putExtra("startDate", et_expo_start_date.getText().toString());
            intent.putExtra("endDate", et_expo_end_date.getText().toString());
            intent.putExtra("avilStall", et_available_stalls.getText().toString());
            intent.putExtra("stallSize", et_stall_size.getText().toString());
            intent.putExtra("desc", et_description.getText().toString());
            intent.putExtra("slug", slug);

            intent.putExtra("venue_address", et_venue_address.getText().toString());
            intent.putExtra("venue_area", et_venue_area.getText().toString());
            intent.putExtra("venue_state", tv_venue_state.getText().toString());
            intent.putExtra("venue_city", et_venue_city.getText().toString());
            intent.putExtra("venue_pincode", et_venue_pincode.getText().toString());

            intent.putExtra("chapter_contact_person", et_chapter_contact_person.getText().toString());
            intent.putExtra("chapter_contact_no", et_chapter_contact_no.getText().toString());

            intent.putExtra("smoi_letter_no", et_smoi_letter_no.getText().toString());
            intent.putExtra("police_station_details", et_police_station_details.getText().toString());
            intent.putExtra("fire_station_details", et_fire_station_details.getText().toString());
            intent.putExtra("income_tax_office_details", et_income_tax_office_details.getText().toString());
            intent.putExtra("local_municipal_corp_details", et_local_municipal_corp_details.getText().toString());
            intent.putExtra("insurance_company_details", et_insuraance_company_details.getText().toString());

            intent.putExtra("expo_path", expo_path);
            intent.putExtra("floor_path", floor_path);
            startActivity(intent);*/

        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {


            //Get State for Venue Address
            getState();
            //Set State for Venue Address
            setState();
            //Get Chapter List
            getChapter();


        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }

    private void getValidation() {
        //Expo Booking Validation
        if (tv_chapters.getText().toString().equalsIgnoreCase("Select Chapter")) {
            Toast.makeText(ExpoBookingActivity.this, "Select Chapter", Toast.LENGTH_SHORT).show();
        } else if (et_expo_name.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type Expo Name", Toast.LENGTH_SHORT).show();
            et_expo_name.requestFocus();
        } else if (et_expo_start_date.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Select Expo Start Date", Toast.LENGTH_SHORT).show();
        } else if (et_expo_end_date.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Select Expo End Date", Toast.LENGTH_SHORT).show();
        } else if (et_available_stalls.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type available stalls", Toast.LENGTH_SHORT).show();
            et_available_stalls.requestFocus();
        } else if (et_stall_size.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type stall size", Toast.LENGTH_SHORT).show();
            et_stall_size.requestFocus();
        } else if (et_description.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type Description", Toast.LENGTH_SHORT).show();
            et_description.requestFocus();
        } else if (expo_path.isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Select Expo Poster", Toast.LENGTH_SHORT).show();
        }

        //Expo Venue Validation
        else if (et_venue_address.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type Venue Address", Toast.LENGTH_SHORT).show();
            et_venue_address.requestFocus();
        } else if (et_venue_area.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type Venue Area", Toast.LENGTH_SHORT).show();
            et_venue_area.requestFocus();
        } else if (tv_venue_state.getText().toString().equalsIgnoreCase("Select State")) {
            Toast.makeText(ExpoBookingActivity.this, "Select Venue State", Toast.LENGTH_SHORT).show();
        } else if (et_venue_city.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type Venue City", Toast.LENGTH_SHORT).show();
            et_venue_city.requestFocus();
        } else if (et_venue_pincode.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type Venue Pin Code", Toast.LENGTH_SHORT).show();
            et_venue_pincode.requestFocus();
        }

        //Expo Chapter Validation
        else if (et_chapter_contact_person.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type Contact Person", Toast.LENGTH_SHORT).show();
            et_chapter_contact_person.requestFocus();
        } else if (et_chapter_contact_no.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type Contact Number", Toast.LENGTH_SHORT).show();
            et_chapter_contact_no.requestFocus();
        }

        //Expo Other Validation
        else if (et_smoi_letter_no.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type SMOI Letter No", Toast.LENGTH_SHORT).show();
            et_smoi_letter_no.requestFocus();
        } else if (floor_path.isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Select Floor Plan Image", Toast.LENGTH_SHORT).show();
        }

        //Expo Details of Intimation Letter Validation
        else if (et_police_station_details.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type Police Station Details", Toast.LENGTH_SHORT).show();
            et_police_station_details.requestFocus();
        } else if (et_fire_station_details.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type Fire Station Details", Toast.LENGTH_SHORT).show();
            et_fire_station_details.requestFocus();
        } else if (et_income_tax_office_details.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type Income Tax Office Details", Toast.LENGTH_SHORT).show();
            et_income_tax_office_details.requestFocus();
        } else if (et_local_municipal_corp_details.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type Local Municipal Corporation Details", Toast.LENGTH_SHORT).show();
            et_local_municipal_corp_details.requestFocus();
        } else if (et_insuraance_company_details.getText().toString().isEmpty()) {
            Toast.makeText(ExpoBookingActivity.this, "Type Insurance Company Details", Toast.LENGTH_SHORT).show();
            et_insuraance_company_details.requestFocus();
        } else {
            String slug = et_expo_name.getText().toString().replace(" ", "-");
            Intent intent = new Intent(ExpoBookingActivity.this, StallRentBookingActivity.class);
            intent.putExtra("chapterId", chapterId);
            intent.putExtra("expoName", et_expo_name.getText().toString());
            intent.putExtra("startDate", et_expo_start_date.getText().toString());
            intent.putExtra("endDate", et_expo_end_date.getText().toString());
            intent.putExtra("avilStall", et_available_stalls.getText().toString());
            intent.putExtra("stallSize", et_stall_size.getText().toString());
            intent.putExtra("desc", et_description.getText().toString());
            intent.putExtra("slug", slug);

            intent.putExtra("venue_address", et_venue_address.getText().toString());
            intent.putExtra("venue_area", et_venue_area.getText().toString());
            intent.putExtra("venue_state", tv_venue_state.getText().toString());
            intent.putExtra("venue_city", et_venue_city.getText().toString());
            intent.putExtra("venue_pincode", et_venue_pincode.getText().toString());

            intent.putExtra("chapter_contact_person", et_chapter_contact_person.getText().toString());
            intent.putExtra("chapter_contact_no", et_chapter_contact_no.getText().toString());

            intent.putExtra("smoi_letter_no", et_smoi_letter_no.getText().toString());
            intent.putExtra("police_station_details", et_police_station_details.getText().toString());
            intent.putExtra("fire_station_details", et_fire_station_details.getText().toString());
            intent.putExtra("income_tax_office_details", et_income_tax_office_details.getText().toString());
            intent.putExtra("local_municipal_corp_details", et_local_municipal_corp_details.getText().toString());
            intent.putExtra("insurance_company_details", et_insuraance_company_details.getText().toString());

            intent.putExtra("expo_path", expo_path);
            intent.putExtra("floor_path", floor_path);
            startActivity(intent);
        }


    }

    private void setChapterAddress() {
        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String chapter_contactPerson = sharedpreferences.getString(AppConstant.TAG_CONTACT_PERSON, "");
        String chapter_mobileNo = sharedpreferences.getString(AppConstant.TAG_mobileNo, "");
        String chapter_address = sharedpreferences.getString(AppConstant.TAG_address, "");
        String chapter_area = sharedpreferences.getString(AppConstant.TAG_AREA, "");
        String chapter_state = sharedpreferences.getString(AppConstant.TAG_state, "");
        String chapter_city = sharedpreferences.getString(AppConstant.TAG_city, "");
        String chapter_pincode = sharedpreferences.getString(AppConstant.TAG_pincode, "");

        et_chapter_contact_person.setText(chapter_contactPerson);
        String s = chapter_mobileNo.replace("-", "");
        et_chapter_contact_no.setText(s);
        et_chapter_address.setText(chapter_address);
        et_chapter_area.setText(chapter_area);
        et_chapter_state.setText(chapter_state);
        et_chapter_city.setText(chapter_city);
        et_chapter_pincode.setText(chapter_pincode);

    }

    private void setState() {
        ll_venue_state.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(ExpoBookingActivity.this);
            dialog.setContentView(R.layout.dialog_searchable_spinner);
//            dialog.getWindow().setLayout(800, 1000);
//            dialog.getWindow().setStatusBarColor(R.color.appcolor);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                dialog.getWindow().setStatusBarColor(getResources().getColor(R.color.appcolor));
                dialog.getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.show();
            TextView selectState = dialog.findViewById(R.id.tv_select);
            EditText editText = dialog.findViewById(R.id.edit_text);
            ListView listView = dialog.findViewById(R.id.list_view);

            selectState.setText(R.string.select_state);

            ArrayAdapter<String> adapter = new ArrayAdapter(ExpoBookingActivity.this, android.R.layout.simple_list_item_1, stringStateArrayList);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    TAG_STATE = adapter.getItem(position);

                    tv_venue_state.setText(TAG_STATE);

                    Log.d("State_POS>>>", String.valueOf(position));

                    for (StateResponse.State state1 : stateArrayList) {
                        String s = String.valueOf(state1.getId());
                        String sname = String.valueOf(state1.getName());
                        Log.d("FindStateId>>>", s + " " + sname);

                        if (state1.getName().equalsIgnoreCase(tv_venue_state.getText().toString())) {
                            String sid = state1.getId();
//                            TAG_STATE=sid;
                            Log.d("State_pos_id>>>", sid);
                        }

                    }
                    dialog.dismiss();

                }
            });

        });
    }

    private void getState() {
        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<StateResponse> call = request.getState();
        call.enqueue(new Callback<StateResponse>() {
            @Override
            public void onResponse(Call<StateResponse> call, Response<StateResponse> response) {

                stateArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("state>>", response.body().getStatus());

                        List<StateResponse.State> stateList = response.body().getStates();
                        if (stateList != null && !stateList.isEmpty() && stateList.size() > 0) {
                            for (int i = 0; i < stateList.size(); i++) {

                                String name = stateList.get(i).getName();
                                String id = stateList.get(i).getId();

                                StateResponse.State state = new StateResponse.State();
                                state.setName(name);
                                state.setId(id);
                                stateArrayList.add(state);

//                                Log.d("state>>", name);

                                stringStateArrayList.add(name);

                            }

                        } else {
                            Toast.makeText(ExpoBookingActivity.this, "State Not Available", Toast.LENGTH_SHORT).show();
                        }

                    }

                }


            }

            @Override
            public void onFailure(Call<StateResponse> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EXPO_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            Context context = ExpoBookingActivity.this;
            expo_path = RealPathUtil.getRealPath(context, uri);
            Bitmap bitmap = BitmapFactory.decodeFile(expo_path);

            Log.d("path>>>", expo_path);

            img_expo_poster.setVisibility(View.VISIBLE);
            img_expo_poster.setImageBitmap(bitmap);

        }

        if (requestCode == FLOOR_PLAN_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            Context context = ExpoBookingActivity.this;
            floor_path = RealPathUtil.getRealPath(context, uri);
            Bitmap bitmap = BitmapFactory.decodeFile(floor_path);
//            Log.d("path>>>", path);
            img_floor_plan.setVisibility(View.VISIBLE);
            img_floor_plan.setImageBitmap(bitmap);
        }


    }

    private void getCalender(String startOrEnd) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(ExpoBookingActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        et_expo_start_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        int Newmonth = monthOfYear + 1;
                        if (dayOfMonth >= 10 && Newmonth >= 10) {
                            str_number_date = dayOfMonth + "-" + Newmonth + "-" + year;
                        } else if (dayOfMonth < 10) {
                            String newday = "0" + dayOfMonth;
                            if (Newmonth < 10) {
                                str_number_date = newday + "-" + ("0" + Newmonth) + "-" + year;
                            } else {
                                str_number_date = newday + "-" + Newmonth + "-" + year;
                            }
                        } else if (dayOfMonth >= 10 && Newmonth < 10) {
                            str_number_date = dayOfMonth + "-" + ("0" + Newmonth) + "-" + year;
                        }


                        if (startOrEnd.matches("start")) {
                            et_expo_start_date.setText(str_number_date);
                        } else {
                            et_expo_end_date.setText(str_number_date);
                        }

                    }
                },
                year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void setChapter() {
        Dialog dialog = new Dialog(ExpoBookingActivity.this);
        dialog.setContentView(R.layout.dialog_searchable_spinner);
        dialog.getWindow().setLayout(1000, 2000);
//            dialog.getWindow().setStatusBarColor(R.color.appcolor);

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            dialog.getWindow().setStatusBarColor(getResources().getColor(R.color.appcolor));
            dialog.getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }*/

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.show();
        TextView selectState = dialog.findViewById(R.id.tv_select);
        EditText editText = dialog.findViewById(R.id.edit_text);
        ListView listView = dialog.findViewById(R.id.list_view);

        selectState.setText(R.string.select_chapter);

        tv_chapters.setText(R.string.select_chapter);

        ArrayAdapter<String> adapter = new ArrayAdapter(ExpoBookingActivity.this, android.R.layout.simple_list_item_1, stringChapterArrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TAG_CHAPTER = adapter.getItem(position);
                tv_chapters.setText(TAG_CHAPTER);

//                    image_filter.setVisibility(View.VISIBLE);
//                Log.d("TAG_CHAPTER>>>", TAG_CHAPTER);
//                Log.d("State_POS>>>", String.valueOf(position));

                for (ChapterResponse.Chapter chapter : chapterArrayList) {
                    String s = String.valueOf(chapter.getId());
                    String sname = String.valueOf(chapter.getUsername());
//                    Log.d("Find_Chapter>>>", s + " " + sname);
                    if (sname.equalsIgnoreCase(tv_chapters.getText().toString())) {
                        chapterId = chapter.getId();
                    }

                }
                dialog.dismiss();
            }
        });
    }

    private void getChapter() {

        final ProgressDialog pDialog = new ProgressDialog(ExpoBookingActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = ExpoBookingActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<ChapterResponse> call = request.getChapter();
        call.enqueue(new Callback<ChapterResponse>() {
            @Override
            public void onResponse(Call<ChapterResponse> call, Response<ChapterResponse> response) {

                chapterArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        List<ChapterResponse.Chapter> chapterList = response.body().getChapter();
                        if (chapterList != null && !chapterList.isEmpty() && chapterList.size() > 0) {
                            for (int i = 0; i < chapterList.size(); i++) {

                                String id = chapterList.get(i).getId();
                                String username = chapterList.get(i).getUsername();
                                String firstname = chapterList.get(i).getFirstName();
                                String lastName = chapterList.get(i).getLastName();
                                String email = chapterList.get(i).getEmail();
                                String profileImage = chapterList.get(i).getProfileImage();

                                ChapterResponse.Chapter chapterList1 = new ChapterResponse.Chapter();
                                chapterList1.setId(id);
                                chapterList1.setUsername(username);
                                chapterList1.setFirstName(firstname);
                                chapterList1.setLastName(lastName);
                                chapterList1.setEmail(email);
                                chapterList1.setProfileImage(profileImage);
                                chapterArrayList.add(chapterList1);

                                stringChapterArrayList.add(username);

//                                Log.d("username_chapter::>>",username);

                            }

                          /*  chapterAdapter = new ChapterAdapter(ExpoBookingActivity.this, chapterArrayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                }
                            });*/

//                            rv_chapterList.setAdapter(chapterAdapter);


                        } else {

                            Toast.makeText(ExpoBookingActivity.this, "chapterList Not Available", Toast.LENGTH_SHORT).show();

                        }

                    }

                }

                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }
            @Override
            public void onFailure(Call<ChapterResponse> call, Throwable t) {
                Log.e("tag::>>>>", "chapterList", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void findViews() {
        btn_next = findViewById(R.id.btn_next);
        img_toolbar_back = findViewById(R.id.img_toolbar_back);
        tv_toolbar_title = (TextView) findViewById(R.id.tv_toolbar_title);
        tv_toolbar_title.setText(R.string.expo_booking);

        //5______

        et_police_station_details = (TextInputEditText) findViewById(R.id.et_police_station_details);
        et_fire_station_details = (TextInputEditText) findViewById(R.id.et_fire_station_details);
        et_income_tax_office_details = (TextInputEditText) findViewById(R.id.et_income_tax_office_details);
        et_local_municipal_corp_details = (TextInputEditText) findViewById(R.id.et_local_municipal_corp_details);
        et_insuraance_company_details = (TextInputEditText) findViewById(R.id.et_insuraance_company_details);

        //4_____

        et_smoi_letter_no = (TextInputEditText) findViewById(R.id.et_smoi_letter_no);
        et_floor_plan = (TextInputEditText) findViewById(R.id.et_floor_plan);
        img_floor_plan = (ImageViewZoom) findViewById(R.id.img_floor_plan);

        //3______

        et_chapter_contact_person = (TextInputEditText) findViewById(R.id.et_chapter_contact_person);
        et_chapter_contact_no = (TextInputEditText) findViewById(R.id.et_chapter_contact_no);
        et_chapter_address = (TextInputEditText) findViewById(R.id.et_chapter_address);
        et_chapter_area = (TextInputEditText) findViewById(R.id.et_chapter_area);
        et_chapter_state = (TextInputEditText) findViewById(R.id.et_chapter_state);
//        ll_chapter_state = (LinearLayout) findViewById(R.id.ll_chapter_state);
//        tv_chapter_state = (TextView) findViewById(R.id.tv_chapter_state);
        et_chapter_city = (TextInputEditText) findViewById(R.id.et_chapter_city);
        et_chapter_pincode = (TextInputEditText) findViewById(R.id.et_chapter_pincode);


        //2____

        et_venue_address = (TextInputEditText) findViewById(R.id.et_venue_address);
        et_venue_area = (TextInputEditText) findViewById(R.id.et_venue_area);
        et_venue_state = (TextInputEditText) findViewById(R.id.et_venue_state);
        ll_venue_state = (LinearLayout) findViewById(R.id.ll_venue_state);
        tv_venue_state = (TextView) findViewById(R.id.tv_venue_state);
        et_venue_city = (TextInputEditText) findViewById(R.id.et_venue_city);
        et_venue_pincode = (TextInputEditText) findViewById(R.id.et_venue_pincode);

        //1___

        ll_chapters = (LinearLayout) findViewById(R.id.ll_chapters);
        tv_chapters = (TextView) findViewById(R.id.tv_chapters);
        et_expo_name = (TextInputEditText) findViewById(R.id.et_expo_name);
        et_expo_start_date = (TextInputEditText) findViewById(R.id.et_expo_start_date);
        et_expo_end_date = (TextInputEditText) findViewById(R.id.et_expo_end_date);
        et_available_stalls = (TextInputEditText) findViewById(R.id.et_available_stalls);
        et_stall_size = (TextInputEditText) findViewById(R.id.et_stall_size);
        et_description = (TextInputEditText) findViewById(R.id.et_description);
        et_expo_image = (TextInputEditText) findViewById(R.id.et_expo_image);
        img_expo_poster = (ImageViewZoom) findViewById(R.id.img_expo_poster);

    }


}