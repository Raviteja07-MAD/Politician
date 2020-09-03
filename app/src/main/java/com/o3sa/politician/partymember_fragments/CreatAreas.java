package com.o3sa.politician.partymember_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.o3sa.politician.AddressPicking.FetchCurntLocatn;
import com.o3sa.politician.R;
import com.o3sa.politician.customfonts.CustomEditText;
import com.o3sa.politician.customfonts.CustomRegularTextView;
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.sidemenu.SideMenu_user;
import com.o3sa.politician.storedobjects.StoredObjects;

import static com.o3sa.politician.sidemenu.SideMenu.btm_log_catch_img;
import static com.o3sa.politician.sidemenu.SideMenu.btm_log_catch_lay;
import static com.o3sa.politician.sidemenu.SideMenu.btm_log_catch_txt;
import static com.o3sa.politician.sidemenu.SideMenu.btm_notifications_img;
import static com.o3sa.politician.sidemenu.SideMenu.btm_notifications_lay;
import static com.o3sa.politician.sidemenu.SideMenu.btm_notifications_txt;
import static com.o3sa.politician.sidemenu.SideMenu.buttonchangemethod;

public class CreatAreas extends Fragment {

    LinearLayout pickuploc_lay;
    CustomEditText any_pickup_edtx;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate ( R.layout.creat_area, null , false);
        StoredObjects.page_type = "cratearea";
        //SideMenu.updatemenu(StoredObjects.page_type);

        buttonchangemethod(getActivity(), btm_notifications_lay, btm_notifications_img, btm_notifications_txt, "3");
       /* try {
            SideMenu.title_txt.setText("Create Areas");
        }catch (Exception e){

        }*/

        init (v);
        return v;
    }

    private void init(View v) {

        CustomRegularTextView title_txt = v.findViewById(R.id.title_txt);
        title_txt.setText("Create Areas");
        ImageView backbtn_img = (ImageView)v.findViewById( R.id.backbtn_img );
        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              fragmentcallinglay(new Areaslist());
            }
        } );

        pickuploc_lay = v.findViewById(R.id.pickuploc_lay);
        any_pickup_edtx = v.findViewById(R.id.any_pickup_edtx);

        any_pickup_edtx.setText(StoredObjects.pickupval);

        any_pickup_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StoredObjects.tab_type ="creatadres";
                fragmentcallinglay(new FetchCurntLocatn());
            }
        });

    }

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ().replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();
    }



}
