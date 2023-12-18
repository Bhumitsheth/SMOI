package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Model.NewsResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.mydataviewholder> {

    Context context;
    ArrayList<NewsResponse.News> newsArrayList;
    ArrayList<NewsResponse.News.NewsTitleName> titleArrayList;
    ArrayList<NewsResponse.News.NewsDescription> descriptionArrayList;
    private OnItemClickListener onItemClickListener;
    FragmentManager fragmentManager;
    public boolean isButtonPressed = false;

    public NewsAdapter(Context context,
                       ArrayList<NewsResponse.News> newsArrayList,
                       ArrayList<NewsResponse.News.NewsTitleName> titleArrayList,
                       ArrayList<NewsResponse.News.NewsDescription> descriptionArrayList,
                       OnItemClickListener onItemClickListener) {
        this.context = context;
        this.newsArrayList = newsArrayList;
        this.titleArrayList = titleArrayList;
        this.descriptionArrayList = descriptionArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public NewsAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_news, parent, false);
        return new mydataviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

        String str_title_en = (titleArrayList.get(position).getEn());
        String str_title_hi = (titleArrayList.get(position).getHi());

        String str_desc_en = (descriptionArrayList.get(position).getEn());
        String str_desc_hi = (descriptionArrayList.get(position).getHi());

        holder.tv_desc_news.setText(HtmlCompat.fromHtml(str_desc_en,0));


        holder.tv_title_news.setText(str_title_en);

        int width= context.getResources().getDisplayMetrics().widthPixels;
        int height= context.getResources().getDisplayMetrics().heightPixels;
        Log.d("screen_size::>>","W:>"+width+" H:>"+height);

        holder.tv_title_news.getLayoutParams().width= (int) (width/1.5);


        String str_date = (newsArrayList.get(position).getCreatedAt());
        holder.tv_date_news.setText(str_date);


        Glide.with(context).load(newsArrayList.get(position).getGalleryimage())
                .placeholder(R.drawable.bg)
                .error(R.drawable.bg)
                .into(holder.img_news);

        holder.rl_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });


        /*SharedPreferences sharedpreferences = context.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String string_language = sharedpreferences.getString(AppConstant.TAG_LANGUAGE_EN, "English");
        if (string_language.matches("English")){
            holder.tv_title_news.setText(str_title_en);
//            holder.tv_desc_faqs.setText(str_desc_en);
        }else {
            holder.tv_title_news.setText(str_title_hi);
//            holder.tv_desc_faqs.setText(str_desc_hi);
        }*/

    }

    @Override
    public int getItemCount() {
        return newsArrayList == null ? 0 : newsArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        TextView tv_title_news,tv_desc_news,tv_date_news;
        ImageView img_news;
        LinearLayout rl_news;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);
            tv_title_news=itemView.findViewById(R.id.tv_title_news);
            tv_desc_news=itemView.findViewById(R.id.tv_desc_news);
            tv_date_news=itemView.findViewById(R.id.tv_date_news);
            img_news=itemView.findViewById(R.id.img_news);
            rl_news=itemView.findViewById(R.id.rl_news);

        }
    }


}
