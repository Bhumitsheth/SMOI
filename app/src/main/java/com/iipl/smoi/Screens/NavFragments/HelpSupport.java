package com.iipl.smoi.Screens.NavFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.HelpSupportResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpSupport extends Fragment {

    TextView tv_desc;

    ImageView img_back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_support, container, false);

        img_back = view.findViewById(R.id.img_back_helpsupport);

        tv_desc = view.findViewById(R.id.tv_desc);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getHelpSupport();

        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//            Snackbar snackbar = Snackbar.make(parent_view, "No Internet Connection", Snackbar.LENGTH_LONG);
//            snackbar.show();
        }

        return view;
    }

    private void getHelpSupport() {

        final ProgressDialog pDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<HelpSupportResponse> call = request.getHelpSupport();
        call.enqueue(new Callback<HelpSupportResponse>() {
            @Override
            public void onResponse(Call<HelpSupportResponse> call, Response<HelpSupportResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("faq>>", response.body().getStatus());

                        List<HelpSupportResponse.HelpAndSupport> helpAndSupportList = response.body().getHelpAndSupport();
                        if (helpAndSupportList != null && !helpAndSupportList.isEmpty() && helpAndSupportList.size() > 0) {
                            for (int i = 0; i < helpAndSupportList.size(); i++) {

                                String description = helpAndSupportList.get(i).getDescription();
                                tv_desc.setText(HtmlCompat.fromHtml(description,0));

                                Log.d("description::>>", description);

                            }

                        } else {
                            Toast.makeText(getContext(), R.string.tc+" Not Available", Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<HelpSupportResponse> call, Throwable t) {
                Log.e("tag::>>>>", "Faqs", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_LONG).show();            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("help::>>", MainActivity.currentFragment = "help");
        ((MainActivity) getActivity()).getFragmentTag(8);
    }

}