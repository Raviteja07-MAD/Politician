package com.o3sa.politician.partymember_fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.o3sa.politician.R;
import com.o3sa.politician.customadapter.CustomRecyclerview;
import com.o3sa.politician.customfonts.CustomRegularTextView;
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.storedobjects.StoredObjects;

import java.util.ArrayList;
import java.util.HashMap;

public class View_donations extends Fragment {

    RecyclerView comments_recycle;
    CustomRecyclerview customRecyclerview;
     public static LinearLayout comments_lay,comments_clik_lay;
     public int comments_count = 0;
     Bundle bundle;
    ArrayList<HashMap<String, String>> postslist;
    int position;
    CustomRegularTextView custm_title_txt;
    String title,images;
    ImageView image_icon;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.view_dontaions,null,false );
        StoredObjects.page_type = "viewdonations";
       /* SideMenu.updatemenu(StoredObjects.page_type);
        try {
            SideMenu.title_txt.setText("Donations");
        }catch (Exception e){

        }*/

        initilization(v);
        return v;
    }

    private void initilization(View v) {

        CustomRegularTextView title_txt = v.findViewById(R.id.title_txt);
        title_txt.setText("Donations");
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

        comments_recycle = (RecyclerView) v.findViewById( R.id.comments_recycle );
        comments_lay = (LinearLayout)v.findViewById( R.id.comments_lay );
        customRecyclerview = new CustomRecyclerview(getActivity());

        StoredObjects.hashmaplist(4);
        customRecyclerview.Assigndatatorecyleviewhashmap(comments_recycle, StoredObjects.dummy_list,"comments", StoredObjects.Listview, 0, StoredObjects.ver_orientation, R.layout.comments_listitems);
    }

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
    }


}
