package com.o3sa.politician.partymember_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.o3sa.politician.R;
import com.o3sa.politician.customfonts.CustomRegularTextView;
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.sidemenu.SideMenu_user;
import com.o3sa.politician.storedobjects.StoredObjects;

import static com.o3sa.politician.sidemenu.SideMenu.btm_log_catch_img;
import static com.o3sa.politician.sidemenu.SideMenu.btm_log_catch_lay;
import static com.o3sa.politician.sidemenu.SideMenu.btm_log_catch_txt;

public class Privacy_Settings extends Fragment {

    Switch social_toggle_btn,web_toggle_btn;
    Button privacy_sub_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.privacy_settings,null,false );
        StoredObjects.page_type = "prvcysetng";
       /* SideMenu.updatemenu(StoredObjects.page_type);
        try {
            SideMenu.title_txt.setText("Privacy Settings");
        }catch (Exception e){

        }*/

        initilization(v);
        return v;
    }

    private void initilization(View v) {

        CustomRegularTextView title_txt = v.findViewById(R.id.title_txt);
        title_txt.setText("Privacy Settings");
        ImageView backbtn_img = (ImageView)v.findViewById( R.id.backbtn_img );
        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               fragmentcallinglay(new Home());
            }
        } );

        social_toggle_btn = v.findViewById(R.id.social_toggle_btn);
        web_toggle_btn = v.findViewById(R.id.web_toggle_btn);


    }

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace( R.id.frame_container, fragment ).addToBackStack( "" ).commit();

    }

}