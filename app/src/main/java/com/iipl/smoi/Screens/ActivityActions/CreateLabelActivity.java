package com.iipl.smoi.Screens.ActivityActions;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.iipl.smoi.Adapter.ChapterAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.ChapterResponse;
import com.iipl.smoi.Model.CreateRequestLabelsResponse;
import com.iipl.smoi.Model.LabelGstResponse;
import com.iipl.smoi.Model.TypeOfLabelsResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Utils.RealPathUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateLabelActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll_chapter_list;
    private TextView tv_chapters;
    private LinearLayout ll_type_of_labels, ll_payment_method;
    private TextView tv_type_of_labels;
    private TextInputEditText et_required_labels;
    private TextInputEditText et_payable_amount;
    private TextInputEditText et_transaction_Id;
    private TextInputEditText et_payment_method;
    private TextInputEditText et_transaction_image;
    private ImageViewZoom img_transaction;
    private MaterialButton btn_create_labels;

    private RadioGroup radioGroup;
    private LinearLayout ll_transaction;

    String TAG_PAYMENT_METHOD = "";

    int TRANSACTION_IMAGE = 101;

    String transaction_image_path = "";


//    ChapterAdapter chapterAdapter;

    static String TAG_CHAPTER;

    String chapterId;

    static String TAG_TYPE_OF_LABELS;

    int TAG_UNIT_COST;

    String TAG_AMOUNT;

    ImageView img_toolbar_back;
