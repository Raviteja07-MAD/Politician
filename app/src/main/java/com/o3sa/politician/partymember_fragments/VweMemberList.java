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
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.storedobjects.StoredObjects;

import java.util.ArrayList;
import java.util.HashMap;


public class VweMemberList extends Fragment {

    RecyclerView vwe_membrs_recycler;
    CustomRecyclerview customRecyclerview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.vwemembers,null,false );

        StoredObjects.page_type = "vwemembers";
        SideMenu.updatemenu(StoredObjects.page_type);
        try {
            SideMenu.title_txt.setText("Members List");
        }catch (Exception e){

        }

        initilization(v);
        return v;
    }

    private void initilization(View v) {

        vwe_membrs_recycler = (RecyclerView) v.findViewById( R.id.vwe_membrs_recycler );
        customRecyclerview = new CustomRecyclerview(getActivity());


        StoredObjects.hashmaplist(9);
        customRecyclerview.Assigndatatorecyleviewhashmap(vwe_membrs_recycler, StoredObjects.dummy_list,"vwemembers",
                StoredObjects.Gridview,3, StoredObjects.ver_orientation,R.layout.vwememberslstitem);


    }

}
