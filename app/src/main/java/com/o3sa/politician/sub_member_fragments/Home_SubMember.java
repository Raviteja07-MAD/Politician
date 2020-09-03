package com.o3sa.politician.sub_member_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.o3sa.politician.R;
import com.o3sa.politician.customadapter.CustomRecyclerview;
import com.o3sa.politician.customadapter.HashMapRecycleviewadapter;
import com.o3sa.politician.partymember_fragments.Donation;
import com.o3sa.politician.partymember_fragments.Followers;
import com.o3sa.politician.partymember_fragments.Following;
import com.o3sa.politician.partymember_fragments.Members;
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.sidemenu.SideMenu_SubMember;
import com.o3sa.politician.storedobjects.StoredObjects;
import com.o3sa.politician.user_fragments.Searchpage;

import java.util.ArrayList;
import java.util.HashMap;

import static com.o3sa.politician.sidemenu.SideMenu.drawablecount;
import static com.o3sa.politician.sidemenu.SideMenu_SubMember.btm_log_catch_img;
import static com.o3sa.politician.sidemenu.SideMenu_SubMember.btm_log_catch_lay;
import static com.o3sa.politician.sidemenu.SideMenu_SubMember.btm_log_catch_txt;
import static com.o3sa.politician.sidemenu.SideMenu_SubMember.buttonchangemethod;
import static com.o3sa.politician.sidemenu.SideMenu_SubMember.list_slidermenu_lay;
import static com.o3sa.politician.sidemenu.SideMenu_SubMember.mDrawerLayout;


public class Home_SubMember extends Fragment {


    RecyclerView landing_recycle;
    CustomRecyclerview customRecyclerview;
    LinearLayout top_lay;
    LinearLayout nodatafound_lay,followers_lay,following_lay,members_lay,donatn_lay;
    ProgressBar progressBar1;

    ImageView custm_menu_img,custm_search_img;


    public ArrayList<HashMap<String, String>> setngs_profilelist = new ArrayList<>();
    String[] c_settingslist  = {String.valueOf(R.drawable.kcr_one_pleanary),String.valueOf(R.drawable.ktr_singapore),String.valueOf(R.drawable.harishrao),String.valueOf(R.drawable.kcr_modi_meeting),String.valueOf(R.drawable.cmrbasnthapnchmi)};
    String[] title_list  = {"KCR in Party plenary meeting ","KTR Minister Meeting With Singapore Consul General","Droughts to end in Telangana: Harish Rao","PM Modi's appreciation of TRS leaves BJP unit in Telangana.","Basantha panchami celebrations"};

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.home_user,null,false );
        StoredObjects.page_type = "landingpage";
        //StoredObjects.back_type="landingpage";
        SideMenu_SubMember.buttonchangemethod(getActivity(), SideMenu_SubMember.btm_log_catch_lay, SideMenu_SubMember.btm_log_catch_img, SideMenu_SubMember.btm_log_catch_txt, "0");

        /*SideMenu.updatemenu(StoredObjects.page_type);
        try {
            SideMenu.title_txt.setText("Home");
        }catch (Exception e){

        }*/
        initilization(v);
        return v;
    }

    private void initilization(View v) {

        custm_menu_img = (ImageView)v.findViewById( R.id.custm_menu_img );
        custm_search_img = (ImageView)v.findViewById( R.id.custm_search_img );

        landing_recycle = (RecyclerView) v.findViewById( R.id.landing_recycle );
        customRecyclerview = new CustomRecyclerview(getActivity());
        top_lay = (LinearLayout)v.findViewById( R.id.top_lay );
        nodatafound_lay = (LinearLayout) v.findViewById( R.id.nodatafound_lay );
        progressBar1 = v.findViewById( R.id.progressBar1 );
        following_lay = v.findViewById( R.id.following_lay );
        followers_lay = v.findViewById( R.id.followers_lay );
        members_lay = v.findViewById( R.id.members_lay );
        donatn_lay = v.findViewById( R.id.donatn_lay );
        custm_menu_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                drawablecount++;
                if (drawablecount == 1) {
                    SideMenu_SubMember.mDrawerLayout.openDrawer(SideMenu_SubMember.list_slidermenu_lay);
                } else {
                    drawablecount = 0;
                    SideMenu_SubMember.mDrawerLayout.closeDrawer(SideMenu_SubMember.list_slidermenu_lay);
                }
            }

        });
        custm_search_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentcallinglay( new Searchpage() );
            }
        } );

        followers_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoredObjects.folwers_type = "flwerhome";
                fragmentcallinglay(new Followers());
            }
        });

        following_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoredObjects.fowing_type = "flwinghome";
                fragmentcallinglay(new Following());
            }
        });

        members_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentcallinglay(new Members_submembers());
            }
        });

        donatn_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoredObjects.help_type="btmhelp";
                fragmentcallinglay(new Donation());
            }
        });

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        landing_recycle.setLayoutManager(linearLayoutManager);

        setngs_profilelist.clear();

        for (int i = 0;i<c_settingslist.length;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("images",c_settingslist[i]);
            hashMap.put("title",title_list[i]);
            setngs_profilelist.add(hashMap);
        }

        customRecyclerview.Assigndatatorecyleviewhashmap( landing_recycle, setngs_profilelist,"feedpage", StoredObjects.Listview, 0, StoredObjects.ver_orientation, R.layout.home_listitems);
    }

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
    }

}
