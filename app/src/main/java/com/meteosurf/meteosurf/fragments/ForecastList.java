package com.meteosurf.meteosurf.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.meteosurf.meteosurf.R;
import com.meteosurf.meteosurf.common.Common;
import com.meteosurf.meteosurf.interfaces.MSWApi;
import com.meteosurf.meteosurf.msw.Forecast;
import com.meteosurf.meteosurf.msw.MSWError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastList extends Fragment {

    private View view;
    SharedPreferences config;
    private Context context;
    private Retrofit restAdapter;
    private MSWApi mswApi;
    private List<Forecast> forecasts = new ArrayList<>();

    ListView forecastList;

    public ForecastList() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        config = context.getSharedPreferences(Common.SHARED_PREF, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create connection to Rest service
        restAdapter = new Retrofit.Builder()
                .baseUrl(MSWApi.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create connection to the API
        mswApi = restAdapter.create(MSWApi.class);

        // Obtain data of forecast
        refreshForecast();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forecast_list, container, false);

        forecastList = view.findViewById(R.id.forecastList);

        // Return view
        return view;

    }

    private View refreshData() {

        return view;

    }

    private void refreshForecast() {

        Call<List<Forecast>> callForecast = mswApi.getForecast(config.getString(Common.SHARED_PREF_LOGIN_API, ""), config.getInt(Common.SHARED_PREF_SPOT_ID, 1));
        callForecast.enqueue(new Callback<List<Forecast>>() {
            @Override
            public void onResponse(Call<List<Forecast>> call, Response<List<Forecast>> response) {

                if (!response.isSuccessful()){

                    String error = "Error desconocido. Contacte con el Administrador";
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {

                        MSWError mswError = MSWError.fromResponseBody(response.errorBody());
                        error = mswError.getErrorResponse().getErrorMsg();
                        Log.d("MainActivity", mswError.getErrorResponse().getErrorMsg());

                    } else {

                        try {
                            Log.d("MainActivity", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();

                } else {

                    forecasts = response.body();

                    // Create Array adapter
                    ForecastAdapter forecastAdapter = new ForecastAdapter(context, R.layout.fragment_forecast_item, forecasts);
                    forecastList.setAdapter(forecastAdapter);
                    refreshData();

                }

            }

            @Override
            public void onFailure(Call<List<Forecast>> call, Throwable t) {

                Toast.makeText(context, "Error al obtener previsión: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
