package com.iipl.smoi.Screens.NavFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Utils.LocaleHelper;
import com.iipl.smoi.R;

public class ChangeLanguage extends Fragment {

    ImageView img_back;

    RadioGroup radio_group_lang;

    RadioButton radio_btn_english, radio_btn_hindi;

    Button btn_save;


    Context context;
    Resources resources;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_language, container, false);


        img_back = view.findViewById(R.id.img_back_lang);

        radio_group_lang = view.findViewById(R.id.radio_group_lang);

        radio_btn_english = view.findViewById(R.id.radio_btn_english);
        radio_btn_hindi = view.findViewById(R.id.radio_btn_hindi);

        btn_save = view.findViewById(R.id.btn_save_cl);


        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String string_language_support = sharedpreferences.getString(AppConstant.TAG_LANGUAGE_CHANGE, "English");
        String string_language = sharedpreferences.getString(AppConstant.TAG_LANGUAGE_EN, "English");

        if (string_language_support.matches("English")) {
            radio_btn_english.setChecked(true);
        } else {
            radio_btn_hindi.setChecked(true);
        }


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_lang = radio_group_lang.getCheckedRadioButtonId();
                radio_btn_hindi = (RadioButton) view.findViewById(selected_lang);

                Log.d("selected_lang::>>", String.valueOf(selected_lang));

                if (selected_lang == R.id.radio_btn_english) {
                    context = LocaleHelper.setLocale(getActivity(), "en");
                    resources = context.getResources();

                    String str_en = String.valueOf(radio_btn_hindi.getText());

                    SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(AppConstant.TAG_LANGUAGE_CHANGE, str_en);
                    editor.putString(AppConstant.TAG_LANGUAGE_EN, "English");
                    editor.commit();

                    Toast.makeText(getActivity(), "Selected Language " + radio_btn_hindi.getText(), Toast.LENGTH_SHORT).show();

                    getActivity().onBackPressed();

                } else {
                    context = LocaleHelper.setLocale(getActivity(), "hi");
                    resources = context.getResources();
                    String str_hi = String.valueOf(radio_btn_hindi.getText());

                    SharedPreferences sharedpreferences = getActivity().getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(AppConstant.TAG_LANGUAGE_CHANGE, str_hi);
                    editor.putString(AppConstant.TAG_LANGUAGE_EN, "Hindi");
                    editor.commit();

                    resources.getString(R.string.aboutus_text);

                    Log.d("str_hi>>", str_hi);

                    Toast.makeText(getActivity(), "चयनित भाषा " + str_hi, Toast.LENGTH_SHORT).show();

                    getActivity().onBackPressed();

                }

            }
        });



       /* radio_group_lang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
                Toast.makeText(getActivity(), "Selected " + radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });*/


//        context = LocaleHelper.setLocale(getActivity(), "hi");
//        resources = context.getResources();
//        Toast.makeText(getActivity(),resources.getString(R.string.address),Toast.LENGTH_LONG).show();


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("cl::>>", MainActivity.currentFragment = "cl");
        ((MainActivity) getActivity()).getFragmentTag(6);
    }

}