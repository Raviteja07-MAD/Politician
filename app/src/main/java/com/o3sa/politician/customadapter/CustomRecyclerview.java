package com.o3sa.politician.customadapter;

import android.app.Activity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.o3sa.politician.dumpdata.DumpData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kiran on 22-09-2018.
 */

public class CustomRecyclerview {

    Activity activity;
    public static HashMapRecycleviewadapter hashMapRecycleviewadapter;


    public CustomRecyclerview(Activity m_activity) {
        activity=m_activity;
    }

    public void Assigndatatorecyleviewhashmap(RecyclerView customrecyleview, ArrayList<HashMap<String, String>> arrayList, String type, String viewtype, int no_of_columns, int orientation, int recylerviewlistitem) {//ArrayList<HashMap<String, String>> arrayList

        if(viewtype.equalsIgnoreCase("Grid")){
            customrecyleview.setLayoutManager(new GridLayoutManager(activity, no_of_columns));
        }else{
            customrecyleview.setLayoutManager(new LinearLayoutManager(activity, orientation, false));
        }

        hashMapRecycleviewadapter = new HashMapRecycleviewadapter(activity,arrayList,type,customrecyleview,recylerviewlistitem);//
        customrecyleview.setAdapter(hashMapRecycleviewadapter);

    }

    public void Assigndatatorecyleviewhashmap123(RecyclerView customrecyleview, ArrayList<DumpData> arrayList, String type, String viewtype, int no_of_columns, int orientation, int recylerviewlistitem) {//ArrayList<HashMap<String, String>> arrayList

        if(viewtype.equalsIgnoreCase("Grid")){
            customrecyleview.setLayoutManager(new GridLayoutManager(activity, no_of_columns));
        }else{
            customrecyleview.setLayoutManager(new LinearLayoutManager(activity, orientation, false));
        }

        hashMapRecycleviewadapter = new HashMapRecycleviewadapter(activity,arrayList,type,customrecyleview,recylerviewlistitem,"dumpdata");//
        customrecyleview.setAdapter(hashMapRecycleviewadapter);

    }

}
