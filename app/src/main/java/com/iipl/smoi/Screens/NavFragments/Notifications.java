package com.iipl.smoi.Screens.NavFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iipl.smoi.Adapter.NotificationAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.NotificationsResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Screens.ActivityActions.GalleryListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notifications extends Fragment {

    ProgressDialog pDialog;

    ImageView img_toolbar_back;
    TextView tv_toolbar_title;

    NotificationAdapter notificationAdapter;

    List<NotificationsResponse> notificationsList =new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        img_toolbar_back = view.findViewById(R.id.img_toolbar_back);
        tv_toolbar_title = view.findViewById(R.id.tv_toolbar_title);

        tv_toolbar_title.setText(R.string.notifications);

        img_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getNotifications();

        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }


        return view;
    }

    private void getNotifications() {
        pDialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String user_id = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("login_id", user_id);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<List<NotificationsResponse>> call = request.getNotifications(requestBody);
        call.enqueue(new Callback<List<NotificationsResponse>>() {
            @Override
            public void onResponse(Call<List<NotificationsResponse>> call, Response<List<NotificationsResponse>> response) {

                if (response.isSuccessful()) {

                    notificationsList.addAll(response.body());

                    ArrayList<NotificationsResponse> list = new ArrayList<NotificationsResponse>();
                    for (int i = 0; i < notificationsList.size(); i++) {

                        /*notificationAdapter = new NotificationAdapter(notificationsList, getNotifications());

                        // below line is to set layout manager for our recycler view.
                        LinearLayoutManager manager = new LinearLayoutManager(getActivity());

                        // setting layout manager for our recycler view.
                        .setLayoutManager(manager);

                        // below line is to set adapter to our recycler view.
                        courseRV.setAdapter(recyclerViewAdapter);*/

                    }

//                    if (response.body()..matches("200")) {
//                    }


                }

                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<NotificationsResponse>> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
            }


        });

    }


}