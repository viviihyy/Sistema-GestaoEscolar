package com.weg.gestaoescolar.GestaoEscolar.mapper;

import com.weg.gestaoescolar.GestaoEscolar.dto.professorDto.ProfessorRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.professorDto.ProfessorRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.model.Professor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfessorMapper {

    public Professor paraEntidade(ProfessorRequisicaoDto professorRequisicaoDto) {
        return new Professor(
                professorRequisicaoDto.nome(),
                professorRequisicaoDto.email(),
                professorRequisicaoDto.disciplina()
        );
    }

    public ProfessorRespostaDto paraResposta(Professor professor) {
        return new ProfessorRespostaDto(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getDisciplina()
        );
    }

    public List<ProfessorRespostaDto> paraListaResposta(List<Professor> professores) {
        List<ProfessorRespostaDto> listaResposta = new ArrayList<>();

        for (Professor professor : professores) {
            listaResposta.add(paraResposta(professor));
        }
        return listaResposta;
    }

}
