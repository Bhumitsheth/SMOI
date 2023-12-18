package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.ExpoListResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Screens.ActivityActions.ExpoDetailsActivity;
import com.iipl.smoi.Screens.ActivityActions.StallBookingActivity;

import org.json.JSONArray;

import java.util.ArrayList;


public class ExpoListAdapter extends RecyclerView.Adapter<ExpoListAdapter.mydataviewholder> {

    Context context;
    ArrayList<ExpoListResponse.ExpoBooking> expoBookingArrayList;
    ArrayList<ExpoListResponse.ExpoBooking.Desc> descArrayList;
    ArrayList<ExpoListResponse.ExpoBooking.Title> titleArrayList;

    ArrayList<String> stringStallNoFromArrayList;
    ArrayList<String> stringStallNoToArrayList;
    ArrayList<String> stringStallRentArrayList;
    ArrayList<String> stringGstArrayList;
    ArrayList<String> stringGstAmountArrayList;
    ArrayList<String> stringTotalArrayList;
    private OnItemClickListener onItemClickListener;

    public ExpoListAdapter(Context context, ArrayList<ExpoListResponse.ExpoBooking> expoBookingArrayList,
                           ArrayList<ExpoListResponse.ExpoBooking.Desc> descArrayList,
                           ArrayList<ExpoListResponse.ExpoBooking.Title> titleArrayList,
                           OnItemClickListener onItemClickListener) {
        this.context = context;
        this.expoBookingArrayList = expoBookingArrayList;
        this.descArrayList = descArrayList;
        this.titleArrayList = titleArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ExpoListAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_expo_list, parent, false);
        return new mydataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {
        String id = expoBookingArrayList.get(position).getId();
        String image = (String) expoBookingArrayList.get(position).getImage();
        String createdAt = (String) expoBookingArrayList.get(position).getCreatedAt();
        String titleEn = (String) titleArrayList.get(position).getEn();
        String titleHi = (String) titleArrayList.get(position).getHi();

        String expoStartdate = expoBookingArrayList.get(position).getExpoStartdate();
        String expoEnddate = expoBookingArrayList.get(position).getExpoEnddate();

        holder.tv_title_val.setText(titleEn);
        holder.tv_date_val.setText(expoStartdate +" to "+expoEnddate);

        //Load Expo Banner Image
        Glide.with(context).load(image)
                .placeholder(R.drawable.bg)
                .error(R.drawable.bg).into(holder.img_expo_banner);

        //Expo Interface Click
      /*  holder.cardview_expo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onItemClickListener.onItemClick(position);
            }
        });*/

        //Start Expo Option Menu Like : View , Details , Booking , Download.
        holder.img_expo_menu.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(context, holder.img_expo_menu);
            popupMenu.getMenuInflater().inflate(R.menu.expo_options_menu, popupMenu.getMenu());

            //Start Only Member can Stall Booking
            SharedPreferences sharedpreferences1 = context.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
            String roll_id = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);
            if (!(roll_id == null)) {
                if (roll_id.matches(AppConstant.TAG_AU)) {
                    popupMenu.getMenu().findItem(R.id.stall_Booking).setVisible(true);
                }
            }
            //End Only Member can Stall Booking



            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
//                    Toast.makeText(context, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                    int id = menuItem.getItemId();
                    switch (id) {
                        //Expo Submit Form View
                        case R.id.expo_view:
                            if (context instanceof OnItemViewExpoForm) {
                                ((OnItemViewExpoForm) context).OnItemViewExpoFormClick(position);
                            }
                            return true;

                        //Expo Details
                        case R.id.expo_details:
                            String image = (expoBookingArrayList.get(position).getImage());
                            String titleEn = titleArrayList.get(position).getEn();
                            String descEn = descArrayList.get(position).getEn();
                            String bookingDate = expoBookingArrayList.get(position).getBookingDate();
                            String contactPerson = expoBookingArrayList.get(position).getContactPerson();
                            String contactNo = expoBookingArrayList.get(position).getContactNo();
                            String address = expoBookingArrayList.get(position).getAddress();
                            String state = expoBookingArrayList.get(position).getState();
                            String city = expoBookingArrayList.get(position).getCity();
                            String pincode = expoBookingArrayList.get(position).getPincode();
                            String stallNo = expoBookingArrayList.get(position).getStallNo();

                            String availableStalls = expoBookingArrayList.get(position).getAvailableStalls();
                            String expoStartdate = expoBookingArrayList.get(position).getExpoStartdate();
                            String expoEnddate = expoBookingArrayList.get(position).getExpoEnddate();
                            String policeStationDetails = expoBookingArrayList.get(position).getPoliceStationDetails();
                            String fireStationDetails = expoBookingArrayList.get(position).getFireStationDetails();
                            String incomeTaxOfficeDetails = expoBookingArrayList.get(position).getIncomeTaxOfficeDetails();
                            String insuranceCompanyDetails = expoBookingArrayList.get(position).getInsuranceCompanyDetails();
                            String localMunicipalCorporationDetails = expoBookingArrayList.get(position).getLocalMunicipalCorporationDetails();

                            String stallNoFrom = expoBookingArrayList.get(position).getStallNoFrom();
                            String stallNoTo = expoBookingArrayList.get(position).getStallNoTo();
                            String stallRent = expoBookingArrayList.get(position).getStallRent();
                            String gst = expoBookingArrayList.get(position).getGst();
                            String gstAmount = expoBookingArrayList.get(position).getGstAmount();
                            String total = expoBookingArrayList.get(position).getTotal();
                            Log.d("stringStallNoFromArrayList::>>", String.valueOf(stringStallNoFromArrayList));

                            Bundle bundle = new Bundle();
                            bundle.putString("image", image);
                            bundle.putString("title", titleEn);
                            bundle.putString("desc", descEn);
                            bundle.putString("bookingDate", bookingDate);
                            bundle.putString("contactPerson", contactPerson);
                            bundle.putString("contactNo", contactNo);
                            bundle.putString("address", address);
                            bundle.putString("state", state);
                            bundle.putString("city", city);
                            bundle.putString("pincode", pincode);
                            bundle.putString("stallNo", stallNo);

                            bundle.putString("availableStalls", availableStalls);
                            bundle.putString("expoStartdate", expoStartdate);
                            bundle.putString("expoEnddate", expoEnddate);

                            bundle.putString("policeStationDetails", policeStationDetails);
                            bundle.putString("fireStationDetails", fireStationDetails);
                            bundle.putString("incomeTaxOfficeDetails", incomeTaxOfficeDetails);
                            bundle.putString("insuranceCompanyDetails", insuranceCompanyDetails);
                            bundle.putString("localMunicipalCorporationDetails", localMunicipalCorporationDetails);

                            bundle.putString("stallNoFrom", stallNoFrom);
                            bundle.putString("stallNoTo", stallNoTo);
                            bundle.putString("stallRent", stallRent);
                            bundle.putString("gst", gst);
                            bundle.putString("gstAmount", gstAmount);
                            bundle.putString("total", total);

                            Intent intent = new Intent(context, ExpoDetailsActivity.class);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                            return true;

                        //Stall Booking
                        case R.id.stall_Booking:
                            String expoId = (expoBookingArrayList.get(position).getId());
                            Bundle bundle1 = new Bundle();
                            bundle1.putString("expo_id", expoId);
                            Intent intent1 = new Intent(context, StallBookingActivity.class);
                            intent1.putExtras(bundle1);
                            context.startActivity(intent1);
                            return true;
                    }
                    return true;
                }
            });
            popupMenu.show();
        });
        //End Expo Option Menu Like : View , Details , Booking , Download.

    }


    @Override
    public int getItemCount() {
        return expoBookingArrayList == null ? 0 : expoBookingArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        private final TextView tv_title_val, tv_date_val;

        ImageView img_expo_banner, img_expo_menu;

        CardView cardview_expo;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);

            img_expo_banner = itemView.findViewById(R.id.img_expo_banner);

            tv_title_val = itemView.findViewById(R.id.tv_title_val);
            tv_date_val = itemView.findViewById(R.id.tv_date_val);

            cardview_expo = itemView.findViewById(R.id.cardview_expo);

            img_expo_menu = itemView.findViewById(R.id.img_expo_menu);


        }
    }


    public interface OnItemViewExpoForm {
        void OnItemViewExpoFormClick(int position);
    }

    public interface OnItemExpoDetails {
        void OnItemExpoDetailsClick(int position);
    }

    public interface OnItemExpoBooking {
        void OnItemExpoBookingClick(int position);
    }

}
