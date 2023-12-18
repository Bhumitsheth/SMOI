package com.iipl.smoi.Screens.NavFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.iipl.smoi.Adapter.FaqsAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.FaqsResponse;
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


public class Faqs extends Fragment {

    Boolean isExecuted=true;

    FaqsAdapter faqsAdapter;

    RecyclerView rv_faqs;

    ImageView img_back;

    SearchView searchView;

    ImageView imageView_search;

    String str_search = "";

    private ArrayList<FaqsResponse.Faq> faqsArrayList = new ArrayList<>();
    private ArrayList<FaqsResponse.Faq.Title> titleArrayList = new ArrayList<>();
    private ArrayList<FaqsResponse.Faq.Description> descriptionArrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faqs, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        imageView_search = view.findViewById(R.id.tv_search);

        searchView = view.findViewById(R.id.searchview_faqs);

        img_back = view.findViewById(R.id.img_back_faq);

        rv_faqs = view.findViewById(R.id.rv_faqs);
        rv_faqs.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));

        faqsAdapter = new FaqsAdapter(getContext(), faqsArrayList, titleArrayList, descriptionArrayList, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });

        rv_faqs.setAdapter(faqsAdapter);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                filter(newText);
                return false;
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                getFaqs();
        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//            Snackbar snackbar = Snackbar.make(parent_view, "No Internet Connection", Snackbar.LENGTH_LONG);
//            snackbar.show();
        }

        imageView_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_search = searchView.getQuery().toString();
                getFaqs();
            }
        });

        return view;
    }

    private void getFaqs() {
        final ProgressDialog pDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        String s=searchView.getQuery().toString();

        Log.d("str_search::>>",str_search);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("search", str_search);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<FaqsResponse> call = request.getFaq(requestBody);
        call.enqueue(new Callback<FaqsResponse>() {
            @Override
            public void onResponse(Call<FaqsResponse> call, Response<FaqsResponse> response) {
                faqsArrayList.clear();
                titleArrayList.clear();
                descriptionArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("faq>>", response.body().getStatus());

                        List<FaqsResponse.Faq> faqsList = response.body().getFaq();
                        if (faqsList != null && !faqsList.isEmpty() && faqsList.size() > 0) {
                            for (int i = 0; i < faqsList.size(); i++) {

                                Log.d("faq_list>>", faqsList.get(i).getTitleEn());

                                String id = faqsList.get(i).getId();
                                String eng_title = faqsList.get(i).getTitleEn();
                                FaqsResponse.Faq faqsList1 = new FaqsResponse.Faq();
                                faqsList1.setId(id);
                                faqsList1.setTitleEn(eng_title);
                                faqsArrayList.add(faqsList1);

                                FaqsResponse.Faq.Title title = faqsList.get(i).getTitle();
                                String title_en = title.getEn();
                                String title_hi = title.getHi();
                                FaqsResponse.Faq.Title title1 = faqsList.get(i).getTitle();
                                title1.setEn(title_en);
                                title1.setHi(title_hi);
                                titleArrayList.add(title1);

                                FaqsResponse.Faq.Description description = faqsList.get(i).getDescription();
                                String FaqsDescription_en = description.getEn();
                                String FaqsDescription_hi = description.getHi();
                                FaqsResponse.Faq.Description description1 = faqsList.get(i).getDescription();
                                description1.setEn(FaqsDescription_en);
                                description1.setHi(FaqsDescription_hi);
                                descriptionArrayList.add(description1);

                            }
                            faqsAdapter.updateList(faqsArrayList,titleArrayList, descriptionArrayList);
                        } else {
                            Toast.makeText(getContext(), "Faqs Not Available", Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<FaqsResponse> call, Throwable t) {
                Log.e("tag::>>>>", "Faqs", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_LONG).show();            }
        });
    }


    private void filter(String text) {

        ArrayList<FaqsResponse.Faq.Title> filteredlist = new ArrayList<>();
        for (FaqsResponse.Faq.Title item : titleArrayList) {
            rv_faqs.setVisibility(View.VISIBLE);
            if (item.getEn().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
                Log.d("faqs:>>", String.valueOf(filteredlist.size()));
            }
        }
        if (filteredlist.isEmpty()) {
            rv_faqs.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "No Match Found", Toast.LENGTH_SHORT).show();
        } else {
            faqsAdapter.filterList(filteredlist);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("faqs::>>", MainActivity.currentFragment = "faqs");
        ((MainActivity) getActivity()).getFragmentTag(7);
    }

}