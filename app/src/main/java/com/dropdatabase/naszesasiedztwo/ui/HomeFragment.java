package com.dropdatabase.naszesasiedztwo.ui;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


import com.dropdatabase.naszesasiedztwo.ListingDetailsActivity;
import com.dropdatabase.naszesasiedztwo.MainActivity;
import com.dropdatabase.naszesasiedztwo.NewListingActivity;
import com.dropdatabase.naszesasiedztwo.R;
import com.dropdatabase.naszesasiedztwo.databinding.FragmentHomeBinding;
import com.dropdatabase.naszesasiedztwo.models.Listing;
import com.dropdatabase.naszesasiedztwo.models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;

    private MapView map;
    private IMapController mapController;

    private Location currentLocation;
    private Location lastKnownLocation;

    private MainActivity mainActivity = null;
    private ActivityResultLauncher<Intent> launcher;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);


        if (requireActivity() instanceof MainActivity)
        {
            mainActivity = (MainActivity) requireActivity();
        }

        if (mainActivity != null) {
            mainActivity.getListings().observe(getViewLifecycleOwner(), this::updateMarkers);
            mainActivity.getCurrentUser().observe(getViewLifecycleOwner(), this::makeGreeting);
        }


        LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(requireActivity(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500, 10, location -> {
            if (location != null) {
                lastKnownLocation = location;
            }
        });

        FloatingActionButton centerMapButton = binding.centerMapButton;
        centerMapButton.setOnClickListener(v ->
        {
            if (lastKnownLocation != null) {
                currentLocation = lastKnownLocation;
                mapController.animateTo(new GeoPoint(currentLocation));
            }
        });

        binding.newButton.setOnClickListener(this::onNewListingClicked);

        Context ctx = getContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        map = binding.map;
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        mapController = map.getController();
        mapController.setZoom(15.0);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            FragmentActivity activity = requireActivity();
            if (activity instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) requireActivity();
                mainActivity.updateListings();
            }
        });

        return binding.getRoot();
    }

    private void makeGreeting(User currentUser) {
        binding.helloView.setText(new SpannableStringBuilder()
                .append(getString(R.string.welcome))
                .append(currentUser.getName(), new StyleSpan(Typeface.BOLD), 0)
        );
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void updateMarkers(List<Listing> listings) {

        List<Marker> markerList = new ArrayList<>();

        if (listings == null || map == null) return;

        for (Listing listing : listings) {
            try {
                Marker marker = new Marker(map);
                marker.setPosition(new GeoPoint(Double.parseDouble(listing.getCoordinatesX()), Double.parseDouble(listing.getCoordinatesY())));
                marker.setIcon(getResources().getDrawable(R.drawable.ic_baseline_person_pin_circle_48, requireContext().getTheme()));

                marker.setOnMarkerClickListener((mark, mapView) -> {
                    onListingPressed(listing);
                    return false;
                });
                markerList.add(marker);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        map.getOverlays().addAll(markerList);
        if (!markerList.isEmpty()) {
            mapController.animateTo(markerList.get(0).getPosition());
        }
    }

    private void onListingPressed(Listing listing) {
        Intent intent = new Intent(requireActivity(), ListingDetailsActivity.class);
        intent.putExtra("listing", listing);

        if (requireActivity() instanceof MainActivity) {
            mainActivity = (MainActivity) requireActivity();
            intent.putExtra("loginData",mainActivity.getLoginData());
        }

        startActivity(intent);
    }

    private void onNewListingClicked(View v) {
        FragmentActivity activity = requireActivity();
        if (activity instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) requireActivity();
            Intent intent = new Intent(activity, NewListingActivity.class);
            intent.putExtra("region", mainActivity.getRegion().getValue());
            intent.putExtra("user", mainActivity.getCurrentUser().getValue());
            intent.putExtra("token", mainActivity.getLoginData().getString("token"));
            intent.putExtra("location", lastKnownLocation);
            launcher.launch(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}