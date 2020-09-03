package com.o3sa.politician.sidemenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.o3sa.politician.Activities.Sign_in_Sign_up;
import com.o3sa.politician.R;
import com.o3sa.politician.circularimageview.CircularImageView;
import com.o3sa.politician.customfonts.CustomMediumTextView;
import com.o3sa.politician.customfonts.CustomRegularTextView;
import com.o3sa.politician.partymember_fragments.Addpost;
import com.o3sa.politician.partymember_fragments.Changepassword;
import com.o3sa.politician.partymember_fragments.Donation;
import com.o3sa.politician.partymember_fragments.Events;
import com.o3sa.politician.partymember_fragments.Home;
import com.o3sa.politician.partymember_fragments.Members;
import com.o3sa.politician.partymember_fragments.Notifications;
import com.o3sa.politician.partymember_fragments.Test;
import com.o3sa.politician.storedobjects.DevicePermissions;
import com.o3sa.politician.storedobjects.StoredObjects;
import com.o3sa.politician.user_fragments.Askfordonation;
import com.o3sa.politician.user_fragments.Events_user;
import com.o3sa.politician.user_fragments.Home_user;
import com.o3sa.politician.user_fragments.Profile_user;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class SideMenu_user extends AppCompatActivity {

    //actionbarlayout
    public static ImageView custm_menu_img, custm_backbtn_img, custm_search_img;
    public static CustomRegularTextView custm_title_txt;
    public static LinearLayout actionbar_lay, bottom_layout, custom_new_actionbar_lay;
    ImageView sidemnu_cancel_img;
    public static CustomMediumTextView header_name;
    public static CircularImageView header_circular_img;

    public static DrawerLayout mDrawerLayout;
    public static ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    String[] navMenuTitles;
    private TypedArray navMenuIconsleft;
    private ArrayList<NavDrawerItem> navDrawerItems;
    public static NavDrawerListAdapter adapter;
    public static int drawablecount = 0;
    public static LinearLayout list_slidermenu_lay;

    public static LinearLayout btm_log_catch_lay, btmaddpost_lay, btm_map_lay, btm_notifications_lay, btm_profile_lay;
    public static ImageView btm_log_catch_img, btm_addpost_img, btm_map_img, btm_notifications_img, btm_profile_img;
    public static TextView btm_log_catch_txt, btm_addpost_txt, btm_map_txt, btm_notifications_txt, btm_profile_txt;

    public static LinearLayout btm_notifications_lay_bg;

    // public static BadgeView badge;
    TextView toolbar_badge_text;
    CardView toolbar_badge_parent;

    //Database database;
    public static ArrayList<HashMap<String, String>> getprofilelist = new ArrayList<>();


    private static final int TAKE_PICTURE_REQUEST_B = 100;

    private Uri mCropImagedUri;
    File f_new;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidemenu_user);
        //database = new Database(SideMenu.this);
        //database.getAllDevice();
        initialization();
      /*  if (InterNetChecker.isNetworkAvailable(SideMenu.this)) {

            new GetProfileTask().execute(StoredObjects.UserId);
        }else{
            StoredObjects.ToastMethod(SideMenu.this.getResources().getString(R.string.checkinternet),SideMenu.this);
        }
*/

        DevicePermissions.checkAndRequestPermissions(SideMenu_user.this);

    }

    public void initialization() {

        list_slidermenu_lay = (LinearLayout) findViewById(R.id.list_slidermenu_lay);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navDrawerItems = new ArrayList<NavDrawerItem>();
        navMenuTitles = getResources().getStringArray(R.array.user_names_user);
        navMenuIconsleft = getResources().obtainTypedArray(R.array.user_icons);

        for (int i = 0; i < navMenuTitles.length; i++) {
            navDrawerItems.add(new NavDrawerItem(navMenuTitles[i], navMenuIconsleft.getResourceId(i, -1)));

        }
        navMenuIconsleft.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        adapter = new NavDrawerListAdapter(SideMenu_user.this, navDrawerItems);
        mDrawerList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // custm_menu_img = (ImageView)findViewById(R.id.custm_menu_img);
        // custm_backbtn_img = (ImageView)findViewById(R.id.custm_backbtn_img);
        // custm_title_txt = (CustomRegularTextView) findViewById(R.id.custm_title_txt);
        // custm_search_img = (ImageView)findViewById( R.id.custm_search_img );
        actionbar_lay = (LinearLayout) findViewById(R.id.actionbar_lay);
        //  custom_new_actionbar_lay = (LinearLayout)findViewById( R.id.custom_new_actionbar_lay );
        bottom_layout = (LinearLayout) findViewById(R.id.bottom_layout);

        sidemnu_cancel_img = (ImageView) findViewById(R.id.sidemnu_cancel_img);
        header_name = (CustomMediumTextView) findViewById(R.id.header_name);
        header_circular_img = (CircularImageView) findViewById(R.id.header_circular_img);


        btm_log_catch_lay = (LinearLayout) findViewById(R.id.btm_log_catch_lay);
        btmaddpost_lay = (LinearLayout) findViewById(R.id.btmaddpost_lay);
        btm_map_lay = (LinearLayout) findViewById(R.id.btm_map_lay);
        btm_notifications_lay = (LinearLayout) findViewById(R.id.btm_notifications_lay);
        btm_profile_lay = (LinearLayout) findViewById(R.id.btm_profile_lay);

        btm_log_catch_img = (ImageView) findViewById(R.id.btm_log_catch_img);
        btm_addpost_img = (ImageView) findViewById(R.id.btm_addpost_img);
        btm_map_img = (ImageView) findViewById(R.id.btm_map_img);
        btm_notifications_img = (ImageView) findViewById(R.id.btm_notifications_img);
        btm_profile_img = (ImageView) findViewById(R.id.btm_profile_img);

        btm_log_catch_txt = findViewById(R.id.btm_log_catch_txt);
        btm_addpost_txt = findViewById(R.id.btm_addpost_txt);
        btm_map_txt = findViewById(R.id.btm_map_txt);
        btm_notifications_txt = findViewById(R.id.btm_notifications_txt);
        btm_profile_txt = findViewById(R.id.btm_profile_txt);
        toolbar_badge_text = findViewById(R.id.toolbar_badge_text);
        toolbar_badge_parent = findViewById(R.id.toolbar_badge_parent);

        btm_notifications_lay_bg = findViewById(R.id.btm_notifications_lay_bg);


       // btmaddpost_lay.setVisibility(View.GONE);

        btm_map_img.setImageResource(R.drawable.invest);

        btm_map_txt.setText(R.string.help);

//        custm_backbtn_img.setVisibility( View.GONE);
        //custm_title_txt.setVisibility( View.GONE);

        sidemnu_cancel_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(list_slidermenu_lay);
            }
        });

