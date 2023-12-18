package com.iipl.smoi.Screens.Fragment;

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
import android.widget.TextView;
import android.widget.Toast;

import com.iipl.smoi.Adapter.BlogViewAllAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.BlogResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BlogViewAll extends Fragment {

    BlogViewAllAdapter blogViewAllAdapter;

    RecyclerView rv_blogviewAll;

    ImageView img_toolbar_back;

    TextView tv_toolbar_title;

//    private View parent_view;

    private ArrayList<BlogResponse.Blog> blogResponseArrayList = new ArrayList<>();
    private ArrayList<BlogResponse.Blog.BlogTitle> titleArrayList = new ArrayList<>();
    private ArrayList<BlogResponse.Blog.BlogDescription> descriptionArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_blog_view_all, container, false);

//        parent_view = view.findViewById(android.R.id.content);

        img_toolbar_back=view.findViewById(R.id.img_toolbar_back);
        tv_toolbar_title=view.findViewById(R.id.tv_toolbar_title);

        rv_blogviewAll = view.findViewById(R.id.rv_blogviewAll);
        rv_blogviewAll.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));

        tv_toolbar_title.setText(R.string.blogs);

        img_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getBlog();

        } else { Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

        return  view;
    }

    private void getBlog() {

        final ProgressDialog pDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

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

                        Log.d("blog>>", response.body().getStatus());

                        List<BlogResponse.Blog> blogList = response.body().getBlogs();
                        if (blogList != null && !blogList.isEmpty() && blogList.size() > 0) {
                            for (int i = 0; i < blogList.size(); i++) {

                                String id = blogList.get(i).getId();
//                                String title = blogList.get(i).getBlogTitle();
                                String pic = blogList.get(i).getBlogImage();
                                String title_en = blogList.get(i).getBlogTitleEn();
                                String blogLike = blogList.get(i).getBlogLike();
                                String blogView = blogList.get(i).getBlogView();
                                String blogPublishedDate = blogList.get(i).getBlogPublishedDate();
//                                String blogDescription = blogList.get(i).getBlogDescription();
                                String order = blogList.get(i).getOrdering();

                                BlogResponse.Blog blogList1 = new BlogResponse.Blog();
                                blogList1.setId(id);
//                                blogList1.setBlogTitle(title);
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
                                Log.d("blogTitleEn::>>", blogTitleEn);
                                Log.d("blogTitleHi::>>", blogTitleHi);

                                BlogResponse.Blog.BlogTitle blogTitle1=blogList.get(i).getBlogTitle();
                                blogTitle1.setEn(blogTitleEn);
                                blogTitle1.setHi(blogTitleHi);
                                titleArrayList.add(blogTitle1);

                                BlogResponse.Blog.BlogDescription  blogDescription = blogList.get(i).getBlogDescription();
                                String blogDescriptionEn = blogDescription.getEn();
                                String blogDescriptionHi = blogDescription.getHi();
                                Log.d("blogTitleEn::>>", blogTitleEn);
                                Log.d("blogTitleHi::>>", blogTitleHi);
                                BlogResponse.Blog.BlogDescription blogDescription1 = blogList.get(i).getBlogDescription();
                                blogDescription1.setEn(blogDescriptionEn);
                                blogDescription1.setHi(blogDescriptionHi);
                                descriptionArrayList.add(blogDescription1);


                            }

                            blogViewAllAdapter = new BlogViewAllAdapter(getContext(), blogResponseArrayList,titleArrayList,descriptionArrayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                    String str_id = (blogResponseArrayList.get(position).getId());

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
                                    fragmentTransaction.replace(R.id.fragment_container, blogDetails);
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();

                                }
                            });

                            rv_blogviewAll.setAdapter(blogViewAllAdapter);



                        } else {

                            Toast.makeText(getContext(), "Blog Not Available", Toast.LENGTH_SHORT).show();

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
            public void onFailure(Call<BlogResponse> call, Throwable t) {
                Log.e("tag::>>>>", "Blog", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_LONG).show();            }
        });
    }

}