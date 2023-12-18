package com.iipl.smoi.Screens.NavFragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Paint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iipl.smoi.Adapter.BlogAdapter;
import com.iipl.smoi.Adapter.SliderAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.AdvertiseSliderResponse;
import com.iipl.smoi.Model.BlogResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Screens.Fragment.BlogDetails;
import com.iipl.smoi.Screens.Fragment.BlogViewAll;
import com.iipl.smoi.Screens.HomeGridFragments.BuySilkActivity;
import com.iipl.smoi.Screens.HomeGridFragments.SilkCare;
import com.iipl.smoi.Screens.HomeGridFragments.SilksFragment;
import com.iipl.smoi.Screens.HomeGridFragments.TestSilkActivity;
import com.iipl.smoi.Utils.LocaleHelper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {


    ProgressDialog pDialog;
    ProgressDialog pDialog1;


    private ReviewManager reviewManager;

    LinearLayout ll_faqs, ll_reviews, ll_qrscan, ll_news, ll_smoi_org, ll_testsilk,
            ll_silks, ll_silkcare, ll_buysilk;

    String city;

    boolean stop_thread = true;

//    boolean is_get_location = false;

    ViewPager2 viewPager2;

    private final Handler sliderHandler = new Handler();

    int TAG_IMAGE_DURATION = 5000;

    int TAG_VIDEO_POSITION;

    boolean connected = false;

    FusedLocationProviderClient client;

    TextView tv_home_title, tv_aboutus, tv_text_readmore, tv_recentblog, tv_home_city, btn_viewall;

    private BlogAdapter blogAdapter;

    private SliderAdapter sliderAdapter;

    Button btn_readmore;

    ImageView img_home_drawer, img_notification;

    RecyclerView rv_blog;

    Context context;

    Resources resources;

    private final ArrayList<BlogResponse.Blog> blogResponseArrayList = new ArrayList<>();
    private final ArrayList<BlogResponse.Blog.BlogTitle> titleArrayList = new ArrayList<>();
    private final ArrayList<BlogResponse.Blog.BlogDescription> descriptionArrayList = new ArrayList<>();
    private final ArrayList<AdvertiseSliderResponse.Ad> adArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        //FindViews
        getFindViews(view);


        //Start of Grid Clicks

        //Notification
        getNotifications(view);

        //Buy Silk
        getBuySilk(view);

        //Silk Care
        getSilkCare(view);

        //Faqs
        getFaqs(view);

        //Reviews on Google Play Store
        getReviews(view);

        //Qr-Scan
        getQrScan(view);

        //News
        getNews(view);

        //ABOUT SMOI
        getAboutsmoi(view);

        //Test Silk
        getTestSilk(view);

        //Silk Fabric
        getSilkFabric(view);

        //End of Grid Clicks



//        int width = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels;
//        Log.d("screen_size::>>", "W:>" + width + " H:>" + height);

//        viewPager2.getLayoutParams().width= (int) (width);
//        viewPager2.getLayoutParams().height= (int) (height/3.8);

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String string_language = sharedpreferences.getString(AppConstant.TAG_LANGUAGE_EN, "English");
        Log.d("string_language>>", string_language);
        if (string_language.matches("English")) {
            context = LocaleHelper.setLocale(getActivity(), "en");
            resources = context.getResources();
            btn_readmore.setText(resources.getString(R.string.readmore));

//            tv_home_title.setText(resources.getString(R.string.home));
            tv_aboutus.setText(resources.getString(R.string.aboutus));
            tv_text_readmore.setText(resources.getString(R.string.aboutus_text));
            tv_recentblog.setText(resources.getString(R.string.recentblog));
            btn_viewall.setText(resources.getString(R.string.viewall));

        } else {
            context = LocaleHelper.setLocale(getActivity(), "hi");
            resources = context.getResources();
            btn_readmore.setText(resources.getString(R.string.readmore));
//            tv_home_title.setText(resources.getString(R.string.home));
            tv_aboutus.setText(resources.getString(R.string.aboutus));
            tv_text_readmore.setText(resources.getString(R.string.aboutus_text));
            tv_recentblog.setText(resources.getString(R.string.recentblog));
            btn_viewall.setText(resources.getString(R.string.viewall));
        }

        img_home_drawer.setOnClickListener(v -> ((MainActivity) getActivity()).openDrawer());

        btn_readmore.setOnClickListener(v -> {  
            AboutSmoi aboutSmoi = new AboutSmoi();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, aboutSmoi, "aboutSmoi");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        btn_viewall.setOnClickListener(v -> {
            BlogViewAll blogViewAll = new BlogViewAll();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, blogViewAll);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            connected = true;
//            Log.d("Internet::>>", String.valueOf(connected));

            getAdvertise();

            getBlog();

            getPermission();

        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }


        /*Bundle bundle = new Bundle();
        bundle.putString("ac_id", str_work_id);
        bundle.putString("ac_main_name", "");
        AcServices fragment2 = new AcServices();
        fragment2.setArguments(bundle);
        FragmentTransaction fragmentTransaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/


        return view;

    }

    private void getFindViews(View view) {
        img_home_drawer = view.findViewById(R.id.img_home_drawer);

        viewPager2 = view.findViewById(R.id.viewpager2);

        tv_home_title = view.findViewById(R.id.tv_home_title);
        tv_aboutus = view.findViewById(R.id.tv_aboutus);

        btn_readmore = view.findViewById(R.id.btn_readmore);
        tv_text_readmore = view.findViewById(R.id.tv_text_readmore);

        btn_viewall = view.findViewById(R.id.btn_viewall);

        tv_recentblog = view.findViewById(R.id.tv_recentblog);

        tv_home_city = view.findViewById(R.id.tv_home_city);

        rv_blog = view.findViewById(R.id.rv_blog);

        rv_blog.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        client = LocationServices.getFusedLocationProviderClient(getActivity());

        btn_viewall.setPaintFlags(btn_viewall.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    private void getSilkFabric(View view) {
        ll_silks = view.findViewById(R.id.ll_silks);
        ll_silks.setOnClickListener(view1 -> {
            SilksFragment silksFragment = new SilksFragment();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, silksFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }

    private void getTestSilk(View view) {
        ll_testsilk = view.findViewById(R.id.ll_testsilk);
        ll_testsilk.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), TestSilkActivity.class);
            startActivity(intent);
        });
    }

    private void getAboutsmoi(View view) {
        ll_smoi_org = view.findViewById(R.id.ll_smoi_org);
        ll_smoi_org.setOnClickListener(view1 -> {
            AboutSmoi aboutSmoi = new AboutSmoi();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, aboutSmoi);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }

    private void getNews(View view) {
        ll_news = view.findViewById(R.id.ll_news);
        ll_news.setOnClickListener(view1 -> {
            News news = new News();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, news);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }

    private void getQrScan(View view) {
        ll_qrscan = view.findViewById(R.id.ll_qrscan);
        ll_qrscan.setOnClickListener(view1 -> {
            QrScanner qrScanner = new QrScanner();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, qrScanner);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }

    private void getReviews(View view) {
        ll_reviews = view.findViewById(R.id.ll_reviews);
        reviewManager = ReviewManagerFactory.create(getActivity());
        ll_reviews.setOnClickListener(view1 -> {
            Task<ReviewInfo> request = reviewManager.requestReviewFlow();
            request.addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    ReviewInfo reviewInfo = task.getResult();
                    Task<Void> flow = reviewManager.launchReviewFlow(getActivity(), reviewInfo);
                    flow.addOnCompleteListener(task1 -> {
                    });
                }
            });
        });

    }

    private void getFaqs(View view) {
        ll_faqs = view.findViewById(R.id.ll_faqs);
        ll_faqs.setOnClickListener(view1 -> {
            Faqs faqs = new Faqs();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, faqs);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }

    private void getSilkCare(View view) {
        ll_silkcare = view.findViewById(R.id.ll_silkcare);
        ll_silkcare.setOnClickListener(view1 -> {
            SilkCare silkCare = new SilkCare();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, silkCare);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }

    private void getBuySilk(View view) {
        ll_buysilk = view.findViewById(R.id.ll_buysilk);
        ll_buysilk.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), BuySilkActivity.class);
            startActivity(intent);
        });

    }

    private void getNotifications(View view) {
        img_notification = view.findViewById(R.id.img_notification);
        img_notification.setOnClickListener(view1 -> {
            Notifications notifications = new Notifications();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, notifications);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        ) {
            getCurrentLocation();
        } else {
            getActivity().requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    },
                    100);
        }

    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull com.google.android.gms.tasks.Task<Location> task) {
                    Location location = task.getResult();
                    Log.d("location>>", String.valueOf(location));
                    if (location != null) {
                        Geocoder geocoder;
                        List<Address> addresses = null;
                        geocoder = new Geocoder(getActivity(), Locale.getDefault());
//                        geocoder = new Geocoder(getActivity());
                        Double latitude = location.getLatitude();
                        Double longitude = location.getLongitude();

//                        Log.d("latitude>>", String.valueOf(latitude));
//                        Log.d("longitude>>", String.valueOf(longitude));

                        try {
                            addresses = geocoder.getFromLocation(latitude, longitude, 1);
                            String address = addresses.get(0).getAddressLine(0);
//                            Log.d("address::>>", String.valueOf(address));

                            if (!addresses.isEmpty()) {
                                city = addresses.get(0).getLocality();
                                Log.d("city::>>", city);
                                tv_home_city.setText(city);
                            }
//                            sendData(city);//To Main Activity
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Log.d("no_Loc::>", "No Get Loc");

                     /*   LocationRequest locationRequest
                                = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);

                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void
                            onLocationResult(LocationResult locationResult) {
                                Location location1 = locationResult.getLastLocation();

                                Geocoder geocoder;
                                List<Address> addresses = null;
                                geocoder = new Geocoder(getActivity(), Locale.getDefault());
//                                geocoder = new Geocoder(getActivity());

                                Double latitude = location1.getLatitude();
                                Double longitude = location1.getLongitude();

                                Log.d("latitude>2>", String.valueOf(latitude));
                                Log.d("longitude>2>", String.valueOf(longitude));

                                try {
                                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
//                                String address = addresses.get(0).getAddressLine(0);

                                *//*if (is_get_location) {
                                    String city = addresses.get(0).getLocality();
                                    tv_home_city.setText(city);
                                    is_get_location = false;
                                    Log.d("city>2>", city);
                                }*//*

                            }
                        };


                        client.requestLocationUpdates(
                                locationRequest,
                                locationCallback,
                                Looper.myLooper());
*/

                    }
                }
            });
        } else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    private void getAdvertise() {
        pDialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<AdvertiseSliderResponse> call = request.getAdvertiseSlider();
        call.enqueue(new Callback<AdvertiseSliderResponse>() {
            @Override
            public void onResponse(Call<AdvertiseSliderResponse> call, Response<AdvertiseSliderResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

//                        Log.d("Ad>>", response.body().getStatus());

                        List<AdvertiseSliderResponse.Ad> adList = response.body().getAds();
                        if (adList != null && !adList.isEmpty() && adList.size() > 0) {
                            for (int i = 0; i < adList.size(); i++) {

                                String str_type = adList.get(i).getType();
                                String str_file = adList.get(i).getFile();

                                AdvertiseSliderResponse.Ad ad = new AdvertiseSliderResponse.Ad();
                                ad.setType(str_type);
                                ad.setFile(str_file);
                                adArrayList.add(ad);

//                                Log.d("AdList>>", String.valueOf(adArrayList.get(i).getType()));

                                if (str_type.matches("video")) {
//                                    Log.d("video_NO>>", String.valueOf(i));
                                    TAG_VIDEO_POSITION = i;
                                }

                            }


                            sliderAdapter = new SliderAdapter(getContext(), adArrayList, viewPager2);
                            viewPager2.setAdapter(sliderAdapter);
                            viewPager2.setClipToPadding(false);
                            viewPager2.setClipChildren(false);
                            viewPager2.setOffscreenPageLimit(20);
                            viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

                            CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
                            compositePageTransformer.addTransformer(new MarginPageTransformer(20));
                            compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                                @Override
                                public void transformPage(@NonNull View page, float position) {
                                    float r = 1 - Math.abs(position);
                                    page.setScaleY(0.85f + r * 0.15f);
                                }
                            });

                            viewPager2.setPageTransformer(compositePageTransformer);

                            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                                @Override
                                public void onPageSelected(int position) {
                                    super.onPageSelected(position);
                                    sliderHandler.removeCallbacks(sliderRunnable);
//                                    Log.e("Selected_Page::>>", String.valueOf(position));

                                    sliderHandler.postDelayed(sliderRunnable, TAG_IMAGE_DURATION);

                                 /*   SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                                    int video_duration = sharedpreferences.getInt(String.valueOf(AppConstant.TAG_VIDEO_DURATION), TAG_IMAGE_DURATION);

//                                    Log.d("video_duration", String.valueOf(video_duration));

                                    if (TAG_VIDEO_POSITION == position) {
                                        sliderHandler.postDelayed(sliderRunnable, video_duration);
                                    } else {
                                        sliderHandler.postDelayed(sliderRunnable, TAG_IMAGE_DURATION);
                                    }*/

                                }
                            });

                        } else {
                            Toast.makeText(getContext(), "Advertise Not Available", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

//                if (pDialog.isShowing()) {pDialog.dismiss();}

                if ((pDialog != null) && pDialog.isShowing()) pDialog.dismiss();

//                pDialog = null;
            }

            @Override
            public void onFailure(Call<AdvertiseSliderResponse> call, Throwable t) {
//                Log.e("tag::>>>>", "Blog", t);
                // Toast.makeText(getContext()," fail",Toast.LENGTH_LONG).show();
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getBlog() {

        pDialog1 = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
        pDialog1.setMessage("Please wait...");
        pDialog1.setCancelable(false);
        pDialog1.show();

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<BlogResponse> call = request.getBlog();
        call.enqueue(new Callback<BlogResponse>() {
            @Override
            public void onResponse(Call<BlogResponse> call, Response<BlogResponse> response) {

                blogResponseArrayList.clear();
                titleArrayList.clear();
                descriptionArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

//                        Log.d("blog>>", response.body().getStatus());

                        List<BlogResponse.Blog> blogList = response.body().getBlogs();
                        if (blogList != null && !blogList.isEmpty() && blogList.size() > 0) {
                            for (int i = 0; i < blogList.size(); i++) {

                                String id = blogList.get(i).getId();
                                String pic = blogList.get(i).getBlogImage();
                                String title_en = blogList.get(i).getBlogTitleEn();
                                String blogLike = blogList.get(i).getBlogLike();
                                String blogView = blogList.get(i).getBlogView();
                                String blogPublishedDate = blogList.get(i).getBlogPublishedDate();
//                                String blogDescription = blogList.get(i).getBlogDescription();
                                String order = blogList.get(i).getOrdering();

                                BlogResponse.Blog blogList1 = new BlogResponse.Blog();
                                blogList1.setId(id);
//                                blogList1.setBlogTitle(blogTitleEn);
                                blogList1.setBlogImage(pic);
                                blogList1.setBlogTitleEn(title_en);
                                blogList1.setBlogLike(blogLike);
                                blogList1.setBlogView(blogView);
                                blogList1.setBlogPublishedDate(blogPublishedDate);
//                                blogList1.setBlogDescription(blogDescription);
                                blogList1.setOrdering(order);
                                blogResponseArrayList.add(blogList1);

                                BlogResponse.Blog.BlogTitle blogTitle = blogList.get(i).getBlogTitle();
                                String blogTitleEn = blogTitle.getEn();
                                String blogTitleHi = blogTitle.getHi();
//                                Log.d("blogTitleEn::>>", blogTitleEn);
//                                Log.d("blogTitleHi::>>", blogTitleHi);
                                BlogResponse.Blog.BlogTitle blogTitle1 = blogList.get(i).getBlogTitle();
                                blogTitle1.setEn(blogTitleEn);
                                blogTitle1.setHi(blogTitleHi);
                                titleArrayList.add(blogTitle1);

                                BlogResponse.Blog.BlogDescription blogDescription = blogList.get(i).getBlogDescription();
                                String blogDescriptionEn = blogDescription.getEn();
                                String blogDescriptionHi = blogDescription.getHi();
//                                Log.d("blogTitleEn::>>", blogTitleEn);
//                                Log.d("blogTitleHi::>>", blogTitleHi);
                                BlogResponse.Blog.BlogDescription blogDescription1 = blogList.get(i).getBlogDescription();
                                blogDescription1.setEn(blogDescriptionEn);
                                blogDescription1.setHi(blogDescriptionHi);
                                descriptionArrayList.add(blogDescription1);

                            }

                            blogAdapter = new BlogAdapter(getContext(), blogResponseArrayList, titleArrayList, descriptionArrayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

//                                    String str_title = (blogResponseArrayList.get(position).getBlogTitleEn());
                                    String str_image = (blogResponseArrayList.get(position).getBlogImage());
                                    String blogPublishedDate = (blogResponseArrayList.get(position).getBlogPublishedDate());
//                                    String str_desc = (blogResponseArrayList.get(position).getBlogDescription());
                                    String str_blogLike = (blogResponseArrayList.get(position).getBlogLike());
                                    String str_blogView = (blogResponseArrayList.get(position).getBlogView());
                                    String str_order = (blogResponseArrayList.get(position).getOrdering());

                                    String str_title_en = (titleArrayList.get(position).getEn());
                                    String str_title_hi = (titleArrayList.get(position).getHi());

                                    String description_en = (descriptionArrayList.get(position).getEn());
                                    String description_hi = (descriptionArrayList.get(position).getHi());


                                    SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                                    String string_language = sharedpreferences.getString(AppConstant.TAG_LANGUAGE_EN, "English");
                                    Log.d("string_language>>", string_language);
                                    if (string_language.matches("English")) {
                                        context = LocaleHelper.setLocale(getActivity(), "en");
                                        resources = context.getResources();

                                        Bundle bundle = new Bundle();
                                        bundle.putString("str_title", str_title_en);
                                        bundle.putString("str_desc", description_en);
                                        bundle.putString("str_image", str_image);
                                        bundle.putString("blogPublishedDate", blogPublishedDate);
                                        bundle.putString("str_blogLike", str_blogLike);
                                        bundle.putString("str_blogView", str_blogView);
                                        bundle.putString("str_order", str_order);

                                        BlogDetails blogDetails = new BlogDetails();
                                        blogDetails.setArguments(bundle);
                                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                        fragmentTransaction.add(R.id.fragment_container, blogDetails);
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.commit();

                                    } else {
                                        context = LocaleHelper.setLocale(getActivity(), "hi");
                                        resources = context.getResources();

                                        Bundle bundle = new Bundle();
                                        bundle.putString("str_title", str_title_hi);
                                        bundle.putString("str_desc", description_hi);
                                        bundle.putString("str_image", str_image);
                                        bundle.putString("blogPublishedDate", blogPublishedDate);
                                        bundle.putString("str_blogLike", str_blogLike);
                                        bundle.putString("str_blogView", str_blogView);
                                        bundle.putString("str_order", str_order);

                                        BlogDetails blogDetails = new BlogDetails();
                                        blogDetails.setArguments(bundle);
                                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                        fragmentTransaction.add(R.id.fragment_container, blogDetails);
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.commit();

                                    }
                                }
                            });

                            tv_recentblog.setVisibility(View.VISIBLE);
                            btn_viewall.setVisibility(View.VISIBLE);

                            rv_blog.setAdapter(blogAdapter);


                        } else {
                            Toast.makeText(getContext(), "Blog Not Available", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


                if ((pDialog1 != null) && pDialog1.isShowing()) pDialog1.dismiss();


            }

            @Override
            public void onFailure(Call<BlogResponse> call, Throwable t) {
                Log.e("tag::>>>>", "Blog", t);
//                if (pDialog.isShowing()) {pDialog.dismiss();}

                if ((pDialog1 != null) && pDialog1.isShowing()) pDialog1.dismiss();

                Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
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
    public void onAttach(Context context) {
        super.onAttach(context);
       /* try {
            this.mCallback = (FragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentToActivity");
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Load_Home::>>", MainActivity.currentFragment = "Home");
        ((MainActivity) getActivity()).getFragmentTag(0);
    }

    @Override
    public void onPause() {
        super.onPause();
        if ((pDialog != null) && pDialog.isShowing())
            pDialog.dismiss();
        pDialog = null;

        if ((pDialog1 != null) && pDialog1.isShowing())
            pDialog1.dismiss();
        pDialog1 = null;

    }

    @Override
    public void onStop() {
        super.onStop();
        if ((pDialog != null) && pDialog.isShowing())
            pDialog.dismiss();
        pDialog = null;

        if ((pDialog1 != null) && pDialog1.isShowing())
            pDialog1.dismiss();
        pDialog1 = null;

    }


}