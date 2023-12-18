package com.iipl.smoi.Screens.NavFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.iipl.smoi.Adapter.BlogViewAllAdapter;
import com.iipl.smoi.Adapter.EventsAdapter;
import com.iipl.smoi.Adapter.FaqsAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.EventsResponse;
import com.iipl.smoi.Model.EventsResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Screens.Fragment.BlogDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Events extends Fragment {

    EventsAdapter eventsAdapter;

    ImageView img_toolbar_back;

    TextView tv_toolbar_title;

    RecyclerView recyclerView;

    private ArrayList<EventsResponse.Event> eventArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_events, container, false);

        img_toolbar_back=view.findViewById(R.id.img_toolbar_back);
        tv_toolbar_title=view.findViewById(R.id.tv_toolbar_title);

        recyclerView = view.findViewById(R.id.rv_blogviewAll);
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

        eventsAdapter = new EventsAdapter(getContext(), eventArrayList,new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });

        recyclerView.setAdapter(eventsAdapter);


        tv_toolbar_title.setText(R.string.events);

        img_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getEvents();

        } else { Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }





        return view;
    }

    private void getEvents() {

        final ProgressDialog pDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<EventsResponse> call = request.getEvents();
        call.enqueue(new Callback<EventsResponse>() {
            @Override
            public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {

                eventArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("blog>>", response.body().getStatus());

                        List<EventsResponse.Event> events = response.body().getEvents();
                        if (events != null && !events.isEmpty() && events.size() > 0) {
                            for (int i = 0; i < events.size(); i++) {

                                String id = events.get(i).getId();
                                String pic = events.get(i).getGalleryimage();
                                String title_en = events.get(i).getTitle();
                                String desc = events.get(i).getDesc();
                                String updatedAt = events.get(i).getUpdatedAt();
                                String eventDate = events.get(i).getEventDate();

                                EventsResponse.Event event1 = new EventsResponse.Event();
                                event1.setGalleryimage(pic);
                                event1.setTitle(title_en);
                                event1.setDesc(desc);
                                event1.setUpdatedAt(updatedAt);
                                event1.setEventDate(eventDate);
                                eventArrayList.add(event1);

                            }

                            eventsAdapter.updateList(eventArrayList);

                        } else {

                            Toast.makeText(getContext(), "Event Not Available", Toast.LENGTH_SHORT).show();

                        }

                    } else {
//                        Snackbar snackbar = Snackbar.make(parent_view, "Blog Failed", Snackbar.LENGTH_LONG);
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
            public void onFailure(Call<EventsResponse> call, Throwable t) {
                Log.e("tag::>>>>", "Blog", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
//                Snackbar snackbar = Snackbar.make(parent_view, "Blog Failed", Snackbar.LENGTH_LONG);
//                snackbar.show();
                // Toast.makeText(getContext(),"Mission fail",Toast.LENGTH_LONG).show();
            }
        });
    }

}