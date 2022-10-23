package com.dropdatabase.naszesasiedztwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dropdatabase.naszesasiedztwo.databinding.ActivityMainBinding;
import com.dropdatabase.naszesasiedztwo.models.Listing;
import com.dropdatabase.naszesasiedztwo.models.User;
import com.dropdatabase.naszesasiedztwo.services.ListingService;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final MutableLiveData<User> currentUser = new MutableLiveData<>();
    private final MutableLiveData<List<Listing>> listings = new MutableLiveData<>();
    private final MutableLiveData<Integer> region = new MutableLiveData<>();
    private final MutableLiveData<String> userToken = new MutableLiveData<>();

    public LiveData<User> getCurrentUser() {
        return currentUser;
    }

    public LiveData<List<Listing>> getListings() {
        return listings;
    }

    public LiveData<Integer> getRegion() {
        return region;
    }

    private Bundle loginData;

    public void setLoginData(Bundle loginData) {
        this.loginData = loginData;
    }
    public Bundle getLoginData() {
        return loginData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListingService listingService = new ListingService(this);

        this.region.observe(this, newRegion -> {
            listingService.fetchListings(newRegion, listings::setValue);
        });


        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_listings)
                .build();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent intent = result.getData();
                assert intent != null;


                loginData = intent.getBundleExtra("loginData");

                this.currentUser.setValue((User) loginData.getSerializable("user"));
                this.userToken.setValue(loginData.getString("token"));
                this.region.setValue(loginData.getInt("region"));

                Toast.makeText(this, Objects.requireNonNull(currentUser.getValue()).getName(), Toast.LENGTH_SHORT).show();
            }
        }).launch(new Intent(this, LoginActivity.class));
    }
}