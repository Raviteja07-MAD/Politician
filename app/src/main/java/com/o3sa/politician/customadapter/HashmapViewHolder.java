package com.o3sa.politician.customadapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.o3sa.politician.Activities.Sign_in_Sign_up;
import com.o3sa.politician.R;
import com.o3sa.politician.circularimageview.CircularImageView;
import com.o3sa.politician.customfonts.CustomBoldTextView;
import com.o3sa.politician.customfonts.CustomEditText;
import com.o3sa.politician.customfonts.CustomMediumTextView;
import com.o3sa.politician.customfonts.CustomRegularTextView;
import com.o3sa.politician.partymember_fragments.Add_event;
import com.o3sa.politician.partymember_fragments.Add_member;
import com.o3sa.politician.partymember_fragments.Addpost;
import com.o3sa.politician.partymember_fragments.Changepassword;
import com.o3sa.politician.partymember_fragments.CreatAreas;
import com.o3sa.politician.partymember_fragments.Donation;
import com.o3sa.politician.partymember_fragments.EvntComentList;
import com.o3sa.politician.partymember_fragments.Privacy_Settings;
import com.o3sa.politician.partymember_fragments.Profile_member;
import com.o3sa.politician.partymember_fragments.Profile_member_admin;
import com.o3sa.politician.partymember_fragments.Profile_partymember;
import com.o3sa.politician.partymember_fragments.Test;
import com.o3sa.politician.partymember_fragments.View_donations;
import com.o3sa.politician.servicesparsing.InterNetChecker;
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.storedobjects.StoredObjects;
import com.o3sa.politician.storedobjects.StoredUrls;
import com.o3sa.politician.sub_member_fragments.Feed_innerpage_submember;
import com.o3sa.politician.user_fragments.Feed_innerpage_user;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by VINI on 20-03-2019.
 */

public class HashmapViewHolder extends RecyclerView.ViewHolder {

   private Activity activity;

    // feedpage
    ImageView post_img,landing_dots_image;
    CustomRegularTextView dscrption_txt;
    LinearLayout comment__lay,share__lay;

    // notifications
    CustomRegularTextView ntfc_profile_name_txt,ntfc_txt;
    CircularImageView landing_circular_img;

    // profile_followers
    CircularImageView following_circular_img;
    CustomRegularTextView profile_name_txt;

    //evntslist
    ImageView evnts_dots_image,evnts_glary_img;
    LinearLayout evnt_cmnt_layout;

    //setngs
   CustomMediumTextView title_list;
   LinearLayout setng_prflelist_lay;
   ImageView settings_icon;

   //mebers
   ImageView membrs_dots_image;
   LinearLayout mem_lay;

   // donations
    ImageView viewdonation_img;

    // comments
    CircularImageView commnt_profile_circular_img;
    CustomBoldTextView comment_profile_txt;
    CustomRegularTextView comment_txt,url_txt,title;
    LinearLayout comments_listitems_lay;

