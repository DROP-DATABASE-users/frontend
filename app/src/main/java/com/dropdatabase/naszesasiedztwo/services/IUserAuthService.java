package com.dropdatabase.naszesasiedztwo.services;

import com.dropdatabase.naszesasiedztwo.models.User;

import java.util.Optional;

public interface IUserAuthService {
    void login(String username, String password) throws Exception;
    void register(String username, String password, String email) throws Exception;
    Optional<User> getLoggedUser();
}
