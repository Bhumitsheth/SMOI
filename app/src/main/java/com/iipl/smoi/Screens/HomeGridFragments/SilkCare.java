package com.iipl.smoi.Screens.HomeGridFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;


import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.SilkCareResponse;
import com.iipl.smoi.Model.SilkCareResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SilkCare extends Fragment {


    ImageView img_toolbar_back;

    ImageView img_silkcare1, img_silkcare2, img_silkcare3, img_silkcare4,
            img_silkcare5, img_silkcare6, img_silkcare7, img_silkcare8;

    TextView tv_toolbar_title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_silk_care, container, false);

        img_silkcare1 = view.findViewById(R.id.img_silkcare1);
        img_silkcare2 = view.findViewById(R.id.img_silkcare2);
        img_silkcare3 = view.findViewById(R.id.img_silkcare3);
        img_silkcare4 = view.findViewById(R.id.img_silkcare4);
        img_silkcare5 = view.findViewById(R.id.img_silkcare5);
        img_silkcare6 = view.findViewById(R.id.img_silkcare6);
        img_silkcare7 = view.findViewById(R.id.img_silkcare7);
        img_silkcare8 = view.findViewById(R.id.img_silkcare8);


        img_toolbar_back = view.findViewById(R.id.img_toolbar_back);
        tv_toolbar_title = view.findViewById(R.id.tv_toolbar_title);


        tv_toolbar_title.setText(R.string.silkcare);

        img_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getSilkCare();

        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }


        return view;

    }

    private void getSilkCare() {

        final ProgressDialog pDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<SilkCareResponse> call = request.getSilkCare();
        call.enqueue(new Callback<SilkCareResponse>() {
            @Override
            public void onResponse(Call<SilkCareResponse> call, Response<SilkCareResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("faq>>", response.body().getStatus());

                        List<SilkCareResponse.TestCenter> naturalList = response.body().getTestCenter();
                        if (naturalList != null && !naturalList.isEmpty() && naturalList.size() > 0) {
                            for (int i = 0; i < naturalList.size(); i++) {

                                String id = naturalList.get(i).getId();
                                String testImage1 = naturalList.get(i).getTestImage1();
                                String testImage2 = naturalList.get(i).getTestImage2();
                                String testImage3 = naturalList.get(i).getTestImage3();
                                String testImage4 = naturalList.get(i).getTestImage4();
                                String testImage5 = naturalList.get(i).getTestImage5();
                                String testImage6 = naturalList.get(i).getTestImage6();
                                String testImage7 = naturalList.get(i).getTestImage7();
                                String testImage8 = naturalList.get(i).getTestImage8();

                                Log.d("silk care Image::>>", testImage1 + " " + testImage2 + "testImage1");


                                Glide.with(getActivity())
                                        .load(testImage1)
                                        .placeholder(R.drawable.bg)
                                        .error(R.drawable.bg)
                                        .into(img_silkcare1);

                                Glide.with(getActivity())
                                        .load(testImage2)
                                        .placeholder(R.drawable.bg)
                                        .error(R.drawable.bg)
                                        .into(img_silkcare2);

                                Glide.with(getActivity())
                                        .load(testImage3)
                                        .placeholder(R.drawable.bg)
                                        .error(R.drawable.bg)
                                        .into(img_silkcare3);

                                Glide.with(getActivity())
                                        .load(testImage4)
                                        .placeholder(R.drawable.bg)
                                        .error(R.drawable.bg)
                                        .into(img_silkcare4);

                                Glide.with(getActivity())
                                        .load(testImage5)
                                        .placeholder(R.drawable.bg)
                                        .error(R.drawable.bg)
                                        .into(img_silkcare5);

                                Glide.with(getActivity())
                                        .load(testImage6)
                                        .placeholder(R.drawable.bg)
                                        .error(R.drawable.bg)
                                        .into(img_silkcare6);

                                Glide.with(getActivity())
                                        .load(testImage7)
                                        .placeholder(R.drawable.bg)
                                        .error(R.drawable.bg)
                                        .into(img_silkcare7);

                                Glide.with(getActivity())
                                        .load(testImage8)
                                        .placeholder(R.drawable.bg)
                                        .error(R.drawable.bg)
                                        .into(img_silkcare8);


                            }

                        } else {
                            Toast.makeText(getContext(), R.string.silks + " Not Available", Toast.LENGTH_SHORT).show();
//                            Snackbar snackbar = Snackbar.make(parent_view, "Faq Not Available", Snackbar.LENGTH_LONG);
//                            snackbar.show();
                        }

                    }
                }

                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<SilkCareResponse> call, Throwable t) {
                Log.e("tag::>>>>", "Faqs", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
//                Snackbar snackbar = Snackbar.make(parent_view, "Faqs Failure", Snackbar.LENGTH_LONG);
//                snackbar.show();
                // Toast.makeText(getContext(),"Mission fail",Toast.LENGTH_LONG).show();
            }
        });
    }

}

// Zb 67 Y 1 Sjn