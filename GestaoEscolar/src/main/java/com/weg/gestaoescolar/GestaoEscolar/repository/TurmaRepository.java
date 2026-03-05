package com.weg.gestaoescolar.GestaoEscolar.repository;

import com.weg.gestaoescolar.GestaoEscolar.dao.Conexao;
import com.weg.gestaoescolar.GestaoEscolar.model.Turma;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurmaRepository {

    public Turma cadastroTurma(Turma turma, List<Integer> listaAlunosIds) throws SQLException {

        String query = """
            INSERT INTO turma
            (nome,curso_id,professor_id)
            VALUES
            (?,?,?)
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getCurso_id());
            stmt.setInt(3, turma.getProfessor_id());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                turma.setId(rs.getInt(1));
            }

            String queryAluno = """
                INSERT INTO turma_aluno
                (turma_id, aluno_id)
                VALUES
                (?,?)
                """;

            try (PreparedStatement stmtAluno = conn.prepareStatement(queryAluno)) {
                for (Integer alunoId : listaAlunosIds) {
                    stmtAluno.setInt(1, turma.getId());
                    stmtAluno.setInt(2, alunoId);
                    stmtAluno.executeUpdate();
                }
            }
        }

        return turma;
    }

    public List<Turma> listaTurma() throws SQLException {

        List<Turma> turmas = new ArrayList<>();

        String query = """
        SELECT
            t.id,
            t.nome,
            t.curso_id,
            t.professor_id,
            c.nome AS nome_curso,
            p.nome AS nome_professor
        FROM turma t
        JOIN curso c ON t.curso_id = c.id
        JOIN professor p ON t.professor_id = p.id
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                turmas.add(new Turma(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("curso_id"),
                        rs.getInt("professor_id")
                ));
            }
        }

        return turmas;
    }

    public Turma buscaPorId(int id) throws SQLException {

        String query = """
        SELECT
            t.id,
            t.nome,
            t.curso_id,
            t.professor_id,
            c.nome AS nome_curso,
            p.nome AS nome_professor
        FROM turma t
        JOIN curso c ON t.curso_id = c.id
        JOIN professor p ON t.professor_id = p.id
        WHERE t.id = ?
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Turma(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("curso_id"),
                        rs.getInt("professor_id")
                );
            }
        }

        return null;
    }

    public void atualizaTurma(Turma turma) throws SQLException {

        String query = """
        UPDATE turma
        SET nome = ?
        WHERE id = ?
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getId());
            stmt.executeUpdate();
        }
    }

    public boolean deletaTurma(int id) throws SQLException {

        String query = """
        DELETE FROM turma
        WHERE id = ?
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        }
    }

    public List<Turma> listarTurmasPorCurso(int cursoId) throws SQLException {

        List<Turma> turmas = new ArrayList<>();

        String query = """
        SELECT
            id,
            nome,
            curso_id,
            professor_id
        FROM turma
        WHERE curso_id = ?
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, cursoId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                turmas.add(new Turma(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("curso_id"),
                        rs.getInt("professor_id")
                ));
            }
        }

        return turmas;
    }
}