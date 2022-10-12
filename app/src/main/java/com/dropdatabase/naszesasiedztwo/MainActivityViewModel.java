package com.dropdatabase.naszesasiedztwo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dropdatabase.naszesasiedztwo.models.Listing;
import com.dropdatabase.naszesasiedztwo.models.Region;
import com.dropdatabase.naszesasiedztwo.models.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final MutableLiveData<User> currentUser;
    private final MutableLiveData<List<Listing>> listings;

    public MainActivityViewModel() {
        this.currentUser = new MutableLiveData<>(
                new User(1,"Jaca", "Praca", "Hej"));

        List<Listing> listingsList = new ArrayList<>();
        listingsList.add(new Listing(1, "Witaj", "potrzebuje pomocy", "0.12312412", "0.1203918512", new Region(10), new User(), currentUser.getValue()));
        this.listings = new MutableLiveData<>(listingsList);


    }

    public LiveData<User> getCurrentUser() {
        return currentUser;
    }

    public LiveData<List<Listing>> getListings() {
        return listings;
    }
}
