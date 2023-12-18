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
import com.iipl.smoi.Model.NaturalTypeResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;

public class NaturalTypesAdapter extends RecyclerView.Adapter<NaturalTypesAdapter.mydataviewholder> {

    Context context;
    ArrayList<NaturalTypeResponse.Natural> naturalArrayList;
    private OnItemClickListener onItemClickListener;

    public NaturalTypesAdapter(Context context, ArrayList<NaturalTypeResponse.Natural> naturalArrayList,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.naturalArrayList = naturalArrayList;
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public NaturalTypesAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_natural_types, parent, false);
        return new mydataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

        String str_title = (naturalArrayList.get(position).getTitle());
        String des = (naturalArrayList.get(position).getDes());

        holder.tv_title.setText(str_title);
        holder.tv_desc.setText(HtmlCompat.fromHtml(des,0));


        Glide.with(context).load(naturalArrayList.get(position).getGalleryimage())
                .placeholder(R.drawable.bg)//.circleCrop()
                .error(R.drawable.bg)//.circleCrop()
                .into(holder.img_silktype);

        holder.ll_natural_types.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return naturalArrayList == null ? 0 : naturalArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        public final TextView tv_title,tv_desc,tv_createdtime;

        public final ImageView img_silktype;

        private final LinearLayout ll_natural_types;


        public mydataviewholder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_createdtime = itemView.findViewById(R.id.tv_createdtime);
            tv_desc = itemView.findViewById(R.id.tv_desc);
//            tv_like = itemView.findViewById(R.id.tv_like);
//            tv_viewed = itemView.findViewById(R.id.tv_viewed);

            img_silktype = itemView.findViewById(R.id.img_silktype);
            ll_natural_types = itemView.findViewById(R.id.ll_natural_types);

        }
    }


}
