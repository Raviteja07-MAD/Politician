package com.o3sa.politician.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.o3sa.politician.R;
import com.o3sa.politician.customfonts.CustomButton;
import com.o3sa.politician.customfonts.CustomEditText;
import com.o3sa.politician.customfonts.CustomRegularTextView;
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.sidemenu.SideMenu_SubMember;
import com.o3sa.politician.sidemenu.SideMenu_user;
import com.o3sa.politician.storedobjects.StoredObjects;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Sign_in_Sign_up extends AppCompatActivity {

    CustomRegularTextView sign_in_text,sign_up_text,sgnin_frgtpswd_btn,sgnup_trmscndtn_txt;
    LinearLayout sign_in_layout,signup_layout;
    CustomButton sgnin_btn,sgnup_btn;
    CustomEditText sgnup_area_edtx,sgnup_brth_edtx,sgnup_grnder_edtx;
    TextInputEditText sgnin_email_edtx,sgnin_pswd_edtx;
    TextInputEditText sgnup_name_edtx,sgnup_email_edtx,sgnup_phne_edtx,sgnup_pswd_edtx,sgnup_cnfmpswd_edtx;

    private ListPopupWindow listPopupWindow,listPopupWindowone;
    String[] arealst = {"Lb Nagar","Uppal","Yamjal","Malkajgiri"};
    String[] gndrlst = {"Male","Female"};

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sign_in );
        listPopupWindow = new ListPopupWindow(Sign_in_Sign_up.this);
        listPopupWindowone = new ListPopupWindow(Sign_in_Sign_up.this);
        initialization();
    }

    private void initialization() {

        sign_in_text = findViewById( R.id.sign_in_text );
        sign_up_text = findViewById( R.id.sign_up_text );
        sign_in_layout = findViewById( R.id.sign_in_layout );
        signup_layout = findViewById( R.id.signup_layout );

        //signin
        sgnin_email_edtx = findViewById( R.id.sgnin_email_edtx );
        sgnin_pswd_edtx = findViewById( R.id.sgnin_pswd_edtx );
        sgnin_frgtpswd_btn = findViewById( R.id.sgnin_frgtpswd_btn );
        sgnin_btn = findViewById( R.id.sgnin_btn );

        //signup
        sgnup_name_edtx = findViewById( R.id.sgnup_name_edtx );
        sgnup_email_edtx = findViewById( R.id.sgnup_email_edtx );
        sgnup_phne_edtx = findViewById( R.id.sgnup_phne_edtx );
        sgnup_pswd_edtx = findViewById( R.id.sgnup_pswd_edtx );
        sgnup_cnfmpswd_edtx = findViewById( R.id.sgnup_cnfmpswd_edtx );
        sgnup_brth_edtx = findViewById( R.id.sgnup_brth_edtx );
        sgnup_grnder_edtx = findViewById( R.id.sgnup_grnder_edtx );
        sgnup_area_edtx = findViewById( R.id.sgnup_area_edtx );
        sgnup_trmscndtn_txt = findViewById( R.id.sgnup_trmscndtn_txt );
        sgnup_btn = findViewById( R.id.sgnup_btn );


        sgnup_trmscndtn_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Sign_in_Sign_up.this, TermsAndConditions.class);
                startActivity(intent);
            }
        });

        sign_in_text.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonchangemethod (Sign_in_Sign_up.this ,sign_in_layout, sign_in_text  ,"0");
            }
        } );

        sign_up_text.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonchangemethod (Sign_in_Sign_up.this ,signup_layout, sign_up_text  ,"1");
            }
        } );

        sgnin_frgtpswd_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Sign_in_Sign_up.this, Forgot_password.class));
            }
        } );

        sgnup_brth_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Sign_in_Sign_up.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                sgnup_brth_edtx.setText(day + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        sgnup_grnder_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GenderListPopup (sgnup_grnder_edtx);
            }
        });


        sgnup_area_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AreasListPopup (sgnup_area_edtx);
            }
        });

        sgnin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sgnin_email_edtx.getText().toString().equalsIgnoreCase("admin")){
                    StoredObjects.UserType="admin";
                    startActivity(new Intent(Sign_in_Sign_up.this, SideMenu.class));
                    Sign_in_Sign_up.this.finish();
                }

                else if (sgnin_email_edtx.getText().toString().equalsIgnoreCase("submember")){
                    StoredObjects.UserType="subuser";
                    startActivity(new Intent(Sign_in_Sign_up.this, SideMenu_SubMember.class));
                    Sign_in_Sign_up.this.finish();
                }

                else if (sgnin_email_edtx.getText().toString().equalsIgnoreCase("user")){
                    StoredObjects.UserType="user";
                    startActivity(new Intent(Sign_in_Sign_up.this, SideMenu_user.class));
                    Sign_in_Sign_up.this.finish();
                }
            }
        });
