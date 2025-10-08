package com.nicoletti.wineapp.service.impl;

import com.nicoletti.wineapp.dao.UsuarioDAO;
import com.nicoletti.wineapp.database.ConectionDatabaseFactory;
import com.nicoletti.wineapp.model.UsuarioVO;
import com.nicoletti.wineapp.service.api.AuthService;
import com.nicoletti.wineapp.utils.HashUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class AuthServiceImpl implements AuthService {

    @Override
    public boolean authenticate(String usuario, char[] senha) {

        boolean isAuth = false;

        try {
            Connection connection = ConectionDatabaseFactory.getConnection("localhost", 5432, "db_wine", "postgres", "postgres");
            if (Objects.isNull(connection)) {
                JOptionPane.showMessageDialog(null, "Falha na conexão!");
            } else {

                UsuarioVO usuarioVO = new UsuarioVO();
                usuarioVO.setUsuario(usuario);
                usuarioVO.setSenha(HashUtils.criarMD5(String.valueOf(senha)));

                UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
                boolean thereIsUser = usuarioDAO.selectByUsuarioESenha(usuarioVO);

                if (!thereIsUser) {
                    JOptionPane.showMessageDialog(null, "Erro ao autenticar!");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário autenticado com sucesso!");
                    isAuth = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel conectar no banco: " + e.getMessage());
        }

        return isAuth;
    }
}
