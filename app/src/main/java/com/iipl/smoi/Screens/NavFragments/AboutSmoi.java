package com.iipl.smoi.Screens.NavFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Adapter.AboutusSliderAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.AboutusResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutSmoi extends Fragment {


    boolean stop_thread = true;

    private final Handler sliderHandler = new Handler();

    ViewPager2 viewPager2;

    ImageView img_back, img_about_banner, img_about_banner2;

    ImageView img_right_arrow,img_down_arrow;
    ImageView img_right_arrow2,img_down_arrow2;
    ImageView img_right_arrow3,img_down_arrow3;
    ImageView img_right_arrow4,img_down_arrow4;

    LinearLayout ll_list,ll_list2,ll_list3,ll_list4;

    TextView tv_title_us, tv_banner_title, tv_about_desc, tv_vision_title, tv_vision_desc;

    TextView tv_title1,tv_title2,tv_title3,tv_title4;

    TextView tv_desc1,tv_desc2,tv_desc3,tv_desc4;


    ProgressBar progressbar;

    private final ArrayList<AboutusResponse.AboutSmous> aboutUsArrayList = new ArrayList<>();
    private final ArrayList<String> stringArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_smoi, container, false);


        findView(view);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getAboutUs();

        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

        return view;
    }

    private void findView(View view) {

        viewPager2 = view.findViewById(R.id.viewpager2);

//        progressbar = view.findViewById(R.id.progressbar);

        img_back = view.findViewById(R.id.img_back_abt_smoi);
        img_about_banner = view.findViewById(R.id.img_about_banner);
        img_about_banner2 = view.findViewById(R.id.img_about_banner2);

        img_down_arrow = view.findViewById(R.id.img_down_arrow);
        img_down_arrow2 = view.findViewById(R.id.img_down_arrow2);
        img_down_arrow3 = view.findViewById(R.id.img_down_arrow3);
        img_down_arrow4 = view.findViewById(R.id.img_down_arrow4);

        img_right_arrow = view.findViewById(R.id.img_right_arrow);
        img_right_arrow2 = view.findViewById(R.id.img_right_arrow2);
        img_right_arrow3 = view.findViewById(R.id.img_right_arrow3);
        img_right_arrow4 = view.findViewById(R.id.img_right_arrow4);

        tv_title_us = view.findViewById(R.id.tv_title_us);
        tv_banner_title = view.findViewById(R.id.tv_banner_title);
        tv_about_desc = view.findViewById(R.id.tv_about_desc);
        tv_vision_title = view.findViewById(R.id.tv_vision_title);
        tv_vision_desc = view.findViewById(R.id.tv_vision_desc);

        tv_title1 = view.findViewById(R.id.tv_title1);
        tv_title2 = view.findViewById(R.id.tv_title2);
        tv_title3 = view.findViewById(R.id.tv_title3);
        tv_title4 = view.findViewById(R.id.tv_title4);

        tv_desc1 = view.findViewById(R.id.tv_desc1);
        tv_desc2 = view.findViewById(R.id.tv_desc2);
        tv_desc3 = view.findViewById(R.id.tv_desc3);
        tv_desc4 = view.findViewById(R.id.tv_desc4);

        ll_list = view.findViewById(R.id.ll_list);
        ll_list2 = view.findViewById(R.id.ll_list2);
        ll_list3 = view.findViewById(R.id.ll_list3);
        ll_list4 = view.findViewById(R.id.ll_list4);

    }

    private void getAboutUs() {

        final ProgressDialog pDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<AboutusResponse> call = request.getSmoi();
        call.enqueue(new Callback<AboutusResponse>() {
            @Override
            public void onResponse(Call<AboutusResponse> call, Response<AboutusResponse> response) {

                aboutUsArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("smoi>>", response.body().getStatus());

                        List<AboutusResponse.AboutSmous> SmoiList = response.body().getAboutSmoi();
                        if (SmoiList != null && !SmoiList.isEmpty() && SmoiList.size() > 0) {
                            for (int i = 0; i < SmoiList.size(); i++) {

                                String id = SmoiList.get(i).getId();
                                String bannerImage = SmoiList.get(i).getBannerImage();
                                String bannerSubTitle = SmoiList.get(i).getBannerSubTitle();
                                String aboutTitle = SmoiList.get(i).getAboutTitle();
                                String aboutImg = SmoiList.get(i).getAboutImg();
                                String aboutDesc = SmoiList.get(i).getAboutDec();
                                String vissionMissionTitle = SmoiList.get(i).getVissionMissionTitle();
                                String vissionMissionDesc = SmoiList.get(i).getVissionMissionDec();

                                String titleOne = SmoiList.get(i).getTitleOne();
                                String titleTwo = SmoiList.get(i).getTitleTwo();
                                String titleThree = SmoiList.get(i).getTitleThree();
                                String titleFour = SmoiList.get(i).getTitleFor();

                                String descOne = SmoiList.get(i).getDecOne();
                                String descTwo = SmoiList.get(i).getTitleDecTwo();
                                String descThree = SmoiList.get(i).getTitleDecThree();
                                String descFour = SmoiList.get(i).getTitleDecFor();

                                String vissionMissionImg = SmoiList.get(i).getVissionMissionImg();
                                String vissionMissionImgTwo = SmoiList.get(i).getVissionMissionImgTwo();
                                String vissionMissionImgThree = SmoiList.get(i).getVissionMissionImgThree();

                                stringArrayList.add(vissionMissionImg);
                                stringArrayList.add(vissionMissionImgTwo);
                                stringArrayList.add(vissionMissionImgThree);

                                AboutusSliderAdapter aboutusSliderAdapter =new AboutusSliderAdapter(getActivity(),stringArrayList,viewPager2);

                                viewPager2.setAdapter(aboutusSliderAdapter);

                                viewPager2.setClipToPadding(false);
                                viewPager2.setClipChildren(false);
                                viewPager2.setOffscreenPageLimit(10);
                                viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

                                CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
                                compositePageTransformer.addTransformer(new MarginPageTransformer(80));
                                compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                                    @Override
                                    public void transformPage(@NonNull View page, float position) {
                                        float r = 1 - Math.abs(position);
                                        page.setScaleY(0.80f + r * 0.2f);
                                    }
                                });

                                viewPager2.setPageTransformer(compositePageTransformer);

                                viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                                    @Override
                                    public void onPageSelected(int position) {
                                        super.onPageSelected(position);
                                        sliderHandler.removeCallbacks(sliderRunnable);
                                        sliderHandler.postDelayed(sliderRunnable, 5000);
                                    }
                                });

                                int width= getResources().getDisplayMetrics().widthPixels;
                                int height= getResources().getDisplayMetrics().heightPixels;

                                viewPager2.getLayoutParams().width= (int) (width);
                                viewPager2.getLayoutParams().height= (int) (height/3.5);


                                tv_title_us.setPaintFlags(tv_title_us.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                                tv_title_us.setText(HtmlCompat.fromHtml(aboutTitle, 0));

                                tv_banner_title.setText(HtmlCompat.fromHtml(bannerSubTitle, 0));
                                tv_about_desc.setText(HtmlCompat.fromHtml(aboutDesc, 0));
                                tv_vision_title.setText(HtmlCompat.fromHtml(vissionMissionTitle, 0));
                                tv_vision_desc.setText(HtmlCompat.fromHtml(vissionMissionDesc, 0));

                                Glide.with(getActivity()).load(bannerImage)
                                        .placeholder(R.drawable.bg)
                                        .error(R.drawable.bg)
                                        .into(img_about_banner);
                                Glide.with(getActivity()).load(aboutImg).placeholder(R.drawable.bg).error(R.drawable.bg).into(img_about_banner2);

                                tv_title1.setText(HtmlCompat.fromHtml(titleOne, 0));
                                tv_title2.setText(HtmlCompat.fromHtml(titleTwo, 0));
                                tv_title3.setText(HtmlCompat.fromHtml(titleThree, 0));
                                tv_title4.setText(HtmlCompat.fromHtml(titleFour, 0));

                                tv_desc1.setText(HtmlCompat.fromHtml(descOne, 0));
                                tv_desc2.setText(HtmlCompat.fromHtml(descTwo, 0));
                                tv_desc3.setText(HtmlCompat.fromHtml(descThree, 0));
                                tv_desc4.setText(HtmlCompat.fromHtml(descFour, 0));

                               /* ll_list.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (tv_desc1.getVisibility()==View.VISIBLE) {
                                            tv_desc1.setVisibility(View.GONE);
                                            img_right_arrow.setVisibility(View.VISIBLE);
                                            img_down_arrow.setVisibility(View.GONE);
                                        }else {
                                            tv_desc1.setVisibility(View.VISIBLE);
                                            img_right_arrow.setVisibility(View.GONE);
                                            img_down_arrow.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });

                                ll_list2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (tv_desc2.getVisibility()==View.VISIBLE) {
                                            tv_desc2.setVisibility(View.GONE);
                                            img_right_arrow2.setVisibility(View.VISIBLE);
                                            img_down_arrow2.setVisibility(View.GONE);
                                        }else {
                                            tv_desc2.setVisibility(View.VISIBLE);
                                            img_right_arrow2.setVisibility(View.GONE);
                                            img_down_arrow2.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });

                                ll_list3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (tv_desc3.getVisibility()==View.VISIBLE) {
                                            tv_desc3.setVisibility(View.GONE);
                                            img_right_arrow3.setVisibility(View.VISIBLE);
                                            img_down_arrow3.setVisibility(View.GONE);
                                        }else {
                                            tv_desc3.setVisibility(View.VISIBLE);
                                            img_right_arrow3.setVisibility(View.GONE);
                                            img_down_arrow3.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });


                                ll_list4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (tv_desc4.getVisibility()==View.VISIBLE) {
                                            tv_desc4.setVisibility(View.GONE);
                                            img_right_arrow4.setVisibility(View.VISIBLE);
                                            img_down_arrow4.setVisibility(View.GONE);
                                        }else {
                                            tv_desc4.setVisibility(View.VISIBLE);
                                            img_right_arrow4.setVisibility(View.GONE);
                                            img_down_arrow4.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });*/



                               /* AboutusResponse.AboutSmous aboutSmous = new AboutusResponse.AboutSmous();
                                aboutSmous.setBannerImage(bannerImage);
                                aboutSmous.setBannerSubTitle(bannerSubTitle);
                                aboutSmous.setAboutTitle(aboutTitle);
                                aboutSmous.setAboutImg(aboutImg);
                                aboutSmous.setAboutDec(aboutDesc);
                                aboutSmous.setVissionMissionTitle(vissionMissionTitle);
                                aboutSmous.setVissionMissionDec(vissionMissionDesc);
                                aboutSmous.setVissionMissionImg(vissionMissionImg);
                                aboutSmous.setVissionMissionImgTwo(vissionMissionImgTwo);
                                aboutSmous.setVissionMissionImgThree(vissionMissionImgThree);
                                aboutUsArrayList.add(aboutSmous);*/

                            }

                        } else {

                            Toast.makeText(getContext(), "About SMOI Not Available", Toast.LENGTH_SHORT).show();

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
            public void onFailure(Call<AboutusResponse> call, Throwable t) {
                Log.e("tag::>>>>", "Blog", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
                // Toast.makeText(getContext()," fail",Toast.LENGTH_LONG).show();
            }
        });
    }


    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            if (stop_thread) {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1, true);
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        Log.d("smoi::>>", MainActivity.currentFragment = "smoi");
        ((MainActivity) getActivity()).getFragmentTag(10);
    }

}