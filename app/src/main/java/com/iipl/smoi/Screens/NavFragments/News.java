package com.iipl.smoi.Screens.NavFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.iipl.smoi.Adapter.NewsAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.NewsResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Screens.Fragment.NewsDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class News extends Fragment {

    NewsAdapter newsAdapter;

    RecyclerView recyclerView;

    ImageView img_back;

    private ArrayList<NewsResponse.News> newsArrayList = new ArrayList<>();
    private ArrayList<NewsResponse.News.NewsTitleName> titleArrayList = new ArrayList<>();
    private ArrayList<NewsResponse.News.NewsDescription> descriptionArrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        img_back = view.findViewById(R.id.img_back_news);

        recyclerView = view.findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,true));

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getNews();

        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//            Snackbar snackbar = Snackbar.make(parent_view, "No Internet Connection", Snackbar.LENGTH_LONG);
//            snackbar.show();
        }



        return view;
    }

    private void getNews() {

        final ProgressDialog pDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<NewsResponse> call = request.getNews();
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                newsArrayList.clear();
                titleArrayList.clear();
                descriptionArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("faq>>", response.body().getStatus());

                        List<NewsResponse.News> newsList = response.body().getNews();
                        if (newsList != null && !newsList.isEmpty() && newsList.size() > 0) {
                            for (int i = 0; i < newsList.size(); i++) {

                                String id = newsList.get(i).getId();
                                String createdAt = newsList.get(i).getCreatedAt();
                                String galleryimage = newsList.get(i).getGalleryimage();
                                NewsResponse.News newsList1 = new NewsResponse.News();
                                newsList1.setId(id);
                                newsList1.setCreatedAt(createdAt);
                                newsList1.setGalleryimage(galleryimage);
                                newsArrayList.add(newsList1);

                                NewsResponse.News.NewsTitleName newsTitleName =newsList.get(i).getNewsTitleName();
                                String title_en = newsTitleName.getEn();
                                String title_hi = newsTitleName.getHi();
                                NewsResponse.News.NewsTitleName newsTitleName1 =new NewsResponse.News.NewsTitleName();
                                newsTitleName1.setEn(title_en);
                                newsTitleName1.setHi(title_hi);
                                titleArrayList.add(newsTitleName1);

                                Log.d("title::>", title_en+" "+title_hi);

                                NewsResponse.News.NewsDescription newsDescription = newsList.get(i).getNewsDescription();
                                String newsDescriptionEn = newsDescription.getEn();
                                String newsDescriptionHi = newsDescription.getHi();
                                NewsResponse.News.NewsDescription newsDescription1 = new NewsResponse.News.NewsDescription();
                                newsDescription1.setEn(newsDescriptionEn);
                                newsDescription1.setHi(newsDescriptionHi);
                                descriptionArrayList.add(newsDescription1);

                            }

                            newsAdapter = new NewsAdapter(getContext(), newsArrayList, titleArrayList, descriptionArrayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                    String str_image = (newsArrayList.get(position).getGalleryimage());
                                    String str_createdAt = (newsArrayList.get(position).getCreatedAt());

                                    String str_title_en = (titleArrayList.get(position).getEn());
                                    String str_title_hi = (titleArrayList.get(position).getHi());

                                    String description_en = (descriptionArrayList.get(position).getEn());
                                    String description_hi = (descriptionArrayList.get(position).getHi());

                                    Bundle bundle = new Bundle();
                                    bundle.putString("str_image", str_image);
                                    bundle.putString("str_createdAt", str_createdAt);
                                    bundle.putString("str_title", str_title_en);
                                    bundle.putString("str_desc", description_en);

                                    NewsDetails newsDetails = new NewsDetails();
                                    newsDetails.setArguments(bundle);
                                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.add(R.id.fragment_container, newsDetails);
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();
                                }
                            });

                            recyclerView.setAdapter(newsAdapter);


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
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("tag::>>>>", "Faqs", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_LONG).show();            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("News::>>", MainActivity.currentFragment = "News");
        ((MainActivity) getActivity()).getFragmentTag(3);
    }
}