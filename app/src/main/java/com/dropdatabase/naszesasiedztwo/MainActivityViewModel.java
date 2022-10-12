package com.dropdatabase.naszesasiedztwo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dropdatabase.naszesasiedztwo.models.Listing;
import com.dropdatabase.naszesasiedztwo.models.Region;
import com.dropdatabase.naszesasiedztwo.models.User;
import com.dropdatabase.naszesasiedztwo.services.ListingService;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final ListingService listingService;

    private final MutableLiveData<User> currentUser;
    private final MutableLiveData<List<Listing>> listings;


    public MainActivityViewModel() {
        listingService = ListingService.getInstance();

        this.currentUser = new MutableLiveData<>(new User(1,"Jaca", "Praca", "Hej"));
        this.listings = new MutableLiveData<>(listingService.getListings());

    }

    public LiveData<User> getCurrentUser() {
        return currentUser;
    }

    public LiveData<List<Listing>> getListings() {
        return listings;
    }
}
