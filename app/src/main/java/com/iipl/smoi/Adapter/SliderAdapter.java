package com.iipl.smoi.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.AdvertiseSliderResponse;
import com.iipl.smoi.R;

import java.net.URI;
import java.util.ArrayList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    Context context;
    ViewPager2 viewPager2;
    ArrayList<AdvertiseSliderResponse.Ad> adArrayList;
    int infinite;
    int video_duration;
    private final Handler sliderHandler = new Handler();

    public SliderAdapter(Context context, ArrayList<AdvertiseSliderResponse.Ad> adArrayList, ViewPager2 viewPager2) {
        this.context = context;
        this.adArrayList = adArrayList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_slider, parent, false));
        View view = LayoutInflater.from(context).inflate(R.layout.layout_slider, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, @SuppressLint("RecyclerView") int position) {

        infinite = position;

        String str_type = adArrayList.get(position).getType();
        String str_file = adArrayList.get(position).getFile();
        holder.progressBar.setVisibility(View.VISIBLE);

//        Log.d("imagelist::>>", String.valueOf(adArrayList));

        if (str_type.matches("image")) {
            holder.progressBar.setVisibility(View.GONE);
            holder.video_slider.setVisibility(View.GONE);
            holder.image_slider.setVisibility(View.VISIBLE);
//            Log.d("image::>>", str_file);
            Glide.with(context)
                    .load(str_file)
                    .placeholder(R.drawable.bg)
                    .error(R.drawable.bg)
                    .into(holder.image_slider);
        } else if (str_type.matches("video")) {
            holder.video_slider.setVisibility(View.VISIBLE);
            holder.image_slider.setVisibility(View.GONE);
            holder.progressBar.setVisibility(View.VISIBLE);

            String link1=str_file.replace("(", "%20");
            String link=link1.replace(")", "%20");

            Uri uri = Uri.parse(str_file);

            Log.d("video::>>", link);

            holder.video_slider.setVideoURI(uri);
            holder.video_slider.requestFocus();
            holder.video_slider.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    holder.progressBar.setVisibility(View.GONE);
                    mp.setVolume(0f, 0f);
                    mp.setLooping(true);

                    video_duration = holder.video_slider.getDuration();
                    Log.d("duration::>>", String.valueOf(video_duration));
                    SharedPreferences sharedpreferences = context.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putInt(String.valueOf(AppConstant.TAG_VIDEO_DURATION), video_duration);
                    editor.commit();

                }
            });

//            MediaController mediaController = new MediaController(context);
//            mediaController.setAnchorView(holder.video_slider);
//            mediaController.setMediaPlayer(holder.video_slider);
//            holder.video_slider.setMediaController(mediaController);
            holder.video_slider.start();



        }

        int width= context.getResources().getDisplayMetrics().widthPixels;
        int height= context.getResources().getDisplayMetrics().heightPixels;
//        Log.d("screen_size::>>","W:>"+width+" H:>"+height);

//        holder.image_slider.getLayoutParams().width= (int) (width);
//        holder.image_slider.getLayoutParams().height= (int) (height/3.8);
//        holder.video_slider.getLayoutParams().width= (int) (width);
//        holder.video_slider.getLayoutParams().height= (int) (height/3.8);


        if (position == adArrayList.size() -2) {
            viewPager2.post(runnable);
        }
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            sliderHandler.postDelayed(runnable,2000);
            adArrayList.addAll(adArrayList);
            notifyDataSetChanged();
        }
    };

    @Override
    public int getItemCount() {
        return adArrayList == null ? 0 : adArrayList.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;
        private ImageView image_slider;
        private VideoView video_slider;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressbar);
            image_slider = itemView.findViewById(R.id.image_slider);
            video_slider = itemView.findViewById(R.id.video_slider);
        }


    }


}
