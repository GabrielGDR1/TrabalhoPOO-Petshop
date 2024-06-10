package unipar.trabalho.com.br.trabalhopoo.repository;

import unipar.trabalho.com.br.trabalhopoo.exceptions.*;
import unipar.trabalho.com.br.trabalhopoo.models.Pelagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelagemRepository {
    private final Connection connection;

    public PelagemRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(Pelagem pelagem) {
        String sql = "INSERT INTO pelagem (tipo) VALUES (?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pelagem.getTipo());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pelagem.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao inserir pelagem", e);
        }
    }

    public List<Pelagem> findAll() {
        List<Pelagem> pelagens = new ArrayList<>();
        String sql = "SELECT * FROM pelagem";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pelagem pelagem = new Pelagem(rs.getLong("id"), rs.getString("tipo"));
                pelagens.add(pelagem);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar pelagens", e);
        }
        return pelagens;
    }
}