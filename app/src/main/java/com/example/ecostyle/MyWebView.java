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
    public String petiteUrl= "https://petitestudionyc.com/?location=us";
    public String elieenUrl= "https://www.eileenfisher.com/";
    public String maraUrl= "https://marahoffman.com/";
    public String alternativeUrl= "https://www.alternativeapparel.com/";
    public String kotnUrl= "https://kotn.com/";
    public String leviUrl= "https://www.levi.com/US/en_US/";
    public String lacausaUrl= "https://www.lacausaclothing.com/";
    public String vettaUrl= "https://www.vettacapsule.com/";
    public String cuyanaUrl= "https://www.cuyana.com/";
    public String backbeatUrl= "https://backbeat.co/";
    public String nisoloUrl= "https://nisolo.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent mIntent = getIntent();
        String name = mIntent.getStringExtra("name");
        if(name.contains("Pact")) {
            popWeb(pactUrl);
        }
        else if (name.contains("womn")){
            popWeb(womnUrl);
        }
        else if (name.contains("Able")){
            popWeb(ableUrl);
        }
        else if (name.contains("Everlan")){
            popWeb(everlaneUrl);
        }
        else if (name.contains("Patagonia")){
            popWeb(pataUrl);
        }
        else if (name.contains("Petite Studio")){
            popWeb(petiteUrl);
        }
        else if (name.contains("Outerknown")){
            popWeb(outerknownUrl);
        }
        else if (name.contains("Tradlands")){
            popWeb(tradlandsUrl);
        }
        else if (name.contains("Boden")){
            popWeb(bodenUrl);
        }
        else if (name.contains("Levi")){
            popWeb(leviUrl);
        }
        else if (name.contains("Eileen Fisher")){
            popWeb(elieenUrl);
        }
        else if (name.contains("Mara Hoffman")){
            popWeb(maraUrl);
        }
        else if (name.contains("Alternative Apparel")){
            popWeb(alternativeUrl);
        }
        else if (name.contains("Kotn")){
            popWeb(kotnUrl);
        }
        else if (name.contains("LACAUSA")){
            popWeb(lacausaUrl);
        }
        else if (name.contains("Vetta")){
            popWeb(vettaUrl);
        }
        else if (name.contains("Cuyana")){
            popWeb(cuyanaUrl);
        }
        else if (name.contains("Back Beat Co")){
            popWeb(backbeatUrl);
        }
        else if (name.contains("Nisolo")){
            popWeb(nisoloUrl);
        }
        else if (name.contains("Reformation")){
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