package com.meteosurf.meteosurf.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.meteosurf.meteosurf.R;
import com.meteosurf.meteosurf.common.Common;

public class InitialActivity extends AppCompatActivity {

    SharedPreferences config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        // Hide Toolbar
        getSupportActionBar().hide();

        // Obtain preferences
        config = getSharedPreferences(Common.SHARED_PREF, Context.MODE_PRIVATE);

        // Verify if login and spot selected
        redirect();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // Verify if login and spot selected
        redirect();

    }

    private void redirect() {

        // Verify if user is login
        if (config.getBoolean(Common.SHARED_PREF_LOGIN, false)) {
            // Verify if user have spot
            if (config.getBoolean(Common.SHARED_PREF_SPOT, false)) {
                Intent i = new Intent(InitialActivity.this, MainActivity.class);
                startActivity(i);
            } else {
                Intent i = new Intent(InitialActivity.this, SpotActivity.class);
                startActivity(i);
            }
        }

    }

    public void fntIniLoadLogin(View view) {

        Intent i = new Intent(InitialActivity.this, LoginActivity.class);
        startActivity(i);
        
    }

    public void fntIniLoadRegister(View view) {

        Intent i = new Intent(InitialActivity.this, RegisterActivity.class);
        startActivity(i);

    }
}
