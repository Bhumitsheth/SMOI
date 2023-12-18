package com.iipl.smoi.Screens.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iipl.smoi.R;

public class AboutUs extends Fragment {

    ImageView img_toolbar_back;
    TextView tv_title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_about_us, container, false);
//        showActionBar();


        img_toolbar_back=view.findViewById(R.id.img_toolbar_back);
        tv_title=view.findViewById(R.id.tv_toolbar_title);

        tv_title.setText(R.string.aboutus);

        img_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().onBackPressed();
                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("about us");
                if(fragment != null)
                    getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        });





        return view;
    }
    /*private void showActionBar() {
        final Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fragment_actionbar, null);
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setCustomView(v);
            toolbar.setNavigationIcon(null);

//            toolbar.setTitle("About Us");
//            toolbar.setTitleMarginEnd(650);

            v.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionBar.setDisplayShowCustomEnabled(false);
                    actionBar.setDisplayShowTitleEnabled(true);
                    DrawerLayout drawer = getActivity().findViewById(R.id.drawerlayout);
                    ActionBarDrawerToggle toggle =
                            new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.navigation_drawer_open,
                            R.string.navigation_drawer_close);
                    toggle.syncState();
                    getActivity().onBackPressed();
                }
            });



        }



//        toolbar.setTitle("ABOUT US");

    }*/


}