package com.iipl.smoi.Screens.Fragment;

import android.os.Bundle;

import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iipl.smoi.R;


public class BlogDetails extends Fragment {

    TextView tv_toolbar_title,tv_date,tv_blogtitle,tv_orderno,tv_blogdesc,tv_like,tv_viewed;
    ImageView img_toolbar_back,img_blogdetails;

    String str_title,str_image,str_createdAt,str_desc,str_blogLike,str_blogView,str_order;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog_details, container, false);

        img_toolbar_back=view.findViewById(R.id.img_toolbar_back);

        img_blogdetails=view.findViewById(R.id.img_blogbanner);

        tv_toolbar_title=view.findViewById(R.id.tv_toolbar_title);



        tv_date=view.findViewById(R.id.tv_date);
        tv_blogtitle=view.findViewById(R.id.tv_blogtitle);
        tv_orderno=view.findViewById(R.id.tv_orderno);
        tv_blogdesc=view.findViewById(R.id.tv_blogdesc);
//        tv_like=view.findViewById(R.id.tv_like);
//        tv_viewed=view.findViewById(R.id.tv_viewed);

        tv_toolbar_title.setText(R.string.blogdetails);

        int width= getResources().getDisplayMetrics().widthPixels;
        int height= getResources().getDisplayMetrics().heightPixels;
        Log.d("screen_size::>>","W:>"+width+" H:>"+height);

        img_blogdetails.getLayoutParams().width= (int) (width);
        img_blogdetails.getLayoutParams().height= (int) (height/3);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            str_title = bundle.getString("str_title","NA");
            str_image = bundle.getString("str_image","NA");
            str_createdAt = bundle.getString("blogPublishedDate","NA");
            str_desc = bundle.getString("str_desc","NA");
            str_blogLike = bundle.getString("str_blogLike","NA");
            str_blogView = bundle.getString("str_blogView","NA");
//            str_order = bundle.getString("str_order","NA");
        }


        tv_date.setText(str_createdAt);
        tv_blogtitle.setText(str_title);
//        tv_orderno.setText(str_order);
        tv_blogdesc.setText(HtmlCompat.fromHtml(str_desc,0));
//        tv_like.setText(str_blogLike);
//        tv_viewed.setText(str_blogView);


        Glide.with(getActivity()).load(str_image).error(R.drawable.bg).into(img_blogdetails);

        img_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return  view;
    }
}