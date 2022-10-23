package com.dropdatabase.naszesasiedztwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.dropdatabase.naszesasiedztwo.databinding.ActivityLoginBinding;
import com.dropdatabase.naszesasiedztwo.databinding.ActivityNewListingBinding;
import com.dropdatabase.naszesasiedztwo.models.Listing;
import com.dropdatabase.naszesasiedztwo.models.User;
import com.dropdatabase.naszesasiedztwo.services.ListingService;

public class NewListingActivity extends AppCompatActivity {

    private ActivityNewListingBinding binding;
    private ListingService ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ls = new ListingService(this);
        binding = ActivityNewListingBinding.inflate(getLayoutInflater());
        binding.newlistingButton.setOnClickListener(this::onAddListingClicked);
        setContentView(binding.getRoot());
    }

    private void onAddListingClicked(View v) {
        Intent intent = getIntent();
        int region = intent.getIntExtra("region", 0);
        Location location = intent.getParcelableExtra("location");
        String title = binding.titleInput.getText().toString();
        String description = binding.newlistingDescriptionInput.getText().toString();

        Listing listingdata = new Listing();
        listingdata.setTitle(title);
        listingdata.setDescription(description);
        listingdata.setCoordinatesX(Double.toString(location.getLatitude()));
        listingdata.setCoordinatesY(Double.toString(location.getLongitude()));
        listingdata.setRegionId(region);
        User user = (User) intent.getSerializableExtra("user");
        String token = intent.getStringExtra("token");
        ls.createListing(user, listingdata, token, this::finish, errorMessage -> binding.newlistingErrorMsg.setText(errorMessage));
    }
}