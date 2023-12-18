package com.iipl.smoi.Screens.HomeGridFragments;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.iipl.smoi.Adapter.BuySilkAdapter;
import com.iipl.smoi.Adapter.MemberAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.BuySilkResponse;
import com.iipl.smoi.Model.CityResponse;
import com.iipl.smoi.Model.MemberResponse;
import com.iipl.smoi.Model.StateResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Screens.ActivityActions.MemberDetailsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuySilkActivity extends AppCompatActivity {


    String TAG_TITLE = "";

    String TAG_CITY = "";

    static String TAG_STATE;

    TextView tv_title_au, tv_state, tv_city, tv_buy_title, tv_buy_desc;

    LinearLayout ll_state, ll_city;

    static int TAG_START = 10;

//    MemberAdapter memberAdapter;

    BuySilkAdapter buySilkAdapter;

    private ArrayList<MemberResponse.Member> memberArrayList = new ArrayList<>();
    private ArrayList<StateResponse.State> stateArrayList = new ArrayList<>();
    private ArrayList<CityResponse.City> cityArrayList = new ArrayList<>();

    private ArrayList<String> stringStateArrayList = new ArrayList<>();
    private ArrayList<String> stringCityArrayList = new ArrayList<>();

    RecyclerView rv_memberList;

    NestedScrollView nestedScrollView;

    ProgressBar progressBar;

    ImageView img_back, img_search_result, image_filter;

    SearchView searchview_member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_silk);

        findViews();

        tv_title_au.setText(R.string.buysilks);
        tv_buy_title.setVisibility(View.VISIBLE);
        tv_buy_desc.setVisibility(View.VISIBLE);

        getBuySilk();

       /* Bundle bundle = getIntent().getExtras();
        if (!(bundle == null)) {
            TAG_TITLE = bundle.getString("title");
            tv_title_au.setText(TAG_TITLE);
            tv_buy_title.setVisibility(View.VISIBLE);
            tv_buy_desc.setVisibility(View.VISIBLE);
            getBuySilk();
        }*/


        image_filter.setOnClickListener(view1 -> {
            tv_state.setText(R.string.select_state);
            tv_city.setText(R.string.select_city);
            getMembersLazyLoading();
        });


        ll_state.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(BuySilkActivity.this);
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

            tv_city.setText(R.string.select_city);

            ArrayAdapter<String> adapter = new ArrayAdapter(BuySilkActivity.this, android.R.layout.simple_list_item_1, stringStateArrayList);
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

//                    getCity(String.valueOf(position + 1));
                    progressBar.setVisibility(View.GONE);

                    TAG_STATE = adapter.getItem(position);

                    tv_state.setText(TAG_STATE);

                    image_filter.setVisibility(View.VISIBLE);

                    Log.d("TAG_STATE>>>", TAG_STATE);

                    Log.d("State_POS>>>", String.valueOf(position));

                    for (StateResponse.State state1 : stateArrayList) {
                        String s = String.valueOf(state1.getId());
                        String sname = String.valueOf(state1.getName());
                        Log.d("FindStateId>>>", s + " " + sname);

                        if (state1.getName().equalsIgnoreCase(tv_state.getText().toString())) {
                            String sid = state1.getId();
//                            TAG_STATE=sid;
                            ll_city.setAlpha(1);
                            getCity(sid);
                            Log.d("sid>>>", sid);
                        }

                    }

                    getMembers();

                    dialog.dismiss();

                }
            });

        });


