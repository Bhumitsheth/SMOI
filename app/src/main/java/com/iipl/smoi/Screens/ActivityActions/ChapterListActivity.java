package com.iipl.smoi.Screens.ActivityActions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iipl.smoi.Adapter.ChapterAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.GMap.ChapterMapsActivity;
import com.iipl.smoi.Model.ChapterResponse;
import com.iipl.smoi.Model.TestSilkResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapterListActivity extends AppCompatActivity implements ChapterAdapter.OnItemGMapClickListener {

    String TAG_TITLE = "";

    TextView tv_title_chapter, tv_test_title, tv_test_desc;

    ChapterAdapter chapterAdapter;

    private ArrayList<ChapterResponse.Chapter> chapterArrayList = new ArrayList<>();

    RecyclerView rv_chapterList;

    SearchView searchview_chapter;

    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);
        tv_title_chapter = findViewById(R.id.tv_title_chapter);
        tv_test_title = findViewById(R.id.tv_test_title);
        tv_test_desc = findViewById(R.id.tv_test_desc);

        searchview_chapter = findViewById(R.id.searchview_chapter);

        img_back = findViewById(R.id.img_back_chapter);

        rv_chapterList = findViewById(R.id.rv_chapterList);
        rv_chapterList.setLayoutManager(new LinearLayoutManager(ChapterListActivity.this, LinearLayoutManager.VERTICAL, false));

        Bundle intent = getIntent().getExtras();
        if (!(intent == null)) {
            TAG_TITLE = intent.getString("key");
            tv_title_chapter.setText(TAG_TITLE);
//            TAG_TITLE = intent.getString("title");
//            tv_title_chapter.setText(TAG_TITLE);
//            tv_test_title.setVisibility(View.VISIBLE);
//            tv_test_desc.setVisibility(View.VISIBLE);
//            getTestSilk();
        }
  /*
        SharedPreferences sharedpreferences1234 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String test_silk_gmap = sharedpreferences1234.getString(AppConstant.TAG_OPEN_TEST_SILK, null);
        if (!(test_silk_gmap == null)) {
            tv_title_chapter.setText(TAG_TITLE);
            tv_test_title.setVisibility(View.VISIBLE);
            tv_test_desc.setVisibility(View.VISIBLE);
            getTestSilk();
            //            sharedpreferences.edit().remove(AppConstant.TAG_OPEN_TEST_SILK).commit();
        }


        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(AppConstant.TAG_OPEN_TEST_SILK, "TAG_OPEN_TEST_SILK");
        editor.commit();*/


        searchview_chapter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChapterListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) ChapterListActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getChapter();

        } else {
            Toast.makeText(ChapterListActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }


    }

    private void getTestSilk() {

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<TestSilkResponse> call = request.getTestSilk();
        call.enqueue(new Callback<TestSilkResponse>() {
            @Override
            public void onResponse(Call<TestSilkResponse> call, Response<TestSilkResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        List<TestSilkResponse.HowToTestSilk> stateList = response.body().getHowToTestSilk();
                        if (stateList != null && !stateList.isEmpty() && stateList.size() > 0) {
                            for (int i = 0; i < stateList.size(); i++) {

                                String title = stateList.get(i).getHowToTestSilk();
                                String description = stateList.get(i).getHowToTestSilkDesc();

                                tv_test_title.setText(title);
                                tv_test_desc.setText(description);

                            }

                        } else {
                            Toast.makeText(ChapterListActivity.this, "Test Silk Not Available", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<TestSilkResponse> call, Throwable t) {

            }
        });
    }

    private void getChapter() {
        final ProgressDialog pDialog = new ProgressDialog(ChapterListActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = ChapterListActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<ChapterResponse> call = request.getChapter();
        call.enqueue(new Callback<ChapterResponse>() {
            @Override
            public void onResponse(Call<ChapterResponse> call, Response<ChapterResponse> response) {

                chapterArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("Member>>", response.body().getStatus());

                        List<ChapterResponse.Chapter> chapterList = response.body().getChapter();
                        if (chapterList != null && !chapterList.isEmpty() && chapterList.size() > 0) {
                            for (int i = 0; i < chapterList.size(); i++) {

                                String id = chapterList.get(i).getId();
                                String username = chapterList.get(i).getUsername();
                                String firstname = chapterList.get(i).getFirstName();
                                String lastName = chapterList.get(i).getLastName();
                                String email = chapterList.get(i).getEmail();
                                String profileImage = chapterList.get(i).getProfileImage();
                                String latitude = chapterList.get(i).getLatitude();
                                String longitude = chapterList.get(i).getLongitude();
                                String chapterAddress = chapterList.get(i).getAddress();
                                String mobileNo = chapterList.get(i).getMobileNo();

                                ChapterResponse.Chapter chapterList1 = new ChapterResponse.Chapter();
                                chapterList1.setUsername(username);
                                chapterList1.setFirstName(firstname);
                                chapterList1.setLastName(lastName);
                                chapterList1.setEmail(email);
                                chapterList1.setProfileImage(profileImage);
                                chapterList1.setLatitude(latitude);
                                chapterList1.setLongitude(longitude);
                                chapterList1.setAddress(chapterAddress);
                                chapterList1.setMobileNo(mobileNo);
                                chapterArrayList.add(chapterList1);

                            }

                            chapterAdapter = new ChapterAdapter(ChapterListActivity.this, chapterArrayList, (OnItemClickListener) position -> {
                                String str_id = (chapterArrayList.get(position).getId());
                                String str_firstname = (chapterArrayList.get(position).getFirstName());
                                String str_lastname = (chapterArrayList.get(position).getLastName());
                                String str_email = (chapterArrayList.get(position).getEmail());
                                String str_mobileno = (chapterArrayList.get(position).getMobileNo());
                                String str_address = (chapterArrayList.get(position).getAddress());
                                String profileImage = (chapterArrayList.get(position).getProfileImage());


                                Intent intent = new Intent(ChapterListActivity.this, ChapterDetails.class);
                                intent.putExtra("str_firstname", str_firstname);
                                intent.putExtra("str_lastname", str_lastname);
                                intent.putExtra("str_email", str_email);
                                intent.putExtra("str_mobileno", str_mobileno);
                                intent.putExtra("str_address", str_address);
                                intent.putExtra("profileImage", profileImage);
                                startActivity(intent);

                                /*com.iipl.smoi.Screens.Fragment.ChapterDetails chapterDetails = new ChapterDetails();
                                chapterDetails.setArguments(intent);
                                FragmentTransaction fragmentTransaction = ChapterListActivity.this.getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_container, chapterDetails);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();*/

                            });
                            getcallback();
                            rv_chapterList.setAdapter(chapterAdapter);
                        } else {

                            Toast.makeText(ChapterListActivity.this, "chapterList Not Available", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(ChapterListActivity.this, "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getcallback() {
        chapterAdapter.setOnItemGMap(this);
    }

    private void filter(String text) {

        ArrayList<ChapterResponse.Chapter> filteredlist = new ArrayList<>();
        for (ChapterResponse.Chapter item : chapterArrayList) {
            rv_chapterList.setVisibility(View.VISIBLE);
            if (item.getFirstName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
                Log.d("search:>>", String.valueOf(filteredlist.size()));
            }
        }
        if (filteredlist.isEmpty()) {
            rv_chapterList.setVisibility(View.GONE);
            Toast.makeText(ChapterListActivity.this, "No Match Found", Toast.LENGTH_SHORT).show();
        } else {
            chapterAdapter.filterList(filteredlist);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
//        Log.d("Chapter::>>", MainActivity.currentFragment = "Chapter");
//        ((MainActivity) ChapterListActivity.this).getFragmentTag(3);
    }


    @Override
    public void onItemGMapClick(int position) {
        String latitude = chapterArrayList.get(position).getLatitude();
        String longitude = chapterArrayList.get(position).getLongitude();

        Log.d("desti_loc>>>", latitude + " " + longitude);

        if (latitude != null && longitude != null) {
            if (!latitude.isEmpty()) {
                SharedPreferences sharedpreferences = ChapterListActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(AppConstant.TAG_LATITUDE, latitude);
                editor.putString(AppConstant.TAG_LONGITUDE, longitude);
                editor.commit();

                Intent intent = new Intent(ChapterListActivity.this, ChapterMapsActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(getApplicationContext(), "Not Available Latitude Longitude", Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(getApplicationContext(), "Not Available Latitude Longitude", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ChapterListActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}