/*
        custm_menu_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                drawablecount++;
                if (drawablecount == 1) {
                    mDrawerLayout.openDrawer(list_slidermenu_lay);
                } else {
                    drawablecount = 0;
                    mDrawerLayout.closeDrawer(list_slidermenu_lay);
                }
            }

        });
*/

        /*custm_backbtn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backbuttonclickevents();
            }
        });*/
        buttonchangemethod(SideMenu_user.this, btm_log_catch_lay, btm_log_catch_img, btm_log_catch_txt, "0");
        fragmentcallinglay(new Home_user());
       /* Intent intent = new Intent(SideMenu.this, ScanActivity.class);
        SideMenu.this.startActivity(intent);*/

        buttonchangelaymethod(SideMenu_user.this, btm_log_catch_lay, btm_log_catch_img, btm_log_catch_txt, "0");
        buttonchangelaymethod(SideMenu_user.this, btmaddpost_lay, btm_addpost_img, btm_addpost_txt, "1");
        buttonchangelaymethod(SideMenu_user.this, btm_map_lay, btm_map_img, btm_map_txt, "2");
        buttonchangelaymethod(SideMenu_user.this, btm_notifications_lay, btm_notifications_img, btm_notifications_txt, "3");
        buttonchangelaymethod(SideMenu_user.this, btm_profile_lay, btm_profile_img, btm_profile_txt, "4");
    }

    public void buttonchangelaymethod(final Activity activity, final LinearLayout layout1, final ImageView image, final TextView text1, final String type) {

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonchangemethod(activity, layout1, image, text1, type);

                if (type.equalsIgnoreCase("0")) {
                    fragmentcallinglay(new Home_user());
                    //  txt_title.setText (R.string.home);
                } else if (type.equalsIgnoreCase("1")) {
                    fragmentcallinglay(new Events_user());
                } else if (type.equalsIgnoreCase("2")) {
                    fragmentcallinglay(new Askfordonation());
                    //  txt_title.setText (R.string.menu_bottom_label_explore);
                } else if (type.equalsIgnoreCase("3")) {
                    fragmentcallinglay(new Notifications());
                    // toolbar_badge_parent.setVisibility( View.GONE );
                } else if (type.equalsIgnoreCase("4")) {
                    fragmentcallinglay(new Profile_user());
                }
            }
        });
    }

    public static void buttonchangemethod(Activity activity, LinearLayout layout1, ImageView image, TextView text1, String type) {

        btm_log_catch_lay.setBackgroundColor(activity.getResources().getColor(R.color.white));
        btmaddpost_lay.setBackgroundColor(activity.getResources().getColor(R.color.white));
        btm_map_lay.setBackgroundColor(activity.getResources().getColor(R.color.white));
        btm_notifications_lay.setBackgroundColor(activity.getResources().getColor(R.color.white));
        btm_profile_lay.setBackgroundColor(activity.getResources().getColor(R.color.white));

        btm_log_catch_img.setColorFilter(ContextCompat.getColor(activity, R.color.lite_color_text), android.graphics.PorterDuff.Mode.SRC_IN);
        btm_addpost_img.setColorFilter(ContextCompat.getColor(activity, R.color.lite_color_text), android.graphics.PorterDuff.Mode.SRC_IN);
        btm_map_img.setColorFilter(ContextCompat.getColor(activity, R.color.lite_color_text), android.graphics.PorterDuff.Mode.SRC_IN);
        btm_notifications_img.setColorFilter(ContextCompat.getColor(activity, R.color.lite_color_text), android.graphics.PorterDuff.Mode.SRC_IN);
        btm_profile_img.setColorFilter(ContextCompat.getColor(activity, R.color.lite_color_text), android.graphics.PorterDuff.Mode.SRC_IN);

        btm_log_catch_txt.setTextColor(activity.getResources().getColor(R.color.lite_color_text));
        btm_addpost_txt.setTextColor(activity.getResources().getColor(R.color.lite_color_text));
        btm_map_txt.setTextColor(activity.getResources().getColor(R.color.lite_color_text));
        btm_notifications_txt.setTextColor(activity.getResources().getColor(R.color.lite_color_text));
        btm_profile_txt.setTextColor(activity.getResources().getColor(R.color.lite_color_text));
        if (type.equals("5")) {

        } else {
            layout1.setBackgroundColor(activity.getResources().getColor(R.color.lightgray_clr));
            image.setColorFilter(ContextCompat.getColor(activity, R.color.orange), android.graphics.PorterDuff.Mode.SRC_IN);
            text1.setTextColor(activity.getResources().getColor(R.color.orange));
        }
    }

    private class SlideMenuClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
            displayView(position);
            adapter.notifyDataSetChanged();
        }
    }



        public void displayView(int position) {

            Fragment fragment = null;
            StoredObjects.listcount = position;

            switch (position) {

                case 0:
                    fragment = new Profile_user();
                    break;
                case 1:
                    fragment = new Askfordonation();
                    break;

                case 2:
                    fragment = new Changepassword();
                    break;
                case 3:
                    Logoutpopup(SideMenu_user.this,"1");
                    break;

        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            mDrawerLayout.closeDrawer(list_slidermenu_lay);

        } else {
            // error in creating fragment
            Log.e("Sidemenu", "Error in creating fragment");
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    public void backbuttonclickevents() {

        if( StoredObjects.page_type.equalsIgnoreCase( "landingpage" )) {
            checkbackclick();
        }

        if (StoredObjects.page_type.equalsIgnoreCase("admnprfile")) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }

            buttonchangemethod(SideMenu_user.this, btm_profile_lay, btm_profile_img, btm_profile_txt, "4");
        }

        if (StoredObjects.page_type.equalsIgnoreCase("prvcysetng")) {
            fragmentcallinglay(new Home_user());
            buttonchangemethod(SideMenu_user.this, btm_log_catch_lay, btm_log_catch_img, btm_log_catch_txt, "0");
        }

        if (StoredObjects.page_type.equalsIgnoreCase("chngepswd")) {
            fragmentcallinglay(new Home_user());

            buttonchangemethod(SideMenu_user.this, btm_profile_lay, btm_profile_img, btm_profile_txt, "4");
        }

        if (StoredObjects.page_type.equalsIgnoreCase("details")) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
            buttonchangemethod(SideMenu_user.this, btm_log_catch_lay, btm_log_catch_img, btm_log_catch_txt, "0");
        }

        if (StoredObjects.page_type.equalsIgnoreCase("viewdonations")) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
            //  buttonchangemethod(SideMenu_user.this, btm_log_catch_lay, btm_log_catch_img, btm_log_catch_txt, "0");
        }

        if (StoredObjects.page_type.equalsIgnoreCase("vwemembers")) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }

            buttonchangemethod(SideMenu_user.this, btm_profile_lay, btm_profile_img, btm_profile_txt, "4");
        }

        if (StoredObjects.page_type.equalsIgnoreCase("members")) {
            fragmentcallinglay(new Home_user());

            buttonchangemethod(SideMenu_user.this, btm_log_catch_lay, btm_log_catch_img, btm_log_catch_txt, "0");
        }

