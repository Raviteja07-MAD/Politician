package com.o3sa.politician.sub_member_fragments;

import android.graphics.Bitmap;
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
import androidx.recyclerview.widget.RecyclerView;

import com.o3sa.politician.R;
import com.o3sa.politician.customadapter.CustomRecyclerview;
import com.o3sa.politician.customfonts.CustomEditText;
import com.o3sa.politician.partymember_fragments.Home;
import com.o3sa.politician.servicesparsing.InterNetChecker;
import com.o3sa.politician.sidemenu.SideMenu_SubMember;
import com.o3sa.politician.sidemenu.SideMenu_user;
import com.o3sa.politician.storedobjects.StoredObjects;

import java.util.ArrayList;
import java.util.HashMap;

import static com.o3sa.politician.sidemenu.SideMenu_user.btm_map_img;
import static com.o3sa.politician.sidemenu.SideMenu_user.btm_map_lay;
import static com.o3sa.politician.sidemenu.SideMenu_user.btm_map_txt;

public class Askfordonation_submember extends Fragment {

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
    TextView title_txt;
    ImageView backbtn_img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate ( R.layout.askfor_donation, null , false);
        StoredObjects.page_type="Events";
        StoredObjects.back_type="Events";
        SideMenu_SubMember.updatemenu(StoredObjects.page_type);
        //buttonchangemethod(getActivity(), btm_map_lay, btm_map_img, btm_map_txt, "2");

        try{
            SideMenu_SubMember.buttonchangemethod (getActivity() , btm_map_lay, btm_map_img, btm_map_txt, "2");

        } catch (Exception e) {
            e.printStackTrace();
        }

        init (v);
        if (InterNetChecker.isNetworkAvailable(getActivity())) {
           // new GetRealtionTask().execute(StoredObjects.UserId);
        }else{
            StoredObjects.ToastMethod(getActivity().getResources().getString(R.string.checkinternet),getActivity());
        }

        return v;
    }

    private void init(View v) {

        title_txt = (TextView)v.findViewById( R.id. title_txt);
        backbtn_img = (ImageView)v.findViewById( R.id.backbtn_img );



        title_txt.setText( R.string.askfordonations );

        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentcallinglay( new Home_SubMember() );
            }
        } );

        try{
         //   UserSidemenu.cstm_srch_img.setVisibility(View.VISIBLE);
        }catch (Exception e){

        }




    }



    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
        fragmentManager.beginTransaction ().replace (R.id.frame_container , fragment).addToBackStack( "" ).commit ();

    }

}
