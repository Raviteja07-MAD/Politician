package com.o3sa.politician.partymember_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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


public class Following extends Fragment {

    RecyclerView profile_following_recyclerview;
    CustomRecyclerview customRecyclerview;
    String[] title_list  = {"Harish Rao ","K.T.Rama Rao","G Kishan Reddy","Bandaru Dattatreya MP","K.T.Rama Rao"};
    String[] c_settingslist  = {String.valueOf(R.drawable.hrshrao),String.valueOf(R.drawable.dattatraya),
            String.valueOf(R.drawable.kishanreddy),String.valueOf(R.drawable.dattatraya),String.valueOf(R.drawable.dattatraya)};
    public ArrayList<HashMap<String, String>> setngs_profilelist = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.followers,null,false );

        StoredObjects.page_type = "flowing";
        StoredObjects.back_type = "flowing";

        initilization(v);
        return v;
    }

    private void initilization(View v) {

        CustomRegularTextView title_txt = v.findViewById(R.id.title_txt);
        title_txt.setText("Following");
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

        profile_following_recyclerview = (RecyclerView) v.findViewById( R.id.profile_following_recyclerview );
        customRecyclerview = new CustomRecyclerview(getActivity());

        setngs_profilelist.clear();
        for (int i = 0;i<title_list.length;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("title",title_list[i]);
            hashMap.put("images",c_settingslist[i]);
            setngs_profilelist.add(hashMap);

        }


        customRecyclerview.Assigndatatorecyleviewhashmap(profile_following_recyclerview, setngs_profilelist,"following", StoredObjects.Listview, 0, StoredObjects.ver_orientation, R.layout.followers_list);

    }

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace( R.id.frame_container, fragment ).addToBackStack("").commit();

    }

}
