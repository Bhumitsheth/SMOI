package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.BlogResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;

public class BlogViewAllAdapter extends RecyclerView.Adapter<BlogViewAllAdapter.mydataviewholder> {

    Context context;
    ArrayList<BlogResponse.Blog> blogArrayList;
    ArrayList<BlogResponse.Blog.BlogTitle> titleArrayList;
    ArrayList<BlogResponse.Blog.BlogDescription> descriptionArrayList;
    private OnItemClickListener onItemClickListener;
    FragmentManager fragmentManager;

    public BlogViewAllAdapter(Context context, ArrayList<BlogResponse.Blog> blogArrayList,
                              ArrayList<BlogResponse.Blog.BlogTitle> titleArrayList,
                              ArrayList<BlogResponse.Blog.BlogDescription> descriptionArrayList,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.blogArrayList = blogArrayList;
        this.titleArrayList = titleArrayList;
        this.descriptionArrayList = descriptionArrayList;
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public BlogViewAllAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_blog_viewall, parent, false);
        return new mydataviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

        String str_title_en = (titleArrayList.get(position).getEn());
        String str_title_hi = (titleArrayList.get(position).getHi());

        String description_en = (descriptionArrayList.get(position).getEn());
        String description_hi = (descriptionArrayList.get(position).getHi());

        SharedPreferences sharedpreferences = context.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String string_language = sharedpreferences.getString(AppConstant.TAG_LANGUAGE_EN, "English");
        if (string_language.matches("English")){
            holder.tv_title1.setText(HtmlCompat.fromHtml(str_title_en,0));
            holder.tv_desc.setText(HtmlCompat.fromHtml(description_en,0));
        }else {
            holder.tv_title1.setText(HtmlCompat.fromHtml(str_title_hi,0));
            holder.tv_desc.setText(HtmlCompat.fromHtml(description_hi,0));
        }

        holder.tv_createdtime.setText(blogArrayList.get(position).getBlogPublishedDate());

        Glide.with(context).load(blogArrayList.get(position).getBlogImage())
                .placeholder(R.drawable.bg)//.circleCrop()
                .error(R.drawable.bg)//.circleCrop()
                .into(holder.img_blogdetails1);

        holder.ll_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return blogArrayList == null ? 0 : blogArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        public final TextView tv_title1,tv_desc,tv_createdtime;

        public final ImageView img_blogdetails1;

        private final LinearLayout ll_blog;


        public mydataviewholder(@NonNull View itemView) {
            super(itemView);
            tv_title1 = itemView.findViewById(R.id.tv_title1);
            tv_createdtime = itemView.findViewById(R.id.tv_createdtime);
            tv_desc = itemView.findViewById(R.id.tv_desc);
//            tv_like = itemView.findViewById(R.id.tv_like);
//            tv_viewed = itemView.findViewById(R.id.tv_viewed);

            img_blogdetails1 = itemView.findViewById(R.id.img_blogdetails1);
            ll_blog = itemView.findViewById(R.id.ll_blog);

        }

    }


}
