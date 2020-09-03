package com.o3sa.politician.partymember_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.o3sa.politician.R;
import com.o3sa.politician.circularimageview.CircularImageView;
import com.o3sa.politician.customadapter.CustomRecyclerview;
import com.o3sa.politician.customadapter.HashMapRecycleviewadapter;
import com.o3sa.politician.customfonts.CustomBoldTextView;
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.storedobjects.StoredObjects;

import static com.o3sa.politician.sidemenu.SideMenu.btm_profile_img;
import static com.o3sa.politician.sidemenu.SideMenu.btm_profile_lay;
import static com.o3sa.politician.sidemenu.SideMenu.btm_profile_txt;
import static com.o3sa.politician.sidemenu.SideMenu.buttonchangemethod;

public class Test extends Fragment {

    CustomRecyclerview customRecyclerview;
    RecyclerView prfle_membrs_recyler;
    public static HashMapRecycleviewadapter adapter;
    ImageView editicon;
    LinearLayout se_all_flwrs_txt, se_all_following_txt;
    CustomBoldTextView prfle_vweslst_txt;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.profile_test, null, false );
        StoredObjects.page_type = "admnprfile";
        SideMenu.updatemenu(StoredObjects.page_type);
        try {
            SideMenu.title_txt.setText("Profile");
        }catch (Exception e){

        }
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
