package com.dropdatabase.naszesasiedztwo;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dropdatabase.naszesasiedztwo.models.Listing;
import com.dropdatabase.naszesasiedztwo.models.User;
import com.dropdatabase.naszesasiedztwo.services.ListingService;
import com.dropdatabase.naszesasiedztwo.utils.DataUpdateCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private final MutableLiveData<User> currentUser;
    private final MutableLiveData<List<Listing>> listings;

    private final List<DataUpdateCallback> listingUpdates;

    public MainActivityViewModel(Application application) {
        super(application);
        ListingService listingService = new ListingService(this);
        listingUpdates = new ArrayList<>();
        this.currentUser = new MutableLiveData<>(new User(1,"Jaca", "Praca", "Hej"));
        this.listings = new MutableLiveData<>(new ArrayList<>());

        listingService.fetchListings(data -> {
            this.setListings(data);

            for (DataUpdateCallback callback : listingUpdates) {
                callback.update();
            }

        });
    }

    public void addListingCallback(DataUpdateCallback callback) {
        listingUpdates.add(callback);
    }

    public void removeListingCallback(DataUpdateCallback callback){
        listingUpdates.remove(callback);
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