    public HashmapViewHolder(View convertView, String type, final Activity activity) {

        super(convertView);
        this.activity = activity;
        // database = new Database(activity);




        if (type.equalsIgnoreCase("feedpage")) {

            post_img = convertView.findViewById(R.id.post_img);
            landing_dots_image = convertView.findViewById(R.id.landing_dots_image);
            dscrption_txt = convertView.findViewById(R.id.dscrption_txt);
            comment__lay = convertView.findViewById(R.id.comment__lay);
            share__lay = convertView.findViewById(R.id.share__lay);

        }
        if (type.equalsIgnoreCase("Submember_feedpage")) {

            post_img = convertView.findViewById(R.id.post_img);
            landing_dots_image = convertView.findViewById(R.id.landing_dots_image);
            dscrption_txt = convertView.findViewById(R.id.dscrption_txt);

        }

        if (type.equalsIgnoreCase("notifications")) {

            ntfc_profile_name_txt = convertView.findViewById(R.id.ntfc_profile_name_txt);
            ntfc_txt = convertView.findViewById(R.id.ntfc_txt);
            landing_circular_img = convertView.findViewById(R.id.landing_circular_img);

        }

        if (type.equalsIgnoreCase("profile_followers")) {

            profile_name_txt = convertView.findViewById(R.id.profile_name_txt);
            following_circular_img = convertView.findViewById(R.id.following_circular_img);

        }

        if (type.equalsIgnoreCase("Followers")) {

            profile_name_txt = convertView.findViewById(R.id.profile_name_txt);
            following_circular_img = convertView.findViewById(R.id.following_circular_img);

        }

        if (type.equalsIgnoreCase("following")) {

            profile_name_txt = convertView.findViewById(R.id.profile_name_txt);
            following_circular_img = convertView.findViewById(R.id.following_circular_img);
        }
        if (type.equalsIgnoreCase("setngs")) {

            title_list = convertView.findViewById(R.id.settings_text);
            settings_icon = convertView.findViewById(R.id.settings_icon);
            setng_prflelist_lay  = convertView.findViewById(R.id.setng_prflelist_lay);
        }

        if (type.equalsIgnoreCase("evntslist")) {
            evnts_dots_image = convertView.findViewById(R.id.evnts_dots_image);
            evnts_glary_img = convertView.findViewById(R.id.evnts_glary_img);
            evnt_cmnt_layout = convertView.findViewById(R.id.evnt_cmnt_layout);
        }

        if (type.equalsIgnoreCase("areas_list")) {
            evnts_dots_image = convertView.findViewById(R.id.evnts_dots_image);
            evnts_glary_img = convertView.findViewById(R.id.evnts_glary_img);
            evnt_cmnt_layout = convertView.findViewById(R.id.evnt_cmnt_layout);
        }

        if (type.equalsIgnoreCase("evntslist_user")) {
            evnts_dots_image = convertView.findViewById(R.id.evnts_dots_image);
            evnts_glary_img = convertView.findViewById(R.id.evnts_glary_img);
            evnt_cmnt_layout = convertView.findViewById(R.id.evnt_cmnt_layout);
        }


        if (type.equalsIgnoreCase("mebers")) {
            membrs_dots_image = convertView.findViewById(R.id.membrs_dots_image);
            mem_lay = convertView.findViewById(R.id.mem_lay);
        }

        if (type.equalsIgnoreCase("donations")) {
            viewdonation_img = convertView.findViewById(R.id.viewdonation_img);
        }
        if (type.equalsIgnoreCase("comments")) {

            comment_profile_txt = convertView.findViewById(R.id.comment_profile_txt);
            commnt_profile_circular_img = convertView.findViewById(R.id.commnt_profile_circular_img);
            comment_txt = convertView.findViewById(R.id.comment_txt);
            comments_listitems_lay = convertView.findViewById(R.id.comments_listitems_lay);
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public void assign_data(final ArrayList<HashMap<String, String>> datalist, final int position, final String formtype) {

        if (formtype.equalsIgnoreCase("feedpage")) {

            dscrption_txt.setText(datalist.get(position).get("title"));
            post_img.setImageResource(Integer.parseInt(datalist.get(position).get("images")));

            post_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    fragmentcalling_comments(new Feed_innerpage_user(),datalist,position);
                }
            });

            comment__lay.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentcalling_comments(new Feed_innerpage_user(),datalist,position);
                }
            } );

            landing_dots_image.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EditDeleteConfirmation( activity, datalist, position ,"");

                }
            } );

            share__lay.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Sharepostpopup( activity, datalist, position, datalist.get( position ).get("post_id"),"" );

                   /* if (post_type.equalsIgnoreCase( "Direct Post" )){
                        Sharepostpopup( activity, datalist, position, datalist.get( position ).get("post_id"),"" );
                    }
                    else {
                        Sharepostpopup( activity, datalist, position, datalist.get( position ).get("original_post_id"),"" );
                    }*/

                }
            } );


        }

        if (formtype.equalsIgnoreCase("comments")) {
            // Collections.reverse(datalist);
            // comment_profile_txt.setText(datalist.get(position).get("name"));
            // comment_txt.setText( datalist.get( position).get( "comments" ) );
/*
            try {
                Glide.with(activity)
                        .load(Uri.parse(StoredUrls.Uploadedimages +datalist.get(position).get("customer_picture"))) // add your image url
                        .centerCrop() // scale to fill the ImageView and crop any extra
                        .fitCenter() // scale to fit entire image within ImageView
                        .placeholder(R.drawable.man)
                        .into(commnt_profile_circular_img);
            } catch (Exception e) {
            }
*/

/*
            commnt_profile_circular_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (datalist.get(position).get("customer_id").equalsIgnoreCase(StoredObjects.UserId)) {
                       // fragmentcalling(new Profile());
                    } else {
                        //fragmentcalling2(new Followers_profile(), datalist.get( position ).get( "customer_id"));
                    }
                }
            });
*/

            comments_listitems_lay.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    CommentEditDeleteConfirmation(activity, datalist, position);
                    return false;
                }
            });

           /* if (datalist.get( position ).get( "customer_id" ).equalsIgnoreCase(StoredObjects.UserId)){
                comments_listitems_lay.setOnLongClickListener( new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        CommentEditDeleteConfirmation( activity,datalist,position );
                        return false;
                    }
                } );
            }
            else {
                comments_listitems_lay.setOnLongClickListener( new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Toast.makeText( activity, "You are not suposed to edit/delete", Toast.LENGTH_SHORT ).show();
                        return false;
                    }
                } );
            }
        }*/
        }

        if (formtype.equalsIgnoreCase("Submember_feedpage")) {

            dscrption_txt.setText(datalist.get(position).get("title"));
            post_img.setImageResource(Integer.parseInt(datalist.get(position).get("images")));

            post_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    fragmentcalling_comments(new Feed_innerpage_submember(),datalist,position);
                }
            });

            landing_dots_image.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EditDeleteConfirmation( activity, datalist, position ,"");

                }
            } );


        }


        if (formtype.equalsIgnoreCase("notifications")) {

            ntfc_profile_name_txt.setText(datalist.get(position).get("title"));
            ntfc_txt.setText(datalist.get(position).get("notifictitle"));
            landing_circular_img.setImageResource(Integer.parseInt(datalist.get(position).get("images")));

        }

        if (formtype.equalsIgnoreCase("profile_followers")) {

            profile_name_txt.setText(datalist.get(position).get("title"));
            following_circular_img.setImageResource(Integer.parseInt(datalist.get(position).get("images")));

        }

        if (formtype.equalsIgnoreCase("Followers")) {

            profile_name_txt.setText(datalist.get(position).get("title"));
            following_circular_img.setImageResource(Integer.parseInt(datalist.get(position).get("images")));

        }

        if (formtype.equalsIgnoreCase("following")) {

            profile_name_txt.setText(datalist.get(position).get("title"));
            following_circular_img.setImageResource(Integer.parseInt(datalist.get(position).get("images")));
        }

        if (formtype.equalsIgnoreCase("setngs")) {

            title_list.setText((datalist.get(position).get("title")));

            if(position == 0){
                settings_icon.setImageResource(R.drawable.my_profile);
            } else if(position == 1){
                settings_icon.setImageResource(R.drawable.b_privacy_settings);
            } else if(position == 2){
                settings_icon.setImageResource(R.drawable.b_privacy_settings);
            } else if(position == 3){
                settings_icon.setImageResource(R.drawable.meeting);
            }   else if(position ==4){
                settings_icon.setImageResource(R.drawable.my_profile);
            }else if(position ==5){
                settings_icon.setImageResource(R.drawable.logout);
            }

            setng_prflelist_lay.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(position == 0){
                        fragmentcalling(new Test());
                    } else if(position == 1){
                        fragmentcalling(new Privacy_Settings());
                    } else if(position == 2){
                        fragmentcalling(new Changepassword());
                    } else if(position == 3){
                        StoredObjects.help_type="setnghelp";
                        fragmentcalling(new Donation());
                    }   else if(position ==4){
                        StoredObjects.pickupval = "";
                        fragmentcalling(new CreatAreas());
                    }
                    else if(position == 5) {
                        Logoutpopup(activity,"1");
                    }
                }
            } );

        }
        if (formtype.equalsIgnoreCase("evntslist_user")) {

            evnts_dots_image.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   // EditDeletepopup( activity, datalist, position ,"Events");

                }
            } );

            evnts_glary_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            evnt_cmnt_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fragmentcalling(new EvntComentList());
                }
            });


        }

        if (formtype.equalsIgnoreCase("areas_list")) {

            evnts_dots_image.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EditDeletepopup( activity, datalist, position ,"areas");

                }
            } );

            evnts_glary_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            evnt_cmnt_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fragmentcalling(new EvntComentList());
                }
            });


        }


        if (formtype.equalsIgnoreCase("evntslist")) {

            evnts_dots_image.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EditDeletepopup( activity, datalist, position ,"Events");

                }
            } );

            evnts_glary_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            evnt_cmnt_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fragmentcalling(new EvntComentList());
                }
            });


        }

        if (formtype.equalsIgnoreCase("mebers")) {

            mem_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fragmentcalling(new Profile_member());
                }
            });

            membrs_dots_image.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EditDeletepopup( activity, datalist, position ,"members");
                }
            } );
        }

        if (formtype.equalsIgnoreCase("donations")) {

            viewdonation_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    fragmentcalling(new View_donations());

                }
            });
        }

    }

    public void fragmentcalling(Fragment fragment) {
        FragmentManager fragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
    }


    public void fragmentcalling_comments(Fragment fragment, ArrayList<HashMap<String, String>> list, int position) {
        FragmentManager fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putSerializable("YourHashMap", list);
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction()/*.setCustomAnimations(R.anim.falldown, R.anim.falldown)*/.replace(R.id.frame_container, fragment).addToBackStack("").commit();
    }



    private void EditDeletepopup(final Activity activity, final ArrayList<HashMap<String, String>> list, final int position,String posttype) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.editdelete_confirmation );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);

        LinearLayout edit_layout = (LinearLayout)dialog.findViewById(R.id.edit_layout);
        LinearLayout delet_layout = (LinearLayout)dialog.findViewById(R.id.delet_layout);
        LinearLayout blockuser_layout = (LinearLayout)dialog.findViewById(R.id.blockuser_layout);
        LinearLayout hidepost_layout = (LinearLayout)dialog.findViewById(R.id.hidepost_layout);

        hidepost_layout.setVisibility(View.GONE);

        hidepost_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                if (InterNetChecker.isNetworkAvailable(activity)) {
                    //   new HidepostTask().execute(StoredObjects.UserId,list.get( position ).get("post_id"));
                    // updateqtydata(list,list.get( position ),position,"No","landingpage_hidepost");

                }else{
                    StoredObjects.ToastMethod(activity.getResources().getString(R.string.checkinternet),activity);
                }
            }
        });


        blockuser_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                if (InterNetChecker.isNetworkAvailable(activity)) {
                    //  new BlockUserTask().execute(list.get( position ).get( "customer_id" ));
                }else{
                    StoredObjects.ToastMethod(activity.getResources().getString(R.string.checkinternet),activity);
                }
            }
        });

        if (posttype.equalsIgnoreCase("members")){
            edit_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentcalling(new Add_member());
                    dialog.dismiss();
                }
            });
        }

             if (posttype.equalsIgnoreCase("Events")){

                 edit_layout.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         fragmentcalling(new Add_event());
                         dialog.dismiss();
                     }
                 });
            }

        if (posttype.equalsIgnoreCase("areas")){

            edit_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentcalling(new CreatAreas());
                    dialog.dismiss();
                }
            });
        }






