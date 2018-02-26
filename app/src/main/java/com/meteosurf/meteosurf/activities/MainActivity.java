package com.meteosurf.meteosurf.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.meteosurf.meteosurf.R;
import com.meteosurf.meteosurf.api.ApiError;
import com.meteosurf.meteosurf.api.CommentBody;
import com.meteosurf.meteosurf.api.Response;
import com.meteosurf.meteosurf.common.Common;
import com.meteosurf.meteosurf.fragments.CommentList;
import com.meteosurf.meteosurf.fragments.CurrentFragment;
import com.meteosurf.meteosurf.fragments.ForecastList;
import com.meteosurf.meteosurf.fragments.NewCommentDialog;
import com.meteosurf.meteosurf.interfaces.MeteoSurfApi;
import com.meteosurf.meteosurf.interfaces.OnNewCommentDialog;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnNewCommentDialog {

    SharedPreferences config;
    private NavigationView navigationView;
    DialogFragment newComment;
    private Retrofit restAdapter;
    private MeteoSurfApi meteoSurfApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshActivity();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Obtain preferences
        config = getSharedPreferences(Common.SHARED_PREF, Context.MODE_PRIVATE);

        // Create connection to Rest service
        restAdapter = new Retrofit.Builder()
                .baseUrl(MeteoSurfApi.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create connection to the API
        meteoSurfApi = restAdapter.create(MeteoSurfApi.class);

        // Refresh Activity
        refreshActivity();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // Refresh Activity
        refreshActivity();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment f = null;

        if (id == R.id.nav_current) {
            f = new CurrentFragment();
        } else if (id == R.id.nav_forecast) {
            f = new ForecastList();
        } else if (id == R.id.nav_comment) {
            f = new CommentList();
        } else if (id == R.id.nav_settings) {
            Intent i = new Intent(MainActivity.this, SpotActivity.class);
            startActivity(i);
            setTitle(config.getString(Common.SHARED_PREF_SPOT_NAME, ""));
        } else if (id == R.id.nav_exit) {
            SharedPreferences.Editor editor = config.edit();
            editor.putString(Common.SHARED_PREF_LOGIN_MAIL, "");
            editor.putString(Common.SHARED_PREF_LOGIN_NAME, "");
            editor.putString(Common.SHARED_PREF_LOGIN_TOKEN, "");
            editor.putString(Common.SHARED_PREF_LOGIN_API, "");
            editor.putString(Common.SHARED_PREF_SPOT_NAME, "");
            editor.putInt(Common.SHARED_PREF_SPOT_ID, 0);
            editor.putBoolean(Common.SHARED_PREF_SPOT, false);
            editor.putBoolean(Common.SHARED_PREF_LOGIN, false);
            editor.apply();

            finish();
        }

        if (f != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, f)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void refreshActivity() {

        // Personalization of Navigation View Header
        setTitle(config.getString(Common.SHARED_PREF_SPOT_NAME, ""));

        ImageView ivMainLogo = navigationView.getHeaderView(0).findViewById(R.id.ivMainLogo);
        ivMainLogo.setImageResource(R.drawable.ic_surfer_surfing_in_a_big_water_wave_white);

        TextView tvMainName = navigationView.getHeaderView(0).findViewById(R.id.tvMainName);
        tvMainName.setText("@".concat(config.getString(Common.SHARED_PREF_LOGIN_NAME, "")));

        TextView tvMainMail = navigationView.getHeaderView(0).findViewById(R.id.tvMainMail);
        tvMainMail.setText(config.getString(Common.SHARED_PREF_LOGIN_MAIL, ""));

        // Load Main Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new CurrentFragment())
                .commit();

    }

    public void fntNewComment(View view) {

        newComment = new NewCommentDialog();
        newComment.show(getSupportFragmentManager(), "NewCommentDialog");

    }

    @Override
    public void onNewCommentSaveClickListener(String comment) {

        if (comment.isEmpty()) {

            Toast.makeText(this, "Debes escribir algo.", Toast.LENGTH_SHORT).show();

        } else if (comment.length() > 120) {

            Toast.makeText(this, "No puede superar los 120 caracteres.", Toast.LENGTH_SHORT).show();

        } else {

            Call<Response> sendCall = meteoSurfApi.sendComment(config.getString(Common.SHARED_PREF_LOGIN_TOKEN, ""), new CommentBody(config.getInt(Common.SHARED_PREF_SPOT_ID, 1), comment));
            sendCall.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                    if (!response.isSuccessful()) {

                        String error = "Error desconocido. Contacte con el Administrador.";
                        if (response.errorBody()
                                .contentType()
                                .subtype()
                                .equals("json")) {

                            ApiError apiError = ApiError.fromResponseBody(response.errorBody());
                            error = apiError.getMessage();
                            Log.d("MainActivity", apiError.getDeveloperMessage());

                        } else {

                            try {
                                Log.d("MainActivity", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(MainActivity.this, "Enviado", Toast.LENGTH_SHORT).show();

                        // Load Comment Fragment
                        getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.container, new CommentList())
                                .commit();

                    }

                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {

                    Toast.makeText(MainActivity.this, "Error al enviar:" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
}
