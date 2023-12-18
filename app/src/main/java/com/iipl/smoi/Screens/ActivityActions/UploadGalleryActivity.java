package com.iipl.smoi.Screens.ActivityActions;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.UploadGalleryResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Utils.RealPathUtil;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadGalleryActivity extends AppCompatActivity /*implements GalleryListAdapter.OnItemEditGallery*/ {

    boolean isClicked = true;

    private static final int PICK_IMAGE_MULTIPLE = 111;
    ArrayList<Uri> mArrayUri = new ArrayList<>();
    ArrayList<String> fileArrayList = new ArrayList<>();

    private String str_galleryId;

    Button btn_upload;

    TextView tv_image_no;

    ImageView img_back,image_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_gallery);

        img_back = findViewById(R.id.img_back);
        image_edit = findViewById(R.id.image_edit);
        btn_upload = findViewById(R.id.btn_upload);
        tv_image_no = findViewById(R.id.tv_image_no);

//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);

        Bundle bundle = getIntent().getExtras();
        if (!(bundle == null)) {
            str_galleryId = bundle.getString("galleryId");
        }

        /*else {
            str_galleryId = "0";
        }*/

        /*image_edit.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
        });*/

        btn_upload.setOnClickListener(view -> {
            setUploadGallery();
        });

        img_back.setOnClickListener(view -> {
            finish();
        });

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
                    String path = RealPathUtil.getRealPath(UploadGalleryActivity.this, imageurl);
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
            finish();
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }


    }

    private void setEditGallery() {

        ProgressDialog pDialog = new ProgressDialog(UploadGalleryActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = UploadGalleryActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);
        String str_UserId = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);

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
                        Toast.makeText(UploadGalleryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(UploadGalleryActivity.this, "Please Try Again Later", Toast.LENGTH_LONG).show();
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
                Log.d("AddExpoTAG_Success::>>", call.toString() + " " + t.toString());
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUploadGallery() {

        ProgressDialog pDialog = new ProgressDialog(UploadGalleryActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = UploadGalleryActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);
        String str_UserId = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);

        String str_galleryId="0";

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
                        Toast.makeText(UploadGalleryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UploadGalleryActivity.this, "Please Try Again Later", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(UploadGalleryActivity.this, GalleryListActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }


}