/*
        else if(StoredObjects.page_type.equalsIgnoreCase("folwers")){

            if(StoredObjects.folwers_type.equalsIgnoreCase("flwerprfile")){
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
                buttonchangemethod(SideMenu_user.this, btm_profile_lay, btm_profile_img, btm_profile_txt, "4");

            }else {
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
            buttonchangemethod(SideMenu_user.this, btm_log_catch_lay, btm_log_catch_img, btm_log_catch_txt, "0");
        }
*/

        if (StoredObjects.page_type.equalsIgnoreCase("profile_member")) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
        }
        if (StoredObjects.page_type.equalsIgnoreCase("adevnt")) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
            buttonchangelaymethod(SideMenu_user.this, btm_map_lay, btm_map_img, btm_map_txt, "2");
        }

        if (StoredObjects.page_type.equalsIgnoreCase("cmntlist")) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
            buttonchangelaymethod(SideMenu_user.this, btm_map_lay, btm_map_img, btm_map_txt, "2");
        }

/*
        else if(StoredObjects.page_type.equalsIgnoreCase("flowing")){

            if(StoredObjects.fowing_type.equalsIgnoreCase("flwingprfile")){
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
                buttonchangemethod(SideMenu_user.this, btm_profile_lay, btm_profile_img, btm_profile_txt, "4");

            }else {
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
            buttonchangemethod(SideMenu_user.this, btm_log_catch_lay, btm_log_catch_img, btm_log_catch_txt, "0");
        }
*/

        else if(StoredObjects.page_type.equalsIgnoreCase("helpcmplnts")) {

            fragmentcallinglay(new Home_user());
        }

        else if(StoredObjects.page_type.equalsIgnoreCase("fetchlctn")) {

            if (StoredObjects.tab_type.equalsIgnoreCase("creatadres")) {
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
                buttonchangemethod(SideMenu_user.this, btm_profile_lay, btm_profile_img, btm_profile_txt, "4");

            } else if (StoredObjects.tab_type.equalsIgnoreCase("evntlctn")) {
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
                buttonchangelaymethod(SideMenu_user.this, btm_map_lay, btm_map_img, btm_map_txt, "2");

            }
        }

        if (StoredObjects.page_type.equalsIgnoreCase("profile")){
            fragmentcallinglay(new Home_user());

            buttonchangemethod(SideMenu_user.this, btm_profile_lay, btm_profile_img, btm_profile_txt, "4");

        }

        if (StoredObjects.page_type.equalsIgnoreCase("profile_new")){
            fragmentcallinglay(new Home_user());

            buttonchangemethod(SideMenu_user.this, btm_profile_lay, btm_profile_img, btm_profile_txt, "4");

        }



        if (StoredObjects.page_type.equalsIgnoreCase("admember")) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }

            buttonchangemethod(SideMenu_user.this, btm_log_catch_lay, btm_log_catch_img, btm_log_catch_txt, "0");
        }

        if (StoredObjects.page_type.equalsIgnoreCase("ntfctn")) {
            fragmentcallinglay(new Home_user());

        }

        if( StoredObjects.page_type.equalsIgnoreCase("adpost")
                ||StoredObjects.page_type.equalsIgnoreCase("evnts")
                ||StoredObjects.page_type.equalsIgnoreCase("cratearea")
                ||StoredObjects.page_type.equalsIgnoreCase("setngs")){

            fragmentcallinglay(new Home_user());
            buttonchangemethod(SideMenu_user.this, btm_log_catch_lay, btm_log_catch_img, btm_log_catch_txt, "0");
        }


        if (StoredObjects.page_type.equalsIgnoreCase("profile_member_admin")) {

            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
        }
        if (StoredObjects.page_type.equalsIgnoreCase("folwers")) {

            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
        }
        if (StoredObjects.page_type.equalsIgnoreCase("flowing")) {

            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
        }
       /* if (StoredObjects.page_type.equalsIgnoreCase("members")) {

            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
        }

        if (StoredObjects.page_type.equalsIgnoreCase("members")) {

            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
        }*/

       /* if (StoredObjects.page_type.equalsIgnoreCase("evnts")) {

            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
        }*/

        if (StoredObjects.page_type.equalsIgnoreCase("adevnt")) {

            fragmentcallinglay( new Events());
        }





        if (StoredObjects.page_type.equalsIgnoreCase("change_password")) {
            fragmentcallinglay(new Home_user());
        }

        if( StoredObjects.page_type.equalsIgnoreCase( "editprofile" )) {
            fragmentcallinglay(new Profile_user());
            buttonchangemethod(SideMenu_user.this, btm_profile_lay, btm_profile_img, btm_profile_txt, "4");
        }

       /* if( StoredObjects.page_type.equalsIgnoreCase( "members" )) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
        }*/

        if( StoredObjects.page_type.equalsIgnoreCase( "Following" )) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
        }
        if( StoredObjects.page_type.equalsIgnoreCase( "Followers" )) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
        }
        if( StoredObjects.page_type.equalsIgnoreCase( "searchpage" )) {

            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
        }

        if (StoredObjects.page_type.equalsIgnoreCase( "feed_innerpage" )){
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
        }


        if( StoredObjects.page_type.equalsIgnoreCase( "followers_profile" )) {

            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
            else if(getSupportFragmentManager().getBackStackEntryCount() == 0||getSupportFragmentManager().getBackStackEntryCount() == 1) {

            }
            fragmentcallinglay( new Test() );

        }
        if( StoredObjects.page_type.equalsIgnoreCase( "Addevent" )) {

            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
            else if(getSupportFragmentManager().getBackStackEntryCount() == 0||getSupportFragmentManager().getBackStackEntryCount() == 1) {

            }
            fragmentcallinglay( new Events() );

        }
        if( StoredObjects.page_type.equalsIgnoreCase( "Addmember" )) {

            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
            else if(getSupportFragmentManager().getBackStackEntryCount() == 0||getSupportFragmentManager().getBackStackEntryCount() == 1) {

            }
            fragmentcallinglay( new Members() );

        }


        if( StoredObjects.page_type.equalsIgnoreCase( "Adddonation" )) {

            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }
            else if(getSupportFragmentManager().getBackStackEntryCount() == 0||getSupportFragmentManager().getBackStackEntryCount() == 1) {

            }
            fragmentcallinglay( new Donation() );

        }


    }

        public void checkbackclick () {

            if (doubleBackToExitPressedOnce) {
                // super.onBackPressed();
                Logoutpopup(SideMenu_user.this,"0");
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 3000);
        }

        public void DisplayAlertDialog (String string){

            AlertDialog.Builder builder = new AlertDialog.Builder(SideMenu_user.this);

            builder.setMessage(string)
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            minimizeApp();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        public void minimizeApp () {
            finish();
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }

        public void onBackPressed () {
             backbuttonclickevents();
        }



        public void fragmentcallinglay (Fragment fragment){

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
        }

        public static void updatemenu (String pagetype){

       /* if(pagetype.equalsIgnoreCase("landingpage")){
        }else{

        }
*/
            if (pagetype.equalsIgnoreCase("home")) {
                // bottom_layout.setVisibility( View.GONE );
            } else {
                // bottom_layout.setVisibility( View.VISIBLE );
            }

        }

/*
    public class GetProfileTask extends AsyncTask<String, String, String> {
        String strResult = "";

        @Override
        protected void onPreExecute() {
           // CustomProgressbar.Progressbarshow( SideMenu.this );
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>( 2 );
                nameValuePairs.add( new BasicNameValuePair( "token", "Mikel" ) );
                nameValuePairs.add( new BasicNameValuePair( "method", "get-profile" ) );
                nameValuePairs.add( new BasicNameValuePair( "customer_id", params[0] ) );
                strResult = HttpPostClass.PostMethod( StoredUrls.BaseUrl, nameValuePairs );
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SocketTimeoutException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ConnectTimeoutException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return strResult;
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                // CustomProgressbar.Progressbarcancel(SideMenu.this);
            }
            try {

                JSONObject jsonObject = new JSONObject( result );
                String status = jsonObject.getString( "status" );
                if (status.equalsIgnoreCase( "200" )) {
                    String results = jsonObject.getString( "results" );
                    getprofilelist = JsonParsing.GetJsonData( results );
                    header_name.setText( getprofilelist.get( 0 ).get( "name" ) );

                    String notifications = getprofilelist.get( 0 ).get( "notifications" );

                    StoredObjects.notific_count = notifications;

                    toolbar_badge_text.setText( notifications );
                    if (notifications.equalsIgnoreCase( "0" )){
                        toolbar_badge_parent.setVisibility( View.GONE );
                    }else {
                        toolbar_badge_parent.setVisibility( View.VISIBLE );
                    }


                    String on_new_user_followed = getprofilelist.get(0).get("on_new_user_followed");
                    String on_another_user_commented_on_your_post = getprofilelist.get(0).get("on_another_user_commented_on_your_post");
                    String on_new_post_by_user_you_following = getprofilelist.get(0).get("on_new_post_by_user_you_following");
                    String on_another_user_liked_your_post = getprofilelist.get(0).get("on_another_user_liked_your_post");
                    String on_new_post_by_your_follower = getprofilelist.get(0).get("on_new_post_by_your_follower");


                    StoredObjects.savedata(getApplicationContext(),on_new_user_followed,"on_new_user_followed");
                    StoredObjects.savedata(getApplicationContext(),on_another_user_commented_on_your_post,"on_another_user_commented_on_your_post");
                    StoredObjects.savedata(getApplicationContext(),on_new_post_by_user_you_following,"on_new_post_by_user_you_following");
                    StoredObjects.savedata(getApplicationContext(),on_another_user_liked_your_post,"on_another_user_liked_your_post");
                    StoredObjects.savedata(getApplicationContext(),on_new_post_by_your_follower,"on_new_post_by_your_follower");


                    StoredObjects.LogMethod( "response", "response_settings:---" + on_new_user_followed+"<>"+on_another_user_commented_on_your_post+"<>"+on_new_post_by_user_you_following
                            +"<>"+on_another_user_liked_your_post+"<>"+on_new_post_by_your_follower);

                    try {
                        Glide.with( SideMenu.this )
                                .load( Uri.parse( StoredUrls.Uploadedimages + getprofilelist.get( 0 ).get( "image" )  ) ) // add your image url
                                .centerCrop() // scale to fill the ImageView and crop any extra
                                .fitCenter() // scale to fit entire image within ImageView
                                .placeholder( R.drawable.man )
                                .into( header_circular_img );
                    } catch (Exception e) {
                    }
                } else {
                    String error = jsonObject.getString( "error" );
                    //StoredObjects.ToastMethod(error,SidemenuIndividual.this);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                // TODO: handle exception
            } catch (IllegalStateException e) {
                // TODO: handle exception
            } catch (IllegalArgumentException e) {
                // TODO: handle exception
            } catch (NetworkOnMainThreadException e) {
                // TODO: handle exception
            } catch (RuntimeException e) {
                // TODO: handle exception
            } catch (Exception e) {
                StoredObjects.LogMethod( "response", "response:---" + e );
            }
        }
    }
*/


    private void Logoutpopup(final Activity activity, final String type) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.logooutpopup );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        Button ok_btn = (Button)dialog.findViewById(R.id.ok_btn);
        Button cancel_btn = (Button)dialog.findViewById(R.id.cancel_btn);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);
        CustomRegularTextView logout_txt = (CustomRegularTextView)dialog.findViewById( R.id.logout_txt );
        CustomRegularTextView exit_txt = (CustomRegularTextView)dialog.findViewById( R.id.exit_txt );

        if (type.equals( "1" )){
            logout_txt.setVisibility( View.VISIBLE );
        }
        else {
            exit_txt.setVisibility( View.VISIBLE );
            logout_txt.setVisibility( View.GONE );
        }

        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
            ok_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (type.equals( "1" )) {
                        //database.UpdateUserId( "0" );
                       // database.UpdateUsertype( "0" );
                        activity.finish();
                        Intent intent = new Intent( activity, Sign_in_Sign_up.class );
                        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        activity.startActivity( intent );
                    } else {
                        minimizeApp();
                    }
                }
            });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }




