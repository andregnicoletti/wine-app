package com.nicoletti.wineapp.dao;

import com.nicoletti.wineapp.model.UsuarioVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO implements HelperDAO<UsuarioVO> {

    /**
     * Objeto de conexão com o banco
     */
    private final Connection conexao;

    /**
     * Comandos de acesso a dados
     */
    private static final String SELECT_USUARIO_BY_SENHA = "SELECT * FROM USUARIOS WHERE USUARIO =? AND SENHA = ?";
    private static final String INSERT_USUARIO = "INSERT INTO USUARIOS (USUARIO, SENHA, PERFIL) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM USUARIOS WHERE ID = ?";
    private static final String UPDATE_USUARIO = "UPDATE USUARIOS SET SENHA = ?, PERFIL = ? WHERE ID = ?";
    private static final String DELETE_USUARIO = "DELETE FROM USUARIOS WHERE ID = ?";

    private final PreparedStatement pstSelectByUsuarioESenha;
    private final PreparedStatement pstInsertUsuario;
    private final PreparedStatement pstSelectById;
    private final PreparedStatement pstUpdateUsuario;
    private final PreparedStatement pstDeleteUsuario;

    public UsuarioDAO(Connection conexao) throws SQLException {
        this.conexao = conexao;
        pstSelectByUsuarioESenha = this.conexao.prepareStatement(SELECT_USUARIO_BY_SENHA);
        pstInsertUsuario = this.conexao.prepareStatement(INSERT_USUARIO);
        pstSelectById = this.conexao.prepareStatement(SELECT_BY_ID);
        pstUpdateUsuario = this.conexao.prepareStatement(UPDATE_USUARIO);
        pstDeleteUsuario = this.conexao.prepareStatement(DELETE_USUARIO);
    }

    @Override
    public void save(UsuarioVO vo) throws SQLException {
        pstInsertUsuario.setString(1, vo.getUsuario());
        pstInsertUsuario.setString(2, vo.getSenha());
        pstInsertUsuario.setString(3, vo.getPerfil());
        pstInsertUsuario.executeUpdate();
    }

    @Override
    public void update(UsuarioVO vo) throws SQLException {
        pstUpdateUsuario.setString(1, vo.getSenha());
        pstUpdateUsuario.setString(2, vo.getPerfil());
        pstUpdateUsuario.setLong(3, vo.getId());
        pstUpdateUsuario.executeUpdate();
    }

    @Override
    public void deleteById(long id) throws SQLException {
        pstDeleteUsuario.setLong(1, id);
        pstDeleteUsuario.executeUpdate();
    }

    @Override
    public UsuarioVO findById(long id) throws SQLException {
        pstSelectById.setLong(1, id);
        try (ResultSet resultado = pstSelectById.executeQuery()) {
            if (resultado.next()) {
                UsuarioVO usuario = new UsuarioVO();
                usuario.setId(resultado.getLong("ID"));
                usuario.setUsuario(resultado.getString("USUARIO"));
                usuario.setSenha(resultado.getString("SENHA"));
                usuario.setPerfil(resultado.getString("PERFIL"));
            }
        }
        return null;
    }

    @Override
    public List<UsuarioVO> findAll() throws SQLException {
        return List.of();
    }

    public boolean selectByUsuarioESenha(UsuarioVO vo) throws SQLException {
        pstSelectByUsuarioESenha.setString(1, vo.getUsuario());
        pstSelectByUsuarioESenha.setString(2, vo.getSenha());
        ResultSet resultado = pstSelectByUsuarioESenha.executeQuery();
        return resultado != null && resultado.next();
    }

}
