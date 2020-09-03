package com.o3sa.politician.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.o3sa.politician.R;
import com.o3sa.politician.customfonts.CustomButton;
import com.o3sa.politician.customfonts.CustomEditText;
import com.o3sa.politician.customfonts.CustomRegularTextView;
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.sidemenu.SideMenu_user;


public class OtpVerification extends AppCompatActivity {

    CustomButton submit_otp_btn;
    TextInputEditText enter_otp_etxt;
    String otpnumber="";
    String mobilenumber,otptext;
    CustomRegularTextView title_txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.otp);

        Intent intent = getIntent();
        if(intent!= null){
            mobilenumber = intent.getStringExtra("mobilenumber");
            otptext = intent.getStringExtra("otptext");
        }

        init();
    }

    private void init() {

        submit_otp_btn = findViewById (R.id.submit_otp_btn);
        enter_otp_etxt = findViewById (R.id.enter_otp_etxt);

        title_txt = findViewById(R.id.title_txt);
        title_txt.setText("Otp Verification");
        ImageView backbtn_img = (ImageView)findViewById( R.id.backbtn_img );
        backbtn_img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        } );

        TextView title_txt = (TextView)findViewById( R.id. title_txt);
        title_txt.setText( "OTP Verification" );

        submit_otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // startActivity(new Intent(OtpVerification.this, SideMenu_user.class));
               // OtpVerification.this.finish();

                finish();

            }
        });

/*
        submit_otp_btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                otpnumber = enter_otp_etxt.getText().toString();
                if(StoredObjects.inputValidation(enter_otp_etxt,getApplicationContext().getResources().getString(R.string.entermobile),OtpVerification.this))
                {
                    StoredObjects.hide_keyboard(OtpVerification.this);
                    if(otpnumber.equalsIgnoreCase(otptext)){
                        Intent intent = new Intent(OtpVerification.this, AdminSetpasswrd.class);
                        intent.putExtra("mobilenumber",mobilenumber);
                        startActivity(intent);
                    }else {
                        StoredObjects.ToastMethod("You Have Entered Invalid OTP Number",OtpVerification.this);
                    }

                }
               */



    }


}
