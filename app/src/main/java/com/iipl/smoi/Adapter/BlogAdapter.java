package com.iipl.smoi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.BlogResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.mydataviewholder> {

    Context context;
    ArrayList<BlogResponse.Blog> blogArrayList;
    ArrayList<BlogResponse.Blog.BlogTitle> titleArrayList;
    ArrayList<BlogResponse.Blog.BlogDescription> descriptionArrayList;
    private OnItemClickListener onItemClickListener;
    FragmentManager fragmentManager;

    public BlogAdapter(Context context, ArrayList<BlogResponse.Blog> blogArrayList,
                       ArrayList<BlogResponse.Blog.BlogTitle>titleArrayList,
                       ArrayList<BlogResponse.Blog.BlogDescription> descriptionArrayList,
                       OnItemClickListener onItemClickListener) {
        this.context = context;
        this.blogArrayList = blogArrayList;
        this.titleArrayList = titleArrayList;
        this.descriptionArrayList = descriptionArrayList;
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public BlogAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_blog_item, parent, false);
        return new mydataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

        String str_title_en = (titleArrayList.get(position).getEn());
        String str_title_hi = (titleArrayList.get(position).getHi());

        String description_en = (descriptionArrayList.get(position).getEn());
        String description_hi = (descriptionArrayList.get(position).getHi());
//        str_title.replace("","");

//        Log.d("title>>",str_title);
        holder.tv_title.setText(str_title_en);

        Glide.with(context)
                .load(blogArrayList.get(position).getBlogImage())
                .placeholder(R.drawable.bg)
                .error(R.drawable.bg)
                .into(holder.img_blog);

        SharedPreferences sharedpreferences = context.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String string_language = sharedpreferences.getString(AppConstant.TAG_LANGUAGE_EN, "English");
        if (string_language.matches("English")){
            holder.tv_title.setText(str_title_en);
        }else {
            holder.tv_title.setText(str_title_hi);
        }

        int width= context.getResources().getDisplayMetrics().widthPixels;
        int height= context.getResources().getDisplayMetrics().heightPixels;

//        Log.d("screen_size::>>","W:>"+width+" H:>"+height);


        holder.ll_blog.getLayoutParams().width=width/2-37;
        holder.img_blog.getLayoutParams().width=width/2-37;
        holder.tv_title.getLayoutParams().width=width/2-37;

        holder.img_blog.getLayoutParams().height= (int) (height/8);

        Log.d("screen_size:w:>>", String.valueOf(width/2));


        holder.ll_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
               /* String str_id = (blogArrayList.get(position).getId());
                String str_title = (blogArrayList.get(position).getBlogTitleEn());

                Bundle bundle = new Bundle();
                bundle.putString("str_id", str_id);
                bundle.putString("str_title", str_title);
                BlogDetails blogDetails = new BlogDetails();
                blogDetails.setArguments(bundle);
                FragmentTransaction fragmentTransaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.frame_container, blogDetails);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

            }
        });

//        holder.setIsRecyclable(false);
//        holder.textView.setText(blogArrayList.get(position).getBlogTitle());

//        Glide.with(context)
//                .load(blogArrayList.get(position).getBlogImage())//.circleCrop()
//                .into(holder.imageView);
//
//        Log.e("pic>>>",blogArrayList.get(position).getBlogTitle());
//
//
//        holder.ll_main_layout.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceType")
//            @Override
//            public void onClick(View view) {
//                onItemClickListener.onItemClick(position);
//
//                String str_work_id = (blogArrayList.get(position).getId());
//                String str_mainservice_name = (blogArrayList.get(position).getBlogTitle());
//
//            /*    Bundle bundle = new Bundle();
//                bundle.putString("ac_id", str_work_id);
//                bundle.putString("ac_main_name", str_mainservice_name);
//                AcServices fragment2 = new AcServices();
//                fragment2.setArguments(bundle);
//                FragmentTransaction fragmentTransaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.add(R.id.frame_container, fragment2);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();*/
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return blogArrayList == null ? 0 : blogArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {
        public final TextView tv_title;
        public final  LinearLayout ll_blog;
        public final  ImageView img_blog;
//        public final ImageView imageView;
//        public final LinearLayout ll_main_layout;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);
            ll_blog = itemView.findViewById(R.id.ll_blog);
            tv_title = itemView.findViewById(R.id.tv_title);
            img_blog = itemView.findViewById(R.id.img_blog);
//            imageView = itemView.findViewById(R.id.sliderall);
//            ll_main_layout = itemView.findViewById(R.id.ll_main_layout);
        }
    }


}
