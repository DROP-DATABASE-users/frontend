package com.dropdatabase.naszesasiedztwo.services;

import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.dropdatabase.naszesasiedztwo.MainActivityViewModel;
import com.dropdatabase.naszesasiedztwo.models.Listing;
import com.dropdatabase.naszesasiedztwo.models.Region;
import com.dropdatabase.naszesasiedztwo.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListingService {
    private MainActivityViewModel vm;
    private RequestQueue requestQueue;

    public ListingService(MainActivityViewModel vm) {
        this.requestQueue = Volley.newRequestQueue(vm.getApplication());
        this.vm = vm;
    }

    public void fetchListings() {
        List<Listing> listings = new ArrayList<>();

        String url = "http://172.30.188.124:5078/api/listing/12";

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
                        vm.setListings(listings);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    System.out.println(error.networkResponse.statusCode);
                    error.printStackTrace();
                }
                ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjciLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoidGVzdCB0ZXN0IiwiZXhwIjoxNjY2ODkxNzkzLCJpc3MiOiJodHRwOi8vbmFzemVzYXNpZWR6dHdvLmNvbSIsImF1ZCI6Imh0dHA6Ly9uYXN6ZXNhc2llZHp0d28uY29tIn0.ZmfuAh1ntUR37lCqTLXs7GM8ze-5HAzHupmx8ZcYK3c");
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

}
