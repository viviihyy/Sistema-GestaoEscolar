package com.weg.gestaoescolar.GestaoEscolar.mapper;

import com.weg.gestaoescolar.GestaoEscolar.dto.turmaDto.TurmaRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.turmaDto.TurmaRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.model.Turma;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TurmaMapper {

    public Turma paraEntidade(TurmaRequisicaoDto turmaRequisicaoDto) {
        return new Turma(
            turmaRequisicaoDto.nome(),
            turmaRequisicaoDto.curso_id(),
            turmaRequisicaoDto.professor_id()
        );
    }

    public TurmaRespostaDto paraResposta(Turma turma, String nomeCurso, String nomeProfessor, List<String> listaAlunos) {
        return new TurmaRespostaDto(
                turma.getId(),
                turma.getNome(),
                nomeCurso,
                nomeProfessor,
                listaAlunos
        );
    }

    public List<TurmaRespostaDto> paraListaResposta(List<Turma> turmas) {
        List<TurmaRespostaDto> lista = new ArrayList<>();

        for (Turma turma : turmas) {
            lista.add(paraResposta(turma, "", "", new ArrayList<>()));
        }
        return lista;
    }

}
