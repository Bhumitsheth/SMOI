package com.iipl.smoi.Screens.ActivityActions;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
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
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.CompanyStatusResponse;
import com.iipl.smoi.Model.StateResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Utils.RealPathUtil;

import java.util.ArrayList;
import java.util.List;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StallBookingActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView img_back_expo_list;
    private ImageView image_stalls_bookings;
    private TextInputEditText et_reference_no;
    private TextInputEditText et_name_of_the_owner;
    private TextInputEditText et_name_of_the_company;
    private TextInputEditText et_door_no;
    private TextInputEditText et_street_no_or_name;
    private TextInputEditText et_location;
    private TextInputEditText et_area;
    private TextInputEditText et_city;
    private TextInputEditText et_district;
    private TextInputEditText et_pincode;
    private TextView tv_state;
    private LinearLayout ll_state;
    private TextInputEditText et_contact_person;
    private TextInputEditText et_telephone_no;
    private TextInputEditText et_mobileno;
    private TextInputEditText et_email;

    private TextInputEditText et_product_covered;
    private TextInputEditText et_product_speciality;
    private TextInputEditText et_awards;
    private TextInputEditText et_specific_info;
    private TextInputEditText et_select_signature_image;
    private ImageViewZoom img_signature;
    private MaterialButton btn_next;

    private TextView tv_company_status;
    private LinearLayout ll_company_status;


    int SIGNATURE_IMAGE = 100;

    static String signature_path = "";

    static String TAG_STATE;
    static String TAG_COMPANY_STATUS;

    String TAG_EXPO_ID;

    private ArrayList<StateResponse.State> stateArrayList = new ArrayList<>();
    private ArrayList<String> stringStateArrayList = new ArrayList<>();

    private ArrayList<CompanyStatusResponse.TypeOfBusiness> companyStatusArrayList = new ArrayList<>();
    private ArrayList<String> stringCompanyStatusArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stall_booking);

        findViews();


        SharedPreferences sharedpreferences1 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String roll_id = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, "NA");
        String firstName = sharedpreferences1.getString(AppConstant.TAG_firstName, "NA");
        String companyName = sharedpreferences1.getString(AppConstant.TAG_companyName, "NA");
        String address = sharedpreferences1.getString(AppConstant.TAG_address, "NA");
        String area = sharedpreferences1.getString(AppConstant.TAG_AREA, "NA");
        String city = sharedpreferences1.getString(AppConstant.TAG_city, "NA");
        String pinCode = sharedpreferences1.getString(AppConstant.TAG_pincode, "NA");
        String ContactPerson = sharedpreferences1.getString(AppConstant.TAG_CONTACT_PERSON, "NA");
        String telephone1 = sharedpreferences1.getString(AppConstant.TAG_telephone1, "NA");
        String mobileNo = sharedpreferences1.getString(AppConstant.TAG_mobileNo, "NA");
        String referenceNo = sharedpreferences1.getString(AppConstant.TAG_Reference_No, "NA");
        if (!(roll_id == null)) {
           et_name_of_the_owner.setText(firstName);
           et_name_of_the_company.setText(companyName);
           et_location.setText(address);
           et_area.setText(area);
           et_city.setText(city);
           et_pincode.setText(pinCode);
           et_contact_person.setText(ContactPerson);
           et_telephone_no.setText(telephone1);
           et_mobileno.setText(mobileNo);
           et_reference_no.setText(referenceNo);
        }



        //Get Data From ExpoList  Class
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            TAG_EXPO_ID = bundle.getString("expo_id");
        }

        img_back_expo_list.setOnClickListener(view -> {
            finish();
        });


        //Internet Connection Check
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            //Get Company Status
            getCompanyStatus();
            //Set Company Status
            setCompanyStatus();

            //Get State Address
            getState();
            //Set State Address
            setState();


        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onClick(View v) {
        if (v == btn_next) {
            // Handle clicks for btn_next

            getValidation();

            /*Intent intent = new Intent(StallBookingActivity.this, StallNoBookingActivity.class);
            intent.putExtra("expo_id", TAG_EXPO_ID);
            intent.putExtra("et_reference_no", et_reference_no.getText().toString());
            intent.putExtra("et_name_of_the_owner", et_name_of_the_owner.getText().toString());
            intent.putExtra("et_name_of_the_company", et_name_of_the_company.getText().toString());
            intent.putExtra("et_door_no", et_door_no.getText().toString());
            intent.putExtra("et_street_no_or_name", et_street_no_or_name.getText().toString());
            intent.putExtra("et_location", et_location.getText().toString());
            intent.putExtra("et_area", et_area.getText().toString());
            intent.putExtra("et_city", et_city.getText().toString());
            intent.putExtra("et_district", et_district.getText().toString());
            intent.putExtra("tv_state", tv_state.getText().toString());
            intent.putExtra("et_pincode", et_pincode.getText().toString());
            intent.putExtra("et_contact_person", et_contact_person.getText().toString());
            intent.putExtra("et_telephone_no", et_telephone_no.getText().toString());
            intent.putExtra("et_mobileno", et_mobileno.getText().toString());
            intent.putExtra("et_email", et_email.getText().toString());
            intent.putExtra("tv_company_status", tv_company_status.getText().toString());
            intent.putExtra("et_product_covered", et_product_covered.getText().toString());
            intent.putExtra("et_product_speciality", et_product_speciality.getText().toString());
            intent.putExtra("et_awards", et_awards.getText().toString());
            intent.putExtra("et_specific_info", et_specific_info.getText().toString());
            intent.putExtra("signature_path", signature_path);
            startActivity(intent);*/

        } else if (v == et_select_signature_image) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, SIGNATURE_IMAGE);
        }


    }

    private void getValidation() {
        if (et_reference_no.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Reference Number", Toast.LENGTH_SHORT).show();
            et_reference_no.requestFocus();
        } else if (et_name_of_the_owner.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Owner Name", Toast.LENGTH_SHORT).show();
            et_name_of_the_owner.requestFocus();
        } else if (et_name_of_the_company.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Company Name", Toast.LENGTH_SHORT).show();
            et_name_of_the_company.requestFocus();
        } else if (et_door_no.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Door No.", Toast.LENGTH_SHORT).show();
            et_door_no.requestFocus();
        } else if (et_street_no_or_name.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Street No. or Name", Toast.LENGTH_SHORT).show();
            et_street_no_or_name.requestFocus();
        } else if (et_location.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Location", Toast.LENGTH_SHORT).show();
            et_location.requestFocus();
        } else if (et_area.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Area", Toast.LENGTH_SHORT).show();
            et_area.requestFocus();
        } else if (et_city.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type City", Toast.LENGTH_SHORT).show();
            et_city.requestFocus();
        } else if (et_district.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type District", Toast.LENGTH_SHORT).show();
            et_district.requestFocus();
        } else if (tv_state.getText().toString().equalsIgnoreCase("Select State")) {
            Toast.makeText(StallBookingActivity.this, "Select State", Toast.LENGTH_SHORT).show();
        } else if (et_pincode.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Pin Code", Toast.LENGTH_SHORT).show();
            et_pincode.requestFocus();
        } else if (et_contact_person.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Contact Person", Toast.LENGTH_SHORT).show();
            et_contact_person.requestFocus();
        } else if (et_telephone_no.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Telephone No.", Toast.LENGTH_SHORT).show();
            et_telephone_no.requestFocus();
        } else if (et_mobileno.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Mobile No.", Toast.LENGTH_SHORT).show();
            et_mobileno.requestFocus();
        }else if (et_email.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Email Id", Toast.LENGTH_SHORT).show();
            et_email.requestFocus();
        } else if (tv_company_status.getText().toString().equalsIgnoreCase("Select Company Status")) {
            Toast.makeText(StallBookingActivity.this, "Type Company Status", Toast.LENGTH_SHORT).show();
        }else if (et_product_covered.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Product Covered", Toast.LENGTH_SHORT).show();
            et_product_covered.requestFocus();
        }else if (et_product_speciality.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Product Speciality", Toast.LENGTH_SHORT).show();
            et_product_speciality.requestFocus();
        } else if (et_awards.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Awards", Toast.LENGTH_SHORT).show();
            et_awards.requestFocus();
        }  else if (et_specific_info.getText().toString().isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Type Specific Information", Toast.LENGTH_SHORT).show();
            et_specific_info.requestFocus();
        }else if (signature_path.isEmpty()) {
            Toast.makeText(StallBookingActivity.this, "Select Your Signature", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(StallBookingActivity.this, StallNoBookingActivity.class);
            intent.putExtra("expo_id", TAG_EXPO_ID);
            intent.putExtra("et_reference_no", et_reference_no.getText().toString());
            intent.putExtra("et_name_of_the_owner", et_name_of_the_owner.getText().toString());
            intent.putExtra("et_name_of_the_company", et_name_of_the_company.getText().toString());
            intent.putExtra("et_door_no", et_door_no.getText().toString());
            intent.putExtra("et_street_no_or_name", et_street_no_or_name.getText().toString());
            intent.putExtra("et_location", et_location.getText().toString());
            intent.putExtra("et_area", et_area.getText().toString());
            intent.putExtra("et_city", et_city.getText().toString());
            intent.putExtra("et_district", et_district.getText().toString());
            intent.putExtra("tv_state", tv_state.getText().toString());
            intent.putExtra("et_pincode", et_pincode.getText().toString());
            intent.putExtra("et_contact_person", et_contact_person.getText().toString());
            intent.putExtra("et_telephone_no", et_telephone_no.getText().toString());
            intent.putExtra("et_mobileno", et_mobileno.getText().toString());
            intent.putExtra("et_email", et_email.getText().toString());
            intent.putExtra("tv_company_status", tv_company_status.getText().toString());
            intent.putExtra("et_product_covered", et_product_covered.getText().toString());
            intent.putExtra("et_product_speciality", et_product_speciality.getText().toString());
            intent.putExtra("et_awards", et_awards.getText().toString());
            intent.putExtra("et_specific_info", et_specific_info.getText().toString());
            intent.putExtra("signature_path", signature_path);
            startActivity(intent);
        }


    }

    private void setCompanyStatus() {
        ll_company_status.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(StallBookingActivity.this);
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

            selectState.setText(R.string.select_company_status);

            ArrayAdapter<String> adapter = new ArrayAdapter(StallBookingActivity.this, android.R.layout.simple_list_item_1, stringCompanyStatusArrayList);
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

                    TAG_COMPANY_STATUS = adapter.getItem(position);

                    tv_company_status.setText(TAG_COMPANY_STATUS);

//                    Log.d("State_POS>>>", String.valueOf(position));

                    for (CompanyStatusResponse.TypeOfBusiness typeOfBusiness : companyStatusArrayList) {
                        String s = String.valueOf(typeOfBusiness.getId());
                        String name = String.valueOf(typeOfBusiness.getTypeOfBusiness());
                        Log.d("FindStateId>>>", s + " " + name);

                        if (typeOfBusiness.getTypeOfBusiness().equalsIgnoreCase(tv_company_status.getText().toString())) {
                            String comId = typeOfBusiness.getId();
//                            TAG_COMPANY_STATUS=comId;
                            Log.d("comId>>>", TAG_COMPANY_STATUS + " " + comId);
                        }

                    }
                    dialog.dismiss();

                }
            });

        });
    }

    private void getCompanyStatus() {
        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<CompanyStatusResponse> call = request.getCompanyStatus();
        call.enqueue(new Callback<CompanyStatusResponse>() {
            @Override
            public void onResponse(Call<CompanyStatusResponse> call, Response<CompanyStatusResponse> response) {

                companyStatusArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("Company Status>>", response.body().getStatus());

                        List<CompanyStatusResponse.TypeOfBusiness> typeOfBusinesses = response.body().getTypeOfBusiness();
                        if (typeOfBusinesses != null && !typeOfBusinesses.isEmpty() && typeOfBusinesses.size() > 0) {
                            for (int i = 0; i < typeOfBusinesses.size(); i++) {

                                String typeOfBusiness = typeOfBusinesses.get(i).getTypeOfBusiness();
                                String id = typeOfBusinesses.get(i).getId();

                                CompanyStatusResponse.TypeOfBusiness typeOfBusiness1 = new CompanyStatusResponse.TypeOfBusiness();
                                typeOfBusiness1.setTypeOfBusiness(typeOfBusiness);
                                typeOfBusiness1.setId(id);
                                companyStatusArrayList.add(typeOfBusiness1);
                                stringCompanyStatusArrayList.add(typeOfBusiness);
                            }
                        } else {
                            Toast.makeText(StallBookingActivity.this, "Company Status Not Available", Toast.LENGTH_SHORT).show();
                        }

                    }
                }


            }

            @Override
            public void onFailure(Call<CompanyStatusResponse> call, Throwable t) {
                Log.d("AddExpoTAG_Success::>>", call.toString() + " " + t.toString());
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setState() {
        ll_state.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(StallBookingActivity.this);
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

            ArrayAdapter<String> adapter = new ArrayAdapter(StallBookingActivity.this, android.R.layout.simple_list_item_1, stringStateArrayList);
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

                    tv_state.setText(TAG_STATE);

                    Log.d("State_POS>>>", String.valueOf(position));

                    for (StateResponse.State state1 : stateArrayList) {
                        String s = String.valueOf(state1.getId());
                        String sname = String.valueOf(state1.getName());
                        Log.d("FindStateId>>>", s + " " + sname);

                        if (state1.getName().equalsIgnoreCase(tv_state.getText().toString())) {
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
                            Toast.makeText(StallBookingActivity.this, "State Not Available", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<StateResponse> call, Throwable t) {

            }
        });

    }

    private void findViews() {
        img_back_expo_list = (ImageView) findViewById(R.id.img_back_expo_list);
        image_stalls_bookings = (ImageView) findViewById(R.id.image_stalls_bookings);

        et_reference_no = (TextInputEditText) findViewById(R.id.et_reference_no);
        et_name_of_the_owner = (TextInputEditText) findViewById(R.id.et_name_of_the_owner);
        et_name_of_the_company = (TextInputEditText) findViewById(R.id.et_name_of_the_company);
        et_door_no = (TextInputEditText) findViewById(R.id.et_door_no);
        et_street_no_or_name = (TextInputEditText) findViewById(R.id.et_street_no_or_name);
        et_location = (TextInputEditText) findViewById(R.id.et_location);
        et_area = (TextInputEditText) findViewById(R.id.et_area);
        et_city = (TextInputEditText) findViewById(R.id.et_city);
        et_district = (TextInputEditText) findViewById(R.id.et_district);
        et_pincode = (TextInputEditText) findViewById(R.id.et_pincode);

        ll_state = (LinearLayout) findViewById(R.id.ll_state);
        tv_state = (TextView) findViewById(R.id.tv_state);

        et_contact_person = (TextInputEditText) findViewById(R.id.et_contact_person);
        et_telephone_no = (TextInputEditText) findViewById(R.id.et_telephone_no);
        et_mobileno = (TextInputEditText) findViewById(R.id.et_mobileno);
        et_email = (TextInputEditText) findViewById(R.id.et_email);
        et_product_covered = (TextInputEditText) findViewById(R.id.et_product_covered);
        et_product_speciality = (TextInputEditText) findViewById(R.id.et_product_speciality);
        et_awards = (TextInputEditText) findViewById(R.id.et_awards);
        et_specific_info = (TextInputEditText) findViewById(R.id.et_specific_info);
        et_select_signature_image = (TextInputEditText) findViewById(R.id.et_select_signature_image);

        img_signature = (ImageViewZoom) findViewById(R.id.img_signature);
        btn_next = (MaterialButton) findViewById(R.id.btn_next);

        tv_company_status = (TextView) findViewById(R.id.et_company_status);
        ll_company_status = (LinearLayout) findViewById(R.id.ll_company_status);

        btn_next.setOnClickListener(this);
        et_select_signature_image.setOnClickListener(this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    //Get Image From Mobile
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGNATURE_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            Context context = StallBookingActivity.this;
            signature_path = RealPathUtil.getRealPath(context, uri);
            Bitmap bitmap = BitmapFactory.decodeFile(signature_path);
            img_signature.setVisibility(View.VISIBLE);
            img_signature.setImageBitmap(bitmap);
        }


    }


}