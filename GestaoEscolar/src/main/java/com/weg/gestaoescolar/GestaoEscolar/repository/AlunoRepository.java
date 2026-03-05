package com.weg.gestaoescolar.GestaoEscolar.repository;

import com.weg.gestaoescolar.GestaoEscolar.dao.Conexao;
import com.weg.gestaoescolar.GestaoEscolar.model.Aluno;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Repository
public class AlunoRepository {

    public Aluno cadastroAluno(Aluno aluno) throws SQLException {

        String query = """
                INSERT INTO aluno
                (nome,email,matricula,data_nascimento)
                VALUES
                (?,?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4, Date.valueOf(aluno.getData_nascimento()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()) {
                aluno.setId(rs.getInt(1));
            }
        }
        return aluno;
    }

    public List<Aluno> listarAlunos() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();

        String query = """
                SELECT   id
                        ,nome
                        ,email
                        ,matricula
                        ,data_nascimento
                FROM aluno
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getDate("data_nascimento").toLocalDate()
                ));
            }
        }
        return alunos;
    }

    public Aluno buscaPorId(int id) throws SQLException {
        String query = """
                SELECT   id
                        ,nome
                        ,email
                        ,matricula
                        ,data_nascimento
                FROM aluno
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getDate("data_nascimento").toLocalDate()
                );
            }
        }
        return null;
    }

    public void atualizaAluno(Aluno aluno) throws SQLException {
        String query = """
                UPDATE aluno
                SET nome = ?, email = ?, matricula = ?, data_nascimento = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4, Date.valueOf(aluno.getData_nascimento()));
            stmt.setInt(5, aluno.getId());
            stmt.executeUpdate();
        }
    }

    public boolean deletaAluno(int id) throws SQLException {
        String query = """
                DELETE FROM aluno
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        }
    }

    public List<Aluno> listarAlunosPorTurma(int turmaId) throws SQLException {

        List<Aluno> alunos = new ArrayList<>();

        String query = """
        SELECT
            a.id,
            a.nome,
            a.email,
            a.matricula,
            a.data_nascimento
        FROM aluno a
        JOIN turma_aluno ta ON a.id = ta.aluno_id
        WHERE ta.turma_id = ?
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, turmaId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getDate("data_nascimento").toLocalDate()
                ));
            }
        }

        return alunos;
    }

}
