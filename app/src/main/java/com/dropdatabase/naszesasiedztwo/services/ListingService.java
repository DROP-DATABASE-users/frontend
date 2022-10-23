package com.dropdatabase.naszesasiedztwo.services;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dropdatabase.naszesasiedztwo.models.Listing;
import com.dropdatabase.naszesasiedztwo.models.ListingUpdateData;
import com.dropdatabase.naszesasiedztwo.models.User;
import com.dropdatabase.naszesasiedztwo.utils.NetworkConfig;
import com.dropdatabase.naszesasiedztwo.utils.StringJsonRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.tileprovider.ReusableBitmapDrawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListingService {
    private final RequestQueue requestQueue;

    public interface FetchCallback<T> {
        void onReceived(T data);
    }

    public ListingService(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
    }




    public void fetchListings(Integer region, FetchCallback<List<Listing>> listingCallback) {
        List<Listing> listings = new ArrayList<>();
        String url = NetworkConfig.API_URL + "/listing/" + region.toString();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                                JSONObject responseObj = response.getJSONObject(i);
                                listings.add(new Listing(responseObj));
                        }
                        listingCallback.onReceived(listings);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    if(error.networkResponse != null) {
                        System.out.println(error.networkResponse.statusCode);
                    }
                    error.printStackTrace();

                }
                ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjciLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoidGVzdCB0ZXN0IiwiZXhwIjoxNjY2ODkxNzkzLCJpc3MiOiJodHRwOi8vbmFzemVzYXNpZWR6dHdvLmNvbSIsImF1ZCI6Imh0dHA6Ly9uYXN6ZXNhc2llZHp0d28uY29tIn0.ZmfuAh1ntUR37lCqTLXs7GM8ze-5HAzHupmx8ZcYK3c");
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }


    public void createListing(User creatingUser, Listing listingData, String token, AcceptCallback acceptCallback, ErrorCallback errorCallback) {
        listingData.setAuthorId(creatingUser.getId());
        listingData.setAuthor(creatingUser);

        StringJsonRequest rq = new StringJsonRequest(Request.Method.POST, NetworkConfig.API_URL + "/listing", listingData.toAddJSONObject(), response -> acceptCallback.onAccept(), error -> errorCallback.onError(error.toString())) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        requestQueue.add(rq);

    }

    public interface AcceptCallback {
        void onAccept();
    }

    public interface ErrorCallback {
        void onError(String errorMessage);
    }



    public void acceptListing(User acceptingUser, Listing selectedListing, String token, AcceptCallback acceptCallback, ErrorCallback errorCallback) {
        ListingUpdateData updateData = new ListingUpdateData(selectedListing, acceptingUser);

        StringRequest request = new StringRequest(Request.Method.PATCH,
                NetworkConfig.API_URL + "/listing/" + selectedListing.getId(), response -> acceptCallback.onAccept(),
                error -> errorCallback.onError(error.toString())
                ){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        requestQueue.add(request);
    }
}
