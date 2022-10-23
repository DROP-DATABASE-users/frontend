package com.dropdatabase.naszesasiedztwo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dropdatabase.naszesasiedztwo.MainActivity;
import com.dropdatabase.naszesasiedztwo.adapters.ListingAdapter;
import com.dropdatabase.naszesasiedztwo.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        if (requireActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) requireActivity();

            mainActivity.getCurrentUser().observe(mainActivity,user ->
            {
                Toast.makeText(mainActivity, user.getName(), Toast.LENGTH_SHORT).show();
            });

        }


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}