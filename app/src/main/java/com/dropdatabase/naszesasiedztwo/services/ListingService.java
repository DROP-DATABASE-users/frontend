package com.dropdatabase.naszesasiedztwo.services;

import com.dropdatabase.naszesasiedztwo.models.Listing;
import com.dropdatabase.naszesasiedztwo.models.Region;
import com.dropdatabase.naszesasiedztwo.models.User;

import java.util.ArrayList;
import java.util.List;

public class ListingService {

    private ListingService() {}
    private static ListingService instance = null;

    public static ListingService getInstance() {
        if (instance == null) {
            instance = new ListingService();
        }
        return instance;
    }

    public List<Listing> getListings() {
        List<Listing> listings =  new ArrayList<Listing>();
        listings.add(
                new Listing(0,
                        "Siema pomocy",
                        "aaaaa",
                        "6",
                        "9",
                        new Region(10),
                        new User(1, "grzegorz", "chętny", "jestem chętny"),
                        new User(2, "stefan", "bezręki", "nie mam ręki")
                ));
        return listings;
    }
}