//    TextView tv_toolbar_title;

    private final ArrayList<TypeOfLabelsResponse.TypeOfLabel> typeOfLabelArrayList = new ArrayList<>();
    private ArrayList<String> stringTypeOfLabelsArrayList = new ArrayList<>();

    private ArrayList<ChapterResponse.Chapter> chapterArrayList = new ArrayList<>();
    private ArrayList<String> stringChapterArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_label);

        findViews();

        //Payment Radio Group
        setRadioGroup();

        SharedPreferences sharedpreferences1 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String roll_id = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);
        String username = sharedpreferences1.getString(AppConstant.TAG_USER_NAME, null);
        String user_id = sharedpreferences1.getString(AppConstant.TAG_USER_ID, null);
        Log.d("UserName::>>",username +" "+user_id);
        if (!(roll_id == null)) {
            if (roll_id.matches(AppConstant.TAG_CHAPTER)||roll_id.matches(AppConstant.TAG_ADMIN1) ||roll_id.matches(AppConstant.TAG_ADMIN2)) {
                ll_chapter_list.setVisibility(View.GONE);
                et_payable_amount.setVisibility(View.GONE);
                ll_payment_method.setVisibility(View.GONE);
                chapterId = "2";
            }
        }

        ll_chapter_list.setOnClickListener(view1 -> {
            setChapter();
        });

        et_transaction_image.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, TRANSACTION_IMAGE);
        });

        img_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateLabelActivity.this.onBackPressed();
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) CreateLabelActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            //Get Chapter List
            getChapter();

            getTypeOfLabels();
            setTypeOfLabels();

            setTextWatcher();

        } else {
            Toast.makeText(CreateLabelActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }

    private void setTextWatcher() {

        et_required_labels.addTextChangedListener(new TextWatcher() {
            int c;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                c = count;
                Log.d("res>>.", s.toString());
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable s) {
                if (c > 0) {
                    int rent = Integer.parseInt(et_required_labels.getText().toString());
                    String res = String.valueOf((rent * TAG_UNIT_COST));
                    TAG_AMOUNT = res;


                    SharedPreferences sharedpreferences1 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                    String roll_id = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);
                    if (!(roll_id == null)) {
                        if (roll_id.matches(AppConstant.TAG_AU)) {
                            //call on only member login
                            getGstAmount();
                        }
                    }


//                    et_payable_amount.setText(res);

//                    getGstAmount();
                }

            }
        });


    }

    private void getGstAmount() {
        final ProgressDialog pDialog = new ProgressDialog(CreateLabelActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = CreateLabelActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_roll_id = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);
        String str_login_id = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String str_state = sharedpreferences.getString(AppConstant.TAG_state, null);

        final RequestBody rb_state = RequestBody.create(MediaType.parse("multipart/form-file"), str_state);
        final RequestBody rb_login_id = RequestBody.create(MediaType.parse("multipart/form-file"), str_login_id);
        final RequestBody rb_roll_id = RequestBody.create(MediaType.parse("multipart/form-file"), str_roll_id);
        final RequestBody rb_amount = RequestBody.create(MediaType.parse("multipart/form-file"), TAG_AMOUNT);
        final RequestBody rb_tag = RequestBody.create(MediaType.parse("multipart/form-file"), TAG_TYPE_OF_LABELS);
        final RequestBody rb_req_to = RequestBody.create(MediaType.parse("multipart/form-file"), chapterId);


        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<LabelGstResponse> call = request.getLabelGstAmount(rb_state, rb_login_id, rb_roll_id, rb_amount, rb_tag, rb_req_to);
        call.enqueue(new Callback<LabelGstResponse>() {
            @Override
            public void onResponse(Call<LabelGstResponse> call, Response<LabelGstResponse> response) {

                chapterArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatusCode().matches("200")) {

                        String cgstPer = response.body().getCgstPer();
                        String sgstPer = response.body().getSgstPer();
                        String igstPer = response.body().getIgstPer();

                        String cgst = response.body().getCgst();
                        String sgst = response.body().getSgst();
                        String igst = response.body().getIgst();

                        String taxRate = response.body().getTaxRate();
                        String payableAmount = response.body().getPayableAmount();

                        Log.d("payableAmount>>>>>>", payableAmount);

                        et_payable_amount.setText(payableAmount);

//                        if (!(igst ==null)){
//                            et_payable_amount.setText(igst);
//                        }else {
//                            et_payable_amount.setText(sgst+" "+cgst);
//                        }


                    }

                }

                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }


            @Override
            public void onFailure(Call<LabelGstResponse> call, Throwable t) {
                Log.e("tag::>>>>", "chapterList", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setChapter() {
        Dialog dialog = new Dialog(CreateLabelActivity.this);
        dialog.setContentView(R.layout.dialog_searchable_spinner);
        dialog.getWindow().setLayout(1000, 2000);
//            dialog.getWindow().setStatusBarColor(R.color.appcolor);

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            dialog.getWindow().setStatusBarColor(getResources().getColor(R.color.appcolor));
            dialog.getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }*/

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.show();
        TextView selectState = dialog.findViewById(R.id.tv_select);
        EditText editText = dialog.findViewById(R.id.edit_text);
        ListView listView = dialog.findViewById(R.id.list_view);

        selectState.setText(R.string.select_chapter);

        tv_chapters.setText(R.string.select_chapter);

        ArrayAdapter<String> adapter = new ArrayAdapter(CreateLabelActivity.this, android.R.layout.simple_list_item_1, stringChapterArrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TAG_CHAPTER = adapter.getItem(position);

                tv_chapters.setText(TAG_CHAPTER);

//                    image_filter.setVisibility(View.VISIBLE);
                Log.d("TAG_CHAPTER>>>", TAG_CHAPTER);
                Log.d("State_POS>>>", String.valueOf(position));

                for (ChapterResponse.Chapter chapter : chapterArrayList) {
                    String cid = String.valueOf(chapter.getId());
                    String sname = String.valueOf(chapter.getUsername());
//                    Log.d("Find_Chapter>>>", cid + " " + sname);
                    if (sname.equalsIgnoreCase(tv_chapters.getText().toString())) {
                        chapterId = chapter.getId();
                        Log.d("Find_Chapter>>>", cid + " " + sname);
                    }

                }
                dialog.dismiss();

            }
        });
    }

    private void getChapter() {

        final ProgressDialog pDialog = new ProgressDialog(CreateLabelActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = CreateLabelActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<ChapterResponse> call = request.getChapter();
        call.enqueue(new Callback<ChapterResponse>() {
            @Override
            public void onResponse(Call<ChapterResponse> call, Response<ChapterResponse> response) {

                chapterArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        List<ChapterResponse.Chapter> chapterList = response.body().getChapter();
                        if (chapterList != null && !chapterList.isEmpty() && chapterList.size() > 0) {
                            for (int i = 0; i < chapterList.size(); i++) {

                                String id = chapterList.get(i).getId();
                                String username = chapterList.get(i).getUsername();
                                String firstname = chapterList.get(i).getFirstName();
                                String lastName = chapterList.get(i).getLastName();
                                String email = chapterList.get(i).getEmail();
                                String profileImage = chapterList.get(i).getProfileImage();

                                ChapterResponse.Chapter chapterList1 = new ChapterResponse.Chapter();
                                chapterList1.setId(id);
                                chapterList1.setUsername(username);
                                chapterList1.setFirstName(firstname);
                                chapterList1.setLastName(lastName);
                                chapterList1.setEmail(email);
                                chapterList1.setProfileImage(profileImage);
                                chapterArrayList.add(chapterList1);

                                stringChapterArrayList.add(username);

//                                Log.d("username_chapter::>>",username);

                            }

                          /*  chapterAdapter = new ChapterAdapter(CreateLabelActivity.this, chapterArrayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                }
                            });*/

                        } else {

                            Toast.makeText(CreateLabelActivity.this, "chapterList Not Available", Toast.LENGTH_SHORT).show();

                        }

                    }

                }

                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }

            }


            @Override
            public void onFailure(Call<ChapterResponse> call, Throwable t) {
                Log.e("tag::>>>>", "chapterList", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setTypeOfLabels() {
        ll_type_of_labels.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(CreateLabelActivity.this);
            dialog.setContentView(R.layout.dialog_searchable_spinner);
            dialog.getWindow().setLayout(600, 800);
//            dialog.getWindow().setStatusBarColor(R.color.appcolor);

            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            dialog.getWindow().setStatusBarColor(getResources().getColor(R.color.appcolor));
            dialog.getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_STABLE);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.show();
            TextView selectState = dialog.findViewById(R.id.tv_select);
            EditText editText = dialog.findViewById(R.id.edit_text);
            ListView listView = dialog.findViewById(R.id.list_view);

            editText.setVisibility(View.GONE);

            selectState.setText(R.string.select_type_of_labels);

            tv_type_of_labels.setText(R.string.select_type_of_labels);

            ArrayAdapter<String> adapter = new ArrayAdapter(CreateLabelActivity.this, android.R.layout.simple_list_item_1, stringTypeOfLabelsArrayList);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    TAG_TYPE_OF_LABELS = adapter.getItem(position);

                    tv_type_of_labels.setText(TAG_TYPE_OF_LABELS);

                    Log.d("TAG_TYPE_OF_LABELS>>>", TAG_TYPE_OF_LABELS);

                    Log.d("State_POS>>>", String.valueOf(position));

                    for (TypeOfLabelsResponse.TypeOfLabel typeOfLabel : typeOfLabelArrayList) {
                        String labelId = typeOfLabel.getId();

                        String typeOfLabelval = typeOfLabel.getTypeOfLabel();
                        Log.d("FindStateId>>>", labelId + " " + typeOfLabelval);

                        if (typeOfLabel.getTypeOfLabel().equalsIgnoreCase(tv_type_of_labels.getText().toString())) {
                            String labels = typeOfLabel.getId();
                            String typeOfLabel1 = typeOfLabel.getTypeOfLabel();
                            String unitCost = typeOfLabel.getUnitCost();
                            TAG_UNIT_COST = Integer.parseInt(unitCost);

                            String s = et_required_labels.getText().toString();
                            if (!s.isEmpty()) {
                                int no = Integer.parseInt(s);
                                String res = String.valueOf(TAG_UNIT_COST * (no));
                                TAG_AMOUNT = res;

                                SharedPreferences sharedpreferences1 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                                String roll_id = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);
                                if (!(roll_id == null)) {
                                    if (roll_id.matches(AppConstant.TAG_AU)) {
                                        //call on only member login
                                        getGstAmount();
                                    }
                                }

//                                et_payable_amount.setText(res);
                            }

                            Log.d("lab typeOfLabel1>>>", typeOfLabel1);
                            Log.d("unitCost>>>", unitCost);
                        }

                    }
                    dialog.dismiss();

                }
            });


        });


    }

    private void getCreateRequestLabels() {
        final ProgressDialog pDialog = new ProgressDialog(CreateLabelActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = CreateLabelActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_roll_id = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);
        String str_login_id = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String str_state = sharedpreferences.getString(AppConstant.TAG_state, null);

        final RequestBody rb_login_id = RequestBody.create(MediaType.parse("multipart/form-file"), str_login_id);
        final RequestBody rb_roll_id = RequestBody.create(MediaType.parse("multipart/form-file"), str_roll_id);
        final RequestBody rb_tag = RequestBody.create(MediaType.parse("multipart/form-file"), TAG_TYPE_OF_LABELS);
        final RequestBody rb_required_labels = RequestBody.create(MediaType.parse("multipart/form-file"), et_required_labels.getText().toString());

        RequestBody rb_transaction_Id;
        String tr_id = et_transaction_Id.getText().toString();
        if (tr_id != null) {
            Log.d("tr_id_not_Null:>>", tr_id);
            rb_transaction_Id = RequestBody.create(MediaType.parse("multipart/form-file"), tr_id);

        } else {
            Log.d("tr_id:Null:>>", tr_id);
            rb_transaction_Id = RequestBody.create(MediaType.parse("multipart/form-file"), "");
            Log.d("tr_id::>>", String.valueOf(rb_transaction_Id));
        }


        final RequestBody rb_payment_method = RequestBody.create(MediaType.parse("multipart/form-file"), TAG_PAYMENT_METHOD);
        final RequestBody rb_payable_amount = RequestBody.create(MediaType.parse("multipart/form-file"), et_payable_amount.getText().toString());
        final RequestBody rb_request_to = RequestBody.create(MediaType.parse("multipart/form-file"), chapterId);

//        File file = new File(transaction_image_path);
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-file"), file);
//        MultipartBody.Part res_part = MultipartBody.Part.createFormData("transaction_image", file.getName(), requestFile);


        MultipartBody.Part fileToUpload = null;
        if (!transaction_image_path.isEmpty()) {
            try {
                File file = new File(transaction_image_path);
                if (file.exists()) {
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"), file);
                    fileToUpload = MultipartBody.Part.createFormData("transaction_image", file.getName(), requestBody);
                    Log.d("requestBody::>>", String.valueOf(fileToUpload));
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            RequestBody attachmentEmpty = RequestBody.create(MediaType.parse("multipart/form-file"), "");
            fileToUpload = MultipartBody.Part.createFormData("transaction_image", "", attachmentEmpty);
            Log.d("attachmentEmpty::>>", String.valueOf(fileToUpload));
        }
//        Log.d("pass_chapter::>>", String.valueOf(chapterId));
//        Log.d("part_transactions::>>", String.valueOf(fileToUpload));

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<CreateRequestLabelsResponse> call = request.getCreateRequestLabels(rb_login_id, rb_roll_id,
                rb_tag, rb_required_labels, rb_transaction_Id,
                rb_payment_method, rb_payable_amount,
                rb_request_to, fileToUpload);
        call.enqueue(new Callback<CreateRequestLabelsResponse>() {
            @Override
            public void onResponse(Call<CreateRequestLabelsResponse> call, Response<CreateRequestLabelsResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus().matches("200")) {
                        Log.d("label_creation_status::>>", response.body().getStatus());
                        Toast.makeText(CreateLabelActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreateLabelActivity.this, RequestLabelsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<CreateRequestLabelsResponse> call, Throwable t) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
            }
        });

    }

    private void getTypeOfLabels() {
        final ProgressDialog pDialog = new ProgressDialog(CreateLabelActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = CreateLabelActivity.this.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<TypeOfLabelsResponse> call = request.getTypeOfLabels();
        call.enqueue(new Callback<TypeOfLabelsResponse>() {
            @Override
            public void onResponse(Call<TypeOfLabelsResponse> call, Response<TypeOfLabelsResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("tag::>>", response.body().getStatus());

                        List<TypeOfLabelsResponse.TypeOfLabel> typeOfLabelList = response.body().getTypeOfLabel();
                        if (typeOfLabelList != null && !typeOfLabelList.isEmpty() && typeOfLabelList.size() > 0) {
                            for (int i = 0; i < typeOfLabelList.size(); i++) {

                                String id = typeOfLabelList.get(i).getId();
                                String typeOfLabel = typeOfLabelList.get(i).getTypeOfLabel();
                                String unitCost = typeOfLabelList.get(i).getUnitCost();

                                stringTypeOfLabelsArrayList.add(typeOfLabel);

                                TypeOfLabelsResponse.TypeOfLabel typeOfLabel1 = new TypeOfLabelsResponse.TypeOfLabel();
                                typeOfLabel1.setTypeOfLabel(typeOfLabel);
                                typeOfLabel1.setUnitCost(unitCost);
                                typeOfLabelArrayList.add(typeOfLabel1);

                            }

                        } else {
                            Toast.makeText(CreateLabelActivity.this, R.string.typeoflabel + " Not Available", Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<TypeOfLabelsResponse> call, Throwable t) {
                Log.e("tag::>>>>", "Faqs", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(CreateLabelActivity.this, "Something went wrong please try again", Toast.LENGTH_LONG).show();
//                Snackbar snackbar = Snackbar.make(parent_view, "Faqs Failure", Snackbar.LENGTH_LONG);
//                snackbar.show();
                // Toast.makeText(CreateLabelActivity.this,"Mission fail",Toast.LENGTH_LONG).show();
            }
        });
    }


    //Get Image From Mobile
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TRANSACTION_IMAGE && resultCode == Activity.RESULT_OK) {
            assert data != null;
            Uri uri = data.getData();
            Context context = this;
            transaction_image_path = RealPathUtil.getRealPath(context, uri);
            Log.d("label_tr_image::>>>", transaction_image_path);
            Bitmap bitmap = BitmapFactory.decodeFile(transaction_image_path);
            img_transaction.setVisibility(View.VISIBLE);
            img_transaction.setImageBitmap(bitmap);
        }
    }


    private void findViews() {
        img_toolbar_back = (ImageView) findViewById(R.id.img_back_create_label);

        ll_chapter_list = (LinearLayout) findViewById(R.id.ll_chapter_list);

        tv_chapters = (TextView) findViewById(R.id.tv_chapters);
        ll_type_of_labels = (LinearLayout) findViewById(R.id.ll_type_of_labels);
        ll_payment_method = (LinearLayout) findViewById(R.id.ll_payment_method);
        tv_type_of_labels = (TextView) findViewById(R.id.tv_type_of_labels);
        et_required_labels = (TextInputEditText) findViewById(R.id.et_required_labels);
        et_payable_amount = (TextInputEditText) findViewById(R.id.et_payable_amount);
        et_transaction_Id = (TextInputEditText) findViewById(R.id.et_transaction_id);
//        et_payment_method = (TextInputEditText) findViewById(R.id.et_payment_method);
        et_transaction_image = (TextInputEditText) findViewById(R.id.et_transaction_image);
        img_transaction = (ImageViewZoom) findViewById(R.id.img_transaction);
        btn_create_labels = (MaterialButton) findViewById(R.id.btn_create_labels);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        ll_transaction = (LinearLayout) findViewById(R.id.ll_transaction);

        btn_create_labels.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    private void setRadioGroup() {
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
            TAG_PAYMENT_METHOD = radioButton.getText().toString();
            Log.d("radioButton>>", radioButton.getText().toString());

            switch (checkedId) {
                case R.id.rb_qrcode_upi:
                    ll_transaction.setVisibility(View.VISIBLE);
                    break;
                case R.id.rb_neft:
                case R.id.rb_rtgs:
                case R.id.rb_bank_transfer:
                case R.id.rb_other:
                    ll_transaction.setVisibility(View.GONE);
                    break;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btn_create_labels) {
            // Handle clicks for btn_create_labels

            SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
            String roll_id123 = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);
            if (!(roll_id123 == null)) {
                if (roll_id123.matches(AppConstant.TAG_CHAPTER)||roll_id123.matches(AppConstant.TAG_ADMIN1) ||roll_id123.matches(AppConstant.TAG_ADMIN2)) {
                    if (tv_type_of_labels.getText().toString().equalsIgnoreCase("Select Type of Labels")) {
                        Toast.makeText(CreateLabelActivity.this, "Select Type of Labels", Toast.LENGTH_SHORT).show();
                    } else if (Objects.requireNonNull(et_required_labels.getText()).toString().isEmpty()) {
                        Toast.makeText(CreateLabelActivity.this, "Type Required Labels", Toast.LENGTH_SHORT).show();
                        et_required_labels.requestFocus();
                    } else {
                        getCreateRequestLabels();
                    }
                }
            }

            SharedPreferences sharedpreferences1 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
            String roll_id = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);
            if (!(roll_id == null)) {
                if (roll_id.matches(AppConstant.TAG_AU)) {
                    if (tv_chapters.getText().toString().equalsIgnoreCase("Select Chapter")) {
                        Toast.makeText(CreateLabelActivity.this, "Select Chapter", Toast.LENGTH_SHORT).show();
                    } else if (tv_type_of_labels.getText().toString().equalsIgnoreCase("Select Type of Labels")) {
                        Toast.makeText(CreateLabelActivity.this, "Select Type of Labels", Toast.LENGTH_SHORT).show();
                    } else if (Objects.requireNonNull(et_required_labels.getText()).toString().isEmpty()) {
                        Toast.makeText(CreateLabelActivity.this, "Type Required Labels", Toast.LENGTH_SHORT).show();
                        et_required_labels.requestFocus();
                    } else if (TAG_PAYMENT_METHOD.length() == 0) {
                        Toast.makeText(CreateLabelActivity.this, "Select Payment Method", Toast.LENGTH_SHORT).show();
                    } else {
                        getCreateRequestLabels();
                    }
                }
            }






       /*     char[] charArray = Objects.requireNonNull(et_required_labels.getText()).toString().trim().toCharArray();
            int n = charArray.length;
            char first = charArray[0];
            char last = charArray[n - 1];
            String str_last = String.valueOf(last);
//            Toast.makeText(CreateLabelActivity.this, str_last, Toast.LENGTH_SHORT).show();
            if (!str_last.matches("0")) {
                Toast.makeText(CreateLabelActivity.this, "Please Enter Round Figure Labels", Toast.LENGTH_LONG).show();
            }*/


        }
    }


}