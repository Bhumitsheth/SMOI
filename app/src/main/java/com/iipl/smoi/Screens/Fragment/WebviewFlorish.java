package com.iipl.smoi.Screens.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;

public class WebviewFlorish extends Fragment {


    ImageView img_back;
    String url;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_webview_florish, container, false);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            url = bundle.getString("url");
        }

        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        startActivity(browserIntent);

        WebView webView = view.findViewById(R.id.webview);
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebChromeClient(new WebChromeClient());
//        webView.setWebViewClient(new WebViewClient());



        return view;
    }


}