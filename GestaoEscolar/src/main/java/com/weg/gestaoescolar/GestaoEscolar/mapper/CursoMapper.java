package com.weg.gestaoescolar.GestaoEscolar.mapper;

import com.weg.gestaoescolar.GestaoEscolar.dto.cursoDto.CursoRequisicaoDto;
import com.weg.gestaoescolar.GestaoEscolar.dto.cursoDto.CursoRespostaDto;
import com.weg.gestaoescolar.GestaoEscolar.model.Curso;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CursoMapper {

    public Curso paraEntidade(CursoRequisicaoDto cursoRequisicaoDto) {
        return new Curso(
                cursoRequisicaoDto.nome(),
                cursoRequisicaoDto.codigo()
        );
    }

    public CursoRespostaDto paraResposta(Curso curso) {
        return new CursoRespostaDto(
                curso.getId(),
                curso.getNome(),
                curso.getCodigo()
        );
    }

    public List<CursoRespostaDto> paraListaResposta(List<Curso> cursos) {
        List<CursoRespostaDto> lista = new ArrayList<>();

        for (Curso curso : cursos) {
            lista.add(paraResposta(curso));
        }
        return lista;
    }
}
