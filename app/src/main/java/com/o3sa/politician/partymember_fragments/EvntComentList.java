package com.o3sa.politician.partymember_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
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
import com.o3sa.politician.sidemenu.SideMenu_user;
import com.o3sa.politician.storedobjects.StoredObjects;

import static com.o3sa.politician.sidemenu.SideMenu.btm_log_catch_img;
import static com.o3sa.politician.sidemenu.SideMenu.btm_log_catch_lay;
import static com.o3sa.politician.sidemenu.SideMenu.btm_log_catch_txt;

public class EvntComentList extends Fragment {

    RecyclerView evnt_cmnt_recycle;
    CustomRecyclerview customRecyclerview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.evnt_coment_list,null,false );
        customRecyclerview = new CustomRecyclerview(getActivity());
        StoredObjects.page_type = "cmntlist";
       /* SideMenu.updatemenu(StoredObjects.page_type);
        try {
            SideMenu.title_txt.setText("Comments List");
        }catch (Exception e){

        }*/
        initilization(v);
        return v;
    }

    private void initilization(View v) {
        CustomRegularTextView title_txt = v.findViewById(R.id.title_txt);
        title_txt.setText("Comments List");
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
        evnt_cmnt_recycle = v.findViewById(R.id.evnt_cmnt_recycle);

        StoredObjects.hashmaplist(4);
        customRecyclerview.Assigndatatorecyleviewhashmap(evnt_cmnt_recycle, StoredObjects.dummy_list,"cmntslstvwe", StoredObjects.Listview, 0, StoredObjects.ver_orientation, R.layout.comments_listitems);

    }

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace( R.id.frame_container, fragment ).addToBackStack( "" ).commit();

    }

}