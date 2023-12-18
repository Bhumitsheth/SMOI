package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.iipl.smoi.R;

import java.util.ArrayList;


public class AboutusSliderAdapter extends RecyclerView.Adapter<AboutusSliderAdapter.SliderViewHolder> {

    Context context;
    ViewPager2 viewPager2;
    ArrayList<String> stringArrayList;

    public AboutusSliderAdapter(Context context, ArrayList<String> stringArrayList, ViewPager2 viewPager2) {
        this.context = context;
        this.stringArrayList = stringArrayList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_slider, parent, false));
        View view = LayoutInflater.from(context).inflate(R.layout.layout_aboutus_slider, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String str_file = stringArrayList.get(position);
//        Log.d("about_img_slider::>>", String.valueOf(stringArrayList));
            Glide.with(context)
                    .load(str_file)
                    .placeholder(R.drawable.bg)
                    .error(R.drawable.bg)
                    .into(holder.image_slider);

        int width= context.getResources().getDisplayMetrics().widthPixels;
        int height= context.getResources().getDisplayMetrics().heightPixels;
//        Log.d("screen_size::>>","W:>"+width+" H:>"+height);

        holder.image_slider.getLayoutParams().width= (int) (width);
        holder.image_slider.getLayoutParams().height= (int) (height/3.5);

        if (position == stringArrayList.size() -2) {
            viewPager2.post(runnable);
        }
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            stringArrayList.addAll(stringArrayList);
            notifyDataSetChanged();
        }
    };

    @Override
    public int getItemCount() {
        return stringArrayList == null ? 0 : stringArrayList.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView image_slider;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            image_slider = itemView.findViewById(R.id.image_slider);
        }


    }


}

