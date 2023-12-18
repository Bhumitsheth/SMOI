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

import com.iipl.smoi.Adapter.RequestLabelAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.RequestLabelResponse;
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

public class RequestLabelsActivity extends AppCompatActivity {

    ImageView imageView,img_add_label;

    RequestLabelAdapter requestLabelAdapter;

    RecyclerView recyclerView;

    private ArrayList<RequestLabelResponse.RequestLabel> requestLabelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_labels);

        img_add_label = findViewById(R.id.img_add_label);

        imageView=findViewById(R.id.img_back_req);
        recyclerView=findViewById(R.id.rv_requestLabel);
        recyclerView.setLayoutManager(new LinearLayoutManager(RequestLabelsActivity.this, LinearLayoutManager.VERTICAL, false));


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestLabelsActivity.this.onBackPressed();
            }
        });

        img_add_label.setOnClickListener(view -> {
            Intent intent = new Intent(RequestLabelsActivity.this, CreateLabelActivity.class);
            startActivity(intent);
        });


        SharedPreferences sharedpreferences1 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String id = sharedpreferences1.getString(AppConstant.TAG_USER_ID, null);
        String roll_id = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);
        if (roll_id != null) {
            if (roll_id.matches(AppConstant.TAG_CHAPTER) || (roll_id.matches(AppConstant.TAG_AU))) {
                img_add_label.setVisibility(View.VISIBLE);
            }
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) RequestLabelsActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getRequestLabel();

        } else {
            Toast.makeText(RequestLabelsActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }

    private void getRequestLabel() {

        final ProgressDialog pDialog = new ProgressDialog(RequestLabelsActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = RequestLabelsActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_user_id = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String str_roll_id = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("login_id", str_user_id);
        requestBody.put("role_id", str_roll_id);

        Log.d("requestBody::>>", String.valueOf(requestBody));

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<RequestLabelResponse> call = request.getRequestLabels(str_token,requestBody);
        call.enqueue(new Callback<RequestLabelResponse>() {
            @Override
            public void onResponse(Call<RequestLabelResponse> call, Response<RequestLabelResponse> response) {

                requestLabelArrayList.clear();

                if (response.isSuccessful()) {
                    if (response.body().getStatus().matches("400")) {
                        Toast.makeText(RequestLabelsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }else if (response.body().getStatus().matches("200")) {

                        Log.d("Member>>", response.body().getStatus());

                        List<RequestLabelResponse.RequestLabel> requestLabels = response.body().getRequestLabels();
                        if (requestLabels != null && !requestLabels.isEmpty() && requestLabels.size() > 0) {
                            for (int i = 0; i < requestLabels.size(); i++) {

                                String id = requestLabels.get(i).getId();
                                String requestedByName = requestLabels.get(i).getRequestedByName();
                                String requestedToName = requestLabels.get(i).getRequestedToName();
                                String typeOfLabel = requestLabels.get(i).getTypeOfLabel();
                                String requiredLabel = requestLabels.get(i).getRequiredLabel();
                                String createdAt = requestLabels.get(i).getCreatedAt();

                                RequestLabelResponse.RequestLabel requestLabel = new RequestLabelResponse.RequestLabel();
                                requestLabel.setRequestedByName(requestedByName);
                                requestLabel.setRequestedToName(requestedToName);
                                requestLabel.setTypeOfLabel(typeOfLabel);
                                requestLabel.setRequiredLabel(requiredLabel);
                                requestLabel.setCreatedAt(createdAt);
                                requestLabelArrayList.add(requestLabel);

                            }

                            requestLabelAdapter = new RequestLabelAdapter(RequestLabelsActivity.this, requestLabelArrayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(int i) {
                                 /*   String str_id = (availableLabelArrayList.get(i).getId());
                                    String str_firstname = (availableLabelArrayList.get(i).getFirstName());
                                    String str_lastname = (availableLabelArrayList.get(i).getLastName());
                                    String str_email = (availableLabelArrayList.get(i).getEmail());
                                    String str_mobileno = (availableLabelArrayList.get(i).getMobileNo());
                                    String str_address = (availableLabelArrayList.get(i).getAddress());

                                    Bundle bundle = new Bundle();
                                    bundle.putString("str_firstname", str_firstname);
                                    bundle.putString("str_lastname", str_lastname);
                                    bundle.putString("str_email", str_email);
                                    bundle.putString("str_mobileno", str_mobileno);
                                    bundle.putString("str_address", str_address);

                                    MemberDetails memberDetails = new MemberDetails();
                                    memberDetails.setArguments(bundle);
                                    FragmentTransaction fragmentTransaction = RequestLabelsActivity.this.getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, memberDetails);
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();*/
                                }
                            });

                            recyclerView.setAdapter(requestLabelAdapter);

                        } else {

                            Toast.makeText(RequestLabelsActivity.this, "List Not Available", Toast.LENGTH_SHORT).show();

                        }

                    }

                }

                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }


            @Override
            public void onFailure(Call<RequestLabelResponse> call, Throwable t) {
                Log.e("tag::>>>>", "chapterList", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(RequestLabelsActivity.this, "Something went wrong please try again", Toast.LENGTH_LONG).show();            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(RequestLabelsActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}