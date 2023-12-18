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
import androidx.cardview.widget.CardView;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Model.FaqsResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.OnItemClickListener;

import java.util.ArrayList;


public class FaqsAdapter extends RecyclerView.Adapter<FaqsAdapter.mydataviewholder> {

    Context context;
    ArrayList<FaqsResponse.Faq> faqsArrayList;
    ArrayList<FaqsResponse.Faq.Title> titleArrayList;
    ArrayList<FaqsResponse.Faq.Description> descriptionArrayList;
    private OnItemClickListener onItemClickListener;
    FragmentManager fragmentManager;
    public boolean isButtonPressed = false;

    public FaqsAdapter(Context context,
                       ArrayList<FaqsResponse.Faq> faqsArrayList,
                       ArrayList<FaqsResponse.Faq.Title> titleArrayList,
                       ArrayList<FaqsResponse.Faq.Description> descriptionArrayList,
                       OnItemClickListener onItemClickListener) {
        this.context = context;
        this.faqsArrayList = faqsArrayList;
        this.titleArrayList = titleArrayList;
        this.descriptionArrayList = descriptionArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    public void filterList(ArrayList<FaqsResponse.Faq.Title> filterllist) {
        titleArrayList = filterllist;
        notifyDataSetChanged();
    }

    @Override
    public FaqsAdapter.mydataviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_faqs, parent, false);
        return new mydataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mydataviewholder holder, @SuppressLint("RecyclerView") final int position) {

        String str_eng_title = (faqsArrayList.get(position).getTitleEn());

        String str_title_en = (titleArrayList.get(position).getEn());
        String str_title_hi = (titleArrayList.get(position).getHi());

        String str_desc_en = (descriptionArrayList.get(position).getEn());
        String str_desc_hi = (descriptionArrayList.get(position).getHi());

        SharedPreferences sharedpreferences = context.getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String string_language = sharedpreferences.getString(AppConstant.TAG_LANGUAGE_EN, "English");
        if (string_language.matches("English")) {
            holder.tv_title_faqs.setText(str_title_en);
            holder.tv_desc_faqs.setText(HtmlCompat.fromHtml(str_desc_en, 0));
        } else {
            holder.tv_title_faqs.setText(str_title_hi);
            holder.tv_desc_faqs.setText(HtmlCompat.fromHtml(str_desc_hi, 0));
        }


        holder.ll_faqlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);

                if (holder.tv_desc_faqs.getVisibility() == View.VISIBLE) {
                    holder.tv_desc_faqs.setVisibility(View.GONE);
                    holder.img_right_arrow.setVisibility(View.VISIBLE);
                    holder.img_down_arrow.setVisibility(View.GONE);
                } else {
                    holder.tv_desc_faqs.setVisibility(View.VISIBLE);
                    holder.img_right_arrow.setVisibility(View.GONE);
                    holder.img_down_arrow.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return faqsArrayList == null ? 0 : faqsArrayList.size();
    }

    public class mydataviewholder extends RecyclerView.ViewHolder {

        private final TextView tv_title_faqs, tv_desc_faqs;

        private final LinearLayout ll_faqlist;

        private final ImageView img_down_arrow, img_right_arrow;
        private final CardView cardview_faq;

        public mydataviewholder(@NonNull View itemView) {
            super(itemView);

            tv_title_faqs = itemView.findViewById(R.id.tv_title_faqs);
            tv_desc_faqs = itemView.findViewById(R.id.tv_desc_faqs);

            ll_faqlist = itemView.findViewById(R.id.ll_faqlist);

            cardview_faq = itemView.findViewById(R.id.cardview_faq);

            img_down_arrow = itemView.findViewById(R.id.img_down_arrow);
            img_right_arrow = itemView.findViewById(R.id.img_right_arrow);

        }

    }

    public void updateList(ArrayList<FaqsResponse.Faq> faqsArrayList,
                           ArrayList<FaqsResponse.Faq.Title> titleArrayList,
                           ArrayList<FaqsResponse.Faq.Description> descriptionArrayList) {
        this.faqsArrayList = faqsArrayList;
        this.titleArrayList = titleArrayList;
        this.descriptionArrayList = descriptionArrayList;
        notifyDataSetChanged();

    }


}
