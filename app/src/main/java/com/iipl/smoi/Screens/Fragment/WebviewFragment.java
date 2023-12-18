package com.iipl.smoi.Screens.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;


public class WebviewFragment extends Fragment {

    ImageView img_back;
    String string_label;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);

        img_back = view.findViewById(R.id.img_back);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            string_label = bundle.getString("string_label");
        }

        WebView webView = view.findViewById(R.id.web);

        webView.loadUrl(APIClient.getClient().baseUrl() + "/qr_code/index/" + string_label);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        return view;
    }
}