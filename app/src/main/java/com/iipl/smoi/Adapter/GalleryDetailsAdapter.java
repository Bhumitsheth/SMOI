package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.FaqsResponse;
import com.iipl.smoi.Model.GalleryDetailsResponse;
import com.iipl.smoi.Model.GalleryResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;


public class GalleryDetailsAdapter extends RecyclerView.Adapter<GalleryDetailsAdapter.mydataviewholder> {

    Context context;
    ArrayList<GalleryDetailsResponse.Gallery> galleryArrayList;
    ArrayList<GalleryDetailsResponse.Gallery.GalleryImage> galleryImageArrayList;
    private OnItemClickListener onItemClickListener;
    private OnItemDeleteGalleryImage onItemDeleteGalleryImage;

    public GalleryDetailsAdapter(Context context,
                                 ArrayList<GalleryDetailsResponse.Gallery> galleryArrayList,
                                 ArrayList<GalleryDetailsResponse.Gallery.GalleryImage> galleryImageArrayList,
                                 OnItemClickListener onItemClickListener) {
        this.context = context;
        this.galleryArrayList = galleryArrayList;
        this.galleryImageArrayList = galleryImageArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemDeleteGalleryImage(OnItemDeleteGalleryImage onItemDeleteGalleryImage) {
        this.onItemDeleteGalleryImage = onItemDeleteGalleryImage;
    }

    @Override
    public GalleryDetailsAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_gallery_details, parent, false);
        return new mydataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

//        Log.d("Id_adaptr>>>", String.valueOf(galleryArrayList.get(position).getId()));
        Log.d("imgUrl_adaptr>>>", galleryImageArrayList.get(position).getImgUrl());

        SharedPreferences sharedpreferences1 = context.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String roll_id  = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);

        if (!(roll_id ==null)) {
            if (roll_id.matches(AppConstant.TAG_ADMIN1)||roll_id.matches(AppConstant.TAG_ADMIN2)){
                holder.img_menu.setVisibility(View.VISIBLE);
            }
        }

        Glide.with(context)
                .load(galleryImageArrayList.get(position).getImgUrl())
                .error(R.drawable.bg)
                .placeholder(R.drawable.bg)
                .into(holder.img_gallery);


        holder.img_menu.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(context, holder.img_menu);
            popupMenu.getMenuInflater().inflate(R.menu.option_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
//                    Toast.makeText(context, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                    int id = menuItem.getItemId();
                    switch (id) {
                        case R.id.option_delete:
                            if (context instanceof GalleryDetailsAdapter.OnItemDeleteGalleryImage) {
                                ((GalleryDetailsAdapter.OnItemDeleteGalleryImage) context).onItemDeleteGalleryImageClick(position);
                            }
                            return true;
                    }
                    return true;

                }
            });
            popupMenu.show();
        });


    }


    @Override
    public int getItemCount() {
        return galleryImageArrayList == null ? 0 : galleryImageArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        private final ImageView img_gallery, img_menu;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);

            img_gallery = itemView.findViewById(R.id.img_gallery);
            img_menu = itemView.findViewById(R.id.img_menu);

        }
    }


    public interface OnItemDeleteGalleryImage {
        void onItemDeleteGalleryImageClick(int position);
    }

    public void updateList(ArrayList<GalleryDetailsResponse.Gallery> galleryArrayList,
                           ArrayList<GalleryDetailsResponse.Gallery.GalleryImage> galleryImageArrayList) {
        this.galleryArrayList = galleryArrayList;
        this.galleryImageArrayList = galleryImageArrayList;
        notifyDataSetChanged();

    }

}
