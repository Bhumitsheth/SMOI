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

import com.iipl.smoi.Adapter.DistributeLabelAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.DistributeLabelResponse;
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

public class DistributionLabelsActivity extends AppCompatActivity {

    ImageView img_back_dis_label;

    DistributeLabelAdapter distributeLabelAdapter;

    RecyclerView recyclerView;

    private ArrayList<DistributeLabelResponse.DistributedLabel> distributedLabelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution_labels);


        img_back_dis_label=findViewById(R.id.img_back_dis_label);


        recyclerView=findViewById(R.id.rv_dis_label_List);
        recyclerView.setLayoutManager(new LinearLayoutManager(DistributionLabelsActivity.this, LinearLayoutManager.VERTICAL, false));


        img_back_dis_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DistributionLabelsActivity.this.onBackPressed();
            }
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) DistributionLabelsActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getDistributeLabel();

        } else {
            Toast.makeText(DistributionLabelsActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
//            Snackbar snackbar = Snackbar.make(parent_view, "No Internet Connection", Snackbar.LENGTH_LONG);
//            snackbar.show();
        }




    }


    private void getDistributeLabel() {

        final ProgressDialog pDialog = new ProgressDialog(DistributionLabelsActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = DistributionLabelsActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_user_id = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String str_roll_id = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("login_id", str_user_id);
        requestBody.put("role_id", str_roll_id);

        Log.d("requestBody::>>", String.valueOf(requestBody));

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<DistributeLabelResponse> call = request.getDistributeLabel(str_token,requestBody);
        call.enqueue(new Callback<DistributeLabelResponse>() {
            @Override
            public void onResponse(Call<DistributeLabelResponse> call, Response<DistributeLabelResponse> response) {

                distributedLabelArrayList.clear();

                if (response.isSuccessful()) {
                    if (response.body().getStatus().matches("400")) {
                        Toast.makeText(DistributionLabelsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }else if (response.body().getStatus().matches("200")) {

                        Log.d("distributedLabel>>", response.body().getStatus());

                        List<DistributeLabelResponse.DistributedLabel> distributedLabelList = response.body().getDistributedLabels();
                        if (distributedLabelList != null && !distributedLabelList.isEmpty() && distributedLabelList.size() > 0) {
                            for (int i = 0; i < distributedLabelList.size(); i++) {

                                String id = distributedLabelList.get(i).getId();
                                String series = distributedLabelList.get(i).getSeriesName();
                                String createdAt = distributedLabelList.get(i).getCreatedAt();
                                String labelFrom = distributedLabelList.get(i).getDistributedLabelFrom();
                                String labelTo = distributedLabelList.get(i).getDistributedLabelTo() ;
                                String quantity = distributedLabelList.get(i).getQuantity();
                                String distributedOn = distributedLabelList.get(i).getDistributedOn();
                                String distributedTo = distributedLabelList.get(i).getDistributedTo();

                                DistributeLabelResponse.DistributedLabel distributedLabel = new DistributeLabelResponse.DistributedLabel();
                                distributedLabel.setId(id);
                                distributedLabel.setSeriesName(series);
                                distributedLabel.setCreatedAt(createdAt);
                                distributedLabel.setDistributedLabelFrom(labelFrom);
                                distributedLabel.setDistributedLabelTo(labelTo);
                                distributedLabel.setQuantity(quantity);
                                distributedLabel.setDistributedOn(distributedOn);
                                distributedLabel.setDistributedTo(distributedTo);
                                distributedLabelArrayList.add(distributedLabel);

                            }

                            distributeLabelAdapter = new DistributeLabelAdapter(DistributionLabelsActivity.this, distributedLabelArrayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                 /*   String str_id = (distributedLabelArrayList.get(position).getId());
                                    String str_firstname = (distributedLabelArrayList.get(position).getFirstName());
                                    String str_lastname = (distributedLabelArrayList.get(position).getLastName());
                                    String str_email = (distributedLabelArrayList.get(position).getEmail());
                                    String str_mobileno = (distributedLabelArrayList.get(position).getMobileNo());
                                    String str_address = (distributedLabelArrayList.get(position).getAddress());

                                    Bundle bundle = new Bundle();
                                    bundle.putString("str_firstname", str_firstname);
                                    bundle.putString("str_lastname", str_lastname);
                                    bundle.putString("str_email", str_email);
                                    bundle.putString("str_mobileno", str_mobileno);
                                    bundle.putString("str_address", str_address);

                                    MemberDetails memberDetails = new MemberDetails();
                                    memberDetails.setArguments(bundle);
                                    FragmentTransaction fragmentTransaction = DistributionLabelsActivity.this.getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, memberDetails);
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();*/



                                }
                            });

//                            recyclerView.setAdapter(distributeLabelAdapter);

                            recyclerView.setAdapter(distributeLabelAdapter);





                        } else {

                            Toast.makeText(DistributionLabelsActivity.this, "Distribute Label Not Available", Toast.LENGTH_SHORT).show();

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
            public void onFailure(Call<DistributeLabelResponse> call, Throwable t) {
                Log.e("tag::>>>>", "chapterList", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(DistributionLabelsActivity.this, "Something went wrong please try again", Toast.LENGTH_LONG).show();            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(DistributionLabelsActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}