/*
    public class ImageUploadTaskNew extends AsyncTask<String, String, String> {
        String strResult = "";
        String filepath  = "";
        @Override
        protected void onPreExecute() {
            CustomProgressbar.Progressbarshow( SideMenu.this);
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>( 2 );
                strResult = HttpPostClass.uploadFile123( params[0], StoredUrls.imageupload_new );
                filepath = params[0];

          */
/*   nameValuePairs.add(new BasicNameValuePair("method","upload-file"));
             nameValuePairs.add(new BasicNameValuePair("uploaded_file ",params[0]));
             strResult = HttpPostClass.PostMethod( StoredUrls.BaseUrl,nameValuePairs);
*//*

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

               */
/* nameValuePairs.add(new BasicNameValuePair("method","upload-file"));
                nameValuePairs.add(new BasicNameValuePair("uploaded_file ",params[0]));*//*


            // strResult = HttpPostClass.PostMethod( StoredUrls.BaseUrl,nameValuePairs);
            return strResult;
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                CustomProgressbar.Progressbarcancel(SideMenu.this);
            }
            try {
                if (InterNetChecker.isNetworkAvailable(SideMenu.this)) {
                    new CheckBreedTask().execute( result );
                }else{
                    Toast.makeText(SideMenu.this, "Please Check Internet Connection.", Toast.LENGTH_SHORT).show();
                }

            }
            catch (Exception e) {
                StoredObjects.LogMethod("response", "response:---"+e);
            }
        }
    }
*/
/*
    public class CheckBreedTask extends AsyncTask<String, String, String> {
        String strResult = "";
        String filepath  = "";
        @Override
        protected void onPreExecute() {
            CustomProgressbar.Progressbarshow( SideMenu.this);
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>( 2 );
               */
/* strResult = HttpPostClass.uploadFile123( params[0], StoredUrls.imageupload_new );
                filepath = params[0];*//*


         */
/*nameValuePairs.add(new BasicNameValuePair("method","upload-file"));
             nameValuePairs.add(new BasicNameValuePair("uploaded_file ",params[0]));*//*

             String Url = StoredUrls.breedimagecheck_url+params[0];
             strResult = HttpPostClass.PostMethod( Url,nameValuePairs);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

               */
/* nameValuePairs.add(new BasicNameValuePair("method","upload-file"));
                nameValuePairs.add(new BasicNameValuePair("uploaded_file ",params[0]));*//*


            // strResult = HttpPostClass.PostMethod( StoredUrls.BaseUrl,nameValuePairs);
            return strResult;
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                CustomProgressbar.Progressbarcancel(SideMenu.this);
            }
            try {


            }
            catch (Exception e) {
                StoredObjects.LogMethod("response", "response:---"+e);
            }
        }
    }
*/





    }



