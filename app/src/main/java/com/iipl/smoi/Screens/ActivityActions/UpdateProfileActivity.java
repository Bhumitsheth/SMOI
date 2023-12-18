package com.iipl.smoi.Screens.ActivityActions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.UpdateProfileResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Utils.RealPathUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity {

    String TAG_UPDATE_PROFILE_REQUEST;

    final int PICKED_IMAGE = 100;

    static String path = "";

    CircleImageView circleImageView;

    Button btn_update_profile;

    ImageView img_back;

    EditText et_first_name, et_last_name, et_email, et_mobileno, et_department, et_address, et_city, et_state, et_pincode;

    String first_name, last_name, email, mobileno, address, city, state, pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        btn_update_profile = findViewById(R.id.btn_update_profile);

        img_back = findViewById(R.id.img_back_profile);

        circleImageView = findViewById(R.id.image_upload);



       /* SharedPreferences sharedpreferences1 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String profile_image = sharedpreferences1.getString(AppConstant.TAG_PROFILE_IMAGE, "");
        if (!profile_image.isEmpty()) {
            byte[] b = Base64.decode(profile_image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            circleImageView.setImageBitmap(bitmap);
        }*/


        SharedPreferences sharedpreferences123 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String roll_id  = sharedpreferences123.getString(AppConstant.TAG_ROLL_ID, null);
        if (!(roll_id ==null)) {
            if (roll_id.matches(AppConstant.TAG_ADMIN1) || roll_id.matches(AppConstant.TAG_ADMIN2)) {
                btn_update_profile.setText(R.string.update_profile);
            }
            else {
                btn_update_profile.setText(R.string.send_request);
            }
        }


        btn_update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!path.isEmpty()) {
                    uploadImage();
                } else {
                    Toast.makeText(getApplicationContext(), "Select Profile Image", Toast.LENGTH_LONG).show();
                }
            }
        });


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewProfileActivity.class));
            }
        });


        et_first_name = findViewById(R.id.et_first_name);
        et_last_name = findViewById(R.id.et_last_name);
        et_email = findViewById(R.id.et_email);
        et_mobileno = findViewById(R.id.et_mobileno);
        et_department = findViewById(R.id.et_department);
        et_address = findViewById(R.id.et_address);
        et_city = findViewById(R.id.et_city);
        et_state = findViewById(R.id.et_state);
        et_pincode = findViewById(R.id.et_pincode);


        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        first_name = sharedpreferences.getString(AppConstant.TAG_firstName, "NA");
        last_name = sharedpreferences.getString(AppConstant.TAG_lastName, "NA");
        email = sharedpreferences.getString(AppConstant.TAG_EMAIL, "NA");
        mobileno = sharedpreferences.getString(AppConstant.TAG_mobileNo, "NA");
        address = sharedpreferences.getString(AppConstant.TAG_address, "NA");
        city = sharedpreferences.getString(AppConstant.TAG_city, "NA");
        state = sharedpreferences.getString(AppConstant.TAG_state, "NA");
        pincode = sharedpreferences.getString(AppConstant.TAG_pincode, "NA");
        String profile_image = sharedpreferences.getString(AppConstant.TAG_PROFILE_IMAGE, "NA");

        Glide.with(UpdateProfileActivity.this)
                .load(profile_image)
                .placeholder(R.drawable.bg)
                .error(R.drawable.bg)
                .into(circleImageView);

        et_first_name.setText(first_name);
        et_last_name.setText(last_name);
        et_email.setText(email);
        et_mobileno.setText(mobileno);
        et_address.setText(address);
        et_city.setText(city);
        et_state.setText(state);
        et_pincode.setText(pincode);


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            clickListeners();

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//            Snackbar snackbar = Snackbar.make(parent_view, "No Internet Connection", Snackbar.LENGTH_LONG);
//            snackbar.show();
        }


    }

    private void clickListeners() {
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Hii......", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICKED_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICKED_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            Context context = UpdateProfileActivity.this;
            path = RealPathUtil.getRealPath(context, uri);
            Bitmap bitmap = BitmapFactory.decodeFile(path);

            Log.d("path>>>", path);

            circleImageView.setImageBitmap(bitmap);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            String str_bitmap = Base64.encodeToString(b, Base64.DEFAULT);

            SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(AppConstant.TAG_PROFILE_IMAGE, str_bitmap);
            editor.commit();

        }
    }

    public void uploadImage() {
        final ProgressDialog pDialog = new ProgressDialog(UpdateProfileActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-file"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("profile_image", file.getName(), requestFile);

        Log.d("body>>>", String.valueOf(body));

        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);
        String str_user_id = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String str_user_name = sharedpreferences.getString(AppConstant.TAG_USER_NAME, null);
        String str_area = sharedpreferences.getString(AppConstant.TAG_AREA, null);
        String roll_id  = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);

        if (!(roll_id ==null)) {
            if (roll_id.matches(AppConstant.TAG_ADMIN1) || roll_id.matches(AppConstant.TAG_ADMIN2)) {
                TAG_UPDATE_PROFILE_REQUEST="1";
            }
            else {
                TAG_UPDATE_PROFILE_REQUEST="0";
            }
        }

        final RequestBody requestBodyID = RequestBody.create(MediaType.parse("multipart/form-file"), str_user_id);
        final RequestBody requestBodyuserName = RequestBody.create(MediaType.parse("multipart/form-file"), str_user_name);
        final RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("multipart/form-file"), et_email.getText().toString());
        final RequestBody requestBodyFirst_name = RequestBody.create(MediaType.parse("multipart/form-file"), et_first_name.getText().toString());
        final RequestBody requestBodylast_name = RequestBody.create(MediaType.parse("multipart/form-file"), et_last_name.getText().toString());
        final RequestBody requestBodymobileno = RequestBody.create(MediaType.parse("multipart/form-file"), et_mobileno.getText().toString());
        final RequestBody requestBodyaddress = RequestBody.create(MediaType.parse("multipart/form-file"), et_address.getText().toString());
        final RequestBody requestBodycity = RequestBody.create(MediaType.parse("multipart/form-file"), et_city.getText().toString());
        final RequestBody requestBodystate = RequestBody.create(MediaType.parse("multipart/form-file"), et_state.getText().toString());
        final RequestBody requestBodypincode = RequestBody.create(MediaType.parse("multipart/form-file"), et_pincode.getText().toString());
        final RequestBody requestBodySendRequest = RequestBody.create(MediaType.parse("multipart/form-file"), TAG_UPDATE_PROFILE_REQUEST);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<UpdateProfileResponse> call = request.getUpdateProfile(str_token, requestBodyID, requestBodyuserName, requestBodyEmail,
                requestBodyFirst_name, requestBodylast_name, requestBodymobileno, requestBodymobileno, requestBodymobileno,
                requestBodyaddress, requestBodycity, requestBodycity, requestBodystate, requestBodypincode,requestBodySendRequest, body);
        call.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("400")) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } else if (response.body().getStatus().matches("200")) {


//                        UpdateProfileResponse.Userdatum userdatum = new UpdateProfileResponse.Userdatum();
//                        String profile_image = userdatum.getProfileImage();

                        List<UpdateProfileResponse.Userdatum> userdatumList = response.body().getUserdata();
                        if (userdatumList != null && !userdatumList.isEmpty() && userdatumList.size() > 0) {
                            for (int i = 0; i < userdatumList.size(); i++) {

                                String profile_image = userdatumList.get(i).getProfileImage();

                                Log.d("profile_image>>>", String.valueOf(profile_image));

                                SharedPreferences sharedpreferences123 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedpreferences123.edit();
                                editor.putString(AppConstant.TAG_PROFILE_IMAGE, profile_image);
                                editor.commit();

                            }

                        }

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                    } else {
//                        Snackbar snackbar = Snackbar.make(parent_view, "flourishMasterList Failed", Snackbar.LENGTH_LONG);
//                        snackbar.show();
                    }

                } else {
//                    Snackbar snackbar = Snackbar.make(parent_view, "Poor Connection", Snackbar.LENGTH_LONG);
//                    snackbar.show();
                }

                setDataToPreference();
                Intent intent = new Intent(UpdateProfileActivity.this, ViewProfileActivity.class);
                startActivity(intent);

                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                Log.e("tag::>>>>", "flourishMasterList", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void setDataToPreference() {
        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(AppConstant.TAG_firstName, et_first_name.getText().toString());
        editor.putString(AppConstant.TAG_lastName, et_last_name.getText().toString());
        editor.putString(AppConstant.TAG_EMAIL, et_email.getText().toString());
        editor.putString(AppConstant.TAG_mobileNo, et_mobileno.getText().toString());
        editor.putString(AppConstant.TAG_address, et_address.getText().toString());
        editor.putString(AppConstant.TAG_city, et_city.getText().toString());
        editor.putString(AppConstant.TAG_state, et_state.getText().toString());
        editor.putString(AppConstant.TAG_pincode, et_pincode.getText().toString());
        editor.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(UpdateProfileActivity.this, ViewProfileActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }


}