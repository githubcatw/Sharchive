package com.nudev.sharchive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Toast;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void openArchive(View view) {
        // Get the view's ID and input field
        EditText et = findViewById(R.id.editTextURL);
        int id = view.getId();
        String url = et.getText().toString();
        // Return if no text is entered
        if (url.equals("")) {
            Toast.makeText(this, "Please enter a URL", Toast.LENGTH_SHORT).show();
            return;
        }
        // Return if entered text isn't a URL
        if (!URLUtil.isValidUrl(url)) {
            Toast.makeText(this, "Not a URL", Toast.LENGTH_SHORT).show();
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