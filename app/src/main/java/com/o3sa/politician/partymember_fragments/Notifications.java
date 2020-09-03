package com.o3sa.politician.partymember_fragments;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.o3sa.politician.R;
import com.o3sa.politician.customadapter.CustomRecyclerview;
import com.o3sa.politician.customfonts.CustomButton;
import com.o3sa.politician.customfonts.CustomRegularTextView;
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.storedobjects.StoredObjects;

import java.util.ArrayList;
import java.util.HashMap;

import static com.o3sa.politician.sidemenu.SideMenu.btm_notifications_img;
import static com.o3sa.politician.sidemenu.SideMenu.btm_notifications_lay;
import static com.o3sa.politician.sidemenu.SideMenu.btm_notifications_txt;
import static com.o3sa.politician.sidemenu.SideMenu.buttonchangemethod;


public class Notifications extends Fragment {

    RecyclerView notifications_recyclerview;
    CustomRecyclerview customRecyclerview;
    LinearLayout send_ntfcn_lay;

    String[] title_list  = {"Harish Rao","K.T.Rama Rao","G Kishan Reddy","Bandaru Dattatreya MP","Harish Rao"};
    String[] notifcmessge_list  = {"Started following you ","Liked your post","Posted a post","Liked your post","Liked your post"};
    String[] c_settingslist  = {String.valueOf(R.drawable.hrshrao),String.valueOf(R.drawable.dattatraya),String.valueOf(R.drawable.kishanreddy),
            String.valueOf(R.drawable.dattatraya),String.valueOf(R.drawable.hrshrao)};

    public ArrayList<HashMap<String, String>> setngs_profilelist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.notifications,null,false );
        StoredObjects.page_type = "ntfctn";

       /* try {
            SideMenu.updatemenu(StoredObjects.page_type);
            SideMenu.title_txt.setText("Notifications");
        }catch (Exception e){

        }*/

        initilization(v);
        return v;
    }

    private void initilization(View v) {

        send_ntfcn_lay = v.findViewById( R.id.send_ntfcn_lay);
        notifications_recyclerview = (RecyclerView) v.findViewById( R.id.notifications_recyclerview );
        customRecyclerview = new CustomRecyclerview(getActivity());

        CustomRegularTextView title_txt = v.findViewById(R.id.title_txt);
        title_txt.setText("Notifications");
        ImageView backbtn_img = (ImageView)v.findViewById( R.id.backbtn_img );
        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //fragmentcallinglay(new Home());

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        } );

        send_ntfcn_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Notificationpopup(getActivity());

            }
        });

        setngs_profilelist.clear();
        for (int i = 0;i<title_list.length;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("title",title_list[i]);
            hashMap.put("notifictitle",notifcmessge_list[i]);
            hashMap.put("images",c_settingslist[i]);
            setngs_profilelist.add(hashMap);

        }

        StoredObjects.hashmaplist(6);
        customRecyclerview.Assigndatatorecyleviewhashmap(notifications_recyclerview, setngs_profilelist,"notifications", StoredObjects.Listview, 0, StoredObjects.ver_orientation, R.layout.notifications_listitems);
    }

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
    }

    private void Notificationpopup(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.notificationpopup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ImageView ntfcn_cancel_img = (ImageView)dialog.findViewById(R.id.ntfcn_cancel_img);
        CustomButton ntfcn_send_btn = (CustomButton)dialog.findViewById(R.id.ntfcn_send_btn);


        ntfcn_cancel_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        ntfcn_send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
