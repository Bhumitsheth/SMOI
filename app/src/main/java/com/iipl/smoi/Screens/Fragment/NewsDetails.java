package com.iipl.smoi.Screens.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iipl.smoi.R;


public class NewsDetails extends Fragment {

    ImageView img_toolbar_back,img_newsbanner;

    TextView tv_toolbar_title,tv_newstitle,tv_newsdesc,tv_date;

    String str_title,str_image,str_createdAt,str_desc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_news_details, container, false);

        img_toolbar_back=view.findViewById(R.id.img_toolbar_back);

        img_newsbanner=view.findViewById(R.id.img_newsbanner);

        tv_toolbar_title=view.findViewById(R.id.tv_toolbar_title);

        tv_newstitle=view.findViewById(R.id.tv_newstitle);
        tv_newsdesc=view.findViewById(R.id.tv_newsdesc);
        tv_date=view.findViewById(R.id.tv_date);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            str_title = bundle.getString("str_title","NA");
            str_image = bundle.getString("str_image","NA");
            str_createdAt = bundle.getString("str_createdAt","NA");
            str_desc = bundle.getString("str_desc","NA");
        }


        tv_toolbar_title.setText(R.string.newsdetails);

        int width= getResources().getDisplayMetrics().widthPixels;
        int height= getResources().getDisplayMetrics().heightPixels;
        Log.d("screen_size::>>","W:>"+width+" H:>"+height);

        img_newsbanner.getLayoutParams().width= (int) (width);
        img_newsbanner.getLayoutParams().height= (int) (height/3);


        tv_newstitle.setText(str_title);
        tv_date.setText(str_createdAt);
        tv_newsdesc.setText(str_desc);
        Glide.with(getActivity()).load(str_image)
                .placeholder(R.drawable.bg)
                .error(R.drawable.bg)
                .into(img_newsbanner);

        img_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}