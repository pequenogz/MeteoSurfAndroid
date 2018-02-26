package com.meteosurf.meteosurf.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meteosurf.meteosurf.R;
import com.meteosurf.meteosurf.msw.Forecast;

import java.sql.Timestamp;
import java.util.List;

class ForecastAdapter extends ArrayAdapter<Forecast> {

    private Context context;
    private int resource;
    private List<Forecast> forecastList;

    TextView tvDateTime;
    TextView tvHeader;
    TextView tvSizeWave;
    TextView tvPeriod;
    ImageView ivCompass;
    TextView tvWind;
    ImageView ivWind;
    ImageView ivStar1;
    ImageView ivStar2;
    ImageView ivStar3;
    ImageView ivStar4;
    ImageView ivStar5;
    TextView tvProbability;

    public ForecastAdapter(@NonNull Context context, int resource, @NonNull List<Forecast> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.forecastList = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(resource, parent, false);

        // Forecast item
        Forecast forecast = forecastList.get(position);

        // Elements of item
        tvDateTime = view.findViewById(R.id.tvDateTime);
        tvHeader = view.findViewById(R.id.tvHeader);
        tvSizeWave = view.findViewById(R.id.tvSizeWave);
        tvPeriod = view.findViewById(R.id.tvPeriod);
        ivCompass = view.findViewById(R.id.ivCompass);
        tvWind = view.findViewById(R.id.tvWind);
        ivWind = view.findViewById(R.id.ivWind);
        ivStar1 = view.findViewById(R.id.ivStar1);
        ivStar2 = view.findViewById(R.id.ivStar2);
        ivStar3 = view.findViewById(R.id.ivStar3);
        ivStar4 = view.findViewById(R.id.ivStar4);
        ivStar5 = view.findViewById(R.id.ivStar5);
        tvProbability = view.findViewById(R.id.tvProbability);

        tvDateTime.setText(String.valueOf(new Timestamp(forecast.getLocalTimestamp()*1000L).toGMTString()));
        tvHeader.setText(String.valueOf(forecast.getSwell().getMinBreakingHeight()).concat("-").concat(String.valueOf(forecast.getSwell().getMaxBreakingHeight())).concat(forecast.getSwell().getUnit()));
        tvSizeWave.setText(String.valueOf(forecast.getSwell().getComponents().getPrimary().getHeight()).concat(forecast.getSwell().getUnit()));
        tvPeriod.setText(String.valueOf(forecast.getSwell().getComponents().getPrimary().getPeriod()).concat("s"));
        ivCompass.setRotation((float) forecast.getSwell().getComponents().getPrimary().getDirection());
        tvWind.setText(String.valueOf(forecast.getWind().getSpeed()).concat("-").concat(String.valueOf(forecast.getWind().getGusts())).concat(forecast.getWind().getUnit()));
        ivWind.setRotation((float) forecast.getWind().getDirection());

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

        // Probability occurrence
        int prob = forecast.getSwell().getProbability();
        tvProbability.setText(String.valueOf(prob).concat("%"));
        tvProbability.setBackgroundColor(Color.rgb((int) Math.round((100-prob)*1.8),(int) Math.round(prob*1.8),0));

        return view;

    }

    private void changeStar(String type, int num) {

        int image = 0;

        switch (type) {
            case "solid":
                image = R.drawable.ic_star_20dp_yellow;
                break;
            case "fade":
                image = R.drawable.ic_star_20dp_pastel;
                break;
            case "off":
                image = R.drawable.ic_star_20dp_white;
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

}
