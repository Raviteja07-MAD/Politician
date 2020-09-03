package com.o3sa.politician.servicesparsing;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by android-4 on 1/25/2018.
 */

public class ProgressBarClass {

    public static ProgressDialog progressDialog;

    public static Dialog dialog;

    public static void Progressbarshow(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

    }
    public static void Progressbarcancel(Context context) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


}
