package unipar.trabalho.com.br.trabalhopoo.repository;

import unipar.trabalho.com.br.trabalhopoo.exceptions.DatabaseException;
import unipar.trabalho.com.br.trabalhopoo.models.Cachorro;
import unipar.trabalho.com.br.trabalhopoo.models.Cor;
import unipar.trabalho.com.br.trabalhopoo.models.Pelagem;
import unipar.trabalho.com.br.trabalhopoo.models.Raca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CachorroRepository {
    private final Connection connection;

    public CachorroRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(Cachorro cachorro) {
        String sql = "INSERT INTO cachorro (nome, raca_id, cor_id, pelagem_id) VALUES (?, ?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cachorro.getNome());
            stmt.setLong(2, cachorro.getRaca().getId());
            stmt.setLong(3, cachorro.getCor().getId());
            stmt.setLong(4, cachorro.getPelagem().getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cachorro.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao inserir cachorro", e);
        }
    }

    public List<Cachorro> findAll() {
        List<Cachorro> cachorros = new ArrayList<>();
        String sql = "SELECT c.id, c.nome, r.id AS raca_id, r.nome AS raca_nome, co.id AS cor_id, co.nome AS cor_nome, p.id AS pelagem_id, p.tipo AS pelagem_tipo " +
                     "FROM cachorro c " +
                     "JOIN raca r ON c.raca_id = r.id " +
                     "JOIN cor co ON c.cor_id = co.id " +
                     "JOIN pelagem p ON c.pelagem_id = p.id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Raca raca = new Raca(rs.getLong("raca_id"), rs.getString("raca_nome"));
                Cor cor = new Cor(rs.getLong("cor_id"), rs.getString("cor_nome"));
                Pelagem pelagem = new Pelagem(rs.getLong("pelagem_id"), rs.getString("pelagem_tipo"));
                Cachorro cachorro = new Cachorro(rs.getLong("id"), rs.getString("nome"), raca, cor, pelagem);
                cachorros.add(cachorro);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar cachorros", e);
        }
        return cachorros;
    }
}