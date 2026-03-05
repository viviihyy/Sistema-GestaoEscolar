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

    public TurmaRespostaDto paraResposta(Turma turma) {
        return new TurmaRespostaDto(
                turma.getId(),
                turma.getNome(),
                turma.getCurso_id(),
                turma.getProfessor_id()
        );
    }

    public List<TurmaRespostaDto> paraListaResposta(List<Turma> turmas) {
        List<TurmaRespostaDto> lista = new ArrayList<>();

        for (Turma turma : turmas) {
            lista.add(paraResposta(turma));
        }
        return lista;
    }

}
