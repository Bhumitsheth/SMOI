package com.iipl.smoi.Dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.FragmentToActivity;
import com.iipl.smoi.Screens.ActivityActions.ChapterListActivity;
import com.iipl.smoi.Screens.ActivityActions.ExpoListForStallBookingActivity;
import com.iipl.smoi.Screens.ActivityActions.GalleryListActivity;
import com.iipl.smoi.Screens.ActivityActions.LabelListActivity;
import com.iipl.smoi.Screens.ActivityActions.MemberListActivity;
import com.iipl.smoi.Screens.ActivityActions.ViewProfileActivity;
import com.iipl.smoi.Screens.Authorization.LoginActivity;
import com.iipl.smoi.Screens.NavFragments.AboutSmoi;
import com.iipl.smoi.Screens.NavFragments.ContactList;
import com.iipl.smoi.Screens.NavFragments.Events;
import com.iipl.smoi.Screens.NavFragments.Faqs;
import com.iipl.smoi.Screens.NavFragments.FlourishList;
import com.iipl.smoi.Screens.NavFragments.HelpSupport;
import com.iipl.smoi.Screens.NavFragments.Home;
import com.iipl.smoi.Screens.NavFragments.News;
import com.iipl.smoi.Screens.NavFragments.QrScanner;
import com.iipl.smoi.Screens.NavFragments.TermsConditions;
import com.google.android.material.navigation.NavigationView;
import com.iipl.smoi.Screens.NavFragments.TestCenter;

public class MainActivity extends AppCompatActivity implements FragmentToActivity {

    ViewDataBinding binding;

    public static String currentFragment = "";

//    Toolbar toolbar;

    DrawerLayout drawerLayout;
    NavigationView navView;

    View mHeaderView;

    LinearLayout ll_login, ll_logout;
    TextView tv_city;
    TextView tv_username, tv_email;
    LinearLayout ll_logo, ll_profile;

    ImageView img_avtar;

    int int_home_Id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
//        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home(), "Home").commit();

//        Log.d("Load_Frag::>>", currentFragment);

//        toolbar = findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        navView = (NavigationView) findViewById(R.id.nav_view);
        mHeaderView = navView.inflateHeaderView(R.layout.nav_header_main);

        ll_logo = (LinearLayout) mHeaderView.findViewById(R.id.ll_logo);
        ll_profile = (LinearLayout) mHeaderView.findViewById(R.id.ll_profile);

        tv_username = (TextView) mHeaderView.findViewById(R.id.tv_username);
        tv_email = (TextView) mHeaderView.findViewById(R.id.tv_email);
        img_avtar = (ImageView) mHeaderView.findViewById(R.id.img_avtar);

        ll_login = (LinearLayout) findViewById(R.id.ll_login);
        ll_logout = (LinearLayout) findViewById(R.id.ll_logout);
        tv_city = (TextView) findViewById(R.id.tv_city);



        NavigationDrawer();





        SharedPreferences sharedpreferences1 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String store_token = sharedpreferences1.getString(AppConstant.TAG_TOKEN, null);
        String id = sharedpreferences1.getString(AppConstant.TAG_USER_ID, null);
        String roll_id  = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);

