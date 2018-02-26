package com.meteosurf.meteosurf.interfaces;

import com.meteosurf.meteosurf.api.CommentBody;
import com.meteosurf.meteosurf.api.Comments;
import com.meteosurf.meteosurf.api.LoginBody;
import com.meteosurf.meteosurf.api.RegisterBody;
import com.meteosurf.meteosurf.api.Response;
import com.meteosurf.meteosurf.api.SpotFilter1;
import com.meteosurf.meteosurf.api.SpotFilter2;
import com.meteosurf.meteosurf.api.SpotFilter3;
import com.meteosurf.meteosurf.api.SpotFilter4;
import com.meteosurf.meteosurf.api.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MeteoSurfApi {

    public static final String URL_BASE = "http://192.168.1.36/meteosurf/v1/";

    @POST("user/login")
    Call<User> login(@Body LoginBody loginBody);

    @POST("user/register")
    Call<User> register(@Body RegisterBody registerBody);

    @GET("spotfilter1")
    Call<SpotFilter1> getFilter1(@Header("authorization") String authorization);

    @GET("spotfilter2/{id1}")
    Call<SpotFilter2> getFilter2(@Header("authorization") String authorization, @Path("id1") int id1);

    @GET("spotfilter3/{id2}")
    Call<SpotFilter3> getFilter3(@Header("authorization") String authorization, @Path("id2") int id2);

    @GET("spotfilter4/{id3}")
    Call<SpotFilter4> getFilter4(@Header("authorization") String authorization, @Path("id3") int id3);

    @GET("comment/{idSpot}")
    Call<Comments> getComments(@Header("authorization") String authorization, @Path("idSpot") int idSpot);

    @POST("comment/new")
    Call<Response> sendComment(@Header("authorization") String authorization, @Body CommentBody commentBody);

}
