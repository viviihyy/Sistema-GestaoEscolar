package com.weg.gestaoescolar.GestaoEscolar.mapper;

import com.weg.gestaoescolar.GestaoEscolar.dto.notaDto.NotaRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.notaDto.NotaRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class NotaMapper {

    public Nota paraEntidade(NotaRequisicaoDto dto) {
        return new Nota(
                dto.alunoId(),
                dto.aulaId(),
                dto.valor()
        );
    }

    public NotaRespostaDto paraResposta(Nota nota) {
        return new NotaRespostaDto(
                nota.getId(),
                nota.getAlunoNome(),
                nota.getAulaAssunto(),
                nota.getValor()
        );
    }

    public List<NotaRespostaDto> paraListaResposta(List<Nota> notas) {

        List<NotaRespostaDto> lista = new ArrayList<>();

        for (Nota nota : notas) {
            lista.add(paraResposta(nota));
        }

        return lista;
    }
}