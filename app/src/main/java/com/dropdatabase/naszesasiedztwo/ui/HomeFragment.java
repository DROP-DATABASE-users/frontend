package com.dropdatabase.naszesasiedztwo.ui;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dropdatabase.naszesasiedztwo.MainActivityViewModel;
import com.dropdatabase.naszesasiedztwo.R;
import com.dropdatabase.naszesasiedztwo.databinding.FragmentHomeBinding;
import com.dropdatabase.naszesasiedztwo.models.Listing;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private MapView map;
    private IMapController mapController;

    private LocationManager locationManager;



    private Location currentLocation;
    private Location lastKnownLocation;
    private MainActivityViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        viewModel.addListingCallback(this::updateMarkers);

        final TextView helloView = binding.helloView;
        viewModel.getCurrentUser().observe(getViewLifecycleOwner(),
                s -> helloView.setText(new SpannableStringBuilder()
                        .append(getString(R.string.welcome))
                        .append(s.getName(), new StyleSpan(Typeface.BOLD),
                                0)
                ));




        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(requireActivity(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500, 10, location -> {
                if (location != null) {
                    lastKnownLocation = location;
                    if(currentLocation == null) {
       //                 mapController.animateTo(new GeoPoint(lastKnownLocation));
                    }
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


        // set up map settings
        Context ctx = getContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        map = binding.map;
        map.setTileSource(TileSourceFactory.MAPNIK);
        mapController = map.getController();
        mapController.setZoom(10.0);

/*
        GeoPoint test_point = new GeoPoint(3.1201294 ,13.123123);

        Marker test_marker = new Marker(map);
        test_marker.setPosition(test_point);

        test_marker.setOnMarkerClickListener((marker, mapView) -> {
            Toast.makeText(requireActivity(), "clicked marker!", Toast.LENGTH_SHORT).show();
            return false;
        });

        List<Marker> markers = new ArrayList<Marker>();

        markers.add(test_marker);


        map.getOverlays().addAll(markers);
        mapController.animateTo(test_point);
*/


        return binding.getRoot();
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(requireActivity(), new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void updateMarkers() {
        List<Marker> markerList = new ArrayList<>();

        List<Listing> listings = viewModel.getListings().getValue();
        if (listings == null || map == null) return;
        for(Listing listing : listings) {
            Marker marker = new Marker(map);
            marker.setPosition(new GeoPoint(Double.parseDouble(listing.getCoordinatesX()), Double.parseDouble(listing.getCoordinatesY())));
            marker.setIcon(getResources().getDrawable(R.drawable.ic_baseline_person_pin_circle_32, requireContext().getTheme()));

            marker.setOnMarkerClickListener((mark, mapView) -> {
                onListingPressed(listing);
                return false;
            });
            markerList.add(marker);
        }
        map.getOverlays().addAll(markerList);
        mapController.animateTo(markerList.get(0).getPosition());
    }

    private void onListingPressed(Listing listing) {
        Toast.makeText(requireActivity(),listing.getTitle(),Toast.LENGTH_SHORT).show();
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