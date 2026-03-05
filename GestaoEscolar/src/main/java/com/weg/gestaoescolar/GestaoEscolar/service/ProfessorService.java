package com.weg.gestaoescolar.GestaoEscolar.service;

import com.weg.gestaoescolar.GestaoEscolar.dto.professorDto.ProfessorRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.professorDto.ProfessorRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.mapper.ProfessorMapper;
import com.weg.gestaoescolar.GestaoEscolar.model.Professor;
import com.weg.gestaoescolar.GestaoEscolar.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    public ProfessorRespostaDto cadastroProfessor(ProfessorRequisicaoDto professorRequisicaoDto) throws SQLException {

        Professor professor = professorMapper.paraEntidade(professorRequisicaoDto);
        Professor cadastro = professorRepository.cadastroProfessor(professor);
        return professorMapper.paraResposta(cadastro);
    }

    public List<ProfessorRespostaDto> listaProfessor() throws SQLException {
        List<Professor> professores = professorRepository.listaProfessor();
        return professorMapper.paraListaResposta(professores);
    }

    public ProfessorRespostaDto buscaPorId(int id) throws SQLException {
        Professor professor = professorRepository.buscaPorId(id);

        if (professor == null) {
            throw new RuntimeException("Professor não encontrado");
        }

        return professorMapper.paraResposta(professor);
    }

    public ProfessorRespostaDto atualizaProfessor(ProfessorRequisicaoDto professorRequisicaoDto, int id) throws SQLException {
        Professor professor = professorMapper.paraEntidade(professorRequisicaoDto);
        professor.setId(id);
        professorRepository.atualizaProfessor(professor);
        return professorMapper.paraResposta(professor);
    }

    public boolean deletaProfessor(int id) throws SQLException {
        return professorRepository.deletaProfessor(id);
    }
}