//        rv_memberList.setLayoutManager(new LinearLayoutManager(BuySilkActivity.this, LinearLayoutManager.VERTICAL, false));

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(BuySilkActivity.this, LinearLayoutManager.VERTICAL, false);
        rv_memberList.setLayoutManager(mLayoutManager);

        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1) != null) {
                    if ((scrollY >= (nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1).getMeasuredHeight() - nestedScrollView.getMeasuredHeight())) && scrollY > oldScrollY) {
                        TAG_START = TAG_START + 10;
                        progressBar.setVisibility(View.VISIBLE);
                        getMembersLazyLoading(TAG_START);
                        tv_state.setText(R.string.select_state);
                        tv_city.setText(R.string.select_city);

                    }

                }

            }

        });

        buySilkAdapter = new BuySilkAdapter(BuySilkActivity.this, memberArrayList, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                String str_id = (memberArrayList.get(position).getId());
                String str_firstname = (memberArrayList.get(position).getFirstName());
                String str_lastname = (memberArrayList.get(position).getLastName());
                String str_email = (memberArrayList.get(position).getEmail());
                String str_mobileno = (memberArrayList.get(position).getMobileNo());
                String str_address = (memberArrayList.get(position).getAddress());
                String profileImage = (memberArrayList.get(position).getProfileImage());
                String city = (memberArrayList.get(position).getCity());

               /* Bundle bundle = new Bundle();
                bundle.putString("str_firstname", str_firstname);
                bundle.putString("str_lastname", str_lastname);
                bundle.putString("str_email", str_email);
                bundle.putString("str_mobileno", str_mobileno);
                bundle.putString("str_address", str_address);
                bundle.putString("city", city);
                bundle.putString("profileImage", profileImage);*/

                Intent intent = new Intent(BuySilkActivity.this, MemberDetailsActivity.class);
                intent.putExtra("str_firstname", str_firstname);
                intent.putExtra("str_lastname", str_lastname);
                intent.putExtra("str_email", str_email);
                intent.putExtra("str_mobileno", str_mobileno);
                intent.putExtra("str_address", str_address);
                intent.putExtra("profileImage", profileImage);
                startActivity(intent);

                /*MemberDetails memberDetails = new MemberDetails();
                memberDetails.setArguments(bundle);
                FragmentTransaction fragmentTransaction = BuySilkActivity.this.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragment_container, memberDetails);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

            }
        });

        rv_memberList.setAdapter(buySilkAdapter);

        searchview_member.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                filter(newText);
                return false;
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuySilkActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) BuySilkActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getState();

            getMembersLazyLoading();


        } else {
            Toast.makeText(BuySilkActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
//            Snackbar snackbar = Snackbar.make(parent_view, "No Internet Connection", Snackbar.LENGTH_LONG);
//            snackbar.show();
        }

        img_search_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                str_search = searchview_member.getQuery().toString();
            }
        });


    }

    private void findViews() {
        image_filter = findViewById(R.id.image_filter);

        tv_buy_title = findViewById(R.id.tv_buy_title);
        tv_buy_desc = findViewById(R.id.tv_buy_desc);

        tv_buy_title = findViewById(R.id.tv_buy_title);
        tv_title_au = findViewById(R.id.tv_title_au);
        tv_state = findViewById(R.id.tv_state);
        tv_city = findViewById(R.id.tv_city);

        ll_state = findViewById(R.id.ll_state);
        ll_city = findViewById(R.id.ll_city);

        img_back = findViewById(R.id.img_back_member);
        img_search_result = findViewById(R.id.img_search_result);

        searchview_member = findViewById(R.id.searchview_member);

        progressBar = findViewById(R.id.ProgressBar);

        nestedScrollView = findViewById(R.id.NestedScrollView);

        rv_memberList = findViewById(R.id.rv_memberList);

    }

    private void getBuySilk() {
        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<BuySilkResponse> call = request.getBuySilk();
        call.enqueue(new Callback<BuySilkResponse>() {
            @Override
            public void onResponse(Call<BuySilkResponse> call, Response<BuySilkResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        List<BuySilkResponse.BuySilk> stateList = response.body().getBuySilk();
                        if (stateList != null && !stateList.isEmpty() && stateList.size() > 0) {
                            for (int i = 0; i < stateList.size(); i++) {

                                String title = stateList.get(i).getTitle();
                                String description = stateList.get(i).getDescription();

                                tv_buy_title.setText(title);
                                tv_buy_desc.setText(description);

                               /* image_buysilk.setOnClickListener(view1 -> {
                                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(BuySilkActivity.this);
                                    View mView = getLayoutInflater().inflate(R.layout.layout_view_natural_types, null);
                                    TextView tv_title = mView.findViewById(R.id.tv_title);
                                    TextView tv_desc = mView.findViewById(R.id.tv_desc);

                                    tv_title.setText(title);
                                    tv_desc.setText((HtmlCompat.fromHtml(description, 0)));

                                    mBuilder.setView(mView);
                                    AlertDialog mDialog = mBuilder.create();
                                    mDialog.show();

                                });*/


                            }

                        } else {
                            Toast.makeText(BuySilkActivity.this, "Buy Silk Not Available", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<BuySilkResponse> call, Throwable t) {

            }
        });
    }

    private void SetCityData() {
        ll_city.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(BuySilkActivity.this);
            dialog.setContentView(R.layout.dialog_searchable_spinner);
//            dialog.getWindow().setLayout(800, 1000);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                dialog.getWindow().setStatusBarColor(getResources().getColor(R.color.appcolor));
                dialog.getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
            dialog.show();
            EditText editText = dialog.findViewById(R.id.edit_text);
            ListView listView = dialog.findViewById(R.id.list_view);

            editText.setVisibility(View.VISIBLE);

            TextView selectcity = dialog.findViewById(R.id.tv_select);
            selectcity.setText(R.string.select_city);


            ArrayAdapter<String> adapter = new ArrayAdapter(BuySilkActivity.this, android.R.layout.simple_list_item_1, stringCityArrayList);
            listView.setAdapter(adapter);

            adapter.notifyDataSetChanged();

            Log.d("SpinnerCity>>>", String.valueOf(stringCityArrayList));

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
                    progressBar.setVisibility(View.GONE);
                    tv_city.setText(adapter.getItem(position));
                    TAG_CITY = adapter.getItem(position);
                    getMembers();
                    dialog.dismiss();
                }
            });

        });
    }

    private void getCity(String stateId) {

        final RequestBody rb_state_id = RequestBody.create(MediaType.parse("multipart/form-file"), stateId);

        Log.d("stateId>>>", stateId);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<CityResponse> call = request.getCity(rb_state_id);
        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {

                cityArrayList.clear();

                stringCityArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        List<CityResponse.City> stateList = response.body().getCities();
                        if (stateList != null && !stateList.isEmpty() && stateList.size() > 0) {
                            for (int i = 0; i < stateList.size(); i++) {

                                String name = stateList.get(i).getName();

                                CityResponse.City city = new CityResponse.City();
                                city.setName(name);
                                cityArrayList.add(city);

                                Log.d("cityArrayList>>", name);

                                stringCityArrayList.add(name);

                                SetCityData();

                            }

                        } else {
                            Toast.makeText(BuySilkActivity.this, "City Not Available", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {

            }
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
                            Toast.makeText(BuySilkActivity.this, "State Not Available", Toast.LENGTH_SHORT).show();
                        }

                    }
                }


            }

            @Override
            public void onFailure(Call<StateResponse> call, Throwable t) {

            }
        });

    }

    private void getMembers() {
        final ProgressDialog pDialog = new ProgressDialog(BuySilkActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = BuySilkActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("state", tv_state.getText().toString());
        requestBody.put("city", TAG_CITY);
        requestBody.put("place", "");
        requestBody.put("area", "");

        Log.d("requestBody>>", String.valueOf(requestBody));

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<MemberResponse> call = request.getMembers(requestBody);
        call.enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {

                memberArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("400")) {
                        Toast.makeText(BuySilkActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        if (pDialog.isShowing()) {
                            pDialog.dismiss();
                        }

                    }

                    if (response.body().getStatus().matches("200")) {

                        Log.d("Member>>", response.body().getStatus());

                        List<MemberResponse.Member> memberList = response.body().getMember();
                        if (memberList != null && !memberList.isEmpty() && memberList.size() > 0) {
                            for (int i = 0; i < memberList.size(); i++) {

//                                String id = memberList.get(i).getId();
                                String username = memberList.get(i).getUsername();
                                String firstname = memberList.get(i).getFirstName();
                                String lastName = memberList.get(i).getLastName();
                                String email = memberList.get(i).getEmail();
                                String profileImage = memberList.get(i).getProfileImage();
                                String city = memberList.get(i).getCity();
                                String latitude = memberList.get(i).getLatitude();
                                String longitude = memberList.get(i).getLongitude();
                                String mobileNo = memberList.get(i).getMobileNo();
                                String address = memberList.get(i).getAddress();

                                MemberResponse.Member memberList1 = new MemberResponse.Member();
                                memberList1.setUsername(username);
                                memberList1.setFirstName(firstname);
                                memberList1.setLastName(lastName);
                                memberList1.setEmail(email);
                                memberList1.setProfileImage(profileImage);
                                memberList1.setCity(city);
                                memberList1.setLatitude(latitude);
                                memberList1.setLongitude(longitude);
                                memberList1.setMobileNo(mobileNo);
                                memberList1.setAddress(address);
                                memberArrayList.add(memberList1);

                            }

                            buySilkAdapter.updateList(memberArrayList);

                            if (pDialog.isShowing()) {
                                pDialog.dismiss();
                            }

                        } else {
                            Toast.makeText(BuySilkActivity.this, "MemberList Not Available", Toast.LENGTH_SHORT).show();
                        }

                    }

                }


            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
//                Log.e("tag::>>>>", "Member", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(BuySilkActivity.this, "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getMembersLazyLoading() {
        final ProgressDialog pDialog = new ProgressDialog(BuySilkActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = BuySilkActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("start", String.valueOf(TAG_START));

        Log.d("requestBody>>", String.valueOf(requestBody));

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<MemberResponse> call = request.getMembersLazyLoading(requestBody);
        call.enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {

                memberArrayList.clear();

//                rv_memberList.setVisibility(View.VISIBLE);

                if (response.isSuccessful()) {

                    Log.d("(Status>>)", response.body().getStatus());

                    if (response.body().getStatus().matches("400")) {
                        Toast.makeText(BuySilkActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        if (pDialog.isShowing()) {
                            pDialog.dismiss();
                        }

                    }

                    if (response.body().getStatus().matches("200")) {

                        Log.d("Member>>", response.body().getStatus());

                        List<MemberResponse.Member> memberList = response.body().getMember();
                        if (memberList != null && !memberList.isEmpty() && memberList.size() > 0) {
                            for (int i = 0; i < memberList.size(); i++) {

//                                String id = memberList.get(i).getId();
                                String username = memberList.get(i).getUsername();
                                String firstname = memberList.get(i).getFirstName();
                                String lastName = memberList.get(i).getLastName();
                                String email = memberList.get(i).getEmail();
                                String profileImage = memberList.get(i).getProfileImage();
                                String city = memberList.get(i).getCity();
                                String latitude = memberList.get(i).getLatitude();
                                String longitude = memberList.get(i).getLongitude();
                                String mobileNo = memberList.get(i).getMobileNo();
                                String address = memberList.get(i).getAddress();

                                MemberResponse.Member memberList1 = new MemberResponse.Member();
                                memberList1.setUsername(username);
                                memberList1.setFirstName(firstname);
                                memberList1.setLastName(lastName);
                                memberList1.setEmail(email);
                                memberList1.setProfileImage(profileImage);
                                memberList1.setCity(city);
                                memberList1.setLatitude(latitude);
                                memberList1.setLongitude(longitude);
                                memberList1.setMobileNo(mobileNo);
                                memberList1.setAddress(address);
                                memberArrayList.add(memberList1);

                            }

                            buySilkAdapter.updateList(memberArrayList);

                            if (pDialog.isShowing()) {
                                pDialog.dismiss();
                            }

                        } else {
                            Toast.makeText(BuySilkActivity.this, "MemberList Not Available", Toast.LENGTH_SHORT).show();
                        }

                    }

                }


            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
//                Log.e("tag::>>>>", "Member", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(BuySilkActivity.this, "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }

        });

    }

    private void getMembersLazyLoading(int start) {
        SharedPreferences sharedpreferences = BuySilkActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("start", String.valueOf(start));

        Log.d("requestBody>>", String.valueOf(requestBody));

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<MemberResponse> call = request.getMembersLazyLoading(requestBody);
        call.enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {

                memberArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("400")) {
                        Toast.makeText(BuySilkActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    if (response.body().getStatus().matches("200")) {

                        Log.d("Member>>", response.body().getStatus());

                        List<MemberResponse.Member> memberList = response.body().getMember();
                        if (memberList != null && !memberList.isEmpty() && memberList.size() > 0) {
                            for (int i = 0; i < memberList.size(); i++) {

//                                String id = memberList.get(i).getId();
                                String username = memberList.get(i).getUsername();
                                String firstname = memberList.get(i).getFirstName();
                                String lastName = memberList.get(i).getLastName();
                                String email = memberList.get(i).getEmail();
                                String profileImage = memberList.get(i).getProfileImage();
                                String city = memberList.get(i).getCity();
                                String latitude = memberList.get(i).getLatitude();
                                String longitude = memberList.get(i).getLongitude();
                                String mobileNo = memberList.get(i).getMobileNo();
                                String address = memberList.get(i).getAddress();

                                MemberResponse.Member memberList1 = new MemberResponse.Member();
                                memberList1.setUsername(username);
                                memberList1.setFirstName(firstname);
                                memberList1.setLastName(lastName);
                                memberList1.setEmail(email);
                                memberList1.setProfileImage(profileImage);
                                memberList1.setCity(city);
                                memberList1.setLatitude(latitude);
                                memberList1.setLongitude(longitude);
                                memberList1.setMobileNo(mobileNo);
                                memberList1.setAddress(address);
                                memberArrayList.add(memberList1);

                            }

                            buySilkAdapter.updateList(memberArrayList);

                        } else {
                            Toast.makeText(BuySilkActivity.this, "MemberList Not Available", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
//                Log.e("tag::>>>>", "Member", t);
            }
        });

    }

    private void filter(String text) {
        ArrayList<MemberResponse.Member> filteredlist = new ArrayList<>();
        for (MemberResponse.Member item : memberArrayList) {
//            rv_memberList.setVisibility(View.VISIBLE);
            if (item.getFirstName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
                Log.d("search:>>", String.valueOf(filteredlist.size()));
            }
        }
        if (filteredlist.isEmpty()) {
//            rv_memberList.setVisibility(View.GONE);
            Toast.makeText(BuySilkActivity.this, "No Match Found", Toast.LENGTH_SHORT).show();
        } else {
//            memberAdapter.filterList(filteredlist);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Member::>>", MainActivity.currentFragment = "Member");
//        ((MainActivity) BuySilkActivity.this).getFragmentTag(4);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(BuySilkActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }


}