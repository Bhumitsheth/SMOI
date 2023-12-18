package com.iipl.smoi.Screens.ActivityActions;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.Toast;

import com.iipl.smoi.Adapter.AvailableLabelAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.AvailableLabelResponse;
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

public class AvailableLabelsActivity extends AppCompatActivity {

    ImageView img_back_avl_label;

    AvailableLabelAdapter availableLabelAdapter;

    RecyclerView recyclerView;

    private ArrayList<AvailableLabelResponse.AvailableLabel> availableLabelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_labels);

        img_back_avl_label = findViewById(R.id.img_back_avl_label);
        recyclerView = findViewById(R.id.rv_avail_label_List);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        img_back_avl_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getAvailableLabel();

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//            Snackbar snackbar = Snackbar.make(parent_view, "No Internet Connection", Snackbar.LENGTH_LONG);
//            snackbar.show();
        }



    }

    private void getAvailableLabel() {

        final ProgressDialog pDialog = new ProgressDialog(AvailableLabelsActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_user_id = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String str_roll_id = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("login_id", str_user_id);
        requestBody.put("role_id", str_roll_id);

        Log.d("requestBody::>>", String.valueOf(requestBody));

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<AvailableLabelResponse> call = request.getAvailableLabel(str_token, requestBody);
        call.enqueue(new Callback<AvailableLabelResponse>() {
            @Override
            public void onResponse(Call<AvailableLabelResponse> call, Response<AvailableLabelResponse> response) {

                availableLabelArrayList.clear();

                if (response.isSuccessful()) {
                    if (response.body().getStatus().matches("400")) {
                        Toast.makeText(AvailableLabelsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } else if (response.body().getStatus().matches("200")) {

                        Log.d("Member>>", response.body().getStatus());

                        List<AvailableLabelResponse.AvailableLabel> availableLabelList = response.body().getAvailableLabels();
                        if (availableLabelList != null && !availableLabelList.isEmpty() && availableLabelList.size() > 0) {
                            for (int i = 0; i < availableLabelList.size(); i++) {

                                String id = availableLabelList.get(i).getId();
                                String series = availableLabelList.get(i).getSeries();
                                String createdAt = availableLabelList.get(i).getCreatedAt();
                                String labelFrom = availableLabelList.get(i).getLabelFrom();
                                String labelTo = availableLabelList.get(i).getLabelTo();
                                String quantity = availableLabelList.get(i).getQuantity();
                                String remainingQty = availableLabelList.get(i).getRemainingQty();
                                String typeOfLabel = availableLabelList.get(i).getTypeOfLabel();

                                AvailableLabelResponse.AvailableLabel availableLabel = new AvailableLabelResponse.AvailableLabel();
                                availableLabel.setId(id);
                                availableLabel.setSeries(series);
                                availableLabel.setCreatedAt(createdAt);
                                availableLabel.setLabelFrom(labelFrom);
                                availableLabel.setLabelTo(labelTo);
                                availableLabel.setQuantity(quantity);
                                availableLabel.setRemainingQty(remainingQty);
                                availableLabel.setTypeOfLabel(typeOfLabel);
                                availableLabelArrayList.add(availableLabel);

                            }

                            availableLabelAdapter = new AvailableLabelAdapter(AvailableLabelsActivity.this, availableLabelArrayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                 /*   String str_id = (availableLabelArrayList.get(position).getId());
                                    String str_firstname = (availableLabelArrayList.get(position).getFirstName());
                                    String str_lastname = (availableLabelArrayList.get(position).getLastName());
                                    String str_email = (availableLabelArrayList.get(position).getEmail());
                                    String str_mobileno = (availableLabelArrayList.get(position).getMobileNo());
                                    String str_address = (availableLabelArrayList.get(position).getAddress());

                                    Bundle bundle = new Bundle();
                                    bundle.putString("str_firstname", str_firstname);
                                    bundle.putString("str_lastname", str_lastname);
                                    bundle.putString("str_email", str_email);
                                    bundle.putString("str_mobileno", str_mobileno);
                                    bundle.putString("str_address", str_address);

                                    MemberDetails memberDetails = new MemberDetails();
                                    memberDetails.setArguments(bundle);
                                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, memberDetails);
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();*/
                                }
                            });

                            recyclerView.setAdapter(availableLabelAdapter);

                        } else {
                            Toast.makeText(getApplicationContext(), "Label List Not Available", Toast.LENGTH_SHORT).show();
                        }

                    } else {
//                        Snackbar snackbar = Snackbar.make(parent_view, "chapterList Failed", Snackbar.LENGTH_LONG);
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
            public void onFailure(Call<AvailableLabelResponse> call, Throwable t) {
                Log.e("tag::>>>>", "chapterList", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();            }


        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(AvailableLabelsActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}