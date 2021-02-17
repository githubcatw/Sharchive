package com.nudev.sharchive.preserve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class PreserveArchiveOrg extends AppCompatActivity {

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
                Toast.makeText(getApplicationContext(), "The archival page loads longer than the view page", Toast.LENGTH_LONG);
                // Get the text shared with the app
                String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                // If the text is a valid URL:
                if (sharedText != null && URLUtil.isValidUrl(sharedText)) {
                    // Create a new WebView
                    WebView webView = new WebView(this);
                    // Enable JavaScript
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    // Load the Wayback Machine
                    webView.loadUrl("https://web.archive.org/save/" + sharedText);
                    // Set the content view to the new WebView
                    setContentView(webView);
                }
            }
        }
    }
}
