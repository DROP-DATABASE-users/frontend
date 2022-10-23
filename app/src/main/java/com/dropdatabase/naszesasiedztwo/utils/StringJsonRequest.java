package com.dropdatabase.naszesasiedztwo.utils;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class StringJsonRequest extends StringRequest {

    @Nullable
    private final JSONObject jsonBody;

    private byte[] body = new byte[]{};

    public StringJsonRequest(int method, String url, @Nullable JSONObject jsonBody, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.jsonBody = jsonBody;
        if (jsonBody != null) {
            body = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
        }
    }

    public StringJsonRequest(String url, @Nullable JSONObject jsonBody, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
        this.jsonBody = jsonBody;
        if (jsonBody != null) {
            body = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
        }
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return body;
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }
}
