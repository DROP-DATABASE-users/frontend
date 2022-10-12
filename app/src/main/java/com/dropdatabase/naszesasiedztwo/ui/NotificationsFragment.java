package com.dropdatabase.naszesasiedztwo.ui;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dropdatabase.naszesasiedztwo.MainActivityViewModel;
import com.dropdatabase.naszesasiedztwo.R;
import com.dropdatabase.naszesasiedztwo.adapters.ListingAdapter;
import com.dropdatabase.naszesasiedztwo.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

        MainActivityViewModel viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        RecyclerView listingsView = binding.rvListings;

        listingsView.setAdapter(new ListingAdapter(viewModel.getListings().getValue()));

        listingsView.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}