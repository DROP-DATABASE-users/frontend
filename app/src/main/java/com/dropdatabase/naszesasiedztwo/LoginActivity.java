package com.dropdatabase.naszesasiedztwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dropdatabase.naszesasiedztwo.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onLoginClicked(View view) {
        Intent result = new Intent();
        result.putExtra("user", binding.usernameInput.getText().toString());
        setResult(RESULT_OK, result);
        finish();
    }
}