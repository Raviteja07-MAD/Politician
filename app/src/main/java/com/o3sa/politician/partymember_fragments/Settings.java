package com.o3sa.politician.partymember_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.o3sa.politician.R;
import com.o3sa.politician.customadapter.CustomRecyclerview;
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.storedobjects.StoredObjects;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment {

  RecyclerView setng_recyler;
  CustomRecyclerview customRecyclerview;
  String[] title_list = {"Profile", "Privacy Settings", "Change Password", "Help/Complaints", "Create Areas","Logout"};
  public ArrayList<HashMap<String, String>> settings_list = new ArrayList<>();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.settings, container, false);
    customRecyclerview = new CustomRecyclerview(getActivity());
    StoredObjects.page_type = "setngs";
    /*SideMenu.updatemenu(StoredObjects.page_type);
    try {
      SideMenu.title_txt.setText("Settings");
    }catch (Exception e){

    }*/
    initilization(view);
    return view;
  }

  private void initilization(View view) {

    setng_recyler = view.findViewById(R.id.setng_recyler);

    settings_list.clear();
    for (int i = 0;i<title_list.length;i++){
      HashMap<String,String> hashMap = new HashMap<>();
      hashMap.put("title",title_list[i]);
      settings_list.add(hashMap);
    }

    customRecyclerview.Assigndatatorecyleviewhashmap(setng_recyler, settings_list,"setngs", StoredObjects.Listview, 0, StoredObjects.ver_orientation, R.layout.settings_list_item);

  }

  public void fragmentcallinglay(Fragment fragment) {
    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

  }
}