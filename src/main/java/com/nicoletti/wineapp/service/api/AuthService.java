package com.nicoletti.wineapp.service.api;

public interface AuthService {

    boolean authenticate(String usuario, char[] senha);

}
