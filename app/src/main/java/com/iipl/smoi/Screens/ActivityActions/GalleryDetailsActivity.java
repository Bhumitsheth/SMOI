package com.iipl.smoi.Screens.ActivityActions;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.iipl.smoi.Adapter.GalleryDetailsAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.DeleteGalleryImageResponse;
import com.iipl.smoi.Model.GalleryDetailsResponse;
import com.iipl.smoi.Model.UploadGalleryResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Utils.RealPathUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryDetailsActivity extends AppCompatActivity implements GalleryDetailsAdapter.OnItemDeleteGalleryImage {

    boolean isClicked = true;

    private static final int PICK_IMAGE_MULTIPLE = 111;
    ArrayList<Uri> mArrayUri = new ArrayList<>();
    ArrayList<String> fileArrayList = new ArrayList<>();

    String galleryId;

    GalleryDetailsAdapter galleryDetailsAdapter;

    RecyclerView recyclerView;

    ImageView img_back,image_edit;

    private final ArrayList<GalleryDetailsResponse.Gallery> galleryArrayList=new ArrayList<>();
    private final ArrayList<GalleryDetailsResponse.Gallery.GalleryImage> galleryImageArrayList =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_details);


        img_back = findViewById(R.id.img_back_glist);
        image_edit = findViewById(R.id.image_edit);


        SharedPreferences sharedpreferences1 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String roll_id  = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);

        if (!(roll_id ==null)) {
            if (roll_id.matches(AppConstant.TAG_ADMIN1)||roll_id.matches(AppConstant.TAG_ADMIN2)){
                image_edit.setVisibility(View.VISIBLE);
            }
        }

        recyclerView = findViewById(R.id.rv_galleryDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(GalleryDetailsActivity.this, LinearLayoutManager.VERTICAL, false));

        galleryDetailsAdapter = new GalleryDetailsAdapter(GalleryDetailsActivity.this, galleryArrayList,galleryImageArrayList, new OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
            }
        });
        recyclerView.setAdapter(galleryDetailsAdapter);

        Bundle bundle =getIntent().getExtras();
        if (bundle != null) {
            galleryId = bundle.getString("galleryId");
            Log.d("galleryId>>>",galleryId);
        }

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        image_edit.setOnClickListener(view -> {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);


        });


        ConnectivityManager connectivityManager = (ConnectivityManager) GalleryDetailsActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getGalleryDetailsList();

        } else {
            Toast.makeText(GalleryDetailsActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
//            Snackbar snackbar = Snackbar.make(parent_view, "No Internet Connection", Snackbar.LENGTH_LONG);
//            snackbar.show();
        }


    }


    private void getGalleryDetailsList() {
        final ProgressDialog pDialog = new ProgressDialog(GalleryDetailsActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = GalleryDetailsActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_user_id = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String str_roll_id = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("id", galleryId);

        Log.d("requestBody::>>", String.valueOf(requestBody));

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<GalleryDetailsResponse> call = request.getGalleryDetails(str_token,requestBody);
        call.enqueue(new Callback<GalleryDetailsResponse>() {
            @Override
            public void onResponse(Call<GalleryDetailsResponse> call, Response<GalleryDetailsResponse> response) {

                galleryArrayList.clear();
                galleryImageArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        List<GalleryDetailsResponse.Gallery> galleryList = response.body().getGallery();
                        if (galleryList != null && !galleryList.isEmpty() && galleryList.size() > 0) {
                            for (int i = 0; i < galleryList.size(); i++) {

                                String id = galleryList.get(i).getId();
                                String createdAt = galleryList.get(i).getCreatedAt();
                                GalleryDetailsResponse.Gallery gallery = new GalleryDetailsResponse.Gallery();
                                gallery.setId(id);
                                gallery.setCreatedAt(createdAt);
                                galleryArrayList.add(gallery);

//                                Log.d("gallery_List::>>", String.valueOf(galleryArrayList.size()));

                                List<GalleryDetailsResponse.Gallery.GalleryImage> galleryImageList = galleryList.get(i).getGalleryImage();
                                galleryImageArrayList.addAll(galleryImageList);

                                Log.d("ImageArrayList::>>", String.valueOf(galleryImageList));

                                if (galleryImageList.isEmpty()){
                                    Toast.makeText(GalleryDetailsActivity.this, "Image Not Available", Toast.LENGTH_SHORT).show();
                                }

                            }

                            galleryDetailsAdapter.updateList(galleryArrayList,galleryImageArrayList);

                            getcallback();


                        } else {
                            Toast.makeText(GalleryDetailsActivity.this, "Image Not Available", Toast.LENGTH_SHORT).show();
                        }

                    } else {
//                        Snackbar snackbar = Snackbar.make(parent_view, "galleryList Failed", Snackbar.LENGTH_LONG);
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
            public void onFailure(Call<GalleryDetailsResponse> call, Throwable t) {
                Log.e("tag::>>>>", "galleryList", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemDeleteGalleryImageClick(int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GalleryDetailsActivity.this);
        alertDialogBuilder.setMessage("Are you sure, You want to Delete Image ?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        final ProgressDialog pDialog = new ProgressDialog(GalleryDetailsActivity.this, R.style.MyAlertDialogStyle);
                        pDialog.setMessage("Please wait...");
                        pDialog.setCancelable(false);
                        pDialog.show();

                        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

                        String id = galleryArrayList.get(0).getId();
                        String imgUrl = galleryImageArrayList.get(position).getImgUrl();

                        Map<String, String> requestBody = new HashMap<>();
                        requestBody.put("id", id);
                        requestBody.put("image_url", imgUrl);

                        Log.d("Gallery_image::>>",id+"pos>"+position);

                        APIInterface request = APIClient.getClient().create(APIInterface.class);
                        Call<DeleteGalleryImageResponse> call = request.getDeleteGalleryImage(str_token,requestBody);
                        call.enqueue(new Callback<DeleteGalleryImageResponse>() {
                            @Override
                            public void onResponse(Call<DeleteGalleryImageResponse> call, Response<DeleteGalleryImageResponse> response) {

                                if (response.isSuccessful()) {
                                    if (response.body().getStatus().matches("200")) {
                                        galleryArrayList.clear();
                                        galleryImageArrayList.clear();
                                        Toast.makeText(GalleryDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                        getGalleryDetailsList();

                                    }else {
                                        Toast.makeText(GalleryDetailsActivity.this, "Please Try Again Later", Toast.LENGTH_LONG).show();
                                    }
                                    if (pDialog.isShowing()) {
                                        pDialog.dismiss();
                                    }

                                }

                            }

                            @Override
                            public void onFailure(Call<DeleteGalleryImageResponse> call, Throwable t) {
                                Log.e("tag", "fail", t);
                                if (pDialog.isShowing()) {
                                    pDialog.dismiss();
                                }
                                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void getcallback() {
        galleryDetailsAdapter.setOnItemDeleteGalleryImage(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                int cout = data.getClipData().getItemCount();
                for (int i = 0; i < cout; i++) {
                    Uri imageurl = data.getClipData().getItemAt(i).getUri();
                    mArrayUri.add(imageurl);
                    String path = RealPathUtil.getRealPath(GalleryDetailsActivity.this, imageurl);
                    File file = new File(imageurl.getPath());
                    String image_name = file.getName();
                    fileArrayList.add(path);
                }
                if (isClicked) {
                    setEditGallery();
                    isClicked = false;
                }
            }
        } else {
//            finish();
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }


    }

    private void setEditGallery() {
        ProgressDialog pDialog = new ProgressDialog(GalleryDetailsActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = GalleryDetailsActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);
        String str_UserId = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);

        final RequestBody rbGalleryId = RequestBody.create(MediaType.parse("multipart/form-file"), galleryId);
        final RequestBody rbUserId = RequestBody.create(MediaType.parse("multipart/form-file"), str_UserId);

        MultipartBody.Part[] parts = new MultipartBody.Part[fileArrayList.size()];
        Log.d("fileArrayList>>>", String.valueOf(fileArrayList.size()));

        for (int index = 0; index < fileArrayList.size(); index++) {
            File file = new File(String.valueOf(fileArrayList.get(index)));
            Log.d("file>>>", String.valueOf(file));
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"), file);
            Log.d("requestBody>>>", String.valueOf(requestBody));
            parts[index] = MultipartBody.Part.createFormData("galleryImages[]", file.getName(), requestBody);
        }

        Log.d("Parts>>>", String.valueOf(parts));
        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<UploadGalleryResponse> call = request.getUploadGalleryImage(str_token, parts, rbGalleryId, rbUserId);
        call.enqueue(new Callback<UploadGalleryResponse>() {
            @Override
            public void onResponse(Call<UploadGalleryResponse> call, Response<UploadGalleryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().matches("200")) {
                        Toast.makeText(GalleryDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        getGalleryDetailsList();
//                        finish();
                    } else {
                        Toast.makeText(GalleryDetailsActivity.this, "Please Try Again Later", Toast.LENGTH_LONG).show();
                    }
                    if (pDialog.isShowing()) {
                        pDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<UploadGalleryResponse> call, Throwable t) {
                Log.e("tag", "fail", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
//                Log.d("AddExpoTAG_Success::>>", call.toString() + " " + t.toString());
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

}