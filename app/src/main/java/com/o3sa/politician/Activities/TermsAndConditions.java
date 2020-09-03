package com.o3sa.politician.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.o3sa.politician.R;


public class TermsAndConditions extends Activity {
    ImageView cstm_srch_img;

    public ProgressBar progress;
    WebView wv;
    public static String weblink = "http://o3sa.co.in/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_contionspage);
        //StoredObjects.pagetype="termsandconditions";
        initialisation();

    }

    public void initialisation() {

       // cstm_srch_img = findViewById(R.id.cstm_srch_img);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        progress = (ProgressBar)findViewById(R.id.progress_privacy);
        progress.setVisibility(View.GONE);
        wv = (WebView) findViewById(R.id.web_privacy);
        wv.setWebViewClient(new MyWebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);

        wv.clearCache(true);
        wv.clearHistory();
        wv.loadUrl(weblink);
        wv.setVerticalScrollBarEnabled(true);

/*
        cstm_srch_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
*/

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //  view.loadUrl(url);


            if (url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                startActivity(intent);
                return true;
            } else if (url.startsWith("mailto:")) {
                url = url.substring(7);
                String body = "Body of message.";
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.setType("application/octet-stream");
                mail.putExtra(Intent.EXTRA_EMAIL, new String[] { url });
                mail.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                mail.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(mail);
                return true;
            } else if (url.startsWith("map:")){
                url = url.substring(4);
                String map = "http://maps.google.com/maps?q=" + url;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                startActivity(intent);
                return true;
            }else if (url.startsWith("www.messenger.com")){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
            else if (url.startsWith("https://m.facebook.com")){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }

            else if(url.startsWith("http:") || url.startsWith("https:")) {
                // view.loadUrl(url);

                //    if(url.contains("http://getright.pro/daisho/")||url.contains("https://getright.pro/daisho/")) {
                Log.i("<><<><>>","<><><><><><><><><><><><>"+url);
                view.loadUrl(url);
                //  }

            }


            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progress.setVisibility(View.GONE);
            progress.setProgress(100);
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progress.setVisibility(View.VISIBLE);
            progress.setProgress(0);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedSslError(WebView v, final SslErrorHandler handler, SslError er){
            // first check certificate with our truststore
            // if not trusted, show dialog to user
            // if trusted, proceed
            try {
                final AlertDialog.Builder builder = new AlertDialog.Builder(TermsAndConditions.this);
                builder.setMessage("Permission for SSL Certificate");
                builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.show();
            }catch (Exception e){

            }
        }



    }

}
