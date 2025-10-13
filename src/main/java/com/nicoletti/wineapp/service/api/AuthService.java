package com.nicoletti.wineapp.service.api;

import java.sql.Connection;

public interface AuthService {

    Connection authenticate(String usuario, char[] senha);

}