/*        if (posttype.equalsIgnoreCase( "sharedpost_own" )){

            edit_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    *//*fragmentcalling1(new Addpost(),list,position);*//*
                   // Sharepostpopup( activity,list,position,list.get( position ).get( "original_post_id" ),"sharedpost");
                    dialog.dismiss();
                }
            });

        }*//*else{

            edit_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // fragmentcalling1(new Addpost(),list,position);
                    dialog.dismiss();
                }
            });
        }*/

        delet_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                if (InterNetChecker.isNetworkAvailable(activity)) {
                    // new DeletePostTask().execute(list.get( position ).get( "post_id" ));
                    // updateqtydata(list,list.get( position ),position,"No","landingpage_delete");
                }else{
                    StoredObjects.ToastMethod(activity.getResources().getString(R.string.checkinternet),activity);
                }

            }
        });



        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });



        dialog.show();
    }

    private void EditDeleteConfirmation(final Activity activity, final ArrayList<HashMap<String, String>> list, final int position,String posttype) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.editdelete_confirmation );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);

        LinearLayout edit_layout = (LinearLayout)dialog.findViewById(R.id.edit_layout);
        LinearLayout delet_layout = (LinearLayout)dialog.findViewById(R.id.delet_layout);
        LinearLayout blockuser_layout = (LinearLayout)dialog.findViewById(R.id.blockuser_layout);
        LinearLayout hidepost_layout = (LinearLayout)dialog.findViewById(R.id.hidepost_layout);


        hidepost_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                if (InterNetChecker.isNetworkAvailable(activity)) {
                 //   new HidepostTask().execute(StoredObjects.UserId,list.get( position ).get("post_id"));
                   // updateqtydata(list,list.get( position ),position,"No","landingpage_hidepost");

                }else{
                    StoredObjects.ToastMethod(activity.getResources().getString(R.string.checkinternet),activity);
                }
            }
        });


        blockuser_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                if (InterNetChecker.isNetworkAvailable(activity)) {
                  //  new BlockUserTask().execute(list.get( position ).get( "customer_id" ));
                }else{
                    StoredObjects.ToastMethod(activity.getResources().getString(R.string.checkinternet),activity);
                }
            }
        });



        edit_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 fragmentcalling(new Addpost());
                dialog.dismiss();
            }
        });

