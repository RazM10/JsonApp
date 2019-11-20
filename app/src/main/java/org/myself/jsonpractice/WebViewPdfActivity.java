package org.myself.jsonpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebViewPdfActivity extends AppCompatActivity {

    WebView webView_pdf;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_pdf);

        webView_pdf=findViewById(R.id.webView_pdf);
        progressBar=findViewById(R.id.progressBar);

        WebSettings ws=webView_pdf.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAppCacheEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setDatabaseEnabled(true);
        ws.setLoadWithOverviewMode(true);
        ws.setUseWideViewPort(true);

        String url=getIntent().getStringExtra("url");

        webView_pdf.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Toast.makeText(getApplicationContext(), "Page loading start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                webView_pdf.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Page loaded", Toast.LENGTH_SHORT).show();
            }
        });
        webView_pdf.loadUrl(url);
    }
}
