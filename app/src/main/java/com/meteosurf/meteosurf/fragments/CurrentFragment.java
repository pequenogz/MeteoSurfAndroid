package com.meteosurf.meteosurf.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
public class CurrentFragment extends Fragment {

    private View view;

    SharedPreferences config;
    private Context context;
    private Retrofit restAdapter;
    private MSWApi mswApi;
    private List<Forecast> forecasts = new ArrayList<>();
    private List<Bitmap> weatherIcons = new ArrayList<>();

    ImageView ivStar1;
    ImageView ivStar2;
    ImageView ivStar3;
    ImageView ivStar4;
    ImageView ivStar5;
    TextView tvHeader;
    TextView tvSizeWave1;
    TextView tvSizeWave2;
    TextView tvSizeWave3;
    TextView tvWind;
    TextView tvTemperature;
    TextView tvProbability;
    ImageView ivCompass1;
    ImageView ivCompass2;
    ImageView ivCompass3;
    ImageView ivWindDir;
    ImageView ivWeather;

    public CurrentFragment() {
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
        view = inflater.inflate(R.layout.fragment_current, container, false);
        ivStar1 = view.findViewById(R.id.ivStar1);
        ivStar2 = view.findViewById(R.id.ivStar2);
        ivStar3 = view.findViewById(R.id.ivStar3);
        ivStar4 = view.findViewById(R.id.ivStar4);
        ivStar5 = view.findViewById(R.id.ivStar5);
        tvHeader = view.findViewById(R.id.tvHeader);
        tvSizeWave1 = view.findViewById(R.id.tvSizeWave1);
        tvSizeWave2 = view.findViewById(R.id.tvSizeWave2);
        tvSizeWave3 = view.findViewById(R.id.tvSizeWave3);
        tvWind = view.findViewById(R.id.tvWind);
        tvTemperature = view.findViewById(R.id.tvTemperature );
        tvProbability = view.findViewById(R.id.tvProbability);
        ivCompass1 = view.findViewById(R.id.ivCompass1);
        ivCompass2 = view.findViewById(R.id.ivCompass2);
        ivCompass3 = view.findViewById(R.id.ivCompass3);
        ivWindDir = view.findViewById(R.id.ivWindDir);
        ivWeather = view.findViewById(R.id.ivWeather);

        return view;

    }

