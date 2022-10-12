package com.dropdatabase.naszesasiedztwo.services;

import com.dropdatabase.naszesasiedztwo.models.Listing;

import java.util.List;

public interface IListingService {
    List<Listing> getListingsForCurrentUser();
}