//        Log.d("id::>>",id);
//        ll_logo.setVisibility(View.VISIBLE);



        if (id !=null) {

            navView.getMenu().findItem(R.id.nav_events).setVisible(false);
            navView.getMenu().findItem(R.id.nav_qr_scanner).setVisible(false);
            navView.getMenu().findItem(R.id.nav_news).setVisible(false);
            navView.getMenu().findItem(R.id.nav_test_center).setVisible(false);
            navView.getMenu().findItem(R.id.nav_tc).setVisible(false);
            navView.getMenu().findItem(R.id.nav_aboutsmoi).setVisible(false);
            navView.getMenu().findItem(R.id.nav_helpsupport).setVisible(false);
            navView.getMenu().findItem(R.id.nav_faqs).setVisible(false);

            ll_logo.setVisibility(View.GONE);
            ll_profile.setVisibility(View.VISIBLE);

//            ll_login.setVisibility(View.GONE);
//            ll_logout.setVisibility(View.VISIBLE);

            navView.getMenu().findItem(R.id.nav_login).setVisible(false);
            navView.getMenu().findItem(R.id.nav_logout).setVisible(true);

            setUsername();

            if (roll_id.matches(AppConstant.TAG_ADMIN1)||roll_id.matches(AppConstant.TAG_ADMIN2)){
                navView.getMenu().findItem(R.id.nav_label).setVisible(true);
                navView.getMenu().findItem(R.id.nav_au_members).setVisible(true);
                navView.getMenu().findItem(R.id.nav_chapter).setVisible(true);
                navView.getMenu().findItem(R.id.nav_flourish).setVisible(true);
                navView.getMenu().findItem(R.id.nav_expo_list).setVisible(true);
            }else if (roll_id.matches(AppConstant.TAG_CHAPTER)){
                navView.getMenu().findItem(R.id.nav_label).setVisible(true);
                navView.getMenu().findItem(R.id.nav_au_members).setVisible(true);
                navView.getMenu().findItem(R.id.nav_flourish).setVisible(true);
                navView.getMenu().findItem(R.id.nav_expo_list).setVisible(true);
            }else if (roll_id.matches(AppConstant.TAG_AU)){
                navView.getMenu().findItem(R.id.nav_label).setVisible(true);
                navView.getMenu().findItem(R.id.nav_expo_list).setVisible(true);
            }

        } else {
            ll_logo.setVisibility(View.VISIBLE);
            ll_profile.setVisibility(View.GONE);
//            ll_login.setVisibility(View.VISIBLE);
//            ll_logout.setVisibility(View.GONE);
            navView.getMenu().findItem(R.id.nav_login).setVisible(true);
            navView.getMenu().findItem(R.id.nav_logout).setVisible(false);
        }


        ll_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                drawerLayout.closeDrawers();
            }
        });

        ll_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        img_avtar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewProfileActivity.class));
            }
        });


    }

    private void setUsername() {
        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String str_username = sharedpreferences.getString(AppConstant.TAG_USER_NAME, null);
        String str_email = sharedpreferences.getString(AppConstant.TAG_EMAIL, null);
        String profile_image = sharedpreferences.getString(AppConstant.TAG_PROFILE_IMAGE, "");
//        Log.d("username>>", str_username);
        tv_username.setText(str_username);
        tv_email.setText(str_email);

        Glide.with(MainActivity.this)
                .load(profile_image)
                .placeholder(R.drawable.bg)
                .error(R.drawable.bg)
                .into(img_avtar);


       /* if (!profile_image.isEmpty()) {
            byte[] b = Base64.decode(profile_image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            img_avtar.setImageBitmap(bitmap);
        }*/


    }

    private void Logout() {
        SharedPreferences sharedpreferences = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        editor.apply();

//        ll_login.setVisibility(View.VISIBLE);
//        ll_logout.setVisibility(View.GONE);

        navView.getMenu().findItem(R.id.nav_login).setVisible(true);
        navView.getMenu().findItem(R.id.nav_logout).setVisible(false);

        navView.getMenu().findItem(R.id.nav_au_members).setVisible(false);
        navView.getMenu().findItem(R.id.nav_chapter).setVisible(false);
        navView.getMenu().findItem(R.id.nav_label).setVisible(false);
        navView.getMenu().findItem(R.id.nav_flourish).setVisible(false);
        navView.getMenu().findItem(R.id.nav_expo_list).setVisible(false);
        drawerLayout.closeDrawers();

    }

    private void NavigationDrawer() {
//        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();
        //toolbar.setNavigationIcon(R.drawable.hmenu);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment frag = null;
                int itemId = menuItem.getItemId();
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                if (itemId == R.id.nav_home) {
                    frag = new Home();
//                    toolbar.setTitle(R.string.home);
                } else if (itemId == R.id.nav_qr_scanner) {
                    frag = new QrScanner();
//                    toolbar.setTitle(R.string.qr_scanner);
//                    navView.getMenu().findItem(R.id.nav_chanelanguage).setVisible(true);
                } else if (itemId == R.id.nav_test_center) {
                    Intent intent = new Intent(MainActivity.this, ChapterListActivity.class);
                    intent.putExtra("key","Test Center");
                    startActivity(intent);
//                    frag = new TestCenter();
//                    toolbar.setTitle(R.string.test_center);
                }
                /*else if (itemId == R.id.nav_label) {
                    frag = new LabelList();
                    toolbar.setTitle(R.string.label);
                }*/
                else if (itemId == R.id.nav_label){
                    startActivity(new Intent(MainActivity.this, LabelListActivity.class));
                }

                else if (itemId == R.id.nav_gallery) {
                    startActivity(new Intent(MainActivity.this, GalleryListActivity.class));
//                    frag = new GalleryList();
//                    toolbar.setTitle(R.string.request_label);
                } else if (itemId == R.id.nav_news) {
                    frag = new News();
//                    toolbar.setTitle(R.string.news);
                }
                else if (itemId == R.id.nav_events) {
                    frag = new Events();
//                    toolbar.setTitle(R.string.events);
                }
                else if (itemId == R.id.nav_contact_list) {
                    frag = new ContactList();
//                    toolbar.setTitle(R.string.contacts);
                }else if (itemId == R.id.nav_chapter) {
                    startActivity(new Intent(MainActivity.this, ChapterListActivity.class));
//                    frag = new Chapter();
//                    toolbar.setTitle(R.string.chapter);
                } else if (itemId == R.id.nav_au_members) {
                    startActivity(new Intent(MainActivity.this, MemberListActivity.class));
//                    frag = new AuMembers();
//                    toolbar.setTitle(R.string.members);
                }
                /*else if (itemId == R.id.nav_profile) {
                    frag = new MyProfile();
                    toolbar.setTitle(R.string.profile);
                }*/
                else if (itemId == R.id.nav_flourish) {
                    frag = new FlourishList();
//                    toolbar.setTitle(R.string.flourishlist);
                }else if (itemId == R.id.nav_expo_list) {
//                    frag = new ExpoList();
//                    toolbar.setTitle(R.string.expolist);
                    startActivity(new Intent(MainActivity.this, ExpoListForStallBookingActivity.class));
                }
                /*else if (itemId == R.id.nav_chanelanguage) {
                    frag = new ChangeLanguage();
                    toolbar.setTitle(R.string.chanelanguage);
                }*/
                else if (itemId == R.id.nav_faqs) {
                    frag = new Faqs();
//                    toolbar.setTitle(R.string.faqs);
                } else if (itemId == R.id.nav_helpsupport) {
                    frag = new HelpSupport();
//                    toolbar.setTitle(R.string.helpsupport);
                } else if (itemId == R.id.nav_tc) {
                    frag = new TermsConditions();
//                    toolbar.setTitle(R.string.tc);
                } else if (itemId == R.id.nav_aboutsmoi) {
                    frag = new AboutSmoi();
//                    toolbar.setTitle(R.string.aboutsmoi);
                }

                else if (itemId == R.id.nav_login) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    drawerLayout.closeDrawers();
                }
                else if (itemId == R.id.nav_logout) {
                    Logout();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }

                return loadFragment(frag);
            }

        });

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {

        /*new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();*/

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

//            finish();

            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }

        }

    }

    @Override
    protected void onPause() {
//        tv_city.setText(set_city);
        super.onPause();
    }

    @Override
    public void communicate(String comm) {
        Log.d("received::>>", comm);
    }

    public void openDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawer.openDrawer(GravityCompat.START);
    }

    public void getFragmentTag(int s) {
        int_home_Id = s;
        navView.getMenu().getItem(s).setChecked(true);
        String ss= String.valueOf(s);
        Log.d("fragment_no::>>",ss );
    }




  /*  @Override
    public boolean onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem item = menu.findItem(R.id.stall_Booking);
        SharedPreferences sharedpreferences1 = getSharedPreferences(AppConstant.LOGGED_IN_PREF, Context.MODE_PRIVATE);
        String roll_id = sharedpreferences1.getString(AppConstant.TAG_ROLL_ID, null);
        if (!(roll_id == null)) {
            if (roll_id.matches(AppConstant.TAG_ADMIN2)) {
                if (!(item.getTitle().toString() ==null)){
                    if (item.getTitle().toString().equalsIgnoreCase("Stall Booking")){
                        item.setVisible(false);
                    }
                }

            }
        }
        super.onPrepareOptionsMenu(menu);
        return false;
    }*/





}