/*        if (posttype.equalsIgnoreCase( "sharedpost_own" )){

            edit_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    *//*fragmentcalling1(new Addpost(),list,position);*//*
                   // Sharepostpopup( activity,list,position,list.get( position ).get( "original_post_id" ),"sharedpost");
                    dialog.dismiss();
                }
            });

        }*//*else{

            edit_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // fragmentcalling1(new Addpost(),list,position);
                    dialog.dismiss();
                }
            });
        }*/

        delet_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                if (InterNetChecker.isNetworkAvailable(activity)) {
                   // new DeletePostTask().execute(list.get( position ).get( "post_id" ));
                   // updateqtydata(list,list.get( position ),position,"No","landingpage_delete");
                }else{
                    StoredObjects.ToastMethod(activity.getResources().getString(R.string.checkinternet),activity);
                }

            }
        });



        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });



        dialog.show();
    }


    public void Logoutpopup(final Activity activity, final String type) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.logooutpopup );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);

        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        Button ok_btn = (Button)dialog.findViewById(R.id.ok_btn);
        Button cancel_btn = (Button)dialog.findViewById(R.id.cancel_btn);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);
        CustomRegularTextView logout_txt = (CustomRegularTextView)dialog.findViewById( R.id.logout_txt );
        CustomRegularTextView exit_txt = (CustomRegularTextView)dialog.findViewById( R.id.exit_txt );

        if (type.equals( "1" )){
            logout_txt.setVisibility( View.VISIBLE );
        }
        else {
            exit_txt.setVisibility( View.VISIBLE );
            logout_txt.setVisibility( View.GONE );
        }

        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals( "1" )) {
                    //database.UpdateUserId( "0" );
                    // database.UpdateUsertype( "0" );
                    activity.finish();
                    Intent intent = new Intent( activity, Sign_in_Sign_up.class );
                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    activity.startActivity( intent );
                } else {
                    minimizeApp();
                }
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void minimizeApp() {
        activity.finish();
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(startMain);
    }
    private void CommentEditDeleteConfirmation(final Activity activity, final ArrayList<HashMap<String, String>> list, final int position) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.editdelete_confirmation );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);

        LinearLayout edit_layout = (LinearLayout)dialog.findViewById(R.id.edit_layout);
        LinearLayout delet_layout = (LinearLayout)dialog.findViewById(R.id.delet_layout);
        LinearLayout blockuser_layout = (LinearLayout)dialog.findViewById(R.id.blockuser_layout);
        LinearLayout hidepost_layout = (LinearLayout)dialog.findViewById(R.id.hidepost_layout);
        blockuser_layout.setVisibility(View.GONE);

        edit_layout.setVisibility(View.VISIBLE);
        delet_layout.setVisibility(View.VISIBLE);
        hidepost_layout.setVisibility(View.GONE);

      /*  if(list.get( position ).get( "customer_id" ).equalsIgnoreCase(StoredObjects.UserId)){
            edit_layout.setVisibility(View.VISIBLE);
            delet_layout.setVisibility(View.VISIBLE);
            hidepost_layout.setVisibility(View.GONE);

        }*/


        edit_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentEditpopup( activity,list,position );
                dialog.dismiss();
            }
        });
        delet_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                if (InterNetChecker.isNetworkAvailable(activity)) {
                   // new DeleteCommentTask().execute(getposdetailslist.get( 0 ).get( "post_id" ),list.get( position ).get( "id" ));
                    // Collections.reverse(list);
                    //updateqtydata(list,list.get( position ),position,"No","comment_delete");
                }else{
                    StoredObjects.ToastMethod(activity.getResources().getString(R.string.checkinternet),activity);
                }

            }
        });

        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }
    CustomEditText commnt_edittxt;
    private void CommentEditpopup(final Activity activity, final ArrayList<HashMap<String, String>> list, final int position) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.editcommentpopup );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);

        commnt_edittxt = (CustomEditText)dialog.findViewById(R.id.commnt_edittxt);
        ImageView cmntsend_btn = (ImageView)dialog.findViewById(R.id.cmntsend_btn);

        if (commnt_edittxt.hasFocus()) {
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }


        commnt_edittxt.setText(list.get( position ).get( "comments" ) );
        commnt_edittxt.requestFocus();
        dialog.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        cmntsend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                if (InterNetChecker.isNetworkAvailable(activity)) {
                  //  new EditCommentTask().execute(getposdetailslist.get( 0 ).get( "post_id" ),list.get( position ).get( "id" ),commnt_edittxt.getText().toString());
                    // Collections.reverse(list);
                   // updateqtydata(list,list.get(position),position,commnt_edittxt.getText().toString(),"editcomment");
                }else{
                    StoredObjects.ToastMethod(activity.getResources().getString(R.string.checkinternet),activity);
                }
            }
        });
        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void Sharepostpopup(final Activity activity,final ArrayList<HashMap<String, String>> list, final int position, final String post_id,String type) {
        final Dialog dialog = new Dialog(activity);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sharepost_confirmation );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialog.getWindow().setElevation(20);
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout cancel_lay = (LinearLayout)dialog.findViewById(R.id.cancel_lay);
        ImageView canclimg = (ImageView)dialog.findViewById(R.id.canclimg);
        CustomRegularTextView sharenow_text = (CustomRegularTextView)dialog.findViewById( R.id.sharenow_text );
        CircularImageView profile_circular_img = (CircularImageView)dialog.findViewById( R.id.profile_circular_img );
        CustomRegularTextView profile_name_txt = (CustomRegularTextView)dialog.findViewById( R.id.profile_name_txt );
        CustomRegularTextView share_date_time_txt = (CustomRegularTextView)dialog.findViewById( R.id.share_date_time_txt );
        final CustomEditText commnt_edittxt = (CustomEditText)dialog.findViewById( R.id.commnt_edittxt );

