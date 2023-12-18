package com.iipl.smoi.Screens.HomeGridFragments;

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

import com.bumptech.glide.Glide;
import com.iipl.smoi.Adapter.NaturalTypesAdapter;
import com.iipl.smoi.Adapter.NewsAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.NaturalTypeResponse;
import com.iipl.smoi.Model.NaturalTypeResponse;
import com.iipl.smoi.Model.NewsResponse;
import com.iipl.smoi.Model.SilksResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Screens.ActivityActions.ViewProfileActivity;
import com.iipl.smoi.Screens.Fragment.NewsDetails;

import java.util.ArrayList;
import java.util.List;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SilksFragment extends Fragment {

    ImageView img_back;

    NaturalTypesAdapter naturalTypesAdapter;

    TextView tv_title, tv_desc, tv_natural_title;

    ImageViewZoom img_banner;

    RecyclerView recyclerView;

    private final ArrayList<NaturalTypeResponse.Natural> naturalArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_silks, container, false);

        img_back = view.findViewById(R.id.img_back);

        tv_title = view.findViewById(R.id.tv_title);
        tv_desc = view.findViewById(R.id.tv_desc);
        tv_natural_title = view.findViewById(R.id.tv_natural_title);
        img_banner = view.findViewById(R.id.img_banner);

        recyclerView = view.findViewById(R.id.rv_silkTypeList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getSilks();

            getSilkTypes();

        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//            Snackbar snackbar = Snackbar.make(parent_view, "No Internet Connection", Snackbar.LENGTH_LONG);
//            snackbar.show();
        }


        return view;
    }

    private void getSilkTypes() {
        final ProgressDialog pDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<NaturalTypeResponse> call = request.getNaturalType();
        call.enqueue(new Callback<NaturalTypeResponse>() {
            @Override
            public void onResponse(Call<NaturalTypeResponse> call, Response<NaturalTypeResponse> response) {

                naturalArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("faq>>", response.body().getStatus());

                        List<NaturalTypeResponse.Natural> naturalList = response.body().getNatural();
                        if (naturalList != null && !naturalList.isEmpty() && naturalList.size() > 0) {
                            for (int i = 0; i < naturalList.size(); i++) {

                                String id = naturalList.get(i).getId();
                                String galleryimage = naturalList.get(i).getGalleryimage();
                                String title = naturalList.get(i).getTitle();
                                String des = naturalList.get(i).getDes();
                                NaturalTypeResponse.Natural naturalList1 = new NaturalTypeResponse.Natural();
                                naturalList1.setTitle(title);
                                naturalList1.setDes(des);
                                naturalList1.setGalleryimage(galleryimage);
                                naturalArrayList.add(naturalList1);

                            }

                            naturalTypesAdapter = new NaturalTypesAdapter(getContext(), naturalArrayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(int i) {

                                    String title = naturalList.get(i).getTitle();
                                    String des = naturalList.get(i).getDes();

                                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                                    View mView = getLayoutInflater().inflate(R.layout.layout_view_natural_types, null);
                                    TextView tv_title = mView.findViewById(R.id.tv_title);
                                    TextView tv_desc = mView.findViewById(R.id.tv_desc);

                                    tv_title.setText(title);
                                    tv_desc.setText((HtmlCompat.fromHtml(des, 0)));

                                    mBuilder.setView(mView);
                                    AlertDialog mDialog = mBuilder.create();
                                    mDialog.show();

                                }
                            });

                            recyclerView.setAdapter(naturalTypesAdapter);


                        } else {
                            Toast.makeText(getContext(), "Silks Type Not Available", Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<NaturalTypeResponse> call, Throwable t) {
                Log.e("tag::>>>>", "Faqs", t);
//                Snackbar snackbar = Snackbar.make(parent_view, "Faqs Failure", Snackbar.LENGTH_LONG);
//                snackbar.show();
                // Toast.makeText(getContext(),"Mission fail",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getSilks() {
        final ProgressDialog pDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<SilksResponse> call = request.getSilks();
        call.enqueue(new Callback<SilksResponse>() {
            @Override
            public void onResponse(Call<SilksResponse> call, Response<SilksResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("faq>>", response.body().getStatus());

                        List<SilksResponse.Info> naturalList = response.body().getInfo();
                        if (naturalList != null && !naturalList.isEmpty() && naturalList.size() > 0) {
                            for (int i = 0; i < naturalList.size(); i++) {

                                String id = naturalList.get(i).getId();
                                String topBannerTitle = naturalList.get(i).getTopBannerTitle();
                                String topBanner = naturalList.get(i).getTopBanner();
                                String bannerDesc = naturalList.get(i).getTopBannerDesOne();
                                String advantagesTitle = naturalList.get(i).getAdvantagesTitle();

                                tv_title.setText(topBannerTitle);
                                tv_desc.setText((HtmlCompat.fromHtml(bannerDesc, 0)));
                                tv_natural_title.setText(advantagesTitle);


                                Glide.with(getActivity())
                                        .load(topBanner)
                                        .placeholder(R.drawable.bg)
                                        .error(R.drawable.bg)
                                        .into(img_banner);

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
            public void onFailure(Call<SilksResponse> call, Throwable t) {
                Log.e("tag::>>>>", "Faqs", t);
            }
        });
    }


}