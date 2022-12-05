package com.nudev.sharchive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void openArchive(View view) {
        // Get the view's ID and input field
        TextInputLayout et = findViewById(R.id.editTextURL);
        int id = view.getId();
        String url = et.getEditText().getText().toString();
        // Return if no text is entered
        if (url.equals("")) {
            Toast.makeText(this, R.string.no_url, Toast.LENGTH_SHORT).show();
            return;
        }
        // Add http if necessary
        if (!url.startsWith("http")) url = "http://" + url;
        // Return if entered text isn't a URL
        if (!URLUtil.isValidUrl(url) || !url.contains(".")) {
            Toast.makeText(this, R.string.not_a_url, Toast.LENGTH_SHORT).show();
            return;
        }
        // Select the correct activity
        Intent intent = null;
        if (id == R.id.orgButton)
            intent = new Intent(this, ArchiveOrg.class);
        else if (id == R.id.aisButton)
            intent = new Intent(this, ArchiveToday.class);
        else if (id == R.id.gwcButton)
            intent = new Intent(this, GoogleWebCache.class);
        assert intent != null;
        // Add parameter and action
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        // Launch
        startActivity(intent);
    }
}