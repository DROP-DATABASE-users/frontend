package com.dropdatabase.naszesasiedztwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dropdatabase.naszesasiedztwo.databinding.ActivityListingDetailsBinding;
import com.dropdatabase.naszesasiedztwo.databinding.ActivityMainBinding;
import com.dropdatabase.naszesasiedztwo.models.Listing;
import com.dropdatabase.naszesasiedztwo.models.User;

public class ListingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityListingDetailsBinding binding = ActivityListingDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Listing listing = (Listing) getIntent().getSerializableExtra("listing");
        assert listing != null;
        User listing_author = listing.getAuthor();
        binding.titleTv.setText(listing.getTitle());
        binding.descriptionTv.setText(listing.getDescription());
        binding.authorTv.setText(listing_author.getName());
        binding.authorDescriptionTv.setText(listing_author.getDescription());
    }
}