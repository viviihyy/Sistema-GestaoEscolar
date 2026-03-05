package com.weg.gestaoescolar.GestaoEscolar.mapper;

import com.weg.gestaoescolar.GestaoEscolar.dto.aulaDto.AulaRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.aulaDto.AulaRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.model.Aula;

import java.util.ArrayList;
import java.util.List;

public class AulaMapper {

    public Aula paraEntidade(AulaRequisicaoDto aulaRequisicaoDto) {
        return new Aula(
                aulaRequisicaoDto.turma_id(),
                aulaRequisicaoDto.data_hora(),
                aulaRequisicaoDto.assunto(),
                null
        );
    }

    public AulaRespostaDto paraResposta(Aula aula) {
        return new AulaRespostaDto(
                aula.getId(),
                aula.getNomeTurma(),
                aula.getData_hora(),
                aula.getAssunto()
        );
    }

    public List<AulaRespostaDto> paraListaResposta(List<Aula> aulas) {
        List<AulaRespostaDto> lista = new ArrayList<>();

        for (Aula aula : aulas) {
            lista.add(paraResposta(aula));
        }
        return lista;
    }
}
