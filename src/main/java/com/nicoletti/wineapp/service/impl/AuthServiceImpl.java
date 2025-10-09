package com.nicoletti.wineapp.service.impl;

import com.nicoletti.wineapp.dao.UsuarioDAO;
import com.nicoletti.wineapp.database.ConnectionDatabaseFactory;
import com.nicoletti.wineapp.model.UsuarioVO;
import com.nicoletti.wineapp.service.api.AuthService;
import com.nicoletti.wineapp.utils.HashUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthServiceImpl implements AuthService {

    @Override
    public boolean authenticate(String usuario, char[] senha) {
        boolean isAuth = false;
        try {
            Connection connection = ConnectionDatabaseFactory.getConnection("localhost", 5432, "db_wine", "postgres", "postgres");

            UsuarioVO usuarioVO = new UsuarioVO();
            usuarioVO.setUsuario(usuario);
            usuarioVO.setSenha(HashUtils.criarMD5(String.valueOf(senha)));

            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            isAuth = usuarioDAO.selectByUsuarioESenha(usuarioVO);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro na autenticação: " + e.getMessage());
        }
        return isAuth;
    }
}
