package com.o3sa.politician.partymember_fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
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
import com.o3sa.politician.customfonts.CustomEditText;
import com.o3sa.politician.customfonts.CustomRegularTextView;
import com.o3sa.politician.storedobjects.StoredObjects;

import java.util.ArrayList;
import java.util.HashMap;

public class Areaslist extends Fragment {

    CustomRecyclerview customRecyclerview;
    public static ArrayList<HashMap<String,String>> relationlist=new ArrayList<>();
    //capture camera
    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    // key to store image path in savedInstance state
    public static final String KEY_IMAGE_STORAGE_PATH = "image_path";
    public static final int MEDIA_TYPE_IMAGE = 1;
    // Bitmap sampling size
    public static final int BITMAP_SAMPLE_SIZE = 8;
    // Gallery directory name to store the images or videos
    public static final String GALLERY_DIRECTORY_NAME = "ProfileImages";
    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";
    private static String imageStoragePath;

    protected static final int SELECT_FILE = 1;
    private static final int PIC_CROP = 3;
    String image_str="";
    private Bitmap myImg;
    private String fileName;
    String file_name;
    public static RecyclerView kidpick_recycle;
    public static LinearLayout nodata_found_txt;
    CustomEditText parent_photo_edtxt;
    LinearLayout addevent_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate ( R.layout.areas, null , false);
        customRecyclerview = new CustomRecyclerview(getActivity());
        StoredObjects.page_type = "evnts";

        init (v);
        return v;
    }

    private void init(View v) {

        CustomRegularTextView title_txt = v.findViewById(R.id.title_txt);
        title_txt.setText("Areas");
        ImageView backbtn_img = (ImageView)v.findViewById( R.id.backbtn_img );
        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              // fragmentcallinglay(new Home());

                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        } );

        kidpick_recycle= v.findViewById(R.id.kidpick_recycle);
        addevent_btn = v.findViewById( R.id.addevent_btn );

        nodata_found_txt = (LinearLayout) v.findViewById(R.id.nodata_found_txt);

        StoredObjects.hashmaplist(4);
        customRecyclerview.Assigndatatorecyleviewhashmap(kidpick_recycle, StoredObjects.dummy_list,"areas_list", StoredObjects.Listview,0, StoredObjects.ver_orientation, R.layout.areaslisitem);

        addevent_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoredObjects.pickupval = "";
                fragmentcallinglay( new CreatAreas() );
            }
        } );

    }

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }

}
