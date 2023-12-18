package com.iipl.smoi.Screens.ActivityActions;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.iipl.smoi.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExpoDetailsActivity extends AppCompatActivity {

    ImageView img_back_expo_list, image_stalls_bookings, image_banner;

    TextView tv_title_val, tv_desc_val, tv_date_val, tv_contact_person_val, tv_contact_no_val, tv_address_val,
            tv_state_val, tv_city_val, tv_pin_val, tv_stall_no_val,
            policeStationDetails_val, fireStationDetails_val,
            incometaxOfficeDetails_val, local_municipal_corporation_details_val, insurance_company_details_val;

    TableLayout tableLayout, tableLayout2, tableLayout3, tableLayout4,
            tableLayout5,
            tableLayout6,
            tableLayout7;

    String str_image, str_title, str_desc, str_date, str_contact_person, str_contact_no, str_address,
            str_state, str_city, str_pin, str_stall_no,
            availableStalls,
            expoStartdate,
            expoEnddate,
            policeStationDetails,
            fireStationDetails,
            incomeTaxOfficeDetails,
            insuranceCompanyDetails,
            localMunicipalCorporationDetails,
            stallNoFrom,
            stallNoTo,
            stallRent,
            gst,
            gstAmount,
            total;

    ArrayList<String> stringStallNoFromArrayList = new ArrayList<>();
    ArrayList<String> stringStallNoToArrayList = new ArrayList<>();
    ArrayList<String> stringStallRentArrayList = new ArrayList<>();
    ArrayList<String> stringGstArrayList = new ArrayList<>();
    ArrayList<String> stringGstAmountArrayList = new ArrayList<>();
    ArrayList<String> stringTotalArrayList = new ArrayList<>();

//    ArrayList<String> stringSort_1 = new ArrayList<>();
//    ArrayList<String> stringSort_2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expo_details);

        findView();

        img_back_expo_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Get Data From Expo List
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            str_image = bundle.getString("image", "NA");
            str_title = bundle.getString("title", "NA");
            str_desc = bundle.getString("desc", "NA");
            str_date = bundle.getString("bookingDate", "NA");
            str_contact_person = bundle.getString("contactPerson", "NA");
            str_contact_no = bundle.getString("contactNo", "NA");
            str_address = bundle.getString("address", "NA");
            str_state = bundle.getString("state", "NA");
            str_city = bundle.getString("city", "NA");
            str_pin = bundle.getString("pincode", "NA");
            str_stall_no = bundle.getString("stallNo", "NA");

            availableStalls = bundle.getString("availableStalls", "NA");
            expoStartdate = bundle.getString("expoStartdate", "NA");
            expoEnddate = bundle.getString("expoEnddate", "NA");

            policeStationDetails = bundle.getString("policeStationDetails", "NA");
            fireStationDetails = bundle.getString("fireStationDetails", "NA");
            incomeTaxOfficeDetails = bundle.getString("incomeTaxOfficeDetails", "NA");
            insuranceCompanyDetails = bundle.getString("insuranceCompanyDetails", "NA");
            localMunicipalCorporationDetails = bundle.getString("localMunicipalCorporationDetails", "NA");

            stallNoFrom = bundle.getString("stallNoFrom", "NA");
            stallNoTo = bundle.getString("stallNoTo", "NA");
            stallRent = bundle.getString("stallRent", "NA");
            gst = bundle.getString("gst", "NA");
            gstAmount = bundle.getString("gstAmount", "NA");
            total = bundle.getString("total", "NA");


            //Table Row Display
            tableInit();

          /*  Gson gson = new Gson();
            Type type = new TypeToken<List<String>>() {
            }.getType();
            if (!(stallNoFrom ==null)){
                stringStallNoFromArrayList = gson.fromJson(stallNoFrom, type);
                for (String s : stringStallNoFromArrayList) {
                    Log.d("stallNoFrom:123:>>", s + " " + stringStallNoFromArrayList.get(0));
                }
            }*/


        }

        Glide.with(getApplicationContext()).load(str_image).error(R.drawable.bg).into(image_banner);

        tv_title_val.setText(str_title);
        tv_desc_val.setText(str_desc);
        tv_date_val.setText(expoStartdate + " to " + expoEnddate);
        tv_contact_person_val.setText(str_contact_person);
        tv_contact_no_val.setText(str_contact_no);
        tv_address_val.setText(str_address);
        tv_state_val.setText(str_state);
        tv_city_val.setText(str_city);
        tv_pin_val.setText(str_pin);
        tv_stall_no_val.setText(availableStalls);

        policeStationDetails_val.setText(policeStationDetails);
        fireStationDetails_val.setText(fireStationDetails);
        incometaxOfficeDetails_val.setText(incomeTaxOfficeDetails);
        local_municipal_corporation_details_val.setText(localMunicipalCorporationDetails);
        insurance_company_details_val.setText(insuranceCompanyDetails);

        image_stalls_bookings.setOnClickListener(view1 -> {

        });


    }

    private void tableInit() {

        tableLayout.setStretchAllColumns(true);
        tableLayout2.setStretchAllColumns(true);
        tableLayout3.setStretchAllColumns(true);
        tableLayout4.setStretchAllColumns(true);
        tableLayout5.setStretchAllColumns(true);
        tableLayout6.setStretchAllColumns(true);
        tableLayout7.setStretchAllColumns(true);

        TableRow tbrow0 = new TableRow(this);
        TableRow tbrow1 = new TableRow(this);
        TableRow tbrow2 = new TableRow(this);
        TableRow tbrow3 = new TableRow(this);
        TableRow tbrow4 = new TableRow(this);
        TableRow tbrow5 = new TableRow(this);
        TableRow tbrow6 = new TableRow(this);

        tbrow0.setPadding(1, 1, 1, 1);
        tbrow1.setPadding(1, 1, 1, 1);
        tbrow2.setPadding(1, 1, 1, 1);
        tbrow3.setPadding(1, 1, 1, 1);
        tbrow4.setPadding(1, 1, 1, 1);
        tbrow5.setPadding(1, 1, 1, 1);
        tbrow6.setPadding(1, 1, 1, 1);

        tbrow0.setBackgroundColor(getColor(R.color.logobg));
        tbrow1.setBackgroundColor(getColor(R.color.logobg));
        tbrow2.setBackgroundColor(getColor(R.color.logobg));
        tbrow3.setBackgroundColor(getColor(R.color.logobg));
        tbrow4.setBackgroundColor(getColor(R.color.logobg));
        tbrow5.setBackgroundColor(getColor(R.color.logobg));
        tbrow6.setBackgroundColor(getColor(R.color.logobg));


        TextView tv0 = new TextView(this);
        tv0.setText(" Sr.No ");
        tv0.setTextSize(16);
        tv0.setTextColor(getColor(R.color.appcolor));
        tv0.setGravity(Gravity.CENTER);
        tv0.setPadding(1, 1, 1, 1);
        tbrow0.addView(tv0);

        TextView tv1 = new TextView(this);
        tv1.setText(" Stall No. From ");
        tv1.setTextSize(16);
        tv1.setTextColor(getColor(R.color.appcolor));
        tv1.setGravity(Gravity.CENTER);
        tv1.setPadding(1, 1, 1, 1);
        tbrow1.addView(tv1);

        TextView tv2 = new TextView(this);
        tv2.setText(" Stall No. To ");
        tv2.setTextSize(16);
        tv2.setTextColor(getColor(R.color.appcolor));
        tv2.setGravity(Gravity.CENTER);
        tv2.setPadding(1, 1, 1, 1);
        tbrow2.addView(tv2);

        TextView tv3 = new TextView(this);
        tv3.setText(" Stall Rent ");
        tv3.setTextSize(16);
        tv3.setTextColor(getColor(R.color.appcolor));
        tv3.setGravity(Gravity.CENTER);
        tv3.setPadding(1, 1, 1, 1);
        tbrow3.addView(tv3);

        TextView tv4 = new TextView(this);
        tv4.setText(" Gst(%) ");
        tv4.setTextSize(16);
        tv4.setTextColor(getColor(R.color.appcolor));
        tv4.setGravity(Gravity.CENTER);
        tv4.setPadding(1, 1, 1, 1);
        tbrow4.addView(tv4);

        TextView tv5 = new TextView(this);
        tv5.setText(" Gst Amount ");
        tv5.setTextSize(16);
        tv5.setTextColor(getColor(R.color.appcolor));
        tv5.setGravity(Gravity.CENTER);
        tv5.setPadding(1, 1, 1, 1);
        tbrow5.addView(tv5);

        TextView tv6 = new TextView(this);
        tv6.setText(" Total ");
        tv6.setTextSize(16);
        tv6.setTextColor(getColor(R.color.appcolor));
        tv6.setGravity(Gravity.CENTER);
        tv6.setPadding(1, 1, 1, 1);
        tbrow6.addView(tv6);

        tableLayout.addView(tbrow0);
        tableLayout2.addView(tbrow1);
        tableLayout3.addView(tbrow2);

        tableLayout4.addView(tbrow3);
        tableLayout5.addView(tbrow4);
        tableLayout6.addView(tbrow5);
        tableLayout7.addView(tbrow6);


        try {
            Gson gson0 = new Gson();
            Type type0 = new TypeToken<List<String>>() {
            }.getType();
            ArrayList<String> size = gson0.fromJson(stallNoFrom, type0);
            for (int i = 0; i < size.size(); i++) {
                String data = String.valueOf(i + 1);
                TableRow tbrow = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(data);
                t1v.setTextSize(14);
                t1v.setTextColor(Color.BLACK);
                t1v.setGravity(Gravity.CENTER);
                t1v.setBackgroundColor(getColor(R.color.offwhite));
                t1v.setPadding(0, 1, 0, 1);
                tbrow.addView(t1v);
                tableLayout.addView(tbrow);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            tableLayout.setVisibility(View.GONE);
            tableLayout2.setVisibility(View.GONE);
            tableLayout3.setVisibility(View.GONE);
            tableLayout4.setVisibility(View.GONE);
            tableLayout5.setVisibility(View.GONE);
            tableLayout6.setVisibility(View.GONE);
            tableLayout7.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Stall Details Not Available", Toast.LENGTH_LONG).show();
        }


        try {
            Gson gson1 = new Gson();
            Type type1 = new TypeToken<List<String>>() {
            }.getType();
            ArrayList<String> stallNoFromList = gson1.fromJson(stallNoFrom, type1);
            for (int i = 0; i < stallNoFromList.size(); i++) {
                String data = stallNoFromList.get(i);
                TableRow tbrow = new TableRow(this);
                TextView t2v = new TextView(this);
                t2v.setText(data);
                t2v.setTextSize(14);
                t2v.setTextColor(Color.BLACK);
                t2v.setGravity(Gravity.CENTER);
                t2v.setBackgroundColor(getColor(R.color.appbg));
                t2v.setPadding(0, 1, 0, 1);
                tbrow.addView(t2v);
                tableLayout2.addView(tbrow);
            }
        } catch (JsonSyntaxException jsonSyntaxException) {
            jsonSyntaxException.printStackTrace();
        }

        try {
            Gson gson2 = new Gson();
            Type type2 = new TypeToken<List<String>>() {
            }.getType();
            ArrayList<String> stallNoToList = gson2.fromJson(stallNoTo, type2);
            for (int i = 0; i < stallNoToList.size(); i++) {
                String data = stallNoToList.get(i);
                TableRow tbrow = new TableRow(this);
                TextView t3v = new TextView(this);
                t3v.setText(data);
                t3v.setTextSize(14);
                t3v.setTextColor(Color.BLACK);
                t3v.setGravity(Gravity.CENTER);
                t3v.setBackgroundColor(getColor(R.color.offwhite));
                t3v.setPadding(0, 1, 0, 1);
                tbrow.addView(t3v);
                tableLayout3.addView(tbrow);
            }
        } catch (JsonSyntaxException jsonSyntaxException) {
            jsonSyntaxException.printStackTrace();
        }


        try {
            Gson gson3 = new Gson();
            Type type3 = new TypeToken<List<String>>() {
            }.getType();
            ArrayList<String> stallRentList = gson3.fromJson(stallRent, type3);
            for (int i = 0; i < stallRentList.size(); i++) {
                String data = stallRentList.get(i);
                TableRow tbrow = new TableRow(this);
                TextView t4v = new TextView(this);
                t4v.setText(data);
                t4v.setTextSize(14);
                t4v.setTextColor(Color.BLACK);
                t4v.setGravity(Gravity.CENTER);
                t4v.setBackgroundColor(getColor(R.color.appbg));
                t4v.setPadding(0, 1, 0, 1);
                tbrow.addView(t4v);
                tableLayout4.addView(tbrow);
            }
        } catch (JsonSyntaxException jsonSyntaxException) {
            jsonSyntaxException.printStackTrace();
        }


        try {
            Gson gson4 = new Gson();
            Type type4 = new TypeToken<List<String>>() {
            }.getType();
            ArrayList<String> gstper = gson4.fromJson(gst, type4);
            for (int i = 0; i < gstper.size(); i++) {
                String data = gstper.get(i);
                TableRow tbrow = new TableRow(this);
                TextView t5v = new TextView(this);
                t5v.setText(data);
                t5v.setTextSize(14);
                t5v.setTextColor(Color.BLACK);
                t5v.setGravity(Gravity.CENTER);
                t5v.setBackgroundColor(getColor(R.color.offwhite));
                t5v.setPadding(0, 1, 0, 1);
                tbrow.addView(t5v);
                tableLayout5.addView(tbrow);
            }
        } catch (JsonSyntaxException jsonSyntaxException) {
            jsonSyntaxException.printStackTrace();
        }


        try {
            Gson gson5 = new Gson();
            Type type5 = new TypeToken<List<String>>() {
            }.getType();
            ArrayList<String> gstamt = gson5.fromJson(gstAmount, type5);
            for (int i = 0; i < gstamt.size(); i++) {
                String data = gstamt.get(i);
                TableRow tbrow = new TableRow(this);
                TextView t6v = new TextView(this);
                t6v.setText(data);
                t6v.setTextSize(14);
                t6v.setTextColor(Color.BLACK);
                t6v.setGravity(Gravity.CENTER);
                t6v.setBackgroundColor(getColor(R.color.appbg));
                t6v.setPadding(0, 1, 0, 1);
                tbrow.addView(t6v);
                tableLayout6.addView(tbrow);
            }
        } catch (JsonSyntaxException jsonSyntaxException) {
            jsonSyntaxException.printStackTrace();
        }

        try {
            Gson gson7 = new Gson();
            Type type7 = new TypeToken<List<String>>() {
            }.getType();
            ArrayList<String> totalList = gson7.fromJson(total, type7);
            for (int i = 0; i < totalList.size(); i++) {
                String data = totalList.get(i);
                TableRow tbrow = new TableRow(this);
                TextView t7v = new TextView(this);
                t7v.setText(data);
                t7v.setTextSize(14);
                t7v.setTextColor(Color.BLACK);
                t7v.setGravity(Gravity.CENTER);
                t7v.setBackgroundColor(getColor(R.color.offwhite));
                t7v.setPadding(0, 1, 0, 1);
                tbrow.addView(t7v);
                tableLayout7.addView(tbrow);
            }
        } catch (JsonSyntaxException jsonSyntaxException) {
            jsonSyntaxException.printStackTrace();
        }

    }

    private void findView() {
        img_back_expo_list = findViewById(R.id.img_back_expo_list);
        image_stalls_bookings = findViewById(R.id.image_stalls_bookings);

        image_banner = findViewById(R.id.image_banner);

        tv_title_val = findViewById(R.id.tv_title_val);
        tv_desc_val = findViewById(R.id.tv_desc_val);
        tv_date_val = findViewById(R.id.tv_date_val);
        tv_contact_person_val = findViewById(R.id.tv_contact_person_val);
        tv_contact_no_val = findViewById(R.id.tv_contact_no_val);
        tv_address_val = findViewById(R.id.tv_address_val);
        tv_state_val = findViewById(R.id.tv_state_val);
        tv_city_val = findViewById(R.id.tv_city_val);
        tv_pin_val = findViewById(R.id.tv_pin_val);
        tv_stall_no_val = findViewById(R.id.tv_stall_no_val);

        policeStationDetails_val = findViewById(R.id.policeStationDetails_val);
        fireStationDetails_val = findViewById(R.id.fireStationDetails_val);
        incometaxOfficeDetails_val = findViewById(R.id.incometaxOfficeDetails_val);
        local_municipal_corporation_details_val = findViewById(R.id.local_municipal_corporation_details_val);
        insurance_company_details_val = findViewById(R.id.insurance_company_details_val);

        tableLayout = (TableLayout) findViewById(R.id.table_stall_details);
        tableLayout2 = (TableLayout) findViewById(R.id.table_stall_details2);
        tableLayout3 = (TableLayout) findViewById(R.id.table_stall_details3);
        tableLayout4 = (TableLayout) findViewById(R.id.table_stall_details4);
        tableLayout5 = (TableLayout) findViewById(R.id.table_stall_details5);
        tableLayout6 = (TableLayout) findViewById(R.id.table_stall_details6);
        tableLayout7 = (TableLayout) findViewById(R.id.table_stall_details7);

    }
}