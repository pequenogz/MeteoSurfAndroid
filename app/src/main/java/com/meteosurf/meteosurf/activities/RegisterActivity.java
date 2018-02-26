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
import com.meteosurf.meteosurf.api.RegisterBody;
import com.meteosurf.meteosurf.api.User;
import com.meteosurf.meteosurf.common.Common;
import com.meteosurf.meteosurf.interfaces.MeteoSurfApi;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    EditText etRegName;
    EditText etRegMail;
    EditText etRegPass1;
    EditText etRegPass2;
    SharedPreferences config;

    private Retrofit restAdapter;
    private MeteoSurfApi meteoSurfApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Hide ToolBar
        getSupportActionBar().hide();

        etRegName = findViewById(R.id.etRegName);
        etRegMail = findViewById(R.id.etRegMail);
        etRegPass1 = findViewById(R.id.etRegPass1);
        etRegPass2 = findViewById(R.id.etRegPass2);

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

    public void fntRegRegister(View view) {

        String name = etRegName.getText().toString();
        String mail = etRegMail.getText().toString();
        String pass1 = etRegPass1.getText().toString();
        String pass2 = etRegPass2.getText().toString();

        if (name.isEmpty()) {

            Toast.makeText(RegisterActivity.this, "Debes indicar un nombre de usuario", Toast.LENGTH_SHORT).show();

        } else if (!Common.validName(name)) {

            Toast.makeText(RegisterActivity.this, "Nombre no válido", Toast.LENGTH_SHORT).show();

        } else if (mail.isEmpty()) {

            Toast.makeText(RegisterActivity.this, "Debes indicar un correo electrónico", Toast.LENGTH_SHORT).show();

        } else if (!Common.validMail(mail)) {

            Toast.makeText(RegisterActivity.this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show();

        } else if (pass1.isEmpty()) {

            Toast.makeText(RegisterActivity.this, "Debes indicar una contraseña", Toast.LENGTH_SHORT).show();

        } else if (pass2.isEmpty()) {

            Toast.makeText(RegisterActivity.this, "Debes confirmar la contraseña", Toast.LENGTH_SHORT).show();

        } else if (!pass1.equals(pass2)) {

            Toast.makeText(RegisterActivity.this, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show();

        } else {

            Call<User> registerCall = meteoSurfApi.register(new RegisterBody(name,mail,pass1));
            registerCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    if(!response.isSuccessful()) {

                        String error = "Error desconocido. Contacte con el Administrador";
                        if (response.errorBody()
                                .contentType()
                                .subtype()
                                .equals("json")) {

                            ApiError apiError = ApiError.fromResponseBody(response.errorBody());
                            error = apiError.getMessage();
                            Log.d("RegisterActivity", apiError.getDeveloperMessage());

                        } else {

                            try {
                                Log.d("RegisterActivity", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        Toast.makeText(RegisterActivity.this,error,Toast.LENGTH_SHORT).show();

                    } else {

                        SharedPreferences.Editor editor = config.edit();
                        editor.putString(Common.SHARED_PREF_LOGIN_MAIL, response.body().getMail());
                        editor.putString(Common.SHARED_PREF_LOGIN_NAME, response.body().getName());
                        editor.putString(Common.SHARED_PREF_LOGIN_TOKEN, response.body().getToken());
                        editor.putString(Common.SHARED_PREF_LOGIN_API, response.body().getApiKey());
                        editor.putBoolean(Common.SHARED_PREF_LOGIN, true);
                        editor.apply();

                        Toast.makeText(RegisterActivity.this, "Registro con éxito", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                    Toast.makeText(RegisterActivity.this, "Error al registrar: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
}
