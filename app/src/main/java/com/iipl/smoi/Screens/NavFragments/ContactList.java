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

import com.iipl.smoi.Adapter.ContactListAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.ContactListResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Screens.Fragment.ContactDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactList extends Fragment {

    ContactListAdapter contactListAdapter;

    ImageView img_back;

    RecyclerView recyclerView;


    private ArrayList<ContactListResponse.Contact> contactArrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_contact_list, container, false);

        img_back = view.findViewById(R.id.img_back_contactlist);

        recyclerView=view.findViewById(R.id.rv_contactList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));




        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });



        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getContactList();

        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//            Snackbar snackbar = Snackbar.make(parent_view, "No Internet Connection", Snackbar.LENGTH_LONG);
//            snackbar.show();
        }


        return view;
    }

    private void getContactList() {

        final ProgressDialog pDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);
        String str_user_id = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String str_roll_id = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("login_id", str_user_id);
        requestBody.put("role_id", str_roll_id);

        Log.d("user_id::>roll_id::>", str_user_id+" "+str_roll_id);

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<ContactListResponse> call = request.getContactList(str_token,requestBody);
        call.enqueue(new Callback<ContactListResponse>() {
            @Override
            public void onResponse(Call<ContactListResponse> call, Response<ContactListResponse> response) {

                contactArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("Contact>>", response.body().getStatus());

                        List<ContactListResponse.Contact> contactList = response.body().getContact();
                        if (contactList != null && !contactList.isEmpty() && contactList.size() > 0) {
                            for (int i = 0; i < contactList.size(); i++) {

                                String id = contactList.get(i).getId();
                                String firstname = contactList.get(i).getFirstname();
                                String lastname = contactList.get(i).getLastname();
                                String email = contactList.get(i).getEmail();
                                String address = contactList.get(i).getAddress();
                                String mobileNo = contactList.get(i).getMobileNo();
                                String contactImage = contactList.get(i).getContactImage();

                                ContactListResponse.Contact contactList1 = new ContactListResponse.Contact();
                                contactList1.setFirstname(firstname);
                                contactList1.setLastname(lastname);
                                contactList1.setEmail(email);
                                contactList1.setAddress(address);
                                contactList1.setMobileNo(mobileNo);
                                contactList1.setContactImage(contactImage);
                                contactArrayList.add(contactList1);

                                Log.d("Contact2>>", String.valueOf(contactArrayList));

                            }

                            contactListAdapter = new ContactListAdapter(getContext(), contactArrayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    String str_id = (contactArrayList.get(position).getId());
                                    String str_firstname = (contactArrayList.get(position).getFirstname());
                                    String str_lastname = (contactArrayList.get(position).getLastname());
                                    String str_email = (contactArrayList.get(position).getEmail());
                                    String str_mobileno = (contactArrayList.get(position).getMobileNo());
                                    String str_address = (contactArrayList.get(position).getAddress());
                                    String contactImage = (contactArrayList.get(position).getContactImage());

                                    Bundle bundle = new Bundle();
                                    bundle.putString("str_firstname", str_firstname);
                                    bundle.putString("str_lastname", str_lastname);
                                    bundle.putString("str_email", str_email);
                                    bundle.putString("str_mobileno", str_mobileno);
                                    bundle.putString("str_address", str_address);
                                    bundle.putString("contactImage", contactImage);

                                    ContactDetails contactDetails = new ContactDetails();
                                    contactDetails.setArguments(bundle);
                                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, contactDetails);
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();


                                }
                            });

                            recyclerView.setAdapter(contactListAdapter);






                        } else {

                            Toast.makeText(getContext(), "contactList Not Available", Toast.LENGTH_SHORT).show();

                        }

                    } else {
//                        Snackbar snackbar = Snackbar.make(parent_view, "contactList Failed", Snackbar.LENGTH_LONG);
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
            public void onFailure(Call<ContactListResponse> call, Throwable t) {
                Log.e("tag::>>>>", "contactList", t);
//                Snackbar snackbar = Snackbar.make(parent_view, "contactList Failed", Snackbar.LENGTH_LONG);
//                snackbar.show();
                // Toast.makeText(getContext(),"Mission fail",Toast.LENGTH_LONG).show();
            }
        });
    }


}