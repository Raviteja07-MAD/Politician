package com.o3sa.politician.sub_member_fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.o3sa.politician.R;
import com.o3sa.politician.customadapter.CustomRecyclerview;
import com.o3sa.politician.customadapter.HashMapRecycleviewadapter;
import com.o3sa.politician.customfonts.CustomBoldTextView;
import com.o3sa.politician.customfonts.CustomButton;
import com.o3sa.politician.customfonts.CustomRegularTextView;
import com.o3sa.politician.partymember_fragments.Edit_profile;
import com.o3sa.politician.partymember_fragments.Followers;
import com.o3sa.politician.partymember_fragments.Following;
import com.o3sa.politician.partymember_fragments.Home;
import com.o3sa.politician.partymember_fragments.Members;
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.sidemenu.SideMenu_SubMember;
import com.o3sa.politician.storedobjects.StoredObjects;

import java.util.ArrayList;
import java.util.HashMap;

public class Profile_submember extends Fragment {

    RecyclerView followers_recycle, following_recycle, catches_recycle;
    CustomRecyclerview customRecyclerview;
    LinearLayout se_all_flwrs_txt, se_all_following_txt, se_all_catches_txt;
    CustomButton edit_profile_btn;
    ImageView backbtn_img;

    Context mContext;
    String[] title_list  = {"Harish Rao ","K.T.Rama Rao","G Kishan Reddy","Bandaru Dattatreya MP"};
    String[] c_settingslist  = {String.valueOf(R.drawable.hrshrao),String.valueOf(R.drawable.dattatraya),String.valueOf(R.drawable.kishanreddy),String.valueOf(R.drawable.dattatraya)};
    public ArrayList<HashMap<String, String>> setngs_profilelist = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.profile, null, false );
        StoredObjects.page_type = "profile_new";
        //StoredObjects.back_type = "home";
        //initilizeMap();
        // buttonchangemethod (getActivity() , btm_profile_lay , btm_profile_img , btm_profile_txt , "4");
        //SideMenu.updatemenu( StoredObjects.back_type );
        initilization( v );

        return v;
    }

    private void initilization(View v) {

        CustomRegularTextView title_txt = v.findViewById(R.id.title_txt);
        title_txt.setText("Profile");
        ImageView backbtn_img = (ImageView)v.findViewById( R.id.backbtn_img );
        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentcallinglay(new Home_SubMember());
            }
        } );

        followers_recycle = (RecyclerView) v.findViewById(R.id.followers_recycle);
        following_recycle = (RecyclerView) v.findViewById(R.id.following_recycle);
        catches_recycle = (RecyclerView) v.findViewById(R.id.catches_recycle);
        customRecyclerview = new CustomRecyclerview(getActivity());
        se_all_flwrs_txt = (LinearLayout) v.findViewById(R.id.se_all_flwrs_txt);
        se_all_following_txt = (LinearLayout) v.findViewById(R.id.se_all_following_txt);
        se_all_catches_txt = (LinearLayout) v.findViewById(R.id.se_all_catches_txt);
        edit_profile_btn = (CustomButton) v.findViewById(R.id.edit_profile_btn);


        edit_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentcallinglay( new Edit_profile() );
            }
        });

        se_all_flwrs_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentcallinglay( new Followers() );
            }
        });

        se_all_following_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentcallinglay( new Following() );
            }
        });

        se_all_catches_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //fragmentcallinglay( new Profile_catches() );
            }
        });



        setngs_profilelist.clear();
        for (int i = 0;i<title_list.length;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("title",title_list[i]);
            hashMap.put("images",c_settingslist[i]);
            setngs_profilelist.add(hashMap);

        }


        customRecyclerview.Assigndatatorecyleviewhashmap(followers_recycle, setngs_profilelist, "profile_followers", StoredObjects.Listview, 0, StoredObjects.horizontal_orientation, R.layout.prfle_following_listitems);
        customRecyclerview.Assigndatatorecyleviewhashmap(following_recycle, setngs_profilelist, "profile_followers", StoredObjects.Listview, 0, StoredObjects.horizontal_orientation, R.layout.prfle_following_listitems);
        // customRecyclerview.Assigndatatorecyleviewhashmap(catches_recycle, StoredObjects.dummy_list, "profile_catches", StoredObjects.Listview, 0, StoredObjects.horizontal_orientation, R.layout.catches_listitems);

    }


    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace( R.id.frame_container, fragment ).addToBackStack("").commit();

    }


    protected void sendEmail(String emailid) {

        Intent emailIntent = new Intent( Intent.ACTION_SENDTO );
        emailIntent.setData( Uri.parse( "mailto:" + emailid ) );


        try {
            startActivity( emailIntent );


        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText( getActivity(),
                    "There is no email client installed.", Toast.LENGTH_SHORT ).show();
        }
    }


}
