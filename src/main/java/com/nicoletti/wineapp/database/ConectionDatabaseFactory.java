package com.nicoletti.wineapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionDatabaseFactory {

    public static Connection getConnection(final String enderecoIp,
                                           final int porta,
                                           final String nomeBanco,
                                           final String usuario,
                                           final String senha) throws SQLException {

        String url = "jdbc:postgresql://" + enderecoIp + ":" + porta + "/" + nomeBanco;
        Connection connection = DriverManager.getConnection(url, usuario, senha);
        return connection;
    }

}
