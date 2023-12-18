package com.iipl.smoi.Screens.NavFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.iipl.smoi.Adapter.FlourishListAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.FlourishListResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlourishList extends Fragment {

    FlourishListAdapter flourishListAdapter;

    RecyclerView recyclerView;

    ImageView img_back;

    private ArrayList<FlourishListResponse.FlourishMaster> flourishMasterArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_flourish_list, container, false);

        img_back = view.findViewById(R.id.img_back_flourish);

        recyclerView=view.findViewById(R.id.rv_flourishList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getFlourishList();

        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//            Snackbar snackbar = Snackbar.make(parent_view, "No Internet Connection", Snackbar.LENGTH_LONG);
//            snackbar.show();
        }

        return view;
    }

    private void getFlourishList() {

        final ProgressDialog pDialog = new ProgressDialog(getContext(), R.style.MyAlertDialogStyle);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_user_id = sharedpreferences.getString(AppConstant.TAG_USER_ID, null);
        String str_roll_id = sharedpreferences.getString(AppConstant.TAG_ROLL_ID, null);
        String str_token = sharedpreferences.getString(AppConstant.TAG_TOKEN, null);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("str_user_id", str_user_id);
        requestBody.put("str_roll_id", str_roll_id);

        Log.d("requestBody::>>", String.valueOf(requestBody));

        APIInterface request = APIClient.getClient().create(APIInterface.class);
        Call<FlourishListResponse> call = request.getFlourishList(str_token,requestBody);
        call.enqueue(new Callback<FlourishListResponse>() {
            @Override
            public void onResponse(Call<FlourishListResponse> call, Response<FlourishListResponse> response) {

                flourishMasterArrayList.clear();

                if (response.isSuccessful()) {

                    if (response.body().getStatus().matches("200")) {

                        Log.d("Member>>", response.body().getStatus());

                        List<FlourishListResponse.FlourishMaster> flourishMasterList = response.body().getFlourishMaster();
                        if (flourishMasterList != null && !flourishMasterList.isEmpty() && flourishMasterList.size() > 0) {
                            for (int i = 0; i < flourishMasterList.size(); i++) {

                                String id = flourishMasterList.get(i).getId();
                                String topic = flourishMasterList.get(i).getTopic();
                                String eventPlace = flourishMasterList.get(i).getEventPlace();
                                String url = flourishMasterList.get(i).getUrl();
                                String address = flourishMasterList.get(i).getAddress();
                                String des = flourishMasterList.get(i).getDes();
                                String scheduleddate = flourishMasterList.get(i).getScheduleddate();


                                FlourishListResponse.FlourishMaster flourishMasterList1 =new FlourishListResponse.FlourishMaster();
                                flourishMasterList1.setTopic(topic);
                                flourishMasterList1.setEventPlace(eventPlace);
                                flourishMasterList1.setUrl(url);
                                flourishMasterList1.setAddress(address);
                                flourishMasterList1.setDes(des);
                                flourishMasterList1.setScheduleddate(scheduleddate);
                                flourishMasterArrayList.add(flourishMasterList1);

                            }

                            flourishListAdapter = new FlourishListAdapter(getContext(), flourishMasterArrayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(int i) {
                                   /* String str_id = (flourishMasterArrayList.get(i).getId());
                                    String topic = flourishMasterArrayList.get(i).getTopic();
                                    String eventPlace = flourishMasterArrayList.get(i).getEventPlace();
                                    String url = flourishMasterArrayList.get(i).getUrl();
                                    String address = flourishMasterArrayList.get(i).getAddress();
                                    String des = flourishMasterArrayList.get(i).getDes();
                                    String scheduleddate = flourishMasterArrayList.get(i).getScheduleddate();

                                    Bundle bundle = new Bundle();
                                    bundle.putString("topic", topic);
                                    bundle.putString("eventPlace", eventPlace);
                                    bundle.putString("url", url);
                                    bundle.putString("address", address);
                                    bundle.putString("des", des);
                                    bundle.putString("scheduleddate", scheduleddate);

                                    FlourishDetails flourishDetails = new FlourishDetails();
                                    flourishDetails.setArguments(bundle);
                                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, flourishDetails);
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();
*/

//                                    startActivity(new Intent(getActivity(), TempActivity.class));

                                }
                            });

                            recyclerView.setAdapter(flourishListAdapter);



                        } else {

                            Toast.makeText(getContext(), "flourishMasterList Not Available", Toast.LENGTH_SHORT).show();

                        }

                    } else {
//                        Snackbar snackbar = Snackbar.make(parent_view, "flourishMasterList Failed", Snackbar.LENGTH_LONG);
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
            public void onFailure(Call<FlourishListResponse> call, Throwable t) {
                Log.e("tag::>>>>", "flourishMasterList", t);
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

}