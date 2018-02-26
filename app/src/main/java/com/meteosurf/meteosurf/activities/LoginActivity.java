package com.meteosurf.meteosurf.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.meteosurf.meteosurf.R;
import com.meteosurf.meteosurf.api.ApiError;
import com.meteosurf.meteosurf.api.LoginBody;
import com.meteosurf.meteosurf.api.User;
import com.meteosurf.meteosurf.common.Common;
import com.meteosurf.meteosurf.interfaces.MeteoSurfApi;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText etLogMail;
    EditText etLogPass;
    SharedPreferences config;

    private Retrofit restAdapter;
    private MeteoSurfApi meteoSurfApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hide Toolbar
        getSupportActionBar().hide();

        etLogMail = findViewById(R.id.etLogMail);
        etLogPass = findViewById(R.id.etLogPass);

        // Obtain preferences
        config = getSharedPreferences(Common.SHARED_PREF, Context.MODE_PRIVATE);

        // Create connection to Rest service
        restAdapter = new Retrofit.Builder()
                .baseUrl(MeteoSurfApi.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create connection to the API
        meteoSurfApi = restAdapter.create(MeteoSurfApi.class);

    }

    public void fntLogLogin(View view) {

        String mail = etLogMail.getText().toString();
        String pass = etLogPass.getText().toString();

        if (mail.isEmpty()) {

            Toast.makeText(LoginActivity.this, "Debes indicar el correo electrónico", Toast.LENGTH_SHORT).show();

        } else if (!Common.validMail(mail)) {

            Toast.makeText(LoginActivity.this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show();

        } else if (pass.isEmpty()) {

            Toast.makeText(LoginActivity.this, "Debes indicar la contraseña", Toast.LENGTH_SHORT).show();

        } else {

            Call<User> loginCall = meteoSurfApi.login(new LoginBody(mail, pass));
            loginCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    if (!response.isSuccessful()) {

                        String error = "Error desconocido. Contacte con el Administrador.";
                        if (response.errorBody()
                                .contentType()
                                .subtype()
                                .equals("json")) {

                            ApiError apiError = ApiError.fromResponseBody(response.errorBody());
                            error = apiError.getMessage();
                            Log.d("LoginActivity", apiError.getDeveloperMessage());

                        } else {

                            try {
                                Log.d("LoginActivity", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();

                    } else {

                        SharedPreferences.Editor editor = config.edit();
                        editor.putString(Common.SHARED_PREF_LOGIN_MAIL, response.body().getMail());
                        editor.putString(Common.SHARED_PREF_LOGIN_NAME, response.body().getName());
                        editor.putString(Common.SHARED_PREF_LOGIN_TOKEN, response.body().getToken());
                        editor.putString(Common.SHARED_PREF_LOGIN_API, response.body().getApiKey());
                        editor.putBoolean(Common.SHARED_PREF_LOGIN, true);
                        editor.apply();

                        Toast.makeText(LoginActivity.this, "Login OK", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                    Toast.makeText(LoginActivity.this, "Error al entrar:" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
}
