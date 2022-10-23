package com.dropdatabase.naszesasiedztwo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dropdatabase.naszesasiedztwo.MainActivity;
import com.dropdatabase.naszesasiedztwo.adapters.ListingAdapter;
import com.dropdatabase.naszesasiedztwo.databinding.FragmentListingsBinding;
import com.dropdatabase.naszesasiedztwo.models.Listing;

import java.util.List;

public class ListingsFragment extends Fragment {

    private FragmentListingsBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListingsBinding.inflate(inflater, container, false);


        RecyclerView listingsView = binding.rvListings;


        if(requireActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) requireActivity();

            List<Listing> listingList = mainActivity.getListings().getValue();
            if (listingList != null) {
                listingsView.setAdapter(new ListingAdapter(mainActivity.getListings().getValue()));
            }
        }


        listingsView.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}