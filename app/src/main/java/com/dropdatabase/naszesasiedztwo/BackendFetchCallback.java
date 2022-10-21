package com.dropdatabase.naszesasiedztwo;

public interface BackendFetchCallback<T> {
    void onReceived(T data);
}
