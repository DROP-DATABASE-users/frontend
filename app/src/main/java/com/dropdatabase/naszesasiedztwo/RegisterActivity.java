package com.dropdatabase.naszesasiedztwo;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dropdatabase.naszesasiedztwo.databinding.ActivityLoginBinding;
import com.dropdatabase.naszesasiedztwo.databinding.ActivityRegisterBinding;
import com.dropdatabase.naszesasiedztwo.models.RegisterData;
import com.dropdatabase.naszesasiedztwo.services.AccountService;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onRegisterClicked(View view) {
        AccountService as = new AccountService(this);
        String firstName = binding.firstnameInput.getText().toString();
        String lastName = binding.lastnameInput.getText().toString();
        String description = binding.descriptionInput.getText().toString();
        String nick = binding.nickInput.getText().toString();
        String pass = binding.registerPasswordInput.getText().toString();
        as.register(new RegisterData(firstName, lastName, description, nick, pass), this::finish, errorMessage -> binding.registerErrorMsg.setText(errorMessage));
    }
}