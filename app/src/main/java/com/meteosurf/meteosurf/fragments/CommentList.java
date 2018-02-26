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
import com.meteosurf.meteosurf.activities.SpotActivity;
import com.meteosurf.meteosurf.api.ApiError;
import com.meteosurf.meteosurf.api.Comment;
import com.meteosurf.meteosurf.api.Comments;
import com.meteosurf.meteosurf.common.Common;
import com.meteosurf.meteosurf.interfaces.MeteoSurfApi;
import com.meteosurf.meteosurf.interfaces.OnNewCommentDialog;

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
public class CommentList extends Fragment {

    private View view;
    SharedPreferences config;
    private Context context;
    private Retrofit restAdapter;
    private MeteoSurfApi meteoSurfApi;
    private List<Comment> comments = new ArrayList<>();

    ListView commentList;

    public CommentList() {
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
                .baseUrl(MeteoSurfApi.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create connection to the API
        meteoSurfApi = restAdapter.create(MeteoSurfApi.class);

        // Obtain comments of Spot
        refreshComments();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_comment_list, container, false);

        commentList = view.findViewById(R.id.commentList);

        return view;

    }

    private View refreshData() {

        return view;

    }

    private void refreshComments() {

        Call<Comments> callComment = meteoSurfApi.getComments(config.getString(Common.SHARED_PREF_LOGIN_TOKEN, ""), config.getInt(Common.SHARED_PREF_SPOT_ID, 1));
        callComment.enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {

                if (!response.isSuccessful()){

                    String error = "Error desconocido. Contacte con el Administrador";
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

                    Toast.makeText(context,error,Toast.LENGTH_SHORT).show();

                } else {

                    comments = response.body().getComment();

                    // Create Array adapter
                    CommentsAdapter commentsAdapter = new CommentsAdapter(context, R.layout.fragment_comment_item, comments);
                    commentList.setAdapter(commentsAdapter);
                    commentList.setSelection(comments.size() - 1);
                    refreshData();

                }

            }

            @Override
            public void onFailure(Call<Comments> call, Throwable t) {

                Toast.makeText(context, "Error al obtener Spots: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
