package com.dropdatabase.naszesasiedztwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.dropdatabase.naszesasiedztwo.databinding.ActivityListingDetailsBinding;
import com.dropdatabase.naszesasiedztwo.databinding.ActivityMainBinding;
import com.dropdatabase.naszesasiedztwo.models.Listing;
import com.dropdatabase.naszesasiedztwo.models.User;
import com.dropdatabase.naszesasiedztwo.services.AccountService;
import com.dropdatabase.naszesasiedztwo.services.ListingService;

public class ListingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityListingDetailsBinding binding = ActivityListingDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Listing listing = (Listing) getIntent().getSerializableExtra("listing");

        ListingService listingService = new ListingService(this);

        Bundle loginData = getIntent().getBundleExtra("loginData");

        assert listing != null;
        User listing_author = listing.getAuthor();
        binding.titleTv.setText(listing.getTitle());
        binding.descriptionTv.setText(listing.getDescription());
        binding.authorTv.setText(listing_author.getName());
        binding.authorDescriptionTv.setText(listing_author.getDescription());

        binding.cancelButton.setOnClickListener(v -> finish());
        binding.acceptButton.setOnClickListener(v -> {
            if (loginData == null) return;

            listingService.acceptListing((User) loginData.getSerializable("user"), listing, loginData.getString("token"),
                () -> {
                    Toast.makeText(this, "Accepted.", Toast.LENGTH_SHORT).show();
                    finish();
                },
                errorMessage -> {
                    if(errorMessage != null) Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            );


        });

    }


}