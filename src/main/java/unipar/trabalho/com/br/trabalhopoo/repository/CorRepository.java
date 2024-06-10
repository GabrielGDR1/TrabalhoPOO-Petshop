package unipar.trabalho.com.br.trabalhopoo.repository;

import unipar.trabalho.com.br.trabalhopoo.exceptions.DatabaseException;
import unipar.trabalho.com.br.trabalhopoo.models.Cor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CorRepository {
    private final Connection connection;

    public CorRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(Cor cor) {
        String sql = "INSERT INTO cor (nome) VALUES (?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cor.getNome());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cor.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao inserir cor", e);
        }
    }

    public List<Cor> findAll() {
        List<Cor> cores = new ArrayList<>();
        String sql = "SELECT * FROM cor";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cor cor = new Cor(rs.getLong("id"), rs.getString("nome"));
                cores.add(cor);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar cores", e);
        }
        return cores;
    }
}