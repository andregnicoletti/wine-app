package com.nicoletti.wineapp.dao;

import com.nicoletti.wineapp.model.PaisVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO implements HelperDAO<PaisVO> {

    /**
     * Objeto de conexão com o banco
     */
    private final Connection conexao;

    /**
     * Comandos de acesso a dados
     */
    private static final String SELECT_PAISES_BY_NOME = "SELECT * FROM PAISES WHERE NOME =?";
    private static final String INSERT_PAISES = "INSERT INTO PAISES (NOME) VALUES (?)";
    private static final String SELECT_PAISES_BY_ID = "SELECT * FROM PAISES WHERE ID = ?";
    private static final String UPDATE_PAISES = "UPDATE PAISES SET NOME = ? WHERE ID = ?";
    private static final String DELETE_PAISES = "DELETE FROM PAISES WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM PAISES";

    private final PreparedStatement pstSelectPaisesByNome;
    private final PreparedStatement pstInsertPaises;
    private final PreparedStatement pstSelectPaisesById;
    private final PreparedStatement pstUpdatePaises;
    private final PreparedStatement pstDeletePaises;
    private final PreparedStatement pstSelectAllPaises;


    public PaisDAO(Connection conexao) throws SQLException {
        this.conexao = conexao;
        this.pstSelectPaisesByNome = conexao.prepareStatement(SELECT_PAISES_BY_NOME);
        this.pstInsertPaises = conexao.prepareStatement(INSERT_PAISES);
        this.pstSelectPaisesById = conexao.prepareStatement(SELECT_PAISES_BY_ID);
        this.pstUpdatePaises = conexao.prepareStatement(UPDATE_PAISES);
        this.pstDeletePaises = conexao.prepareStatement(DELETE_PAISES);
        this.pstSelectAllPaises = conexao.prepareStatement(SELECT_ALL);
    }

    @Override
    public void save(PaisVO vo) throws SQLException {
        pstInsertPaises.setString(1, vo.nome());
        pstInsertPaises.executeUpdate();
    }

    @Override
    public void update(PaisVO vo) throws SQLException {
        pstUpdatePaises.setString(1, vo.nome());
        pstUpdatePaises.executeUpdate();
    }

    @Override
    public void deleteById(long id) throws SQLException {
        pstDeletePaises.setLong(1, id);
        pstDeletePaises.executeUpdate();

    }

    @Override
    public PaisVO findById(long id) throws SQLException {
        pstSelectPaisesById.setLong(1, id);
        try (ResultSet resultado = pstSelectPaisesById.executeQuery()) {
            if (resultado.next()) {
                PaisVO usuario = new PaisVO(
                        resultado.getLong("ID"),
                        resultado.getString("NOME"));
            }
        }
        return null;
    }

    @Override
    public List<PaisVO> findAll() throws SQLException {
        List<PaisVO> paises = new ArrayList<>();
        try (ResultSet resultado = pstSelectAllPaises.executeQuery()) {
            while (resultado.next()) {
                PaisVO vo = new PaisVO(
                        resultado.getLong("ID"),
                        resultado.getString("NOME"));
                paises.add(vo);
            }
        }
        return paises;
    }

    public boolean selectByNome(PaisVO vo) throws SQLException {
        pstSelectPaisesByNome.setString(1, vo.nome());
        ResultSet resultado = pstSelectPaisesByNome.executeQuery();
        return resultado != null && resultado.next();
    }

}
