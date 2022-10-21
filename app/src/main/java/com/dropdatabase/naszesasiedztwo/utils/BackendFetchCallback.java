package com.dropdatabase.naszesasiedztwo.utils;

public interface BackendFetchCallback<T> {
    void onReceived(T data);
}
