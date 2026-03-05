package com.weg.gestaoescolar.GestaoEscolar.repository;

import com.weg.gestaoescolar.GestaoEscolar.dao.Conexao;
import com.weg.gestaoescolar.GestaoEscolar.model.Nota;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotaRepository {

    public Nota cadastrarNota(Nota nota) throws SQLException {

        String query = """
                INSERT INTO nota
                (aluno_id, aula_id, valor)
                VALUES
                (?, ?, ?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, nota.getAlunoId());
            stmt.setInt(2, nota.getAulaId());
            stmt.setDouble(3, nota.getValor());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                nota.setId(rs.getInt(1));
            }
        }

        return nota;
    }

    public List<Nota> listarNotas() throws SQLException {

        List<Nota> notas = new ArrayList<>();

        String query = """
                SELECT
                    n.id,
                    n.aluno_id,
                    n.aula_id,
                    n.valor,
                    a.nome AS aluno_nome,
                    au.assunto AS aula_assunto
                FROM nota n
                JOIN aluno a ON n.aluno_id = a.id
                JOIN aula au ON n.aula_id = au.id
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                notas.add(new Nota(
                        rs.getInt("id"),
                        rs.getInt("aluno_id"),
                        rs.getInt("aula_id"),
                        rs.getDouble("valor"),
                        rs.getString("aluno_nome"),
                        rs.getString("aula_assunto")
                ));
            }
        }

        return notas;
    }

    public Nota buscarPorId(int id) throws SQLException {

        String query = """
                SELECT
                    n.id,
                    n.aluno_id,
                    n.aula_id,
                    n.valor,
                    a.nome AS aluno_nome,
                    au.assunto AS aula_assunto
                FROM nota n
                JOIN aluno a ON n.aluno_id = a.id
                JOIN aula au ON n.aula_id = au.id
                WHERE n.id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Nota(
                        rs.getInt("id"),
                        rs.getInt("aluno_id"),
                        rs.getInt("aula_id"),
                        rs.getDouble("valor"),
                        rs.getString("aluno_nome"),
                        rs.getString("aula_assunto")
                );
            }
        }

        return null;
    }

    public void atualizarNota(Nota nota) throws SQLException {

        String query = """
                UPDATE nota
                SET valor = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, nota.getValor());
            stmt.setInt(2, nota.getId());

            stmt.executeUpdate();
        }
    }

    public boolean deletarNota(int id) throws SQLException {

        String query = """
                DELETE FROM nota
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();

            return linhas > 0;
        }
    }
}