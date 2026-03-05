package com.weg.gestaoescolar.GestaoEscolar.service;

import com.weg.gestaoescolar.GestaoEscolar.dto.turmaDto.TurmaRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.turmaDto.TurmaRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.mapper.TurmaMapper;
import com.weg.gestaoescolar.GestaoEscolar.model.Turma;
import com.weg.gestaoescolar.GestaoEscolar.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final TurmaMapper turmaMapper;

    public TurmaService(TurmaRepository turmaRepository, TurmaMapper turmaMapper) {
        this.turmaRepository = turmaRepository;
        this.turmaMapper = turmaMapper;
    }

    public TurmaRespostaDto cadastroTurma(TurmaRequisicaoDto turmaRequisicaoDto) throws SQLException {
        Turma turma = turmaMapper.paraEntidade(turmaRequisicaoDto);
        Turma cadastro = turmaRepository.cadastroTurma(turma, turmaRequisicaoDto.listaAlunosIds());
        return turmaMapper.paraResposta(cadastro, "", "", new ArrayList<>());
    }

    public List<TurmaRespostaDto> listaTurma() throws SQLException {
        List<Turma> turmas = turmaRepository.listaTurma();
        return turmaMapper.paraListaResposta(turmas);
    }

    public TurmaRespostaDto buscaPorId(int id) throws SQLException {
        Turma turma = turmaRepository.buscaPorId(id);

        if (turma == null) {
            throw new RuntimeException("Turma não encontrada");
        }

        return turmaMapper.paraResposta(turma, "", "", new ArrayList<>());
    }

    public TurmaRespostaDto atualizaTurma(TurmaRequisicaoDto turmaRequisicaoDto, int id) throws SQLException {
        Turma turma = turmaMapper.paraEntidade(turmaRequisicaoDto);
        turma.setId(id);
        turmaRepository.atualizaTurma(turma);
        return turmaMapper.paraResposta(turma, "", "", new ArrayList<>());
    }

    public boolean deletaTurma(int id) throws SQLException {
        return turmaRepository.deletaTurma(id);
    }

    public List<TurmaRespostaDto> listarTurmasPorCurso(int cursoId) throws SQLException {
        return turmaMapper.paraListaResposta(
                turmaRepository.listarTurmasPorCurso(cursoId)
        );
    }
}
