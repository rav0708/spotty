package com.example.spotty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

//This class is used to let the user provide a space to preview their uploaded wall image with the chosen desired colour
public class samplepreview extends AppCompatActivity {
    Button preview1;
    private WebView wv1;

    WebView ourBrows;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samplepreview);
        getSupportActionBar().hide(); //to hide the action bar - name of the app

        preview1=(Button)findViewById(R.id.preview1);

        wv1=(WebView)findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());


        preview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://storage.cloud.google.com/spotty-69438.appspot.com/final_new.jpg";
                //String url= "https://storage.googleapis.com/spotty-69438.appspot.com/final_new.jpg";

                    
                wv1.getSettings().setLoadsImagesAutomatically(true);
                wv1.getSettings().setJavaScriptEnabled(true);
                wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                wv1.loadUrl(url);
            }
        });
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}