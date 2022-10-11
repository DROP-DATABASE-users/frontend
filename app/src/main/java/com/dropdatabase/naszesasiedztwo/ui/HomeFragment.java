package com.dropdatabase.naszesasiedztwo.ui;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.dropdatabase.naszesasiedztwo.MainActivityViewModel;
import com.dropdatabase.naszesasiedztwo.R;
import com.dropdatabase.naszesasiedztwo.databinding.FragmentHomeBinding;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MainActivityViewModel viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        final TextView helloView = binding.helloView;
        viewModel.getUserName().observe(getViewLifecycleOwner(), s -> helloView.setText(new SpannableStringBuilder(getString(R.string.welcome)).append(s, new StyleSpan(Typeface.BOLD), 0)));
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}