package com.iipl.smoi.Screens.ActivityActions;

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
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iipl.smoi.Adapter.GalleryListAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.DeleteGalleryResponse;
import com.iipl.smoi.Model.GalleryResponse;
import com.iipl.smoi.Model.UploadGalleryResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Utils.ItemOffsetDecoration;
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

public class GalleryListActivity extends AppCompatActivity implements GalleryListAdapter.OnItemDeleteGallery {


    boolean isClicked = true;

    private static final int PICK_IMAGE_MULTIPLE = 111;
    ArrayList<Uri> mArrayUri = new ArrayList<>();
    ArrayList<String> fileArrayList = new ArrayList<>();


    GalleryListAdapter galleryListAdapter;

    RecyclerView recyclerView;

    ImageView img_back, image_upload;

    private final ArrayList<GalleryResponse.Gallery> galleryArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_list);

        img_back = findViewById(R.id.img_back_glist);
        image_upload = findViewById(R.id.image_upload);


        recyclerView = findViewById(R.id.rv_galleryList);
        recyclerView.setLayoutManager(new GridLayoutManager(GalleryListActivity.this, 2));

        //Item Decoration
        int spanCount = 2;
        int spacing = 8;
        boolean includeEdge = true;
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(spanCount, spacing, includeEdge);
        recyclerView.addItemDecoration(itemDecoration);


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GalleryListActivity.this, MainActivity.class));
            }
        });


        SharedPreferences sharedpreferences1 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String roll_id = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);

        if (!(roll_id == null)) {
            if (roll_id.matches(AppConstant.TAG_ADMIN1) || roll_id.matches(AppConstant.TAG_ADMIN2)) {
                image_upload.setVisibility(View.VISIBLE);
            }
        }


        image_upload.setOnClickListener(view1 -> {
//            startActivity(new Intent(GalleryListActivity.this, UploadGalleryActivity.class));
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getGalleryList();

        } else {
            Toast.makeText(GalleryListActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }

    private void getGalleryList() {
        final ProgressDialog pDialog = new ProgressDialog(GalleryListActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_user_id = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String str_roll_id = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("str_user_id", str_user_id);
        requestBody.put("str_roll_id", str_roll_id);

        Log.d("requestBody::>>", String.valueOf(requestBody));

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<GalleryResponse> call = request.getGallery();
        call.enqueue(new Callback<GalleryResponse>() {
            @Override
            public void onResponse(Call<GalleryResponse> call, Response<GalleryResponse> response) {

                galleryArrayList.clear();

                if (response.isSuccessful()) {

                    Log.d("msg::>>", response.body().getMessage());

                    if (response.body().getStatus().matches("200")) {

                        List<GalleryResponse.Gallery> galleryList = response.body().getGallery();
                        if (galleryList != null && !galleryList.isEmpty() && galleryList.size() > 0) {
                            for (int i = 0; i < galleryList.size(); i++) {

                                String id = galleryList.get(i).getId();
                                String createdAt = galleryList.get(i).getCreatedAt();
                                String galleryImage = galleryList.get(i).getGalleryImage();
                                GalleryResponse.Gallery gallery = new GalleryResponse.Gallery();
                                gallery.setId(id);
                                gallery.setCreatedAt(createdAt);
                                gallery.setGalleryImage(galleryImage);
                                galleryArrayList.add(gallery);

                                Log.d("gallery_List_size::>>", String.valueOf(galleryArrayList.size()));

                            }

                            galleryListAdapter = new GalleryListAdapter(GalleryListActivity.this, galleryArrayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(int i) {
                                    String galleryId = (galleryArrayList.get(i).getId());

                                    Bundle bundle = new Bundle();
                                    bundle.putString("galleryId", galleryId);

                                    Intent intent = new Intent(GalleryListActivity.this, GalleryDetailsActivity.class);
                                    intent.putExtra("galleryId", galleryId);
                                    startActivity(intent);

                                }
                            });
                            getCallForDeleteImages();
                            recyclerView.setAdapter(galleryListAdapter);


                        } else {

                            Toast.makeText(GalleryListActivity.this, "Gallery Not Available", Toast.LENGTH_SHORT).show();

                        }

                    }

                } else {
                    Toast.makeText(GalleryListActivity.this, "Try Again Later", Toast.LENGTH_LONG).show();
                }

                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }


            @Override
            public void onFailure(Call<GalleryResponse> call, Throwable t) {
                Log.e("tag::>>>>", "galleryList", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemDeleteGalleryClick(int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GalleryListActivity.this);
        alertDialogBuilder.setMessage("Are you sure, You want to Delete Gallery Images?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int arg1) {
                        dialogInterface.dismiss();
                        final ProgressDialog pDialog = new ProgressDialog(GalleryListActivity.this, R.style.MyAlertDialogStyle);
                        pDialog.setMessage("Please wait...");
                        pDialog.setCancelable(false);
                        pDialog.show();

                        SharedPreferences sharedpreferences = GalleryListActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

                        String id = galleryArrayList.get(position).getId();

                        Map<String, String> requestBody = new HashMap<>();
                        requestBody.put("id", id);

                        Log.d("GalleryId::>>", id);

                        APIInterface request = APIClient.getClient().create(APIInterface.class);
                        Call<DeleteGalleryResponse> call = request.getDeleteGallery(str_token, requestBody);
                        call.enqueue(new Callback<DeleteGalleryResponse>() {
                            @Override
                            public void onResponse(Call<DeleteGalleryResponse> call, Response<DeleteGalleryResponse> response) {

                                if (response.isSuccessful()) {
                                    if (response.body().getStatus().matches("200")) {
                                        galleryArrayList.clear();
                                        Toast.makeText(GalleryListActivity.this, "Gallery Delete Successfully ", Toast.LENGTH_SHORT).show();
                                        getGalleryList();

                                    } else {
                                        Toast.makeText(GalleryListActivity.this, "Please Try Again Later", Toast.LENGTH_LONG).show();
                                    }
                                    if (pDialog.isShowing()) {
                                        pDialog.dismiss();
                                    }

                                }

                            }

                            @Override
                            public void onFailure(Call<DeleteGalleryResponse> call, Throwable t) {
                                Log.e("tag", "fail", t);
                                if (pDialog.isShowing()) {
                                    pDialog.dismiss();
                                }
                                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void getCallForDeleteImages() {
        galleryListAdapter.setOnItemDeleteGallery(this);
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
                    String path = RealPathUtil.getRealPath(GalleryListActivity.this, imageurl);
                    File file = new File(imageurl.getPath());
                    String image_name = file.getName();
                    fileArrayList.add(path);
                }

                if (isClicked) {
                    setUploadGallery();
                    isClicked = false;
                }
            }
        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }


    }

    private void setUploadGallery() {
        ProgressDialog pDialog = new ProgressDialog(GalleryListActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = GalleryListActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);
        String str_UserId = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);

        String str_galleryId = "0";

        final RequestBody rbGalleryId = RequestBody.create(MediaType.parse("multipart/form-file"), str_galleryId);
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
                        Toast.makeText(GalleryListActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        getGalleryList();
                    } else {
                        Toast.makeText(GalleryListActivity.this, "Please Try Again Later", Toast.LENGTH_LONG).show();
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(GalleryListActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
        getGalleryList();
    }


}