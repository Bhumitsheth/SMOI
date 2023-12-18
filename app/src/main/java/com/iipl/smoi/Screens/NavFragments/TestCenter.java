package com.iipl.smoi.Screens.NavFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Adapter.NewsAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.TestCenterResponse;
import com.iipl.smoi.Model.TestCenterResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Screens.Fragment.NewsDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestCenter extends Fragment {

    ImageView img_back,img;

    TextView tv_title,tv_date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_test_center, container, false);

        img_back = view.findViewById(R.id.img_back);
        img = view.findViewById(R.id.img);
        tv_title = view.findViewById(R.id.tv_title);
        tv_date = view.findViewById(R.id.tv_date);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getTestCenter();

        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//            Snackbar snackbar = Snackbar.make(parent_view, "No Internet Connection", Snackbar.LENGTH_LONG);
//            snackbar.show();
        }


        return  view;
    }

    private void getTestCenter() {


        final ProgressDialog pDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<TestCenterResponse> call = request.getTestCenter();
        call.enqueue(new Callback<TestCenterResponse>() {
            @Override
            public void onResponse(Call<TestCenterResponse> call, Response<TestCenterResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("faq>>", response.body().getStatus());

                        List<TestCenterResponse.TestCenter> newsList = response.body().getTestCenter();
                        if (newsList != null && !newsList.isEmpty() && newsList.size() > 0) {
                            for (int i = 0; i < newsList.size(); i++) {

                                String id = newsList.get(i).getId();
                                String title = newsList.get(i).getTitle();
                                String testImage = newsList.get(i).getTestImage();
                                String createdAt = newsList.get(i).getCreatedAt();

                                tv_title.setText(title);
                                tv_date.setText(createdAt);

                                Glide.with(getActivity())
                                        .load(testImage)
                                        .placeholder(R.drawable.bg)
                                        .error(R.drawable.bg)
                                        .into(img);

                                TestCenterResponse.TestCenter newsList1 = new TestCenterResponse.TestCenter();
                                newsList1.setId(id);
                                newsList1.setTitle(title);
                                newsList1.setTestImage(testImage);
                                newsList1.setCreatedAt(createdAt);




                            }

                        } else {
                            Toast.makeText(getContext(), R.string.test_center+" Not Available", Toast.LENGTH_SHORT).show();
//                            Snackbar snackbar = Snackbar.make(parent_view, "Faq Not Available", Snackbar.LENGTH_LONG);
//                            snackbar.show();
                        }

                    } else {
//                        Snackbar snackbar = Snackbar.make(parent_view, "Faqs Failed", Snackbar.LENGTH_LONG);
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
            public void onFailure(Call<TestCenterResponse> call, Throwable t) {
                Log.e("tag::>>>>", "Faqs", t);
//                Snackbar snackbar = Snackbar.make(parent_view, "Faqs Failure", Snackbar.LENGTH_LONG);
//                snackbar.show();
                // Toast.makeText(getContext(),"Mission fail",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.currentFragment = String.valueOf(R.string.test_center);
        Log.d("QR Scanner::>>", MainActivity.currentFragment = String.valueOf(R.string.test_center));
        ((MainActivity) getActivity()).getFragmentTag(2);
    }

}