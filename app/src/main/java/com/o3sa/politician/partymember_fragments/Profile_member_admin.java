package com.o3sa.politician.partymember_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
import com.o3sa.politician.customfonts.CustomRegularTextView;
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.storedobjects.StoredObjects;

public class Profile_member_admin extends Fragment {

    CustomRecyclerview customRecyclerview;
    RecyclerView prfle_membrs_recyler;
    public static HashMapRecycleviewadapter adapter;
    ImageView editicon;
    LinearLayout se_all_flwrs_txt, se_all_following_txt;
    CustomBoldTextView prfle_vweslst_txt;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.profile_member_admin, null, false );
        StoredObjects.page_type = "profile_member_admin";
        initilization( v );
        return v;
    }

    private void initilization(View v) {

        editicon = v.findViewById(R.id.editicon);
        se_all_flwrs_txt = (LinearLayout) v.findViewById(R.id.se_all_flwrs_txt);
        se_all_following_txt = (LinearLayout) v.findViewById(R.id.se_all_following_txt);
        prfle_vweslst_txt = v.findViewById(R.id.prfle_vweslst_txt);

        customRecyclerview = new CustomRecyclerview(getActivity());
        prfle_membrs_recyler = (RecyclerView) v.findViewById(R.id.prfle_membrs_recyler);

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        prfle_membrs_recyler.setLayoutManager(linearLayoutManager);


        CustomRegularTextView title_txt = v.findViewById(R.id.title_txt);
        title_txt.setText("Pofile");
        ImageView backbtn_img = (ImageView)v.findViewById( R.id.backbtn_img );
        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        } );

        editicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StoredObjects.dropupval = "";
                fragmentcallinglay(new Edit_profile());
            }
        });

        se_all_flwrs_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoredObjects.folwers_type = "flwerprfile";
                fragmentcallinglay( new Followers() );
            }
        });

        se_all_following_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoredObjects.fowing_type = "flwingprfile";
                fragmentcallinglay( new Following() );
            }
        });

        prfle_vweslst_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentcallinglay( new Members() );
            }
        });

        StoredObjects.hashmaplist(4);
        customRecyclerview.Assigndatatorecyleviewhashmap(prfle_membrs_recyler, StoredObjects.dummy_list,"editmembrs",
                StoredObjects.Listview,0, StoredObjects.horizontal_orientation, R.layout.prfle_membrs_listitem);
    }

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace( R.id.frame_container, fragment ).addToBackStack("").commit();
    }
}
