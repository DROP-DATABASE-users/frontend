package com.dropdatabase.naszesasiedztwo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private final MutableLiveData<String> userName;

    public MainActivityViewModel() {
        this.userName = new MutableLiveData<>();
        userName.setValue("Mietek");
    }

    public MutableLiveData<String> getUserName() {
        return userName;
    }
}
