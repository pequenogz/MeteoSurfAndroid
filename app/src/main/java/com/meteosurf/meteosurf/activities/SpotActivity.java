package com.meteosurf.meteosurf.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.meteosurf.meteosurf.R;
import com.meteosurf.meteosurf.api.ApiError;
import com.meteosurf.meteosurf.api.Filter1;
import com.meteosurf.meteosurf.api.Filter2;
import com.meteosurf.meteosurf.api.Filter3;
import com.meteosurf.meteosurf.api.Filter4;
import com.meteosurf.meteosurf.api.SpotFilter1;
import com.meteosurf.meteosurf.api.SpotFilter2;
import com.meteosurf.meteosurf.api.SpotFilter3;
import com.meteosurf.meteosurf.api.SpotFilter4;
import com.meteosurf.meteosurf.common.Common;
import com.meteosurf.meteosurf.interfaces.MeteoSurfApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpotActivity extends AppCompatActivity {

    Spinner spFilter1;
    Spinner spFilter2;
    Spinner spFilter3;
    Spinner spFilter4;
    Button btAplySpot;
    SharedPreferences config;

    private Retrofit restAdapter;
    private MeteoSurfApi meteoSurfApi;

    private List<Filter1> listFilter1 = new ArrayList<>();
    private List<Filter2> listFilter2 = new ArrayList<>();
    private List<Filter3> listFilter3 = new ArrayList<>();
    private List<Filter4> listFilter4 = new ArrayList<>();
    private int idSpot;
    private String nameSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);

        // Hide Toolbar
        getSupportActionBar().hide();

        spFilter1 = findViewById(R.id.spFilter1);
        spFilter2 = findViewById(R.id.spFilter2);
        spFilter3 = findViewById(R.id.spFilter3);
        spFilter4 = findViewById(R.id.spFilter4);
        btAplySpot = findViewById(R.id.btAplySpot);

        // Obtain preferences
        config = getSharedPreferences(Common.SHARED_PREF, Context.MODE_PRIVATE);

        // Create connection to Rest service
        restAdapter = new Retrofit.Builder()
                .baseUrl(MeteoSurfApi.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create connection to the API
        meteoSurfApi = restAdapter.create(MeteoSurfApi.class);

        // Cargamos datos del primer spinner
        refreshSpinner1();

        // Listeners
        spFilter1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                clearLists(1);
                if (!listFilter1.get(i).getName().equals("Seleccione localización ...")) {
                    refreshSpinner2(listFilter1.get(i).getId1());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spFilter2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                clearLists(2);
                if (!listFilter2.get(i).getName().equals("Seleccione localización ...")) {
                    refreshSpinner3(listFilter2.get(i).getId2());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spFilter3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                clearLists(3);
                if (!listFilter3.get(i).getName().equals("Seleccione localización ...")) {
                    refreshSpinner4(listFilter3.get(i).getId3());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spFilter4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                clearLists(4);
                if (!listFilter4.get(i).getName().equals("Seleccione localización ...")) {
                    idSpot = listFilter4.get(i).getId();
                    nameSpot = listFilter4.get(i).getName();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void refreshSpinner1() {

        Call<SpotFilter1> filter1Call = meteoSurfApi.getFilter1(config.getString(Common.SHARED_PREF_LOGIN_TOKEN, ""));
        filter1Call.enqueue(new Callback<SpotFilter1>() {
            @Override
            public void onResponse(Call<SpotFilter1> call, Response<SpotFilter1> response) {

                if (!response.isSuccessful()){

                    responseError(response);

                } else {

                    listFilter1 = response.body().getFilter1();
                    listFilter1.add(0, new Filter1(0, "Seleccione localización ..."));
                    ArrayAdapter<Filter1> filter1ArrayAdapter = new ArrayAdapter<Filter1>(getApplicationContext(), android.R.layout.simple_spinner_item, listFilter1);
                    filter1ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spFilter1.setAdapter(filter1ArrayAdapter);

                }

            }

            @Override
            public void onFailure(Call<SpotFilter1> call, Throwable t) {
                failureCall(t);
            }
        });

    }

    private void refreshSpinner2(final int id1) {

        Call<SpotFilter2> filter2Call = meteoSurfApi.getFilter2(config.getString(Common.SHARED_PREF_LOGIN_TOKEN, ""), id1);
        filter2Call.enqueue(new Callback<SpotFilter2>() {
            @Override
            public void onResponse(Call<SpotFilter2> call, Response<SpotFilter2> response) {

                if (!response.isSuccessful()){

                    responseError(response);

                } else {

                    listFilter2 = response.body().getFilter2();
                    listFilter2.add(0, new Filter2(0, id1, "Seleccione localización ..."));
                    ArrayAdapter<Filter2> filter2ArrayAdapter = new ArrayAdapter<Filter2>(getApplicationContext(), android.R.layout.simple_spinner_item, listFilter2);
                    filter2ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spFilter2.setAdapter(filter2ArrayAdapter);

                }

            }

            @Override
            public void onFailure(Call<SpotFilter2> call, Throwable t) {
                failureCall(t);
            }
        });

    }

    private void refreshSpinner3(final int id2) {

        Call<SpotFilter3> filter3Call = meteoSurfApi.getFilter3(config.getString(Common.SHARED_PREF_LOGIN_TOKEN, ""), id2);
        filter3Call.enqueue(new Callback<SpotFilter3>() {
            @Override
            public void onResponse(Call<SpotFilter3> call, Response<SpotFilter3> response) {

                if (!response.isSuccessful()){

                    responseError(response);

                } else {

                    listFilter3 = response.body().getFilter3();
                    listFilter3.add(0, new Filter3(0, id2, "Seleccione localización ..."));
                    ArrayAdapter<Filter3> filter3ArrayAdapter = new ArrayAdapter<Filter3>(getApplicationContext(), android.R.layout.simple_spinner_item, listFilter3);
                    filter3ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spFilter3.setAdapter(filter3ArrayAdapter);

                }

            }

            @Override
            public void onFailure(Call<SpotFilter3> call, Throwable t) {
                failureCall(t);
            }
        });

    }

    private void refreshSpinner4(final int id3) {

        Call<SpotFilter4> filter4Call = meteoSurfApi.getFilter4(config.getString(Common.SHARED_PREF_LOGIN_TOKEN, ""), id3);
        filter4Call.enqueue(new Callback<SpotFilter4>() {
            @Override
            public void onResponse(Call<SpotFilter4> call, Response<SpotFilter4> response) {

                if (!response.isSuccessful()){

                    responseError(response);

                } else {

                    listFilter4 = response.body().getFilter4();
                    listFilter4.add(0, new Filter4(0, id3, "Seleccione localización ..."));
                    ArrayAdapter<Filter4> filter4ArrayAdapter = new ArrayAdapter<Filter4>(getApplicationContext(), android.R.layout.simple_spinner_item, listFilter4);
                    filter4ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spFilter4.setAdapter(filter4ArrayAdapter);

                }

            }

            @Override
            public void onFailure(Call<SpotFilter4> call, Throwable t) {
                failureCall(t);
            }
        });

    }

    private void clearLists(int idFilter) {

        switch (idFilter) {
            case 1:
                spFilter2.setAdapter(null);
                listFilter2.clear();
            case 2:
                spFilter3.setAdapter(null);
                listFilter3.clear();
            case 3:
                spFilter4.setAdapter(null);
                listFilter4.clear();
            case 4:
                idSpot = 0;
                nameSpot = "";
        }

    }

    private void failureCall(Throwable t) {

        Toast.makeText(SpotActivity.this, "Error al obtener Spots: " + t.getMessage(), Toast.LENGTH_SHORT).show();

    }

    private void responseError(Response response) {

        String error = "Error desconocido. Contacte con el Administrador";
        if (response.errorBody()
                .contentType()
                .subtype()
                .equals("json")) {

            ApiError apiError = ApiError.fromResponseBody(response.errorBody());
            error = apiError.getMessage();
            Log.d("SpotActivity", apiError.getDeveloperMessage());

        } else {

            try {
                Log.d("SpotActivity", response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        Toast.makeText(SpotActivity.this,error,Toast.LENGTH_SHORT).show();

    }

    public void fntAplySpot(View view) {

        if (idSpot > 0) {
            SharedPreferences.Editor editor = config.edit();
            editor.putString(Common.SHARED_PREF_SPOT_NAME, nameSpot);
            editor.putInt(Common.SHARED_PREF_SPOT_ID, idSpot);
            editor.putBoolean(Common.SHARED_PREF_SPOT, true);
            editor.apply();

            Toast.makeText(SpotActivity.this, "Spot " + nameSpot + " con id: " + idSpot, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(SpotActivity.this, "Debes seleccionar un Spot", Toast.LENGTH_SHORT).show();
        }

    }
}
