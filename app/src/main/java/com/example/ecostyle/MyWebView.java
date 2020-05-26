package com.example.ecostyle;

import android.content.Intent;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MyWebView   extends AppCompatActivity {

    private WebView webView;
    public String pactUrl= "https://wearpact.com/about";
    public String womnUrl= "https://womn.co/";
    public String reformationUrl= "https://www.thereformation.com/pages/sustainable-practices";
    public String everlaneUrl= "https://www.everlane.com/about";
    public String bodenUrl= "https://www.bodenusa.com/en-us";
    public String ableUrl= "https://www.livefashionable.com/pages/about-able";
    public String tradlandsUrl= "https://tradlands.com/pages/about-us";
    public String outerknownUrl= "https://www.outerknown.com/pages/sustainability";
    public String pataUrl= "https://www.patagonia.com/sustainable-apparel-coalition.html";
    public String madewellUrl= "https://www.madewell.com/do-well-initiatives.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent mIntent = getIntent();
        int cost = mIntent.getIntExtra("cost", 0);
        if(cost == 1) {
            popWeb(pactUrl);
        }
        else if (cost == 2){
            popWeb(womnUrl);
        }
        else if (cost == 3){
            popWeb(ableUrl);
        }
        else if (cost == 4){
            popWeb(everlaneUrl);
        }
        else if (cost == 5){
            popWeb(pataUrl);
        }
        else if (cost == 6){
            popWeb(madewellUrl);
        }
        else if (cost == 7){
            popWeb(outerknownUrl);
        }
        else if (cost == 8){
            popWeb(tradlandsUrl);
        }
        else if (cost == 9){
            popWeb(bodenUrl);
        }
        else if (cost == 10){
            popWeb(reformationUrl);
        }
    }

    public void popWeb(String url){
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(url);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.77),(int)(height*0.77));
    }
}