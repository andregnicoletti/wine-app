package com.nicoletti.wineapp.dao;

import com.nicoletti.wineapp.model.UsuarioVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private final Connection conexao;
    private String selectUsuarioESenha = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
    private PreparedStatement pstSelectByUsuarioESenha;

    public UsuarioDAO(Connection conexao) throws SQLException {
        this.conexao = conexao;
        pstSelectByUsuarioESenha = this.conexao.prepareStatement(selectUsuarioESenha);
    }

    public boolean selectByUsuarioESenha(UsuarioVO vo) throws SQLException {
        pstSelectByUsuarioESenha.setString(1, vo.getUsuario());
        pstSelectByUsuarioESenha.setString(2, vo.getSenha());
        ResultSet resultado = pstSelectByUsuarioESenha.executeQuery();
        return resultado != null && resultado.next();
    }

}