    private View refreshData() {

        // Select actual foecast
        Forecast forecast = new Forecast();
        long now = System.currentTimeMillis() / 1000L;
        for (Forecast f : forecasts) {
            if (now > f.getLocalTimestamp()) {
                forecast = f;
            } else {
                break;
            }
        }

        // Show rating
        int soliRate = forecast.getSolidRating();
        int fadeRate = forecast.getFadedRating();
        int star = 1;
        for (int i = 1; i <= soliRate; i++) {
            changeStar("solid", star);
            star++;
        }
        for (int j = 1; j <= fadeRate; j++) {
            changeStar("fade", star);
            star++;
        }
        for (int k = star; k <= 5; k++) {
            changeStar("off", star);
            star++;
        }

        // Show size wave header
        tvHeader.setText(String.valueOf(forecast.getSwell().getMinBreakingHeight()).concat("-").concat(String.valueOf(forecast.getSwell().getMaxBreakingHeight())).concat(forecast.getSwell().getUnit()));

        // Show size wave primary
        tvSizeWave1.setText(String.valueOf(forecast.getSwell().getComponents().getPrimary().getHeight()).concat(forecast.getSwell().getUnit()).concat(" ").concat(String.valueOf(forecast.getSwell().getComponents().getPrimary().getPeriod())).concat("s"));

        // Show Compass direction primary
        ivCompass1.setRotation((float) forecast.getSwell().getComponents().getPrimary().getDirection());

        // Verify if exists waves secondary
        if (forecast.getSwell().getComponents().getSecondary() != null) {

            // Show size wave secondary
            tvSizeWave2.setText(String.valueOf(forecast.getSwell().getComponents().getSecondary().getHeight()).concat(forecast.getSwell().getUnit()).concat(" ").concat(String.valueOf(forecast.getSwell().getComponents().getSecondary().getPeriod())).concat("s"));

            // Show Compass direction secondary
            ivCompass2.setRotation((float) forecast.getSwell().getComponents().getSecondary().getDirection());

        } else {

            // Empty data
            tvSizeWave2.setText("-- --");

            // Do not show compass
            ivCompass2.setVisibility(View.INVISIBLE);

        }

        // Verify if exists waves tertiary
        if (forecast.getSwell().getComponents().getTertiary() != null) {

            // Show size wave secondary
            tvSizeWave3.setText(String.valueOf(forecast.getSwell().getComponents().getTertiary().getHeight()).concat(forecast.getSwell().getUnit()).concat(" ").concat(String.valueOf(forecast.getSwell().getComponents().getTertiary().getPeriod())).concat("s"));

            // Show Compass direction secondary
            ivCompass3.setRotation((float) forecast.getSwell().getComponents().getTertiary().getDirection());

        } else {

            // Empty data
            tvSizeWave3.setText("-- --");

            // Do not show compass
            ivCompass3.setVisibility(View.INVISIBLE);

        }

        // Show wind data
        tvWind.setText(String.valueOf(forecast.getWind().getSpeed()).concat("-").concat(String.valueOf(forecast.getWind().getGusts())).concat(forecast.getWind().getUnit()));
        ivWindDir.setRotation(forecast.getWind().getDirection());

        // Load image weather
        weatherIcons = splitWeather(getResources().getDrawable(R.drawable.weather));
        ivWeather.setImageBitmap(Bitmap.createScaledBitmap(weatherIcons.get(Integer.parseInt(forecast.getCondition().getWeather())-1),ivWeather.getMaxWidth(),ivWeather.getMaxHeight(),false));

        // Load temperature
        tvTemperature.setText(String.valueOf(forecast.getCondition().getTemperature()).concat("º").concat(forecast.getCondition().getUnit().toUpperCase()));

        // Probability occurrence
        int prob = forecast.getSwell().getProbability();
        tvProbability.setText(" ".concat(String.valueOf(prob)).concat("% "));
        tvProbability.setTextColor(Color.rgb((int) Math.round((100-prob)*1.8),(int) Math.round(prob*1.8),0));
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setColor(Color.WHITE);
        gd.setStroke(8, Color.rgb((int) Math.round((100-prob)*1.8),(int) Math.round(prob*1.8),0));
        gd.setCornerRadius(60f);
        tvProbability.setBackground(gd);

        return view;

    }

    private List<Bitmap> splitWeather(Drawable drawable) {

        int rows = 34;
        int cols = 2;
        int icoHeight;
        int icoWidth;
        ArrayList<Bitmap> icoWeather = new ArrayList<>(38);

        // Get scaled bitmap
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        Bitmap bitmapScaled = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(),true);

        icoHeight = bitmap.getHeight()/rows;
        icoWidth = bitmap.getWidth()/cols;

        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 0*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 11*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 22*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 32*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 1*icoWidth, 0*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 1*icoWidth, 1*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 1*icoWidth, 2*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 1*icoWidth, 3*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 1*icoWidth, 4*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 1*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 2*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 3*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 4*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 5*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 6*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 7*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 8*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 9*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 10*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 12*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 13*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 14*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 15*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 16*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 17*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 18*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 19*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 20*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 21*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 23*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 24*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 25*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 26*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 27*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 28*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 29*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 30*icoHeight, icoWidth*3/5, icoHeight*3/5));
        icoWeather.add(Bitmap.createBitmap(bitmapScaled, 0*icoWidth, 31*icoHeight, icoWidth*3/5, icoHeight*3/5));

        return icoWeather;

    }

    private void changeStar(String type, int num) {

        int image = 0;

        switch (type) {
            case "solid":
                image = R.drawable.ic_star_60dp_yellow;
                break;
            case "fade":
                image = R.drawable.ic_star_60dp_pastel;
                break;
            case "off":
                image = R.drawable.ic_star_60dp_white;
                break;
        }

        switch (num){
            case 1:
                ivStar1.setImageResource(image);
                break;
            case 2:
                ivStar2.setImageResource(image);
                break;
            case 3:
                ivStar3.setImageResource(image);
                break;
            case 4:
                ivStar4.setImageResource(image);
                break;
            case 5:
                ivStar5.setImageResource(image);
                break;
        }

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
