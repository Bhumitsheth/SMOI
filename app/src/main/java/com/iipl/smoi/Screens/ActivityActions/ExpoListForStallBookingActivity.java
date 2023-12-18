package com.iipl.smoi.Screens.ActivityActions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.iipl.smoi.Adapter.ExpoListAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.ExpoListResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpoListForStallBookingActivity extends AppCompatActivity {

    private ImageView img_back, img_add_expo;
    private RecyclerView recyclerView;

    ExpoListAdapter expoListAdapter;

    private ArrayList<ExpoListResponse.ExpoBooking> expoBookingArrayList = new ArrayList<>();
    private ArrayList<ExpoListResponse.ExpoBooking.Desc> descArrayList = new ArrayList<>();
    private ArrayList<ExpoListResponse.ExpoBooking.Title> titleArrayList = new ArrayList<>();

    ArrayList<String> stringStallNoFromArrayList = new ArrayList<>();
    ArrayList<String> stringStallNoToArrayList = new ArrayList<>();
    ArrayList<String> stringStallRentArrayList = new ArrayList<>();
    ArrayList<String> stringGstArrayList = new ArrayList<>();
    ArrayList<String> stringGstAmountArrayList = new ArrayList<>();
    ArrayList<String> stringTotalArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expo_list_for_stall_booking);

        //Here Your Views & Widget
        findViews();


        SharedPreferences sharedpreferences1 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String roll_id = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);
        if (!(roll_id == null)) {
            if (roll_id.matches(AppConstant.TAG_CHAPTER)) {
                img_add_expo.setVisibility(View.VISIBLE);
            }
        }


        //Create Expo Image Click
        img_add_expo.setOnClickListener(view -> {
            Intent intent = new Intent(ExpoListForStallBookingActivity.this, ExpoBookingActivity.class);
            startActivity(intent);
        });

        //Back Press of screen
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpoListForStallBookingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //Check Internet Connections
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            //Attache Expo List Api Methode
            getExpoList();

        } else {
            Toast.makeText(ExpoListForStallBookingActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }


    }

    private void getExpoList() {
        final ProgressDialog pDialog = new ProgressDialog(ExpoListForStallBookingActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_user_id = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String str_roll_id = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("login_id", str_user_id);
        requestBody.put("roll_id", str_roll_id);

        Log.d("requestBody::>>", String.valueOf(requestBody));

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<ExpoListResponse> call = request.getExpoList(str_token, requestBody);
        call.enqueue(new Callback<ExpoListResponse>() {
            @Override
            public void onResponse(Call<ExpoListResponse> call, Response<ExpoListResponse> response) {

                expoBookingArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        List<ExpoListResponse.ExpoBooking> flourishMasterList = response.body().getExpoBookings();
                        if (flourishMasterList != null && !flourishMasterList.isEmpty() && flourishMasterList.size() > 0) {
                            for (int i = 0; i < flourishMasterList.size(); i++) {

                                String id = (String) flourishMasterList.get(i).getId();


                                String image = (String) flourishMasterList.get(i).getImage();
//                                String titleEn = (String) flourishMasterList.get(i).getTitleEn();
                                String createdAt = (String) flourishMasterList.get(i).getCreatedAt();

//                                String desc = flourishMasterList.get(i).getDesc();
                                String bookingDate = flourishMasterList.get(i).getBookingDate();
                                String contactPerson = flourishMasterList.get(i).getContactPerson();
                                String contactNo = flourishMasterList.get(i).getContactNo();
                                String address = flourishMasterList.get(i).getAddress();
                                String state = flourishMasterList.get(i).getState();
                                String city = flourishMasterList.get(i).getCity();
                                String pincode = flourishMasterList.get(i).getPincode();
                                String stallNo = flourishMasterList.get(i).getStallNo();

                                String availableStalls = flourishMasterList.get(i).getAvailableStalls();
                                String expoStartdate = flourishMasterList.get(i).getExpoStartdate();
                                String expoEnddate = flourishMasterList.get(i).getExpoEnddate();
                                String policeStationDetails = flourishMasterList.get(i).getPoliceStationDetails();
                                String fireStationDetails = flourishMasterList.get(i).getFireStationDetails();
                                String incomeTaxOfficeDetails = flourishMasterList.get(i).getIncomeTaxOfficeDetails();
                                String insuranceCompanyDetails = flourishMasterList.get(i).getInsuranceCompanyDetails();
                                String localMunicipalCorporationDetails = flourishMasterList.get(i).getLocalMunicipalCorporationDetails();

                                String stallNoFrom = flourishMasterList.get(i).getStallNoFrom();
                                String stallNoTo = flourishMasterList.get(i).getStallNoTo();
                                String stallRent = flourishMasterList.get(i).getStallRent();
                                String gst = flourishMasterList.get(i).getGst();
                                String gstAmount = flourishMasterList.get(i).getGstAmount();
                                String total = flourishMasterList.get(i).getTotal();

                              /*  stringStallNoFromArrayList.add(stallNoFrom);
                                stringStallNoToArrayList.add(stallNoTo);
                                stringStallRentArrayList.add(stallRent);
                                stringGstArrayList.add(gst);
                                stringGstAmountArrayList.add(gstAmount);
                                stringTotalArrayList.add(total);*/

//                                Log.d("stringStallNoFromArrayList::>>>", String.valueOf(stringStallNoFromArrayList));

                                ExpoListResponse.ExpoBooking expoBooking = new ExpoListResponse.ExpoBooking();
                                expoBooking.setId(id);
                                expoBooking.setImage(image);
                                expoBooking.setCreatedAt(createdAt);
                                expoBooking.setBookingDate(bookingDate);
                                expoBooking.setContactPerson(contactPerson);
                                expoBooking.setContactNo(contactNo);
                                expoBooking.setAddress(address);
                                expoBooking.setState(state);
                                expoBooking.setCity(city);
                                expoBooking.setPincode(pincode);
                                expoBooking.setStallNo(stallNo);

                                expoBooking.setAvailableStalls(availableStalls);
                                expoBooking.setExpoStartdate(expoStartdate);
                                expoBooking.setExpoEnddate(expoEnddate);
                                expoBooking.setPoliceStationDetails(policeStationDetails);
                                expoBooking.setFireStationDetails(fireStationDetails);
                                expoBooking.setIncomeTaxOfficeDetails(incomeTaxOfficeDetails);
                                expoBooking.setInsuranceCompanyDetails(insuranceCompanyDetails);
                                expoBooking.setLocalMunicipalCorporationDetails(localMunicipalCorporationDetails);

                                expoBooking.setStallNoFrom(stallNoFrom);
                                expoBooking.setStallNoTo(stallNoTo);
                                expoBooking.setStallRent(stallRent);
                                expoBooking.setGst(gst);
                                expoBooking.setGstAmount(gstAmount);
                                expoBooking.setTotal(total);
                                expoBookingArrayList.add(expoBooking);

                                ExpoListResponse.ExpoBooking.Desc desc = flourishMasterList.get(i).getDesc();
                                String descEn = desc.getEn();
                                String descHi = desc.getHi();
                                ExpoListResponse.ExpoBooking.Desc desc1 = flourishMasterList.get(i).getDesc();
                                desc1.setEn(descEn);
                                desc1.setHi(descHi);
                                descArrayList.add(desc1);

                                ExpoListResponse.ExpoBooking.Title title = flourishMasterList.get(i).getTitle();
                                String titleEn = title.getEn();
                                String titleHi = title.getHi();
                                ExpoListResponse.ExpoBooking.Title title1 = flourishMasterList.get(i).getTitle();
                                title1.setEn(titleEn);
                                title1.setHi(titleHi);
                                titleArrayList.add(title1);


                            }

                            expoListAdapter = new ExpoListAdapter(ExpoListForStallBookingActivity.this,
                                    expoBookingArrayList, descArrayList, titleArrayList,
                                    new OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int i) {
                                            String str_id = (expoBookingArrayList.get(i).getId());

                                            Bundle bundle = new Bundle();
                                            bundle.putString("id", str_id);

                                   /* Intent intent = new Intent(ExpoListForStallBookingActivity.this, StallBookingActivity.class);
                                    intent.putExtra("id", str_id);
                                    startActivity(intent);*/

                                        }
                                    });

                            recyclerView.setAdapter(expoListAdapter);

                        } else {
                            Toast.makeText(ExpoListForStallBookingActivity.this, "Expo List Not Available", Toast.LENGTH_SHORT).show();
                        }

                    }

                }

                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }


            @Override
            public void onFailure(Call<ExpoListResponse> call, Throwable t) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void findViews() {
        img_back = (ImageView) findViewById(R.id.img_back_expo);
        img_add_expo = (ImageView) findViewById(R.id.img_add_expo);
        recyclerView = (RecyclerView) findViewById(R.id.rv_expo_booking);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //Attache Expo List Api Methode
        getExpoList();
    }

    //Mobile Key Handling
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ExpoListForStallBookingActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }


}