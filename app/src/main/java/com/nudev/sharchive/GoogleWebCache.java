package com.nudev.sharchive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class GoogleWebCache extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        // If we are responding to a Send action:
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            // If the action hase a MIME type of plain text:
            if ("text/plain".equals(type)){
                // Get the text shared with the app
                String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                // If the text is a valid URL:
                if (sharedText != null && URLUtil.isValidUrl(sharedText)) {
                    // Create a new WebView
                    WebView webView = new WebView(this);
                    // Enable JavaScript
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    // Load Google Web Cache
                    webView.loadUrl("http://webcache.googleusercontent.com/search?q=cache:" + sharedText);
                    // Set the content view to the new WebView
                    setContentView(webView);
                }
            }
        }
    }
}