/*
        sgnin_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signin_email_id = sgnin_email_edtx.getText().toString();
                signin_password = sign_in_passwrd_edtxt.getText().toString();

                if (StoredObjects.inputValidation( sgnin_email_edtx, getApplicationContext().getResources().getString( R.string.enteremail ), Sign_in_Sign_up.this )) {
                    if (StoredObjects.inputValidation( sign_in_passwrd_edtxt, getApplicationContext().getResources().getString( R.string.enterpassword ), Sign_in_Sign_up.this )) {

                        StoredObjects.hide_keyboard( Sign_in_Sign_up.this );
                        if (InterNetChecker.isNetworkAvailable( Sign_in_Sign_up.this )) {
                            new Signin_Task().execute( signin_email_id, signin_password,StoredObjects.gcm_regid );

                        } else {
                            StoredObjects.ToastMethod( getApplicationContext().getResources().getString( R.string.checkinternet ), Sign_in_Sign_up.this );
                        }
                    }
                }
            }
        } );
*/


        sgnup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 startActivity(new Intent(Sign_in_Sign_up.this, OtpVerification.class));
            }
        });


/*
        sign_up_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               name = sign_up_name_edtxt.getText().toString();
                phone_number = sign_up_phone_edtxt.getText().toString();
                email_id = sign_up_email_edtxt.getText().toString();
                password = sign_up_password_edtxt.getText().toString();
                confrm_password = sign_up_cnfrmpsswrd_edtxt.getText().toString();

                if (StoredObjects.inputValidation( sign_up_name_edtxt, getApplicationContext().getResources().getString( R.string.entername ), Sign_in_Sign_up.this )) {
                    if (StoredObjects.inputValidation( sign_up_phone_edtxt, getApplicationContext().getResources().getString( R.string.entermobile ), Sign_in_Sign_up.this )) {
                        if (StoredObjects.inputValidation( sign_up_email_edtxt, getApplicationContext().getResources().getString( R.string.enteremail ), Sign_in_Sign_up.this )) {
                            if (StoredObjects.inputValidation( sign_up_password_edtxt, getApplicationContext().getResources().getString( R.string.enterpassword ), Sign_in_Sign_up.this )) {
                                if (StoredObjects.inputValidation( sign_up_cnfrmpsswrd_edtxt, getApplicationContext().getResources().getString( R.string.cnfrmpassword ), Sign_in_Sign_up.this )) {
                                    if (confrm_password.equals( password ) ) {
                                        StoredObjects.hide_keyboard( Sign_in_Sign_up.this );
                                        if (InterNetChecker.isNetworkAvailable( Sign_in_Sign_up.this )) {
                                            if(StoredObjects.isValidEmail(email_id)){

                                                new Signup_Task().execute( name, phone_number, email_id, password ,confrm_password );

                                            }else{
                                                StoredObjects.ToastMethod( getApplicationContext().getResources().getString( R.string.please_entervalidemail), Sign_in_Sign_up.this );
                                            }
                                        } else {
                                            StoredObjects.ToastMethod( getApplicationContext().getResources().getString( R.string.checkinternet ), Sign_in_Sign_up.this );
                                        }
                                    } else {
                                        StoredObjects.ToastMethod( "passwords not matched", Sign_in_Sign_up.this );
                                    }

                                }
                            }
                        }
                    }
                }
            }
        } );
*/

    }

    public void buttonchangemethod(Activity activity , LinearLayout layout1, TextView text1 , String type) {

        signup_layout.setVisibility( View.GONE );
        sign_in_layout.setVisibility( View.GONE );

        sign_in_text.setBackgroundResource( R.drawable.signin_white_btn_bg );
        sign_up_text.setBackgroundResource( R.drawable.signin_white_btn_bg );

        sign_up_text.setTextColor( getResources().getColor(R.color.form_text_color));
        sign_in_text.setTextColor( getResources().getColor(R.color.form_text_color));

        layout1.setVisibility( View.VISIBLE );
        text1.setTextColor (activity.getResources ().getColor (R.color.white));
        text1.setBackgroundResource( R.drawable.signin_btn_bg );
    }

    private void GenderListPopup(final CustomEditText prfilenme){
        listPopupWindow.setAdapter(new ArrayAdapter<>(Sign_in_Sign_up.this,R.layout.drpdwn_lay,gndrlst));
        listPopupWindow.setAnchorView(prfilenme);
        listPopupWindow.setHeight(LinearLayout.MarginLayoutParams.WRAP_CONTENT);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prfilenme.setText(gndrlst[position]);
                listPopupWindow.dismiss();

            }
        });

        listPopupWindow.show();
    }


    private void AreasListPopup(final CustomEditText prfilenme){
        listPopupWindowone.setAdapter(new ArrayAdapter<>(Sign_in_Sign_up.this,R.layout.drpdwn_lay,arealst));
        listPopupWindowone.setAnchorView(prfilenme);
        listPopupWindowone.setHeight(LinearLayout.MarginLayoutParams.WRAP_CONTENT);

        listPopupWindowone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                prfilenme.setText(arealst[position]);
                listPopupWindowone.dismiss();

            }
        });

        listPopupWindowone.show();
    }

}
