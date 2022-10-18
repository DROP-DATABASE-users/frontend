package com.dropdatabase.naszesasiedztwo;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dropdatabase.naszesasiedztwo.models.Listing;
import com.dropdatabase.naszesasiedztwo.models.User;
import com.dropdatabase.naszesasiedztwo.services.ListingService;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private final ListingService listingService;

    private final MutableLiveData<User> currentUser;
    private final MutableLiveData<List<Listing>> listings;


    public MainActivityViewModel(Application application) {
        super(application);
        listingService = new ListingService(this);
        this.currentUser = new MutableLiveData<>(new User(1,"Jaca", "Praca", "Hej"));
        this.listings = new MutableLiveData<>(new ArrayList<>());

        listingService.fetchListings();
    }


    public LiveData<User> getCurrentUser() {
        return currentUser;
    }

    public void setListings(List<Listing> listings) {
        this.listings.setValue(listings);
    }

    public LiveData<List<Listing>> getListings() {
        return listings;
    }
}
