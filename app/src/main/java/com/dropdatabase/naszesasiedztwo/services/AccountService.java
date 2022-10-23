package com.dropdatabase.naszesasiedztwo.services;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.ClientError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dropdatabase.naszesasiedztwo.models.LoginData;
import com.dropdatabase.naszesasiedztwo.models.RegisterData;
import com.dropdatabase.naszesasiedztwo.models.User;
import com.dropdatabase.naszesasiedztwo.utils.NetworkConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class AccountService {

    public interface RegisterCallback {
        void onRegister();
    }

    public interface LoginCallback {
        void onLogin(String token);
    }

    public interface ErrorCallback {
        void onError(String errorMessage);
    }

    public interface UserDataCallback {
        void getData(User user);
    }


    private RequestQueue requestQueue;

    private final String route = NetworkConfig.API_URL + "/account";

    public AccountService(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public void register(RegisterData data, RegisterCallback callback, ErrorCallback errorCallback) {
        JsonObjectRequest registerRequest = new JsonObjectRequest(Request.Method.POST,
                route + "/register",
                data.toJsonObject(),
                response -> {
                    callback.onRegister();
                },
                error -> {
                    errorCallback.onError(error.toString());
                }
        ){

        };

        requestQueue.add(registerRequest);
    }

    public void login(LoginData data, LoginCallback callback, ErrorCallback errorCallback) {
        JsonObjectRequest loginRequest = new JsonObjectRequest(
                Request.Method.POST,
                route + "/login",
                data.toJsonObject(),
                response -> {
                    try {
                        callback.onLogin(response.getString("jwtToken"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    errorCallback.onError(error.toString());
                }
        );

        requestQueue.add(loginRequest);
    }

    public void getUserData(String token, UserDataCallback callback, ErrorCallback errorCallback) {
        JsonObjectRequest userDataRequest = new JsonObjectRequest(Request.Method.GET,  route + "/user", null,  response -> {
            callback.getData(new User(response));
        }, error -> {
            errorCallback.onError(error.toString());
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        requestQueue.add(userDataRequest);

    }

}
