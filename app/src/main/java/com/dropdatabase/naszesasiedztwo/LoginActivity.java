package com.dropdatabase.naszesasiedztwo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.dropdatabase.naszesasiedztwo.databinding.ActivityLoginBinding;
import com.dropdatabase.naszesasiedztwo.models.LoginData;
import com.dropdatabase.naszesasiedztwo.models.User;
import com.dropdatabase.naszesasiedztwo.services.AccountService;
import com.dropdatabase.naszesasiedztwo.services.ListingService;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    private AccountService accountService;

    private MutableLiveData<String> error = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accountService = new AccountService(this);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


    public void onLoginClicked(View view) {
        Intent result = new Intent();

        LoginData loginData = new LoginData();
        loginData.setUsername(binding.usernameInput.getText().toString());
        loginData.setPassword(binding.passwordInput.getText().toString());

        this.error.observe(this, msg -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show());

        accountService.login(
                loginData,
                token -> {
                    Bundle loginBundle = new Bundle();
                    int selectedRegion = (int) binding.regionSpinner.getSelectedItemId();

                    loginBundle.putInt("region", selectedRegion);
                    loginBundle.putString("token", token);

                    accountService.getUserData(
                            token,
                            user -> {
                                loginBundle.putSerializable("user", user);
                                result.putExtra("loginData",loginBundle);
                                setResult(RESULT_OK, result);
                                finish();
                            },
                            errorMessage -> {
                                error.setValue(errorMessage);
                            }
                    );



                },
                errorMessage -> {
                    error.setValue(errorMessage);
                }
        );
    }
}