package com.meteosurf.meteosurf.interfaces;

import com.meteosurf.meteosurf.msw.Forecast;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MSWApi {

    public static final String URL_BASE = "http://magicseaweed.com/api/";

    @GET("{apiToken}/forecast/")
    Call<List<Forecast>> getForecast(@Path("apiToken") String apiToken, @Query("spot_id") int idSpot);

}
