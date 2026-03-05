package com.weg.gestaoescolar.GestaoEscolar.repository;

import com.weg.gestaoescolar.GestaoEscolar.dao.Conexao;
import com.weg.gestaoescolar.GestaoEscolar.model.Aula;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AulaRepository {

    public Aula cadastroAula(Aula aula) throws SQLException {

        String query = """
                INSERT INTO aula
                (turma_id,data_hora,assunto)
                VALUES
                (?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, aula.getTurma_id());
            stmt.setTimestamp(2, Timestamp.valueOf(aula.getData_hora()));
            stmt.setString(3, aula.getAssunto());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()) {
                aula.setId(rs.getInt(1));
            }
        }
        return aula;
    }

    public List<Aula> listarAula() throws SQLException {
        List<Aula> aulas = new ArrayList<>();

        String query = """
            SELECT
                a.id,
                a.turma_id,
                a.data_hora,
                a.assunto,
                t.nome AS nome_turma
            FROM aula a
            JOIN turma t ON a.turma_id = t.id
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                aulas.add(new Aula(
                        rs.getInt("id"),
                        rs.getInt("turma_id"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        rs.getString("assunto"),
                        rs.getString("nome_turma")
                ));
            }
        }
        return aulas;
    }

    public Aula buscaPorId(int id) throws SQLException {

        String query = """
            SELECT
                a.id,
                a.turma_id,
                a.data_hora,
                a.assunto,
                t.nome AS nome_turma
            FROM aula a
            JOIN turma t ON a.turma_id = t.id
            WHERE a.id = ?
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Aula(
                        rs.getInt("id"),
                        rs.getInt("turma_id"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        rs.getString("assunto"),
                        rs.getString("nome_turma")
                );
            }
        }
        return null;
    }
    public void atualizaAula(Aula aula) throws SQLException {
        String query = """
                UPDATE aula
                SET data_hora = ?, assunto = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setTimestamp(1, Timestamp.valueOf(aula.getData_hora()));
            stmt.setString(2, aula.getAssunto());
            stmt.setInt(3, aula.getId());
            stmt.executeUpdate();
        }
    }

    public boolean deletaAula(int id) throws SQLException {
        String query = """
                DELETE FROM aula
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        }
    }

}
