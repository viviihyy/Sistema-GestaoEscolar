package com.weg.gestaoescolar.GestaoEscolar.dto.cursoDto;

import java.util.List;

public record CursoRequisicaoDto(
        String nome,
        String codigo,
        List<Integer> listaProfessorIds
) {
}
