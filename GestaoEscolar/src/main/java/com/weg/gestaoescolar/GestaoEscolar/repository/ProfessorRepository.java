package com.weg.gestaoescolar.GestaoEscolar.repository;

import com.weg.gestaoescolar.GestaoEscolar.dao.Conexao;
import com.weg.gestaoescolar.GestaoEscolar.model.Professor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorRepository {

    public Professor cadastroProfessor(Professor professor) throws SQLException {
        String query = """
                INSERT INTO professor
                (nome,email,disciplina)
                VALUES
                (?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                professor.setId(rs.getInt(1));
            }
        }
        return professor;
    }

    public List<Professor> listaProfessor() throws SQLException {
        List<Professor> professores = new ArrayList<>();

        String query = """
                SELECT   id
                        ,nome
                        ,email
                        ,disciplina
                FROM professor
                """;

        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                professores.add(new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("disciplina")
                ));
            }
        }
        return professores;
    }

    public Professor buscaPorId(int id) throws SQLException {
        String query = """
                SELECT   id
                        ,nome
                        ,email
                        ,disciplina
                FROM professor
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("disciplina")
                );
            }
        }
        return null;
    }

    public void atualizaProfessor(Professor professor) throws SQLException {
        String query = """
                UPDATE professor
                SET nome = ?, email = ?, disciplina = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.setInt(4, professor.getId());
            stmt.executeUpdate();
        }
    }

    public boolean deletaProfessor(int id) throws SQLException {
        String query = """
                DELETE FROM professor
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