/*
        if (type.equalsIgnoreCase( "sharedpost" )){
            commnt_edittxt.setText( list.get( position ).get( "shared_text" ) );
            commnt_edittxt.requestFocus();
            dialog.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

            try {
                profile_name_txt.setText(list.get( position ).get( "shared_customer" ) );
*/
/*
                Glide.with( activity )
                        .load( Uri.parse( StoredUrls.Uploadedimages + list.get( position ).get( "shared_customer_profile_picture" )  ) ) // add your image url
                        .centerCrop() // scale to fill the ImageView and crop any extra
                        .fitCenter() // scale to fit entire image within ImageView
                        .placeholder( R.drawable.man )
                        .into( profile_circular_img );
*//*

               // sharenow_text.setText( "Edit shared post" );
                sharenow_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        if (InterNetChecker.isNetworkAvailable(activity)) {
                            //new SharePostTask().execute(post_id,commnt_edittxt.getText().toString());
                           // new EditPostTask().execute(list.get( position ).get("shared_by_customer_id"), list.get( position ).get( "post_id" ), commnt_edittxt.getText().toString());

                        }else{
                            StoredObjects.ToastMethod(activity.getResources().getString(R.string.checkinternet),activity);
                        }

                    }
                });


            } catch (Exception e) {
            }

        }else {
            try {
                commnt_edittxt.requestFocus();
                dialog.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                profile_name_txt.setText(SideMenu.getprofilelist.get( 0 ).get( "name" ) );
*/
/*
                Glide.with( activity )
                        .load( Uri.parse( StoredUrls.Uploadedimages + SideMenu.getprofilelist.get( 0 ).get( "image" )  ) ) // add your image url
                        .centerCrop() // scale to fill the ImageView and crop any extra
                        .fitCenter() // scale to fit entire image within ImageView
                        .placeholder( R.drawable.man )
                        .into( profile_circular_img );
*//*


                sharenow_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        if (InterNetChecker.isNetworkAvailable(activity)) {
                           // new SharePostTask().execute(post_id,commnt_edittxt.getText().toString());
                        }else{
                            StoredObjects.ToastMethod(activity.getResources().getString(R.string.checkinternet),activity);
                        }

                    }
                });

            } catch (Exception e) {
            }
*/


        //share_date_time_txt.setText( list.get( position ).get( "created_at") );


        sharenow_text.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 dialog.dismiss();
                                             }
                                         });

        cancel_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        canclimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


        dialog.show();
    }

}

