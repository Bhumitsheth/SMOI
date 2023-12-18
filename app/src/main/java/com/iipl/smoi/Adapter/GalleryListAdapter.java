package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.AvailableLabelResponse;
import com.iipl.smoi.Model.GalleryResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Screens.ActivityActions.GalleryListActivity;
import com.iipl.smoi.Screens.ActivityActions.UploadGalleryActivity;

import java.util.ArrayList;


public class GalleryListAdapter extends RecyclerView.Adapter<GalleryListAdapter.mydataviewholder> {

    Context context;
    ArrayList<GalleryResponse.Gallery> galleryArrayList;
    private OnItemClickListener onItemClickListener;
    private OnItemDeleteGallery onItemDeleteGallery;
    private OnItemEditGallery onItemEditGallery;

    public GalleryListAdapter(Context context, ArrayList<GalleryResponse.Gallery> galleryArrayList,
                              OnItemClickListener onItemClickListener) {
        this.context = context;
        this.galleryArrayList = galleryArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemDeleteGallery(OnItemDeleteGallery onItemDeleteGallery) {
        this.onItemDeleteGallery = onItemDeleteGallery;
    }

    public void setOnItemEditGallery(OnItemEditGallery onItemEditGallery) {
        this.onItemEditGallery = onItemEditGallery;
    }

    @Override
    public GalleryListAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_gallery_list, parent, false);
        return new mydataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

        SharedPreferences sharedpreferences1 = context.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String roll_id  = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);

        if (!(roll_id ==null)) {
            if (roll_id.matches(AppConstant.TAG_ADMIN1) || roll_id.matches(AppConstant.TAG_ADMIN2)) {
                holder.img_menu.setVisibility(View.VISIBLE);
            }
        }

        Glide.with(context)
                .load(galleryArrayList.get(position).getGalleryImage())
                .placeholder(R.drawable.bg)
                .error(R.drawable.bg)
                .into(holder.img_gallery);

      /*  holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemDeleteGallery.onItemDeleteGalleryClick(position);
                if (context instanceof OnItemDeleteGallery) {
                    ((OnItemDeleteGallery) context).onItemDeleteGalleryClick(position);
                }
            }
        });*/

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
                            if (context instanceof OnItemDeleteGallery) {
                                ((OnItemDeleteGallery) context).onItemDeleteGalleryClick(position);
                            }
                            return true;
                    }
                    return true;

                }
            });
            popupMenu.show();
        });

        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return galleryArrayList == null ? 0 : galleryArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        private final ImageView img_gallery, img_menu;
        private final RelativeLayout rl_layout;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);
            img_gallery = itemView.findViewById(R.id.img_gallery);
            img_menu = itemView.findViewById(R.id.img_menu);
            rl_layout = itemView.findViewById(R.id.rl_layout);
        }
    }


    public interface OnItemDeleteGallery {
        void onItemDeleteGalleryClick(int position);
    }

    public interface OnItemEditGallery {
        void onItemEditGalleryClick(int position);
    }


}
