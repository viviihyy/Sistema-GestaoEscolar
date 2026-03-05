package com.weg.gestaoescolar.GestaoEscolar.service;

import com.weg.gestaoescolar.GestaoEscolar.dto.alunoDto.AlunoRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.alunoDto.AlunoRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.mapper.AlunoMapper;
import com.weg.gestaoescolar.GestaoEscolar.model.Aluno;
import com.weg.gestaoescolar.GestaoEscolar.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AlunoService {

    private final AlunoMapper alunoMapper;
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoMapper alunoMapper, AlunoRepository alunoRepository) {
        this.alunoMapper = alunoMapper;
        this.alunoRepository = alunoRepository;
    }

    public AlunoRespostaDto cadastroAluno(AlunoRequisicaoDto alunoRequisicaoDto) throws SQLException {
        Aluno aluno = alunoMapper.paraEntidade(alunoRequisicaoDto);
        Aluno cadastro = alunoRepository.cadastroAluno(aluno);
        return alunoMapper.paraResposta(cadastro);
    }

    public List<AlunoRespostaDto> listaAlunos() throws SQLException {
        List<Aluno> alunos = alunoRepository.listarAlunos();
        return alunoMapper.paraListaResposta(alunos);
    }

    public AlunoRespostaDto buscaPorId(int id) throws SQLException {
        Aluno aluno = alunoRepository.buscaPorId(id);

        if (aluno == null) {
            throw new RuntimeException("Aluno não encontrado");
        }
        return alunoMapper.paraResposta(aluno);
    }

    public AlunoRespostaDto atualizaAluno(int id, AlunoRequisicaoDto alunoRequisicaoDto) throws SQLException {
        Aluno aluno = alunoMapper.paraEntidade(alunoRequisicaoDto);
        aluno.setId(id);
        alunoRepository.atualizaAluno(aluno);
        return alunoMapper.paraResposta(aluno);
    }

    public boolean deletaAluno(int id) throws SQLException {
        return alunoRepository.deletaAluno(id);
    }

    public List<AlunoRespostaDto> listarAlunosPorTurma(int turmaId) throws SQLException {
        List<Aluno> alunos = alunoRepository.listarAlunosPorTurma(turmaId);
        return alunoMapper.paraListaResposta(alunos);
    }
}
